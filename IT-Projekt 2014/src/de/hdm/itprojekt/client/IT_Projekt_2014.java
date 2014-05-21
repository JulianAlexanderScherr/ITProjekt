package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Anchor;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IT_Projekt_2014 implements EntryPoint {
	
		//Anlegen der erforderlichen Panel
		private VerticalPanel basisPanel = new VerticalPanel();
		private HorizontalPanel menuePanel = new HorizontalPanel();	
		private VerticalPanel funktionenPanel = new VerticalPanel();
		
		
		//Anlegen der erforderlichen Buttons
		private Button pinnwandButton = new Button("zur Pinnwand");
		private Button reportButton = new Button("Reportgenerator");
		
		//Anlegen des Verwaltungsobjekts
		VerwaltungsklasseAsync verwaltung = null;
		
		//LoginServie instantiieren
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		
		//Widgets f�r Login
		private LoginInfo loginInfo = null;
		private VerticalPanel loginPanel = new VerticalPanel();
		private Label loginLabel = new Label("Bitte Loggen Sie sich mit Ihrem Google-Account ein um Social Media Pinnwand nutzen zu können");
		private Anchor signInLink = new Anchor("Einloggen");
		private Anchor signOutLink = new Anchor("Ausloggen");
		private Image icon = new Image("images/smp_icon.png");
		
	public void onModuleLoad() {
		
		//Die im HTML-code angegebene Positionierung mit dem Basis Panel verbinden
		RootPanel.get("body_smp").add(basisPanel);
		
		//BasisPanel 		
		basisPanel.addStyleName("basisPanel");
		basisPanel.add(menuePanel);
		basisPanel.add(loginPanel);
		basisPanel.add(funktionenPanel);
		
		//Bei Klick auf den Button "Reportgenerator" entsprechende GUI laden
		reportButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				//Erstellen der erforderlichen Instanz
				ReportGui rg = new ReportGui();
				funktionenPanel.clear();
				funktionenPanel.add(rg);
				
			}
		});
		
		//Bei Klick auf den Button "Zur Pinnwand" entsprechende GUI laden
		pinnwandButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				//Erstellen der erforderlichen Instanz
				PinnwandGui pg = new PinnwandGui();
				funktionenPanel.clear();
				funktionenPanel.add(pg);
			}
		});	
		
		//Login-Status �berpr�fen
	    LoginServiceAsync loginService = GWT.create(LoginService.class);
	    loginService.login(GWT.getHostPageBaseURL() + "IT_Projekt_2014.html?gwt.codesvr=127.0.0.1:9997", new AsyncCallback<LoginInfo>() {
	  	
	      public void onFailure(Throwable error) {
	      }

	      public void onSuccess(LoginInfo result) {
	        loginInfo = result;
	        if(loginInfo.isLoggedIn()) {
	        	//Men�buttons einblenden
				menuePanel.addStyleName("menue");
				menuePanel.add(pinnwandButton);
				menuePanel.add(reportButton);
				
				//Pinnwand-GUI laden
	        	PinnwandGui pg = new PinnwandGui();
				funktionenPanel.clear();
				funktionenPanel.add(pg);
				
				//Ausloggen-Button einrichten
				signOutLink.setHref(loginInfo.getLogoutUrl());
				signOutLink.setStyleName("logout");
				menuePanel.add(signOutLink);
	        } else {
	          loadLogin();
	        }
	      }
	    });
		    
		//AsyncCallback f�r das Einloggen    
	    AsyncCallback<LoginInfo> acb = new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
			}
 
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					System.out.println("Logged in");
		        	PinnwandGui pg = new PinnwandGui();
					funktionenPanel.clear();
					funktionenPanel.add(pg);
				} else {
					loadLogin();
				}
			}
		};
		loginService.login(GWT.getHostPageBaseURL() + "IT_Projekt_2014.html?gwt.codesvr=127.0.0.1:9997", acb);

		}
	
		//Wenn User nicht eingeloggt ist, dann einloggen lassen
		private void loadLogin() {
			//Login Panel einrichten
			signInLink.setHref(loginInfo.getLoginUrl());
			loginLabel.setStyleName("loginText");
			loginPanel.setStyleName("loginPanel");
			loginPanel.add(loginLabel);
			signInLink.setStyleName("signInLink");
			loginPanel.add(signInLink);
			icon.setStyleName("icon");
			loginPanel.add(icon);
		}
}

