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
@XmlRootElement(name="city")

public class City
{	 
	private int id;
	private String name;
    
    private Connection conn;
    private Connect objC;

    
    	//==========================METHODS===========================================
    public void getObjCity(int id)
	{    	
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM city WHERE id = "+id;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				this.setId(id);
				this.setName(res.getString("name"));																
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
    
  //=========================GETTERS AND SETTERS============================================
    @XmlElement(required=true)
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(required=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}