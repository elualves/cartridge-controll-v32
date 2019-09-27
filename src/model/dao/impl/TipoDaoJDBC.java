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
import model.dao.TipoDao;
import model.entities.Tipo;

public class TipoDaoJDBC implements TipoDao {

	private Connection conn;
	
	public TipoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Tipo findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM TIPO WHERE IDTIPO = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Tipo obj = new Tipo();
				obj.setId(rs.getInt("IDTIPO"));
				obj.setTipo(rs.getString("NOME_DO_TIPO"));
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
	public List<Tipo> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM TIPO ORDER BY IDTIPO");
			rs = st.executeQuery();

			List<Tipo> list = new ArrayList<>();

			while (rs.next()) {
				Tipo obj = new Tipo();
				obj.setId(rs.getInt("IDTIPO"));
				obj.setTipo(rs.getString("NOME_DO_TIPO"));
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
	public void insert(Tipo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO TIPO " +
				"(Name) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getTipo());

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
	public void update(Tipo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE TIPO " +
				"SET Name = ? " +
				"WHERE IDTIPO = ?");

			st.setString(1, obj.getTipo());
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
				"DELETE FROM TIPO WHERE IDTIPO = ?");

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