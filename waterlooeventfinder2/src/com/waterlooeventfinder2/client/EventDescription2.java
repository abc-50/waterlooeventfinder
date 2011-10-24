package com.waterlooeventfinder2.client;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
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
import com.waterlooeventfinder2.shared.Event;


public class EventDescription2 implements EntryPoint {
	
	private int eventID;
	protected Event eventObj;
	
	private EventRetrievalServiceAsync retrievalService = GWT.create(EventRetrievalService.class);

	
	private void retrieveEvent() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<Event> callback = new AsyncCallback<Event>() {
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(Event result) {
				eventObj = result;
			}
		};
		retrievalService.GetEventById(eventID,callback);		
	}
	
	
	public void onModuleLoad() {
		
		final Button ChooseMain = new Button("Main");
		final Button ChooseAllEvents = new Button("All Events");
		final Button ChooseMyEvents = new Button("My Events");
		final Label lastUpdatedLabel = new Label();
		
		ChooseMain.addStyleName("menuBar");
		ChooseAllEvents.addStyleName("menuBar");
		ChooseMyEvents.addStyleName("menuBar");
		
		// Associate Main Panel with host page.
		RootPanel.get("menubar").add(ChooseMain);
		RootPanel.get("menuBar").add(ChooseAllEvents);
		RootPanel.get("menuBar").add(ChooseMyEvents);
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
		
		getval();
		//retrieveEvent();
		//HTML html = new HTML(" "
			//	+ eventObj.Name()
			//	+ " "
			//	+ "Location: " + eventObj.Location()
			//	+ " "
			//	+ eventObj.Description()
			//	+ " "
			//	+ "Video: " + eventObj.Video()
			//	+ "Event Website: " + eventObj.Website()
			//	+ " "
			//	+ "Contact:"
			//	+ "Phone: " + eventObj.PhoneNumber()
			//	+ "E-mail: " + eventObj.Email(), true);

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		if (eventID == 1) {
			HTML html = new HTML(" "
						+ "EVENT NAME HERE"
						+ " "
						+ "Location: SLC GREAT HALL"
						+ " "
						+ " This is where the description of the event will go. For tomorrow, I hope we make some fake data that looks realistic. For now, lets just work with this."
						+ " "
						+ "Video: youtube.com/dfgsf"
						+ "Event Website: blahblahblah"
						+ " "
						+ "Contact:"
						+ "Phone: 5197297731"
						+ "E-mail: dsghfdhgdf@yahoo.com", true);
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


