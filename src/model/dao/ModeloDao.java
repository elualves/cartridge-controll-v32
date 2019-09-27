package model.dao;

import java.util.List;

import model.entities.Modelo;

public interface ModeloDao {

	void insert(Modelo obj);
	void update(Modelo obj);
	void deleteById(Integer id);
	Modelo findById(Integer id);
	List<Modelo> findAll();
}
