package com.waterlooeventfinder2.server;

import java.sql.*;

import java.util.ArrayList;
import java.util.Calendar;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.waterlooeventfinder2.client.EventRetrievalService;
import com.waterlooeventfinder2.shared.Event;

@SuppressWarnings("serial")
public class EventRetrievalServiceImpl extends RemoteServiceServlet implements
		EventRetrievalService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<Event> GetAllEvents() throws Exception {
		Connection conn = null ;
		ArrayList<Event> rtn = new ArrayList<Event>();
		
//		String query = "select * from Event";
//		
//		try {
//			//Class.forName("org.sqlite.JDBC");  
//			conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Mike\\MySQLiteDB\\t");
//			
//			try {
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(query);
//				
//				while (rs.next()) {
//					 int userId = rs.getInt("userID");
//					 int categoryId = rs.getInt("category");
//					 Date startHour = rs.getDate("startTime");
//					 Date endHour = rs.getDate("endTime");
//					 String location = rs.getString("location");
//					 String eventDescription = rs.getString("eventDescription");
//					 String eventName = rs.getString("eventName");
//					 String eventWebsite = rs.getString("eventWebsite");
//					 String eventVideo = rs.getString("eventVideo");
//					 String eventPhoneNumber = rs.getString("eventPhoneNumber");
//					 String eventEmail = rs.getString("eventEmail");
//					 
//					 rtn.add(new Event(userId, categoryId, startHour, endHour, location, eventDescription, 
//							 eventName, eventWebsite, eventVideo, eventPhoneNumber, eventEmail));
//					
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			conn.close();
//		}

		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		start.add(Calendar.DATE, +100);
		end.add(Calendar.DATE, +101);
		rtn.add(new Event(1, 2, start.getTime(), end.getTime(), 
				"location1", "desc1", "name1BEGIN", "www.website1.com", "www.youtube.com", 
				"519-777-7890", "event1@mail.com"));
		
		start.add(Calendar.DATE, +50);
		end.add(Calendar.DATE, +51);
		rtn.add(new Event(1, 3, start.getTime(), end.getTime(), 
				"location2", "desc2", "name2", "www.website2.com", "www.youtube.com", 
				"519-777-7890", "event2@mail.com"));
		
		start.add(Calendar.DATE, +70);
		end.add(Calendar.DATE, +71);
		rtn.add(new Event(1, 1, start.getTime(), end.getTime(), 
				"location6", "desc3", "name3", "www.website3.com", "www.youtube.com", 
				"519-777-7890", "event3@mail.com"));
		
		start.add(Calendar.DATE, +70);
		end.add(Calendar.DATE, +101);
		rtn.add(new Event(1, 2, start.getTime(), end.getTime(), 
				"location4", "des4c", "name4", "www.website4.com", "www.youtube.com", 
				"519-777-7890", "event4@mail.com"));

		start.add(Calendar.DATE, +70);
		end.add(Calendar.DATE, +101);
		rtn.add(new Event(1, 2, start.getTime(), end.getTime(), 
				"location4", "des4c", "name5", "www.website4.com", "www.youtube.com", 
				"519-777-7890", "event5@mail.com"));

		start.add(Calendar.DATE, +70);
		end.add(Calendar.DATE, +101);
		rtn.add(new Event(1, 2, start.getTime(), end.getTime(), 
				"location4", "des4c", "name6", "www.website4.com", "www.youtube.com", 
				"519-777-7890", "event6@mail.com"));

		return rtn;
	}
	
	
	// use "1", "2", "3", "4" as temporary time filters
	public ArrayList<Event> GetEventsByFilter(String categoryFilter, String timeFilter, int startEventNumber, int endEventNumber) {
		
		ArrayList<Event> rtn = new ArrayList<Event>();
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		if (timeFilter == "1") {
			start.add(Calendar.DATE, +1);
			end.add(Calendar.DATE, +2);
			rtn.add(new Event(1, 2, start.getTime(), end.getTime(), 
					"location1", "desc1", "name1", "www.website5.com", "www.youtube.com", 
					"519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +1);
			end.add(Calendar.DATE, +2);
			rtn.add(new Event(2, 2, start.getTime(), end.getTime(), 
					"location1", "desc1", "name2", "www.website5.com", "www.youtube.com", 
					"519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +1);
			end.add(Calendar.DATE, +2);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(), 
					"location1", "desc1", "name3", "www.website5.com", "www.youtube.com", 
					"519-777-7890", "event@mail.com"));
		
		} else if (timeFilter == "2") {
		
			start.add(Calendar.DATE, +3);
			end.add(Calendar.DATE, +4);
			rtn.add(new Event(4, 2, start.getTime(), end.getTime(), 
					"location2", "desc2", "name4", "www.website6.com", "www.youtube.com", 
					"519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +3);
			end.add(Calendar.DATE, +4);
			rtn.add(new Event(4, 2, start.getTime(), end.getTime(), 
					"location2", "desc2", "name5", "www.website6.com", "www.youtube.com", 
					"519-777-7890", "event@mail.com"));
		
		} else if (timeFilter == "2") {
			start.add(Calendar.DATE, +7);
			end.add(Calendar.DATE, +8);
			rtn.add(new Event(5, 2, start.getTime(), end.getTime(), 
					"location6", "desc3", "name6", "www.website.com", "www.youtube.com", 
					"519-777-7890", "event@mail.com"));
		
		} else {
			start.add(Calendar.DATE, +10);
			end.add(Calendar.DATE, +20);
			rtn.add(new Event(7, 2, start.getTime(), end.getTime(), 
					"location6", "desc3", "name7", "www.website.com", "www.youtube.com", 
					"519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +10);
			end.add(Calendar.DATE, +20);
			rtn.add(new Event(8, 2, start.getTime(), end.getTime(), 
					"location6", "desc3", "name8", "www.website.com", "www.youtube.com", 
					"519-777-7890", "event@mail.com"));
		}
		return rtn;
	}
	

	public Event GetEventById(int eventId) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		return new Event(1, 2, start.getTime(), end.getTime(), 
				"location1", "desc1", "name1", "www.website4.com", "www.youtube.com", 
				"519-777-7890", "event@mail.com");
	}
	

}
