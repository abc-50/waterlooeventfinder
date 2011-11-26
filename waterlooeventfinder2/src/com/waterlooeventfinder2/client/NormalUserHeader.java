package com.waterlooeventfinder2.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class NormalUserHeader extends Header{
	
	public NormalUserHeader(){
		CreateMainButton();
		CreateLoginButton();
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
	
	private void CreateLoginButton() {
		Button login = new Button("Login", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance();
				ContentContainer.setContent(new LoginContent());

			}
		});

		panel.add(login);

	}
}
