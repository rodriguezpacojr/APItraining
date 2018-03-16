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

import model.Course;
import model.Log;

@Path("/course")
public class WSCourse 
{	
	@POST
	@Path("/insertcourse/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Course insertCourse(Course objCou, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			objCou.insertCourse();
			return objCou;
		}
		else 
			return null;		
	}
	
	@PUT
	@Path("/updatecourse/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCourse(Course objCou, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			objCou.updateCourse();
		}
	}
	
	@DELETE
	@Path("/deletecourse/{keyCourse}/{token}")
	public void deleteCourse(@PathParam("keyCourse") int keyCourse, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			Course objCou = new Course();
			objCou.setKeyCourse(keyCourse);		
			objCou.deleteCourse();
		}		
	}
	
	@GET
	@Path("/listcourses/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> listCourse(@PathParam("token") String token)
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			Course objCom = new Course();
			return objCom.listCourses();
		}
		else 
			return null;
		
	}	
}