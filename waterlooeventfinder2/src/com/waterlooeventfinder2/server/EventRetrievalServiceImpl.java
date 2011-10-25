package com.waterlooeventfinder2.server;

import java.sql.*;
import java.util.*;


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
		Connection conn = null;
		
		ArrayList<Event> rtn = new ArrayList<Event>();
		rtn.clear();
		// String query = "select * from Event";
		//
		// try {
		// //Class.forName("org.sqlite.JDBC");
		// conn =
		// DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Mike\\MySQLiteDB\\t");
		//
		// try {
		// Statement stmt = conn.createStatement();
		// ResultSet rs = stmt.executeQuery(query);
		//
		// while (rs.next()) {
		// int userId = rs.getInt("userID");
		// int categoryId = rs.getInt("category");
		// Date startHour = rs.getDate("startTime");
		// Date endHour = rs.getDate("endTime");
		// String location = rs.getString("location");
		// String eventDescription = rs.getString("eventDescription");
		// String eventName = rs.getString("eventName");
		// String eventWebsite = rs.getString("eventWebsite");
		// String eventVideo = rs.getString("eventVideo");
		// String eventPhoneNumber = rs.getString("eventPhoneNumber");
		// String eventEmail = rs.getString("eventEmail");
		//
		// rtn.add(new Event(userId, categoryId, startHour, endHour, location,
		// eventDescription,
		// eventName, eventWebsite, eventVideo, eventPhoneNumber, eventEmail));
		//
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } finally {
		// conn.close();
		// }

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.add(Calendar.DATE, +1);
		end.add(Calendar.DATE, +1); 
		
		Calendar calendar = new GregorianCalendar();
		String am_pm;
		
		int hour = calendar.get(Calendar.HOUR);
		
		// EVENTS USED FOR THE DEMO
		// ONCE ALL EVENTS HAVE BEEN ADDED TO getEventsByFilter
		// EVENTS HAS TO BE ADDED CLASSED FROM THEIR STARTING DAY + HOUR
		
		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(1, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "Football: Waterloo vs Wilfried", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));

		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(2, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "Bomber : Night Beer Event ", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));

		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "Concert U2 on Waterloo !", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "Niagara Falls visit", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "Engineering Society Night Club", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "Engineering Society Night Club", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "GradeHouse Night : > 19 years", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "Free Horse Riding Session", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "Wine Degustation at SLC", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +0);
		end.add(Calendar.DATE, +1);
		rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
				"location1", "desc1", "Wine Degustation at SLC", "www.website5.com",
				"www.youtube.com", "519-777-7890", "event@mail.com"));
		return rtn;
	}

	// use "1", "2", "3", "4" "5" as temporary time filters
	public ArrayList<Event> GetEventsByFilter(String categoryFilter,
			String timeFilter, int startEventNumber, int endEventNumber) {

		ArrayList<Event> rtn = new ArrayList<Event>();

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		// EVENTS OF TODAY
		if (timeFilter.equals("One day")) {
			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(1, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Football: Waterloo vs Wilfried", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));

			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(2, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Bomber : Night Beer Event ", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));

			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Concert U2 on Waterloo !", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Niagara Falls visit", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Engineering Society Night Club", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Engineering Society Night Club", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "GradeHouse Night : > 19 years", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Free Horse Riding Session", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Wine Degustation at SLC", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +0);
			end.add(Calendar.DATE, +1);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Wine Degustation at SLC", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));

			// EVENTS OF TOMORROW
		} else if (timeFilter.equals("Two days")) {

			start.add(Calendar.DATE, +3);
			end.add(Calendar.DATE, +4);
			rtn.add(new Event(4, 2, start.getTime(), end.getTime(),
					"location2", "desc2", "Hockey game Waterloo vs Wilfried", "www.website6.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));

			start.add(Calendar.DATE, +3);
			end.add(Calendar.DATE, +4);
			rtn.add(new Event(4, 2, start.getTime(), end.getTime(),
					"location2", "desc2", "Chess tournament Waterloo vs Wilfried", "www.website6.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));

			// EVENTS IN 1 WEEK
		} else if (timeFilter.equals("Three days")) {
			start.add(Calendar.DATE, +7);
			end.add(Calendar.DATE, +8);
			rtn.add(new Event(5, 2, start.getTime(), end.getTime(),
					"location6", "desc3", "Free hot dogs at SLC", "www.website.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
		}else if(timeFilter.equals("Upcoming")){
			start.add(Calendar.DATE, +8);
			end.add(Calendar.DATE, +9);
			rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Camping at Algonquin Park", "www.website5.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +10);
			end.add(Calendar.DATE, +10);
			rtn.add(new Event(7, 2, start.getTime(), end.getTime(),
					"location6", "desc3", "name7", "www.website.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
			
			start.add(Calendar.DATE, +10);
			end.add(Calendar.DATE, +20);
			rtn.add(new Event(8, 2, start.getTime(), end.getTime(),
					"location6", "desc3", "name8", "www.website.com",
					"www.youtube.com", "519-777-7890", "event@mail.com"));
		} else {
			
		}
		return rtn;
	}

	public Event GetEventById(int eventId) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		return new Event(1, 2, start.getTime(), end.getTime(), "location1",
				"desc1", "name1", "www.website4.com", "www.youtube.com",
				"519-777-7890", "event@mail.com");
	}

}
