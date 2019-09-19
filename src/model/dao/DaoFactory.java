package model.dao;

import db.DB;
import model.dao.impl.CorDaoJDBC;
import model.dao.impl.LogradouroDaoJDBC;

public class DaoFactory {

	public static CorDao createCorDao() {
		return new CorDaoJDBC(DB.getConnection());
	}
	
	public static LogradouroDao createLogradouroDao() {
		return new LogradouroDaoJDBC(DB.getConnection());
	}
	
	/*
	public static MarcaDao createMarcaDao() {
		return new MarcaDaoJDBC(DB.getConnection());
	}
	
	public static ModeloDao createModeloDao() {
		return new CorModeloJDBC(DB.getConnection());
	}
	
	public static StatusDao createStatusDao() {
		return new StatusDaoJDBC(DB.getConnection());
	}
	
	public static TipoDao createTipoDao() {
		return new TipoDaoJDBC(DB.getConnection());
	}
	*/
}
