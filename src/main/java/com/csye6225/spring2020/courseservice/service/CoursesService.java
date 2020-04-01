package com.csye6225.spring2020.courseservice.service;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.Lecture;
import com.csye6225.spring2020.courseservice.datamodel.Program;
import com.csye6225.spring2020.courseservice.datamodel.Student;

public class CoursesService {
	
	DynamoDBMapper mapper; 
	LecturesService lecturesService;
	StudentsService studentsService;
	ProgramService programService;
	
	public CoursesService() {
		mapper = new DynamoDBMapper(DynamoDbConnector.getClient());
		lecturesService = new LecturesService();
		studentsService = new StudentsService();
		programService = new ProgramService();
	}
	
	public List<Course> getAllCourses() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Course> scanResult = mapper.scan(Course.class, scanExpression);
		return scanResult;
	}
	
	public Course getCourse(String courseCode) {
		Course result = mapper.load(Course.class, courseCode);
		return result;
	}
	
	public List<Lecture> getLecturesByCourse(String courseCode) {
		Course course = getCourse(courseCode);
		if (course == null) {
			return null;
		}
		List<String> lectureIds = course.getLectureIds();
		return lecturesService.getLectures(lectureIds);
	}
	
	public Course deleteCourse(String courseCode) {
		Course deletedCourse = getCourse(courseCode);
		mapper.delete(deletedCourse);
		return deletedCourse;
	}
	
	public Course addCourse(Course c) {
		Course course = new Course(c.getCourseCode(), c.getCourseName(), c.getProfessorId(), c.getStudentTAId());
		mapper.save(course);
		return course;
	}
	
	public Course updateCourseInformation(String courseCode, Course newCourse) {
		Course c = getCourse(courseCode);
		if(c == null) {
			return null;
		}
		try {
			c.updateInfo(newCourse);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		mapper.save(c);
		return c;
	}
	
	public Course enrollStudent(String courseCode, String studentId) {
		Course course = getCourse(courseCode);
		if (course == null) {
			return null;
		}
		Student student = studentsService.getStudent(studentId);
		String programCode = student.getProgramCode();
		Program prog = programService.getProgram(programCode);
		if (!prog.getCourseCodes().contains(courseCode)) {
			return null;
		}
		course.enrollStudent(studentId);
		mapper.save(course);
		return course;
	}
	
	public Course addmessageToBoard(String courseCode, String message) {
		Course course = getCourse(courseCode);
		if (course == null) {
			return null;
		}
		String boardMsg = course.getBoard();
		boardMsg += "\n" + message; 
		course.setBoard(boardMsg);
		mapper.save(course);
		return course;
	}
	
	public Course addLecture(String courseCode, String lectureId) {
		Course course = getCourse(courseCode);
		if (course == null) {
			return null;
		}
		Lecture lecture = lecturesService.getLecture(lectureId);
		if (lecture == null) {
			return null;
		}
		course.addLecture(lectureId);
		mapper.save(course);
		return course;
	}
}
