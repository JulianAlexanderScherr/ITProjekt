package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Abonnement;
import de.hdm.itprojekt.shared.bo.Beitrag;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Like;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Pinnwand;


/**
 * Asynchrone KLasse von {@link Verwaltungsklasse}.
 * Für die GWT RPC (Remote Procedure Call) wird ein synchrones und ein asynchrones Interface 
 * ben�tigt um zwischen Client und Server kommunizieren zu k�nnen 
 * </br>
 * Weitere Informationen zu GWT RPC (recht einfache Erkl�rung): Theorie: https://www.youtube.com/watch?v=9FZt7EbMp_E#t=439
 * </br>
 * Praxis: https://www.youtube.com/watch?v=3Ak5xVrvh-E
 * @author Martin
 *
 */
public interface VerwaltungsklasseAsync {

	void init(AsyncCallback<Void> callback);

	void createKommentar(Kommentar k, AsyncCallback<Void> callback);

	void createBeitrag(Beitrag b, AsyncCallback<Void> callback);
	
	void createAbonnement(Abonnement abonnement, AsyncCallback<Void> callback);

	void loeschenBeitrag(Beitrag beitrag, AsyncCallback<Void> callback);
	
	void loeschenKommentar(Kommentar kommentar, AsyncCallback<Void> callback);

	void likeBeitrag(Beitrag beitrag, Nutzer nutzer,AsyncCallback<Void> callback);

	void unlikeBeitrag(Like like, AsyncCallback<Void> callback);

	void getAlleNutzer(AsyncCallback<Vector<Nutzer>> callback);

	void getAlleLikes(AsyncCallback<Vector<Like>> callback);

	void getAlleBeitraege(AsyncCallback<Vector<Beitrag>> callback);

	void getAlleAbonnenten(AsyncCallback<Vector<Abonnement>> callback);

	void getBeitrag(int id, AsyncCallback<Beitrag> callback);

	void getNutzerByID(int id, AsyncCallback<Nutzer> callback);

	void getNutzerByNachname(String nachname,AsyncCallback<Vector<Nutzer>> callback);

	void getNutzerByVorname(String vorname,AsyncCallback<Vector<Nutzer>> callback);

	void getNutzerByNickname(String nickname,AsyncCallback<Vector<Nutzer>> callback);

	void getPinnwand(int id, AsyncCallback<Pinnwand> callback);

	void getKommentar(int id, AsyncCallback<Kommentar> callback);

	void getAbonnement(int id, AsyncCallback<Abonnement> callback);

	void getLike(int id, AsyncCallback<Like> callback);

	void getBeitraegeByNutzer(Nutzer n, AsyncCallback<Vector<Beitrag>> callback);

	void getKommentarByBeitrag(Beitrag b,
			AsyncCallback<Vector<Kommentar>> callback);

	void getLikeByBeitrag(Beitrag b, AsyncCallback<Vector<Like>> callback);

	void createNutzer(Nutzer nutzer, AsyncCallback<Void> callback);

	void updateBeitrag(Beitrag b, AsyncCallback<Void> callback);

	void updateNutzer(Nutzer n, AsyncCallback<Void> callback);

	void loeschenAbonnement(int pID, int nID, AsyncCallback<Void> callback);

	void sortBeitraege(Vector<Beitrag> vb,
			AsyncCallback<Vector<Beitrag>> callback);

	void checkEmail(String mail, AsyncCallback<Nutzer> callback);

	void updateKommentar(Kommentar k, AsyncCallback<Void> callback);

	void getCurrentUserMail(AsyncCallback<String> callback);

	void getAbonnementByNutzer(Nutzer n,
			AsyncCallback<Vector<Abonnement>> callback);

	void getAlleBeitraegeByNutzer(Nutzer n,
			AsyncCallback<Vector<Beitrag>> callback);

	void getAbonnementNutzer(Nutzer n, AsyncCallback<Vector<Nutzer>> callback);

}
