package com.csye6225.spring2020.courseservice.datamodel;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId() {
		this.professorId = UUID.randomUUID().toString();
	}

	public String getAlias() {
		return alias;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail() {
		this.email = String.format("%s@example.com", getAlias());
	}

	@Override
	public String toString() {
		return "professorId=" + getProfessorId() + "alias=" + getAlias() + ", firstName=" + getFirstName()
				+ ", department=" + getDepartment() + ", joiningDate=" + getJoiningDate() + ", email=" + getEmail();
	}

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
