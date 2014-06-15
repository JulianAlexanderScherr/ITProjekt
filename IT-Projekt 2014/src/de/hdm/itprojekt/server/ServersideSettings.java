package de.hdm.itprojekt.server;

import java.util.logging.Logger;

import de.hdm.itprojekt.shared.CommonSettings;

/**
 * Mit Logging wird das Protokollieren eines Programmablaufes beschrieben. 
 * Grundsätzlich reicht dazu schon ein System.out.println("Nachricht"); aus. 
 * Allerdings bringt es die weitere Entwicklung recht schnell mit sich, dass das 
 * Interesse an "alten" Debug-Nachrichten schwindet oder diese das Protokoll unleserlich 
 * oder Überfrachtet erscheinen lassen. 
 * <br/>
 * Es wird ein
 * applikationszentraler Logger realisiert, der mittels
 * <code>ServerSideSettings.getLogger()</code> genutzt werden kann.
 * </p>
 * 
 * @author Thies, Schwab
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
   * bedarfsweise Einfügen und Auskommentieren etwa von
   * <code>System.out.println(...);</code> steuern. Sie belassen künftig
   * sämtliches Logging im Code und können ohne abermaliges Kompilieren den Log
   * Level "von außen" durch die Datei <code>logging.properties</code> steuern.
   * Sie finden diese Datei in dem <code>war/WEB-INF</code>-Ordner Ihres
   * Projekts. Der dort standardmäßig vorgegebene Log Level ist
   * <code>WARN</code>. Dies w�rde bedeuten, dass Sie keine <code>INFO</code>
   * -Meldungen wohl aber <code>WARN</code>- und <code>SEVERE</code>-Meldungen
   * erhielten. Wenn Sie also auch Log des Levels <code>INFO</code> wollten,
   * müssten Sie in dieser Datei <code>.level = INFO</code> setzen.
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