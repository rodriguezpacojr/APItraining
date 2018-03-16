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
@XmlRootElement(name="coursetraining")

public class CourseTraining
{	
    private int keyCourseTraining;
    private String schedule;      
    private Date startDate;
    private Date endDate;
    private float price;
    private int capacity; 
    private int hours; 
    private String place;
    private String startHour;
    private String endHour;
    
    private int keyCourse;
    private Course Course;
    
    private int keyInstructor;    
    private Instructor Instructor;
    
    
    private Connection conn;
    private Connect objC;
    
    	//==========================METHODS===========================================
	public void insertCourseTraining() 
	{
		objC = new Connect();
		conn = objC.getConn();
			
		String query = "INSERT INTO course_training (keycourse,keyinstructor, schedule, startdate, enddate, price, capacity, hours, place, starthour, endhour)"
				+ " values ("+keyCourse+","+keyInstructor+",'"+schedule+"', '"+startDate+"', '"+endDate+"', '"+price+"', '"+capacity+"','"+hours+"','"+place+"','"+startHour+"','"+endHour+"')";
		try
		{					
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			
			this.schedule=query;
		}
	}
	
	public void updateCourseTraining() 
	{
		objC = new Connect();
		conn = objC.getConn();
				
		String query = "UPDATE course_training SET keyinstructor = "+keyInstructor+", "
				+ "schedule = '"+schedule+"',"
				+ "startdate= '"+startDate+"', enddate = '"+endDate+"',"
				+ "price = '"+price+"', capacity = '"+capacity+"',"
				+ "hours = '"+hours+"', place = '"+place+"',"
				+ "starthour = '"+startHour+"', endhour = '"+endHour+"' "
				+ "WHERE keycoursetraining = "+keyCourseTraining+" AND keycourse = "+keyCourse;
		try
		{						
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			this.schedule=query;
		}
	}
	
	public void deleteCourseTraining() 
	{
		objC = new Connect();
		conn = objC.getConn();
		try 
		{
			String query = "DELETE FROM course_training WHERE keycoursetraining = "+keyCourseTraining+ " AND keycourse = "+keyCourse;
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			conn.close();
		} catch (Exception e) {}
	}
		
	public List<CourseTraining> listCourseTrainings()
	{
		CourseTraining objCT;
		ArrayList<CourseTraining> arrCT = new ArrayList<CourseTraining>();

		objC = new Connect();
		conn = objC.getConn();
		
		try
		{
			String query = "SELECT * FROM course_training";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			//Convert all registers from query to objects
			while(res.next())
			{
				Course objCou = new Course();
				Instructor objIns = new Instructor();
				
				objCou.getObjCourse(res.getInt("keycourse"));
				objIns.getObjInstructor(res.getInt("keyinstructor"));
				
				objCT = new CourseTraining();
				objCT.keyCourseTraining = res.getInt("keycoursetraining");
				objCT.Course = objCou;
				objCT.Instructor = objIns;
				objCT.schedule = res.getString("schedule");
				objCT.startDate = res.getDate("startdate");
				objCT.endDate = res.getDate("enddate");
				objCT.price = res.getFloat("price");
				objCT.capacity = res.getInt("capacity");
				objCT.hours = res.getInt("hours");
				objCT.place = res.getString("place");
				objCT.startHour = res.getString("starthour");
				objCT.endHour = res.getString("endhour");
				arrCT.add(objCT);
			}
			conn.close();
		}
		catch(Exception e)
		{

		}
		return arrCT;
	}
	
	
	//=========================GETTERS AND SETTERS============================================
    // Annotation for determinate that the attribute is part of WS' out
    @XmlElement(required=true)
	public int getKeyCourseTraining() {
		return keyCourseTraining;
	}

	public void setKeyCourseTraining(int keyCourseTraining) {
		this.keyCourseTraining = keyCourseTraining;
	}

	@XmlElement(required=true)
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@XmlElement(required=true)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@XmlElement(required=true)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@XmlElement(required=true)
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@XmlElement(required=true)
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@XmlElement(required=true)
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@XmlElement(required=true)
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@XmlElement(required=true)
	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	@XmlElement(required=true)
	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	@XmlElement(required=true)
	public int getKeyCourse() {
		return keyCourse;
	}

	public void setKeyCourse(int keyCourse) {
		this.keyCourse = keyCourse;
	}

	@XmlElement(required=true)
	public int getKeyInstructor() {
		return keyInstructor;
	}

	public void setKeyInstructor(int keyInstructor) {
		this.keyInstructor = keyInstructor;
	}

	
	
	
	@XmlElement(required=true)
	public Course getCourse() {
		return Course;
	}
	public void setCourse(Course course) {
		Course = course;
	}
	
	@XmlElement(required=true)
	public Instructor getInstructor() {
		return Instructor;
	}
	public void setInstructor(Instructor instructor) {
		Instructor = instructor;
	}
}
