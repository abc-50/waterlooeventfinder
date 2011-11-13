package com.waterlooeventfinder2.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.waterlooeventfinder2.shared.Event;

public class MyTable extends CellTable<Event> {

	private CellTable<Event> table = new CellTable<Event>();
	
	public MyTable(){
		
	}
	
	public CellTable<Event> createTable(){
		this.table.setPageSize(3);

		// Add a text column to show the name.
		TextColumn<Event> nameColumn = new TextColumn<Event>() {
			@Override
			public String getValue(Event object) {
				return object.Name();
			}
		};

		// Add a column to show the Start time
		TextColumn<Event> startColumn = new TextColumn<Event>() {
			@Override
			public String getValue(Event object) {
				String startDateTime = null;
				/*if (infoButtonPressed.getTime().equals("Upcoming")) {
					startDateTime = DateTimeFormat.getFormat(
							PredefinedFormat.TIME_SHORT).format(
							object.getStarHour());
				} else {
					startDateTime = DateTimeFormat.getFormat(
							PredefinedFormat.DATE_TIME_SHORT).format(
							object.getStarHour());
				}*/
				return startDateTime;
				
			}
		};

		// Add a column to show end time
		TextColumn<Event> endColumn = new TextColumn<Event>() {
			@Override
			public String getValue(Event object) {
				String endDateTime = null;
				/*if (infoButtonPressed.getTime().equals("Upcoming")) {
					endDateTime = DateTimeFormat.getFormat(
							PredefinedFormat.TIME_SHORT).format(
							object.getEndHour());
				} else {
					endDateTime = DateTimeFormat.getFormat(
							PredefinedFormat.DATE_TIME_SHORT).format(
							object.getEndHour());
				}
				*/
				return endDateTime;
				
			}
		};

		// Add columns to the table
		this.table.addColumn(nameColumn, "Name");
		this.table.addColumn(startColumn, "Start");
		this.table.addColumn(endColumn, "End");

		this.table.addColumnStyleName(0, "nameColumStyle");
		this.table.addColumnStyleName(1, "StartColumStyle");
		this.table.addColumnStyleName(2, "EndColumnStyle");

		this.table.setWidth("100%", true);
		this.table.setColumnWidth(nameColumn, 40.0, Unit.PCT);
		this.table.setColumnWidth(startColumn, 20.0, Unit.PCT);
		this.table.setColumnWidth(endColumn, 20.0, Unit.PCT);
		this.table.setColumnWidth(endColumn, 20.0, Unit.PCT);
		
		return this.table;
	}
	
}
