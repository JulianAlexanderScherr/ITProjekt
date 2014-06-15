package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.*;


/**
 * <p>
 * Synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Verwaltung einer Social Media Pinnwand.
 * </p>
 * <p>
 * <b>Frage:</b> Warum werden diese Methoden nicht als Teil der Klassen
 * {@link Nutzer}, {@link Pinnwand}, usw. implementiert?<br>
 * <b>Antwort:</b> Beispielsweise erfordert das L�schen eines Beitrags, Informationen �ber die Verfechtung zu
 * Kommentaren und allen Likes. Damit diese Klassen nicht so stark an andere KLassen gekoppelt werden, werden
 * die Inforamtionen �ber das Beziehungsgeflecht in der Verwaltungsklasse gekapselt.
 * </p>
 * <p>
 * <code>@RemoteServiceRelativePath("verwaltung")</code> ist bei der
 * Adressierung des aus der zugeh�rigen Impl-Klasse entstehenden
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
     * RPC zus�tzlich zum No Argument Constructor der implementierenden Klasse
     * {@link VerwaltungsklaseeImpl} notwendig.
	 * @throws IllegalArgumentException
	 * @author Thies
	 */
	public void init() throws IllegalArgumentException;
	
	/**
	 * Einen Nutzer anlegen
	 * @param nutzer
	 * @throws IllegalArgumentException
	 */
	public void createNutzer(Nutzer nutzer) throws IllegalArgumentException;
		
	/**
	 * Kommentartext erstellen und einem Beitrag zuweisen
	 * @param text
	 * @param beitrag
	 * @throws IllegalArgumentException
	 */
	public void createKommentar(Kommentar k) throws IllegalArgumentException;
	
	
	/**
	 * Beitragstext erstellen
	 * @param text
	 * @throws IllegalArgumentException
	 */
	public void createBeitrag(Beitrag b) throws IllegalArgumentException;
	
	
	/**
	 * Setzen eines Abonnements
	 * @param abonnement
	 * @throws IllegalArgumentException
	 */
	public void createAbonnement(Abonnement a) throws IllegalArgumentException;
	
	/**
	 * Überprüfen ob E-Mail Adresse in der Datenbank verfügbar ist
	 * @param mail
	 * @throws IllegalArgumentException
	 */
	public Nutzer checkEmail(String mail) throws IllegalArgumentException;
	
	/**
	 * Abonnement loeschen
	 * @param kommentar
	 * @throws IllegalArgumentException
	 */
	public void loeschenAbonnement(int pID, int nID) throws IllegalArgumentException;
	
	
	/**
	 * Kommentar loeschen
	 * @param kommentar
	 * @throws IllegalArgumentException
	 */
	public void loeschenKommentar(Kommentar k) throws IllegalArgumentException;
	
	
	/**
	 * Einen Beitrag l�schen
	 * @param beitrag
	 * @throws IllegalArgumentException
	 */
	public void loeschenBeitrag(Beitrag beitrag) throws IllegalArgumentException;
	
	
	/**
	 * Einen Beitrag liken (Kommentare k�nnen nicht geliked oder kommentiert werden)
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
	public void unlikeBeitrag(Like like) throws IllegalArgumentException;
	
	
	/**
	 * Auslesen aller Nutzer. Zur�ckgegeben wird ein Vector der alle Nutzer Objekte der Datenbank enth�lt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getAlleNutzer() throws IllegalArgumentException;
	
	
	/**
	 * Auslesen aller Likes. Zur�ckgegeben wird ein Vector der alle Like Objekte der Datenbank enth�lt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Like> getAlleLikes() throws IllegalArgumentException; 
	
	
	/**
	 * Auslesen aller Beitr�ge. Zur�ckgegeben wird ein Vector der alle Beitrags Objekte der Datenbank enth�lt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> getAlleBeitraege() throws IllegalArgumentException; 
	
	
	/**
	 * Auslesen aller Abonnenten. Zur�ckgegeben wird ein Vector der alle Abonnement Objekte der Datenbank enth�lt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Abonnement> getAlleAbonnenten() throws IllegalArgumentException; 
	
	/**
	 * Auslesen eines einzelnen Beitrag. Zur�ckgegeben wird das Beitrags Objekt
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Beitrag getBeitrag(int id) throws IllegalArgumentException;
	
	/**
	 * Auslesen der Beiträge für einen Nutzer
	 * @param n
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> getBeitraegeByNutzer(Nutzer n) throws IllegalArgumentException;
	
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
	 * Ausgeben aller Kommentare für einen Beitrag
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Kommentar> getKommentarByBeitrag(Beitrag b) throws IllegalArgumentException;
	
	/**
	 * Ausgeben eines Abonnement-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Abonnement getAbonnement(int id) throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Abonnenten anhand eines Nutzers
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Abonnement> getAbonnementByNutzer(Nutzer n) throws IllegalArgumentException;
	
	/**
	 * Gibt einen Vector mit Nutzern aus, welche vom Nutzer abonniert wurden
	 * @param n
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getAbonnementNutzer(Nutzer n) throws IllegalArgumentException;
	
	/**
	 * Ausgeben der Beiträge der Abonnenten und des Nutzers
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> getAlleBeitraegeByNutzer(Nutzer n) throws IllegalArgumentException;
	
	/**
	 * Ausgeben eines Like-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Like getLike(int id) throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Likes für einen Beitrag
	 * @param b
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Like> getLikeByBeitrag(Beitrag b) throws IllegalArgumentException;
	
	/**
	 * Beitrag ändern
	 * @param b
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void updateBeitrag(Beitrag b) throws IllegalArgumentException;
	
	/**
	 * Nutzer ändern
	 * @param n
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void updateNutzer(Nutzer n) throws IllegalArgumentException;
	
	/**
	 * Kommentar ändern
	 * @param k
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void updateKommentar(Kommentar k) throws IllegalArgumentException;
	
	/**
	 * Sortieren eines Beitragsvectors anhand des Erstellungszeitpunktes
	 * @param vb
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> sortBeitraege(Vector<Beitrag> vb) throws IllegalArgumentException;
	
	/**
	 * Holt den aktuell eingeloggten Nutzer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public String getCurrentUserMail() throws IllegalArgumentException;
}
