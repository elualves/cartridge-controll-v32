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
import model.entities.Marca;
import model.services.MarcaService;

public class CadastrarMarcaController implements Initializable {
	
	private MarcaService service;
	
	@FXML
	private TableView<Marca> tableViewMarca;
	
	@FXML
	private TableColumn<Marca, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Marca, String> tableColumnMarca;
	
	@FXML
	private Button btNovaMarca;
	
	private ObservableList<Marca> obsList;
	
	@FXML
	public void onBtNovaMarcaAction() {
		System.out.println("bt Nova Marca Action");
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
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Marca> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewMarca.setItems(obsList);
	}

}
