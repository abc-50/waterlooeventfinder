package com.waterlooeventfinder2.shared;

import java.io.Serializable;
import java.util.*;

public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int eventId;
	private int userId;
	private int categoryId;
	private Date starHour = new Date();
	private Date endHour = new Date();
	private String location;
	private String eventDescription;
	private String eventName;
	private String eventWebsite;
	private String eventVideo;
	private String eventPhoneNumber;
	private String eventEmail;

	public Event() {
	}


	public Event(int eventId, int userId, int categoryId, Date starHour, Date endHour,
			String location, String eventDescription, String eventName,
			String eventWebsite, String eventVideo, String eventPhoneNumber,
			String eventEmail) {

		this.eventId = eventId;
		this.userId = userId;
		this.setCategoryId(categoryId);
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

	public Event(String name) {
		eventName = name;
	}

	public String Location() {
		return location;
	}

	public void SetLocation(String location) {
		this.location = location;
	}

	public String Description() {
		return eventDescription;
	}

	public void SetDescription(String description) {
		eventDescription = description;
	}

	public String Name() {
		return eventName;
	}

	public void SetName(String name) {
		eventName = name;
	}

	public String Website() {
		return eventWebsite;
	}

	public void SetWebsite(String website) {
		eventWebsite = website;
	}

	public String Video() {
		return eventVideo;
	}

	public void SetVideo(String video) {
		eventVideo = video;
	}

	public String PhoneNumber() {
		return eventPhoneNumber;
	}

	public void SetPhoneNumber(String phoneNumber) {
		eventPhoneNumber = phoneNumber;
	}

	public String Email() {
		return eventEmail;
	}

	public void SetEmail(String email) {
		eventEmail = email;
	}

	public Date getStarHour() {
		return starHour;
	}

	public Date getEndHour() {
		return endHour;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}


	public int getEventId() {
		return eventId;
	}


	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
}
