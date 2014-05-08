package de.hdm.itprojekt.shared.bo;


/**
 * Realisierung der Beitragsklasse
 * </br>
 * Ein Beitrag wird durch einen Text dargestellt, welcher geliked und kommentiert werden kann
 * </br>
 * Like und Kommentar sind Fremdschlüssel
 * @author Schwab
 */
public class Beitrag extends BusinessObject {

	/**
	 * serialVersionUID wird benötigt um eine Art Version festzulegen um bei einer Deserialisierung den Wert der Variable zu vergleichen
	 * </br>weitere Informationen zu Serializable siehe <a href="http://www.zdnet.de/39154667/wissenswertes-zur-serialisierung-von-java-objekten/">Link</a>
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Beitragstext als String
	 */
	private String beitragstext = "";
	
	
	/**
	 * Fremdschlüsselbeziehung der Likes
	 */
	private int likeID = 0;
	
	
	/**
	 * Fremdschlüsselbeziehung zu Kommentaren
	 */
	private int kommentarID = 0;

	
	/**
	 * Auslesen des Beitragstextes 
	 * @return text
	 */
	public String getBeitragstext() {
		return beitragstext;
	}

	
	/**
	 * Beitragstext setzen
	 * @param text
	 */
	public void setBeitragstext(String text) {
		this.beitragstext = text;
	}

	
	/**
	 * Auslesen des Fremdschlüssels der Likes
	 * @return likeID
	 */
	public int getLikeID() {
		return likeID;
	}

	
	/**
	 * Setzen des Fremdschlüssels der Likes
	 * @param likeID
	 */
	public void setLikeID(int likeID) {
		this.likeID = likeID;
	}

	
	/**
	 * Auslesen des Fremdschlüssels der Kommentare
	 * @return kommentarID
	 */
	public int getKommentarID() {
		return kommentarID;
	}

	
	/**
	 * Setzen des Fremdschlüssels der Kommentare
	 * @param kommentarID
	 */
	public void setKommentarID(int kommentarID) {
		this.kommentarID = kommentarID;
	}
	
	
	/**
	 * Textuelle Repräsentation der relevanten Inhalte eines Beitrages
	 */
	public String toString(){
		return this.getBeitragstext(); // Notwendigkeit überprüfen (Martin Schwab)
	}
}
