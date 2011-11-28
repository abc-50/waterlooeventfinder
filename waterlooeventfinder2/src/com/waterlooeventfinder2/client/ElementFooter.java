package com.waterlooeventfinder2.client;

import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class ElementFooter extends Footer {
	public ElementFooter() {
		CreateElementFooter();
	}

	private void CreateElementFooter() {
		Hyperlink link = new Hyperlink("Have a question? Write us a mail", "waterlooeventfinder@uwaterloo.ca");
		link.setStyleName("FooterStyle");
		panel.add(link);
		
		
	}
	
	
}
