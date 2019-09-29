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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Logradouro;
import model.exceptions.ValidationException;
import model.services.LogradouroService;

public class LogradouroFormController implements Initializable {

	private Logradouro entity;

	private LogradouroService service;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtLogradouro;

	@FXML
	private Label labelErrorLogradouro;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	public void setLogradouro(Logradouro entity) {
		this.entity = entity;
	}

	public void setLogradouroService(LogradouroService service) {
		this.service = service;
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
			Alerts.showAlert("ATENÇÃO", null, "Erroa ao salvar", AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Logradouro getFormData() {
		Logradouro obj = new Logradouro();

		ValidationException exception = new ValidationException("Validation Error");

		obj.setId(Utils.tryParseToInt(txtId.getText()));

		if (txtLogradouro.getText() == null || txtLogradouro.getText().trim().equals("")) {
			exception.addError("Logradouro", " << Informe o logradouro para salvar");	
		}
		obj.setLogradouro(txtLogradouro.getText());
		
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
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtLogradouro, 30);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtLogradouro.setText(entity.getLogradouro());
	}
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if (fields.contains("Logradouro")) {
			labelErrorLogradouro.setText(errors.get("Logradouro"));
		}
	}
}
