package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Cartucho;
import model.entities.Cor;
import model.entities.Marca;
import model.entities.Modelo;
import model.entities.Status;
import model.entities.Tipo;
import model.exceptions.ValidationException;
import model.services.CartuchoService;
import model.services.CorService;
import model.services.MarcaService;
import model.services.ModeloService;
import model.services.StatusService;
import model.services.TipoService;

public class CartuchoFormController<T> implements Initializable {

	private Cartucho entity;

	private CartuchoService service;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	
	@FXML
	private TextField txtUsuario;
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtUsuarioRetirada;
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private TextField txtCelular;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtPesoEntrada;
	
	@FXML
	private TextField txtPesoSaida;
	
	@FXML
	private TextField txtEndColeta;
	
	@FXML
	private TextField txtEndEntrega;
	
	@FXML
	private TextField txtContatoColeta;
	
	@FXML
	private TextField txtContatoEntrega;
	
	@FXML
	private ComboBox<Marca> cBMarca;
	
	@FXML
	private ComboBox<Modelo> cBModelo;
	
	@FXML
	private ComboBox<Cor> cBCor;
	
	@FXML
	private ComboBox<Tipo> cBTipo;
	
	@FXML
	private ComboBox<Status> cBStatus;
	
	@FXML
	private DatePicker dPDataEntrada;
	
	@FXML
	private DatePicker dPDataSaida;
	
	@FXML
	private Label labelErrorGeral;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;
	
	private ObservableList<T> obsList;

	private CorService Cor;
	
	private TipoService Tipo;
	
	private MarcaService Marca;
	
	private ModeloService Modelo;
	
	private StatusService Status;


	public void setCartucho(Cartucho entity) {
		this.entity = entity;
	}

	public void setServices(CartuchoService service, CorService corService, TipoService tipoService, MarcaService marcaService,
							ModeloService modeloServioce, StatusService statusService) {
		this.service = service;
		this.Cor = corService;
		this.Tipo = tipoService;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	public void onBtSalvarAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		}
		catch (ValidationException e)	{
			  setErrorMessages(e.getErrors());
		} catch (DbException e) {
			Alerts.showAlert("ATENÇÃO", null, "Erro ao salvar", AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Cartucho getFormData() {
		Cartucho obj = new Cartucho();

		ValidationException exception = new ValidationException("Validation error");

		obj.setId(Utils.tryParseToInt(txtCodigo.getText()));

		if (txtCodigo.getText() == null || txtCodigo.getText().trim().equals("")) {
			exception.addError("Cartucho", " << Informe o código para salvar");
		}
		//obj.setCartucho(txtCodigo.getText());

		
		if (exception.getErrors().size() > 0) {
			throw exception;
		}
		
		return obj;
	}

	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		initializeNodes();
	}

	private void initializeNodes() {
		//Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtCodigo, 30);
		Constraints.setTextFieldMaxLength(txtUsuario, 70);
		Constraints.setTextFieldMaxLength(txtUsuarioRetirada, 70);
		Constraints.setTextFieldMaxLength(txtEmail, 70);
		Constraints.setTextFieldDouble(txtPesoEntrada);
		Constraints.setTextFieldDouble(txtPesoSaida);
		Utils.formatDatePicker(dPDataEntrada, "dd/MM/yyyy");
		Utils.formatDatePicker(dPDataSaida, "dd/MM/yyyy");
		
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		//txtId.setText(String.valueOf(entity.getId()));
		//txtCartucho.setText(entity.getCartucho());
		
		
	}
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if (fields.contains("Cartucho")) {
			labelErrorGeral.setText(errors.get("Cartucho"));
		}
	}
}
