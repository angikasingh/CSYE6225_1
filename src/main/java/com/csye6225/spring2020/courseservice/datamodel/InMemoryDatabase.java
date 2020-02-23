package com.csye6225.spring2020.courseservice.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InMemoryDatabase {

	private static HashMap<String, Professor> professorDB = new HashMap<> ();

	public static HashMap<String, Professor> getProfessorDB() {
		return professorDB;
	}
}

