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
@XmlRootElement(name="history")

public class History
{	
    private int keyHistory;
    private int keyCourseTraining;
    private CourseTraining CourseTraining;
    
    private int keyCourse;
    private Course Course;
    
    private int keyEmployee;
    private Employee Employee;
    
    private float calification;
    
    private Connection conn;
    private Connect objC;
	    
    
    	//==========================METHODS===========================================
	public void insertHistory() 
	{
		objC = new Connect();
		conn = objC.getConn();
					
		try
		{	
			String query = "INSERT INTO history (keycoursetraining, keycourse, keyemployee, calification)"
					+ " values ("+keyCourseTraining+", "+keyCourse+", "+keyEmployee+", "+calification+")";
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) 
		{						
		}
	}
	
	public void updateHistory() 
	{
		objC = new Connect();
		conn = objC.getConn();
						
		try
		{		
			String query = "UPDATE history SET calification = "+calification										
					+ " WHERE keyhistory = "+keyHistory+" AND keycoursetraining = "+keyCourseTraining
							+ " AND keycourse = "+keyCourse+" AND keyemployee = "+keyEmployee;
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) 
		{		
		}
	}
		
	public List<History> listHistories()
	{
		History objHis;
		ArrayList<History> arrHis = new ArrayList<History>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT * FROM history";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				Course objCo = new Course(); 
				Employee objEm = new Employee();
				
				objCo.getObjCourse(res.getInt("keycourse"));				
				objEm.getObjEmployee(res.getInt("keyemployee"));
				
				objHis = new History();				
				objHis.keyHistory = res.getInt("keyhistory");				
				objHis.keyCourseTraining = res.getInt("keycoursetraining");
				objHis.Course = objCo;
				objHis.Employee = objEm;
				objHis.calification = res.getFloat("calification");
				arrHis.add(objHis);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrHis;
	}
	
	
	//=========================GETTERS AND SETTERS============================================
	@XmlElement(required=true)		
	public int getKeyHistory() {
		return keyHistory;
	}

	public void setKeyHistory(int keyHistory) {
		this.keyHistory = keyHistory;
	}	
	
	@XmlElement(required=true)
	public int getKeyCourseTraining() {
		return keyCourseTraining;
	}

	public void setKeyCourseTraining(int keyCourseTraining) {
		this.keyCourseTraining = keyCourseTraining;
	}

	@XmlElement(required=true)
	public int getKeyCourse() {
		return keyCourse;
	}

	public void setKeyCourse(int keyCourse) {
		this.keyCourse = keyCourse;
	}

	@XmlElement(required=true)
	public int getKeyEmployee() {
		return keyEmployee;
	}

	public void setKeyEmployee(int keyEmployee) {
		this.keyEmployee = keyEmployee;
	}

	@XmlElement(required=true)
	public float getCalification() {
		return calification;
	}

	public void setCalification(float calification) {
		this.calification = calification;
	}

	
	
	@XmlElement(required=true)
	public CourseTraining getCourseTraining() {
		return CourseTraining;
	}
	public void setCourseTraining(CourseTraining courseTraining) {
		CourseTraining = courseTraining;
	}

	@XmlElement(required=true)
	public Course getCourse() {
		return Course;
	}
	public void setCourse(Course course) {
		Course = course;
	}

	@XmlElement(required=true)
	public Employee getEmployee() {
		return Employee;
	}
	public void setEmployee(Employee employee) {
		Employee = employee;
	}
}
