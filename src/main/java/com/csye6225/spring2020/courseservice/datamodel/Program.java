package com.csye6225.spring2020.courseservice.datamodel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Program")
public class Program {
	private String programCode;
	private String programName;
	private List<String> courseCodes;

	public Program() {

	}

	public Program(String programCode, String programName) {
		this.programCode = programCode;
		this.programName = programName;
		this.courseCodes = new ArrayList<>();
	}

	@DynamoDBHashKey(attributeName="programCode")
	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	@DynamoDBAttribute(attributeName="programName")
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	@DynamoDBAttribute(attributeName="courseCodes")
	public List<String> getCourseCodes() {
		return courseCodes;
	}
	
	public void setCourseCodes(List<String> courseCodes) {
		this.courseCodes = courseCodes;
	}

	public void addCourse(String courseCode) {
		this.courseCodes.add(courseCode);
	}

	public void updateInfo(Program newProg) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : getClass().getDeclaredFields()) {
			if (f.get(newProg) != null) {
				f.set(this, f.get(newProg));
			}
		}
	}

}
