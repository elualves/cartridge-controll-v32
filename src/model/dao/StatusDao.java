package model.dao;

import java.util.List;

import model.entities.Cor;
import model.entities.Status;

public interface StatusDao {

	void insert(Status obj);
	void update(Status obj);
	void deleteById(Integer id);
	Cor findById(Integer id);
	List<Cor> findAll();
}
