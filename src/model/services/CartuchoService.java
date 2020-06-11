package model.services;

import java.util.List;

import model.dao.CartuchoDao;
import model.dao.DaoFactory;
import model.entities.Cartucho;

public class CartuchoService {

	private CartuchoDao dao = DaoFactory.createCartuchoDao();

	public List<Cartucho> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Cartucho obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Cartucho obj) {
		dao.deleteById(obj.getId());
	}
}