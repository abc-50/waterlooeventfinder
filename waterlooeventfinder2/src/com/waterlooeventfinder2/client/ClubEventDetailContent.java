package com.waterlooeventfinder2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.waterlooeventfinder2.shared.Event;

public class ClubEventDetailContent extends Content {
	
	HorizontalPanel hpanel = new HorizontalPanel();
	
		public ClubEventDetailContent(final int userId, int eventId){
			if (retrievalService == null) {
				retrievalService = GWT.create(EventRetrievalService.class);
			}
			
			GetEvent(eventId);
			CreateDeleteButton(userId, eventId);
			CreateSaveButton(eventId);
		}
		
		private void GetEvent(int eventId) {
			if (retrievalService == null) {
				retrievalService = GWT.create(EventRetrievalService.class);
			}

			// Set up the callback object.
			AsyncCallback<Event> callback = new AsyncCallback<Event>() {

				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				public void onSuccess(Event result) {
					FlexTable table = CreateEventDetails(result);

					if (table != null) 
						panel.add(table);
					else
						panel.add(new Label("Invalid event ID."));
				}
			};

			retrievalService.GetEventById(eventId, callback);
		}
		
		private FlexTable CreateEventDetails(Event ev) {
			FlexTable ft = new FlexTable();

			int curRow = 0;
		
					ft.setText(curRow, 0, "Event Name: ");
					TextBox nameBox = new TextBox();
					nameBox.setText(ev.Name());
					nameBox.getText();
					ft.setWidget(curRow, 1, nameBox);
					curRow++;
				
				
					ft.setText(curRow, 0, "Event Category: ");
					TextBox categoryBox = new TextBox();
					categoryBox.setText(Integer.toString(ev.getCategoryId()));
					ft.setWidget(curRow, 1, categoryBox);
					curRow++;
				
				
					ft.setText(curRow, 0, "Start Time: ");
					TextBox startBox = new TextBox();
					startBox.setText(ev.getStarHour().toString());
					ft.setWidget(curRow, 1, startBox);
					curRow++;
				
				
					ft.setText(curRow, 0, "End time: ");
					TextBox endBox = new TextBox();
					endBox.setText(ev.getEndHour().toString());
					ft.setWidget(curRow, 1, endBox);
					curRow++;
				
				
					ft.setText(curRow, 0, "Location: ");
					TextBox locationBox = new TextBox();
					locationBox.setText(ev.Location());
					ft.setWidget(curRow, 1, locationBox);
					curRow++;
				
				
					ft.setText(curRow, 0, "Description: ");
					TextBox descriptionBox = new TextBox();
					descriptionBox.setText(ev.Description());
					ft.setWidget(curRow, 1, descriptionBox);
					curRow++;
				
				
					ft.setText(curRow, 0, "Website: ");
					TextBox websiteBox = new TextBox();
					websiteBox.setText(ev.Website());
					ft.setWidget(curRow, 1, websiteBox);
					curRow++;
					
					
					ft.setText(curRow, 0, "Promotional video: ");
					TextBox videoBox = new TextBox();
					videoBox.setText(ev.Video());
					ft.setWidget(curRow, 1, videoBox);
					curRow++;
				
					
					ft.setText(curRow, 0, "Phone: ");
					TextBox phoneNumberBox= new TextBox();
					phoneNumberBox.setText(ev.PhoneNumber());
					ft.setWidget(curRow, 1, phoneNumberBox);
					curRow++;
				
					
					ft.setText(curRow, 0, "Email: ");
					TextBox emailBox = new TextBox();
					emailBox.setText(ev.Email());
					ft.setWidget(curRow, 1, emailBox);
					curRow++;

			return ft;
		}
		
		
		private void CreateDeleteButton(final int userId, final int eventId) {
			Button deleteButton = new Button("Delete this event", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
						deleteEventById(userId, eventId);

				}
			});

			hpanel.add(deleteButton);
			panel.add(hpanel);

		}
		
		private void CreateSaveButton(final int eventId) {
			Button saveButton = new Button("Save modifications", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
						saveEventById(eventId);

				}
			});

			hpanel.add(saveButton);
			panel.add(hpanel);

		}
		
		private void deleteEventById(final int userId, int eventId){
			if (retrievalService == null) {
				retrievalService = GWT.create(EventRetrievalService.class);
			}

			// Set up the callback object.
			AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(Integer result) {
					Window.alert("Event deleted");
					ContentContainer.getInstance();
					ContentContainer.setContent(new ClubEventsListContent(userId));
				}

			};
			retrievalService.deleteEventById(eventId, callback);
		}
		
		private void saveEventById(int eventId){
			
		}
		
}
