package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Logradouro;

public class LogradouroService {

	public List<Logradouro> findAll() {
		List<Logradouro> list = new ArrayList<>();
		list.add(new Logradouro(1, "Rua"));
		list.add(new Logradouro(2, "Avenida"));
		list.add(new Logradouro(3, "Praça"));
		list.add(new Logradouro(4, "Largo"));

		return list;
	}
}