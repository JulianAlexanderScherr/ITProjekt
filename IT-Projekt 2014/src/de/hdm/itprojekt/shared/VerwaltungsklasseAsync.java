package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Beitrag;
import de.hdm.itprojekt.shared.bo.BusinessObject;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Like;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;


/**
 * Asynchrone KLasse von {@link Verwaltungsklasse}.
 * Für die GWT RPC (Remote Procedure Call) wird ein synchrones und ein asynchrones Interface 
 * benötigt um zwischen Client und Server kommunizieren zu können 
 * </br>
 * Weitere Informationen zu GWT RPC (recht einfache Erklärung): Theorie: https://www.youtube.com/watch?v=9FZt7EbMp_E#t=439
 * </br>
 * Praxis: https://www.youtube.com/watch?v=3Ak5xVrvh-E
 * @author Martin
 *
 */
public interface VerwaltungsklasseAsync {

	void init(AsyncCallback<Void> callback);

	void setPinnwand(Nutzer nutzer, AsyncCallback<Void> callback);

	void setKommentar(String text, Beitrag beitrag, AsyncCallback<Void> callback);

	void setBeitrag(String text, AsyncCallback<Void> callback);
	
	void setAbonnement(Abonnement abonnement, AsyncCallback<Void> callback);

	void loeschenBeitrag(Beitrag beitrag, AsyncCallback<Void> callback);
	
	void loeschenKommentar(Kommentar kommentar, AsyncCallback<Void> callback);

	void likeBeitrag(Beitrag beitrag, Nutzer nutzer,AsyncCallback<Void> callback);

	void unlikeBeitrag(Beitrag beitrag, Nutzer nutzer,AsyncCallback<Void> callback);

	void getAlleNutzer(AsyncCallback<Vector<Nutzer>> callback);

	void getAlleLikes(AsyncCallback<Vector<Like>> callback);

	void getAlleBeitraege(AsyncCallback<Vector<Beitrag>> callback);

	void getAlleAbonnenten(AsyncCallback<Vector<Abonnement>> callback);

	void getAlleBusinessObjects(AsyncCallback<Vector<BusinessObject>> callback);

	void getBeitrag(int id, AsyncCallback<Beitrag> callback);

	void getNutzerByID(int id, AsyncCallback<Nutzer> callback);

	void getNutzerByNachname(String nachname,AsyncCallback<Vector<Nutzer>> callback);

	void getNutzerByVorname(String vorname,AsyncCallback<Vector<Nutzer>> callback);

	void getNutzerByNickname(String nickname,AsyncCallback<Vector<Nutzer>> callback);

	void getPinnwand(int id, AsyncCallback<Pinnwand> callback);

	void getKommentar(int id, AsyncCallback<Kommentar> callback);

	void getAbonnement(int id, AsyncCallback<Abonnement> callback);

	void getLike(int id, AsyncCallback<Like> callback);

}
