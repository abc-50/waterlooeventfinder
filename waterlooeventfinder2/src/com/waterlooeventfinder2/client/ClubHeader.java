package com.waterlooeventfinder2.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class ClubHeader extends Header{
	private DockPanel dp = new DockPanel();
	/**
	 * Creates the header content for logged in users
	 * @param userId User Id of club member
	 */
	public ClubHeader(final int userId){
		CreateMainButton();
		MyEventsButton(userId);
		CreateAddButton(userId);
		CreateLogoutButton();
		
		dp.setStyleName("headerNav");
		panel.add(dp);
		
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
		
		dp.add(main, DockPanel.WEST);		
		
	}
	
	private void MyEventsButton(final int userId) {
		Button myEvents = new Button("My events", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				ContentContainer.setContent(new ClubEventsListContent(userId));
			}
		});
		
		dp.add(myEvents, DockPanel.WEST);
		dp.setCellHorizontalAlignment(myEvents, HasHorizontalAlignment.ALIGN_LEFT);
	}
	
	private void CreateLogoutButton() {
		Button logOutButton = new Button("Logout", new ClickHandler() {
			public void onClick(ClickEvent event) {
				logout();
			}
		});
		dp.add(logOutButton, DockPanel.CENTER);
		dp.setCellHorizontalAlignment(logOutButton, HasHorizontalAlignment.ALIGN_RIGHT);
		
	}

	
	private void CreateAddButton(final int userId){
		Button addButton = new Button("New Event", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new ClubEventDetailContent(userId, 0, "addEvent"));

			}
		});
		
		dp.add(addButton, DockPanel.WEST);
		dp.setCellHorizontalAlignment(addButton, HasHorizontalAlignment.ALIGN_LEFT);
		
	}
	
	
}
