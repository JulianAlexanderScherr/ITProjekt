package de.hdm.itprojekt.server;

import java.util.logging.Logger;

import de.hdm.itprojekt.shared.CommonSettings;

/**
 * Mit Logging wird das Protokollieren eines Programmablaufes beschrieben. 
 * Grunds�tzlich reicht dazu schon ein System.out.println("Nachricht"); aus. 
 * Allerdings bringt es die weitere Entwicklung recht schnell mit sich, dass das 
 * Interesse an "alten" Debug-Nachrichten schwindet oder diese das Protokoll unleserlich 
 * oder �berfrachtet erscheinen lassen. 
 * <br/>
 * Es wird ein
 * applikationszentraler Logger realisiert, der mittels
 * <code>ServerSideSettings.getLogger()</code> genutzt werden kann.
 * </p>
 * 
 * @author Thies
 * 
 */
public class ServersideSettings extends CommonSettings {
  private static final String LOGGER_NAME = "SocialMediaPinnwand Server";
  private static final Logger log = Logger.getLogger(LOGGER_NAME);

  /**
   * <p>
   * Auslesen des applikationsweiten (Server-seitig!) zentralen Loggers.
   * </p>
   * 
   * <h2>Anwendungsbeispiel:</h2> Zugriff auf den Logger herstellen durch:
   * 
   * <pre>
   * Logger logger = ServerSideSettings.getLogger();
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
   * Bitte auf <em>angemessene Log Levels</em> achten! <em>severe</em> und
   * <em>info</em> sind nur Beispiele.
   * </p>
   * 
   * <h2>HINWEIS:</h2>
   * <p>
   * Beachten Sie, dass Sie den auszugebenden Log nun nicht mehr durch
   * bedarfsweise Einf�gen und Auskommentieren etwa von
   * <code>System.out.println(...);</code> steuern. Sie belassen k�nftig
   * s�mtliches Logging im Code und k�nnen ohne abermaliges Kompilieren den Log
   * Level "von au�en" durch die Datei <code>logging.properties</code> steuern.
   * Sie finden diese Datei in dem <code>war/WEB-INF</code>-Ordner Ihres
   * Projekts. Der dort standardm��ig vorgegebene Log Level ist
   * <code>WARN</code>. Dies w�rde bedeuten, dass Sie keine <code>INFO</code>
   * -Meldungen wohl aber <code>WARN</code>- und <code>SEVERE</code>-Meldungen
   * erhielten. Wenn Sie also auch Log des Levels <code>INFO</code> wollten,
   * m�ssten Sie in dieser Datei <code>.level = INFO</code> setzen.
   * </p>
   * 
   * Weitere Infos siehe Dokumentation zu Java Logging.
   * 
   * @return die Logger-Instanz f�r die Server-Seite
   */
  public static Logger getLogger() {
    return log;
  }

}