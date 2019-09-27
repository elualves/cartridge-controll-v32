package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.ModeloDao;
import model.entities.Modelo;

public class ModeloDaoJDBC implements ModeloDao {

	private Connection conn;
	
	public ModeloDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Modelo findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM MODELO WHERE IDMODELO = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Modelo obj = new Modelo();
				obj.setId(rs.getInt("IDMODELO"));
				obj.setModelo(rs.getString("NOME_DO_MODELO"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Modelo> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM MODELO ORDER BY IDMODELO");
			rs = st.executeQuery();

			List<Modelo> list = new ArrayList<>();

			while (rs.next()) {
				Modelo obj = new Modelo();
				obj.setId(rs.getInt("IDMODELO"));
				obj.setModelo(rs.getString("NOME_DO_MODELO"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Modelo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO MODELO " +
				"(Name) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getModelo());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Modelo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE MODELO " +
				"SET Name = ? " +
				"WHERE IdMODELO = ?");

			st.setString(1, obj.getModelo());
			st.setInt(2, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM MODELO WHERE IdMODELO = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}