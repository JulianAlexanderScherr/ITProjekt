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
 * @author Schwab
 *
 */
public class VerwaltungsklasseImpl extends RemoteServiceServlet
implements Verwaltungsklasse{
	
	/**
	 * serialVersionUID wird benötigt um eine Art Version festzulegen um bei einer Deserialisierung den Wert der Variable zu vergleichen
	 * </br>weitere Informationen zu Serializable siehe <a href="http://www.zdnet.de/39154667/wissenswertes-zur-serialisierung-von-java-objekten/">Link</a>
	 */
	private static final long serialVersionUID = 1L;

	private NutzerMapper nMapper = null;
	
	private BeitragMapper bMapper = null;
	
	private KommentarMapper kMapper = null;
	
	private AbonnementMapper aMapper = null;
	
	private LikeMapper lMapper = null;
	
	private PinnwandMapper pMapper = null;
	
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
	
	
	@Override
	public void setPinnwand(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setKommentar(String text, Beitrag beitrag)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBeitrag(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAbonnement(Abonnement abonnement)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loeschenKommentar(Kommentar kommentar)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loeschenBeitrag(Beitrag beitrag)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void likeBeitrag(Beitrag beitrag, Nutzer nutzer)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlikeBeitrag(Beitrag beitrag, Nutzer nutzer)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Nutzer> getAlleNutzer() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Like> getAlleLikes() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Beitrag> getAlleBeitraege() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Abonnement> getAlleAbonnenten()
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<BusinessObject> getAlleBusinessObjects()
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beitrag getBeitrag(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public Nutzer getNutzerByID(int id) throws IllegalArgumentException {
		return nMapper.suchenID(id);
	}

	@Override
	public Vector<Nutzer> getNutzerByNachname(String nachname)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Nutzer> getNutzerByVorname(String vorname)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Nutzer> getNutzerByNickname(String nickname)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pinnwand getPinnwand(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kommentar getKommentar(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Abonnement getAbonnement(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Like getLike(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	//wichtig !!
}
