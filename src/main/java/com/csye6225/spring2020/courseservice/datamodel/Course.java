package com.csye6225.spring2020.courseservice.datamodel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Course {
	private String courseCode;
	private String courseName;
	private List<String> lectureIds;
	private String board;
	private List<String> enrolledStudentIds;
	private String professorId;
	private String studentTAId;
	
	public Course() {
		
	}
	
	public Course(String courseCode, String courseName, String professorId, String studentTAId) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.professorId = professorId;
		this.studentTAId = studentTAId;
		this.board = "";
		this.enrolledStudentIds = new ArrayList<>();
		this.lectureIds = new ArrayList<>();
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<String> getLectureIds() {
		return lectureIds;
	}

	public void addLecture(String lectureId) {
		this.lectureIds.add(lectureId);
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public List<String> getRoster() {
		return enrolledStudentIds;
	}

	public void enrollStudent(String studentIds) {
		this.enrolledStudentIds.add(studentIds);
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessor(String professorId) {
		this.professorId = professorId;
	}

	public String getStudentTAId() {
		return studentTAId;
	}

	public void setStudentTAid(String studentId) {
		this.studentTAId = studentId;
	}
	
	public void updateInfo(Course c) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : getClass().getDeclaredFields()) {
			if (f.get(c) != null) {
				f.set(this, f.get(c));
			}
		}
	}

}
