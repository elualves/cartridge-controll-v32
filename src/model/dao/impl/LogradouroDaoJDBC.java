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
import model.dao.LogradouroDao;
import model.entities.Logradouro;

public class LogradouroDaoJDBC implements LogradouroDao {

	private Connection conn;
	
	public LogradouroDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Logradouro findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM LOGRADOURO WHERE IDLOGRADOURO = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Logradouro obj = new Logradouro();
				obj.setId(rs.getInt("IDLOGRADOURO"));
				obj.setLogradouro(rs.getString("NOME_DO_LOGRADOURO"));
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
	public List<Logradouro> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM LOGRADOURO ORDER BY IDLOGRADOURO");
			rs = st.executeQuery();

			List<Logradouro> list = new ArrayList<>();

			while (rs.next()) {
				Logradouro obj = new Logradouro();
				obj.setId(rs.getInt("IDLOGRADOURO"));
				obj.setLogradouro(rs.getString("NOME_DO_LOGRADOURO"));
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
	public void insert(Logradouro obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO LOGRADOURO " +
				"(NOME_DO_LOGRADOURO) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getLogradouro());

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
	public void update(Logradouro obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE LOGRADOURO " +
				"SET NOME_DO_LOGRADOURO = ? " +
				"WHERE IDLOGRADOURO = ?");

			st.setString(1, obj.getLogradouro());
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
				"DELETE FROM LOGRADOURO WHERE IDCOR = ?");

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