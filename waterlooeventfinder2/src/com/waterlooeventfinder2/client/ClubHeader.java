package com.waterlooeventfinder2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

public class ClubHeader extends Header{
	
	/**
	 * Creates the header content for logged in users
	 * @param userId User Id of club member
	 */
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
				logout();
			}
		});
		panel.add(logOutButton);
		
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
