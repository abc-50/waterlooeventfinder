package com.waterlooeventfinder2.server;

import java.sql.*;
import java.util.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.waterlooeventfinder2.client.EventRetrievalService;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.server.utils;

@SuppressWarnings("serial")
public class EventRetrievalServiceImpl extends RemoteServiceServlet implements
EventRetrievalService {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
	private static final String DB = "eventsfinder";
	private static final String USER = "root";
	private static final String PW = "";

	public ArrayList<Event> GetAllEvents() {
		Connection dbConn = null;

		ArrayList<Event> rtn = new ArrayList<Event>();
		rtn.clear();
		String query = "select * from Event";
		
		try {
			dbConn = DriverManager.getConnection( URL + DB, USER, PW );
			
			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next()) {

					//rtn.add(RStoEvent(rs));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return rtn;
	}


	// use "1", "2", "3", "4" "5" as temporary time filters
	public ArrayList<Event> GetEventsByFilter(String categoryFilter,
	String timeFilter, int startEventNumber, int endEventNumber) {

		Connection dbConn = null;
		
		ArrayList<Event> rtn = new ArrayList<Event>();
		rtn.clear();
		String query = "select * from Event";
		
		try {
			dbConn = DriverManager.getConnection( URL + DB, USER, PW );
			
			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next()) {

					rtn.add(utils.RStoEvent(rs));
					Calendar start = Calendar.getInstance();
					Calendar end = Calendar.getInstance();
					start.add(Calendar.DATE, +8);
					start.add(Calendar.HOUR, +3);
					end.add(Calendar.DATE, +8);
					end.add(Calendar.DATE, +8);
					rtn.add(new Event(3, 2, start.getTime(), end.getTime(),
					"location1", "desc1", "Concert AC/DC - Waterloo !",
					"www.website5.com", "www.youtube.com", "519-777-7890",
					"event@mail.com"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return rtn;
		
		// We want to show ALL + UPCOMING
		


	}


	public Event GetEventById(int eventId) {
		Connection dbConn = null;

		Event rtn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/eventsfinder";
		String query = String.format("select * from Event where eventId = %d", eventId);
		
		try {
			dbConn = DriverManager.getConnection( URL + DB , USER, PW );
			
			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next()) {

					rtn = utils.RStoEvent(rs);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return rtn;
	}

	@Override
	public ArrayList<Category> GetAllCategory() {
		ArrayList<Category> categories = null;
		Connection dbConn = null;
		
		String query = "select * from Category";
		
		try {
			dbConn = DriverManager.getConnection( URL + DB, USER, PW );
			
			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next()) {

					categories.add(utils.RStoCategory(rs));
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (dbConn != null) dbConn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return categories;
	}

}
