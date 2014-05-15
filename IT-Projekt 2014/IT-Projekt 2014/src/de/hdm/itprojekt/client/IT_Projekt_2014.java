package de.hdm.itprojekt.client;


import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Nutzer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IT_Projekt_2014 implements EntryPoint {
		//Anlegen der erforderlichen Panel
		private VerticalPanel basisPanel = new VerticalPanel();
		private HorizontalPanel menuePanel = new HorizontalPanel();
		private HorizontalPanel funktionenPanel = new HorizontalPanel();	
		private VerticalPanel verwaltungsPanel = new VerticalPanel();
		private VerticalPanel beitragsPanel = new VerticalPanel();
		private HorizontalPanel suchenPanel = new HorizontalPanel();	
		private HorizontalPanel pinnwand_head = new HorizontalPanel();
		
		//Anlegen der erforderlichen Buttons
		private Button pinnwandButton = new Button("zur Pinnwand");
		private Button reportButton = new Button("Reportgenerator");
		private Button eigenePinnwand = new Button("Eigene Pinnwand");
		private Button neuerBeitrag = new Button("+ neuen Beitrag erstellen");
		
		//Anlegen der erforderlichen Widgets
		private TextBox suchFeld = new TextBox();
		private Label abonnenten = new Label("Abonnenten");
		private Label pinnwand = new Label("Pinnwand");
		private Label pinnwandVon = new Label("Meine persoenliche Pinnwand");
		private DatePicker datePicker = new DatePicker();
		
		
		//
		private Label nutzerAusDB = new Label("ERROR");
		VerwaltungsklasseAsync verwaltung = null;
		
	public void onModuleLoad() {
		
		RootPanel.get("body_smp").add(basisPanel);
		
		//BasisPanel 		
		basisPanel.addStyleName("basisPanel");
		basisPanel.add(menuePanel);
		basisPanel.add(funktionenPanel);

		//menuePanel
		menuePanel.addStyleName("menue");
		menuePanel.add(pinnwandButton);
		menuePanel.add(reportButton);
		
		//funktionenPanel	
		funktionenPanel.addStyleName("funktionenPanel");
		funktionenPanel.add(verwaltungsPanel);
		funktionenPanel.add(beitragsPanel);
		
		//verwaltungsPanel
		verwaltungsPanel.addStyleName("verwaltungsPanel");
		abonnenten.addStyleName("section_abonnenten");
		verwaltungsPanel.add(abonnenten);
		verwaltungsPanel.add(suchenPanel);
		verwaltungsPanel.add(datePicker);

		
		
		//suchPanel
		suchenPanel.addStyleName("suchenPanel");
		suchenPanel.add(eigenePinnwand);
		suchFeld.setValue(" Abonnenten suchen...");
		suchenPanel.add(suchFeld);
		
		//beitragsPanel
		beitragsPanel.addStyleName("beitragsPanel");
		pinnwand.addStyleName("section_pinnwand");
		pinnwandVon.addStyleName("head_text");
		beitragsPanel.add(pinnwand);
		beitragsPanel.add(pinnwand_head);
		
		
		//pinnwand_head
		pinnwand_head.setWidth("100%");
		pinnwand_head.add(pinnwandVon);
		neuerBeitrag.addStyleName("neuerBeitrag");
		pinnwand_head.add(neuerBeitrag);
		
		
		
		
		//		
		if (verwaltung == null) {
			verwaltung = ClientsideSettings.getVerwaltung();
		}
		

		verwaltung.getNutzerByID(1, new AsyncCallback<Nutzer>(){

			@Override
			public void onFailure(Throwable caught) {
				beitragsPanel.add(nutzerAusDB);
			}

			@Override
			public void onSuccess(Nutzer result) {
				nutzerAusDB.setText(result.getVorname() + " " + result.getNachname());
				beitragsPanel.add(nutzerAusDB);
			}
			
			});
		
		
		
	}
	
}
