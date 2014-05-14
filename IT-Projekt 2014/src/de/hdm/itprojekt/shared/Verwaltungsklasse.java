package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.*;


/**
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung einer Social Media Pinnwand.
 * </p>
 * <p>
 * <b>Frage:</b> Warum werden diese Methoden nicht als Teil der Klassen
 * {@link Nutzer}, {@link Pinnwand}, usw. implementiert?<br>
 * <b>Antwort:</b> Beispielsweise erfordert das Löschen eines Beitrags, Informationen über die Verfechtung zu
 * Kommentaren und allen Likes. Damit diese Klassen nicht so stark an andere KLassen gekoppelt werden, werden
 * die Inforamtionen über das Beziehungsgeflecht in der Verwaltungsklasse gekapselt.
 * </p>
 * <p>
 * <code>@RemoteServiceRelativePath("verwaltung")</code> ist bei der
 * Adressierung des aus der zugehörigen Impl-Klasse entstehenden
 * Servlet-Kompilats behilflich. Es gibt im Wesentlichen einen Teil der URL des
 * Servlets an.
 * </p>
 * 
 * @author Thies
 */
@RemoteServiceRelativePath("verwaltung")
public interface Verwaltungsklasse extends RemoteService{

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
     * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
     * {@link VerwaltungsklaseeImpl} notwendig.
	 * @throws IllegalArgumentException
	 * @author Thies
	 */
	public void init() throws IllegalArgumentException;
	
	
	/**
	 * Eine Pinnwand anlegen, bzw mit einem Nutzer verknüpfen
	 * @param nutzer
	 * @throws IllegalArgumentException
	 */
	public void setPinnwand(Nutzer nutzer) throws IllegalArgumentException;
	
	
	/**
	 * Kommentartext erstellen und einem Beitrag zuweisen
	 * @param text
	 * @param beitrag
	 * @throws IllegalArgumentException
	 */
	public void setKommentar(String text, Beitrag beitrag) throws IllegalArgumentException;
	
	
	/**
	 * Beitragstext erstellen
	 * @param text
	 * @throws IllegalArgumentException
	 */
	public void setBeitrag(String text) throws IllegalArgumentException;
	
	
	/**
	 * Setzen eines Abonnements
	 * @param abonnement
	 * @throws IllegalArgumentException
	 */
	public void setAbonnement(Abonnement abonnement) throws IllegalArgumentException;
	
	
	/**
	 * Kommentar loeschen
	 * @param kommentar
	 * @throws IllegalArgumentException
	 */
	public void loeschenKommentar(Kommentar kommentar) throws IllegalArgumentException;
	
	
	/**
	 * Einen Beitrag löschen
	 * @param beitrag
	 * @throws IllegalArgumentException
	 */
	public void loeschenBeitrag(Beitrag beitrag) throws IllegalArgumentException;
	
	
	/**
	 * Einen Beitrag liken (Kommentare können nicht geliked oder kommentiert werden)
	 * @param beitrag
	 * @param nutzer
	 * @throws IllegalArgumentException
	 */
	public void likeBeitrag(Beitrag beitrag, Nutzer nutzer) throws IllegalArgumentException;
	
	
	/**
	 * Einen geliketen Beitrag wieder entliken
	 * @param beitrag
	 * @param nutzer
	 * @throws IllegalArgumentException
	 */
	public void unlikeBeitrag(Beitrag beitrag, Nutzer nutzer) throws IllegalArgumentException;
	
	
	/**
	 * Auslesen aller Nutzer. Zurückgegeben wird ein Vector der alle Nutzer Objekte der Datenbank enthält
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getAlleNutzer() throws IllegalArgumentException;
	
	
	/**
	 * Auslesen aller Likes. Zurückgegeben wird ein Vector der alle Like Objekte der Datenbank enthält
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Like> getAlleLikes() throws IllegalArgumentException; 
	
	
	/**
	 * Auslesen aller Beiträge. Zurückgegeben wird ein Vector der alle Beitrags Objekte der Datenbank enthält
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> getAlleBeitraege() throws IllegalArgumentException; 
	
	
	/**
	 * Auslesen aller Abonnenten. Zurückgegeben wird ein Vector der alle Abonnement Objekte der Datenbank enthält
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Abonnement> getAlleAbonnenten() throws IllegalArgumentException; 
	
	
	/**
	 * Auslesen aller Businessobjekte. Zurückgegeben wird ein Vector der alle Inhalte der Datenbank enthält
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<BusinessObject> getAlleBusinessObjects() throws IllegalArgumentException;
	
	
	/**
	 * Auslesen eines einzelnen Beitrag. Zurückgegeben wird das Beitrags Objekt
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Beitrag getBeitrag(int id) throws IllegalArgumentException;
	
	
	/**
	 * Ausgeben eines Nutzers anhand dessen ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Nutzer getNutzerByID(int id) throws IllegalArgumentException;
	
	/**
	 * Ausgeben von Nutzern durch Eingabe des Nachnamens
	 * @param nachname
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getNutzerByNachname(String nachname) throws IllegalArgumentException;
	
	
	/**
	 * Ausgeben von Nutzern durch Eingabe des Vornamens
	 * @param vorname
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getNutzerByVorname(String vorname) throws IllegalArgumentException;
	
	
	/**
	 * Ausgeben von Nutzern durch Eingabe des Nicknamen
	 * @param nickname
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getNutzerByNickname(String nickname) throws IllegalArgumentException;
	
	
	/**
	 * Ausgeben eines Pinnwand-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Pinnwand getPinnwand(int id) throws IllegalArgumentException;
	
	/**
	 * Ausgeben eines Kommentar-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Kommentar getKommentar(int id) throws IllegalArgumentException;
	
	/**
	 * Ausgeben eines Abonnement-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Abonnement getAbonnement(int id) throws IllegalArgumentException;
	
	/**
	 * Ausgeben eines Like-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Like getLike(int id) throws IllegalArgumentException;
}
