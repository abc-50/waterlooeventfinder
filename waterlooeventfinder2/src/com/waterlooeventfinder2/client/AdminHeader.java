package com.waterlooeventfinder2.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class AdminHeader extends Header {
	public AdminHeader() {
		CreateMainButton();
		DeleteUser();
		AddUser();
		CreateLogoutButton();
	}

	private void DeleteUser() {
		Button deleteUser = new Button("Delete User", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new DeleteUserContent());

			}
		});

		panel.add(deleteUser);
		panel.setCellHorizontalAlignment(deleteUser, HasHorizontalAlignment.ALIGN_CENTER);

	}

	private void AddUser() {
		Button addUser = new Button("Add User", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new AddUserContent());
			}
		});

		panel.add(addUser);
		panel.setCellHorizontalAlignment(addUser, HasHorizontalAlignment.ALIGN_CENTER);
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

	private void CreateLogoutButton() {
		Button logOutButton = new Button("Logout", new ClickHandler() {
			public void onClick(ClickEvent event) {
				logout();
			}
		});
		panel.add(logOutButton);
		panel.setCellHorizontalAlignment(logOutButton, HasHorizontalAlignment.ALIGN_RIGHT);

	}

}
