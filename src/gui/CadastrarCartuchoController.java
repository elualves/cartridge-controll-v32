package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Cartucho;
import model.services.CartuchoService;

public class CadastrarCartuchoController implements Initializable, DataChangeListener {

	private CartuchoService service;

	@FXML
	private TableView<Cartucho> tableViewCartucho;
	
	
	@FXML
	private TextField txtCodigo;
	
	
	@FXML
	private Label lblMarca;
	
	@FXML
	private Label lblModelo;
	
	@FXML
	private Label lblCor;
	
	@FXML
	private Label lblTipo;

	@FXML
	private TableColumn<Cartucho, String> tableColumnUsuario;

	@FXML
	private TableColumn<Cartucho, Date> tableColumnDataEntrada;

	@FXML
	private TableColumn<Cartucho, Date> tableColumnDataSaida;
	
	@FXML
	private TableColumn<Cartucho, Double> tableColumnPesoEntrada;
	
	@FXML
	private TableColumn<Cartucho, Double> tableColumnPesoSaida;
	
	@FXML
	private TableColumn<Cartucho, String> tableColumnStatus;
	
	@FXML
	private TableColumn<Cartucho, String> tableColumnObs;

	@FXML
	private TableColumn<Cartucho, Cartucho> tableColumnEDIT;

	@FXML
	private TableColumn<Cartucho, Cartucho> tableColumnREMOVE;

	
	@FXML
	private Button btNovaRecarga;
	
	@FXML
	private Button btPesquisarRecarga;

	private ObservableList<Cartucho> obsList;

	@FXML
	public void onBtPesquisarRecargaqAction() {
		System.out.println("Botão Pesquisar Recarga OK!");
	}
	
	@FXML
	public void onBtNovaRecargaAction(ActionEvent event) {
		Cartucho obj = new Cartucho();
		Stage parentStage = Utils.currentStage(event);
		createDialogForm(obj, "/gui/CartuchoForm.fxml", parentStage);
	}
	
	
	
	

	public void setCartuchoService(CartuchoService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		
		tableColumnUsuario.setCellValueFactory(new PropertyValueFactory<>("clienteDireto"));
		tableColumnDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
		tableColumnDataSaida.setCellValueFactory(new PropertyValueFactory<>("dataSaida"));
		tableColumnPesoEntrada.setCellValueFactory(new PropertyValueFactory<>("pesoEntrada"));
		tableColumnPesoSaida.setCellValueFactory(new PropertyValueFactory<>("pesoSaida"));
		tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tableColumnObs.setCellValueFactory(new PropertyValueFactory<>("obs"));
		Utils.formatTableColumnDate(tableColumnDataEntrada, "dd/MM/yyyy");
		Utils.formatTableColumnDate(tableColumnDataSaida, "dd/MM/yyyy");
		Utils.formatTableColumnDouble(tableColumnPesoEntrada, 2);
		Utils.formatTableColumnDouble(tableColumnPesoSaida, 2);
		

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewCartucho.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		List<Cartucho> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewCartucho.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
		
	}

   	private void createDialogForm(Cartucho obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			CartuchoFormController<?> controller = loader.getController();
			controller.setCartucho(obj);
			controller.setServices(new CartuchoService(), null, null, null, null, null);
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Cartucho");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
		Alerts.showAlert("IO Exception", "Error loading view", e.getLocalizedMessage(), AlertType.ERROR);		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}

	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Cartucho, Cartucho>() {
			private final Button button = new Button("editar");

			@Override
			protected void updateItem(Cartucho obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/CartuchoForm.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Cartucho, Cartucho>() {
			private final Button button = new Button("remover");

			@Override
			protected void updateItem(Cartucho obj, boolean empty) {
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

	private void removeEntity(Cartucho obj) {
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
