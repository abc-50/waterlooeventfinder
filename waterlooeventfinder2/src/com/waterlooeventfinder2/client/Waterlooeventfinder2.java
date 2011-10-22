package com.waterlooeventfinder2.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.waterlooeventfinder2.client.AsyncDataProviderExample.MyDataProvider;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Waterlooeventfinder2 implements EntryPoint {

	public void onModuleLoad() {

		final Button CategoryAll = new Button("All");
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
