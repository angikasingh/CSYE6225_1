package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Lecture;
import com.csye6225.spring2020.courseservice.datamodel.Professor;
import com.csye6225.spring2020.courseservice.datamodel.Program;
import com.csye6225.spring2020.courseservice.datamodel.Student;

public class CoursesService {
	static HashMap<String, Course> course_Map = InMemoryDatabase.getCourseDB();
	static HashMap<String, Professor> professor_Map = InMemoryDatabase.getProfessorDB();
	static HashMap<String, Student> student_Map = InMemoryDatabase.getStudentDB();
	static HashMap<String, Program> prog_Map = InMemoryDatabase.getProgDB();
	static HashMap<String, Lecture> lecture_Map = InMemoryDatabase.getLectureDB();
	
	public List<Course> getAllCourses() {
		// Getting the list
		ArrayList<Course> list = new ArrayList<>();
		for (Course s : course_Map.values()) {
			list.add(s);
		}
		return list;
	}
	
	public List<Course> getCoursesByProfId(String profId) {
		// Getting the list
		ArrayList<Course> list = new ArrayList<>();
		for (Course c : course_Map.values()) {
			if (c.getProfessorId().contentEquals(profId)) {
				list.add(c);
			}
		}
		return list;
	}
	
	public Course getCourse(String courseCode) {
		return course_Map.getOrDefault(courseCode, null);
	}
	
	public List<Lecture> getLecturesByCourse(String courseCode) {
		if (!course_Map.containsKey(courseCode)) {
			return null;
		}
		Course course = course_Map.getOrDefault(courseCode, null);
		List<String> lectureIds = course.getLectureIds();
		List<Lecture> lectures = new ArrayList<>();
		for (String id : lectureIds) {
			lectures.add(lecture_Map.get(id));
		}
		return lectures;
	}
	
	public List<Student> getStudentsByCourse(String courseCode) {
		if (!course_Map.containsKey(courseCode)) {
			return null;
		}
		Course course = course_Map.getOrDefault(courseCode, null);
		List<String> studentIds = course.getRoster();
		List<Student> students = new ArrayList<>();
		for (String id : studentIds) {
			students.add(student_Map.get(id));
		}
		return students;
	}
	
	public Course deleteCourse(String courseCode) {
		Course deletedCourse = course_Map.get(courseCode);
		course_Map.remove(courseCode);
		return deletedCourse;
	}
	
	public Course addCourse(Course c) {
		if (!student_Map.containsKey(c.getStudentTAId())) {
			return null;
		}
		if (!professor_Map.containsKey(c.getProfessorId())) {
			return null;
		}
		Course course = new Course(c.getCourseCode(), c.getCourseName(), c.getProfessorId(), c.getStudentTAId());
		course_Map.put(course.getCourseCode(), course);
		return course;
	}
	
	public Course updateCourseInformation(String courseCode, Course newCourse) {
		Course c = course_Map.get(courseCode);
		try {
			c.updateInfo(newCourse);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		course_Map.put(courseCode, c);
		return c;
	}
	
	public Course enrollStudent(String courseCode, String studentId) {
		Course c = course_Map.get(courseCode);
		if (!course_Map.containsKey(courseCode)) {
			return null;
		}
		if (!student_Map.containsKey(studentId)) {
			return null;
		}
		Student s = student_Map.get(studentId);
		String programCode = s.getProgramCode();
		Program prog = prog_Map.get(programCode);
		if (!prog.getCourses().contains(courseCode)) {
			return null;
		}
		c.enrollStudent(studentId);
		course_Map.put(courseCode, c);
		return c;
	}
	
	public Course addmessageToBoard(String courseCode, String message) {
		Course c = course_Map.get(courseCode);
		if (!course_Map.containsKey(courseCode)) {
			return null;
		}
		String boardMsg = c.getBoard();
		boardMsg += "\n" + message; 
		c.setBoard(boardMsg);
		course_Map.put(courseCode, c);
		return c;
	}
	
	public Course addLecture(String courseCode, String lectureId) {
		Course c = course_Map.get(courseCode);
		if (!course_Map.containsKey(courseCode)) {
			return null;
		}
		if (!lecture_Map.containsKey(lectureId)) {
			return null;
		}
		c.addLecture(lectureId);
		course_Map.put(courseCode, c);
		return c;
	}
}
