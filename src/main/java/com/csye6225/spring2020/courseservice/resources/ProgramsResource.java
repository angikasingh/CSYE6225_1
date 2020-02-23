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

import com.csye6225.spring2020.courseservice.datamodel.Program;
import com.csye6225.spring2020.courseservice.service.ProgramService;

@Path("programs")
public class ProgramsResource {
	ProgramService programService = new ProgramService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getAllPrograms() {
		return programService.getAllPrograms();
	}
	
	@GET
	@Path("/{programCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("programCode") String programCode) {
		System.out.println("Program Resource: Looking for: " + programCode);
		return programService.getProgram(programCode);
	}
	
	@DELETE
	@Path("/{programCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("programCode") String programCode) {
		return programService.deleteProgram(programCode);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program program) {
		return programService.addProgram(program);
	}

	@PUT
	@Path("/{programCode}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programCode") String programCode, Program program) {
		return programService.updateProgramInformation(programCode, program);
	}
}
