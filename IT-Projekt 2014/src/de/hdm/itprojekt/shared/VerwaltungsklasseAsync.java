package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Beitrag;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Nutzer;

public interface VerwaltungsklasseAsync {

	void init(AsyncCallback<Void> callback);

	void setPinnwand(Nutzer nutzer, AsyncCallback<Void> callback);

	void setKommentar(String text, Beitrag beitrag, AsyncCallback<Void> callback);

	void loeschenKommentar(Kommentar kommentar, AsyncCallback<Void> callback);

	void setBeitrag(String text, AsyncCallback<Void> callback);

	void loeschenBeitrag(Beitrag beitrag, AsyncCallback<Void> callback);

	void likeBeitrag(Beitrag beitrag, Nutzer nutzer,
			AsyncCallback<Void> callback);

	void getBeitrag(int id, AsyncCallback<Beitrag> callback);

	void unlikeBeitrag(Beitrag beitrag, Nutzer nutzer,
			AsyncCallback<Void> callback);

	void getAlleNutzer(AsyncCallback<Vector<Nutzer>> callback);

}
