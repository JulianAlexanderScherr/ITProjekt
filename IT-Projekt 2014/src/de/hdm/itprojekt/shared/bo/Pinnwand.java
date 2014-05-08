package de.hdm.itprojekt.shared.bo;

/**
 * Realisierung der Pinnwandklasse
 * </br>
 * Eine Pinnwand enthält nur eine Referenz zu den Beiträgen
 * @author Schwab
 *
 */
public class Pinnwand extends BusinessObject {

	/**
	 * serialVersionUID wird benötigt um eine Art Version festzulegen um bei einer Deserialisierung den Wert der Variable zu vergleichen
	 * </br>weitere Informationen zu Serializable siehe <a href="http://www.zdnet.de/39154667/wissenswertes-zur-serialisierung-von-java-objekten/">Link</a>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fremdschlüsselbeziehung zu Beiträgen
	 */
	private int beitragID = 0;

	
	/**
	 * Auslesen des Fremdschlüssels der Beiträge
	 * @return beitragID
	 */
	public int getBeitragID() {
		return beitragID;
	}

	/**
	 * Setzen des Fremdschlüssels der Beiträge
	 * @param beitragID
	 */
	public void setBeitragID(int beitragID) {
		this.beitragID = beitragID;
	}
	
}
