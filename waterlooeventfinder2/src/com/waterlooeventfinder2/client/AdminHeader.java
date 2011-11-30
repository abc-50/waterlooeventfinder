package com.waterlooeventfinder2.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class AdminHeader extends Header {
	DockPanel dp = new DockPanel();
	
	public AdminHeader() {
		CreateMainButton();
		DeleteUser();
		AddUser();
		CreateLogoutButton();
		
		dp.setStyleName("headerNav");
		panel.add(dp);
	}

	private void DeleteUser() {
		Button deleteUser = new Button("Delete User", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new DeleteUserContent());

			}
		});

		dp.add(deleteUser, DockPanel.WEST);
	}

	private void AddUser() {
		Button addUser = new Button("Add User", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new AddUserContent());
			}
		});

		dp.add(addUser, DockPanel.WEST);
	}

	private void CreateMainButton() {
		Button main = new Button("Home", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new EventsListContent());

			}
		});

		dp.add(main, DockPanel.WEST);

	}

	private void CreateLogoutButton() {
		Button logOutButton = new Button("Logout", new ClickHandler() {
			public void onClick(ClickEvent event) {
				logout();
			}
		});
		dp.add(logOutButton, DockPanel.CENTER);
		dp.setCellHorizontalAlignment(logOutButton, HasHorizontalAlignment.ALIGN_RIGHT);

	}

}
