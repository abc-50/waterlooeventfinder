package com.waterlooeventfinder2.client;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;

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
}
