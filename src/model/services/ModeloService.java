package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ModeloDao;
import model.entities.Modelo;

public class ModeloService {

	private ModeloDao dao = DaoFactory.createModeloDao();

	public List<Modelo> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Modelo obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	public void remove(Modelo obj) {
		dao.deleteById(obj.getId());
	}
}
