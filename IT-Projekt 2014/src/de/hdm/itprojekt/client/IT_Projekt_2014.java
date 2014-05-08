package de.hdm.itprojekt.client;

import de.hdm.itprojekt.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IT_Projekt_2014 implements EntryPoint {

	//Basis Platzhalter
	private VerticalPanel basisPanel = new VerticalPanel();
	private HorizontalPanel funktionenPanel = new HorizontalPanel();
	
	//ZUM TESTEN
	private Label basisPanelLabel = new Label();
	private Label funktionenPanelLabel = new Label();	
	
	public void onModuleLoad() {
	
		//ZUM TESTEN
		basisPanelLabel.setText("Basis Panel");
		funktionenPanelLabel.setText("Funktionen Panel");
		
		
		//
		funktionenPanel.add(funktionenPanelLabel);
		funktionenPanel.addStyleName("funktionenPanel");
		
		basisPanel.add(basisPanelLabel);
		basisPanel.add(funktionenPanel);
		basisPanel.addStyleName("basisPanel");
		
		
		// Associate the Main panel with the HTML host page.
		RootPanel.get("body_smp").add(basisPanel);
	}
	
}
