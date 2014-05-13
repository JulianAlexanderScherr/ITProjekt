package de.hdm.itprojekt.shared.bo;

/**
 * Realisierung der Nutzerklasse 
 * </br>
 * Ein Nutzer besteht aus einem Vorname, Nachnamen und einem Nickname
 * </br>
 * Zudem wird in dieser Klasse eine Referenz zu den Klassen Abonnement und Pinnwand über die jeweilige ID erstellt
 * @author Schwab
 */
public class Nutzer extends BusinessObject {

	/**
	 * serialVersionUID wird benötigt um eine Art Version festzulegen um bei einer Deserialisierung den Wert der Variable zu vergleichen
	 * </br>weitere Informationen zu Serializable siehe <a href="http://www.zdnet.de/39154667/wissenswertes-zur-serialisierung-von-java-objekten/">Link</a>
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Fremdschlüsselbeziehung zum Abonnement
	 */
	private int abonnementID = 0;
	
	
	/**
	 * Fremdschlüsselbeziehung und einmalige Referenz zur Pinnwand
	 */
	private int pinnwandID = 0;
	
	/**
	 * Vorname eines Nutzers
	 */
	private String vorname = "";
	  
	
	/**
	 * Nachname eines Nutzers
	 */
	private String nachname = "";
	
	
	/**
	 * Nickname eines Nutzers
	 */
	private String nickname = "";


	/**
	 * Auslesen des Fremdschlüssels vom Abonnement
	 * @return abonnementID
 	 */
	public int getAbonnementID() {
		return abonnementID;
	}

	/**
	 * Setzen des Fremdschlüssels vom Abonnement
	 * @param abonnementID
	 */
	public void setAbonnementID(int abonnementID) {
		this.abonnementID = abonnementID;
	}
	
	/**
	 * Auslesen des Fremdschlüssels zur Pinnwand 
	 * @return pinnwandID
	 */
	public int getPinnwandID() {
		return pinnwandID;
	}

	/**
	 * Setzen des Fremdschlüssels zur Pinnwand
	 * @param pinnwandID
	 */
	public void setPinnwandID(int pinnwandID) {
		this.pinnwandID = pinnwandID;
	}
	
	
	/**
	 * Vornamen auslesen
	 * @return vorname
	 */
	public String getVorname() {
		return vorname;
	}


	/**
	 * Vorname setzen
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	/**
	 * Nachnamen auslesen
	 * @return nachname
	 */
	public String getNachname() {
		return nachname;
	}


	/**
	 * Nachnamen auslesen
	 * @param nachname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}


	/**
	 * Nickname auslesen
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}


	/**
	 * Nickname setzen
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	/**
	 * Textuelle Repräsentation der relevanten Inhalte des Nutzers in der Form: Vorname Nachname (Nickname)
	 * </br>
	 * Beispiel: Mike Friedrichsen (Turbomike)
	 */
	public String toString(){
		return this.getVorname() + " " + this.getNachname() + " (" + this.getNickname() + ")";
	}
	
}
