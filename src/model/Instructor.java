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
@XmlRootElement(name="instructor")

public class Instructor 
{
	private int keyInstructor;
    private String nameInst;
    private String lastName;
    private String lastNameMom;
    private Date bornDate;
    private String emailIns;
    private String phone;
    private String RFC;
    
    private String keyGender;
    private Gender Gender;
    
    private int keyCompany;    
    private Company Company;
    
    private Connection conn;
    private Connect objC;
    
    	//==========================METHODS===========================================
    public void insertInstructor() 
	{
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO instructor (nameinst, lastname, lastnamemom, borndate, emailins, phone, rfc, keygender, keycompany)"
				+ " values ('"+nameInst+"', '"+lastName+"', '"+lastNameMom+"', '"+bornDate+"', '"+emailIns+"','"+phone+"','"+RFC+"', '"+keyGender+"',"+keyCompany+")";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			//this.nameInst=query;
		}
	}
	
	public void updateInstructor() 
	{
		objC = new Connect();
		conn = objC.getConn();
						
		try
		{		
			String query = "UPDATE instructor SET nameinst = '"+nameInst+"', "
					+ "lastname = '"+lastName+"', lastnamemom = '"+lastNameMom+"', "
					+ "borndate = '"+bornDate+"', emailins = '"+emailIns+"', "
					+ "phone = '"+phone+"', rfc = '"+RFC+"', "
					+ "keygender = '"+keyGender+"', keycompany = "+keyCompany+" "
					+ "WHERE keyinstructor = "+keyInstructor;
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			//this.nameInst=query;
		}
	}
	
	public void deleteInstructor() 
	{
		objC = new Connect();
		conn = objC.getConn();
		try 
		{
			String query = "DELETE FROM instructor WHERE keyinstructor = "+keyInstructor;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<Instructor> listInstructors()
	{
		Instructor objI;
		ArrayList<Instructor> arrInst = new ArrayList<Instructor>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT * FROM instructor";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				Gender objGen = new Gender();
				Company objCom = new Company();
				
				objGen.getObjGender(res.getString("keygender"));
				objCom.getObjCompany(res.getInt("keycompany"));
				
				objI = new Instructor();
				objI.keyInstructor = res.getInt("keyinstructor");
				objI.nameInst = res.getString("nameinst");
				objI.lastName = res.getString("lastname");
				objI.lastNameMom = res.getString("lastnamemom");
				objI.bornDate = res.getDate("borndate");
				objI.emailIns = res.getString("emailins");
				objI.phone = res.getString("phone");
				objI.RFC = res.getString("rfc");								
				objI.Gender = objGen;
				objI.Company = objCom;
				
				arrInst.add(objI);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrInst;
	}  
    
	public void getObjInstructor(int keyinstructor)
	{
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM instructor WHERE keyinstructor = "+keyinstructor;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				Gender objG = new Gender();
				Company objC= new Company();
				
				objG.getObjGender(res.getString("keygender"));
				objC.getObjCompany(res.getInt("keycompany"));
				
				
				this.keyInstructor = keyinstructor;
				this.nameInst = res.getString("nameinst");
				this.lastName = res.getString("lastname");
				this.lastNameMom = res.getString("lastnamemom");
				this.bornDate = res.getDate("borndate");
				this.emailIns = res.getString("emailins");
				this.phone = res.getString("phone");
				this.RFC = res.getString("rfc");				
				this.setGender(objG);
				this.setCompany(objC);
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
    //=========================GETTERS AND SETTERS============================================
    @XmlElement(required=true)
	public int getKeyInstructor() {
		return keyInstructor;
	}
	public void setKeyInstructor(int keyInstructor) {
		this.keyInstructor = keyInstructor;
	}
	
	
	@XmlElement(required=true)
	public String getNameInst() {
		return nameInst;
	}
	public void setNameInst(String nameInst) {
		this.nameInst = nameInst;
	}
	
	
	@XmlElement(required=true)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	@XmlElement(required=true)
	public String getLastNameMom() {
		return lastNameMom;
	}
	public void setLastNameMom(String lastNameMom) {
		this.lastNameMom = lastNameMom;
	}
	
	
	@XmlElement(required=true)
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	
	
	@XmlElement(required=true)
	public String getEmailIns() {
		return emailIns;
	}
	public void setEmailIns(String emailIns) {
		this.emailIns = emailIns;
	}
	
	
	@XmlElement(required=true)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	@XmlElement(required=true)
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		RFC = rFC;
	}
	
	
	@XmlElement(required=true)
	public String getKeyGender() {
		return keyGender;
	}
	public void setKeyGender(String keyGender) {
		this.keyGender = keyGender;
	}
		
	
	@XmlElement(required=true)
	public int getKeyCompany() {
		return keyCompany;
	}
	public void setKeyCompany(int keyCompany) {
		this.keyCompany = keyCompany;
	}

	
	
	@XmlElement(required=true)
	public Gender getGender() {
		return Gender;
	}	
	public void setGender(Gender gender) {
		Gender = gender;
	}

	@XmlElement(required=true)
	public Company getCompany() {
		return Company;
	}
	public void setCompany(Company company) {
		Company = company;
	}    	 
}