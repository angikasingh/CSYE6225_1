package com.csye6225.spring2020.courseservice.service;

import java.util.List;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Professor;
import com.csye6225.spring2020.courseservice.datamodel.Program;

public class ProgramService {

	DynamoDBMapper mapper;

	public ProgramService() {
		mapper = new DynamoDBMapper(DynamoDbConnector.getClient());
	}

	public List<Program> getAllPrograms() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		return scanResult;
	}

	public Program addProgram(Program p) {
		Program prog = new Program(p.getProgramCode(), p.getProgramName());
		mapper.save(prog);
		return prog;
	}

	public Program addCourse(String programCode, String courseCode) {
		Program prog = getProgram(programCode);
		if (prog == null) {
			return null;
		}
		prog.addCourse(courseCode);
		mapper.save(prog);
		return prog;
	}

	public Program getProgram(String progCode) {
		Program result = mapper.load(Program.class, progCode);
		return result;
	}

	public Program deleteProgram(String progCode) {
		Program deletedProgDetails = getProgram(progCode);
		mapper.delete(deletedProgDetails);
		return deletedProgDetails;
	}

	public Program updateProgramInformation(String progCode, Program newProg) {
		Program p = getProgram(progCode);
		if (p == null) {
			return null;
		}
		try {
			p.updateInfo(newProg);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		mapper.save(p);
		return p;

	}

}
