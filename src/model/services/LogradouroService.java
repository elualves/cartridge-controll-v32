package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.LogradouroDao;
import model.entities.Logradouro;

public class LogradouroService {

	private LogradouroDao dao = DaoFactory.createLogradouroDao();

	public List<Logradouro> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Logradouro obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	public void remove(Logradouro obj) {
		dao.deleteById(obj.getId());
	}
}