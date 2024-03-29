package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Waterlooeventfinder2 extends Composite implements EntryPoint {

	/**
	 * Set initial elements to display
	 */
	private void loadMainPage() {

		RootPanel.get("header").clear();
		RootPanel.get("content").clear();
		RootPanel.get("footer").clear();

		String SessionID = utils.getStringCookie("sid");

		if (SessionID != null) {
			// cookie present, attempt to login
			checkSessionIdWithSever(SessionID);
			int userId = utils.getIntCookie("userId");
			int userType = utils.getIntCookie("userType");

			if (userType == 1 || userType == 2) {
				ContentContainer.getInstance();
				ContentContainer.setHeader(new AdminHeader());
				ContentContainer.setContent(new EventsListContent());
			} else {
				ContentContainer.getInstance();
				ContentContainer.setHeader(new ClubHeader(userId));
				ContentContainer.getInstance();
				ContentContainer.setContent(new ClubEventsListContent(userId));

			}

		} else {
			// not logged in
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
		AsyncCallback<ArrayList<Integer>> callback = new AsyncCallback<ArrayList<Integer>>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(ArrayList<Integer> result) {
				if (result.size() != 0) {
					if (result.get(0) != 0) {
						// set user id to cookie too
						utils.setCookie("userId", result.get(0).toString());
						utils.setCookie("userType", result.get(1).toString());
						
					}
				} else {
					// invalid or expired credentials
					utils.removeCookie("sid");
					utils.removeCookie("userId");
					utils.removeCookie("userType");
				}

			}

		};

		retrievalService.loginUsingSession(SessionID, callback);
	}
}