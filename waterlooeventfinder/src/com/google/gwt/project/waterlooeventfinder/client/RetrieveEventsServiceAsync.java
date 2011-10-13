package com.google.gwt.project.waterlooeventfinder.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RetrieveEventsServiceAsync {
	void getEvents(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
