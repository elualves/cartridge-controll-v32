package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Status;

public class StatusService {

	public List<Status> findAll() {
		List<Status> list = new ArrayList<>();
		list.add(new Status(1, "Sem Condições"));
		list.add(new Status(2, "Queimado"));
		list.add(new Status(3, "Recarregado"));

		return list;
	}
}
