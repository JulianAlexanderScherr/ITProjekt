package de.hdm.itprojekt.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Nutzer;

/**
 * In der KommentarGui werden Kommentare zu dem dazugehörigen Beiträgen ausgegeben und durch Funktionen ergänzt<br>
 * Funktionen: <br>
 * - Kommentare löschen, ändern
 * - Funktionsrestriktionen 
 * @author Schwab
 *
 */
public class KommentarGui  extends VerticalPanel {

		//Anlegen der erforderlichen Panel
		private VerticalPanel einzelKommentarPanel = new VerticalPanel();
		private FlowPanel kInformationsPanel = new FlowPanel();
		private FlowPanel kFunktionenPanel = new FlowPanel();
		private VerticalPanel textPanel = new VerticalPanel();
		
		//Anlegen der erforderlichen Label
		private Label kommentarText = new Label(" ");
		private Label kNutzerName = new Label(" ");
		private Label kDatum = new Label(" ");
		private Label kUhrzeit = new Label(" ");
		
		//Anlegen der erforderlichen Buttons
		private Button kommentarLoeschen = new Button("löschen");
		private Button kommentarAendern = new Button("ändern");
		
		VerwaltungsklasseAsync verwaltung = null;
		
		public KommentarGui(final Kommentar k, Nutzer currentNutzer){
			
			this.add(einzelKommentarPanel);
			
			//Panel und Widgets zuweisen
			einzelKommentarPanel.setStyleName("einzelBeitragsPanel");
			kInformationsPanel.setStyleName("informationsPanel");
			einzelKommentarPanel.add(kInformationsPanel);
			textPanel.setStyleName("textPanel");
			einzelKommentarPanel.add(textPanel);	
			kommentarText.setStyleName("beitragsText");
			einzelKommentarPanel.add(kommentarText);
			kFunktionenPanel.setStyleName("funktionenPanel");
			einzelKommentarPanel.add(kFunktionenPanel);
			
			kNutzerName.setStyleName("nutzerName");
			kInformationsPanel.add(kNutzerName);
			kDatum.setStyleName("datum");
			kInformationsPanel.add(kDatum);
			kUhrzeit.setStyleName("uhrzeit");
			kInformationsPanel.add(kUhrzeit);
			
			kFunktionenPanel.add(kommentarLoeschen);
			kFunktionenPanel.add(kommentarAendern);
			
			//Fremde Kommentare dürfen nicht verändert oder gelöscht werden
			if(currentNutzer.getId() != k.getNutzerID()){
				kommentarLoeschen.setVisible(false);
				kommentarAendern.setVisible(false);
				}
			
			//Verwaltung instaziieren
			if (verwaltung == null) {
				verwaltung = ClientsideSettings.getVerwaltung();
			}
			
			//Kommentartext setzen
			kommentarText.setText(k.getKommentartext());

			//Nutzer-Namen setzen
			verwaltung.getNutzerByID(k.getNutzerID(), new AsyncCallback<Nutzer>(){

				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(Nutzer result) {
					for(int i=0; i<1; i++){
						kNutzerName.setText(result.getVorname() + " " + result.getNachname());
					}
				}
			});
			
			//Timestamp konvertieren
			Date d = new Date();
			d.setTime(k.getErstellungszeitpunkt().getTime());
			
			//Zeitformat erstellen
			DateTimeFormat kDatumFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
			DateTimeFormat kUhrzeitFormat = DateTimeFormat.getFormat("HH:mm");
			
			//Kommentardatum setzen
			kDatum.setText(kDatumFormat.format(d, TimeZone.createTimeZone(0)));
			
			//Kommentaruhrzeit setzen
			kUhrzeit.setText(kUhrzeitFormat.format(d, TimeZone.createTimeZone(-120)) + " Uhr");
			
			//Kommentar Löschen
			kommentarLoeschen.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					verwaltung.loeschenKommentar(k, new AsyncCallback<Void>(){
						@Override
						public void onFailure(Throwable caught) {
						}
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Kommentar wurde gelöscht");
							einzelKommentarPanel.removeFromParent();
						}
					});
				}});
			
			//Kommentar ändern
			kommentarAendern.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					kommentarAendern.setEnabled(false);
					
					final TextArea ta = new TextArea();
					final Button kAendern = new Button("Kommentar ändern");

					kommentarText.removeFromParent();
					ta.setText(k.getKommentartext());
					textPanel.add(ta);
					
					kAendern.setStyleName("aendernButton");
					textPanel.add(kAendern);
					
					kAendern.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							k.setKommentartext(ta.getText());
							verwaltung.updateKommentar(k, new AsyncCallback<Void>(){
								public void onFailure(Throwable caught) {
								}

								public void onSuccess(Void result) {
									ta.removeFromParent();
									kAendern.removeFromParent();
									kommentarText.setText(ta.getText());
									textPanel.add(kommentarText);
									kommentarAendern.setEnabled(true);
								}
							});
						}
					});
				}
			});
		}
	}