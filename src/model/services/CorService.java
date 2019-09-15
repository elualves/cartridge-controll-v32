package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Cor;

public class CorService {

	public List<Cor> findAll() {
		List<Cor> list = new ArrayList<>();
		list.add(new Cor(1, "Color"));
		list.add(new Cor(2, "BK"));

		return list;
	}
}
