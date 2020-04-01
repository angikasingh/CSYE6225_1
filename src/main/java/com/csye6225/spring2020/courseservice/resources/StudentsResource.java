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

import com.csye6225.spring2020.courseservice.datamodel.Student;
import com.csye6225.spring2020.courseservice.service.StudentsService;

@Path("students")
public class StudentsResource {
	StudentsService studentService = new StudentsService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@GET
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("studentId") String studentId) {
		System.out.println("Student Resource: Looking for: " + studentId);
		return studentService.getStudent(studentId);
	}
	
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentId") String studentId) {
		return studentService.deleteStudent(studentId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student student) {
		return studentService.addStudent(student);
	}

	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentId") String studentId, Student student) {
		return studentService.updateStudentInformation(studentId, student);
	}
	
	@PUT
	@Path("/{studentId}/enrollCourse/{courseCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student enrollCourse(@PathParam("studentId") String studentId, @PathParam("courseCode") String courseCode) {
		return studentService.enrollCourse(studentId, courseCode);
	}
}
