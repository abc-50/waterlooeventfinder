package com.google.gwt.project.waterlooeventfinder.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

public interface RetrieveEventsService extends RemoteService {
	String getEvents(String input) throws IllegalArgumentException;
}
