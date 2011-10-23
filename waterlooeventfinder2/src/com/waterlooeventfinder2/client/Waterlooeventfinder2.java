package com.waterlooeventfinder2.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.waterlooeventfinder2.client.AsyncDataProviderExample.MyDataProvider;
import com.waterlooeventfinder2.shared.Event;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Waterlooeventfinder2 implements EntryPoint {

	private EventRetrievalServiceAsync retrievalService = GWT
			.create(EventRetrievalService.class);

	// sample events retrieval call
	private void retrieveAllEvents() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<ArrayList<Event>> callback = new AsyncCallback<ArrayList<Event>>() {
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				// TODO: Do something with errors.
			}

			public void onSuccess(ArrayList<Event> result) {
				// FOR MARTIN: result object for list of events
				// result.add(new Event());
				Window.alert("success");
				// updateRowData(start, result);
			}
		};

		retrievalService.GetAllEvents(callback);
	}

	public void onModuleLoad() {

		final Button CategoryAll = new Button("All");
		CategoryAll.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				retrieveAllEvents();
			}
		});
		final Button CategoryFootball = new Button("Football");
		final Button CategoryDance = new Button("Dance");
		final Button CategoryConcert = new Button("Concert");
		final Button CategoryBars = new Button("Bars");

		final Button TimeUpcoming = new Button("Upcoming");
		final Button TimeOneDay = new Button("One day");
		final Button TimeOneWeek = new Button("One week");
		final Button TimeOneMonth = new Button("One month");

		// We can add style names to widgets

		CategoryAll.addStyleName("categoryButton");
		CategoryFootball.addStyleName("categoryButton");
		CategoryDance.addStyleName("categoryButton");
		CategoryConcert.addStyleName("categoryButton");
		CategoryBars.addStyleName("categoryButton");

		TimeUpcoming.addStyleName("timeButton");
		TimeOneDay.addStyleName("timeButton");
		TimeOneWeek.addStyleName("timeButton");
		TimeOneMonth.addStyleName("timeButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element

		RootPanel.get("categoryButton").add(CategoryAll);
		RootPanel.get("categoryButton").add(CategoryFootball);
		RootPanel.get("categoryButton").add(CategoryDance);
		RootPanel.get("categoryButton").add(CategoryConcert);
		RootPanel.get("categoryButton").add(CategoryBars);

		RootPanel.get("timeButton").add(TimeUpcoming);
		RootPanel.get("timeButton").add(TimeOneDay);
		RootPanel.get("timeButton").add(TimeOneWeek);
		RootPanel.get("timeButton").add(TimeOneMonth);

		// Focus the cursor on the name field when the app loads

		CategoryAll.setEnabled(true);
		CategoryAll.setFocus(true);
		CategoryFootball.setEnabled(true);
		CategoryDance.setEnabled(true);
		CategoryConcert.setEnabled(true);

		// List of Events
		// Create a CellTable.
		final CellTable<Event> table = new CellTable<Event>();
		// Display 3 rows in one page
		table.setPageSize(10);

		// Add a text column to show the name.
		TextColumn<Event> nameColumn = new TextColumn<Event>() {
			@Override
			public String getValue(Event object) {
				return object.Name();
			}
		};
		
		table.addColumn(nameColumn, "Name");

		// Associate an async data provider to the table
		// XXX: Use AsyncCallback in the method onRangeChanged
		// to actaully get the data from the server side
		AsyncDataProvider<Event> provider = new AsyncDataProvider<Event>() {
			@Override
			protected void onRangeChanged(final HasData<Event> display) {

				if (retrievalService == null) {
					retrievalService = GWT.create(EventRetrievalService.class);
				}
				
				final int start = display.getVisibleRange().getStart();
				final int end = start + display.getVisibleRange().getLength();

				// Set up the callback object.
				AsyncCallback<ArrayList<Event>> callback = new AsyncCallback<ArrayList<Event>>() {

					
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
						// TODO: Do something with errors.
					}

					public void onSuccess(ArrayList<Event> result) {
						// FOR MARTIN: result object for list of events
						result.add(new Event());
						String lol = Integer.toString(start);
						updateRowData(start, result);
						Window.alert(Integer.toString(result.size()));
						updateRowCount(result.size(), true);
					}
				};
				
				retrievalService.GetAllEvents(callback);
				
				
			}
		};

		provider.addDataDisplay(table);

		SimplePager pager = new SimplePager();
		pager.setDisplay(table);

		VerticalPanel vp = new VerticalPanel();
		vp.add(table);
		vp.add(pager);

		// Add it to the root panel.
		RootPanel.get().add(vp);
	}

	// ///////////////////////////////////////////////////////////////////////////////////

}
