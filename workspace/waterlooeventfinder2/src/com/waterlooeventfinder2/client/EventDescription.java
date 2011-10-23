package com.waterlooeventfinder2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.waterlooeventfinder2.client.Waterlooeventfinder2;
import com.waterlooeventfinder2.client.EventRetrievalService;


public class EventDescription {	
	
	/*
	 * Entry point method
	 */
	public EventDescription(int eventID){
		
		final VerticalPanel mainPanel = new VerticalPanel();
		final MenuBar menu = new MenuBar();
		final TextArea eventText = new TextArea();
		final Label lastUpdatedLabel = new Label();
		
		
		// Create menu
		
		// Make commands that we will execute from menu.
		Command cmdMain = new Command(){
			public void execute(){
				//Waterlooeventfinder2.onModuleLoad();
			}
		};
		Command cmdAddEvent = new Command(){
			public void execute(){
				Window.alert("Selected Add Event from the menu!");
			}
		};
		Command cmdMyEvents = new Command(){
			public void execute(){
				Window.alert("Selected My Events from the menu!");
			}
		};
		// Add Menu events.
		menu.addItem("Main", cmdMain);
		menu.addItem("Add Event", cmdAddEvent);
		menu.addItem("My Events", cmdMyEvents);
		
		
		// TODO Get description
		
		
		// Assemble Main Panel.
		mainPanel.add(menu);
		mainPanel.add(eventText);
		mainPanel.add(lastUpdatedLabel);
		
		// Associate Main Panel with host page.
		RootPanel.get("eventDescription").add(mainPanel);
		
		// Move cursor focus to menu.
		menu.setFocusOnHoverEnabled(true);
	}
	
}
