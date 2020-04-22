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
import javax.ws.rs.core.MediaType;

import com.csye6225.spring2020.courseservice.datamodel.Professor;
import com.csye6225.spring2020.courseservice.service.ProfessorsService;

@Path("professors")
public class ProfessorsResource {

	ProfessorsService profService = new ProfessorsService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getAllProfessors() {
		return profService.getAllProfessors();

	}

	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") String profid) {
		System.out.println("Professor Resource: Looking for: " + profid);
		return profService.getProfessor(profid);
	}

	@DELETE
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("professorId") String profid) {
		return profService.deleteProfessor(profid);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor addProfessor(Professor prof) {
		return profService.addProfessor(prof);
	}

	@PUT
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("professorId") String profId, Professor prof) {
		return profService.updateProfessorInformation(profId, prof);
	}
}
