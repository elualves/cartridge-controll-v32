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
import model.entities.Cor;
import model.services.CorService;

public class CadastrarCorController implements Initializable {
	
	private CorService service;
	
	@FXML
	private TableView<Cor> tableViewCor;
	
	@FXML
	private TableColumn<Cor, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Cor, String> tableColumnCor;
	
	@FXML
	private Button btNovaCor;
	
	private ObservableList<Cor> obsList;
	
	@FXML
	public void onBtNovaCorAction() {
		System.out.println("bt Nova Cor Action");
	}

	public void setCorService(CorService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewCor.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Cor> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewCor.setItems(obsList);
	}
}


