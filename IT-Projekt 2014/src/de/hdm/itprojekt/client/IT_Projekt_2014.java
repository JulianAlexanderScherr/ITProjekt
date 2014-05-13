package de.hdm.itprojekt.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
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
		private FlowPanel menueLeiste = new FlowPanel();
		private HorizontalPanel funktionenPanel = new HorizontalPanel();	
		//Anlegen der erforderlichen Button
		private Button pinnwandButton = new Button("Pinnwand");
		private Button reportButton = new Button("Reports");
		private Button sucheButton = new Button("Suche");	
		
		
		
		//reportFunktionsPanel: Panel für Report Erstellung
		//Anlegen der erforderlichen Panel
		private VerticalPanel reportFunktionsPanel = new VerticalPanel();
		private HorizontalPanel reportPanel =new HorizontalPanel();
		private VerticalPanel menuePanel = new VerticalPanel();	
		//Anlegen der erforderlichen Label
		private Label reportLabel = new Label("Report");
		private Label informationLabel = new Label("Information");
		private Label sortierenNachLabel = new Label("sortieren nach");
		private Label vonLabel = new Label("von");
		private Label bisLabel = new Label("bis");	
		private Label reportAusgabeLabel = new Label("Testreport 1 \nTestreport 2 \nTestreport 3 \nusw....................................");
		//Anlegen der erforderlichen ListBox
		private ListBox suchObjekt = new ListBox();
		private ListBox sortierenNach = new ListBox();
		//Anlegen der erfordelrichen DatePicker
		private DatePicker startDatum = new DatePicker();
		private DatePicker endeDatum = new DatePicker();
		
		
		//
		private Label nutzerAusDB = new Label();
		
	public void onModuleLoad() {
		
		
		// Associate the Main panel with the HTML host page.
		RootPanel.get("body_smp").add(basisPanel);
		
		//basisPanel: Aufbau der GUI
		//BasisPanel 		
		basisPanel.addStyleName("basisPanel");
		basisPanel.add(menueLeiste);
		basisPanel.add(funktionenPanel);
		basisPanel.add(reportFunktionsPanel);
		//menueLeiste		
		menueLeiste.add(pinnwandButton);
		menueLeiste.add(reportButton);
		menueLeiste.add(sucheButton);		
		//FunktionenPanel			
		funktionenPanel.addStyleName("funktionenPanel");
		
		
		//reportPanel: Aufbau der GUI
		//reportFunktionsPanel
		reportFunktionsPanel.add(reportLabel);
		reportFunktionsPanel.add(reportPanel);
		//reportPanel
		reportPanel.add(menuePanel);
		reportPanel.add(reportAusgabeLabel);		
		//menuePanel
		//menuePanel:suchObjekt+sortierenNach: ListBox befüllen und sichtbar machen
		suchObjekt.addItem("User");
		suchObjekt.addItem("Likes");
		suchObjekt.addItem("Pinnwand");
		suchObjekt.setVisibleItemCount(1);
		
		sortierenNach.addItem("Monat");
		sortierenNach.addItem("User");
		sortierenNach.addItem("Anzahl");
		sortierenNach.setVisibleItemCount(1);
				
		menuePanel.add(informationLabel);
		menuePanel.add(suchObjekt);
		menuePanel.add(sortierenNachLabel);
		menuePanel.add(sortierenNach);
		menuePanel.add(vonLabel);
		menuePanel.add(startDatum);
		menuePanel.add(bisLabel);
		menuePanel.add(endeDatum);
		
		
		
		//
		VerwaltungsklasseAsync verwaltung = ClientsideSettings.getVerwaltung();
		verwaltung.getNutzerByID(2, new AsyncCallback<Nutzer>(){

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("ERROR");
			}

			@Override
			public void onSuccess(Nutzer result) {
				System.out.println("SUCCESS");
				nutzerAusDB.setText("AAAA");
			}
			
			});
		
		
		menuePanel.add(nutzerAusDB);
	}
	
	class SetVerwaltungCallback implements AsyncCallback<Void> {

	    @Override
	    public void onFailure(Throwable caught) {
	      /*
	       * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
	       */
	      ClientsideSettings.getLogger().severe("Fehlgeschlagen!");
	    }

	    @Override
	    public void onSuccess(Void result) {
	      /*
	       * Wir erwarten diesen Ausgang, wollen aber keine Notifikation ausgeben.
	       */
	    }

	  }
}
