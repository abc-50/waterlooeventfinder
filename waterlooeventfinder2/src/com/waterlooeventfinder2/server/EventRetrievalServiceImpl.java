package com.waterlooeventfinder2.server;

import java.sql.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.waterlooeventfinder2.client.EventRetrievalService;
import com.waterlooeventfinder2.shared.Event;

public class EventRetrievalServiceImpl extends RemoteServiceServlet implements
		EventRetrievalService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<Event> GetAllEvents() {
		Connection conn ;
		ArrayList<Event> rtn = new ArrayList<Event>();
		
//		String query = "select * from Event";
//		
//		try {
//			conn = DriverManager.getConnection("jdbc:sqlite:testdb.sql");
//			
//			try {
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(query);
//				
//				while (rs.next()) {
//					rtn.add(new Event(rs));
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
				"location1", "desc1", "name1", "www.website.com", "www.youtube.com", 
				"519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +50);
		end.add(Calendar.DATE, +51);
		rtn.add(new Event(1, 3, start.getTime(), end.getTime(), 
				"location2", "desc2", "name2", "www.website.com", "www.youtube.com", 
				"519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +70);
		end.add(Calendar.DATE, +71);
		rtn.add(new Event(1, 1, start.getTime(), end.getTime(), 
				"location6", "desc3", "name3", "www.website.com", "www.youtube.com", 
				"519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +70);
		end.add(Calendar.DATE, +101);
		rtn.add(new Event(1, 2, start.getTime(), end.getTime(), 
				"location4", "des4c", "name4", "www.website.com", "www.youtube.com", 
				"519-777-7890", "event@mail.com"));

		return rtn;
	}
	
	@Override
	public ArrayList<Event> GetEventsByCategory(String category) {
		ArrayList<Event> rtn = new ArrayList<Event>();
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		start.add(Calendar.DATE, +100);
		end.add(Calendar.DATE, +101);
		rtn.add(new Event(1, 2, start.getTime(), end.getTime(), 
				"location1", "desc1", "name1", "www.website.com", "www.youtube.com", 
				"519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +50);
		end.add(Calendar.DATE, +51);
		rtn.add(new Event(1, 2, start.getTime(), end.getTime(), 
				"location2", "desc2", "name2", "www.website.com", "www.youtube.com", 
				"519-777-7890", "event@mail.com"));
		
		start.add(Calendar.DATE, +70);
		end.add(Calendar.DATE, +71);
		rtn.add(new Event(1, 2, start.getTime(), end.getTime(), 
				"location6", "desc3", "name3", "www.website.com", "www.youtube.com", 
				"519-777-7890", "event@mail.com"));
		
		
		return rtn;
	}
	

}
