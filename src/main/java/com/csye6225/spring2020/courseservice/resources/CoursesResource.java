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

import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.service.CoursesService;

@Path("courses")
public class CoursesResource {
	CoursesService courseService = new CoursesService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCoursesByProfessor(@QueryParam("profId") String profId) {
		if (profId == null) {
			return courseService.getAllCourses();
		}
		return courseService.getCoursesByProfId(profId);
	}
	
	@GET
	@Path("/{courseCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseCode") String courseCode) {
		System.out.println("Course Resource: Looking for: " + courseCode);
		return courseService.getCourse(courseCode);
	}
	
	@DELETE
	@Path("/{courseCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteCourse(@PathParam("courseCode") String courseCode) {
		return courseService.deleteCourse(courseCode);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course course) {
		return courseService.addCourse(course);
	}
	
	@PUT
	@Path("/{courseCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course addToRoster(@PathParam("courseCode") String courseCode, @QueryParam("studentId") String studentId) {
		return courseService.enrollStudent(courseCode, studentId);
	}

	@PUT
	@Path("/{courseCode}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course updateCourse(@PathParam("courseCode") String courseCode, Course course) {
		return courseService.updateCourseInformation(courseCode, course);
	}
}
