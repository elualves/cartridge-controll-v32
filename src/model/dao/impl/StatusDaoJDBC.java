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
import model.dao.StatusDao;
import model.entities.Status;

public class StatusDaoJDBC implements StatusDao {

	private Connection conn;
	
	public StatusDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Status findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM STATUS WHERE IDSTATUS = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Status obj = new Status();
				obj.setId(rs.getInt("IDSTATUS"));
				obj.setStatus(rs.getString("NOME_DO_STATUS"));
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
	public List<Status> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM STATUS ORDER BY IDSTATUS");
			rs = st.executeQuery();

			List<Status> list = new ArrayList<>();

			while (rs.next()) {
				Status obj = new Status();
				obj.setId(rs.getInt("IDSTATUS"));
				obj.setStatus(rs.getString("NOME_DO_STATUS"));
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
	public void insert(Status obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO STATUS " +
				"(NOME_DO_STATUS) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getStatus());

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
	public void update(Status obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE STATUS " +
				"SET NOME_DO_STATUS = ? " +
				"WHERE IDSTATUS = ?");

			st.setString(1, obj.getStatus());
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
				"DELETE FROM STATUS WHERE IdSTATUS = ?");

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