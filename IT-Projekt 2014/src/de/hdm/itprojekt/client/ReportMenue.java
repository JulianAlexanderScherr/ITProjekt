package de.hdm.itprojekt.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Nutzer;

/**
 * Klasse zur Darstellung der Reportseite und des Menues auf der linken Seite der Sektion Report
 * Beim bet�tigen des "Report erstellen" Button wird der Konstruktor der Klasse "ReportAusgabe"
 * mit den 4 �bergabeparametern aufgerufen und eine neue Instanz erstellt. 
 */

public class ReportMenue extends VerticalPanel{
	
	
	
	/**Anlegen der für die Darstellung der GUI erforderlichen GWT-Widgets
	 * 
	 */
	
	//Anlegen der erforderlichen Panel
	private HorizontalPanel reportPanel = new HorizontalPanel();
	private VerticalPanel reportMenuePanel = new VerticalPanel();
	private VerticalPanel reportAusgabePanel = new VerticalPanel();

	//Anlegen der erforderlichen Label
	private Label report = new Label("Menue Report");
	private Label benutzer = new Label("Benutzer");
	private Label auswahl = new Label("Auswahl");
	private Label vonLabel = new Label("von");
	private Label bisLabel = new Label("bis");
		
	//Anlegen der erforderlichen Vectoren
	private Nutzer nutzer  = new Nutzer();
	
	//Anlegen der erforderlichen ListBox
	private ListBox auswahlBox = new ListBox();
	
	//Anlegen der erforderlichen DateBox
	private DateBox startDatum = new DateBox();
	private DateBox endeDatum = new DateBox();
	
	//Anlegen der erforderlichen Buttons
	private Button erstellenReport = new Button("Report erstellen");
	
