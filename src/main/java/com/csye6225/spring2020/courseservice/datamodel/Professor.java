package com.csye6225.spring2020.courseservice.datamodel;

import java.lang.reflect.Field;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName="Professor")
public class Professor {
	private String professorId;
	private String alias;
	private String firstName;
	private String lastName;
	private String department;
	private String joiningDate;
	private String email;

	public Professor() {

	}

	public Professor(String professorId, String alias, String firstName, String lastName, String department) {
		this.professorId = professorId;
		this.alias = alias;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.joiningDate = new Date().toString();
		this.email = String.format("%s@example.com", getAlias());
	}

	@DynamoDBAttribute(attributeName="firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDBAttribute(attributeName="department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	
	@DynamoDBHashKey(attributeName="professorId")
	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	@DynamoDBAttribute(attributeName="alias")
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}

	
	@DynamoDBAttribute(attributeName="joiningDate")
	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	@DynamoDBAttribute(attributeName="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DynamoDBIgnore
	@Override
	public String toString() {
		return "professorId=" + getProfessorId() + "alias=" + getAlias() + ", firstName=" + getFirstName()
				+ ", department=" + getDepartment() + ", joiningDate=" + getJoiningDate() + ", email=" + getEmail();
	}

	@DynamoDBAttribute(attributeName="lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void updateInfo(Professor newProf) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : getClass().getDeclaredFields()) {
			if (f.get(newProf) != null) {
				f.set(this, f.get(newProf));
			}
		}
	}
}
