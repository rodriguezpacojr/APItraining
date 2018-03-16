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

import model.Log;
import model.TypeCourse;

@Path("/typecourse")
public class WSTypeCourse 
{	
	@POST
	@Path("/inserttypecourse/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TypeCourse insertTypeCourse(TypeCourse objTC, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			objTC.insertTypeCourse();
			return objTC;
		}
		else
			return null;				
	}
	
	@PUT
	@Path("/updatetypecourse/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatetypecourse(TypeCourse objTC, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
			objTC.updateTypeCourse();
	}
	
	@DELETE
	@Path("/deletetypecourse/{keytypecourse}/{token}")
	public void deletetypecourse(@PathParam("keyTypeCourse") int keyTypeCourse, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			TypeCourse objTC = new TypeCourse();
			objTC.setKeyTypeCourse(keyTypeCourse);		
			objTC.deleteTypeCourse();
		}
	}
	
	@GET
	@Path("/listtypecourses/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TypeCourse> listtypecourse(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			TypeCourse objCom = new TypeCourse();
			return objCom.listTypeCourses();
		}
		else
			return null;				
	}	
}