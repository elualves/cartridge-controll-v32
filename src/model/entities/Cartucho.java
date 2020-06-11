package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Cartucho implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codigo;
	private String clienteDireto;
	private Date dataEntrada;
	private Date dataSaida;
	private Double pesoEntrada;
	private Double pesoSaida;
	private String IdStatus;
	private String obs;
	private String telefone;
	private String enderecoColeta;
	private String endrecoEntrega;
	private String contatoNome;
	private String nomeEntrada;
	private String nomeSaida;
	private String idMarca;
	private String idModelo;
	private String idCor;
	private String idTipo;
	
	

	public Cartucho() {
	}

	public Cartucho(Integer id, String codigo, String clienteDireto, Date dataEntrada, Date dataSaida, Double pesoEntrada, Double pesoSaida, String idStatus,
			String obs, String telefone, String enderecoColeta, String endrecoEntrega, String contatoNome, String nomeEntrada, String nomeSaida, String idMarca,
			String idModelo, String idCor, String idTipo) {
		this.id = id;
		this.codigo = codigo;
		this.clienteDireto = clienteDireto;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.pesoEntrada = pesoEntrada;
		this.pesoSaida = pesoSaida;
		this.IdStatus = idStatus;
		this.obs = obs;
		this.telefone = telefone;
		this.enderecoColeta = enderecoColeta;
		this.contatoNome = contatoNome;
		this.nomeEntrada = nomeEntrada;
		this.nomeSaida = nomeSaida;
		this.idMarca = idMarca;
		this.idModelo = idModelo;
		this.idCor = idCor;
		this.idTipo = idTipo;
	}



	

	public String getIdStatus() {
		return IdStatus;
	}

	public void setIdStatus(String idStatus) {
		IdStatus = idStatus;
	}

	public String getContatoNome() {
		return contatoNome;
	}

	public void setContatoNome(String contatoNome) {
		this.contatoNome = contatoNome;
	}

	public String getNomeEntrada() {
		return nomeEntrada;
	}

	public void setNomeEntrada(String nomeEntrada) {
		this.nomeEntrada = nomeEntrada;
	}

	public String getNomeSaida() {
		return nomeSaida;
	}

	public void setNomeSaida(String nomeSaida) {
		this.nomeSaida = nomeSaida;
	}

	public String getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(String idMarca) {
		this.idMarca = idMarca;
	}

	public String getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(String idModelo) {
		this.idModelo = idModelo;
	}

	public String getIdCor() {
		return idCor;
	}

	public void setIdCor(String idCor) {
		this.idCor = idCor;
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public String getEndrecoEntrega() {
		return endrecoEntrega;
	}

	public void setEndrecoEntrega(String endrecoEntrega) {
		this.endrecoEntrega = endrecoEntrega;
	}

	public String getEnderecoColeta() {
		return enderecoColeta;
	}

	public void setEnderecoColeta(String enderecoColeta) {
		this.enderecoColeta = enderecoColeta;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getClienteDireto() {
		return clienteDireto;
	}

	public void setClienteDireto(String clienteDireto) {
		this.clienteDireto = clienteDireto;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Double getPesoEntrada() {
		return pesoEntrada;
	}

	public void setPesoEntrada(Double pesoEntrada) {
		this.pesoEntrada = pesoEntrada;
	}

	public Double getPesoSaida() {
		return pesoSaida;
	}

	public void setPesoSaida(Double pesoSaida) {
		this.pesoSaida = pesoSaida;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Cartucho [id=" + id + ", codigo=" + codigo + ", clienteDireto=" + clienteDireto + ", dataEntrada="
				+ dataEntrada + ", dataSaida=" + dataSaida + ", pesoEntrada=" + pesoEntrada + ", pesoSaida=" + pesoSaida
				+ ", IdStatus=" + IdStatus + ", obs=" + obs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteDireto == null) ? 0 : clienteDireto.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result + ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((pesoEntrada == null) ? 0 : pesoEntrada.hashCode());
		result = prime * result + ((pesoSaida == null) ? 0 : pesoSaida.hashCode());
		result = prime * result + ((IdStatus == null) ? 0 : IdStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartucho other = (Cartucho) obj;
		if (clienteDireto == null) {
			if (other.clienteDireto != null)
				return false;
		} else if (!clienteDireto.equals(other.clienteDireto))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!dataSaida.equals(other.dataSaida))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (pesoEntrada == null) {
			if (other.pesoEntrada != null)
				return false;
		} else if (!pesoEntrada.equals(other.pesoEntrada))
			return false;
		if (pesoSaida == null) {
			if (other.pesoSaida != null)
				return false;
		} else if (!pesoSaida.equals(other.pesoSaida))
			return false;
		if (IdStatus == null) {
			if (other.IdStatus != null)
				return false;
		} else if (!IdStatus.equals(other.IdStatus))
			return false;
		return true;
	}

	public void setCartucho(String string) {
		// TODO Auto-generated method stub
		
	}

	public String getCartucho() {
		// TODO Auto-generated method stub
		return null;
	}


}

