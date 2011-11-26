package com.waterlooeventfinder2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class Header extends Composite {
	protected EventRetrievalServiceAsync retrievalService = GWT
			.create(EventRetrievalService.class);

	// add all elements to one panel
	protected HorizontalPanel panel = new HorizontalPanel();

	// constructor
	public Header() {

	}

	public HorizontalPanel getPanel() {

		return panel;
	}
}
