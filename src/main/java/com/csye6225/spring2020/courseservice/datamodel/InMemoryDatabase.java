package com.csye6225.spring2020.courseservice.datamodel;

import java.util.HashMap;

public class InMemoryDatabase {

	private static HashMap<String, Professor> professorDB = new HashMap<> ();
	private static HashMap<String, Student> studentDB = new HashMap<> ();
	private static HashMap<String, Course> courseDB = new HashMap<> ();
	private static HashMap<String, Program> progDB = new HashMap<> ();
	private static HashMap<String, Lecture> lectureDB = new HashMap<> ();

	public static HashMap<String, Professor> getProfessorDB() {
		return professorDB;
	}
	
	public static HashMap<String, Student> getStudentDB() {
		return studentDB;
	}
	
	public static HashMap<String, Course> getCourseDB() {
		return courseDB;
	}
	
	public static HashMap<String, Program> getProgDB() {
		return progDB;
	}
	
	public static HashMap<String, Lecture> getLectureDB() {
		return lectureDB;
	}
}

