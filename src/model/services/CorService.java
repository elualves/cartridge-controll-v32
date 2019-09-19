package model.services;

import java.util.List;

import model.dao.CorDao;
import model.dao.DaoFactory;
import model.entities.Cor;

public class CorService {

	private CorDao dao = DaoFactory.createCorDao();

	public List<Cor> findAll() {
		return dao.findAll();
	}
}