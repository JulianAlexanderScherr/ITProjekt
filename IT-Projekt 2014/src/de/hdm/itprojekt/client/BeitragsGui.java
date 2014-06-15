package de.hdm.itprojekt.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.*;

/**
 * Die Klasse BeitragsGui ist für die Darstellung der Beitröge verantwortlich. <br>
 * Hierbei werden folgende Funktionen realisiert und dargestellt: <br>
 * - Funktionsrestriktionen<br>
 * - Beitrags-Informationen zuweisen<br>
 * - Like-Funktionen
 * - Beiträge ändern, löschen
 * - Kommentar schreiben
 * @author Schwab
 *
 */
public class BeitragsGui  extends VerticalPanel {

	//Anlegen der erforderlichen Panel
	private VerticalPanel beitragsPanel = new VerticalPanel();
	private VerticalPanel einzelBeitragsPanel = new VerticalPanel();
	private FlowPanel informationsPanel = new FlowPanel();
	private VerticalPanel textPanel = new VerticalPanel();
	private HorizontalPanel funktionenPanel = new HorizontalPanel();
	private VerticalPanel kommentarPanel = new VerticalPanel();
	private VerticalPanel kommentierenPanel = new VerticalPanel();
	
	//Anlegen der erforderlichen Label
	private Label beitragsText = new Label(" ");
	private Label nutzerName = new Label(" ");
	private Label datum = new Label(" ");
	private Label uhrzeit = new Label(" ");
	private Label likes = new Label(" ");
	
	//Anlegen der erforderlichen Buttons
	private Button kommentieren = new Button("Kommentieren");
	private Button liken = new Button("Gefällt mir");
	private Button entliken = new Button("Gefällt mir nicht mehr");
	private Button beitragAendern = new Button("ändern");
	private Button beitragLoeschen = new Button("löschen");
	
	//Kommentieren-Feld anlegen
	private TextArea kommentierenFeld = new TextArea();
	
	VerwaltungsklasseAsync verwaltung = null;
	
	public BeitragsGui(final Beitrag b, final Nutzer currentNutzer, String s){
		
		this.add(beitragsPanel);
		
		//Panel und Widgets zuweisen
		beitragsPanel.add(einzelBeitragsPanel);
		kommentarPanel.setStyleName("kommentarPanel");
		beitragsPanel.add(kommentierenPanel);
		beitragsPanel.add(kommentarPanel);
		
		einzelBeitragsPanel.setStyleName("einzelBeitragsPanel");
		informationsPanel.setStyleName("informationsPanel");
		einzelBeitragsPanel.add(informationsPanel);
		textPanel.setStyleName("textPanel");
		einzelBeitragsPanel.add(textPanel);	
		beitragsText.setStyleName("beitragsText");
		textPanel.add(beitragsText);
		funktionenPanel.setStyleName("funktionenPanel");
		einzelBeitragsPanel.add(funktionenPanel);
		
		nutzerName.setStyleName("nutzerName");
		informationsPanel.add(nutzerName);
		datum.setStyleName("datum");
		informationsPanel.add(datum);
		uhrzeit.setStyleName("uhrzeit");
		informationsPanel.add(uhrzeit);
		
		likes.setStyleName("likes");
		funktionenPanel.add(likes);
		funktionenPanel.add(kommentieren);

		//Verwaltung instaziieren
		if (verwaltung == null) {
			verwaltung = ClientsideSettings.getVerwaltung();
		}
		
		//Fremde Kommentare dürfen nicht verändert oder gelöscht werden
		if(currentNutzer.getId() != b.getNutzerID()){
			beitragAendern.setVisible(false);
			beitragLoeschen.setVisible(false);
		}
		
		//Beitragstext setzen
		beitragsText.setText(b.getBeitragstext());
		
		//Nutzer-Namen setzen
		verwaltung.getNutzerByID(b.getNutzerID(), new AsyncCallback<Nutzer>(){
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Nutzer result) {
				nutzerName.setText(result.getVorname() + " " + result.getNachname());
			}
			
		});
		
		//Timestamp konvertieren
		Date d = new Date();
		d.setTime(b.getErstellungszeitpunkt().getTime());
		//Zeitformat erstellen
		DateTimeFormat datumFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
		DateTimeFormat uhrzeitFormat = DateTimeFormat.getFormat("HH:mm");
		
		//Beitragsdatum setzen
		datum.setText(datumFormat.format(d, TimeZone.createTimeZone(0)));
		
		//Beitragsuhrzeit setzen
		uhrzeit.setText(uhrzeitFormat.format(d, TimeZone.createTimeZone(-120)) + " Uhr");
		
		
		//Nicht-Abonnierte Beitrage dürfen keine Funktionen bereitstellen
		if(s == "keinABO"){
			verwaltung.getAbonnementByNutzer(currentNutzer, new AsyncCallback<Vector<Abonnement>>(){
				public void onFailure(Throwable caught) {
				}
				
				public void onSuccess(Vector<Abonnement> result) {
					for(Abonnement a : result){
						if(a.getNutzerID() != b.getNutzerID()){
							beitragAendern.setVisible(false);
							beitragLoeschen.setVisible(false);
							kommentieren.setVisible(false);
							liken.setVisible(false);
						}
						else{
							kommentieren.setVisible(true);
							liken.setVisible(true);
							return;
						}
					}
				}
				
			});
		}
		
