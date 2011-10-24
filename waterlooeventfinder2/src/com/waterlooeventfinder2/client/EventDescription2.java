package com.waterlooeventfinder2.client;

import java.lang.Integer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.waterlooeventfinder2.client.EventRetrievalService;
import com.waterlooeventfinder2.client.Waterlooeventfinder2;
import com.waterlooeventfinder2.shared.Event;


public class EventDescription2 implements EntryPoint {
	
	public int eventID;
	
	private EventRetrievalServiceAsync retrievalService = GWT
			.create(EventRetrievalService.class);
	
	private void retrieveEvent() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}
		retrievalService.GetEventById(eventID,null);
	}
	
	public EventDescription2(){}
	
	public EventDescription2(int ID){
		eventID = ID;
	}
	
	
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
		getval();
		
		ChooseMain.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        callMain();
		      }
		});
		ChooseAllEvents.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        Window.alert(""+eventID);
		      }
		});
	}
	
	private void callMain(){
		String baseurl = GWT.getHostPageBaseURL();
		Window.Location.assign(baseurl+"Waterlooeventfinder2.html");
	}
	
	public void getval(){
		String url = Window.Location.getHref();
		String search = "?";
		String result = "";
		int i;
		i = url.indexOf(search);
		result = url.substring(i+1);
		eventID = Integer.parseInt(result);
				
	}
	
}


