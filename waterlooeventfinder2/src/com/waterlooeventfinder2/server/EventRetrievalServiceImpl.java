package com.waterlooeventfinder2.server;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.waterlooeventfinder2.client.EventRetrievalService;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.User;
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
	private static final String PW = "a3z4e5r6";

	public ArrayList<Event> GetAllEvents() {
		Connection dbConn = null;

		ArrayList<Event> rtn = new ArrayList<Event>();
		rtn.clear();
		String query = "select * from Event";

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					rtn.add(utils.RStoEvent(rs));

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
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					rtn.add(utils.RStoEvent(rs));
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
		String query = String.format("select * from Event where eventId = %d",
				eventId);

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

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
	public ArrayList<Event> GetEventByUserId(int userId) {
		// TODO Auto-generated method stub
		Connection dbConn = null;

		ArrayList<Event> rtn = new ArrayList<Event>();
		rtn.clear();
		String query = String.format("select * from Event where userID = %d",
				userId);

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					rtn.add(utils.RStoEvent(rs));

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
		ArrayList<Category> categories = new ArrayList<Category>();
		Connection dbConn = null;

		String query = "select * from Category";

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					categories.add(utils.RStoCategory(rs));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (dbConn != null)
				dbConn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return categories;
	}

	@Override
	public int deleteEventById(int eventId) {
		// TODO Auto-generated method stub
		Connection dbConn = null;

		String query = String.format("DELETE FROM Event WHERE eventId = %d",
				eventId);

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				stmt.executeUpdate(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public User logToServer(String login, String password) {
		// TODO Auto-generated method stub
		User user = new User();

		Connection dbConn = null;
		// String passwordEncrypted = BCrypt.hashpw(password, BCrypt.gensalt());
		int rtn = 1;
		int sessionKey = 0;

		/*
		 * String insertQuery = String .format(
		 * "INSERT INTO user (loginId, password, displayName) VALUES ('%s','%s','Martin')"
		 * , login, passwordEncrypted);
		 */
		String selectQuery = String.format(
				"SELECT * from user WHERE loginId = '%s' AND password = '%s'",
				login, password);

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				// stmt.executeUpdate(insertQuery);
				ResultSet rs = stmt.executeQuery(selectQuery);

				while (rs.next()) {
					if (rs.getString("loginId").equals(login)) {
						sessionKey = 2;
						user.setLoggedInApplication(true);
						String nameUser = rs.getString("displayName");
						user.setDisplayName(nameUser);

						if (user.isLoggedInApplication()) {
							// We store User Sessions
							HttpServletRequest httpServletRequest = this
									.getThreadLocalRequest();
							HttpSession session = httpServletRequest
									.getSession();
							session.setAttribute("user", user);
						}
					} else {
						user.setLoggedInApplication(false);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User loginUsingSession() {
		User user = null;
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		HttpSession session = httpServletRequest.getSession();
		Object userSession = session.getAttribute("user");
		if (userSession != null && userSession instanceof User) {
			user = (User) userSession;
		}
		return user;
	}

	@Override
	public int logout() {

		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		HttpSession session = httpServletRequest.getSession();
		session.removeAttribute("user");

		return 1;
	}

	@Override
	public String AddEvent(int userId, int categoryId, String start,
			String end, String location, String eventDescription,
			String eventName, String eventWebsite, String eventVideo,
			String eventPhoneNumber, String eventEmail) {
		Connection dbConn = null;

		eventPhoneNumber = "510342349";

		String query = String
				.format("INSERT INTO Event (userID, category, startTime, endTime, location, eventDescription, eventName, eventWebsite, eventVideo, eventPhoneNumber, eventEmail)"
						+ "								 VALUES ('%d','%d','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
						userId, categoryId, start, end, location,
						eventDescription, eventName, eventWebsite, eventVideo,
						eventPhoneNumber, eventEmail);
		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				stmt.executeUpdate(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "ok";
	}

	@Override
	public ArrayList<Category> getCategories() {
		Connection dbConn = null;

		ArrayList<Category> rtn = new ArrayList<Category>();

		rtn.clear();
		String query = "SELECT * FROM category ORDER BY categoryId";

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					rtn.add(utils.RStoCategory(rs));
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

}