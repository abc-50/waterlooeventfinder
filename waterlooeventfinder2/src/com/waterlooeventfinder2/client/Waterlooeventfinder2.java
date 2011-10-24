package com.waterlooeventfinder2.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.waterlooeventfinder2.shared.Event;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.waterlooeventfinder2.client.EventDescription2;
import com.google.gwt.user.client.ui.Button;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Waterlooeventfinder2 extends Composite implements EntryPoint {


	ButtonPressed infoButtonPressed = new ButtonPressed();
	SimplePager pager = new SimplePager();
	
	private EventRetrievalServiceAsync retrievalService = GWT
			.create(EventRetrievalService.class);

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
					//String lol = Integer.toString(start);
					updateRowData(start, result);
					updateRowCount(result.size(), true);
					
				}
			};
			
			retrievalService.GetAllEvents(callback);	
		}
		
	};
	
	// Associate an async data provider to the table
	// XXX: Use AsyncCallback in the method onRangeChanged
	// to actaully get the data from the server side
	
	protected void selectEvents(final String category, final String time){
		
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
				result.add(new Event());
				//Inform the displays of the new data.
				provider.updateRowData(0, result);
				// Inform the displays of the total number of items that are available.
				provider.updateRowCount(result.size(), true);
				pager.firstPage();
			}
		};
		
		retrievalService.GetAllEvents(callback);	
	}
	
	//Call this function to load description page
	public void viewEvent(int ID){
		Window.Location.assign(GWT.getHostPageBaseURL()+"EventDescription2.html?"+ID );
	}

	public void onModuleLoad() {

		Button CategoryAll = new Button("All");
		CategoryAll.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("All");
				selectEvents(infoButtonPressed.getCategory(), infoButtonPressed.getTime());
			}
		});
		
		Button CategoryFootball = new Button("Football");
		CategoryFootball.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("Football");
				selectEvents(infoButtonPressed.getCategory(), infoButtonPressed.getTime());
			}
		});
		
		Button CategoryDance = new Button("Dance");
		CategoryDance.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("Dance");
				selectEvents(infoButtonPressed.getCategory(), infoButtonPressed.getTime());
			}
		});
		
		Button CategoryConcert = new Button("Concert");
		CategoryConcert.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("Concert");
				selectEvents(infoButtonPressed.getCategory(), infoButtonPressed.getTime());
			}
		});
		
		Button CategoryBars = new Button("Bars");
		CategoryBars.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("Bars");
				selectEvents(infoButtonPressed.getCategory(), infoButtonPressed.getTime());
			}
		});
		
		Button TimeUpcoming = new Button("Upcoming");
		TimeUpcoming.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setTime("Upcoming");
				selectEvents(infoButtonPressed.getCategory(), infoButtonPressed.getTime());
			}
		});
		
		Button TimeOneDay = new Button("One day");
		TimeOneDay.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setTime("One day");
				selectEvents(infoButtonPressed.getCategory(), infoButtonPressed.getTime());
			}
		});
		
		Button TimeOneWeek = new Button("One week");
		TimeOneWeek.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setTime("One week");
				selectEvents(infoButtonPressed.getCategory(), infoButtonPressed.getTime());
			}
		});
		
		Button TimeOneMonth = new Button("One month");
		TimeOneMonth.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setTime("One month");
				selectEvents(infoButtonPressed.getCategory(), infoButtonPressed.getTime());
			}
		});
<<<<<<< HEAD
=======
		final Button CategoryFootball = new Button("Football");
		CategoryFootball.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				viewEvent(7);
			}
		});
		final Button CategoryDance = new Button("Dance");
		final Button CategoryConcert = new Button("Concert");
		final Button CategoryBars = new Button("Bars");

		final Button TimeUpcoming = new Button("Upcoming");
		final Button TimeOneDay = new Button("One day");
		final Button TimeOneWeek = new Button("One week");
		final Button TimeOneMonth = new Button("One month");

>>>>>>> 2e42e9ac56613116d14792e93b4166051dd897c5
		// We can add style names to widgets

		CategoryAll.addStyleName("gwt-Button");
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
		// Display 2 rows in one page
		table.setPageSize(2);

		// Add a text column to show the name.
		TextColumn<Event> nameColumn = new TextColumn<Event>() {
			@Override
			public String getValue(Event object) {
				return object.Name();
			}
		};		
		
		// Add a date column to show the Start time
	    DateCell dateCell = new DateCell();
	    Column<Event, Date> startColumn = new Column<Event, Date>(dateCell) {
	      @Override
	      public Date getValue(Event object) {
	        return object.getStarHour();
	      }
	    };	

	 // Add a date column to show the Start time
	    Column<Event, Date> endColumn = new Column<Event, Date>(dateCell) {
	      @Override
	      public Date getValue(Event object) {
	        return object.getEndHour();
	      }
	    };
	    
	    // Add columns to the table
		table.addColumn(nameColumn, "Name");
	    table.addColumn(startColumn, "Start");
	    table.addColumn(endColumn, "End");
	    
	    table.setWidth("100%", true);
	    table.setColumnWidth(nameColumn, 50.0, Unit.PCT);
	    table.setColumnWidth(startColumn, 15.0, Unit.PCT);
	    table.setColumnWidth(endColumn, 15.0, Unit.PCT);
	   
	    final SingleSelectionModel<Event> selectionModel = new SingleSelectionModel<Event>();
	    table.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        Event selected = selectionModel.getSelectedObject();
	        if (selected != null) {
	          Window.alert("You selected: " + selected.Name());
	        }
	      }
	    });

		provider.addDataDisplay(table);

		pager.setDisplay(table);

		//VerticalPanel vp = new VerticalPanel();
		//vp.add(table);
		
		RootPanel.get("listOfEvents").add(table);
		RootPanel.get("elementPager").add(pager);
		pager.addStyleName("elementPager");
	}	

}
