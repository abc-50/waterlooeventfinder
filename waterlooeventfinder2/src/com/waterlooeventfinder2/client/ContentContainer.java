package com.waterlooeventfinder2.client;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * Singleton class used for controlling content
 * @author Mike
 *
 */
public class ContentContainer {
	private static ContentContainer container;
	
	/**
	 * Protected constructor
	 */
	protected ContentContainer() {

	}

	/**
	 * get an instance of ContentContainer
	 * @return ContentContainer instance
	 */
	public static ContentContainer getInstance() {
		if (container == null) {
			synchronized(ContentContainer.class) {
				if (container == null) {
					container = new ContentContainer();

				}
			}
		}
		
		// automatically attempt to login via session

		return container;
	}

	/**
	 * Set dock panel to root panel
	 * @param c content
	 */
	public static void setContent(Content c) {
		RootPanel rp = RootPanel.get("content");
		rp.clear();

		rp.add(c.getPanel());
	
	}
	
	public static void setHeader(Header c) {
		RootPanel rp = RootPanel.get("header");
		rp.clear();

		rp.add(c.getPanel());
	}
	
	public static void setFooter(Footer c) {
		RootPanel rp = RootPanel.get("footer");
		rp.clear();

		rp.add(c.getPanel());
	}

}