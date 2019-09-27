package model.dao;

import java.util.List;

import model.entities.Marca;

public interface MarcaDao {

	void insert(Marca obj);
	void update(Marca obj);
	void deleteById(Integer id);
	Marca findById(Integer id);
	List<Marca> findAll();
}
