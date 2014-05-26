package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

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
	private Label pinnwandVon = new Label("Meine persönliche Pinnwand");
	private Label aboSuche = new Label("Nutzer suchen: ");

	VerwaltungsklasseAsync verwaltung = null;
	
	
	
	
	//
	private Button eintragloeschen = new Button("Eintrag loeschen");
	
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
				
				NutzerCell cell = new NutzerCell();
			    
			    final ListDataProvider<Nutzer> nutzerDataProvider = new ListDataProvider<Nutzer>();
			    final CellList<Nutzer> nutzerCellList;
			    final SingleSelectionModel<Nutzer>  singleSelectionModel;
	
			    ProvidesKey<Nutzer> keyProvider = new ProvidesKey<Nutzer>() {
			            public Object getKey(Nutzer item) {
			                // Always do a null check.
			                return (item == null) ? null : item.getId();
			            }
			        };
			        nutzerCellList = new CellList<Nutzer> (cell, keyProvider);
			        nutzerDataProvider.addDataDisplay(nutzerCellList);
			        nutzerCellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
	
			        singleSelectionModel = new SingleSelectionModel<Nutzer>(keyProvider);
			        nutzerCellList.setSelectionModel(singleSelectionModel);
			        singleSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	
			        	 @Override
			             public void onSelectionChange(SelectionChangeEvent event) {
			                 
			             }

		        });

			        	
		        for(Nutzer n : result){
		        	nutzerDataProvider.getList().add(0, n);
				}
		        
		        nutzerCellList.setStyleName("cellList");
		        abonnentenPanel.add(nutzerCellList);  
		        
		        
		        Button b = new Button("Eintrag löschen", new ClickHandler() {

					//Button Eintrag Löschen
					public void onClick(ClickEvent event) {
						int selectedIndex = nutzerDataProvider.getList().indexOf(singleSelectionModel.getSelectedObject());
						nutzerDataProvider.getList().remove(selectedIndex);
					}
				    });
				abonnentenPanel.add(b); 
		       
				
				MultiWordSuggestOracle vorauswahl = new MultiWordSuggestOracle();
				
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
