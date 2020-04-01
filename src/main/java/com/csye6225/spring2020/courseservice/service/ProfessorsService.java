package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Professor;

public class ProfessorsService {

	DynamoDBMapper mapper; 
	
	public ProfessorsService() {
		mapper = new DynamoDBMapper(DynamoDbConnector.getClient());
	}

	public List<Professor> getAllProfessors() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Professor> scanResult = mapper.scan(Professor.class, scanExpression);
		return scanResult;
	}

	public Professor addProfessor(Professor p) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		String professorId = mapper.count(Professor.class, scanExpression) + 1 + "";
 		Professor prof = new Professor(professorId, p.getAlias(), p.getFirstName(), p.getLastName(), p.getDepartment());
		mapper.save(prof);
		return prof;
	}

	public Professor getProfessor(String profId) {
		Professor result = mapper.load(Professor.class, profId);
		return result;
	}

	public Professor deleteProfessor(String profId) {
		Professor deletedProfDetails = getProfessor(profId);
		mapper.delete(deletedProfDetails);
		return deletedProfDetails;
	}

	public Professor updateProfessorInformation(String profId, Professor newProfInfo) {
		Professor prof = getProfessor(profId);
		if(prof == null) {
			return null;
		}
		try {
			prof.updateInfo(newProfInfo);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		mapper.save(prof);
		return prof;
	}

}
