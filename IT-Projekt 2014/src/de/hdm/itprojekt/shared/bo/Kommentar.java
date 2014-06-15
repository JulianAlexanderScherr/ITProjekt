package de.hdm.itprojekt.shared.bo;


/**
 * Realisierung der Kommentarklasse
 * </br>
 * Ein Kommentar besteht aus einem Text und der Referenz zum Nutzer
 * </br>
 * Der Nutzer ist eine Fremdschl�sselbeziehung
 * @author Schwab
 */
public class Kommentar extends BusinessObject {

	/**
	 * serialVersionUID wird ben�tigt um eine Art Version festzulegen um bei einer Deserialisierung den Wert der Variable zu vergleichen
	 * </br>weitere Informationen zu Serializable siehe <a href="http://www.zdnet.de/39154667/wissenswertes-zur-serialisierung-von-java-objekten/">Link</a>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Kommentartext als String
	 */
	private String kommentartext = "";
	
	/**
	 * Fremdschl�sselbeziehung zu Nutzern
	 */
	private int nutzerID = 0;

	/**
	 * Fremdschlüsselbeziehung zu Beitrag 
	 */
	private int beitragID = 0;	
	
	/**
	 * Auslesen des Fremdschl�ssels der Kommentare
	 * @return kommentartext
	 */
	public String getKommentartext() {
		return kommentartext;
	}

	
	/**
	 * Setzen des Fremdschl�ssels der Kommentare
	 * @param kommentartext
	 */
	public void setKommentartext(String kommentartext) {
		this.kommentartext = kommentartext;
	}

	
	/**
	 * Auslesen des Fremdschl�ssels der Nutzer
	 * @return nutzerID
	 */
	public int getNutzerID() {
		return nutzerID;
	}

	
	/**
	 * Setzen des Fremdschl�ssels der Nutzer
	 * @param nutzerID
	 */
	public void setNutzerID(int nutzerID) {
		this.nutzerID = nutzerID;
	}
	
	
	/**
	 * Textuelle Repr�sentation der relevanten Inhalte eines Kommentars
	 */
	public String toString(){
		return this.getKommentartext(); // Notwendigkeit �berpr�fen (Martin Schwab)
	}

	/**
	 * Auslesen des Fremdschl�ssels der Beiträge
	 * @return beitragID
	 */
	public int getBeitragID() {
		return beitragID;
	}


	public void setBeitragID(int beitragID) {
		this.beitragID = beitragID;
	}
	
}
