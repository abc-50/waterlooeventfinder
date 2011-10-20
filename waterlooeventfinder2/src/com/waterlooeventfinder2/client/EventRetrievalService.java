package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.waterlooeventfinder2.shared.Event;

public interface EventRetrievalService extends RemoteService {
	ArrayList<Event> GetAllEvents();
	ArrayList<Event> GetEventsByCategory(String category);
}
