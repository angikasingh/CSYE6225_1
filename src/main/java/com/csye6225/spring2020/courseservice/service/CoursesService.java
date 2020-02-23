package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Lecture;
import com.csye6225.spring2020.courseservice.datamodel.Student;

public class CoursesService {
	static HashMap<String, Course> course_Map = InMemoryDatabase.getCourseDB();
	static HashMap<String, Student> student_Map = InMemoryDatabase.getStudentDB();
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
	
	public Course deleteCourse(String courseCode) {
		Course deletedCourse = course_Map.get(courseCode);
		course_Map.remove(courseCode);
		return deletedCourse;
	}
	
	public Course addCourse(Course c) {
		Course Course = new Course(c.getCourseCode(), c.getCourseName(), c.getProfessorId(), c.getStudentTAId());
		course_Map.put(Course.getCourseCode(), Course);
		return Course;
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
		if (!student_Map.containsKey(studentId)) {
			return null;
		}
		c.enrollStudent(studentId);
		course_Map.put(courseCode, c);
		return c;
	}
	
	public Course addLecture(String courseCode, String lectureId) {
		Course c = course_Map.get(courseCode);
		if (!lecture_Map.containsKey(lectureId)) {
			return null;
		}
		c.addLecture(lectureId);
		course_Map.put(courseCode, c);
		return c;
	}
}
