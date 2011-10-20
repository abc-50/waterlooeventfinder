package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.waterlooeventfinder2.shared.Event;

public interface EventRetrievalServiceAsync {
	void GetAllEvents(AsyncCallback<ArrayList<Event>> callback)
			throws IllegalArgumentException;
	
	void GetEventsByCategory(String category, AsyncCallback<ArrayList<Event>> callback)
			throws IllegalArgumentException;
	
}