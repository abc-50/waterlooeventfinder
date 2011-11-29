package com.waterlooeventfinder2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

// simplify creation and layout of a new page
public abstract class Content extends Composite {

	protected EventRetrievalServiceAsync retrievalService = GWT
			.create(EventRetrievalService.class);

	protected String SessionID;

	// add all elements to one panel
	protected VerticalPanel panel = new VerticalPanel();

	// constructor
	public Content() {
		SessionID = Cookies.getCookie("sid");

		if (SessionID != null) {
			checkSessionIdWithSever();
		}

	}

	/**
	 * Accessor to display panel
	 * @return a vertical panel filled with elements
	 */
	public VerticalPanel getPanel() {

		return panel;
	}

	/**
	 * Check the stored session Id with the server
	 * If invalid, then redirect user to the login page, delete invalid cookie
	 * If valid, do nothing/continue execution
	 */
	private void checkSessionIdWithSever() {

		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {
				if (result == 0) {
					Cookies.removeCookie("sid");
					ContentContainer.getInstance();
					ContentContainer.setContent(new LoginContent());
				}
			}
		};

		retrievalService.loginUsingSession(SessionID, callback);
	}
}