package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.User;

@Path("/usr")
public class WSUser 
{
	@GET
	@Path("/validate/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public User validateUser(@PathParam("username") String username, @PathParam("password") String password)
	{
		User objU = new User();
		objU.setUserName(username);
		objU.setPassword(password);
		objU.validateUser();
		return objU;
	}	
}
