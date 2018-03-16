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
import java.util.Date;

//Annotations for include a root in the out of WS
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="employee")
public class Employee
{	
    private int keyEmployee;
    private String nameEmp;
    private String lastName;
    private String lastNameMom;
    private Date bornDate;
    private String emailEmp;
    private String phone;
    private String RFC;
    
    private String keyGender;
    private Gender Gender;    
    
    private int keyJob;
    private Job Job;
    
    private int keyUser;    
    private User Usr;
    
    private Connection conn;
    private Connect objC;
    
	//==========================METHODS===========================================
	public void insertEmployee() 
	{
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO employee (nameemp, lastname, lastnamemom, borndate, emailemp, phone, rfc,keygender,keyjob,keyuser)"
				+ " values ('"+nameEmp+"', '"+lastName+"', '"+lastNameMom+"', '"+bornDate+"', '"+emailEmp+"','"+phone+"','"+RFC+"',"+keyGender+","+keyJob+","+keyUser+")";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			this.nameEmp=query;
		}
	}
	
	public void updateEmployee() 
	{
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE employee SET nameemp = '"+nameEmp+"',"
				+ "lastname = '"+lastName+"', lastnamemom = '"+lastNameMom+"',"
				+ "borndate = '"+bornDate+"', emailemp = '"+emailEmp+"',"
				+ "phone = '"+phone+"', rfc = '"+RFC+"', "
				+ "keygender = '"+keyGender+"',"
				+ "keyjob = "+keyJob+", keyuser = "+keyUser+" "
				+ "WHERE keyemployee = "+keyEmployee;
		try
		{						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.nameEmp=query;
		}
	}
	
	public void deleteEmployee() 
	{
		objC = new Connect();
		conn = objC.getConn();
		try 
		{
			String query = "DELETE FROM employee WHERE keyemployee = "+keyEmployee;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<Employee> listEmployees()
	{				
		Employee objE = new  Employee();;
		ArrayList<Employee> arrEmp = new ArrayList<Employee>();		
										
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			String query = "SELECT * FROM employee";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
				
			//Convert all registers from query to objects
			while(res.next())
			{
				Gender objGen = new Gender();
				User objUser = new User();				
				Job objJ = new Job();
				
				objJ.getObjJob(res.getInt("keyjob"));				
				objGen.getObjGender(res.getString("keygender"));
				objUser.getObjUsr(res.getInt("keyuser"));
				
				objE = new Employee();
				objE.keyEmployee = res.getInt("keyemployee");
				objE.nameEmp = res.getString("nameemp");
				objE.lastName = res.getString("lastname");
				objE.lastNameMom = res.getString("lastnamemom");
				objE.bornDate = res.getDate("borndate");
				objE.emailEmp = res.getString("emailemp");
				objE.phone = res.getString("phone");
				objE.RFC = res.getString("rfc");												
				objE.Gender = objGen;				
				objE.Usr = objUser;																					
				objE.Job = objJ;
				
				arrEmp.add(objE);
			}
			conn.close();
		}
		catch(Exception e)
		{	
			
		}
			return arrEmp;		
	}
	
	public void getObjEmployee(int keyemployee)
	{
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM employee WHERE keyemployee = "+keyemployee;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				Gender objG = new Gender();
				User objU = new User();
				Job objJ = new Job();
				
				objG.getObjGender(res.getString("keygender"));
				objU.getObjUsr(res.getInt("keyuser"));
				objJ.getObjJob(res.getInt("keyjob"));
				
				this.keyEmployee = keyemployee;
				this.nameEmp = res.getString("nameemp");
				this.lastName = res.getString("lastname");
				this.lastNameMom = res.getString("lastnamemom");
				this.bornDate = res.getDate("borndate");
				this.emailEmp = res.getString("emailemp");								
				this.phone = res.getString("phone");
				this.RFC = res.getString("rfc");
				
				this.setGender(objG);
				this.setUsr(objU);
				this.setJob(objJ);
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	//=========================GETTERS AND SETTERS============================================
    // Annotation for determinate that the attribute is part of WS' out
    @XmlElement(required=true)
	public int getKeyEmployee() {
		return keyEmployee;
	}
    
	public void setKeyEmployee(int keyEmployee) {
		this.keyEmployee = keyEmployee;
	}
	
	@XmlElement(required = true)
	public String getNameEmp() {
		return nameEmp;
	}
	
	public void setNameEmp(String nameEmp) {
		this.nameEmp = nameEmp;
	}
	
	@XmlElement(required = true)
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@XmlElement(required = true)
	public String getLastNameMom() {
		return lastNameMom;
	}
	
	public void setLastNameMom(String lastNameMom) {
		this.lastNameMom = lastNameMom;
	}
	
	@XmlElement(required = true)
	public Date getBornDate() {
		return bornDate;
	}
	
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	
	@XmlElement(required = true)
	public String getEmailEmp() {
		return emailEmp;
	}
	
	public void setEmailEmp(String emailEmp) {
		this.emailEmp = emailEmp;
	}
	
	@XmlElement(required = true)
	public int getKeyJob() {
		return keyJob;
	}
	
	public void setKeyJob(int keyJob) {
		this.keyJob = keyJob;
	}	 		
	
	@XmlElement(required = true)
	public int getKeyUser() {
		return keyUser;
	}
	
	public void setKeyUser(int keyUser) {
		this.keyUser = keyUser;
	}
	
	@XmlElement(required = true)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@XmlElement(required = true)
	public String getRFC() {
		return RFC;
	}

	public void setRFC(String rFC) {
		RFC = rFC;
	}

	@XmlElement(required = true)
	public String getKeyGender() {
		return keyGender;
	}

	public void setKeyGender(String keyGender) {
		this.keyGender = keyGender;
	}
	
	
		
	
	@XmlElement(required = true)
	public User getUsr() {
		return Usr;
	}
	public void setUsr(User usr) {
		Usr = usr;
	}
	
	@XmlElement(required = true)
	public Job getJob() {
		return Job;
	}
	public void setJob(Job job) {
		Job = job;
	}

	@XmlElement(required=true)
	public Gender getGender() {
		return Gender;
	}	
	public void setGender(Gender gender) {
		Gender = gender;
	}
}