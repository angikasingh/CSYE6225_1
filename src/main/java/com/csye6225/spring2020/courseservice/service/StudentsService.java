package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Program;
import com.csye6225.spring2020.courseservice.datamodel.Student;

public class StudentsService {
	static HashMap<String, Student> student_Map = InMemoryDatabase.getStudentDB();
	static HashMap<String, Course> course_Map = InMemoryDatabase.getCourseDB();
	static HashMap<String, Program> prog_Map = InMemoryDatabase.getProgDB();
	
	public List<Student> getAllStudents() {
		// Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student s : student_Map.values()) {
			list.add(s);
		}
		return list;
	}
	
	public List<Student> getStudentsByProgramCode(String proCode) {
		// Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student s : student_Map.values()) {
			if (s.getProgramCode().contentEquals(proCode)) {
				list.add(s);
			}
		}
		return list;
	}
	
	public Student getStudent(String studentId) {
		return student_Map.getOrDefault(studentId, null);
	}
	
	public Student deleteStudent(String studentId) {
		Student deletedStudentDetails = student_Map.get(studentId);
		student_Map.remove(studentId);
		return deletedStudentDetails;
	}
	
	public Student addStudent(Student s) {
		String studentId = student_Map.size() + 1 + "";
		if (!prog_Map.containsKey(s.getProgramCode())) {
			return null;
		}
		Student student = new Student(studentId, s.getName(), s.getImageUrl(), s.getProgramCode());
		student_Map.put(student.getStudentId(), student);
		return student;
	}
	
	public Student updateStudentInformation(String studentId, Student newStudentinfo) {
		Student s = student_Map.get(studentId);
		try {
			s.updateInfo(newStudentinfo);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		student_Map.put(studentId, s);
		return s;
	}
	
	public Student enrollCourse(String studentId, String courseCode) {
		Student student = student_Map.get(studentId);
		if (!course_Map.containsKey(courseCode)) {
			return null;
		}
		student.enrollCourse(courseCode);
		student_Map.put(studentId, student);
		return student;
	}
}
