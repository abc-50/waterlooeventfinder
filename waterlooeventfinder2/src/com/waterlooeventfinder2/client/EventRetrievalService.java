package com.waterlooeventfinder2.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.User;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("eventsretrieval")
public interface EventRetrievalService extends RemoteService {
	ArrayList<Event> GetAllEvents();

	ArrayList<Event> GetEventsByFilter(String categoryFilter,
			String timeFilter, int startEventNumber, int endEventNumber);

	Event GetEventById(int eventId);

	ArrayList<Category> GetAllCategory();
	
	ArrayList<Event> GetEventByUserId(int userId);

	String AddEvent(int userId, int categoryId, String start, String end,
			String location, String eventDescription, String eventName,
			String eventWebsite, String eventVideo, String eventPhoneNumber,
			String eventEmail);

	User logToServer(String login, String password);

	User loginUsingSession();

	int logout();

	int deleteEventById(int eventId);

	ArrayList<Category> getCategories();

	

	
}
