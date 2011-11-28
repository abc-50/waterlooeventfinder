package com.waterlooeventfinder2.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class AdminHeader extends Header {
	public AdminHeader() {
		CreateMainButton();
		CreateLogoutButton();
		DeleteUser();
		AddUser();
	}

	private void DeleteUser() {
		Button main = new Button("Delete User", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new DeleteUserContent());

			}
		});

		panel.add(main);

	}

	private void AddUser() {
		Button main = new Button("Add User", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new AddUserContent());
			}
		});

		panel.add(main);

	}

	private void CreateMainButton() {
		Button main = new Button("Home", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new EventsListContent());

			}
		});

		panel.add(main);

	}

	private void MyEventsButton(final int userId) {
		Button myEvents = new Button("My events", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ContentContainer.setContent(new ClubEventsListContent(userId));
			}
		});

		panel.add(myEvents);
	}

	private void CreateLogoutButton() {
		Button logOutButton = new Button("Logout", new ClickHandler() {
			public void onClick(ClickEvent event) {
				// disconnect();
			}
		});
		panel.add(logOutButton);

	}

	private void CreateAddButton(final int userId) {
		Button addButton = new Button("Add an event", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new ClubEventDetailContent(userId,
						0, "addEvent"));

			}
		});

		panel.add(addButton);

	}

}
