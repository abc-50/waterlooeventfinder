package com.waterlooeventfinder2.client;

import java.lang.Integer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;


public class EventDescription2 implements EntryPoint {
	
	//protected Event eventObj;
	
	//private EventRetrievalServiceAsync retrievalService = GWT.create(EventRetrievalService.class);

	
	//private void retrieveEvent() {
		//if (retrievalService == null) {
			//retrievalService = GWT.create(EventRetrievalService.class);
		//}

		// Set up the callback object.
		//AsyncCallback<Event> callback = new AsyncCallback<Event>() {
			//public void onFailure(Throwable caught) {
				//Window.alert(caught.getMessage());
			//}

			//public void onSuccess(Event result) {
				//eventObj = result;
			//}
		//};
		//retrievalService.GetEventById(eventID,callback);		
	//}
	
	
	public void onModuleLoad() {
		int eventID;
		
		final Button ChooseMain = new Button("Main");
		final String label1 = "Football season finale!!! \n\n" +
				"Location: Warrior Field, University of Waterloo, Waterloo, ON, N2L3G1 \n\n" +
				"LIGHTS, CAMERA, ACTION: The Warrior field will this Saturday be the host to the ultimate showdown between the Wilfrid Laurier Hawks and your very own Waterloo Warriors. Come on out this weekend whether it be to have fun with your friends or to enjoy a nice game of football. After the crushing loss two weeks ago at the hands of Queen's, the Warriors need your support." +
				"So, bring out your Warrior spirit and help Warriors win the final game of the season. Tickets are only $10 and go on sale 26th October, 2011. Pick up your ticket soon from the athletics office or the sales booth in Student Life Centre. \n\n" +
				"Video: http://www.youtube.com/watch?v=zkbXTQ95kLc \n" +
				"Event Website: http://www.varsity.uwaterloo.ca/schedule.aspx?path=football&schedule=38& \n\n" +
				"Contact: \n" +
				"Phone: 5198884567 \n" +
				"E-mail: campusrec@uwaterloo.ca";
		
		final String label2 = "Brilliance in Music!!! \n\n" +
				"Location: Waterloo Public Square, Waterloo, ON, N2J 1P2 \n\n" +
				"What could be better than spending a relaxing evening listening to the music of some local musicians? This is an evening to enjoy some reggae, rock, pop and country. The event will be a great deal of fun for everyone involved, you�ll be able to spend some time with your family and friends while assisting with raising funds for the Brain Injury Association of Waterloo-Wellington. On top of an evening filled with melodious soothing music, the night will also consist of 50/50 draws, buffet, dancing, fun activities and of course music.  The cost is only $25 and tickets currently on sale on our website. \n\n"+
				"Video: http://www.youtube.com/watch?v=U-iorEGq8OI \n" +
				"Event Website: http://www.kitchener-waterloo.worldweb.com/Events/ConcertsPerformances/ \n\n" +
				"Contact: \n" +
				"Phone: 5195795300 \n" +
				"E-mail: brillianceinmusic@kwcweb.com";
		final String flink1 = "http://www.youtube.com/embed/zkbXTQ95kLc";
		final String flink2 = "http://www.youtube.com/embed/U-iorEGq8OI";
		
		eventID = 1; //getval();
		final Label desclabel1 = new Label(label1);
		final Label desclabel2 = new Label(label2);
		final Label ftitle = new Label("\nCheck out the Video here:\n\n");
		final Frame frame1 = new Frame(flink1);
		final Frame frame2 = new Frame(flink2);
		frame1.setWidth("100%");
		frame1.setHeight("40%");
		frame2.setWidth("100%");
		frame2.setHeight("40%");
		
		final VerticalPanel vp1 = new VerticalPanel();
		final VerticalPanel vp2 = new VerticalPanel();
		final VerticalPanel vp3 = new VerticalPanel();
		final ScrollPanel sp = new ScrollPanel();
		
		ChooseMain.addStyleName("menuBar");
		vp1.add(ChooseMain); vp1.setWidth("100%");
		vp2.add(sp); vp2.setWidth("100%");
		vp3.add(ftitle);
		if (eventID == 1){
			sp.add(desclabel1);
			vp3.add(frame1);
		} else if (eventID == 2){
			sp.add(desclabel2);
			vp3.add(frame2);
		}
		vp3.setWidth("100%");
		
		
		// Associate Main Panel with host page.
		RootPanel.get("menubar").add(vp1);
		RootPanel.get("label").add(vp2);
		RootPanel.get("frames").add(vp3);
		
		
		ChooseMain.setEnabled(true);
		ChooseMain.setFocus(true);
		
		
		ChooseMain.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  String baseurl = GWT.getHostPageBaseURL();
		  		  Window.Location.assign(baseurl+"Waterlooeventfinder2.html");
		      }
		});	
	}
	
	public int getval(){
		String url = Window.Location.getHref();
		String search = "?";
		String result = "";
		int eventID = 0;
		int i;
		i = url.indexOf(search);
		result = url.substring(i+1);
		
		if(result != null){
			eventID = Integer.parseInt(result);
		}
		
		return eventID;
	}
	
}


