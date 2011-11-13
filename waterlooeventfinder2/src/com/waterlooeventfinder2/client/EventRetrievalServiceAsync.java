package com.waterlooeventfinder2.client;

import java.util.ArrayList;

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
}