/**
 * 
 */
package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;

/**
 * @author Mike
 *
 */
public class EventDetailContent extends Content {
	
	String category = "";
	int curRow = 0;
	FlexTable ft = new FlexTable();
	
	public EventDetailContent(int eventId) {

		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		GetEvent(eventId);	
	}

	/**
	 * Populate events list from database
	 */
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

	private FlexTable CreateEventDetails(final Event ev) {
				
		ft.setStyleName("NormalUserEntireTableDescription", true);

		if (ev != null) {
			if (ev.Name() != null) {
				ft.setText(curRow, 0, "Event Name: ");
				ft.setText(curRow, 1, ev.Name());
				curRow++;
			}
			
			
			
			if (ev.getCategoryId() != 0) {
				ft.setText(curRow, 0, "Event Category: ");
				ft.setText(curRow, 1, category);
				final int saveCurRow = curRow;
				
				if (retrievalService == null) {
					retrievalService = GWT.create(EventRetrievalService.class);
				}

				// Set up the callback object.
				AsyncCallback<ArrayList<Category>> callback = new AsyncCallback<ArrayList<Category>>() {

					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(ArrayList<Category> result) {
						for (Category categoryResults : result) {

							if (categoryResults.getCategoryId() == ev.getCategoryId() ){
								
								category = categoryResults.getCategoryName();
								ft.setText(saveCurRow, 1, category);
							}
						}
					}
				};

				retrievalService.GetAllCategory(callback);

				curRow++;
			}
			
			if (ev.getStarHour() != null) {
				ft.setText(curRow, 0, "Start Time: ");
				ft.setText(curRow, 1, ev.getStarHour().toString());
				curRow++;
			}
			
			if (ev.getEndHour() != null) {
				ft.setText(curRow, 0, "End time: ");
				ft.setText(curRow, 1, ev.getEndHour().toString());
				curRow++;
			}
			
			if (ev.Location() != null) {
				ft.setText(curRow, 0, "Location: ");
				ft.setText(curRow, 1, ev.Location());
				curRow++;
			}
			
			if (ev.Description() != null) {
				ft.setText(curRow, 0, "Description: ");
				ft.setText(curRow, 1, ev.Description());
				curRow++;
			}
			
			if (ev.Website() != null) {
				ft.setText(curRow, 0, "Website: ");
				ft.setWidget(curRow, 1, new Hyperlink(ev.Website(), "website"));
				curRow++;
			}
			
			if (ev.Video() != null) {
				ft.setText(curRow, 0, "Promotional video: ");
				ft.setWidget(curRow, 1, new Frame(ev.Video()));
				curRow++;
			}
			
			if (ev.PhoneNumber() != null) {
				ft.setText(curRow, 0, "Phone: ");
				ft.setText(curRow, 1, ev.PhoneNumber());
				curRow++;
			}
			
			if (ev.Email() != null) {
				ft.setText(curRow, 0, "Email: ");
				ft.setText(curRow, 1, ev.Email());
				curRow++;
			}
			
			for (int i=0; i<ft.getRowCount(); i++){
				ft.getFlexCellFormatter().setStyleName(i, 0, "FlexTableNormalUserStyle");
			}
			
			
		}

		return ft;
	}
}