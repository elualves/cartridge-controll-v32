package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.CorService;
import model.services.LogradouroService;
import model.services.MarcaService;
import model.services.ModeloService;
import model.services.StatusService;
import model.services.TipoService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemCadastrarNovaRecarga;/* INCLUIR NO BANCO HORA DA ENTREGA */

	@FXML
	private MenuItem menuItemCadastrarCliente;

	@FXML
	private MenuItem menuItemCadastrarLogradouro;

	@FXML
	private MenuItem menuItemCadastrarMarca;

	@FXML
	private MenuItem menuItemCadastrarModelo;

	@FXML
	private MenuItem menuItemCadastrarCor;

	@FXML
	private MenuItem menuItemCadastrarTipo;

	@FXML
	private MenuItem menuItemCadastrarStatus;

	@FXML
	private MenuItem menuItemEditarRecarga;

	@FXML
	private MenuItem menuItemEditarCliente;

	@FXML
	private MenuItem menuItemEditarLogradouro;

	@FXML
	private MenuItem menuItemEditarMarca;

	@FXML
	private MenuItem menuItemEditarModelo;

	@FXML
	private MenuItem menuItemEditarCor;

	@FXML
	private MenuItem menuItemEditarTipo;

	@FXML
	private MenuItem menuItemEditarStatus;

	@FXML
	private MenuItem menuItemPesquisarRecarga;

	@FXML
	private MenuItem menuItemPesquisarCliente;

	@FXML
	private MenuItem menuItemRelatorioRecargaDia;

	@FXML
	private MenuItem menuItemRelatorioRecargaMes;

	@FXML
	private MenuItem menuItemRelatorioRecargaAno;

	@FXML
	private MenuItem menuItemRelatorioRecargaPeriodo;

	@FXML
	private MenuItem menuItemAjudaCadastrar;

	@FXML
	private MenuItem menuItemAjudaEditar;

	@FXML
	private MenuItem menuItemAjudaPesquisar;

	@FXML
	private MenuItem menuItemAjudaRelatorio;

	@FXML
	private Button btnNovaRecarga;

	@FXML
	private Button btnPesquisar;

	@FXML
	private Button btnLimpar;

	/*---------------------------------------------------------------------------*/

	@FXML
	public void onMenuItemCadastrarNovaRecargaAction() {
		System.out.println("menu Item Cadastrar Nova Recarga Action");
	}

	@FXML
	public void onMenuItemCadastrarClienteAction() {
		System.out.println("menu Item Cadastrar Cliente Action");
	}

	@FXML
	public void onMenuItemCadastrarLogradouroAction() {
		loadView("/gui/CadastrarLogradouro.fxml", (CadastrarLogradouroController controller) -> {
			controller.setLogradouroService(new LogradouroService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemCadastrarMarcaAction() {
		loadView("/gui/CadastrarMarca.fxml", (CadastrarMarcaController controller) -> {
			controller.setMarcaService(new MarcaService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemCadastrarModeloAction() {
		loadView("/gui/CadastrarModelo.fxml", (CadastrarModeloController controller) -> {
			controller.setModeloService(new ModeloService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemCadastrarCorAction() {
		loadView("/gui/CadastrarCor.fxml", (CadastrarCorController controller) -> {
			controller.setCorService(new CorService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemCadastrarTipoAction() {
		loadView("/gui/CadastrarTipo.fxml", (CadastrarTipoController controller) -> {
			controller.setTipoService(new TipoService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemCadastrarStatusAction() {
		loadView("/gui/CadastrarStatus.fxml", (CadastrarStatusController controller) -> {
			controller.setStatusService(new StatusService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemEditarRecargaAction() {
		System.out.println("menu Item Editar Recarga Action");

	}

	@FXML
	public void onMenuItemEditarClienteAction() {
		System.out.println("menu Item Editar Cliente Action");
	}

	@FXML
	public void onMenuItemEditarLogradouroAction() {
		System.out.println("menu Item Editr Logradouro Action");
	}

	@FXML
	public void onMenuItemEditarMarcaAction() {
		System.out.println("menu Item Editar Marca Action");
	}

	@FXML
	public void onMenuItemEditarModeloAction() {
		System.out.println("menu Item Editar Modelo Action");
	}

	@FXML
	public void onMenuItemEditarCorAction() {
		System.out.println("menu Item Editar Cor Action");
	}

	@FXML
	public void onMenuItemEditarTipoAction() {
		System.out.println("menu Item Editar Tipo Action");
	}

	@FXML
	public void onMenuItemEditarStatusAction() {
		System.out.println("menu Item Editar Status Action");
	}

	@FXML
	public void onMenuItemPesquisarRecargaAction() {
		System.out.println("menu Item Pesquisar Recarga Action");
	}

	@FXML
	public void onMenuItemPesquisarClienteAction() {
		System.out.println("menu Item Pesquisar Cliente Action");
	}

	@FXML
	public void onMenuItemRelatorioRecargaDiaAction() {
		System.out.println("menu Item Relat�rio Recarga Dia Action");
	}

	@FXML
	public void onMenuItemRelatorioRecargaMesAction() {
		System.out.println("menu Item Relatorio Recarga Mes Action");
	}

	@FXML
	public void onMenuItemRelatorioRecargaAnoAction() {
		System.out.println("menu Item Relatorio Recarga Ano Action");
	}

	@FXML
	public void onMenuItemRelatorioRecargaPeriodoAction() {
		System.out.println("menu Item Relatorio Recarga Periodo Action");
	}

	@FXML
	public void onMenuItemAjudaCadastrarAction(ActionEvent event) {
		loadView("/gui/AjudaSobreCadastrarView.fxml", x -> {});
	}

	@FXML
	public void onMenuItemAjudaEditarAction(ActionEvent event) {
		loadView("/gui/AjudaSobreEditarView.fxml", x -> {});
	}

	@FXML
	public void onMenuItemAjudaPesquisarAction(ActionEvent event) {
		loadView("/gui/AjudaSobrePesquisarView.fxml", x -> {});
	}

	@FXML
	public void onMenuItemAjudaRelatorioAction(ActionEvent event) {
		loadView("/gui/AjudaSobreRelatorioView.fxml", x -> {});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox =  (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();		
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a p�gina", e.getMessage(), AlertType.ERROR);
		}
	}
}






















