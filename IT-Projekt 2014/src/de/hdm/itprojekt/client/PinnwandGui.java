package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.*;

/**
 * Zentrales Panel für die Pinnwandverwaltung
 * Hier werden folgende Funktionen realisiert und dargestellt: <br>
 * - Nutzer suchen <br>
 * - Nutzer auswählen und dessen  Beiträge ausgeben <br>
 * - Abonnenten-Liste <br>
 * - Abonnement anlegen <br>
 * - Abonnement löschen <br>
 * - Beiträge und Kommentare des eingeloggten Users und dessen Abonnenten ausgeben <br>
 * - Neuen Beitrag erstellen<br>
 * 
 * @author Schwab, Schlitz
 */
public class PinnwandGui extends HorizontalPanel {
	
	//Anlegen der erforderlichen Panel
	private VerticalPanel abonnentenPanel = new VerticalPanel();
	private VerticalPanel neuerBeitragPanel = new VerticalPanel();
	private VerticalPanel beitragsPanel = new VerticalPanel();
	private HorizontalPanel suchenPanel = new HorizontalPanel();
	private HorizontalPanel pinnwand_head = new HorizontalPanel();
	private HorizontalPanel pinnwandPanel = new HorizontalPanel();
	private VerticalPanel suchenErgebnis = new VerticalPanel();
	private VerticalPanel suchErgebnis = new VerticalPanel();
	
	//Anlegen der erforderlichen Buttons
	private Button neuerBeitrag = new Button("+ neuen Beitrag erstellen");
	private Button aboLoeschen = new Button("Abonnement löschen");
	private Button abonnieren = new Button("Abonnieren");

	//Anlegen der erforderlichen Widgets
	private Label abonnenten = new Label("Abonnenten");
	private Label pinnwand = new Label("Pinnwand");
	private Label pinnwandVon = new Label("Meine persönliche Pinnwand");
	private Label aboSuche = new Label("Nutzer suchen: ");
	private Label meineAbos = new Label("Meine Abonnenten:");
	private SuggestBox suchFeld;
	

	//Anlegen des Vectors für alle Beiträge eines Nutzers und dessen Abonnenten
	final Vector<Beitrag> alleBeitraegeCurrentNutzer = new Vector<Beitrag>();
	
	VerwaltungsklasseAsync verwaltung = null;
	
	

