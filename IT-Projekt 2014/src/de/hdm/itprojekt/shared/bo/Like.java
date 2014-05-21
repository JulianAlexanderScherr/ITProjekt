package de.hdm.itprojekt.shared.bo;


/**
 * Realisierung der Likeklasse
 * </br>
 * Ein Like besteht nur aus einer Referenz zum jeweiligen Nutzer
 * </br>
 * Der Nutzer ist eine Fremdschlüsselbeziehung
 * @author Schwab
 */
public class Like extends BusinessObject {

	/**
	 * serialVersionUID wird benötigt um eine Art Version festzulegen um bei einer Deserialisierung den Wert der Variable zu vergleichen
	 * </br>weitere Informationen zu Serializable siehe <a href="http://www.zdnet.de/39154667/wissenswertes-zur-serialisierung-von-java-objekten/">Link</a>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fremdschlüsselbeziehung zu Nutzern
	 */
	private int nutzerID = 0;
	
	/**
	 * Fremdschlüsselbeziehung zu Beiträgen
	 */
	private int beitragID = 0;
	
	/**
	 * Auslesen des Fremdschlüssels der Nutzer
	 * @return nutzerID
	 */
	public int getNutzerID() {
		return nutzerID;
	}

	/**
	 * Setzen des Fremdschlüssels der Nutzer
	 * @param nutzerID
	 */
	public void setNutzerID(int nutzerID) {
		this.nutzerID = nutzerID;
	}

	/**
	 * Auslesen des Fremdschlüssels der Beiträge
	 * @return nutzerID
	 */
	public int getBeitragID() {
		return beitragID;
	}

	/**
	 * Setzen des Fremdschlüssels der Beiträge
	 * @param nutzerID
	 */
	public void setBeitragID(int beitragID) {
		this.beitragID = beitragID;
	}
}
