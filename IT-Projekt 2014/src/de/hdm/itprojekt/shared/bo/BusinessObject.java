package de.hdm.itprojekt.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Die Klasse <b>BusinessObject</b> steht als Basisklasse mit allen anderen 
 * KLassen in diesem Projekt in Verbindung
 * </br>
 * <b>BusinessObject</b> besitzt eine ID, die man in einer relationalen Datenbank auch als Primärschlüssel
 * bezeichnen würde. 
 * </br> 
 * Zudem implementiert diese abstrakte Klasse das Interface {@link Serializable}.
 * Durch diese Eigenschaft kann jedes <b>BusinessObject</b> automatisch in eine textuelle Form überführt und
 * z.B. zwischen Client und Server transportiert werden. 
 * </br>Bei GWT RPC ist diese
 * textuelle Notation in JSON (siehe <a href="http://www.json.org/">http://www.json.org/</a>) kodiert.
 * 
 * @author Thies, Schwab
 * @version 1.0
 */
public abstract class BusinessObject implements Serializable {
	
	/**
	 * serialVersionUID wird benötigt um eine Art Version festzulegen um bei einer Deserialisierung den Wert der Variable zu vergleichen
	 * </br>weitere Informationen zu Serializable siehe <a href="http://www.zdnet.de/39154667/wissenswertes-zur-serialisierung-von-java-objekten/">Link</a>
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * ID = eindeutige Referenz einer Instanz dieser Klasse
	 */
	private int id = 0;
	
	
	/**
	 * bei Erstellung eines Businessobjekts wird der Erstellungszeitpunkt generiert
	 */
	private Timestamp erstellungszeitpunkt = new Timestamp(System.currentTimeMillis());

	
	/**
	 * Auslesen der ID
	 * @return id
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Setzen der ID
	 * @param id
	 */
	public void setId(int id){
		this.id = id;
	}
	
	
	/**
	 * Erstellungszeitpunkt auslesen 
	 * @return erstellungszeitpunkt
	 */
	public Timestamp getErstellungszeitpunkt(){
		return erstellungszeitpunkt;
	}
	
	
	/**
	 * Textuelle Repräsentation der relevanten Inhalte des BusinessObjekts
	 */
	public String toString(){
		/*
	     * Wir geben den Klassennamen gefolgt von der ID des Objekts zurück.
	     * (von Herrn Thies übernommen)
	     */
		return this.getClass().getName() + " #" + this.id;
	}
	
	/**
	 * </br>" == " = Überprüfung ob die zu überprüfende Objekte das selbe Objekt referenzieren (am selben Ort im Speicher) = Wertgleichheit
	 * </br>Unterschied zu:
	 * equals = Überprüfung ob der Inhalt der beiden Objekte gleich ist = (inhaltliche) Objekt-Gleichheit
	 * @return
	 */
	public boolean equals(){
		return false; //muss noch eingefügt werden wenn der Kontext klar ist
	}
	
	
	/**
	 * Normalerweise zur Generierung eines Wertes vom BusinessObjekt benötigt um den Inhalt des BusinessObjektes zu charakterisieren
	 * </br>In diesem Fall wird aber keine HashCode Berechnung benötigit, deshalb wird nur die ID zurückgegeben um dem Rückgabwert int gerecht zu werden
	 */
	public int hashCode(){
		return this.id; //hashcode steht noch nicht fest.. wird evtl nie fest stehen
	}
}
