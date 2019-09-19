package application;

import java.util.List;

import model.dao.CorDao;
import model.dao.DaoFactory;
import model.entities.Cor;

public class program {

	public static void main(String[] args) {
		CorDao corDao = DaoFactory.createCorDao();
		
		List<Cor> cor = corDao.findAll();
		
		System.out.println(cor);
	}

}
