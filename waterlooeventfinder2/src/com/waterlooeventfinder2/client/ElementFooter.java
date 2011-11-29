package com.waterlooeventfinder2.client;

import com.google.gwt.user.client.ui.Hyperlink;

public class ElementFooter extends Footer {
	public ElementFooter() {
		CreateElementFooter();
	}

	/**
	 * Creates static footer
	 */
	private void CreateElementFooter() {
		Hyperlink link = new Hyperlink("Have a question? Write us a mail", "mailto:waterlooeventfinder@uwaterloo.ca");
		link.setStyleName("FooterStyle");
		panel.add(link);
		
		
	}
	
	
}
