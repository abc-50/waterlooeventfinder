/**
 * 
 */
package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Login page content
 * 
 * @author Mike
 * 
 */
public class LoginContent extends Content {

	/**
	 * Constructor
	 */
	public LoginContent() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		final PasswordTextBox passwordBox = new PasswordTextBox();
		final TextBox loginBox = new TextBox();

		// Add them to the root panel.
		DockPanel vpanel = new DockPanel();
//		vpanel.setStyleName("LoginStyle");
		InlineLabel signIn = new InlineLabel("Sign in");
		signIn.setStyleName("subHeading");
		
		
		// user name box
        FlowPanel nameAndLabel = new FlowPanel(); 
        InlineLabel label = new InlineLabel("User name"); 

        nameAndLabel.add(label); 
        nameAndLabel.add( new InlineHTML("<br/>") ); 
        nameAndLabel.add(loginBox); 
        
        // password box
        FlowPanel passwordAndLabel = new FlowPanel(); 
        InlineLabel label2 = new InlineLabel("Password"); 

        passwordAndLabel.add(label2); 
        passwordAndLabel.add( new InlineHTML("<br/>") ); 
        passwordAndLabel.add(passwordBox); 
        
        vpanel.add(signIn, DockPanel.NORTH);
		vpanel.add(nameAndLabel, DockPanel.NORTH);
		vpanel.add(passwordAndLabel, DockPanel.NORTH);

		Button loginButton = new Button("Sign in", new ClickHandler() {
			public void onClick(ClickEvent event) {
				String login = loginBox.getText();
				String password = passwordBox.getText();

				// check if login is valid
				connectToAccount(login, password);
			}
		});

		vpanel.setStyleName("loginPanel");
		vpanel.add(loginButton, DockPanel.CENTER);
		panel.add(vpanel);
	}

	/**
	 * Attempt to login with given username and password
	 * 
	 * @param login
	 * @param password
	 */
	private void connectToAccount(final String login, final String password) {

		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<String> callback = new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				//Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				if (result == null) {
					Window.alert("Login Unsuccessful");
				} else {

					// store sessionID in a cookie
					SessionID = result;
					

					// Window.alert("Session ID : " + SessionID);

					utils.setCookie("sid", SessionID);
					checkSessionIdWithSever(SessionID);
					
				}
			}
		};

		retrievalService.logToServer(login, password, callback);
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
				if (result.get(0) != 0) {
					// set user id to cookie too
					utils.setCookie("userId", result.get(0).toString());
					utils.setCookie("userType", result.get(1).toString());
					int userId = utils.getIntCookie("userId");
					int userType = utils.getIntCookie("userType");
					
					//Window.alert("LoginContent --> "+ Integer.toString(userType));
					if (userType == 1 || userType == 2) {
						ContentContainer.getInstance();
						ContentContainer.setHeader(new AdminHeader());
						ContentContainer.setContent(new EventsListContent());
					} else{
						ContentContainer.getInstance();
						ContentContainer.setHeader(new ClubHeader(userId));
						ContentContainer.getInstance();
						ContentContainer.setContent(new ClubEventsListContent(userId));

					}
				} else {
					// invalid or expired credentials
					utils.removeCookie("sid");
					utils.removeCookie("userId");
					utils.removeCookie("userType");
					ContentContainer.getInstance();
					ContentContainer.setHeader(new NormalUserHeader());
					ContentContainer.getInstance();
					ContentContainer.setContent(new EventsListContent());
				}

			}

		};

		retrievalService.loginUsingSession(SessionID, callback);
	}

}