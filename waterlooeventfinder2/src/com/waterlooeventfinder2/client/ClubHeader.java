package com.waterlooeventfinder2.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class ClubHeader extends Header{
	
	
	public ClubHeader(final int userId){
		CreateMainButton();
		MyEventsButton(userId);
		CreateAddButton(userId);
		CreateLogoutButton();
	}
	
	private void CreateMainButton() {
		Button main = new Button("Home", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new EventsListContent());

			}
		});
		
		panel.add(main);		
		
	}
	
	private void MyEventsButton(final int userId) {
		Button myEvents = new Button("My events", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				ContentContainer.setContent(new ClubEventsListContent(userId));
			}
		});
		
		panel.add(myEvents);
	}
	
	private void CreateLogoutButton() {
		Button logOutButton = new Button("Logout", new ClickHandler() {
			public void onClick(ClickEvent event) {
				disconnect();
			}
		});
		panel.add(logOutButton);
		
	}

	private void disconnect() {
//
//		if (retrievalService == null) {
//			retrievalService = GWT.create(EventRetrievalService.class);
//		}
//
//		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.getMessage());
//				// TODO: Do something with errors.
//			}
//
//			public void onSuccess(Integer result) {
//				Window.alert("Disconnected");
//				ContentContainer.getInstance();
//				ContentContainer.setHeader(new NormalUserHeader());
//				ContentContainer.setContent(new EventsListContent());
//				
//			}
//		};
//		retrievalService.logout(SessionI, callback);
	}
	
	private void CreateAddButton(final int userId){
		Button addButton = new Button("Add an event", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new ClubEventDetailContent(userId, 0, "addEvent"));

			}
		});
		
		panel.add(addButton);
		
	}
	
	
}
