package com.waterlooeventfinder2.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.waterlooeventfinder2.client.EventRetrievalService;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.Time;
import com.waterlooeventfinder2.shared.TypeUser;

public class EventRetrievalServiceImpl extends RemoteServiceServlet implements
		EventRetrievalService {

	private static final long serialVersionUID = 1L;

	// Connection strings
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
	private static final String DB = "eventsfinder";
	private static final String USER = "root";
	private static final String PW = "";
	private Calendar c;
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * Return all events
	 */
	public ArrayList<Event> GetAllEvents() {
		Connection dbConn = null;

		ArrayList<Event> rtn = new ArrayList<Event>();
		c = Calendar.getInstance();
		String query = String.format(
				"select * from Event where startTime > '%s'",
				dateFormat.format(c.getTime()));

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					// add events to return value
					rtn.add(utils.RStoEvent(rs));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rtn;
	}

	/**
	 * Return events that passes given filter
	 */
	public ArrayList<Event> GetEventsByFilter(int categoryFilter, int timeFilter) {
		ArrayList<Event> rtn = new ArrayList<Event>();

		Connection dbConn = null;
		c = Calendar.getInstance();
		c.add(Calendar.DATE, GetTimeInDays(timeFilter));

		// query string
		String query = String.format(
				"select * from Event where category = %d and startTime > '%s'",
				categoryFilter + 1, dateFormat.format(c.getTime()));

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					// add events to return
					rtn.add(utils.RStoEvent(rs));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rtn;

		// We want to show ALL + UPCOMING

	}

	/**
	 * Return specific event given event id
	 */
	public Event GetEventById(int eventId) {
		Connection dbConn = null;

		Event rtn = null;
		String query = String.format("select * from event where eventId = %d",
				eventId);

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				if (rs.next()) {
					rtn = utils.RStoEvent(rs);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rtn;
	}

	/**
	 * Gets all events of a user
	 */
	@Override
	public ArrayList<Event> GetEventByUserId(int userId) {
		Connection dbConn = null;

		ArrayList<Event> rtn = new ArrayList<Event>();

		// query
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

			e.printStackTrace();
		}

		return rtn;
	}

	/**
	 * Gets list of all category filters
	 */
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

				while (rs.next())
					categories.add(utils.RStoCategory(rs));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (dbConn != null)
				dbConn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return categories;
	}

	/**
	 * Gets list of all time filters
	 */
	@Override
	public ArrayList<Time> GetAllTime() {
		ArrayList<Time> times = new ArrayList<Time>();
		Connection dbConn = null;

		String query = "SELECT * FROM time";

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next())
					times.add(utils.RStoTime(rs));

			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (dbConn != null)
				dbConn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return times;
	}

	/**
	 * Delete an event
	 */
	@Override
	public int deleteEventById(int eventId) {
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

			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Login with user name and password
	 */
	@Override
	public String logToServer(String login, String password) {

		Connection dbConn = null;
		// ideally we could use hashed passwords
		// but we ran out of time
		// basic structure is commented out

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

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Store the user id and session id so we can login using session later
	 * 
	 * @param userId
	 *            user id
	 * @param sessionId
	 *            session id
	 */
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

			e.printStackTrace();
		}

	}

	/**
	 * Get day representation of time filter
	 * 
	 * @param timeFilter
	 * @return
	 */
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

			e.printStackTrace();
		}

		return rtn;
	}

	/**
	 * attempt to login using given session id
	 */
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

			e.printStackTrace();
		}

		return rtn;
	}

	/**
	 * log out with session id
	 */
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

			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * check if url is valid TODO: (why is this here)???
	 */
	@Override
	public boolean CheckUrl(String website) {
		try {
			URL url = new URL(website);
			URLConnection conn = url.openConnection();
			conn.connect();
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	/**
	 * add event to database
	 */
	@Override
	public String AddEvent(int userId, String categoryId, String start,
			String end, String location, String eventDescription,
			String eventName, String eventWebsite, String eventVideo,
			String eventPhoneNumber, String eventEmail) {
		Connection dbConn = null;

		eventPhoneNumber = "510342349";

		String query = String
				.format("INSERT INTO Event (userID, category, startTime, endTime, location, eventDescription, eventName,"
						+ " eventWebsite, eventVideo, eventPhoneNumber, eventEmail)"
						+ " VALUES ('%d','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
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

			e.printStackTrace();
		}

		return "ok";
	}

	/**
	 * Delete a user
	 */
	@Override
	public String DeleteUserByName(String userName) {
		Connection dbConn = null;

		String message = "";

		String query = String.format("SELECT userId FROM user", userName);
		int userId = 0;

		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				if (rs.next())
					userId = rs.getInt("userId"); // get user id

			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		// delete all events created by this user
		String queryDeleteEventUserId = String.format(
				"DELETE FROM event WHERE userId = '%d'", userId);
		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				stmt.executeUpdate(queryDeleteEventUserId);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		// delete user object
		String queryDeleteUserById = String.format(
				"DELETE FROM user WHERE userId = '%d'", userId);
		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				stmt.executeUpdate(queryDeleteUserById);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return message;
	}

	@Override
	public Category getCategoryNameById(int categoryId) {
		// TODO: WHAT DOES THIS DO???
		return null;
	}

	/**
	 * Create a user
	 */
	@Override
	public String AddUserByName(String nameOfUser, String password,
			String userType) {

		Connection dbConn = null;
		String query = String
				.format("INSERT INTO user (password, loginId, displayName, userType) VALUES ('%s','%s','%s','%s')",
						password, nameOfUser, nameOfUser, userType);
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

			e.printStackTrace();
		}

		return "ok";
	}

	/**
	 * update an event
	 */
	@Override
	public String ModifyEvent(int eventId, int userId, String categoryId,
			String startEvent, String endEvent, String location,
			String description, String name, String website, String video,
			String phoneNumber, String email) {

		Connection dbConn = null;

		String query = String
				.format("UPDATE event SET category = '%s', startTime = '%s', endTime = '%s', location = '%s', eventDescription='%s', eventName='%s', eventWebsite='%s', eventVideo='%s', eventPhoneNumber='%s', eventEmail='%s' WHERE eventId = '%d'",
						categoryId, startEvent, endEvent, location,
						description, name, website, video, phoneNumber, email,
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

			e.printStackTrace();
		}

		return "ok";
	}

	@Override
	public ArrayList<String> TakeAllNamesFromUsers() {
		Connection dbConn = null;

		ArrayList<String> userNames = new ArrayList<String>();

		String query = String.format("SELECT displayName from user");
		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					userNames.add(rs.getString("displayName"));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return userNames;
	}

	@Override
	public ArrayList<TypeUser> GetTypesUser() {
		Connection dbConn = null;

		ArrayList<TypeUser> userTypes = new ArrayList<TypeUser>();

		String query = String.format("SELECT * FROM usertype");
		try {
			dbConn = DriverManager.getConnection(URL + DB, USER, PW);

			try {
				Statement stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					userTypes.add(utils.RStoTypeUser(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbConn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return userTypes;
	}

}