package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.waterlooeventfinder2.shared.Event;

public interface EventRetrievalService extends RemoteService {
	ArrayList<Event> GetAllEvents() throws Exception;
	ArrayList<Event> GetEventsByFilter(String categoryFilter, String timeFilter, int startEventNumber, int endEventNumber);
	static Event GetEventById(int eventId);
}
