package model.dao;

import java.util.List;

import model.entities.Cartucho;
import model.entities.Cor;
import model.entities.Marca;
import model.entities.Modelo;
import model.entities.Status;
import model.entities.Tipo;

public interface CartuchoDao {

	void insert(Cartucho obj);
	void update(Cartucho obj);
	void deleteById(Integer id);
	Cartucho findById(Integer id);
	List<Cartucho> findAll();
	List<Cartucho> findByMarca(Marca marca);
	List<Cartucho> findByModelo(Modelo modelo);
	List<Cartucho> findByCor(Cor cor);
	List<Cartucho> findByTipo(Tipo tipo);
	List<Cartucho> findByStatus(Status status);
	List<Cartucho> findByCartucho(Cartucho cartucho);
}
