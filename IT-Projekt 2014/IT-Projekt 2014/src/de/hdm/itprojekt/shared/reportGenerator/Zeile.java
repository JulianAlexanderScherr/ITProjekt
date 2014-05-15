package de.hdm.itprojekt.shared.reportGenerator;

import java.io.Serializable;
import java.util.Vector;



public class Zeile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	/**
	 * Speicherplatz für die Spalten der Zeile
	 */
	private Vector<Spalte> spalten = new Vector<Spalte>();
	
	/**
	 * Hinzufügen einer Spalte
	 * @param s das Spaltenobjekt
	 */
	
	public void addSpalte(Spalte s){
		this.spalten.addElement(s);
	}
	
	/**
	 * entfernen einer Spalte
	 * 
	 */
	
	public void removeSpalte (Spalte s){
		this.spalten.removeElement (s);
	}
	
	/**
	 * Auslesen sämtlicher Spalten
	 * @return Vector-Objekt mit sämtlichen Spalten
	 */
	
	public Vector<Spalte> getSpalten(){
		return this.spalten;
	}
	
	/**
	 * Auslesen der Anzahl sämtlicher Spalten
	 * @return int Anzahl der Spalten
	 */
	public int getAnzahlSpalten(){
		return this.spalten.size();
	}
	/**
	 * Gibt eine Einzelne Spalte zurück
	 * @param i = stelle an der die Spalte die zurückgegeben wird steht.
	 * @return
	 */
	public Spalte getSpalteAt(int i){
		return this.spalten.elementAt(i);
		
	}
}