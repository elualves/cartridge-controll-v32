package model.dao;

import java.util.List;

import model.entities.Cor;
import model.entities.Modelo;

public interface ModeloDao {

	void insert(Modelo obj);
	void update(Modelo obj);
	void deleteById(Integer id);
	Cor findById(Integer id);
	List<Cor> findAll();
}
