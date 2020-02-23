package com.csye6225.spring2020.courseservice.datamodel;

import java.lang.reflect.Field;
import java.util.Date;

public class Lecture {
	private String id;
	private String notes;
	private String associatedMaterials;
	private String lectureDate;
	
	public Lecture() {
		
	}
	
	public Lecture(String id, String notes, String associatedMaterials) {
		this.id = id;
		this.notes = notes;
		this.associatedMaterials = associatedMaterials;
		this.lectureDate = new Date().toString();
	}
	
	public String getid() {
		return id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAssociatedMaterials() {
		return associatedMaterials;
	}

	public void setAssociatedMaterials(String associatedMaterials) {
		this.associatedMaterials = associatedMaterials;
	}

	public String getLectureDate() {
		return lectureDate;
	}

	public void setLectureDate(String lectureDate) {
		this.lectureDate = lectureDate;
	}

	public void updateInfo(Lecture l) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : getClass().getDeclaredFields()) {
			if (f.get(l) != null) {
				f.set(this, f.get(l));
			}
		}
	}

}
