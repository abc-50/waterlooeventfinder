package com.waterlooeventfinder2.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sun.net.www.protocol.file.Handler;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.Time;

public class EventsListContent extends Content {

	private int selectedCategory;
	private int selectedTime;

	public EventsListContent() {
		super();
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		final ListBox categoryBox = new ListBox();
		final ListBox timeBox = new ListBox();
		CellTable<Event> table = new CellTable<Event>();
		final ListDataProvider<Event> ldp = new ListDataProvider<Event>();

		// category
		GetAllCategories(categoryBox);
		categoryBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				selectedCategory = categoryBox.getSelectedIndex();

				GetEventsByFilter(ldp, categoryBox.getSelectedIndex(),
						timeBox.getSelectedIndex());
				// Window.alert("selected category: " + selectedCategory);
			}
		});

		panel.add(categoryBox);

		// time
		GetAllTimes(timeBox);
		timeBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				selectedTime = timeBox.getSelectedIndex();

				GetEventsByFilter(ldp, categoryBox.getSelectedIndex(),
						timeBox.getSelectedIndex());
				// Window.alert("selectedTime: " + selectedTime);
			}
		});

		panel.add(timeBox);

		// events
		SetupCellTable(ldp, table);

		GetAllEvents(ldp);

		// To set the size of the table

		SimplePager pager = new SimplePager();
		pager.setDisplay(table);
		pager.firstPage();
		panel.add(table);
		panel.add(pager);

	}

	private void GetAllTimes(final ListBox timeBox) {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<ArrayList<Time>> callback = new AsyncCallback<ArrayList<Time>>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(ArrayList<Time> results) {
				for (Iterator<Time> i = results.iterator(); i.hasNext();) {
					timeBox.addItem(i.next().getTimeDisplayValue());
				}
			}
		};

		retrievalService.GetAllTime(callback);

	}

	private void GetAllCategories(final ListBox categoryBox) {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<ArrayList<Category>> callback = new AsyncCallback<ArrayList<Category>>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(ArrayList<Category> results) {
				for (Iterator<Category> i = results.iterator(); i.hasNext();) {
					categoryBox.addItem(i.next().getCategoryName());
				}
			}
		};

		retrievalService.GetAllCategory(callback);
	}

	/**
	 * Populate events list from database
	 * 
	 * @param table
	 */
	private void GetAllEvents(final ListDataProvider<Event> ldp) {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<ArrayList<Event>> callback = new AsyncCallback<ArrayList<Event>>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(ArrayList<Event> results) {
				populateCellTable(ldp, results);
			}
		};

		retrievalService.GetAllEvents(callback);
	}

	private void GetEventsByFilter(final ListDataProvider<Event> ldp,
			int catFilter, int timeFilter) {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<ArrayList<Event>> callback = new AsyncCallback<ArrayList<Event>>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(ArrayList<Event> results) {
				populateCellTable(ldp, results);
			}
		};

		retrievalService.GetEventsByFilter(catFilter, timeFilter, callback);
	}

	/**
	 * Using a list of events to create a cell table
	 * 
	 * @param table2
	 * @param results
	 *            arraylist of events
	 */

	private void CreateCellTable(ArrayList<Event> results) {
		CellTable<Event> table = new CellTable<Event>();
		// To set the size of the table
		table.setPageSize(5);
		SimplePager pager = new SimplePager();

		if (results != null) {
			// short description column
			TextColumn<Event> nameColumn = new TextColumn<Event>() {
				@Override
				public String getValue(Event object) {
					return object.Name();
				}
			};

			// Starting time column
			DateCell dateCell = new DateCell();
			Column<Event, Date> startDateColumn = new Column<Event, Date>(
					dateCell) {
				@Override
				public Date getValue(Event object) {
					return object.getStarHour();
				}
			};

			table.addColumn(nameColumn, "Description");
			table.addColumn(startDateColumn, "Start Time");

			// Style for the list
			table.setColumnWidth(nameColumn, "40%");
			table.setColumnWidth(startDateColumn, "30%");
			table.setStylePrimaryName("StyleTable");

			ListDataProvider<Event> dataProvider = new ListDataProvider<Event>();
			dataProvider.addDataDisplay(table);
			pager.setDisplay(table);

			List<Event> list = dataProvider.getList();
			for (Iterator<Event> i = results.iterator(); i.hasNext();) {
				list.add(i.next());
			}
		}
	}

	private void SetupCellTable(ListDataProvider<Event> dataProvider,
			CellTable<Event> table) {
		table.setPageSize(10);

		// short description column
		TextColumn<Event> nameColumn = new TextColumn<Event>() {
			@Override
			public String getValue(Event object) {
				return object.Name();
			}
		};

		// Starting time column
		TextColumn<Event> startDateColumn = new TextColumn<Event>() {
			@Override
			public String getValue(Event object) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				return dateFormat.format(object.getStarHour());
			}
		};

		// DateCell dateCell = new DateCell();
		// Column<Event, Date> startDateColumn = new Column<Event,
		// Date>(dateCell) {
		// @Override
		// public Date getValue(Event object) {
		// SimpleDateFormat dateFormat = new SimpleDateFormat(
		// "yyyy-MM-dd HH:mm:ss");
		// return object.getStarHour();
		// }
		// };

		table.addColumn(nameColumn, "Description");
		table.addColumn(startDateColumn, "Start Time");

		dataProvider.addDataDisplay(table);

		final SingleSelectionModel<Event> selectionModel = new SingleSelectionModel<Event>();
		table.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Event selected = selectionModel.getSelectedObject();
						if (selected != null) {
							ContentContainer.getInstance();
							ContentContainer.setContent(new EventDetailContent(
									selected.getEventId()));
						}
					}
				});

	}

	private void populateCellTable(ListDataProvider<Event> dataProvider,
			ArrayList<Event> results) {
		List<Event> list = dataProvider.getList();
		list.clear();

		for (Iterator<Event> i = results.iterator(); i.hasNext();) {
			list.add(i.next());
		}
	}

}
