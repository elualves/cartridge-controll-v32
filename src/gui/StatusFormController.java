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
import model.entities.Status;
import model.exceptions.ValidationException;
import model.services.StatusService;

public class StatusFormController implements Initializable {

	private Status entity;

	private StatusService service;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtStatus;

	@FXML
	private Label labelErrorStatus;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	public void setStatus(Status entity) {
		this.entity = entity;
	}

	public void setStatusService(StatusService service) {
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
			Alerts.showAlert("ATEN��O", null, "Erro ao salvar", AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Status getFormData() {
		Status obj = new Status();

		ValidationException exception = new ValidationException("Validation Error");

		obj.setId(Utils.tryParseToInt(txtId.getText()));

		if (txtStatus.getText() == null || txtStatus.getText().trim().equals("")) {
			exception.addError("Status", " << Informe o status para salvar");
		}
		obj.setStatus(txtStatus.getText());
		
		
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
		Constraints.setTextFieldMaxLength(txtStatus, 30);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtStatus.setText(entity.getStatus());
	}
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if (fields.contains("Status")) {
			labelErrorStatus.setText(errors.get("Status"));
		}
	}
}
