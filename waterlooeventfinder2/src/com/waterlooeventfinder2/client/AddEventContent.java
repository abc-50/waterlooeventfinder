//package com.waterlooeventfinder2.client;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.Date;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.dev.util.Callback;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.logical.shared.ValueChangeEvent;
//import com.google.gwt.event.logical.shared.ValueChangeHandler;
//import com.google.gwt.i18n.client.DateTimeFormat;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.ListBox;
//import com.google.gwt.user.client.ui.TextArea;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.datepicker.client.DatePicker;
//import com.waterlooeventfinder2.shared.Category;
//
//public class AddEventContent extends Content {
//
//	HorizontalPanel hpanel = new HorizontalPanel();
//	Boolean validityURL;
//	TextBox nameBox = new TextBox();
//	ListBox category = new ListBox();
//
//	Button startDate = new Button();
//	Button endDate = new Button();
//
//	ListBox hourStart = new ListBox();
//	ListBox minuteStart = new ListBox();
//
//	ListBox hourEnd = new ListBox();
//	ListBox minuteEnd = new ListBox();
//
//	TextBox locationBox = new TextBox();
//	TextArea descriptionBox = new TextArea();
//
//	TextBox websiteBox = new TextBox();
//	TextBox videoBox = new TextBox();
//	TextBox phoneNumberBox = new TextBox();
//	TextBox emailBox = new TextBox();
//
//	public AddEventContent(int userId) {
//
//		setCategories();
//		AddFieldsHours();
//		CreateFormatForBoxes();
//		CreateAddTableForEvent();
//		CreateRemoveFieldsButton();
//		CreateSaveAndCreateEventButton(userId);
//	}
//
//	private void CreateFormatForBoxes() {
//		descriptionBox.setCharacterWidth(70);
//		descriptionBox.setVisibleLines(15);
//
//		websiteBox.setWidth("50%");
//		nameBox.setWidth("50%");
//		locationBox.setWidth("50%");
//		websiteBox.setWidth("50%");
//		videoBox.setWidth("50%");
//		phoneNumberBox.setWidth("50%");
//		emailBox.setWidth("50%");
//
//	}
//
//	private void setCategories() {
//		if (retrievalService == null) {
//			retrievalService = GWT.create(EventRetrievalService.class);
//		}
//
//		// Set up the callback object.
//		AsyncCallback<ArrayList<Category>> callback = new AsyncCallback<ArrayList<Category>>() {
//
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.getMessage());
//			}
//
//			public void onSuccess(ArrayList<Category> results) {
//				for (Category categoryResults : results) {
//					category.addItem(categoryResults.getCategoryName());
//				}
//			}
//		};
//
//		retrievalService.getCategories(callback);
//
//	}
//
//	private void AddFieldsHours() {
//		int i = 0;
//		while (i < 24) {
//
//			String num = Integer.toString(i);
//			hourStart.addItem(num + "h");
//			hourEnd.addItem(num + "h");
//			i = i + 1;
//
//		}
//
//		i = 0;
//		while (i < 60) {
//			String num = Integer.toString(i);
//			minuteStart.addItem(num + "min");
//			minuteEnd.addItem(num + "min");
//			i = i + 5;
//		}
//
//	}
//
//	private void CreateRemoveFieldsButton() {
//		Button RemoveFieldsButton = new Button("Clear fields",
//				new ClickHandler() {
//					public void onClick(ClickEvent event) {
//
//						nameBox.setText("");
//						locationBox.setText("");
//						descriptionBox.setText("");
//						websiteBox.setText("");
//						videoBox.setText("");
//						phoneNumberBox.setText("");
//						emailBox.setText("");
//
//					}
//				});
//		hpanel.add(RemoveFieldsButton);
//		panel.add(hpanel);
//	}
//
//	private void CreateSaveAndCreateEventButton(final int userId) {
//
//		Button SaveAndCreateEventButton = new Button("Save & Create Event",
//				new ClickHandler() {
//					public void onClick(ClickEvent event) {
//						String name = nameBox.getText();
//						int categoryId = category.getSelectedIndex() + 1;
//
//						String startEvent = CreateStringStartEvent();
//						String endEvent = CreateStringEndEvent();
//						String location = locationBox.getText();
//
//						String description = descriptionBox.getText();
//						String website = websiteBox.getText();
//						String video = videoBox.getText();
//						String phoneNumber = phoneNumberBox.getText();
//						String email = emailBox.getText();
//
//						try {
//							if (ControlFieldsContent(name, categoryId,
//									startEvent, endEvent, location,
//									description, website, video, phoneNumber,
//									email)) {
//
//								if (retrievalService == null) {
//									retrievalService = GWT
//											.create(EventRetrievalService.class);
//								}
//
//								// Set up the callback object.
//								AsyncCallback<String> callback = new AsyncCallback<String>() {
//
//									public void onFailure(Throwable caught) {
//										Window.alert(caught.getMessage());
//									}
//
//									@Override
//									public void onSuccess(String result) {
//										Window.alert("Event added");
//
//									}
//								};
//
//								retrievalService.AddEvent(userId, categoryId,
//										startEvent, endEvent, location,
//										description, name, website, video,
//										phoneNumber, email, callback);
//
//							}
//						} catch (IllegalArgumentException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//
//				});
//
//		hpanel.add(SaveAndCreateEventButton);
//		panel.add(hpanel);
//	}
//
//	protected String CreateStringStartEvent() {
//		String dateStart = startDate.getText();
//
//		int hourLength = hourStart.getValue(hourStart.getSelectedIndex())
//				.length();
//		String hourStringStart = hourStart.getValue(
//				hourStart.getSelectedIndex()).substring(0, hourLength - 1);
//
//		int minLength = minuteStart.getValue(minuteStart.getSelectedIndex())
//				.length();
//		String minuteStringStart = minuteStart.getValue(
//				minuteStart.getSelectedIndex()).substring(0, minLength - 3);
//
//		if (hourStringStart.length() == 1) {
//			hourStringStart = "0" + hourStringStart;
//		}
//		if (minuteStringStart.length() == 1) {
//			minuteStringStart = "0" + minuteStringStart;
//		}
//		String startDateFinal = dateStart + " " + hourStringStart + ":"
//				+ minuteStringStart + ":00";
//		return startDateFinal;
//	}
//
//	protected String CreateStringEndEvent() {
//
//		String dateEnd = endDate.getText();
//
//		int hourLength = hourEnd.getValue(hourEnd.getSelectedIndex()).length();
//		String hourStringEnd = hourEnd.getValue(hourEnd.getSelectedIndex())
//				.substring(0, hourLength - 1);
//
//		int minLength = minuteEnd.getValue(minuteEnd.getSelectedIndex())
//				.length();
//		String minuteStringEnd = minuteEnd.getValue(
//				minuteEnd.getSelectedIndex()).substring(0, minLength - 3);
//
//		if (hourStringEnd.length() == 1) {
//			hourStringEnd = "0" + hourStringEnd;
//		}
//		if (minuteStringEnd.length() == 1) {
//			minuteStringEnd = "0" + minuteStringEnd;
//		}
//		String endDateFinal = dateEnd + " " + hourStringEnd + ":"
//				+ minuteStringEnd + ":00";
//		return endDateFinal;
//	}
//
//	private boolean ControlFieldsContent(String name, int categoryId,
//			String start, String end, String location, String description,
//			String website, String video, String phoneNumber, String email) {
//
//		if (name.length() < 5 || name.length() > 30) {
//			Window.alert("Please use between 5 and 30 characters for the name");
//			return false;
//		} else if (location.length() < 5 || name.length() > 150) {
//			Window.alert("Please use between 5 and 150 characters for the location");
//			return false;
//		} else if (location.length() < 5 || name.length() > 150) {
//			Window.alert("Please use between 5 and 150 characters for the location");
//			return false;
//		} else if (description.length() < 10 || name.length() > 1000) {
//			Window.alert("Please use between 10 and 1000 characters for the description");
//			return false;
//		} else if (!checkWebsiteUrl(website)) {
//			Window.alert(website);
//
//			Window.alert("The url for your website is invalid : Please use httpwww.YOUR-WEBSITE.com format");
//			return false;
//
//		} else if (!verifyYoutubeVideoLink(video)) {
//			Window.alert("Please insert a correct Youtube Video link ");
//			videoBox.setText("");
//			return false;
//		} else if (!verifyEmail(email)) {
//			Window.alert("Please insert a correct email");
//			return false;
//		}
//
//		return true;
//
//	}
//
//	private boolean verifyEmail(String email) {
//		int index1 = email.indexOf("@");
//		int index2 = email.indexOf(".");
//		if (index1 == -1) {
//			return false;
//		} else if (index2 == -1) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//	private Boolean checkWebsiteUrl(String website) {
//		if (website.length() == 0) {
//			return true;
//		} else {
//			if (retrievalService == null) {
//				retrievalService = GWT.create(EventRetrievalService.class);
//			}
//
//			// Set up the callback object.
//			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//
//				public void onFailure(Throwable caught) {
//					validityURL = false;
//				}
//
//				@Override
//				public void onSuccess(Boolean result) {
//					if (result) {
//						validityURL = true;
//					} else {
//
//						validityURL = false;
//					}
//
//				}
//			};
//
//			retrievalService.CheckUrl(website, callback);
//
//			if (validityURL) {
//				return true;
//			} else {
//				return false;
//			}
//		}
//
//	}
//
//	private boolean verifyYoutubeVideoLink(String video) {
//		if (video.length() != 0) {
//			int index1 = video.indexOf("www.youtube.com");
//
//			if (index1 == -1) {
//				return false;
//			} else {
//				if (retrievalService == null) {
//					retrievalService = GWT.create(EventRetrievalService.class);
//				}
//
//				// Set up the callback object.
//				AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//
//					public void onFailure(Throwable caught) {
//					}
//
//					@Override
//					public void onSuccess(Boolean result) {
//						if (result) {
//							validityURL = true;
//						} else {
//
//							validityURL = false;
//						}
//					}
//				};
//
//				retrievalService.CheckUrl(video, callback);
//
//				if (validityURL) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//		} else {
//			return true;
//		}
//
//	}
//
//	private void CreateAddTableForEvent() {
//
//		FlexTable ft = new FlexTable();
//
//		int curRow = 0;
//
//		ft.setText(curRow, 0, "Event Name: ");
//		ft.setWidget(curRow, 1, nameBox);
//		curRow++;
//
//		ft.setText(curRow, 0, "Event Category: ");
//		ft.setWidget(curRow, 1, category);
//		curRow++;
//
//		ft.setText(curRow, 0, "Start date: ");
//
//		DatePicker datePicker1 = new DatePicker();
//		datePicker1.setSize("40%", "15%");
//
//		// Set the value in the text box when the user selects a date
//		datePicker1.addValueChangeHandler(new ValueChangeHandler() {
//			public void onValueChange(ValueChangeEvent event) {
//				Date date = (Date) event.getValue();
//				DateTimeFormat dateString = DateTimeFormat
//						.getFormat("yyyy:MM:dd");
//				startDate.setText(dateString.format(date));
//			}
//		});
//
//		datePicker1.setValue(new Date(), true);
//
//		DatePicker datePicker2 = new DatePicker();
//		datePicker2.setSize("40%", "15%");
//
//		// Set the value in the text box when the user selects a date
//		datePicker2.addValueChangeHandler(new ValueChangeHandler() {
//			public void onValueChange(ValueChangeEvent event) {
//				Date date = (Date) event.getValue();
//				DateTimeFormat dateString = DateTimeFormat
//						.getFormat("yyyy:MM:dd");
//				endDate.setText(dateString.format(date));
//			}
//		});
//
//		datePicker2.setValue(new Date(), true);
//
//		// Set the default value
//		// Add the widgets to the page
//		ft.setWidget(curRow, 1, startDate);
//		ft.setWidget(curRow, 2, datePicker1);
//		curRow++;
//
//		ft.setText(curRow, 0, "End date: ");
//		ft.setWidget(curRow, 1, endDate);
//		ft.setWidget(curRow, 2, datePicker2);
//		curRow++;
//
//		ft.setText(curRow, 0, "Start time: ");
//		ft.setWidget(curRow, 1, hourStart);
//		ft.setWidget(curRow, 2, minuteStart);
//		curRow++;
//
//		ft.setText(curRow, 0, "End time: ");
//		ft.setWidget(curRow, 1, hourEnd);
//		ft.setWidget(curRow, 2, minuteEnd);
//		curRow++;
//
//		ft.setText(curRow, 0, "Location: ");
//		ft.setWidget(curRow, 1, locationBox);
//		curRow++;
//
//		ft.setText(curRow, 0, "Description: ");
//		ft.setWidget(curRow, 1, descriptionBox);
//		curRow++;
//
//		ft.setText(curRow, 0, "Website: ");
//		ft.setWidget(curRow, 1, websiteBox);
//		curRow++;
//
//		ft.setText(curRow, 0, "Promotional video: ");
//		ft.setWidget(curRow, 1, videoBox);
//		curRow++;
//
//		ft.setText(curRow, 0, "Phone: ");
//		ft.setWidget(curRow, 1, phoneNumberBox);
//		curRow++;
//
//		ft.setText(curRow, 0, "Email: ");
//		ft.setWidget(curRow, 1, emailBox);
//		curRow++;
//
//		panel.add(ft);
//	}
//
//}
