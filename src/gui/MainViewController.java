package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemCadastrarNovaRecarga;

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
		System.out.println("menu Item CadastrarLogradouro Action");
	}

	@FXML
	public void onMenuItemCadastrarMarcaAction() {
		System.out.println("menu Item Cadastrar Marca Action");
	}

	@FXML
	public void onMenuItemCadastrarModeloAction() {
		System.out.println("menu Item Cadastrar Modelo Action");
	}

	@FXML
	public void onMenuItemCadastrarCorAction() {
		System.out.println("menu Item Cadastrar Cor Action");
	}

	@FXML
	public void onMenuItemCadastrarTipoAction() {
		System.out.println("menu Item Cadastrar Tipo Action");
	}

	@FXML
	public void onMenuItemCadastrarStatusAction() {
		System.out.println("menuI tem Cadastrar Status Action");
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
	public void onMenuItemAjudaCadastrarAction() {
		System.out.println("menu Item Ajuda Cadastrar Action");
	}

	@FXML
	public void onMenuItemAjudaEditarAction() {
		System.out.println("menu Item Ajuda Editar Action");
	}

	@FXML
	public void onMenuItemAjudaPesquisarAction() {
		System.out.println("menu Item Ajuda Pesquisar Action");
	}

	@FXML
	public void onMenuItemAjudaRelatorioAction() {
		System.out.println("menu Item Ajuda Relat�rio Action");
	}
	
	@FXML
	public void onBtnNovaRecargaAvtion() {
		System.out.println("Bot�o Nova Recarga Action");
	}
	
	@FXML
	public void onBtnPesquisarAction() {
		System.out.println("Bot�o Pesquisar Action");
	}
	
	@FXML
	public void onBtnLimparAction() {
		System.out.println("Bot�o Limpar Action");
	}
	
/*-----------------------------------------------------------------------------------*/

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}

}
