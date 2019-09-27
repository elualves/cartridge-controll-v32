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
import model.entities.Tipo;
import model.services.TipoService;

public class CadastrarTipoController implements Initializable {
	
	private TipoService service;
	
	@FXML
	private TableView<Tipo> tableViewTipo;
	
	@FXML
	private TableColumn<Tipo, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Tipo, String> tableColumnTipo;
	
	@FXML
	private Button btNovaTipo;
	
	private ObservableList<Tipo> obsList;
	
	@FXML
	public void onBtNovaTipoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/TipoForm.fxml", parentStage);
	}

	public void setTipoService(TipoService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewTipo.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Tipo> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewTipo.setItems(obsList);
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Tipo");
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


