package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.User;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Waterlooeventfinder2 extends Composite implements EntryPoint {

	ButtonPressed infoButtonPressed = new ButtonPressed();
	MyTable table = new MyTable();
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
					// result.add(new Event());
					updateRowData(start, result);
					updateRowCount(result.size(), true);

				}
			};

			retrievalService.GetEventsByFilter(infoButtonPressed.getCategory(),
					infoButtonPressed.getTime(), start, end, callback);
		}

	};

	protected void selectEventByID(final int id) {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<Event> callback = new AsyncCallback<Event>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				// TODO: Do something with errors.
			}

			public void onSuccess(Event result) {
				Window.alert("got event: " + result.Name());

			}
		};

		retrievalService.GetEventById(id, callback);
	}

	protected void disconnect() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				// TODO: Do something with errors.
			}

			public void onSuccess(Integer result) {
				Window.alert("Disconnected");
			}
		};
		retrievalService.logout(callback);
	}

	// Associate an async data provider to the table
	// XXX: Use AsyncCallback in the method onRangeChanged
	// to actually get the data from the server side

	protected void connectToAccount(final String login, final String password) {

		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<User> callback = new AsyncCallback<User>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				// TODO: Do something with errors.
			}

			@Override
			public void onSuccess(User result) {
				// TODO Auto-generated method stub
				// Window.alert(Integer.toString(result));
				if (result.isLoggedInApplication() == false) {
					Window.alert("Login Unsuccessful");
				} else {
					String name = result.getDisplayName();
					Button LogOutButton = new Button(name + ": Logout",
							new ClickHandler() {
								public void onClick(ClickEvent event) {
									disconnect();

								}
							});
					RootPanel.get().add(LogOutButton);
				}

			}

		};

		retrievalService.logToServer(login, password, callback);
	}
	
	protected void loginUsingSession(final String login, final String password) {

		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<User> callback = new AsyncCallback<User>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				// TODO: Do something with errors.
			}

			@Override
			public void onSuccess(User result) {
				// TODO Auto-generated method stub
				// Window.alert(Integer.toString(result));
				if (result.isLoggedInApplication() == false) {
					Window.alert("Login Using Session Unsuccessful");
					// TODO: Redirect to the login page
				} else {
					Window.alert("Login using Session successful");
					// TODO: Display the elements as the user is logged in
				}

			}

		};

		retrievalService.loginUsingSession(callback);
	}

	protected void selectEvents(final String category, final String time) {

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
				// Inform the displays of the new data.
				provider.updateRowData(0, result);
				// Inform the displays of the total number of items that are
				// available.
				provider.updateRowCount(result.size(), true);
				pager.firstPage();
			}
		};

		retrievalService.GetEventsByFilter(infoButtonPressed.getCategory(),
				infoButtonPressed.getTime(), 0, 0, callback);
	}

	// get list of categories
	protected void getAllCategories() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<ArrayList<Category>> callback = new AsyncCallback<ArrayList<Category>>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(ArrayList<Category> result) {
				// TODO: Create control for category
			}
		};

		retrievalService.GetAllCategory(callback);
	}

	// Call this function to load description page
	public void viewEvent(int ID) {
		loadDetailsPage(ID);
		// Window.Location.assign(GWT.getHostPageBaseURL()
		// + "EventDescription2.html?" + ID);
	}

	public void loadMainPage() { // WRITE EVERYTHING HERE !

		final PasswordTextBox passwordBox = new PasswordTextBox();
		final TextBox loginBox = new TextBox();

		// Add them to the root panel.
		VerticalPanel panel = new VerticalPanel();
		panel.add(loginBox);
		panel.add(passwordBox);

		Button buttonConnection = new Button("Connect to your account",
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						Window.alert("Connection starts");
						String login = loginBox.getText();
						String password = passwordBox.getText();

						connectToAccount(login, password);
					}
				});

		panel.add(buttonConnection);
		RootPanel.get().add(panel);

		showMainPage();
		clearDescriptionPage();

		final Button CategoryAll = new Button("All");
		final Button CategorySport = new Button("Sport");
		final Button CategoryDance = new Button("Dance");
		final Button CategoryConcert = new Button("Concert");
		final Button CategoryBars = new Button("Bars");
		final Button TimeUpcoming = new Button("Today");
		final Button TimeOneDay = new Button("1 day");
		final Button TimeTwoDays = new Button("2 days");
		final Button TimeThreeDays = new Button("3 days");
		final Button TimeOneWeek = new Button("1 week");

		// MIKES SECTION PLEASE DO NOT CHANGE!
		Button test = new Button("eventdetail");
		test.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				selectEventByID(1);

			}
		});

		Button addEvent = new Button("Add Event");

		RootPanel.get("row1").add(test);
		RootPanel.get("row1").add(addEvent);
		// END MIKES SECTION

		// We put in Green All + Upcoming
		DOM.setElementAttribute(CategoryAll.getElement(), "id",
				"pressed-button");
		DOM.setElementAttribute(TimeUpcoming.getElement(), "id",
				"pressed-button");
		CategoryAll.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("All");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(CategoryAll.getElement(), "id",
						"pressed-button");
				DOM.setElementAttribute(CategorySport.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryDance.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryConcert.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryBars.getElement(), "id",
						"non-pressed-button");
			}
		});

		CategorySport.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("Sport");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(CategoryAll.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategorySport.getElement(), "id",
						"pressed-button");
				DOM.setElementAttribute(CategoryDance.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryConcert.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryBars.getElement(), "id",
						"non-pressed-button");
			}
		});

		CategoryDance.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("Dance");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(CategoryAll.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategorySport.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryDance.getElement(), "id",
						"pressed-button");
				DOM.setElementAttribute(CategoryConcert.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryBars.getElement(), "id",
						"non-pressed-button");
			}
		});

		CategoryConcert.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("Concert");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(CategoryAll.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategorySport.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryDance.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryConcert.getElement(), "id",
						"pressed-button");
				DOM.setElementAttribute(CategoryBars.getElement(), "id",
						"non-pressed-button");
			}
		});

		CategoryBars.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setCategory("Bars");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(CategoryAll.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategorySport.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryDance.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryConcert.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(CategoryBars.getElement(), "id",
						"pressed-button");
			}
		});

		TimeUpcoming.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setTime("Upcoming");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(TimeUpcoming.getElement(), "id",
						"pressed-button");
				DOM.setElementAttribute(TimeOneDay.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeTwoDays.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeThreeDays.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeOneWeek.getElement(), "id",
						"non-pressed-button");
			}
		});

		TimeOneDay.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setTime("One day");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(TimeUpcoming.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeOneDay.getElement(), "id",
						"pressed-button");
				DOM.setElementAttribute(TimeTwoDays.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeThreeDays.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeOneWeek.getElement(), "id",
						"non-pressed-button");
			}
		});

		TimeTwoDays.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setTime("Two days");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(TimeUpcoming.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeOneDay.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeTwoDays.getElement(), "id",
						"pressed-button");
				DOM.setElementAttribute(TimeThreeDays.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeOneWeek.getElement(), "id",
						"non-pressed-button");
			}
		});

		TimeThreeDays.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setTime("Three days");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(TimeUpcoming.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeOneDay.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeTwoDays.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeThreeDays.getElement(), "id",
						"pressed-button");
				DOM.setElementAttribute(TimeOneWeek.getElement(), "id",
						"non-pressed-button");
			}
		});

		TimeOneWeek.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				infoButtonPressed.setTime("One week");
				selectEvents(infoButtonPressed.getCategory(),
						infoButtonPressed.getTime());
				DOM.setElementAttribute(TimeUpcoming.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeOneDay.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeTwoDays.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeThreeDays.getElement(), "id",
						"non-pressed-button");
				DOM.setElementAttribute(TimeOneWeek.getElement(), "id",
						"pressed-button");
			}
		});

		// We can add style names to widgets

		CategoryAll.addStyleName("categoryButton");
		CategorySport.addStyleName("categoryButton");
		CategoryDance.addStyleName("categoryButton");
		CategoryConcert.addStyleName("categoryButton");
		CategoryBars.addStyleName("categoryButton");

		TimeUpcoming.addStyleName("timeButton");
		TimeOneDay.addStyleName("timeButton");
		TimeTwoDays.addStyleName("timeButton");
		TimeThreeDays.addStyleName("timeButton");
		TimeOneWeek.addStyleName("timeButton");

		// final Button ChooseMain = new Button("Main");
		/*
		 * // table.createTable();
		 * 
		 * final SingleSelectionModel<Event> selectionModel = new
		 * SingleSelectionModel<Event>(); //
		 * table.setSelectionModel(selectionModel);
		 * 
		 * selectionModel .addSelectionChangeHandler(new
		 * SelectionChangeEvent.Handler() { public void
		 * onSelectionChange(SelectionChangeEvent event) { Event selected =
		 * selectionModel.getSelectedObject(); if (selected != null) { //
		 * randomly pick 0 or 1 //viewEvent(selected.getCategoryId() % 2);
		 * String search = "1"; String result = ""; int i; i =
		 * selected.Name().indexOf(search);
		 * 
		 * //result = selected.Name().substring(i); //if(result != null){ //
		 * viewEvent(1); //} else { // viewEvent(2); //} } } });
		 */

		table.createTable();
		// Display 2 rows in one page
		table.setPageSize(3);

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
				if (infoButtonPressed.getTime().equals("Upcoming")) {
					startDateTime = DateTimeFormat.getFormat(
							PredefinedFormat.TIME_SHORT).format(
							object.getStarHour());
				} else {
					startDateTime = DateTimeFormat.getFormat(
							PredefinedFormat.DATE_TIME_SHORT).format(
							object.getStarHour());
				}
				return startDateTime;
			}
		};

		// Add a column to show end time
		TextColumn<Event> endColumn = new TextColumn<Event>() {
			@Override
			public String getValue(Event object) {
				String endDateTime = null;
				if (infoButtonPressed.getTime().equals("Upcoming")) {
					endDateTime = DateTimeFormat.getFormat(
							PredefinedFormat.TIME_SHORT).format(
							object.getEndHour());
				} else {
					endDateTime = DateTimeFormat.getFormat(
							PredefinedFormat.DATE_TIME_SHORT).format(
							object.getEndHour());
				}
				return endDateTime;
			}
		};

		// Add columns to the table
		table.addColumn(nameColumn, "Name");
		table.addColumn(startColumn, "Start");
		table.addColumn(endColumn, "End");

		table.addColumnStyleName(0, "nameColumStyle");
		table.addColumnStyleName(1, "StartColumStyle");
		table.addColumnStyleName(2, "EndColumnStyle");

		table.setWidth("100%", true);
		table.setColumnWidth(nameColumn, 40.0, Unit.PCT);
		table.setColumnWidth(startColumn, 20.0, Unit.PCT);
		table.setColumnWidth(endColumn, 20.0, Unit.PCT);
		table.setColumnWidth(endColumn, 20.0, Unit.PCT);

		final SingleSelectionModel<Event> selectionModel = new SingleSelectionModel<Event>();
		// table.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Event selected = selectionModel.getSelectedObject();
						if (selected != null) {
							// randomly pick 0 or 1
							viewEvent(selected.getCategoryId() % 2);
							String search = "1";
							String result = "";
							int i;
							i = selected.Name().indexOf(search);

							// result = selected.Name().substring(i);
							// if(result != null){
							// viewEvent(1);
							// } else {
							// viewEvent(2);
							// }
						}
					}
				});

		provider.addDataDisplay(table);
		pager.setDisplay(table);

		RootPanel.get("row1").add(CategoryAll);
		RootPanel.get("row1").add(CategorySport);
		RootPanel.get("row1").add(CategoryDance);
		RootPanel.get("row1").add(CategoryConcert);
		RootPanel.get("row1").add(CategoryBars);

		RootPanel.get("row2").add(TimeUpcoming);
		RootPanel.get("row2").add(TimeOneDay);
		RootPanel.get("row2").add(TimeTwoDays);
		RootPanel.get("row2").add(TimeThreeDays);
		RootPanel.get("row2").add(TimeOneWeek);

		RootPanel.get("row3").add(table);
		pager.addStyleName("elementPager");
		RootPanel.get("row4").add(pager);

	}

	public void onModuleLoad() {

		loadMainPage();
	}

	public void loadDetailsPage(int eventID) {

		clearDescriptionPage();

		// TO-DO:
		// Connect to the database and get the number of columns for the
		// description of an event
		final String flink1 = "http://www.youtube.com/embed/zkbXTQ95kLc";
		Frame youtubeVideo = new Frame(flink1);
		// To set the size of the video
		youtubeVideo.setWidth("95%");

		Grid g = new Grid(2, 2);
		int col = 0;
		// Put some values in the grid cells.
		for (int row = 0; row < 2; row++) {
			for (col = 0; col < 2; col++)

				g.setText(
						row,
						col,
						""
								+ row
								+ ",erdctfvygb hnj,ksxd fgcvhjnk, dzdzdz dzdzdzds d fghjkad zfghz ejfkz efezf zefezf ezf efzefezf "
								+ col);
			g.getCellFormatter().setStyleName(row, 0, "column1Description");
			g.getCellFormatter().setStyleName(row, 1, "column2Description");
			g.setWidget(0, 1, youtubeVideo);

		}

		// Set the width to the first column
		g.getColumnFormatter().setWidth(0, "25%");
		g.getColumnFormatter().setWidth(1, "65");

		TextBox box1 = new TextBox();
		box1.setText("Lol");
		g.setWidget(1, 1, box1);

		// You can use the CellFormatter to affect the layout of the grid's
		// cells.

		final Button returnMain = new Button("Main");
		returnMain.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				showMainPage();
				clearDescriptionPage();
			}
		});
		eventID = 2;

		final VerticalPanel vp2 = new VerticalPanel();

		returnMain.addStyleName("menuBar");

		// Associate Main Panel with host page.
		hideMainPage();
		RootPanel.get("row5").add(returnMain);
		RootPanel.get("row6").add(vp2);
		RootPanel.get("row7").add(g);

		returnMain.setEnabled(true);
	}

	private void clearMainPage() {

		RootPanel.get("row1").clear();
		RootPanel.get("row2").clear();
		RootPanel.get("row3").clear();
		RootPanel.get("row4").clear();
	}

	private void clearDescriptionPage() {

		RootPanel.get("row5").clear();
		RootPanel.get("row6").clear();
		RootPanel.get("row7").clear();

	}

	private void hideMainPage() {
		// TODO Auto-generated method stub
		RootPanel.get("row1").setStyleName("hide");
		RootPanel.get("row2").setStyleName("hide");
		RootPanel.get("row3").setStyleName("hide");
		RootPanel.get("row4").setStyleName("hide");

	}

	private void showMainPage() {
		// TODO Auto-generated method stub
		RootPanel.get("row1").setStyleName("show");
		RootPanel.get("row2").setStyleName("show");
		RootPanel.get("row3").setStyleName("show");
		RootPanel.get("row4").setStyleName("show");
	}

}