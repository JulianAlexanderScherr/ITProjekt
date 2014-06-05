package de.hdm.itprojekt.shared.report;

import java.util.Vector;



/**
 * <p>
 * Ein einfacher Report, der neben den Informationen der Superklasse <code>
 * Report</code> eine Tabelle mit "Positionsdaten" aufweist. Die Tabelle greift
 * auf zwei Hilfsklassen namens <code>Spalte</code> und <code>Zeile</code> zur�ck.
 * 
 * 
 * @see Spalte
 * @see Zeile
 * @author Stefan Oberrieder vgl. Peter Thies
 */
public abstract class EinfacherReport extends Report {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * Tabelle mit den Positionsdaten welche im <code>Vector</code> abgelegt werden.
 */
private Vector<Zeile> tabelle = new Vector<Zeile>();

/**
 * Hinzuf�gen einer Zeile.
 * 
 * @param r die neue Zeile
 */

public void hinzufuegenZeile (Zeile r){
	this.tabelle.addElement(r);
}
/**
 * l�schen einer Zeile
 * @param r die zu l�schende Zeile.
 */
public void loescheZeile (Zeile r){
	this.tabelle.removeElement(r);
}
/** 
 * Auslesen aller Positionsdaten aus der <code>tabelle</code>
 * @return die Tabelle mit den Positionsdaten.
 */
public Vector<Zeile> getZeile(){
	return this.tabelle;
}


}
