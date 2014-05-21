package de.hdm.itprojekt.server;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.AbonnementMapper;
import de.hdm.itprojekt.server.db.BeitragMapper;
import de.hdm.itprojekt.server.db.NutzerMapper;
import de.hdm.itprojekt.server.db.KommentarMapper;
import de.hdm.itprojekt.server.db.LikeMapper;
import de.hdm.itprojekt.server.db.PinnwandMapper;
import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Beitrag;
import de.hdm.itprojekt.shared.bo.BusinessObject;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Like;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;


/**
 * Das ist die eigentliche Applikationslogik... hier wird verknüpft und dargestellt !!!!
 * @author Thies, Schwab
 *
 */
public class VerwaltungsklasseImpl extends RemoteServiceServlet
implements Verwaltungsklasse{
	
	/**
	 * serialVersionUID wird benötigt um eine Art Version festzulegen um bei einer Deserialisierung den Wert der Variable zu vergleichen
	 * </br>weitere Informationen zu Serializable siehe <a href="http://www.zdnet.de/39154667/wissenswertes-zur-serialisierung-von-java-objekten/">Link</a>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Referenz auf den DatenbankMapper, der Nutzerobjekte mit der Datenbank
	 * abgleicht.
	 */
	private NutzerMapper nMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der Beitragsobjekte mit der Datenbank
	 * abgleicht.
	 */
	private BeitragMapper bMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der Kommentarobjekte mit der Datenbank
	 * abgleicht.
	 */
	private KommentarMapper kMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der Abonnementobjekte mit der Datenbank
	 * abgleicht.
	 */
	private AbonnementMapper aMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der Likeobjekte mit der Datenbank
	 * abgleicht.
	 */
	private LikeMapper lMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der Pinnwandobjekte mit der Datenbank
	 * abgleicht.
	 */
	private PinnwandMapper pMapper = null;
	
	
	
    /*
     * ***********************************
     * Abschnitt: Initialisierung
     */
	
	/**
     * <p>Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
     * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
     * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
     * Konstruktors ist durch die Client-seitige Instantiierung durch
     * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
     * möglich.
     * </p>
     * <p>
     * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
     * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
     * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
     * </p>
     * 
     * @see #init()
	 * @throws IllegalArgumentException
	 */
	public VerwaltungsklasseImpl() throws IllegalArgumentException {
	    /*
	     * Der No-Argument Konstruktor muss nur vorhanden sein und bedarf keinen Inhalt
	     */
	  }
	
	/**
	 *
     * Initialsierungsmethode für die Datenbank-Mapper
     * 
     * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException {
	    /*
	     * Ganz wesentlich ist, dass die BankAdministration einen vollständigen Satz
	     * von Mappern besitzt, mit deren Hilfe sie dann mit der Datenbank
	     * kommunizieren kann.
	     */
	    this.nMapper = NutzerMapper.nutzerMapper();
	    this.bMapper = BeitragMapper.beitragMapper();
	    this.kMapper = KommentarMapper.kommentarMapper();
	    this.aMapper = AbonnementMapper.abonnementMapper();
	    this.lMapper = LikeMapper.likeMapper();
	    this.pMapper = PinnwandMapper.pinnwandMapper();
	  }
	
	
	/**
	 * Eine Pinnwand anlegen, bzw mit einem Nutzer verknüpfen
	 * @param nutzer
	 * @throws IllegalArgumentException
	 */
	public void setPinnwand(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
	}

	/**
	 * Kommentartext erstellen und einem Beitrag zuweisen
	 * @param text
	 * @param beitrag
	 * @throws IllegalArgumentException
	 */
	public void setKommentar(String text, Beitrag beitrag) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Beitragstext erstellen
	 * @param text
	 * @throws IllegalArgumentException
	 */
	public void setBeitrag(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Setzen eines Abonnements
	 * @param abonnement
	 * @throws IllegalArgumentException
	 */
	public void setAbonnement(Abonnement abonnement) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Kommentar loeschen
	 * @param kommentar
	 * @throws IllegalArgumentException
	 */
	public void loeschenKommentar(Kommentar kommentar) throws IllegalArgumentException {
		this.kMapper.entfernen(kommentar);
		
	}

	/**
	 * Einen Beitrag löschen
	 * @param beitrag
	 * @throws IllegalArgumentException
	 */
	public void loeschenBeitrag(Beitrag beitrag) throws IllegalArgumentException {
		//zuerst alle Likes und Kommentare löschen
		
	}

	/**
	 * Einen Beitrag liken (Kommentare können nicht geliked oder kommentiert werden)
	 * @param beitrag
	 * @param nutzer
	 * @throws IllegalArgumentException
	 */
	public void likeBeitrag(Beitrag beitrag, Nutzer nutzer) throws IllegalArgumentException {
		Like l = new Like();
		l.setNutzerID(nutzer.getId());
		l.setBeitragID(beitrag.getId());
		//BEITRAG FEHLT IN MAPPER
		this.lMapper.anlegen(l);
		
	}

	/**
	 * Einen geliketen Beitrag wieder entliken
	 * @param beitrag
	 * @param nutzer
	 * @throws IllegalArgumentException
	 */
	public void unlikeBeitrag(Like like) throws IllegalArgumentException {
		this.lMapper.entfernen(like);
		
	}

	/**
	 * Auslesen aller Nutzer. Zurückgegeben wird ein Vector der alle Nutzer Objekte der Datenbank enthält
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getAlleNutzer() throws IllegalArgumentException {
		return this.nMapper.suchenAlle();
	}

	/**
	 * Auslesen aller Likes. Zurückgegeben wird ein Vector der alle Like Objekte der Datenbank enthält
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Like> getAlleLikes() throws IllegalArgumentException {
		return this.lMapper.suchenAlle();
	}

	/**
	 * Auslesen aller Beiträge. Zurückgegeben wird ein Vector der alle Beitrags Objekte der Datenbank enthält
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> getAlleBeitraege() throws IllegalArgumentException {
		return this.bMapper.suchenAlle();
	}

	/**
	 * Auslesen aller Abonnenten. Zurückgegeben wird ein Vector der alle Abonnement Objekte der Datenbank enthält
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Abonnement> getAlleAbonnenten() throws IllegalArgumentException {
		return this.aMapper.suchenAlle();
	}
	
	/**
	 * Auslesen eines einzelnen Beitrag. Zurückgegeben wird das Beitrags Objekt
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Beitrag getBeitrag(int id) throws IllegalArgumentException {
		return this.bMapper.suchenID(id);
	}

	/**
	 * Ausgeben eines Nutzers anhand dessen ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Nutzer getNutzerByID(int id) throws IllegalArgumentException {
		return nMapper.suchenID(id);
		
	}

	/**
	 * Ausgeben von Nutzern durch Eingabe des Nachnamens
	 * @param nachname
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getNutzerByNachname(String nachname) throws IllegalArgumentException {
		return this.nMapper.suchenNachname(nachname);
	}

	/**
	 * Ausgeben von Nutzern durch Eingabe des Vornamens
	 * @param vorname
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getNutzerByVorname(String vorname) throws IllegalArgumentException {
		return this.nMapper.suchenVorname(vorname);
	}

	/**
	 * Ausgeben von Nutzern durch Eingabe des Nicknamen
	 * @param nickname
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getNutzerByNickname(String nickname) throws IllegalArgumentException {
		return this.nMapper.suchenNickname(nickname);
	}

	/**
	 * Ausgeben eines Pinnwand-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Pinnwand getPinnwand(int id) throws IllegalArgumentException {
		return this.pMapper.suchenID(id);
	}

	/**
	 * Ausgeben eines Kommentar-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Kommentar getKommentar(int id) throws IllegalArgumentException {
		return this.kMapper.suchenID(id);
	}

	/**
	 * Ausgeben eines Abonnement-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Abonnement getAbonnement(int id) throws IllegalArgumentException {
		return this.aMapper.suchenID(id);
	}

	/**
	 * Ausgeben eines Like-Objektes anhand der ID
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Like getLike(int id) throws IllegalArgumentException {
		return this.lMapper.suchenID(id);
	}
}
