package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.LoginService;
import de.hdm.itprojekt.shared.LoginServiceAsync;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.<br><br>
 * 
 * Einstiegsklasse des Projekts. Hier werden die grundlegenen Panel angelegt und mit der
 * HTML verbunden. Zudem wird hier das Einloggen des Nutzers und das Ausloggen realisiert. <br>
 * Außerdem werden die Buttons angelegt, welche im Reportgenerator und in der SocialMediaPinnwand
 * immer im Header sichtbar sind.
 */
public class IT_Projekt_2014 implements EntryPoint {
	
		//Anlegen der erforderlichen Panel
		private VerticalPanel basisPanel = new VerticalPanel();
		private HorizontalPanel menuePanel = new HorizontalPanel();	
		private VerticalPanel funktionenPanel = new VerticalPanel();
		
		
		//Anlegen der erforderlichen Buttons für das menuePanel
		private Button pinnwandButton = new Button("zur eigenen Pinnwand");
		private Button nutzerVerwaltungButton = new Button("Profil verwalten");
		private Button reportButton = new Button("Reportgenerator");
		private Button zumLogin = new Button("zum Login");
		
		//Anlegen des Verwaltungsobjekts
		VerwaltungsklasseAsync verwaltung = null;
		
		//LoginServie instantiieren
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		
		//Widgets für Login
		private LoginInfo loginInfo = null;
		private VerticalPanel loginPanel = new VerticalPanel();
		private Label loginLabel = new Label("Bitte Loggen Sie sich mit Ihrem Google-Account ein um die Social Media Pinnwand nutzen zu können");
		private Label reportLabel = new Label("Reportausgaben einzelner oder aller Nutzer über Beiträge, Kommentare und Likes. Eine Reportausgabe kann anhand von Erstellungszeiten sortiert werden");
		private Anchor signInLink = new Anchor("Einloggen");
		private Anchor signOutLink = new Anchor("Ausloggen");
		private Image icon = new Image("images/smp_icon.png");
		
	/**
	* Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	* zusichert, benötigen wir eine Methode
	* <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	* <code>main()</code>-Methode normaler Java-Applikationen.
	*/	
	public void onModuleLoad() {
		
		//Die im HTML-code angegebene Positionierung mit dem Basis Panel verbinden
		RootPanel.get("body_smp").add(basisPanel);
		
		//BasisPanel 		
		basisPanel.addStyleName("basisPanel");
		basisPanel.add(menuePanel);
		basisPanel.add(loginPanel);
		basisPanel.add(funktionenPanel);
		
		
		//Login-Status überprüfen
	    LoginServiceAsync loginService = GWT.create(LoginService.class);
	    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
	  	
	      public void onFailure(Throwable error) {
	      }

	      public void onSuccess(LoginInfo result) {
	        loginInfo = result;
	        if(loginInfo.isLoggedIn()) {
	        	//Menübuttons einblenden
				menuePanel.addStyleName("menue");
				menuePanel.add(pinnwandButton);
				menuePanel.add(nutzerVerwaltungButton);
				
				//Ausloggen-Button einrichten
				signOutLink.setHref(loginInfo.getLogoutUrl());
				signOutLink.setStyleName("logout");
				menuePanel.add(signOutLink);


	        } else {
	          loadLogin();
	        }
	      }
	    });
		    
		//AsyncCallback für das Einloggen    
	    AsyncCallback<LoginInfo> acb = new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
			}
 
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					NutzerVerwaltung nv = new NutzerVerwaltung("start");
					funktionenPanel.clear();
					funktionenPanel.add(nv);
					
				} else {
					loadLogin();
				}
			}
		};
		loginService.login(GWT.getHostPageBaseURL(), acb);
		
		

		
		//Bei Klick auf den Button "Zur Pinnwand" entsprechende GUI laden
		pinnwandButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//Erstellen der erforderlichen Instanz und an das funktionenPanel anhängen
				NutzerVerwaltung nv = new NutzerVerwaltung("start");
				funktionenPanel.clear();
				funktionenPanel.add(nv);
			}
		});	
				
		nutzerVerwaltungButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//Erstellen der erforderlichen Instanz und an das funktionenPanel anhängen
				NutzerVerwaltung nv = new NutzerVerwaltung(" ");
				funktionenPanel.clear();
				funktionenPanel.add(nv);
			}
		});	
		
	}
	
	
	
		//Wenn User nicht eingeloggt ist, dann einloggen lassen
		private void loadLogin() {
			//Login Panel einrichten
			signInLink.setHref(loginInfo.getLoginUrl());
			loginLabel.setStyleName("loginText");
			reportLabel.setStyleName("reportText");
			loginPanel.setStyleName("loginPanel");
			signInLink.setStyleName("signInLink");
			loginPanel.add(loginLabel);
			loginPanel.add(signInLink);
			loginPanel.add(reportLabel);
			loginPanel.add(reportButton);
			icon.setStyleName("icon");
			loginPanel.add(icon);
					
			
			/**
			 * Um die PinnwandGui und die Gui des Reports zu trennen wird der Report gleich vor dem Login aufgerufen.
			 * So wird verhindert das der Report in der PinnwandGui ausgeführt werden kann.
			 */
			
			
			//Bei Klick auf den Button "Reportgenerator" entsprechende GUI laden
			reportButton.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					//Erstellen der erforderlichen Instanz und an das basisPanel anhängen
					ReportGui rg = new ReportGui();
					basisPanel.clear();	
					zumLogin.setStyleName("zumLogin");
					basisPanel.add(zumLogin);					
					basisPanel.add(rg);
					
				}
			});
			//Beim klick auf den Button "zumLogin" wird man wieder auf die Login Seite geleitet
			zumLogin.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					basisPanel.clear();
					basisPanel.add(loginPanel);				
				}
			});
		}
}

