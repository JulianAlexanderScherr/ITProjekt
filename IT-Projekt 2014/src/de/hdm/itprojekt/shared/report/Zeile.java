package de.hdm.itprojekt.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Klasse die aus mehreren Spalten (Einzelnen Werten) eine ganze Zeile erstellt.
 * @author Stefan
 *
 */

public class Zeile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	/**
	 * Speicherplatz f�r die Spalten der Zeile
	 */
	private Vector<Spalte> spalten = new Vector<Spalte>();
	
	/**
	 * Hinzuf�gen einer Spalte
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
	 * Auslesen s�mtlicher Spalten
	 * @return Vector-Objekt mit s�mtlichen Spalten
	 */
	
	public Vector<Spalte> getSpalten(){
		return this.spalten;
	}
	
	/**
	 * Auslesen der Anzahl s�mtlicher Spalten
	 * @return int Anzahl der Spalten
	 */
	public int getAnzahlSpalten(){
		return this.spalten.size();
	}
	/**
	 * Gibt eine Einzelne Spalte zur�ck
	 * @param i = stelle an der die Spalte die zur�ckgegeben wird steht.
	 * @return
	 */
	public Spalte getSpalteAt(int i){
		return this.spalten.elementAt(i);
		
	}
}