package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.reportGenerator.EinzelbeitragsReport;
import de.hdm.itprojekt.shared.reportGenerator.EinzelnutzerReport;
import de.hdm.itprojekt.shared.reportGenerator.MehrereBeitraegeReport;

/**
 * Das Interface Report Generator bietet eine Reihe von <code>create...</code>-Methoden, mit deren Hilfe die Reports erstellt werden k�nnen. 
 * Das Interface kann einfach durch zus�tzliche <code>create..</code>-Methoden erweitert werden.
 * @author Stefan
 *
 */



public interface ReportGenerator extends RemoteService {
	/**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   */	
	public void init() throws IllegalArgumentException;
	/**
	 * Setzen des zugeordneten Benutzers.
	 * @param Nutzer
	 * @throws IllegalArgumentException
	 * 
	 */
	public void setNutzer( Nutzer n) throws IllegalArgumentException;
	/**
	 * Erstellen eines <code>MehrereBeitraegeReport</code>.
	 * Dieser Report zeigt die Beitr�ge mehrerer Nutzer an
	 * @param n
	 * @throws IllegalArgumentException
	 */
	public abstract MehrereBeitraegeReport createMehrereBeitraegeReport() throws IllegalArgumentException;
	/**
	 * Dieser Report zeigt alle Beitr�ge eines Nutzers an.
	 * @return
	 * @throws IllegalArgumentException
	 */
	public abstract EinzelnutzerReport createEinzelnutzerReport() throws IllegalArgumentException;
	/**
	 * Dieser Report gibt einen einzelnen Beitrag eines einzelnen Nutzers aus.
	 * @return
	 * @throws IllegalArgumentException
	 */
	public abstract EinzelbeitragsReport createEinzelbeitragsReport() throws IllegalArgumentException;
	
}
