package de.hdm.itprojekt.shared.report;

import java.io.Serializable;
/**
 * Spalte eines <code>Zeile</code>-Objekts. <code>Spalte</code>-Objekte
 * implementieren das <code>Serializable</code>-Interface und k�nnen daher als
 * Kopie �bertragen werden.
 * @author Stefan Oberrieder vgl Peter Thies
 */
public class Spalte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Wert einer Spalte. Entspricht dem Eintrag in einer Zelle der Tabelle
	 */
	private String wert= "";
	
	 /**
	   * <p>
	   * Serialisierbare Klassen, die mittels GWT-RPC transportiert werden sollen,
	   * m�ssen einen No-Argument-Konstruktor besitzen. Ist kein Konstruktor
	   * explizit angegeben, so existiert in Java-Klassen der Default-Konstruktor,
	   * der dem No-Argument-Konstruktor entspricht. (Peter Thies, BankProject)
	   */
	public Spalte(){
		
	}
	
	/**
	 * Konstruktor mit der Angabe eines String-Wertes
	 * @param s String-Wert der dargestellt werden soll.
	 */
	
	public Spalte (String s){
		this.setWert(s);
	}

	/**
	 * Auslesen des Spaltenwertes
	 * @return Spaltenwert
	 */
	public String getWert() {
		return wert;
	}

	/**
	 * Setzen ueberschreiben eines Spaltenwertes
	 * @param wert
	 */
	public void setWert(String wert) {
		this.wert = wert;
	}
	/**
	 * Umwandeln der Spalte in ein String-Objekt
	 */
	
	public String toString(){
		return this.wert;
	}
	
}