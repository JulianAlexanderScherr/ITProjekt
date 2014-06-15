package de.hdm.itprojekt.server;

import java.util.Vector;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
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
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Like;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;


/**
 * Implementationsklasse der Verwaltung. Hier werden die benötigten serverseitigen Methoden realisiert.
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
     * m�glich.
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
     * Initialsierungsmethode f�r die Datenbank-Mapper
     * 
     * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException {
	    /*
	     * Ganz wesentlich ist, dass die BankAdministration einen vollst�ndigen Satz
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
	 * Einen Nutzer anlegen
	 * @param nutzer
	 * @throws IllegalArgumentException
	 */
	public void createNutzer(Nutzer nutzer) throws IllegalArgumentException {
		this.nMapper.anlegen(nutzer);
		Pinnwand p = new Pinnwand();
		p.setId(nutzer.getId());
		this.pMapper.anlegen(p);
		
	}

	/**
	 * Kommentartext erstellen und einem Beitrag zuweisen
	 * @param text
	 * @param beitrag
	 * @throws IllegalArgumentException
	 */
	public void createKommentar(Kommentar k) throws IllegalArgumentException {
		this.kMapper.anlegen(k);
	}

	/**
	 * Beitragstext erstellen
	 * @param text
	 * @throws IllegalArgumentException
	 */
	public void createBeitrag(Beitrag b) throws IllegalArgumentException {
		this.bMapper.anlegen(b);
	}

	/**
	 * Abonnement erstellen
	 * @param abonnement
	 * @throws IllegalArgumentException
	 */
	public void createAbonnement(Abonnement abonnement) throws IllegalArgumentException {
		this.aMapper.anlegen(abonnement);
	}

	/**
	 * Kommentar löschen
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
		this.lMapper.entfernenLikeVon(beitrag);
		this.kMapper.entfernenKommentarVon(beitrag);
		this.bMapper.entfernen(beitrag);
		
	}

	/**
	 * Einen Beitrag liken (Kommentare k�nnen nicht geliked oder kommentiert werden)
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
	 * Auslesen aller Likes. Zur�ckgegeben wird ein Vector der alle Like Objekte der Datenbank enth�lt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Like> getAlleLikes() throws IllegalArgumentException {
		return this.lMapper.suchenAlle();
	}

	/**
	 * Auslesen aller Beitr�ge. Zur�ckgegeben wird ein Vector der alle Beitrags Objekte der Datenbank enth�lt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> getAlleBeitraege() throws IllegalArgumentException {
		return this.bMapper.suchenAlle();
	}

	/**
	 * Auslesen aller Abonnenten. Zur�ckgegeben wird ein Vector der alle Abonnement Objekte der Datenbank enth�lt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Abonnement> getAlleAbonnenten() throws IllegalArgumentException {
		return this.aMapper.suchenAlle();
	}
	
	/**
	 * Auslesen eines einzelnen Beitrag. Zur�ckgegeben wird das Beitrags Objekt
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
	 * Ausgeben aller Abonnenten anhand eines Nutzers
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Abonnement> getAbonnementByNutzer(Nutzer n) throws IllegalArgumentException {
		return this.aMapper.suchenNutzer(n);
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

	/**
	 * Ausgeben aller Beiträge für einen Nutzer
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> getBeitraegeByNutzer(Nutzer n) throws IllegalArgumentException {
		return this.bMapper.suchenNutzer(n);
	}

	/**
	 * Ausgeben aller Kommentare für einen Beitrag
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Kommentar> getKommentarByBeitrag(Beitrag b) throws IllegalArgumentException {
		return this.kMapper.suchenBeitrag(b);
	}

	/**
	 * Ausgeben aller Likes für einen Beitrag
	 * @param b
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Like> getLikeByBeitrag(Beitrag b) throws IllegalArgumentException {
		return this.lMapper.suchenBeitrag(b);
	}

	/**
	 * Ausgeben der Beiträge der Abonnenten und des Nutzers
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> getAlleBeitraegeByNutzer(Nutzer currentNutzer) throws IllegalArgumentException {
		Vector<Beitrag> alleBeitraegeCurrentNutzer = new Vector<Beitrag>();
		
		for(Abonnement a : this.aMapper.suchenNutzer(currentNutzer)){
			for(Beitrag b : this.getBeitraegeByNutzer(this.nMapper.suchenID(a.getNutzerID()))){
				alleBeitraegeCurrentNutzer.add(b);
			}
		}
		
		for(Beitrag bn : this.getBeitraegeByNutzer(currentNutzer)){
			alleBeitraegeCurrentNutzer.add(bn);
		}

		return alleBeitraegeCurrentNutzer;
	}
	

	/**
	 * Gibt einen Vector mit Nutzern aus, welche vom Nutzer abonniert wurden
	 * @param n
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Nutzer> getAbonnementNutzer(Nutzer n) throws IllegalArgumentException {
		Vector<Nutzer> vn = new Vector<Nutzer>();
		for(Abonnement a : this.aMapper.suchenNutzer(n)){
			vn.add(this.nMapper.suchenID(a.getNutzerID()));
		}
		return vn;
	}

	/**
	 * Abonnement loeschen
	 * @param kommentar
	 * @throws IllegalArgumentException
	 */
	public void loeschenAbonnement(int pID, int nID) throws IllegalArgumentException {
		this.aMapper.entfernen(this.aMapper.suchenNutzerIDPinnwandID(pID, nID));		
	}

	/**
	 * Beitrag ändern
	 * @param b
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void updateBeitrag(Beitrag b) throws IllegalArgumentException {
		this.bMapper.aendern(b);
	}

	/**
	 * Nutzer ändern
	 * @param n
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void updateNutzer(Nutzer n) throws IllegalArgumentException {
		this.nMapper.aendern(n);
	}
	
	/**
	 * Kommentar ändern
	 * @param k
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void updateKommentar(Kommentar k) throws IllegalArgumentException {
		this.kMapper.aendern(k);		
	}

	/**
	 * Sortieren eines Beitragsvectors anhand des Erstellungszeitpunktes
	 * @param vb
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Beitrag> sortBeitraege(Vector<Beitrag> vb) throws IllegalArgumentException {
		Beitrag temp = new Beitrag();
		
		//Bubble Sort
		for(int i=1; i<vb.size(); i++) {
			for(int j=0; j<vb.size()-i; j++) {
				if(vb.elementAt(j).getErstellungszeitpunkt().getTime() < vb.elementAt(j+1).getErstellungszeitpunkt().getTime()) {
					temp = vb.elementAt(j);
					vb.setElementAt(vb.elementAt(j+1), j);
					vb.setElementAt(temp, j+1);
				}
				
			}
		}
		
		return vb;
	}

	/**
	 * Überprüfen ob E-Mail Adresse in der Datenbank verfügbar ist
	 * @param mail
	 * @throws IllegalArgumentException
	 */
	public Nutzer checkEmail(String mail) throws IllegalArgumentException {
		return this.nMapper.pruefenEmail(mail);
	}

	/**
	 * Holt den aktuell eingeloggten Nutzer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public String getCurrentUserMail() throws IllegalArgumentException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		return user.getEmail();
	}
}
