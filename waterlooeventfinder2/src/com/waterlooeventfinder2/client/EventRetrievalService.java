package com.waterlooeventfinder2.client;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.Time;
import com.waterlooeventfinder2.shared.TypeUser;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("eventsretrieval")
public interface EventRetrievalService extends RemoteService {
	ArrayList<Event> GetAllEvents();

	ArrayList<Event> GetEventsByFilter(int categoryFilter, int timeFilter);

	Event GetEventById(int eventId);

	ArrayList<Category> GetAllCategory();

	ArrayList<Time> GetAllTime();

	ArrayList<Event> GetEventByUserId(int userId);

	String AddEvent(int userId, String categoryId, String start, String end,
			String location, String eventDescription, String eventName,
			String eventWebsite, String eventVideo, String eventPhoneNumber,
			String eventEmail);

	String logToServer(String login, String password);

	Integer loginUsingSession(String sessionID);

	Integer logout(String sessionID);

	int deleteEventById(int eventId);

	boolean CheckUrl(String website);

	Category getCategoryNameById(int categoryId);

	String DeleteUserByName(String nameOfUser);

	String AddUserByName(String nameOfUser, String password2, String typeUser);

	String ModifyEvent(int eventId, int userId, String categoryId,
			String startEvent, String endEvent, String location,
			String description, String name, String website, String video,
			String phoneNumber, String email);

	ArrayList<String> TakeAllNamesFromUsers();

	ArrayList<TypeUser> GetTypesUser();

}