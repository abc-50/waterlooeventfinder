package com.waterlooeventfinder2.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;

public class utils {
	public static Event RStoEvent(ResultSet rs) {
		Event ev = null;
		try {
			int eventId = rs.getInt("eventId");
			int userId = rs.getInt("userID");
			int categoryId = rs.getInt("category");
			Date starHour = rs.getDate("startTime");
			Date endHour = rs.getDate("endTime");
			String location = rs.getString("location");
			String eventDescription = rs.getString("eventDescription");
			String eventName = rs.getString("eventName");
			String eventWebsite = rs.getString("eventWebsite");
			String eventVideo = rs.getString("eventVideo");
			String eventPhoneNumber = rs.getString("eventPhoneNumber");
			String eventEmail = rs.getString("eventEmail");
			
			ev = new Event(eventId, userId, categoryId, starHour, endHour,
					location, eventDescription, eventName,
					eventWebsite, eventVideo, eventPhoneNumber,
					eventEmail);
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ev;

	}
	
	public static Category RStoCategory(ResultSet rs) {
		Category c = null;
		try {
			int catId = rs.getInt("categoryId");
			String catName = rs.getString("categoryName");
			
			c = new Category(catId, catName);
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
}