	public PinnwandGui(final Nutzer currentNutzer){
		
		this.add(pinnwandPanel);
		
		//funktionenPanel	
		pinnwandPanel.addStyleName("funktionenPanel");
		pinnwandPanel.add(abonnentenPanel);
		pinnwandPanel.add(beitragsPanel);
		
		//verwaltungsPanel
		abonnentenPanel.addStyleName("verwaltungsPanel");
		abonnenten.addStyleName("section_abonnenten");
		suchenErgebnis.setStyleName("suchergebnis");
		meineAbos.setStyleName("meineAbos");
		abonnentenPanel.add(abonnenten);
		abonnentenPanel.add(suchenErgebnis);
		suchenErgebnis.add(suchenPanel);
		suchenErgebnis.add(suchErgebnis);
		suchenErgebnis.add(meineAbos);
		
		//suchPanel
		suchenPanel.addStyleName("suchenPanel");
		suchenPanel.add(aboSuche);
		
		//Abonnieren Buttons
		aboLoeschen.addStyleName("abonnieren");
		abonnieren.addStyleName("abonnieren");
		
		//beitragsPanel
		beitragsPanel.addStyleName("beitragsPanel");
		pinnwand.addStyleName("section_pinnwand");
		pinnwandVon.addStyleName("head_text");
		beitragsPanel.add(pinnwand);
		beitragsPanel.add(pinnwand_head);
		neuerBeitragPanel.setStyleName("neuerBeitragPanel");
		beitragsPanel.add(neuerBeitragPanel);
		
		
		//pinnwand_head
		pinnwand_head.setWidth("100%");
		pinnwand_head.add(pinnwandVon);
		neuerBeitrag.addStyleName("neuerBeitrag");
		pinnwand_head.add(neuerBeitrag);
		
		if (verwaltung == null) {
			verwaltung = ClientsideSettings.getVerwaltung();
		}	   
		
		//Beiträge aller Abonnenten und des aktuellen Nutzers ausgeben
		verwaltung.getAbonnementNutzer(currentNutzer, new AsyncCallback<Vector<Nutzer>>(){
			public void onFailure(Throwable caught) {
			}

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
		        if(result.size() == 0){
		        	Label keineAbos = new Label("Keine Abonnenten vorhanden");
		        	abonnentenPanel.add(keineAbos);
		        }
		        else{
		        	abonnentenPanel.add(nutzerCellList);  
		        }
		        
		        
		        singleSelectionModel.addSelectionChangeHandler(new Handler() {
					//Button Eintrag LÃ¶schen
					public void onSelectionChange(SelectionChangeEvent event) {
						beitragsPanel.clear();
						pinnwand_head.clear();
						pinnwand_head.setWidth("100%");
						pinnwandVon.setText("Pinnwand von "+singleSelectionModel.getSelectedObject().getVorname()+" "+singleSelectionModel.getSelectedObject().getNachname());
						pinnwand_head.add(pinnwandVon);
						neuerBeitrag.addStyleName("neuerBeitrag");
						pinnwand_head.add(aboLoeschen);
						beitragsPanel.add(pinnwand_head);
						suchErgebnis.clear();
						suchFeld.setText("");
						
						//Abonnement loeschen
						aboLoeschen.addClickHandler(new ClickHandler(){
							public void onClick(ClickEvent event) {
								verwaltung.loeschenAbonnement(currentNutzer.getId(), singleSelectionModel.getSelectedObject().getId(), new AsyncCallback<Void>(){
									@Override
									public void onFailure(Throwable caught) {
									}

									@Override
									public void onSuccess(Void result) {
										Window.Location.reload();
									}
									
								});
							}
						});
						//Alle Beiträge eines Nutzers holen
						verwaltung.getBeitraegeByNutzer(singleSelectionModel.getSelectedObject(), new AsyncCallback<Vector<Beitrag>>(){

							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(Vector<Beitrag> result) {
								//BeitrÃ¤ge nach Erstellungszeitpunkt sortieren
								verwaltung.sortBeitraege(result, new AsyncCallback<Vector<Beitrag>>(){
									public void onFailure(Throwable caught) {
									}

									public void onSuccess(Vector<Beitrag> result) {							
										//BeitrÃ¤ge ausgeben
										for(Beitrag b : result){
											BeitragsGui bg = new BeitragsGui(b, currentNutzer, "");
											beitragsPanel.add(bg);
										}
										
										
									}
									
								});
														
							}
							
						});
						
						
					}
			    });
			}
		});

