
package com.waterlooeventfinder2.shared;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Event {
	private int userId;
	private int categoryId;
	Date starHour = new Date();
	Date endHour = new Date();
	private String location;
	private String eventDescription;
	private String eventName;
	private String eventWebsite;
	private String eventVideo;
	private String eventPhoneNumber;
	private String eventEmail;
	
//	public Event(){
//		
//	}
	
	public Event(ResultSet rs) {
		try {
			userId = rs.getInt("userID");
			categoryId = rs.getInt("category");
			starHour = rs.getDate("startTime");
			endHour = rs.getDate("endTime");
			location = rs.getString("location");
			eventDescription = rs.getString("eventDescription");
			eventName = rs.getString("eventName");
			eventWebsite = rs.getString("eventWebsite");
			eventVideo = rs.getString("eventVideo");
			eventPhoneNumber = rs.getString("eventPhoneNumber");
			eventEmail = rs.getString("eventEmail");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Event(int userId, int categoryId, Date starHour, Date endHour,
			String location, String eventDescription, String eventName, String eventWebsite,
			String eventVideo, String eventPhoneNumber, String eventEmail) {
		
		this.userId = userId;
		this.categoryId = categoryId;
		this.starHour = starHour;
		this.endHour = endHour;
		this.location = location;
		this.eventDescription = eventDescription;
		this.eventName = eventName;
		this.eventWebsite = eventWebsite;
		this.eventVideo = eventVideo;
		this.eventPhoneNumber = eventPhoneNumber;
		this.eventEmail = eventEmail;
	}
	
	public Event(String name){
		eventName = name;
	}

	public String Location(){
		return location;
	}
	public void SetLocation(String location){
		this.location = location;
	}
	
	public String Description(){
		return eventDescription;
	}
	
	public void SetDescription(String description){
		eventDescription = description;
	}
	
	public String Name(){
		return eventName;
	}
	
	public void SetName(String name){
		eventName = name;
	}
	
	public String Website(){
		return eventWebsite;
	}
	
	public void SetWebsite(String website){
		eventWebsite = website;
	}
	
	public String Video(){
		return eventVideo;
	}
	
	public void SetVideo(String video){
		eventVideo = video;
	}
	
	public String PhoneNumber(){
		return eventPhoneNumber;
	}
	
	public void SetPhoneNumber(String phoneNumber){
		eventPhoneNumber = phoneNumber;
	}
	
	public String Email(){
		return eventEmail;
	}
	
	public void SetEmail(String email){
		eventEmail = email;
	}
}
