package de.hdm.itprojekt.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.report.HTMLReportUebersetzer;
import de.hdm.itprojekt.shared.report.MehrereBeitraegeReport;
import de.hdm.itprojekt.shared.report.MehrnutzerReport;

public class ReportAusgabe extends VerticalPanel{
	
	VerticalPanel ausgabe = new VerticalPanel();
	ScrollPanel panel = new ScrollPanel();
	
	ReportGeneratorAsync rga = null;
	

	
	
	
	
	public ReportAusgabe(Date az, Date ez, Nutzer nutzer, String auswahl){
		
		this.add(ausgabe);

		
		ausgabe.addStyleName("reportAusgabePanel");

		
		
		if (rga == null) {
			rga = ClientsideSettings.getReportGenerator();
		}
		
		if(nutzer.getNachname().equals("AlleNutzerGewaehlt")){
			
			rga.createMehrnutzerReport(az, ez, auswahl, new AsyncCallback<MehrnutzerReport>(){
				
				public void onFailure(Throwable caught) {

				}
				
				public void onSuccess(MehrnutzerReport result) {

					HTMLReportUebersetzer ru = new HTMLReportUebersetzer();
						
					ru.process(result);	
							
						
					panel.add(new HTML(ru.getReportText()));
					ausgabe.add(panel);
				}
			});
		}
		else {
		rga.createMehrereBeitraegeReport(az, ez, nutzer, auswahl, new AsyncCallback<MehrereBeitraegeReport>(){

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(MehrereBeitraegeReport result) {

				HTMLReportUebersetzer ru = new HTMLReportUebersetzer();
					
				ru.process(result);	
						
					
				panel.add(new HTML(ru.getReportText()));
				ausgabe.add(panel);

			}
				
			
		});
	}
	}
}
