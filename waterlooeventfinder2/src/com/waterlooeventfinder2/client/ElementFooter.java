package com.waterlooeventfinder2.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class ElementFooter extends Footer {
	public ElementFooter() {
		CreateElementFooter();
	}

	/**
	 * Creates static footer
	 */
	private void CreateElementFooter() {
		HTML html = new HTML("<div align='center'>Have a question? <a href='mailto:waterlooeventfinder@gmail.com'>" +
				" Write us an email</a>   |  Created by Martin Lacombe and Mike Ye 2011 </div>");
		panel.add(html);

	}
}
