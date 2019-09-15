package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public void onBtNovaModeloAction() {
		System.out.println("bt Nova Modelo Action");
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

}

