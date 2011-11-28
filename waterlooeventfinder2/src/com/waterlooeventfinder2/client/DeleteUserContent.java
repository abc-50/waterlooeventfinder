package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;

public class DeleteUserContent extends Content {
	
	HorizontalPanel hpanel = new HorizontalPanel();
	
	MultiWordSuggestOracle userNameList = new MultiWordSuggestOracle();  
	
	SuggestBox userName = new SuggestBox(userNameList);
	   
	public DeleteUserContent() {
		
		addElementToSuggestBox(userNameList);
		createDeleteButton();
		

	}
	
	private void addElementToSuggestBox(MultiWordSuggestOracle userNameList2) {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<ArrayList<String>> callback = new AsyncCallback<ArrayList<String>> () {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
				int sizeOfList = result.size();
				for (int i = 0; i<sizeOfList; i++){
					userNameList.add(result.get(i));
					
				}
				
			}
		};

		retrievalService.TakeAllNamesFromUsers(callback);
		panel.add(userName);
	}

	private void createDeleteButton() {
		
		
		Button deleteButton = new Button("Delete this user",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						final String nameOfUser = userName.getText();					
						deleteUserByName(nameOfUser);
					}

					
				});

		hpanel.add(deleteButton);
		panel.add(hpanel);

	}
	
	private void deleteUserByName(final String nameOfUser) {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<String> callback = new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(String result) {
				Window.alert("The user " + nameOfUser + " and his events are now DELETED");
			}
		};

		retrievalService.DeleteUserByName(nameOfUser, callback);
		
	}
}
