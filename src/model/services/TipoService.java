package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.TipoDao;
import model.entities.Tipo;

public class TipoService {

	private TipoDao dao = DaoFactory.createTipoDao();

	public List<Tipo> findAll() {
		return dao.findAll();
	}
}

