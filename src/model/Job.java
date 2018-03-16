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
@XmlRootElement(name="job")
public class Job
{	
    private int keyJob;
    private String nameJob;
    
    private Connection conn;
    private Connect objC;
	    
    
    	//==========================METHODS===========================================
	public void insertJob() 
	{
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO job (namejob)"
				+ " values ('"+nameJob+"')";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			this.nameJob=query;
		}
	}
	
	public void updateJob() 
	{
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE job SET namejob = '"+nameJob+"' "											
				+ "WHERE keyjob = "+keyJob;
		try
		{						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.nameJob=query;
		}
	}
	
	public void deleteJob() 
	{
		objC = new Connect();
		conn = objC.getConn();
		try 
		{
			String query = "DELETE FROM job WHERE keyjob = "+keyJob;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<Job> listJobs()
	{
		Job objJob;
		ArrayList<Job> arrJob = new ArrayList<Job>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT * FROM job";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				objJob = new Job();
				objJob.keyJob = res.getInt("keyjob");
				objJob.nameJob = res.getString("namejob");				
				arrJob.add(objJob);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrJob;
	}
	
	public void getObjJob(int keyjob)
	{
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM job WHERE keyjob = "+keyjob;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				this.keyJob = keyjob;
				this.nameJob = res.getString("namejob");
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	//=========================GETTERS AND SETTERS============================================
    @XmlElement(required=true)		
	public int getKeyJob() {
		return keyJob;
	}

	public void setKeyJob(int keyJob) {
		this.keyJob = keyJob;
	}

	@XmlElement(required=true)
	public String getNameJob() {
		return nameJob;
	}

	public void setNameJob(String nameJob) {
		this.nameJob = nameJob;
	}
}
