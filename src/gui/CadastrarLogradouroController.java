package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Logradouro;
import model.services.LogradouroService;

public class CadastrarLogradouroController implements Initializable {

	private LogradouroService service;

	@FXML
	private TableView<Logradouro> tableViewLogradouro;

	@FXML
	private TableColumn<Logradouro, Integer> tableColumnId;

	@FXML
	private TableColumn<Logradouro, String> tableColumnLogradouro;
	
	@FXML
	private Button btNovoLogradouro;

	private ObservableList<Logradouro> obsList;
	
	@FXML
	public void onBtNovoLogradouroAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/LogradouroForm.fxml", parentStage);
	}

	public void setLogradouroService(LogradouroService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewLogradouro.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Logradouro> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewLogradouro.setItems(obsList);
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Logradouro");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getLocalizedMessage(), AlertType.ERROR);
		}
	}

}
