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
import model.entities.Status;
import model.services.StatusService;

public class CadastrarStatusController implements Initializable, DataChangeListener {

	private StatusService service;

	@FXML
	private TableView<Status> tableViewStatus;

	@FXML
	private TableColumn<Status, Integer> tableColumnId;

	@FXML
	private TableColumn<Status, String> tableColumnStatus;

	@FXML
	private TableColumn<Status, Status> tableColumnEDIT;

	@FXML
	private TableColumn<Status, Status> tableColumnREMOVE;

	@FXML
	private Button btNovaStatus;

	private ObservableList<Status> obsList;

	@FXML
	public void onBtNovaStatusAction(ActionEvent event) {
		Status obj = new Status();
		Stage parentStage = Utils.currentStage(event);
		createDialogForm(obj, "/gui/StatusForm.fxml", parentStage);
	}

	public void setStatusService(StatusService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewStatus.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Status> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewStatus.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}

	private void createDialogForm(Status obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			StatusFormController controller = loader.getController();
			controller.setStatus(obj);
			controller.updateFormData();
			controller.subscribeDataChangeListener(this);
			controller.setStatusService(new StatusService());

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Status");
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
		tableColumnEDIT.setCellFactory(param -> new TableCell<Status, Status>() {
			private final Button button = new Button("editar");

			@Override
			protected void updateItem(Status obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/StatusForm.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Status, Status>() {
			private final Button button = new Button("remover");

			@Override
			protected void updateItem(Status obj, boolean empty) {
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

	private void removeEntity(Status obj) {
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
