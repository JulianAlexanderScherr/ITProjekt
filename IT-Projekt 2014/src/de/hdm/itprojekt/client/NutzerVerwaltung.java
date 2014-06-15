package de.hdm.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.*;

/**
 * Klasse zur Verwaltung der Nutzerdaten wie Name, Nachname und Nickname<br>
 * Wenn sich ein neuer Nutzer einloggt, müssen zuerst die nötigen Daten eingegeben werden.<br>
 * Wenn ein Nuter sich danach wieder einloggt, wird der Nutzer direkt auf die Pinnwand geleitet
 * @author Martin
 *
 */
public class NutzerVerwaltung extends VerticalPanel {
	
	//Anlegen der benötigten Panel
	private VerticalPanel nutzerVerwaltung = new VerticalPanel();
	private HorizontalPanel infoLabel = new HorizontalPanel();
	private VerticalPanel textBoxes = new VerticalPanel();
	private VerticalPanel textLabels = new VerticalPanel();
	private FlowPanel buttonLabel = new FlowPanel();
	
	//Anlegen der benötigten Labels
	private Label nVerwaltung = new Label("Nutzer Verwaltung");
	private Label vorname = new Label("Vorname: ");
	private Label nachname = new Label("Nachname: ");
	private Label nickname = new Label("Nickname: ");
	
	//Anlegen der benötigten Buttons
	private Button aendern = new Button("Ändern");
	
	//Anlegen der benötigten Textareas
	private TextBox tVorname = new TextBox();
	private TextBox tNachname = new TextBox();
	private TextBox tNickname = new TextBox();
	
	//String E-Mail
	public String eMail = "";
	
	//Aktueller Nutzer
	public Nutzer aktuellerNutzer = new Nutzer();
	
	//Ist Nutzer neu?
	public Boolean ifNew = true;

	VerwaltungsklasseAsync verwaltung = null;
	
	public NutzerVerwaltung(final String s){
		
		this.add(nutzerVerwaltung);
		
		if (verwaltung == null) {
			verwaltung = ClientsideSettings.getVerwaltung();
		}

		//Aktuellen Nutzer als Objekt holen
		verwaltung.getCurrentUserMail(new AsyncCallback<String>(){
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(String result) {
				eMail = result;
				verwaltung.checkEmail(eMail, new AsyncCallback<Nutzer>(){
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(final Nutzer result) {
						if(result != null){
							if(s == "start"){
								PinnwandGui pg = new PinnwandGui(result);
								nutzerVerwaltung.clear();
								nutzerVerwaltung.add(pg);
							}
							else{
								NutzerVerwaltung.this.aktuellerNutzer = result;
								ifNew = false;
								tVorname.setText(result.getVorname());
								tNachname.setText(result.getNachname());
								tNickname.setText(result.getNickname());
							}
						}
						//Wenn Nutzer eingetragen ist, werden dessen Daten bereitgestellt
						if((s != "start") || (result == null)){
							nutzerVerwaltung.setStyleName("nutzerVerwaltung");
							nVerwaltung.setStyleName("nVerwaltung");
							nutzerVerwaltung.add(nVerwaltung);
							nutzerVerwaltung.add(infoLabel);
							nutzerVerwaltung.add(buttonLabel);
							
							infoLabel.setStyleName("infoLabel");
							infoLabel.add(textLabels);
							infoLabel.add(textBoxes);
								
							textLabels.setStyleName("textLabels");
							textLabels.add(vorname);
							textLabels.add(nachname);
							textLabels.add(nickname);
							
							textBoxes.setStyleName("textBoxes");
							textBoxes.add(tVorname);
							textBoxes.add(tNachname);
							textBoxes.add(tNickname);
							
							if(result == null){
								aendern.setText("Neuen Nutzer anlegen");
							}
							
							buttonLabel.setStyleName("buttonLabel");
							buttonLabel.setStyleName("buttonLabel");
							buttonLabel.add(aendern);
							
							//Nutzer ändern/erstellen
							aendern.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
									if(ifNew == true){
										//Neuen Nutzer erstellen
										final Nutzer n = new Nutzer();
										n.setVorname(tVorname.getText());
										n.setNachname(tNachname.getText());
										n.setNickname(tNickname.getText());
										n.seteMail(eMail);
										
										verwaltung.createNutzer(n, new AsyncCallback<Void>(){
											public void onFailure(Throwable caught) {
											}

											public void onSuccess(Void result) {
												System.out.println("Neuer Nutzer angelegt");
												PinnwandGui pg = new PinnwandGui(n);
												nutzerVerwaltung.clear();
												nutzerVerwaltung.add(pg);
											}
											
										});
									}
									else{
										//Nutzer aktualisieren
										aktuellerNutzer.setNachname(tVorname.getText());
										aktuellerNutzer.setVorname(tNachname.getText());
										aktuellerNutzer.setNickname(tNickname.getText());
										verwaltung.updateNutzer(aktuellerNutzer, new AsyncCallback<Void>(){
											public void onFailure(Throwable caught) {
											}

											public void onSuccess(Void result) {
												System.out.println("Nutzer geupdatet");
												PinnwandGui pg = new PinnwandGui(aktuellerNutzer);
												nutzerVerwaltung.clear();
												nutzerVerwaltung.add(pg);
											}
											
										});
									}
									
								}
							});
						}
					}
				});
			}  
		});

	}
}
