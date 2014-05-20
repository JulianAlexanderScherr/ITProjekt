package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Nutzer;

public class PinnwandGui extends HorizontalPanel {
	
	
	//Anlegen der erforderlichen Panel
	private VerticalPanel abonnentenPanel = new VerticalPanel();
	private VerticalPanel beitragsPanel = new VerticalPanel();
	private HorizontalPanel suchenPanel = new HorizontalPanel();
	private HorizontalPanel pinnwand_head = new HorizontalPanel();
	private HorizontalPanel pinnwandPanel = new HorizontalPanel();
	private VerticalPanel suchErgebnis = new VerticalPanel();
	
	
	//Anlegen der erforderlichen Buttons
	private Button neuerBeitrag = new Button("+ neuen Beitrag erstellen");
	
	//Anlegen der erforderlichen Widgets
	private Label abonnenten = new Label("Abonnenten");
	private Label pinnwand = new Label("Pinnwand");
	private Label pinnwandVon = new Label("Meine persoenliche Pinnwand");
	private Label aboSuche = new Label("Nutzer suchen: ");

	VerwaltungsklasseAsync verwaltung = null;
	
	public PinnwandGui(){
		
		this.add(pinnwandPanel);
		
		//funktionenPanel	
		pinnwandPanel.addStyleName("funktionenPanel");
		pinnwandPanel.add(abonnentenPanel);
		pinnwandPanel.add(beitragsPanel);
		
		//verwaltungsPanel
		abonnentenPanel.addStyleName("verwaltungsPanel");
		abonnenten.addStyleName("section_abonnenten");
		abonnentenPanel.add(abonnenten);
		abonnentenPanel.add(suchenPanel);
		suchErgebnis.setStyleName("suchergebnis");
		suchenPanel.add(suchErgebnis);
		
		//suchPanel
		suchenPanel.addStyleName("suchenPanel");
		suchenPanel.add(aboSuche);
		
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
		
		if (verwaltung == null) {
			verwaltung = ClientsideSettings.getVerwaltung();
		}
		
		verwaltung.getAlleNutzer(new AsyncCallback<Vector<Nutzer>>(){

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("ERROR in getAlleNutzer");
			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
				ListBox userList = new ListBox();
				MultiWordSuggestOracle vorauswahl = new MultiWordSuggestOracle();
				
				for(Nutzer n : result){
					userList.addItem(n.getVorname() + " " + n.getNachname());
					vorauswahl.add(n.getVorname() + " " + n.getNachname());
				}
				userList.setVisibleItemCount(3);
				abonnentenPanel.add(userList);
				final SuggestBox suchFeld = new SuggestBox(vorauswahl);
				suchenPanel.add(suchFeld);
				
				suchFeld.addKeyDownHandler(new KeyDownHandler() {
				      public void onKeyDown(KeyDownEvent event) {
				          if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				        	  verwaltung.getNutzerByVorname(suchFeld.getText(), new AsyncCallback<Vector<Nutzer>>(){
									@Override
									public void onFailure(Throwable caught) {
										System.out.println("ERROR in getNutzerByVorname");
									}
						
									@Override
									public void onSuccess(Vector<Nutzer> result) {
										/**
											suchErgebnis.clear();
											if(result.isEmpty()){
												Label l = new Label(" kein Treffer gefunden");
												suchErgebnis.add(l);
										
										}
										else{
											
												FlexTable ergebnis = new FlexTable();
												int i = 0;
												for(Nutzer n : result){		
													ergebnis.setText(i++, 0, n.getVorname() +" " + n.getNachname());
												}
												suchErgebnis.add(ergebnis);
											
										}
										*/
									}
								}); 
				          }
				        }
				    });

			}
			
			});
		
	}
}
