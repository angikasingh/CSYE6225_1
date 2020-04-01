package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Lecture;
import com.csye6225.spring2020.courseservice.datamodel.Professor;
import com.csye6225.spring2020.courseservice.datamodel.Program;
import com.csye6225.spring2020.courseservice.datamodel.Student;

public class StudentsService {
	
	DynamoDBMapper mapper; 
	ProgramService programService;
	
	public StudentsService() {
		mapper = new DynamoDBMapper(DynamoDbConnector.getClient());
		programService = new ProgramService();
	}
	
	public List<Student> getAllStudents() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Student> scanResult = mapper.scan(Student.class, scanExpression);
		return scanResult;
	}
	
	public Student getStudent(String studentId) {
		Student result = mapper.load(Student.class, studentId);
		return result;
	}
	
	public List<Student> getStudents(List<String> studentIds) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withSS(studentIds));

		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>() 
		    .withKeyConditionExpression("Id IN :v1")
		    .withExpressionAttributeValues(eav);

		List<Student> result = mapper.query(Student.class, queryExpression);
		return result;
	}
	
	public Student deleteStudent(String studentId) {
		Student deletedStudentDetails  = getStudent(studentId);
		mapper.delete(deletedStudentDetails);
		return deletedStudentDetails;
	}
	
	public Student addStudent(Student s) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		String studentId = mapper.count(Student.class, scanExpression) + 1 + "";
		Student student = new Student(studentId, s.getName(), s.getImageUrl(), s.getProgramCode());
		mapper.save(student);
		return student;
	}
	
	public Student updateStudentInformation(String studentId, Student newStudentinfo) {
		Student s = getStudent(studentId);
		try {
			s.updateInfo(newStudentinfo);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		mapper.save(s);
		return s;
	}
	
	public Student enrollCourse(String studentId, String courseCode) {
		Student student = getStudent(studentId);
		if (student == null) {
			return null;
		}
		String programCode = student.getProgramCode();
		Program prog = programService.getProgram(programCode);
		if (!prog.getCourseCodes().contains(courseCode)) {
			return null;
		}
		student.enrollCourse(courseCode);
		mapper.save(student);
		return student;
	}
}
