package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Anchor;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Nutzer;

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
		
		//
		VerwaltungsklasseAsync verwaltung = null;
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		
		private LoginInfo loginInfo = null;
		private VerticalPanel loginPanel = new VerticalPanel();
		private Label loginLabel = new Label("Please sign in to your Google Account to access the StockWatcher application.");
		private Anchor signInLink = new Anchor("Sign In");
		private Anchor signOutLink = new Anchor("Sign Out");
		
	public void onModuleLoad() {
		
		RootPanel.get("body_smp").add(basisPanel);
		
		
		//BasisPanel 		
		basisPanel.addStyleName("basisPanel");
		basisPanel.add(menuePanel);
		basisPanel.add(loginPanel);
		basisPanel.add(funktionenPanel);
		
		
		reportButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				System.out.println("test");
				//Erstellen der erforderlichen Instanz
				ReportGui rg = new ReportGui();
				
				funktionenPanel.clear();
				
				funktionenPanel.add(rg);
				
			}
		});
		
		pinnwandButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				System.out.println("test");
				//Erstellen der erforderlichen Instanz
				PinnwandGui pg = new PinnwandGui();
				funktionenPanel.clear();
				funktionenPanel.add(pg);
			}
		});	
		
		// Check login status using login service.
		    LoginServiceAsync loginService = GWT.create(LoginService.class);
		    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
		      public void onFailure(Throwable error) {
		      }

		      public void onSuccess(LoginInfo result) {
		        loginInfo = result;
		        if(loginInfo.isLoggedIn()) {
		        	//menuePanel
					menuePanel.addStyleName("menue");
					menuePanel.add(pinnwandButton);
					menuePanel.add(reportButton);
					
		        	PinnwandGui pg = new PinnwandGui();
					funktionenPanel.clear();
					funktionenPanel.add(pg);
					
					signOutLink.setHref(loginInfo.getLogoutUrl());
					signOutLink.setStyleName("logout");
					menuePanel.add(signOutLink);
		        } else {
		          loadLogin();
		        }
		      }
		    });
		    
		    
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
			loginService.login(GWT.getHostPageBaseURL(), acb);

		}
	
	 private void loadLogin() {
		    // Assemble login panel.
		    signInLink.setHref(loginInfo.getLoginUrl());
		    loginPanel.add(loginLabel);
		    loginPanel.add(signInLink);
		
		  }
	
}

