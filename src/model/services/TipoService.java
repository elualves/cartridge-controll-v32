package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Tipo;

public class TipoService {

	public List<Tipo> findAll() {
		List<Tipo> list = new ArrayList<>();
		list.add(new Tipo(1, "Laser"));
		list.add(new Tipo(2, "Jato de Tinta"));

		return list;
	}
}

