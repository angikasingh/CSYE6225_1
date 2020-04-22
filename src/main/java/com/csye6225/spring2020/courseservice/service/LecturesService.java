package com.csye6225.spring2020.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.Lecture;

public class LecturesService {
	
	DynamoDBMapper mapper; 
	
	public LecturesService() {
		mapper = new DynamoDBMapper(DynamoDbConnector.getClient());
	}
	
	public List<Lecture> getAllLectures() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Lecture> scanResult = mapper.scan(Lecture.class, scanExpression);
		return scanResult;
	}
	
	public List<Lecture> getLectures(List<String> lectureIds) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withSS(lectureIds));

		DynamoDBQueryExpression<Lecture> queryExpression = new DynamoDBQueryExpression<Lecture>() 
		    .withKeyConditionExpression("Id IN :v1")
		    .withExpressionAttributeValues(eav);

		List<Lecture> result = mapper.query(Lecture.class, queryExpression);
		return result;
	}
	
	public Lecture getLecture(String lectureId) {
		Lecture result = mapper.load(Lecture.class, lectureId);
		return result;
	}
	
	public Lecture addLecture(Lecture l) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		String lectureId = mapper.count(Lecture.class, scanExpression) + 1 + "";
 		Lecture lecture = new Lecture(lectureId, l.getNotes(), l.getAssociatedMaterials());
		mapper.save(lecture);
		return lecture;
	}
	
	public Lecture deleteLecture(String lectureId) {
		Lecture deletedLectureInfo = getLecture(lectureId);
		mapper.delete(deletedLectureInfo);
		return deletedLectureInfo;
	}
	
	public Lecture updateLectureInformation(String lectureId, Lecture newLectureInfo) {
		Lecture l = getLecture(lectureId);
		try {
			l.updateInfo(newLectureInfo);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		mapper.save(l);
		return l;
	}
}
