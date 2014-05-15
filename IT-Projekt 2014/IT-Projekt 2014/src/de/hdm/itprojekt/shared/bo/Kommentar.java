package de.hdm.itprojekt.shared.bo;


/**
 * Realisierung der Kommentarklasse
 * </br>
 * Ein Kommentar besteht aus einem Text und der Referenz zum Nutzer
 * </br>
 * Der Nutzer ist eine Fremdschlüsselbeziehung
 * @author Schwab
 */
public class Kommentar extends BusinessObject {

	/**
	 * serialVersionUID wird benötigt um eine Art Version festzulegen um bei einer Deserialisierung den Wert der Variable zu vergleichen
	 * </br>weitere Informationen zu Serializable siehe <a href="http://www.zdnet.de/39154667/wissenswertes-zur-serialisierung-von-java-objekten/">Link</a>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Kommentartext als String
	 */
	private String kommentartext = "";
	
	
	/**
	 * Fremdschlüsselbeziehung zu Nutzern
	 */
	private int nutzerID = 0;

	
	/**
	 * Auslesen des Fremdschlüssels der Kommentare
	 * @return kommentartext
	 */
	public String getKommentartext() {
		return kommentartext;
	}

	
	/**
	 * Setzen des Fremdschlüssels der Kommentare
	 * @param kommentartext
	 */
	public void setKommentartext(String kommentartext) {
		this.kommentartext = kommentartext;
	}

	
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
	 * Textuelle Repräsentation der relevanten Inhalte eines Kommentars
	 */
	public String toString(){
		return this.getKommentartext(); // Notwendigkeit überprüfen (Martin Schwab)
	}
	
}
