package com.waterlooeventfinder2.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.Time;
import com.waterlooeventfinder2.shared.User;

public class utils {
	public static Event RStoEvent(ResultSet rs) {
		Event ev = null;
		try {
			int eventId = rs.getInt("eventId");
			int userId = rs.getInt("userID");
			int categoryId = rs.getInt("category");
			Date starHour = rs.getTimestamp("startTime");
			Date endHour = rs.getTimestamp("endTime");			
			String location = rs.getString("location");
			String eventDescription = rs.getString("eventDescription");
			String eventName = rs.getString("eventName");
			String eventWebsite = rs.getString("eventWebsite");
			String eventVideo = rs.getString("eventVideo");
			String eventPhoneNumber = rs.getString("eventPhoneNumber");
			String eventEmail = rs.getString("eventEmail");

			ev = new Event(eventId, userId, categoryId, starHour, endHour,
					location, eventDescription, eventName, eventWebsite,
					eventVideo, eventPhoneNumber, eventEmail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}
	
	public static Time RStoTime(ResultSet rs) {
		Time t = null;
		
		try {
			int timeId = rs.getInt("timeId");
			String timeDisplay = rs.getString("timeDisplayName");

			t = new Time(timeId, timeDisplay);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}

	public static User RStoUser(ResultSet rs) {
		User u = null;

		try {
			int userId = rs.getInt("userId");
			String loginId = rs.getString("loginId");
			String displayName = rs.getString("displayName");
			String phoneNumber = rs.getString("phoneNumber");
			//String email = rs.getString("email");
			
			u = new User(userId,loginId,"",displayName,"",phoneNumber,1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}

}