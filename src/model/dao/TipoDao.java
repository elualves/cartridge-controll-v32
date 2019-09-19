package model.dao;

import java.util.List;

import model.entities.Cor;
import model.entities.Tipo;

public interface TipoDao {

	void insert(Tipo obj);
	void update(Tipo obj);
	void deleteById(Integer id);
	Cor findById(Integer id);
	List<Cor> findAll();
}
