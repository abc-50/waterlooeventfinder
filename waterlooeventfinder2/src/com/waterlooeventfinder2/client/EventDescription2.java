package com.waterlooeventfinder2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.waterlooeventfinder2.client.EventRetrievalService;
import com.waterlooeventfinder2.shared.Event;

public class EventDescription2 implements EntryPoint {
	public void onModuleLoad() {
		
		final Button ChooseMain = new Button("Main");
		final Button ChooseAllEvents = new Button("All Events");
		final Button ChooseMyEvents = new Button("My Events");
		final TextArea eventText = new TextArea();
		final Label lastUpdatedLabel = new Label();
		
		ChooseMain.addStyleName("menuBar");
		ChooseAllEvents.addStyleName("menuBar");
		ChooseMyEvents.addStyleName("menuBar");
		
		// Associate Main Panel with host page.
		RootPanel.get("menubar").add(ChooseMain);
		RootPanel.get("menuBar").add(ChooseAllEvents);
		RootPanel.get("menuBar").add(ChooseMyEvents);
		RootPanel.get("textArea").add(eventText);
		RootPanel.get("label").add(lastUpdatedLabel);
		
		ChooseMain.setEnabled(true);
		ChooseMain.setFocus(true);
		ChooseAllEvents.setEnabled(true);
		ChooseMyEvents.setEnabled(true);
		
		ChooseMain.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        callMain();
		      }
		});
		
	}
	
	private void callMain(){
		// TODO Call Waterlooeventfinder2.html
	}
	
	public void showDescription(int eventID){
		Event temp = new Event();
		temp = EventRetrievalService.GetEventById(eventID);
		// TODO print all attributes of temp.
	}
	
}


