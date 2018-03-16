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
@XmlRootElement(name="company")

public class Company
{	
    private int keyCompany;
    private String nameCompany;
    private String emailCompany;
    private String phone;
    private String RFC;
    private String bussinesTurn;      
    
    private Connection conn;
    private Connect objC;
	    
	
		//==========================METHODS===========================================
	public void insertCompany() 
	{
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO company (namecompany, emailcompany, phone, rfc, bussinesturn)"
				+ " values ('"+nameCompany+"', '"+emailCompany+"', '"+phone+"','"+RFC+"','"+bussinesTurn+"')";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			this.nameCompany=query;
		}
	}
	
	public void updateCompany() 
	{
		objC = new Connect();
		conn = objC.getConn();
						
		try
		{		
			String query = "UPDATE company SET namecompany = '"+nameCompany+"',"
					+ "emailcompany = '"+emailCompany+"', phone = '"+phone+"',"
					+ "rfc = '"+RFC+"', bussinesturn = '"+bussinesTurn+"' "				
					+ "WHERE keycompany = "+keyCompany;
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) 
		{
			
		}
	}
	
	public void deleteCompany() 
	{
		objC = new Connect();
		conn = objC.getConn();
		try 
		{
			String query = "DELETE FROM company WHERE keycompany = "+keyCompany;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<Company> listCompanys()
	{
		Company objCom;
		ArrayList<Company> arrCom = new ArrayList<Company>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT * FROM company";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				objCom = new Company();
				objCom.keyCompany = res.getInt("keycompany");
				objCom.nameCompany = res.getString("namecompany");				
				objCom.emailCompany = res.getString("emailcompany");
				objCom.phone = res.getString("phone");
				objCom.RFC = res.getString("rfc");
				objCom.bussinesTurn = res.getString("bussinesturn");												
				
				arrCom.add(objCom);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrCom;
	}
	
	public void getObjCompany(int keycompany)
	{
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM company WHERE keycompany = "+keycompany;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{							
				this.keyCompany = keycompany;
				this.nameCompany = res.getString("namecompany");
				this.emailCompany = res.getString("emailcompany");
				this.phone = res.getString("phone");
				this.RFC = res.getString("rfc");
				this.bussinesTurn = res.getString("bussinesturn");				
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	//=========================GETTERS AND SETTERS============================================
    @XmlElement(required=true)
	public int getKeyCompany() {
		return keyCompany;
	}

	public void setKeyCompany(int keyCompany) {
		this.keyCompany = keyCompany;
	}

	@XmlElement(required=true)
	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	@XmlElement(required=true)
	public String getEmailCompany() {
		return emailCompany;
	}

	public void setEmailCompany(String emailCompany) {
		this.emailCompany = emailCompany;
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
	public String getBussinesTurn() {
		return bussinesTurn;
	}

	public void setBussinesTurn(String bussinesTurn) {
		this.bussinesTurn = bussinesTurn;
	}
}
