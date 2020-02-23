package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Professor;

public class ProfessorsService {

	static HashMap<String, Professor> prof_Map = InMemoryDatabase.getProfessorDB();

	public ProfessorsService() {
		
	}

	public List<Professor> getAllProfessors() {
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		return list;
	}

	public Professor addProfessor(Professor p) {
		String professorId = prof_Map.size() + 1 + "";
 		Professor prof = new Professor(professorId, p.getAlias(), p.getFirstName(), p.getLastName(), p.getDepartment());
		prof_Map.put(prof.getProfessorId(), prof);
		return prof;
	}

	public Professor getProfessor(String profId) {
		return prof_Map.getOrDefault(profId, null);
	}

	public Professor deleteProfessor(String profId) {
		Professor deletedProfDetails = prof_Map.get(profId);
		prof_Map.remove(profId);
		return deletedProfDetails;
	}

	public Professor updateProfessorInformation(String profId, Professor newProfInfo) {
		Professor prof = prof_Map.get(profId);
		try {
			prof.updateInfo(newProfInfo);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		prof_Map.put(profId, prof);
		return prof;
	}

	public List<Professor> getProfessorsByDepartment(String department) {
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list;
	}

}
