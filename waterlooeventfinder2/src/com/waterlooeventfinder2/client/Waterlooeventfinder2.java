package com.waterlooeventfinder2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.Composite;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Waterlooeventfinder2 extends Composite implements EntryPoint {

	private void loadMainPage() {
		
		RootPanel.get("header").clear();
		RootPanel.get("footer").clear();

		String SessionID = utils.getStringCookie("sid");
		
		if (SessionID != null ) {
			checkSessionIdWithSever(SessionID);
			int userId = utils.getIntCookie("userId");


			ContentContainer.getInstance();
			ContentContainer.setHeader(new ClubHeader(userId));

			ContentContainer.getInstance();
			ContentContainer.setContent(new ClubEventsListContent(userId));
		}  else {
			ContentContainer.getInstance();
			ContentContainer.setHeader(new NormalUserHeader());

			ContentContainer.getInstance();
			ContentContainer.setContent(new EventsListContent());
		}
		
		ContentContainer.getInstance();
		ContentContainer.setFooter(new ElementFooter());

		
	}

	@Override
	public void onModuleLoad() {
		loadMainPage();
	}

	private void checkSessionIdWithSever(String SessionID) {
		EventRetrievalServiceAsync retrievalService = GWT
				.create(EventRetrievalService.class);

		// Set up the callback object.
		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {
				if (result != 0) {
					//Window.alert("Login using Session successful: " + result);
					utils.setCookie("userId", result.toString());

					// TODO: set the user id here
				} else {
					//Window.alert("Login Using Session Unsuccessful: " + result);
				}
			}

		};

		retrievalService.loginUsingSession(SessionID, callback);
	}
}