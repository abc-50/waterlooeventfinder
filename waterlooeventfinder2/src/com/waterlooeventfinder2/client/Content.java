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
		
		if (SessionID != null ) {
			checkSessionIdWithSever();
		} else {
			// display login page
		}
//	    if ( sessionID != null ) checkWithServerIfSessionIdIsStillLegal();
//	    else displayLoginBox();
	}

	public VerticalPanel getPanel() {
		
		return panel;
	}
	
	
	private void checkSessionIdWithSever() {

		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<String> callback = new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				// TODO: Do something with errors.
			}

			@Override
			public void onSuccess(String result) {
				if (result == null) {
					Window.alert("Login Using Session Unsuccessful: " + result);
				} else {
					Window.alert("Login using Session successful: "+ result);
				}

			}

		};

		retrievalService.loginUsingSession(SessionID, callback);
	}
	// save current user here
}