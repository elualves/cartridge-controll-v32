package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Modelo;

public class ModeloService {

	public List<Modelo> findAll() {
		List<Modelo> list = new ArrayList<>();
		list.add(new Modelo(1, "662"));
		list.add(new Modelo(2, "662XL"));
		list.add(new Modelo(3, "664"));
		list.add(new Modelo(4, "664XL"));

		return list;
	}
}
