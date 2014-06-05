package de.hdm.itprojekt.client;

import com.google.gwt.user.client.ui.HorizontalPanel;


public class ReportGui extends HorizontalPanel{
	

	private HorizontalPanel reportPanel = new HorizontalPanel();
	
		
	public ReportGui(){
		
		this.add(reportPanel);
		
		//Erstellen der erforderlichen Instanz und an das reportPanel anhängen		
		ReportMenue rm = new ReportMenue();		
		reportPanel.add(rm);
		
		
	}

}
