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

import model.CourseTraining;
import model.Log;

@Path("/coursetraining")
public class WSCourseTraining 
{	
	@POST
	@Path("/insertcoursetraining/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CourseTraining insertcoursetraining(CourseTraining objCT, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			objCT.insertCourseTraining();
			return objCT;
		}
		else
			return null;		
	}
	
	@PUT
	@Path("/updatecoursetraining/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatecoursetraining(CourseTraining objCT, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
			objCT.updateCourseTraining();
	}
	
	@DELETE
	@Path("/deletecoursetraining/{keycoursetraining}/{token}")
	public void deletecoursetraining(@PathParam("keyCourseTraining") int keyCourseTraining, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			CourseTraining objCT = new CourseTraining();
			objCT.setKeyCourseTraining(keyCourseTraining);		
			objCT.deleteCourseTraining();
		}
	}
	
	@GET
	@Path("/listcoursetrainings/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseTraining> listCourseTraining(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			CourseTraining objCT = new CourseTraining();
			return objCT.listCourseTrainings();
		}
		else
			return null;
	}
}