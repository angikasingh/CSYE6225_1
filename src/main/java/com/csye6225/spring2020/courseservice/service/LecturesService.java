package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Lecture;

public class LecturesService {
	static HashMap<String, Lecture> lecture_Map = InMemoryDatabase.getLectureDB();
	
	public List<Lecture> getAllLectures() {
		ArrayList<Lecture> list = new ArrayList<>();
		for (Lecture l : lecture_Map.values()) {
			list.add(l);
		}
		return list;
	}
	
	public Lecture getLecture(String lectureId) {
		return lecture_Map.getOrDefault(lectureId, null);
	}
	
	public Lecture addLecture(Lecture l) {
		String lectureId = lecture_Map.size() + 1 + "";
		Lecture lecture = new Lecture(lectureId, l.getNotes(), l.getAssociatedMaterials());
		lecture_Map.put(lectureId, lecture);
		return lecture;
	}
	
	public Lecture deleteLecture(String lectureId) {
		Lecture deletedLectureInfo = lecture_Map.get(lectureId);
		lecture_Map.remove(lectureId);
		return deletedLectureInfo;
	}
	
	public Lecture updateLectureInformation(String lectureId, Lecture newLectureInfo) {
		Lecture l = lecture_Map.get(lectureId);
		try {
			l.updateInfo(newLectureInfo);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		lecture_Map.put(lectureId, l);
		return l;
	}
}
