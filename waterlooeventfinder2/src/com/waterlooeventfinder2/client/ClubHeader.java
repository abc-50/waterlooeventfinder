package com.waterlooeventfinder2.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

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
		
//		int height = getHeightScreen();
//		double heightHeader = (double) height/3;
//		Window.alert(Double.toString(heightHeader));
//		
//		int heightContent = height/2;
//		int heightFooter = height/6;
//		
//		RootPanel.get("header").setHeight(Double.toString(heightHeader) + "px");
//		RootPanel.get("content").setHeight(Double.toString(heightContent) + "px");
//		RootPanel.get("footer").setHeight(Double.toString(heightFooter) + "px");
		
	}
	
//	private int getHeightScreen() {
//		Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit ();
//		Dimension screensize = toolkit.getScreenSize ();
//		
//		int height = screensize.height;
//		Window.alert(Integer.toString(height));
//		return height;
//	}

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
		panel.setCellHorizontalAlignment(myEvents, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	private void CreateLogoutButton() {
		Button logOutButton = new Button("Logout", new ClickHandler() {
			public void onClick(ClickEvent event) {
				logout();
			}
		});
		panel.add(logOutButton);
		panel.setCellHorizontalAlignment(logOutButton, HasHorizontalAlignment.ALIGN_RIGHT);
		
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
		panel.setCellHorizontalAlignment(addButton, HasHorizontalAlignment.ALIGN_CENTER);
		
	}
	
	
}
