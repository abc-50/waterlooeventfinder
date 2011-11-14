/**
 * 
 */
package com.waterlooeventfinder2.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Login page content
 * @author Mike
 *
 */
public class LoginContent extends Content {

	public LoginContent() {
		FormPanel form = new FormPanel();

//		// set action 
//		//https://google-web-toolkit.googlecode.com/svn/javadoc/1.6/com/google/gwt/user/client/ui/FormPanel.html
//
//		VerticalPanel vpanel = new VerticalPanel();
//		form.setWidget(vpanel);
//
//		// Add a 'submit' button.
//		vpanel.add(new Button("Submit", new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				form.submit();
//			}
//		}));
//
//		// Add an event handler to the form.
//	    form.addSubmitHandler(new FormPanel.SubmitHandler() {
//	        public void onSubmit(SubmitEvent event) {
//	          // This event is fired just before the form is submitted. We can take
//	          // this opportunity to perform validation.
//	          if (tb.getText().length() == 0) {
//	            Window.alert("The text box must not be empty");
//	            event.cancel();
//	          }
//	        }
//	      });
//	    
//	    panel.add(vpanel);
	}

}
