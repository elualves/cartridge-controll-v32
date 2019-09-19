package model.dao;

import java.util.List;

import model.entities.Cor;

public interface CorDao {

	void insert(Cor obj);
	void update(Cor obj);
	void deleteById(Integer id);
	Cor findById(Integer id);
	List<Cor> findAll();
}
