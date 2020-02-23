package com.csye6225.spring2020.courseservice.resources;

import java.util.ArrayList;
import java.util.Date;
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

import com.csye6225.spring2020.courseservice.datamodel.Professor;
import com.csye6225.spring2020.courseservice.service.ProfessorsService;

// .. /webapi/myresource
@Path("professors")
public class ProfessorsResource {

	ProfessorsService profService = new ProfessorsService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorsByDeparment(@QueryParam("department") String department) {
		if (department == null) {
			return profService.getAllProfessors();
		}
		return profService.getProfessorsByDepartment(department);

	}

	// ... webapi/professor/1
	@GET
	@Path("/{alias}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("alias") String alias) {
		System.out.println("Professor Resource: Looking for: " + alias);
		return profService.getProfessor(alias);
	}

	@DELETE
	@Path("/{alias}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("alias") String alias) {
		return profService.deleteProfessor(alias);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor addProfessor(Professor prof) {
		prof.setEmail();
		prof.setJoiningDate(new Date().toString());
		return profService.addProfessor(prof);
	}

	@PUT
	@Path("/{alias}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("alias") String alias, Professor prof) {
		return profService.updateProfessorInformation(alias, prof);
	}
}
