package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//Annotations for include a root in the out of WS
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="typecourse")

public class TypeCourse
{	
    private int keyTypeCourse;
    private String nameTypeCourse;
    private String description;    
    
    private Connection conn;
    private Connect objC;
	    

		//==========================METHODS===========================================
	public void insertTypeCourse() 
	{
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO type_course (nametypecourse, description)"
				+ " values ('"+nameTypeCourse+"', '"+description+"')";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			this.nameTypeCourse=query;
		}
	}
	
	public void updateTypeCourse() 
	{
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE type_course SET nametypecourse = '"+nameTypeCourse+"',"
				+ "description = '"+description+"' "								
				+ "WHERE keytypecourse = "+keyTypeCourse;
		try
		{						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.nameTypeCourse=query;
		}
	}
	
	public void deleteTypeCourse() 
	{
		objC = new Connect();
		conn = objC.getConn();
		try 
		{
			String query = "DELETE FROM type_course WHERE keytypecourse = "+keyTypeCourse;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<TypeCourse> listTypeCourses()
	{
		TypeCourse objCou;
		ArrayList<TypeCourse> arrCou = new ArrayList<TypeCourse>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT * FROM type_course";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				objCou = new TypeCourse();
				objCou.keyTypeCourse = res.getInt("keytypecourse");
				objCou.nameTypeCourse = res.getString("nametypecourse");				
				objCou.description = res.getString("description");				
				arrCou.add(objCou);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrCou;
	}
	
	public void getObjTypeCourse(int keytypecourse)
	{
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM type_course WHERE keytypecourse = "+keytypecourse;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{							
				this.keyTypeCourse = keytypecourse;
				this.nameTypeCourse = res.getString("nametypecourse");
				this.description = res.getString("description");				
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	//=========================GETTERS AND SETTERS============================================
    @XmlElement(required=true)		
	public int getKeyTypeCourse() {
		return keyTypeCourse;
	}

	public void setKeyTypeCourse(int keyTypeCourse) {
		this.keyTypeCourse = keyTypeCourse;
	}

	@XmlElement(required=true)
	public String getNameTypeCourse() {
		return nameTypeCourse;
	}

	public void setNameTypeCourse(String nameTypeCourse) {
		this.nameTypeCourse = nameTypeCourse;
	}

	@XmlElement(required=true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
