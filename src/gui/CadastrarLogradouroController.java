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
	private Button btNovaLogradouro;

	private ObservableList<Logradouro> obsList;

	@FXML
	public void onBtNovaLogradouroAction() {
		System.out.println("bt Nova Logradouro Action");
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

}