		//Like oder entliken Button setzen
		verwaltung.getLikeByBeitrag(b, new AsyncCallback<Vector<Like>>(){
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Like> result) {
				
				int counter = 0;
				for(Like l : result){
					if(l.getNutzerID() != currentNutzer.getId()){
						counter++;						
						}						
						
					}
					if(counter == result.size()){
						funktionenPanel.add(liken);
						funktionenPanel.add(beitragAendern);
						funktionenPanel.add(beitragLoeschen);
						
					}
					else {
						funktionenPanel.add(entliken);
						funktionenPanel.add(beitragAendern);
						funktionenPanel.add(beitragLoeschen);
					}
			}
		});
		
		
		//Likes anzeigen
		verwaltung.getLikeByBeitrag(b, new AsyncCallback<Vector<Like>>(){
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Like> result) {
				if(result.size() == 0){
					likes.setWidth("137px");
					
				}
				else if(result.size() == 1){
					likes.setText("einer Person gefällt das");
				}
				else{
					likes.setText(String.valueOf(result.size()) + " Personen gefällt das");
				}
			}
		});
		
		//Kommentare laden
		verwaltung.getKommentarByBeitrag(b, new AsyncCallback<Vector<Kommentar>>(){

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Kommentar> result) {
				for(Kommentar k : result){
					KommentarGui kg = new KommentarGui(k, currentNutzer);
					kommentarPanel.add(kg);
				}
			}
		});
	
		
		
		//Kommentar erstellen
		kommentieren.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
					final Button bKommentieren = new Button("Kommentar absenden");
					kommentierenPanel.add(kommentierenFeld);
					bKommentieren.setStyleName("beitragKommentieren");
					kommentierenPanel.add(bKommentieren);
					kommentieren.setEnabled(false);
					
					//Kommentar absenden
					bKommentieren.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							final Kommentar k = new Kommentar();
							k.setBeitragID(b.getId());
							k.setNutzerID(currentNutzer.getId());
							k.setKommentartext(kommentierenFeld.getText());
							verwaltung.createKommentar(k, new AsyncCallback<Void>(){
								public void onFailure(Throwable caught) {
								}

								public void onSuccess(Void result) {
									KommentarGui kg = new KommentarGui(k, currentNutzer);
									kommentarPanel.add(kg);
									bKommentieren.removeFromParent();
									kommentierenFeld.removeFromParent();
									kommentieren.setEnabled(true);
								}
							});
						}
					});
				}
		});
		
		//Beitrag ändern
		beitragAendern.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				beitragAendern.setEnabled(false);
				
				final TextArea ta = new TextArea();
				final Button bAendern = new Button("Beitrag ändern");

				beitragsText.removeFromParent();
				ta.setText(b.getBeitragstext());
				textPanel.add(ta);
				
				bAendern.setStyleName("aendernButton");
				textPanel.add(bAendern);
				
				bAendern.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						b.setBeitragstext(ta.getText());
						verwaltung.updateBeitrag(b, new AsyncCallback<Void>(){
							public void onFailure(Throwable caught) {
							}

							public void onSuccess(Void result) {
								ta.removeFromParent();
								bAendern.removeFromParent();
								beitragsText.setText(ta.getText());
								textPanel.add(beitragsText);
								beitragAendern.setEnabled(true);
							}
						});
					}
				});
			}
		});
		
		//Beitrag löschen
		beitragLoeschen.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				verwaltung.loeschenBeitrag(b, new AsyncCallback<Void>(){
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Beitrag wurde gelöscht");
						einzelBeitragsPanel.removeFromParent();
					}

				});
		}});
		
		//Like entfernen 
		entliken.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				verwaltung.getLikeByBeitrag(b, new AsyncCallback<Vector<Like>>(){

					@Override
					public void onFailure(Throwable caught) {
					
					}

					@Override
					public void onSuccess(Vector<Like> result) {
						for(Like i : result){
							if(i.getNutzerID()==currentNutzer.getId()){
								verwaltung.unlikeBeitrag(i, new AsyncCallback<Void>(){

									@Override
									public void onFailure(Throwable caught) {
								
									}

									@Override
									public void onSuccess(Void result) {
										entliken.removeFromParent();
										beitragAendern.removeFromParent();
										beitragLoeschen.removeFromParent();
										funktionenPanel.add(liken);
										funktionenPanel.add(beitragAendern);
										funktionenPanel.add(beitragLoeschen);
										
										verwaltung.getLikeByBeitrag(b, new AsyncCallback<Vector<Like>>(){
											@Override
											public void onFailure(Throwable caught) {
											}

											@Override
											public void onSuccess(Vector<Like> result) {
												if(result.size() == 0){
													likes.setWidth("135px");
													likes.setText("");
													
												}
												else if(result.size() == 1){
													likes.setText("einer Person gefällt das");
												}
												else{
													likes.setText(String.valueOf(result.size()) + " Personen gefällt das");
												}
											}
										});
										
									}
									
								});
							}
						}
					}
						
					
					
				});
				
			}
			
		});
		
		//Beitrag Liken
		liken.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				verwaltung.likeBeitrag(b, currentNutzer, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						
					}

					@Override
					public void onSuccess(Void result) {
						verwaltung.getLikeByBeitrag(b, new AsyncCallback<Vector<Like>>(){
							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(Vector<Like> result) {
							
								if(result.size() == 0){
									likes.setWidth("135px");
								}
								else if(result.size() == 1){
									likes.setText("einer Person gefällt das");
								}
								else{
									likes.setText(String.valueOf(result.size()) + " Personen gefällt das");
								}
								
								liken.removeFromParent();
								beitragAendern.removeFromParent();
								beitragLoeschen.removeFromParent();
								funktionenPanel.add(entliken);
								funktionenPanel.add(beitragAendern);
								funktionenPanel.add(beitragLoeschen);
							}
						});
					}
				});
			}
		});
	}
}
