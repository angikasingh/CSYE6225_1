package com.csye6225.spring2020.courseservice.datamodel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Student")
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

	@DynamoDBHashKey(attributeName="studentId")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@DynamoDBAttribute(attributeName="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute(attributeName="imageUrl")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@DynamoDBAttribute(attributeName="enrolledCourseCodes")
	public List<String> getEnrolledCourseCodes() {
		return enrolledCourseCodes;
	}
	
	public void setEnrolledCourseCodes(List<String> courseCodes) {
		this.enrolledCourseCodes = courseCodes;
	}

	public void enrollCourse(String courseCode) {
		this.enrolledCourseCodes.add(courseCode);
	}
	
	@DynamoDBAttribute(attributeName="programCode")
	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
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
