
package com.waterlooeventfinder2.shared;
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
	
	public Event(){
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