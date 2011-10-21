package com.waterlooeventfinder2.client;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Waterlooeventfinder2 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		final Button CategoryAll = new Button("All");
		final Button CategoryFootball = new Button("Football");
		final Button CategoryDance = new Button("Dance");
		final Button CategoryConcert = new Button("Concert");
		
		final Button TimeUpcoming = new Button("Upcoming");
		final Button TimeOneDay = new Button("One day");
		final Button TimeOneWeek = new Button("One week");
		final Button TimeOneMonth = new Button("One month");
		
		// We can add style names to widgets

		CategoryAll.addStyleName("categoryButton");
		CategoryFootball.addStyleName("categoryButton");
		CategoryDance.addStyleName("categoryButton");
		CategoryConcert.addStyleName("categoryButton");

		TimeUpcoming.addStyleName("timeButton");
		TimeOneDay.addStyleName("timeButton");
		TimeOneWeek.addStyleName("timeButton");
		TimeOneMonth.addStyleName("timeButton");
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
				
		RootPanel.get("categoryButton").add(CategoryAll);
		RootPanel.get("categoryButton").add(CategoryFootball);
		RootPanel.get("categoryButton").add(CategoryDance);
		RootPanel.get("categoryButton").add(CategoryConcert);

		RootPanel.get("timeButton").add(TimeUpcoming);
		RootPanel.get("timeButton").add(TimeOneDay);
		RootPanel.get("timeButton").add(TimeOneWeek);
		RootPanel.get("timeButton").add(TimeOneMonth);
		
		// Focus the cursor on the name field when the app loads
		
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		
		// TO ADD A CSS STYLE dialogVPanel.addStyleName("dialogVPanel");
		
		CategoryAll.setEnabled(true);
		CategoryAll.setFocus(true);
		CategoryFootball.setEnabled(true);
		CategoryDance.setEnabled(true);
		CategoryConcert.setEnabled(true);
		
		
		// Create a handler for the sendButton and nameField
		/*class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}*/

		// Add a handler to send the name to the server
		/*MyHandler handler = new MyHandler();
		
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		*/
	}
}
