package de.hdm.itprojekt.shared.bo;


/**
 * Realisierung der Abonnementklasse
 * </br>
 * Ein Abonnement ist eine Verknüpfung zwischen einer Pinnwand und einem Nutzer,
 * somit werden hier nur Fremndschlüsselbeziehungen realisiert
 * @author Schwab
 */
public class Abonnement extends BusinessObject {

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
	 * Fremdschlüsselbeziehung zur Pinnwand
	 */
	private int pinnwandID = 0;

	
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
	 * Auslesen des Fremdschlüssels der Pinnwand
	 * @return pinnwandID
	 */
	public int getPinnwandID() {
		return pinnwandID;
	}

	
	/**
	 * Setzen des Fremdschlüssels der Pinnwand
	 * @param pinnwandID
	 */
	public void setPinnwandID(int pinnwandID) {
		this.pinnwandID = pinnwandID;
	}
}
