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

import model.Company;
import model.Log;

@Path("/company")
public class WSCompany 
{	
	@POST
	@Path("/insertcompany/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Company insertcompany(Company objCom, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			objCom.insertCompany();
			return objCom;
		}
		else 
			return null;
	}
	
	@PUT
	@Path("/updatecompany/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCompany(Company objCom, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			objCom.updateCompany();
		}			
	}
	
	@DELETE
	@Path("/deletecompany/{keyCompany}/{token}")
	public void deleteCompany(@PathParam("keyCompany") int keyCompany, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Company objE = new Company();
			objE.setKeyCompany(keyCompany);		
			objE.deleteCompany();
		}		
	}
	
	@GET
	@Path("/listcompanys/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> listCompany(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Company objCom = new Company();
			return objCom.listCompanys();
		}
		else
			return null;			
	}	
}