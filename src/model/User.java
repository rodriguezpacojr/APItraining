package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//Annotations for include a root in the out of WS
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="user")

public class User
{	 
	private int keyUser;
	private String userName;
	private String password;
	private String token;
	
    private Connection conn;
    private Connect objC;

    
    	//==========================METHODS===========================================
	public void validateUser() 
	{
		try 
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM usr WHERE username = '"+userName+"' AND "
					+ "password = md5('"+password+"')";
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			if(res.next()) 
			{
				Log objB = new Log();
				objB.insertLog(userName);
				token = objB.getToken();
			}
			else 
			{
				token = "Access Denied";
			}
		}
		catch(Exception e) {}
	}
	
	public void getObjUsr(int keyUser)
	{    	
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM usr WHERE keyuser = "+keyUser;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				this.setKeyUser(keyUser);
				this.setUserName(res.getString("username"));																
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}	
	
	
	//=========================GETTERS AND SETTERS============================================
	@XmlElement(required=true)
    public int getKeyUser() {
		return keyUser;
	}	
	
	@XmlElement(required = true)
	public String getToken() {
		return token;
	}
	
	@XmlElement(required = true)
	public String getUserName() {
		return userName;
	}
	
	public void setKeyUser(int keyUser) {
		this.keyUser = keyUser;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}