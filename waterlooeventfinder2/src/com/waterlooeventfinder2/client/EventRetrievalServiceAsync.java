package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.dev.util.Callback;
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
	
	void GetEventByUserId(int userId, AsyncCallback<ArrayList<Event>> callback)
			throws IllegalArgumentException;
	
	void GetAllCategory(AsyncCallback<ArrayList<Category>> callback)
			throws IllegalArgumentException;
	
	void AddEvent(int userId, String categoryId, String start, String end,
			String location, String eventDescription, String eventName,
			String eventWebsite, String eventVideo, String eventPhoneNumber,
			String eventEmail, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void logToServer(String login, String password, AsyncCallback<String> callback);

	void logout(String sessionId, AsyncCallback<Integer> callback);

	void loginUsingSession(String sessionID, AsyncCallback<String> callback);

	void deleteEventById(int eventId, AsyncCallback<Integer> callback);

	void getCategories(AsyncCallback<ArrayList<Category>> callback);

	void CheckUrl(String website, AsyncCallback<Boolean> callback);

	void getCategoryNameById(int categoryId, AsyncCallback<Category> callback);

	
}