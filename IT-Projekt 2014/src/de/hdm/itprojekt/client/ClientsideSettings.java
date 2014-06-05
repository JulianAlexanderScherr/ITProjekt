package de.hdm.itprojekt.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.CommonSettings;
import de.hdm.itprojekt.shared.ReportGenerator;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;

/**
 * Klasse mit Eigenschaften und Diensten, die f�r alle Client-seitigen Klassen
 * relevant sind.
 * @author Thies, Schwab
 *
 */
public class ClientsideSettings extends CommonSettings {
	
	private static VerwaltungsklasseAsync verwaltung = null;
	
	private static ReportGeneratorAsync reportGenerator = null;
	

	  /**
	   * Name des Client-seitigen Loggers.
	   */
	  private static final String LOGGER_NAME = "Social Media Pinnwand LOGGER";
	  
	  /**
	   * Instanz des Client-seitigen Loggers.
	   */
	  private static final Logger log = Logger.getLogger(LOGGER_NAME);

	  /**
	   * <p>
	   * Auslesen des applikationsweiten (Client-seitig!) zentralen Loggers.
	   * </p>
	   * 
	   * <h2>Anwendungsbeispiel:</h2> Zugriff auf den Logger herstellen durch:
	   * 
	   * <pre>
	   * Logger logger = ClientSideSettings.getLogger();
	   * </pre>
	   * 
	   * und dann Nachrichten schreiben etwa mittels
	   * 
	   * <pre>
	   * logger.severe(&quot;Sie sind nicht berechtigt, ...&quot;);
	   * </pre>
	   * 
	   * oder
	   * 
	   * <pre>
	   * logger.info(&quot;Lege neuen Kunden an.&quot;);
	   * </pre>
	   * 
	   * <p>
	   * Bitte auf <em>angemessene Log Levels</em> achten! Severe und info sind nur
	   * Beispiele.
	   * </p>
	   * 
	   * <h2>HINWEIS:</h2>
	   * <p>
	   * Beachten Sie, dass Sie den auszugebenden Log nun nicht mehr durch
	   * bedarfsweise Einf�gen und Auskommentieren etwa von
	   * <code>System.out.println(...);</code> steuern. Sie belassen k�nftig
	   * s�mtliches Logging im Code und k�nnen ohne abermaliges Kompilieren den Log
	   * Level "von au�en" durch die Datei <code>logging.properties</code> steuern.
	   * Sie finden diese Datei in Ihrem <code>war/WEB-INF</code>-Ordner. Der dort
	   * standardm��ig vorgegebene Log Level ist <code>WARN</code>. Dies w�rde
	   * bedeuten, dass Sie keine <code>INFO</code>-Meldungen wohl aber
	   * <code>WARN</code>- und <code>SEVERE</code>-Meldungen erhielten. Wenn Sie
	   * also auch Log des Levels <code>INFO</code> wollten, m�ssten Sie in dieser
	   * Datei <code>.level = INFO</code> setzen.
	   * </p>
	   * 
	   * Weitere Infos siehe Dokumentation zu Java Logging.
	   * 
	   * @return die Logger-Instanz f�r die Server-Seite
	   */
	  public static Logger getLogger() {
	    return log;
	  }

	  /**
	   * <p>
	   * Anlegen und Auslesen der applikationsweit eindeutigen Social Media Pinnwand - Verwaltung. Diese
	   * Methode erstellt die Verwaltung, sofern sie noch nicht existiert. Bei
	   * wiederholtem Aufruf dieser Methode wird stets das bereits zuvor angelegte
	   * Objekt zur�ckgegeben.
	   * </p>
	   * 
	   * <p>
	   * Der Aufruf dieser Methode erfolgt im Client z.B. durch
	   * <code>VerwaltungsklasseAsync verwaltung = ClientSideSettings.getVerwaltung()</code>
	   * .
	   * </p>
	   * 
	   * @return eindeutige Instanz des Typs <code>VerwaltungsklasseAsync</code>
	   * @author Thies, Schwab
	   */
	  public static VerwaltungsklasseAsync getVerwaltung() {
	    // Gab es bislang noch keine Verwaltungs-Instanz, dann...
	    if (verwaltung == null) {
	      //Verwaltungsklasse wird instanziiert
	      verwaltung = GWT.create(Verwaltungsklasse.class);
	    }
	 
	    return verwaltung;
	  }	
	  /**
	   * <p>
	   * Anlegen und Auslesen des applikationsweit eindeutigen ReportGenerators.
	   * Diese Methode erstellt den ReportGenerator, sofern dieser noch nicht
	   * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
	   * zuvor angelegte Objekt zurÃ¼ckgegeben.
	   * </p>
	   * 
	   * <p>
	   * Der Aufruf dieser Methode erfolgt im Client z.B. durch
	   * <code>ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator()</code>
	   * .
	   * </p>
	   */
	  
	  public static ReportGeneratorAsync getReportGenerator(){
		  
		  if(reportGenerator == null){
			  reportGenerator = GWT.create(ReportGenerator.class);
			
			  final AsyncCallback <Void> initReportGeneratorCallback = new AsyncCallback <Void>(){
				 public void onFailure(Throwable caught){
				  ClientsideSettings.getLogger().severe(
						  "Der ReportGenerator konnte nicht initialisiert werden!");
			  }
			 
			@Override
			public void onSuccess(Void result) {
				 ClientsideSettings.getLogger().info(
						  "Der ReportGenerator wurde initialisiert.");
			}
		  };
		  reportGenerator.init(initReportGeneratorCallback);
			  }
	  return reportGenerator;
		  }
	  
}