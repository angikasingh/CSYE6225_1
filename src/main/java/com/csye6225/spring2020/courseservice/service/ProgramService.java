package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Program;

public class ProgramService {

	static HashMap<String, Program> prog_Map = InMemoryDatabase.getProgDB();
	static HashMap<String, Course> course_Map = InMemoryDatabase.getCourseDB();

	public ProgramService() {

	}

	public List<Program> getAllPrograms() {
		ArrayList<Program> list = new ArrayList<>();
		for (Program prog : prog_Map.values()) {
			list.add(prog);
		}
		return list;
	}

	public Program addProgram(Program p) {
		Program prog = new Program(p.getProgramCode(), p.getProgramName());
		prog_Map.put(p.getProgramCode(), prog);
		return prog;
	}
	
	public Program addCourse(String programId, String courseId) {
		Program prog = prog_Map.get(programId);
		if (!course_Map.containsKey(courseId)) {
			return null;
		}
		prog.addCourse(courseId);
		prog_Map.put(programId, prog);
		return prog;
	}

	public Program getProgram(String progCode) {
		return prog_Map.getOrDefault(progCode, null);
	}

	public Program deleteProgram(String progCode) {
		Program deletedProgDetails = prog_Map.get(progCode);
		prog_Map.remove(progCode);
		return deletedProgDetails;
	}

	public Program updateProgramInformation(String progCode, Program newProg) {
		Program p = prog_Map.get(progCode);
		try {
			p.updateInfo(newProg);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		prog_Map.put(progCode, p);
		return p;
	}

}
