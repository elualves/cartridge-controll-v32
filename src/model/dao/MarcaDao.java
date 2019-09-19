package model.dao;

import java.util.List;

import model.entities.Cor;
import model.entities.Marca;

public interface MarcaDao {

	void insert(Marca obj);
	void update(Marca obj);
	void deleteById(Integer id);
	Cor findById(Integer id);
	List<Cor> findAll();
}
