package controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.History;
import model.Log;

@Path("/history")
public class WSHistory 
{	
	@POST
	@Path("/inserthistory/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public History insertHistory(History objHis, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			objHis.insertHistory();
			return objHis;
		}
		else
			return null;		
	}
	
	@PUT
	@Path("/updatehistory/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateHistory(History objHis, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
			objHis.updateHistory();
	}	
	
	@GET
	@Path("/listhistories/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<History> listHistory(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			History objHis = new History();
			return objHis.listHistories();
		}
		else
			return null;		
	}	
}