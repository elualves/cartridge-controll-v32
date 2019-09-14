package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Marca;

public class MarcaService {
	
	public List<Marca> findAll() {		
		List<Marca> list = new ArrayList<>();
		list.add (new Marca(1, "HP"));
		list.add (new Marca(2, "Canon"));
		list.add (new Marca(3, "Sansung"));
		list.add (new Marca(4, "Brother"));
	
		return list;
	}
}
