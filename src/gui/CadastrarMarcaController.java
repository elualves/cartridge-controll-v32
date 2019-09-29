package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Marca;
import model.services.MarcaService;

public class CadastrarMarcaController implements Initializable, DataChangeListener  {

	private MarcaService service;

	@FXML
	private TableView<Marca> tableViewMarca;

	@FXML
	private TableColumn<Marca, Integer> tableColumnId;

	@FXML
	private TableColumn<Marca, String> tableColumnMarca;
	
	@FXML
	private TableColumn<Marca, Marca> tableColumnEDIT;

	@FXML
	private Button btNovaMarca;

	private ObservableList<Marca> obsList;

	@FXML
	public void onBtNovaMarcaAction(ActionEvent event) {
		Marca obj = new Marca();
		Stage parentStage = Utils.currentStage(event);
		createDialogForm(obj, "/gui/MarcaForm.fxml", parentStage);
	}

	public void setMarcaService(MarcaService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewMarca.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Marca> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewMarca.setItems(obsList);
		initEditButtons();
	}

	private void createDialogForm(Marca obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			MarcaFormController controller = loader.getController();
			controller.setMarca(obj);
			controller.updateFormData();
			controller.subscribeDataChangeListener(this);
			controller.setMarcaService(new MarcaService());

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Marca");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getLocalizedMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();	
	}
	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Marca, Marca>() {
			private final Button button = new Button("edit");

			@Override
			protected void updateItem(Marca obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/MarcaForm.fxml", Utils.currentStage(event)));
			}
		});
	}
}
