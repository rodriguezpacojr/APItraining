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

import model.Job;
import model.Log;

@Path("/job")
public class WSJob 
{	
	@POST
	@Path("/insertjob/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Job insertJob(Job objJob, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			objJob.insertJob();
			return objJob;
		}
		else
			return null;		
	}
	
	@PUT
	@Path("/updatejob/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatejob(Job objJob, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
			objJob.updateJob();
	}
	
	@DELETE
	@Path("/deletejob/{keyJob}/{token}")
	public void deleteJob(@PathParam("keyJob") int keyJob, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Job objJob = new Job();
			objJob.setKeyJob(keyJob);		
			objJob.deleteJob();
		}						
	}
	
	@GET
	@Path("/listjobs/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Job> listJob(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Job objCom = new Job();
			return objCom.listJobs();
		}
		else
			return null;			
	}	
}