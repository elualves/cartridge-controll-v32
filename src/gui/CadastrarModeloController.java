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
import model.entities.Modelo;
import model.services.ModeloService;

public class CadastrarModeloController implements Initializable {
	
	private ModeloService service;
	
	@FXML
	private TableView<Modelo> tableViewModelo;
	
	@FXML
	private TableColumn<Modelo, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Modelo, String> tableColumnModelo;
	
	@FXML
	private Button btNovaModelo;
	
	private ObservableList<Modelo> obsList;
	
	@FXML
	public void onBtNovaModeloAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/ModeloForm.fxml", parentStage);
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
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Modelo> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewModelo.setItems(obsList);
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Modelo");
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

