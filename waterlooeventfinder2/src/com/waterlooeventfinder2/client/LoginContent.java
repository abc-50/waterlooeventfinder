/**
 * 
 */
package com.waterlooeventfinder2.client;


import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.waterlooeventfinder2.client.utils;
/**
 * Login page content
 * @author Mike
 *
 */
public class LoginContent extends Content {

	public LoginContent() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		final PasswordTextBox passwordBox = new PasswordTextBox();
		final TextBox loginBox = new TextBox();

		// Add them to the root panel.
		VerticalPanel vpanel = new VerticalPanel();
		vpanel.setStyleName("LoginStyle");
		vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpanel.add(loginBox);
		vpanel.add(passwordBox);
		

		Button loginButton = new Button("Login",
				new ClickHandler() {
			public void onClick(ClickEvent event) {
				String login = loginBox.getText();
				String password = passwordBox.getText();

				connectToAccount(login, password);
			}
		});

		Button logoutButton = new Button("Logout",
				new ClickHandler() {
			public void onClick(ClickEvent event) {
				logout();
			}
		});
		
		vpanel.add(loginButton);
		vpanel.add(logoutButton);
		panel.add(vpanel);
	}

	private void logout() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				// TODO: Do something with errors.
			}

			public void onSuccess(Integer result) {
				Window.alert("Disconnected");
			}
		};
		retrievalService.logout(SessionID,callback);
		
		Cookies.removeCookie("sid");	// remove the cookie that stored the session ID
	}

	private void connectToAccount(final String login, final String password) {

		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<String> callback = new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				if (result == null) {
					Window.alert("Login Unsuccessful");
				} else {
					Window.alert("Login Successful");
					
					// store sessionID in a cookie
					SessionID = result;
					
					//Window.alert("Session ID : " + SessionID);
				    utils.setCookie("sid", SessionID);
				    
				    int userId = 3;
					
					ContentContainer.setHeader(new ClubHeader(userId));
					ContentContainer.setContent(new ClubEventsListContent(userId));
				    
//				    ContentContainer.setHeader(new AdminHeader());
									    
				}

			}


		};

		retrievalService.logToServer(login, password, callback);
	}



}