		//Nutzer Suchen
		verwaltung.getAlleNutzer(new AsyncCallback<Vector<Nutzer>>(){
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Vector<Nutzer> result) {
				//Alle Nutzer der Socialmediapinnwand außer dem aktuellen Nutzer werden in die Vorauswahl geschrieben
				MultiWordSuggestOracle vorauswahl = new MultiWordSuggestOracle();
				for(Nutzer n : result){
					if(n.getId() != currentNutzer.getId()){
						vorauswahl.add(n.getVorname() + " " + n.getNachname());
					}
				}

				suchFeld = new SuggestBox(vorauswahl);
				suchenPanel.add(suchFeld);
				
				suchFeld.addKeyDownHandler(new KeyDownHandler() {
				      public void onKeyDown(KeyDownEvent event) {
				    	  if(!suchFeld.getText().isEmpty()){
					          if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						        	  verwaltung.getNutzerByVorname(suchFeld.getText(), new AsyncCallback<Vector<Nutzer>>(){
										@Override
										public void onFailure(Throwable caught) {
										}
							
										@Override
										public void onSuccess(Vector<Nutzer> result) {
												if(result.isEmpty()){
													suchErgebnis.clear();
													Label l = new Label("kein Treffer gefunden");
													suchErgebnis.add(l);
											
											}
											else{
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
											        suchErgebnis.clear();
											        suchErgebnis.add(nutzerCellList);
											        
											        nutzerCellList.addStyleName("suchenNamen");
												    
												    singleSelectionModel.addSelectionChangeHandler(new Handler() {
														//Button Eintrag LÃ¶schen
														public void onSelectionChange(SelectionChangeEvent event) {
															beitragsPanel.clear();
															pinnwand_head.clear();
															pinnwand_head.setWidth("100%");
															pinnwandVon.setText("Pinnwand von "+singleSelectionModel.getSelectedObject().getVorname()+" "+singleSelectionModel.getSelectedObject().getNachname());
															pinnwand_head.add(pinnwandVon);
															neuerBeitrag.addStyleName("neuerBeitrag");
															pinnwand_head.add(abonnieren);
															beitragsPanel.add(pinnwand_head);
															
															//Abonnieren
															abonnieren.addClickHandler(new ClickHandler() {
																public void onClick(ClickEvent event) {
																	Abonnement a = new Abonnement();
																	a.setNutzerID(singleSelectionModel.getSelectedObject().getId());
																	a.setPinnwandID(currentNutzer.getId());
																	verwaltung.createAbonnement(a, new AsyncCallback<Void>(){
																		public void onFailure( Throwable caught) {
																		}

																		public void onSuccess( Void result) {
																			Window.Location.reload();
																		}		
																	});
																}
															});
															
															//Abonnement löschen
															verwaltung.getAbonnementNutzer(currentNutzer, new AsyncCallback<Vector<Nutzer>>(){
																public void onFailure( Throwable caught) {
																}

																public void onSuccess(Vector<Nutzer> result) {
																	for(Nutzer n : result){
																		if(n.getId() == singleSelectionModel.getSelectedObject().getId()){
																			//Abo-Löschen Button anzeigen
																			abonnieren.removeFromParent();
																			pinnwand_head.add(aboLoeschen);
																			//Abonnement loeschen
																			aboLoeschen.addClickHandler(new ClickHandler(){
																				public void onClick(ClickEvent event) {
																					verwaltung.loeschenAbonnement(currentNutzer.getId(), singleSelectionModel.getSelectedObject().getId(), new AsyncCallback<Void>(){
																						@Override
																						public void onFailure(Throwable caught) {
																						}

																						@Override
																						public void onSuccess(Void result) {
																							Window.Location.reload();
																						}
																						
																					});
																				}
																			});
																		}
																	}
																}
																
															});

															
															//Beiträge aufrufen
															verwaltung.getBeitraegeByNutzer(singleSelectionModel.getSelectedObject(), new AsyncCallback<Vector<Beitrag>>(){

																@Override
																public void onFailure(Throwable caught) {
																}

																@Override
																public void onSuccess(Vector<Beitrag> result) {
																	//BeitrÃ¤ge nach Erstellungszeitpunkt sortieren
																	verwaltung.sortBeitraege(result, new AsyncCallback<Vector<Beitrag>>(){
																		public void onFailure(Throwable caught) {
																		}

																		public void onSuccess(Vector<Beitrag> result) {							
																			//BeitrÃ¤ge ausgeben
																			for(Beitrag b : result){
																				BeitragsGui bg = new BeitragsGui(b, currentNutzer, "keinABO");
																				beitragsPanel.add(bg);
																			}
																		}
																		
																	});
																							
																}
																
															});
															
															
														}
												    });
											}
											
										}
									}); 
				        	  }
				          }
				        }
				    });
				}
			});
		
		//Alle Beiträge der Abonnenten sowie des aktuellen Nutzers ausgeben
		verwaltung.getAlleBeitraegeByNutzer(currentNutzer, new AsyncCallback<Vector<Beitrag>>(){
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Vector<Beitrag> result) {
				//Beiträge nach Erstellungszeitpunkt sortieren lassen
				verwaltung.sortBeitraege(result, new AsyncCallback<Vector<Beitrag>>(){
					public void onFailure(Throwable caught) {
					}

					public void onSuccess(final Vector<Beitrag> result) {	
						//Beiträge ausgeben
						for(Beitrag b : result){
							BeitragsGui bg = new BeitragsGui(b, currentNutzer, "");
							beitragsPanel.add(bg);
						}
					}
				});
			}
		});
		
		
		//Neuen Beitrag erstellen
		neuerBeitrag.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				neuerBeitrag.setEnabled(false);
				final TextArea ta = new TextArea();
				final Button beitragErstellen = new Button("Beitrag erstellen");
				neuerBeitragPanel.add(ta);
				neuerBeitragPanel.add(beitragErstellen);
				
				beitragErstellen.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						final Beitrag b = new Beitrag();
						b.setBeitragstext(ta.getText());
						b.setNutzerID(currentNutzer.getId());
						verwaltung.createBeitrag(b, new AsyncCallback<Void>(){
							public void onFailure(Throwable caught) {
							}

							public void onSuccess(Void result) {
								ta.removeFromParent();
								beitragErstellen.removeFromParent();
								BeitragsGui bg = new BeitragsGui(b, currentNutzer, "");
								neuerBeitragPanel.add(bg);
							
							}
							
						});
					}
				});
			}
		});
	}
}
