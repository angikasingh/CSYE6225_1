package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Professor;

public class ProfessorsService {

	static HashMap<String, Professor> prof_Map = InMemoryDatabase.getProfessorDB();

	public ProfessorsService() {
	}

	// Getting a list of all professor
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {
		// Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		return list;
	}

	// Adding a professor
	public Professor addProfessor(Professor prof) {
		prof_Map.put(prof.getAlias(), prof);
		return prof;
	}

	// Getting One Professor
	public Professor getProfessor(String alias) {
		// Simple HashKey Load
		return prof_Map.getOrDefault(alias, null);
	}

	// Deleting a professor
	public Professor deleteProfessor(String alias) {
		Professor deletedProfDetails = prof_Map.get(alias);
		prof_Map.remove(alias);
		return deletedProfDetails;
	}

	// Updating Professor Info
	public Professor updateProfessorInformation(String alias, Professor prof) {
		prof_Map.put(alias, prof);
		return prof;
	}

	// Get professors in a department
	public List<Professor> getProfessorsByDepartment(String department) {
		// Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list;
	}

	// Get professors for a year with a size limit

}
