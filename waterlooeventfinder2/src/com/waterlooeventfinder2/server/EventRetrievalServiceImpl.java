package com.waterlooeventfinder2.server;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.waterlooeventfinder2.client.EventRetrievalService;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Time;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.server.utils;

public class EventRetrievalServiceImpl extends RemoteServiceServlet implements
		EventRetrievalService {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
	private static final String DB = "eventsfinder";
	private static final String USER = "root";
	private static final String PW = "1secret";

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
	public ArrayList<Event> GetEventsByFilter(int categoryFilter, int timeFilter) {

		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Connection dbConn = null;

		c.add(Calendar.DATE, GetTimeInDays(timeFilter));

		ArrayList<Event> rtn = new ArrayList<Event>();

		String query = String.format(
				"select * from Event where category = %d and startTime > '%s'",
				categoryFilter + 1, dateFormat.format(c.getTime()));

		
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
	public ArrayList<Time> GetAllTime() {
		ArrayList<Time> times = new ArrayList<Time>();
		Connection dbConn = null;

		String query = "select * from Time";

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					times.add(utils.RStoTime(rs));

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

		return times;
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
	public String logToServer(String login, String password) {

		Connection dbConn = null;
		// String passwordEncrypted = BCrypt.hashpw(password, BCrypt.gensalt());

		String selectQuery = String
				.format("SELECT userId, password from user WHERE loginId = '%s'",
						login);

		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		HttpSession session = httpServletRequest.getSession();

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				// stmt.executeUpdate(insertQuery);
				ResultSet rs = stmt.executeQuery(selectQuery);

				if (rs.next()) {
					// check password hash
					String passwordHash = rs.getString("password");

					if (passwordHash.equals(password)) {

						// store session ID in database
						int userId = rs.getInt("userId");
						String sId = session.getId();

						StoreSessionIDInDB(userId, sId);

						return sId;
					} else {
						return null;
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

		return null;
	}

	private void StoreSessionIDInDB(int userId, String sessionId) {
		Connection dbConn = null;

		String updateQuery = String.format(
				"INSERT INTO session (userId, sessionID) VALUES ('%s', '%s') "
						+ "ON DUPLICATE KEY UPDATE sessionID = '%s'", userId,
				sessionId, sessionId);

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			Statement stmt = dbConn.createStatement();
			stmt.executeUpdate(updateQuery);

			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int GetTimeInDays(int timeFilter) {
		Connection dbConn = null;
		int rtn = 0;

		String selectQ = String
				.format("Select timeInDays from time where timeId = %d",
						timeFilter + 1);

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			Statement stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery(selectQ);

			if (rs.next()) {
				rtn = rs.getInt("timeInDays");
			}
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rtn;
	}

	@Override
	public Integer loginUsingSession(String sessionID) {
		Connection dbConn = null;

		String selectQuery = String.format(
				"SELECT * FROM session where sessionID = '%s'", sessionID);

		Integer rtn = 0;

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			Statement stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);

			if (rs.next()) {
				rtn = rs.getInt("userId");
			}
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rtn;
	}

	@Override
	public Integer logout(String sessionID) {

		Connection dbConn = null;

		String deleteQuery = String.format(
				"Delete FROM session where sessionID = '%s'", sessionID);

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			Statement stmt = dbConn.createStatement();
			stmt.executeUpdate(deleteQuery);

			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
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

}