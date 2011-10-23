package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
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
	        // TODO: Do something with errors.
	      }

	      public void onSuccess(ArrayList<Event> result) {
	    	  // FOR MARTIN:  result object for list of events
	        //result.add(new Event());
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

		Grid gridListEvents = new Grid(10, 3);

		// Put some values in the grid cells.

		gridListEvents.setText(0, 0, "Event");
		gridListEvents.setText(0, 1, "Start");
		gridListEvents.setText(0, 2, "End");

		for (int row = 1; row < 10; ++row) {
			for (int col = 0; col < 3; ++col) {
				gridListEvents.setText(row, col, "" + row + ", " + col);
			}
		}

		gridListEvents.getCellFormatter().setWidth(0, 0, "150px");
		gridListEvents.getCellFormatter().setWidth(0, 1, "50px");
		gridListEvents.getCellFormatter().setWidth(0, 2, "50px");

		gridListEvents.getRowFormatter().setStyleName(0, "NamesGroupList");
		gridListEvents.addStyleName("GridListEvents");

		RootPanel.get("listOfEvents").add(gridListEvents);
		
		// Create a CellList.
		CellList<String> cellList = new CellList<String>(new TextCell());
		
		// Create a data provider.
		MyDataProvider dataProvider = new MyDataProvider();
		
		// Add the cellList to the dataProvider.
		dataProvider.addDataDisplay(cellList);
		
		
		//Style for the list
		cellList.setStyleName("NamesEventList");
		// Create paging controls.
		SimplePager pager = new SimplePager();
		pager.setDisplay(cellList);

		// Add the widgets to the root panel.
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(cellList);
		vPanel.add(pager);
		RootPanel.get("listOfEvents").add(vPanel);
	}
	// ///////////////////////////////////////////////////////////////////////////////////

}
