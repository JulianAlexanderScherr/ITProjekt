package de.hdm.itprojekt.client;


import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.server.ServersideSettings;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IT_Projekt_2014 implements EntryPoint {

	//Basis Platzhalter
	private VerticalPanel basisPanel = new VerticalPanel();
	private HorizontalPanel funktionenPanel = new HorizontalPanel();

	
	// TEST DB INSERT
	private Button testButton = new Button("Button 1 Test");
	
	public void onModuleLoad() {
		
		//
		funktionenPanel.add(testButton);
		funktionenPanel.addStyleName("funktionenPanel");
		
		basisPanel.add(funktionenPanel);
		basisPanel.addStyleName("basisPanel");
		
		
		// Associate the Main panel with the HTML host page.
		RootPanel.get("body_smp").add(basisPanel);
		
		
		testButton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  	System.out.println("Button gedrueckt");
		      }
		});
	}
}
