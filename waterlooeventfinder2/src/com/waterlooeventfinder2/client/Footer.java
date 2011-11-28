package com.waterlooeventfinder2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Footer extends Composite {
	protected EventRetrievalServiceAsync retrievalService = GWT
			.create(EventRetrievalService.class);

	// add all elements to one panel
	protected VerticalPanel panel = new VerticalPanel();

	// constructor
	public Footer() {

	}

	public VerticalPanel getPanel() {

		return panel;
	}
}
