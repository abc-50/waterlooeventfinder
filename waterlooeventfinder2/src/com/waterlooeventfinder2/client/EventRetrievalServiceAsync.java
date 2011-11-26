package com.waterlooeventfinder2.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;

/**
 * The async counterpart of <code>EventRetrievalService</code>.
 */
public interface EventRetrievalServiceAsync {
	void GetAllEvents(AsyncCallback<ArrayList<Event>> callback)
			throws IllegalArgumentException;

	void GetEventsByFilter(String categoryFilter, String timeFilter,
			int startEventNumber, int endEventNumber,
			AsyncCallback<ArrayList<Event>> callback)
			throws IllegalArgumentException;

	void GetEventById(int eventId, AsyncCallback<Event> callback)
			throws IllegalArgumentException;
	
	void GetAllCategory(AsyncCallback<ArrayList<Category>> callback)
			throws IllegalArgumentException;
	
	void AddEvent(int userId, int categoryId, Date starHour, Date endHour,
			String location, String eventDescription, String eventName,
			String eventWebsite, String eventVideo, String eventPhoneNumber,
			String eventEmail, AsyncCallback<Integer> callback)
			throws IllegalArgumentException;

	void logToServer(String login, String password, AsyncCallback<String> callback);

	void logout(String sessionId, AsyncCallback<Integer> callback);

	void loginUsingSession(String sessionID, AsyncCallback<String> callback);
	
}