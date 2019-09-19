package model.dao;

import java.util.List;

import model.entities.Logradouro;

public interface LogradouroDao {

	void insert(Logradouro obj);
	void update(Logradouro obj);
	void deleteById(Integer id);
	Logradouro findById(Integer id);
	List<Logradouro> findAll();
}
