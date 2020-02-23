package com.csye6225.spring2020.courseservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.spring2020.courseservice.datamodel.Lecture;
import com.csye6225.spring2020.courseservice.service.LecturesService;

@Path("lectures")
public class LectureResource {
	LecturesService lectureService = new LecturesService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lecture> getAllLectures() {
		return lectureService.getAllLectures();
	}
	
	@GET
	@Path("/{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture getLecture(@PathParam("lectureId") String lectureId) {
		System.out.println("Lecture Resource: Looking for: " + lectureId);
		return lectureService.getLecture(lectureId);
	}
	
	@DELETE
	@Path("/{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture deleteCourse(@PathParam("lectureId") String lectureId) {
		return lectureService.deleteLecture(lectureId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture addCourse(Lecture lecture) {
		return lectureService.addLecture(lecture);
	}

	@PUT
	@Path("/{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture updateCourse(@PathParam("lectureId") String lectureId, Lecture lecture) {
		return lectureService.updateLectureInformation(lectureId, lecture);
	}
}
