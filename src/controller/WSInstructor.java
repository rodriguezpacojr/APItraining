package controller;

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

import model.Instructor;
import model.Log;

@Path("/instructor")
public class WSInstructor 
{
	@POST
	@Path("/insertinstructor/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Instructor insertInstructor(Instructor objI, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			objI.insertInstructor();
			return objI;
		}
		else
			return null;		
	}
	
	@PUT
	@Path("/updateinstructor/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateInstructor(Instructor objI, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
			objI.updateInstructor();
	}
	
	@DELETE
	@Path("/deleteinstructor/{keyInstructor}/{token}")
	public void deleteInstructor(@PathParam("keyInstructor") int keyInstructor, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Instructor objI = new Instructor();
			objI.setKeyInstructor(keyInstructor);		
			objI.deleteInstructor();
		}
	}
	
	@GET
	@Path("/listinstructors/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Instructor> listInstructor(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Instructor objI = new Instructor();
			return objI.listInstructors();
		}
		else
			return null;		
	}
}