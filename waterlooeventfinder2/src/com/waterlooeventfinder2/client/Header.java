package com.waterlooeventfinder2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class Header extends Composite {
	protected EventRetrievalServiceAsync retrievalService = GWT
			.create(EventRetrievalService.class);

	// add all elements to one panel
	protected HorizontalPanel panel = new HorizontalPanel();

	// constructor
	public Header() {

	}

	public HorizontalPanel getPanel() {

		return panel;
	}
	
	protected void logout() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(Integer result) {
				//Window.alert("Disconnected");
				ContentContainer.setHeader(new NormalUserHeader());
				ContentContainer.setContent(new EventsListContent());
			}
		};
		
		retrievalService.logout(utils.getStringCookie("sid"),callback);
		
		Cookies.removeCookie("sid");	// remove the session ID cookie
	}
}
