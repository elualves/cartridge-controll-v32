package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Modelo;
import model.services.ModeloService;

public class CadastrarModeloController implements Initializable, DataChangeListener {

	private ModeloService service;

	@FXML
	private TableView<Modelo> tableViewModelo;

	@FXML
	private TableColumn<Modelo, Integer> tableColumnId;

	@FXML
	private TableColumn<Modelo, String> tableColumnModelo;

	@FXML
	private TableColumn<Modelo, Modelo> tableColumnEDIT;

	@FXML
	private TableColumn<Modelo, Modelo> tableColumnREMOVE;

	@FXML
	private Button btNovaModelo;

	private ObservableList<Modelo> obsList;

	@FXML
	public void onBtNovaModeloAction(ActionEvent event) {
		Modelo obj = new Modelo();
		Stage parentStage = Utils.currentStage(event);
		createDialogForm(obj, "/gui/ModeloForm.fxml", parentStage);
	}

	public void setModeloService(ModeloService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewModelo.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Modelo> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewModelo.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}

	private void createDialogForm(Modelo obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ModeloFormController controller = loader.getController();
			controller.setModelo(obj);
			controller.updateFormData();
			controller.subscribeDataChangeListener(this);
			controller.setModeloService(new ModeloService());

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Modelo");
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
		tableColumnEDIT.setCellFactory(param -> new TableCell<Modelo, Modelo>() {
			private final Button button = new Button("editar");

			@Override
			protected void updateItem(Modelo obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/ModeloForm.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Modelo, Modelo>() {
			private final Button button = new Button("remover");

			@Override
			protected void updateItem(Modelo obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(Modelo obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("CONFIRMAÇÃO", "Deseja realmente remover este item?");

		if (result.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
				service.remove(obj);
				updateTableView();
			} catch (DbIntegrityException e) {
				Alerts.showAlert("Erro ao remover", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
}
