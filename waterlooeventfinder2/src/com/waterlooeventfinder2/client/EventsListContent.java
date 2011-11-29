package com.waterlooeventfinder2.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.Time;

public class EventsListContent extends Content {

	/**
	 * Creates a a events list view
	 */
	public EventsListContent() {
		super();
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		final ListBox categoryBox = new ListBox();
		final ListBox timeBox = new ListBox();
		CellTable<Event> table = new CellTable<Event>();
		final ListDataProvider<Event> ldp = new ListDataProvider<Event>();
		HorizontalPanel hp = new HorizontalPanel();
		SimplePager pager = new SimplePager();

		// Setup category filter
		GetAllCategories(categoryBox);
		categoryBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				GetEventsByFilter(ldp, categoryBox.getSelectedIndex(),
						timeBox.getSelectedIndex());
			}
		});

		hp.add(categoryBox);

		// Setup time filter
		GetAllTimes(timeBox);
		timeBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				GetEventsByFilter(ldp, categoryBox.getSelectedIndex(),
						timeBox.getSelectedIndex());
			}
		});

		hp.add(timeBox);

		// Setup events table
		SetupCellTable(ldp, table);
		GetAllEvents(ldp);

		pager.setDisplay(table);
		pager.firstPage();
		panel.add(hp);
		panel.add(table);
		panel.add(pager);

	}

	/**
	 * RPC call to retrieve time filter text
	 * 
	 * @param timeBox
	 */
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
				// add items to list
				for (Iterator<Time> i = results.iterator(); i.hasNext();) {
					timeBox.addItem(i.next().getTimeDisplayValue());
				}
			}
		};

		retrievalService.GetAllTime(callback);
	}

	/**
	 * RPC Call to get category filter text
	 * 
	 * @param categoryBox
	 */
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
				// send results to data provider
				populateCellTable(ldp, results);
			}
		};

		retrievalService.GetAllEvents(callback);
	}

	/**
	 * Populate events list with applied filters
	 * 
	 * @param ldp
	 *            data provider
	 * @param catFilter
	 *            category filter int
	 * @param timeFilter
	 *            time filter int
	 */
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
				// send filtered results to data provider
				populateCellTable(ldp, results);
			}
		};

		retrievalService.GetEventsByFilter(catFilter, timeFilter, callback);
	}

	/**
	 * Setup table styles
	 * 
	 * @param dataProvider
	 * @param table
	 */
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
				DateTimeFormat dateFormat = DateTimeFormat
						.getFormat("E, MMMM dd 'at' HH:mm");
				return dateFormat.format(object.getStarHour());
			}
		};

		// styling
		table.setColumnWidth(nameColumn, "50%");
		table.setColumnWidth(startDateColumn, "30%");
		table.setStylePrimaryName("styleTable");

		table.addColumn(nameColumn, "Description");
		table.addColumn(startDateColumn, "Start Time");
		
		// setup data provider for table
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

	/**
	 * Using a list of events to populate a data provider
	 * 
	 * @param results
	 *            arraylist of events
	 */
	private void populateCellTable(ListDataProvider<Event> dataProvider,
			ArrayList<Event> results) {

		List<Event> list = dataProvider.getList();
		list.clear();

		for (Iterator<Event> i = results.iterator(); i.hasNext();) {
			list.add(i.next());
		}
	}

}