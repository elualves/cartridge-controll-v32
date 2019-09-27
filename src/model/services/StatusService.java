package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.StatusDao;
import model.entities.Status;

public class StatusService {

	private StatusDao dao = DaoFactory.createStatusDao();

	public List<Status> findAll() {
		return dao.findAll();
	}
}
