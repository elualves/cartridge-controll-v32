package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.CartuchoDao;
import model.entities.Cartucho;
import model.entities.Cor;
import model.entities.Marca;
import model.entities.Modelo;
import model.entities.Status;
import model.entities.Tipo;

public class CartuchoDaoJDBC implements CartuchoDao {

	private Connection conn;

	public CartuchoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Cartucho obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO CARTUCHO " 
							+ "(CODIGO, CLIENTE_DIRETO, TELEFONE, ENDERECO_COLETA,"
							+ " ENDERECO_ENTREGA, CONTATO_NOME, NOME_ENTRADA, NOME_SAIDA, PESO_ENTRADA, PESO_SAIDA,"
							+ "DATA_ENTRADA, DATA_SAIDA, OBS, ID_MARCA, ID_MODELO, ID_COR, ID_TIPO, ID_STATUS) "
							+ "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getCodigo());
			st.setString(2, obj.getClienteDireto());
			st.setString(3, obj.getTelefone());
			st.setString(4, obj.getEnderecoColeta());
			st.setString(5, obj.getEndrecoEntrega());
			st.setString(6, obj.getContatoNome());
			st.setString(7, obj.getNomeEntrada());
			st.setString(8, obj.getNomeSaida());
			st.setDouble(9, obj.getPesoEntrada());
			st.setDouble(10, obj.getPesoSaida());
			st.setDate(11, new java.sql.Date(obj.getDataEntrada().getTime()));
			st.setDate(12, new java.sql.Date(obj.getDataSaida().getTime()));
			st.setString(13, obj.getObs());
			st.setString(14, obj.getIdMarca());
			st.setString(15, obj.getIdModelo());
			st.setString(16, obj.getIdCor());
			st.setString(17, obj.getIdTipo());
			st.setString(18, obj.getIdStatus());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Cartucho obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE cartucho "
					+ "SET CODIGO = ?, CLIENTE_DIRETO = ?, TELEFONE = ?, ENDERECO_COLETA = ?, ENDERECO_ENTREGA = ?, CONTATO_NOME = ?, NOME_ENTRADA = ?, NOME_SAIDA = ?,"
					+ " PESO_ENTRADA = ?, PESO_SAIDA = ?, DATA_ENTRADA = ?, DATA_SAIDA = ?, OBS = ?, ID_MARCA = ?, ID_MODELO = ?, ID_COR = ?, ID_TIPO = ?, ID_STATUS = ?");
			
			st.setString(1, obj.getCodigo());
			st.setString(2, obj.getClienteDireto());
			st.setString(3, obj.getTelefone());
			st.setString(4, obj.getEnderecoColeta());
			st.setString(5, obj.getEndrecoEntrega());
			st.setString(6, obj.getContatoNome());
			st.setString(7, obj.getNomeEntrada());
			st.setString(8, obj.getNomeSaida());
			st.setDouble(9, obj.getPesoEntrada());
			st.setDouble(10, obj.getPesoSaida());
			st.setDate(11, new java.sql.Date(obj.getDataEntrada().getTime()));
			st.setDate(12, new java.sql.Date(obj.getDataSaida().getTime()));
			st.setString(13, obj.getObs());
			st.setString(14, obj.getIdMarca());
			st.setString(15, obj.getIdModelo());
			st.setString(16, obj.getIdCor());
			st.setString(17, obj.getIdTipo());
			st.setString(18, obj.getIdStatus());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM cartucho WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Cartucho findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT cartucho.*,department.Name as DepName " + "FROM cartucho INNER JOIN department "
							+ "ON cartucho.DepartmentId = department.Id " + "WHERE cartucho.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Cartucho car = instantiateCartucho(rs);
				Cartucho obj = instantiateCartucho(rs, car);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Cartucho instantiateCartucho(ResultSet rs, Cartucho car) throws SQLException {
		Cartucho obj = new Cartucho();

		obj.setCodigo(rs.getString("CODIGO"));
		obj.setClienteDireto(rs.getString("CLIENTE_DIRETO"));
		obj.setTelefone(rs.getString("TELEFONE"));
		obj.setEnderecoColeta(rs.getString("ENDERECO_COLETA"));
		obj.setEndrecoEntrega(rs.getString("ENDERECO_ENTREGA"));
		obj.setContatoNome(rs.getString("CONTATO_NOME"));
		obj.setNomeEntrada(rs.getString("NOME_ENTRADA"));
		obj.setNomeSaida(rs.getString("NOME_SAIDA"));
		obj.setPesoEntrada(rs.getDouble("PESO_ENTRADA"));
		obj.setPesoSaida(rs.getDouble("PESO_SAIDA"));
		obj.setDataEntrada(new java.util.Date(rs.getTimestamp("DATA_ENTRADA").getTime()));
		obj.setDataSaida(new java.util.Date(rs.getTimestamp("DATA_SAIDA").getTime()));
		obj.setObs(rs.getString("OBS"));
		obj.setIdMarca(rs.getString("ID_MARCA"));
		obj.setIdModelo(rs.getString("ID_MODELO"));
		obj.setIdCor(rs.getString("ID_COR"));
		obj.setIdTipo(rs.getString("ID_TIPO"));
		obj.setIdStatus(rs.getString("ID_STATUS"));

		return obj;
	}

	private Cartucho instantiateCartucho(ResultSet rs) throws SQLException {
		Cartucho car = new Cartucho();
		car.setId(rs.getInt("IDCARTUCHO"));
		car.setCodigo(rs.getString("CODIGO"));
		return car;
	}

	@Override
	public List<Cartucho> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT cartucho.*,CARTUCHO.CODIGO  " + "FROM cartucho INNER JOIN COR "
							+ "ON CARTUCHO.IDCOR = COR.NOME_DA_COR " + "ORDER BY NOME_DA_COR");

			rs = st.executeQuery();

			List<Cartucho> list = new ArrayList<>();
			Map<Integer, Cartucho> map = new HashMap<>();

			while (rs.next()) {

				Cartucho car = map.get(rs.getInt("CartuchoId"));

				if (car == null) {
					car = instantiateCartucho(rs);
					map.put(rs.getInt("CartuchoId"), car);
				}

				Cartucho obj = instantiateCartucho(rs, car);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Cartucho> findByCartucho(Cartucho cartucho) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT cartucho.*,department.Name as DepName "
					+ "FROM cartucho INNER JOIN department " + "ON cartucho.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? " + "ORDER BY Name");

			st.setInt(1, cartucho.getId());

			rs = st.executeQuery();

			List<Cartucho> list = new ArrayList<>();
			Map<Integer, Cartucho> map = new HashMap<>();

			while (rs.next()) {

				Cartucho car = map.get(rs.getInt("CartuchoId"));

				if (car == null) {
					car = instantiateCartucho(rs);
					map.put(rs.getInt("CartuchoId"), car);
				}

				Cartucho obj = instantiateCartucho(rs, car);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Cartucho> findByMarca(Marca marca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cartucho> findByModelo(Modelo modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cartucho> findByCor(Cor cor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cartucho> findByTipo(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cartucho> findByStatus(Status status) {
		// TODO Auto-generated method stub
		return null;
	}
}