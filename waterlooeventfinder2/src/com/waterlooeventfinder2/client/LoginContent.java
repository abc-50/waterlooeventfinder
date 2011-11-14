/**
 * 
 */
package com.waterlooeventfinder2.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.waterlooeventfinder2.shared.User;

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
		
		//FormPanel form = new FormPanel();

		final PasswordTextBox passwordBox = new PasswordTextBox();
		final TextBox loginBox = new TextBox();

		// Add them to the root panel.
		VerticalPanel vpanel = new VerticalPanel();
		vpanel.add(loginBox);
		vpanel.add(passwordBox);

		Button buttonConnection = new Button("Connect to your account",
				new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Connection starts");
				String login = loginBox.getText();
				String password = passwordBox.getText();

				connectToAccount(login, password);
			}
		});

		vpanel.add(buttonConnection);
		panel.add(vpanel);

		//		// set action 
		//		//https://google-web-toolkit.googlecode.com/svn/javadoc/1.6/com/google/gwt/user/client/ui/FormPanel.html

	}

	protected void disconnect() {
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
		retrievalService.logout(callback);
	}

	// Associate an async data provider to the table
	// XXX: Use AsyncCallback in the method onRangeChanged
	// to actually get the data from the server side

	protected void connectToAccount(final String login, final String password) {

		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<User> callback = new AsyncCallback<User>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(User result) {
				// Window.alert(Integer.toString(result));
				if (result.isLoggedInApplication() == false) {
					Window.alert("Login Unsuccessful");
				} else {
					String name = result.getDisplayName();
					Button LogOutButton = new Button(name + ": Logout",
							new ClickHandler() {
						public void onClick(ClickEvent event) {
							disconnect();

						}
					});
					RootPanel.get().add(LogOutButton);
				}

			}

		};

		retrievalService.logToServer(login, password, callback);
	}

	protected void loginUsingSession(final String login, final String password) {

		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<User> callback = new AsyncCallback<User>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				// TODO: Do something with errors.
			}

			@Override
			public void onSuccess(User result) {
				// TODO Auto-generated method stub
				// Window.alert(Integer.toString(result));
				if (result.isLoggedInApplication() == false) {
					Window.alert("Login Using Session Unsuccessful");
					// TODO: Redirect to the login page
				} else {
					Window.alert("Login using Session successful");
					// TODO: Display the elements as the user is logged in
				}

			}

		};

		retrievalService.loginUsingSession(callback);
	}

}
