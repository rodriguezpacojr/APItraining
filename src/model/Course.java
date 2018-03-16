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
@XmlRootElement(name="course")

public class Course
{	
    private int keyCourse;
    private String nameCourse;
    private String temary;
    private String objetive;
    
    private int keyTypeCourse;
    private TypeCourse TypeCourse; 
    
    private Connection conn;
    private Connect objC;	    

		//==========================METHODS===========================================
	public void insertCourse() 
	{
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO course (namecourse, temary, objetive,keytypecourse)"
				+ " values ('"+nameCourse+"', '"+temary+"', '"+objetive+"',"+keyTypeCourse+")";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			this.nameCourse=query;
		}
	}
	
	public void updateCourse() 
	{
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE course SET namecourse = '"+nameCourse+"',"
				+ "temary = '"+temary+"', objetive = '"+objetive+"', keytypecourse = "+keyTypeCourse+" "								
				+ "WHERE keycourse = "+keyCourse;
		try
		{						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.nameCourse=query;
		}
	}
	
	public void deleteCourse() 
	{
		objC = new Connect();
		conn = objC.getConn();
		try 
		{
			String query = "DELETE FROM course WHERE keycourse = "+keyCourse;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<Course> listCourses()
	{
		Course objCou;
		ArrayList<Course> arrCou = new ArrayList<Course>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT * FROM course";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				TypeCourse objTC = new TypeCourse();
				objTC.getObjTypeCourse(res.getInt("keytypecourse"));
				
				objCou = new Course();
				objCou.keyCourse = res.getInt("keycourse");
				objCou.nameCourse = res.getString("namecourse");				
				objCou.temary = res.getString("temary");
				objCou.objetive = res.getString("objetive");
				objCou.TypeCourse = objTC;
				arrCou.add(objCou);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrCou;
	}
	
	public void getObjCourse(int keycourse)
	{
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			
			String query = "SELECT * FROM course WHERE keycourse = "+keycourse;
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				TypeCourse objTC = new TypeCourse();
				objTC.getObjTypeCourse(res.getInt("keytypecourse"));
				
				this.keyCourse = keycourse;
				this.nameCourse = res.getString("namecourse");
				this.temary = res.getString("temary");
				this.objetive = res.getString("objetive");
				this.setTypeCourse(objTC);
			}
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	//=========================GETTERS AND SETTERS============================================
    @XmlElement(required=true)		
	public int getKeyCourse() {
		return keyCourse;
	}
	public void setKeyCourse(int keyCourse) {
		this.keyCourse = keyCourse;
	}

	@XmlElement(required=true)
	public String getNameCourse() {
		return nameCourse;
	}
	public void setNameCourse(String nameCourse) {
		this.nameCourse = nameCourse;
	}

	@XmlElement(required=true)
	public String getTemary() {
		return temary;
	}
	public void setTemary(String temary) {
		this.temary = temary;
	}

	@XmlElement(required=true)
	public String getObjetive() {
		return objetive;
	}
	public void setObjetive(String objetive) {
		this.objetive = objetive;
	}

	@XmlElement(required=true)
	public int getKeyTypeCourse() {
		return keyTypeCourse;
	}
	public void setKeyTypeCourse(int keyTypeCourse) {
		this.keyTypeCourse = keyTypeCourse;
	}

	
	
	@XmlElement(required=true)
	public TypeCourse getTypeCourse() {
		return TypeCourse;
	}
	public void setTypeCourse(TypeCourse typeCourse) {
		TypeCourse = typeCourse;
	}	
}
