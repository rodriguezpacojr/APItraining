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
@XmlRootElement(name="gender")

public class Gender
{	 
	private String keyGender;
	private String description;
    
    private Connection conn;
    private Connect objC;

    
    	//==========================METHODS===========================================
    public void getObjGender(String keygender)
	{    	
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM gender WHERE keygender = '"+keygender+"'";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				this.setKeyGender(keygender);
				this.setDescription(res.getString("description"));																
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
    
    
    //=========================GETTERS AND SETTERS============================================
    @XmlElement(required=true)
    public String getKeyGender() {
		return keyGender;
	}

	public void setKeyGender(String keyGender) {
		this.keyGender = keyGender;
	}
	
	@XmlElement(required=true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}