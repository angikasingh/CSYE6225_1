package com.csye6225.spring2020.courseservice.datamodel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Student {
	private String studentId;
	private String name;
	private String imageUrl;
	private List<String> enrolledCourseCodes;
	private String programCode;
	
	public Student() {

	}
	
	public Student(String studentId, String name, String imageUrl, String programCode) {
		this.studentId = studentId;
		this.name = name;
		this.imageUrl = imageUrl;
		this.enrolledCourseCodes = new ArrayList<>();
		this.programCode = programCode;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<String> getEnrolledCourseCodes() {
		return enrolledCourseCodes;
	}

	public void enrollCourse(String courseCode) {
		this.enrolledCourseCodes.add(courseCode);
	}
	
	public String getProgramCode() {
		return programCode;
	}

	public void setProgram(String programCode) {
		this.programCode = programCode;
	}
	
	public void updateInfo(Student student) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : getClass().getDeclaredFields()) {
			if (f.get(student) != null) {
				f.set(this, f.get(student));
			}
		}
	}
}
