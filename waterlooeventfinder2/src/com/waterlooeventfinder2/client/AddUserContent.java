package com.waterlooeventfinder2.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.waterlooeventfinder2.shared.Category;
import com.waterlooeventfinder2.shared.Event;
import com.waterlooeventfinder2.shared.TypeUser;

public class AddUserContent extends Content {

	HorizontalPanel hpanel = new HorizontalPanel();
	
	TextBox nameBox = new TextBox();
	
	PasswordTextBox password = new PasswordTextBox();
	PasswordTextBox passwordRetyped = new PasswordTextBox();
	
	ListBox typeUser = new ListBox();

	public AddUserContent() {
		CreateAddUserTable();
		addUserButton();
		setType();
	}
	
	private void setType() {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<ArrayList<TypeUser>> callback = new AsyncCallback<ArrayList<TypeUser>>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(ArrayList<TypeUser> results) {
				for (TypeUser typeUserContent : results) {

					typeUser.addItem(typeUserContent.getUserType(),Integer.toString(typeUserContent.getTypeId()));
				}
				
			}
		};
		
		retrievalService.GetTypesUser(callback);
		
	}

	private void CreateAddUserTable() {

		FlexTable ft = new FlexTable();

		int curRow = 0;

		ft.setText(curRow, 0, "User Name: ");
		ft.setWidget(curRow, 1, nameBox);
		curRow++;

		ft.setText(curRow, 0, "User Password");
		ft.setWidget(curRow, 1, password);
		curRow++;

		ft.setText(curRow, 0, "Retype User Password");
		ft.setWidget(curRow, 1, passwordRetyped);
		curRow++;
		
		ft.setText(curRow, 0, "Type of the user");
		ft.setWidget(curRow, 1, typeUser);
		curRow++;

		panel.add(ft);
	}

	private void addUserButton() {

		Button addUserButton = new Button("Add this user",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						final String nameOfUser = nameBox.getText();
						
						
						if (verifyFields()){
							AddUserByName(nameOfUser, password.getText(), typeUser.getValue(typeUser.getSelectedIndex()));
						}
					}

					

				});

		hpanel.add(addUserButton);
		panel.add(hpanel);

	}
	
	protected void AddUserByName(final String nameOfUser, String password, String typeUser) {
		if (retrievalService == null) {
			retrievalService = GWT.create(EventRetrievalService.class);
		}

		// Set up the callback object.
		AsyncCallback<String> callback = new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			public void onSuccess(String results) {
				Window.alert("User " + nameOfUser + "added !");		
			}
		};

		retrievalService.AddUserByName(nameOfUser, password, typeUser, callback);
		
	}

	private boolean verifyFields() {
		String categoryId = typeUser.getValue(typeUser.getSelectedIndex());
		
		if (password.getText().equals(passwordRetyped.getText())){
			return true;
		}
		return false;
	}
}