	//Anlegen der erforderlichen RadioButton
	private RadioButton einzelNutzer = new RadioButton("gruppe1","Einzelner Nutzer");
	private RadioButton alleNutzer = new RadioButton("gruppe1","Alle Nutzer");

	
		
	
	VerwaltungsklasseAsync verwaltung = null;

	
	/**
	 * Standart Konstruktor der Klasse ReportMenue. Wird in der Klasse ReportGui aufgerufen
	 * und instanziiert. Hier wird das auf der Reportseite zu sehende Men� mit den Auswahlm�glichkeiten 
	 * der Reporterstellung zusammengesetzt. Zudem wird hier die CellList mit den Nutzern erzeugt.
	 * @return 
	 */
	public ReportMenue(){
		
		this.add(reportPanel);

		
		//Setzen eines Default Datums in der DateBox
		startDatum.getTextBox().setText("2014 Jan 01 00:00:00");
		Date ed = new Date(System.currentTimeMillis());	
		endeDatum.setValue(ed);
		
		//Style Namen hinzufuegen
		reportMenuePanel.addStyleName("reportMenuePanel");
		report.addStyleName("report");
		erstellenReport.addStyleName("erstellenReport");
		
		//Erstellen der Grundstruktur fuer das Menue links und die Ausgabe des Reports rechts		
		reportPanel.add(reportMenuePanel);		
		reportPanel.add(reportAusgabePanel);

		//Befuellen der ListBox AuswahlBox mit Werten
		auswahlBox.addItem("Beitrag");
		auswahlBox.addItem("Kommentar");
		auswahlBox.addItem("Likes");
		auswahlBox.addItem("Alles");
			
		//Zusammensetzen des reportMenuePanel						
		reportMenuePanel.add(report);
		reportMenuePanel.add(benutzer);
		reportMenuePanel.add(einzelNutzer);
		reportMenuePanel.add(alleNutzer);
		
		//Setzen des Default Wertes f�r die RadioButton
		alleNutzer.setValue(true);

		
		if (verwaltung == null) {
			verwaltung = ClientsideSettings.getVerwaltung();
		}
		
		/**
		 * Erzeugen der CellList die im ReportMenue links eingebettet ist und den Vornamen, Nachnamen und Nickname anzeigt.
		 */
		verwaltung.getAlleNutzer(new AsyncCallback<Vector<Nutzer>>(){

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("ERROR in getAlleNutzer");
			}

			@Override
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
			        		 nutzer = singleSelectionModel.getSelectedObject();
			                 
			             }
		        });
			        	
		        for(Nutzer n : result){
		        	nutzerDataProvider.getList().add(0, n);
				}
		        
		        nutzerCellList.setStyleName("cellList");
		        
				//Anhaengen der CellList an das reportMenuePanel
		        reportMenuePanel.add(nutzerCellList);
		        
		        //Standartm�ssig ist der RadioButton alleNutzer aktiviert und die CellList wird somit ausgeblendet
		        nutzerCellList.setVisible(false);
		        
		        //Anhaengen der restlichen Elemente an das reportMenuePanel
		        reportMenuePanel.add(auswahl);
		        reportMenuePanel.add(auswahlBox);
		        reportMenuePanel.add(vonLabel);
				reportMenuePanel.add(startDatum);
				reportMenuePanel.add(bisLabel);
				reportMenuePanel.add(endeDatum);		
				reportMenuePanel.add(erstellenReport);
				
				
				/**
				 * ValueChangeHandler fuer die RadioButtons einzelNutzer und alleNutzer.
				 * Als Default ist der RadioButton alleNutzer gecheckt und die nutzerCellList ausgeblendet.
				 * Wird nun der RadioButton einzelNutzer angew�hlt wird die nutzerCellList eingelendet.
				 * Wird danach wieder der RadioButton alleNutzer angew�hlt, wird sie wieder ausgeblendet.
				 */
				
				einzelNutzer.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						nutzerCellList.setVisible(true);
					}
				});
				
				alleNutzer.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						nutzerCellList.setVisible(false);
					}
				});
				
				/**
				 * Erstellen des ClickHaendler f�r den "Report erstellen" Button.
				 * Wird in der nutzerCellList kein Nutzer gew�hlt wird ein Window Alert ausgel�st.
				 * An dieser Stelle wird auch differenziert ob der Report f�r einen oder mehrere Nutzer erstellt wird.
				 * Wird der Button bet�tigt wird zuerst gefragt ob der Report f�r einen oder alle Nutzer ist. 
				 * Im fall alle wird einfach der Nachnamen des Aktuellen Nutzerobjekts in "AlleNutzerGewaehlt" ge�ndert um einen 
				 * Indikator f�r diesen Fall zu haben. Anschlie�end wird der Konstruktor der Klasse ReportAusgabe aufgerufen um ihm 
				 * die 4 �bergabeparameter: startDatum, endDatum, nutzer und Auswahlkriterium �bergeben.
				 */
				erstellenReport.addClickHandler(new ClickHandler() {

					public void onClick(ClickEvent event) {
						
						
						//Abfrage ob einer oder alle Nutzer
						if(alleNutzer.getValue() == true){
							//Setzen des Indikators
							nutzer.setNachname("AlleNutzerGewaehlt");
							//Erstellen der erforderlichen Instanz mit den benötigten Übergabeparametern und anhängen an das reportAusgabePanel				
							ReportAusgabe ra = new ReportAusgabe(startDatum.getValue(), endeDatum.getValue(), nutzer, auswahlBox.getItemText(auswahlBox.getSelectedIndex()));	
							reportAusgabePanel.clear();
							reportAusgabePanel.add(ra);
						}else{
							//Abfrage ob ein Nutzer ausgew�hlt wurde
							if(singleSelectionModel.isSelected(nutzer)==false){
								//Wenn kein Nutzer ausgew�hlt wurde gibt es folgenden Alert
								Window.alert("Bitte einen Nutzer ausw�hlen");
								
							}else{
								//Erstellen der erforderlichen Instanz mit den benötigten Übergabeparametern und anhängen an das reportAusgabePanel				
								ReportAusgabe ra = new ReportAusgabe(startDatum.getValue(), endeDatum.getValue(), nutzer, auswahlBox.getItemText(auswahlBox.getSelectedIndex()));	
								reportAusgabePanel.clear();
								reportAusgabePanel.add(ra);
								
							}
						}
					}
				});
			}
		});
		



		
	}
	
}
