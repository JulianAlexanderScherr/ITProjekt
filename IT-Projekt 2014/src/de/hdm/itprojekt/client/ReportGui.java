package de.hdm.itprojekt.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class ReportGui extends HorizontalPanel{
	
	//reportFunktionsPanel: Panel für Report Erstellung
	//Anlegen der erforderlichen Panel
	private HorizontalPanel reportPanel = new HorizontalPanel();
	private VerticalPanel reportAusgabePanel =new VerticalPanel();
	private VerticalPanel steuerPanel = new VerticalPanel();	
	//Anlegen der erforderlichen Label
	private Label reportLabel = new Label("Report");
	private Label informationLabel = new Label("Information");
	private Label sortierenNachLabel = new Label("sortieren nach");
	private Label vonLabel = new Label("von");
	private Label bisLabel = new Label("bis");	
	private Label reportAusgabeLabel = new Label("Testreport 1 \nTestreport 2 \nTestreport 3 \nusw....................................");
	//Anlegen der erforderlichen ListBox
	private ListBox suchObjekt = new ListBox();
	private ListBox sortierenNach = new ListBox();
	//Anlegen der erfordelrichen DatePicker
	private DatePicker startDatum = new DatePicker();
	private DatePicker endeDatum = new DatePicker();
	
	
	public ReportGui(){
		
		
		System.out.println("test12345");
		
		this.add(reportPanel);
		//reportPanel
		reportPanel.add(steuerPanel);
		reportPanel.add(reportAusgabePanel);
		//reportAusgabePanel
		reportAusgabePanel.add(reportAusgabeLabel);		
		//menuePanel
		//menuePanel:suchObjekt+sortierenNach: ListBox befüllen und sichtbar machen
		suchObjekt.addItem("User");
		suchObjekt.addItem("Likes");
		suchObjekt.addItem("Pinnwand");
		suchObjekt.setVisibleItemCount(1);
				
		sortierenNach.addItem("Monat");
		sortierenNach.addItem("User");
		sortierenNach.addItem("Anzahl");
		sortierenNach.setVisibleItemCount(1);
						
		steuerPanel.add(informationLabel);
		steuerPanel.add(suchObjekt);
		steuerPanel.add(sortierenNachLabel);
		steuerPanel.add(sortierenNach);
		steuerPanel.add(vonLabel);
		steuerPanel.add(startDatum);
		steuerPanel.add(bisLabel);
		steuerPanel.add(endeDatum);
		
	}

}
