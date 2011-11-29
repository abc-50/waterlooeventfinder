package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.Time;
import com.waterlooeventfinder2.shared.TypeUser;

/**
 * The async counterpart of <code>EventRetrievalService</code>.
 */
public interface EventRetrievalServiceAsync {
	void GetAllEvents(AsyncCallback<ArrayList<Event>> callback)
			throws IllegalArgumentException;

	void GetEventsByFilter(int categoryFilter, int timeFilter,
			AsyncCallback<ArrayList<Event>> callback)
			throws IllegalArgumentException;

	void GetEventById(int eventId, AsyncCallback<Event> callback)
			throws IllegalArgumentException;

	void GetEventByUserId(int userId, AsyncCallback<ArrayList<Event>> callback)
			throws IllegalArgumentException;

	void GetAllCategory(AsyncCallback<ArrayList<Category>> callback)
			throws IllegalArgumentException;

	void GetAllTime(AsyncCallback<ArrayList<Time>> callback)
			throws IllegalArgumentException;

	void AddEvent(int userId, String categoryId, String start, String end,
			String location, String eventDescription, String eventName,
			String eventWebsite, String eventVideo, String eventPhoneNumber,
			String eventEmail, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void logToServer(String login, String password,
			AsyncCallback<String> callback);

	void logout(String sessionId, AsyncCallback<Integer> callback);

	void loginUsingSession(String sessionID, AsyncCallback<Integer> callback);

	void deleteEventById(int eventId, AsyncCallback<Integer> callback);

	void CheckUrl(String website, AsyncCallback<Boolean> callback);

	void getCategoryNameById(int categoryId, AsyncCallback<Category> callback);

	void AddUserByName(String nameOfUser, String password2, String typeUser,
			AsyncCallback<String> callback);

	void ModifyEvent(int eventId, int userId, String categoryId,
			String startEvent, String endEvent, String location,
			String description, String name, String website, String video,
			String phoneNumber, String email, AsyncCallback<String> callback);

	void DeleteUserByName(String nameOfUser, AsyncCallback<String> callback);

	void TakeAllNamesFromUsers(AsyncCallback<ArrayList<String>> callback);

	void GetTypesUser(AsyncCallback<ArrayList<TypeUser>> callback);

	
}