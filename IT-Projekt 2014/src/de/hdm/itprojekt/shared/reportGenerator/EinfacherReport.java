package de.hdm.itprojekt.shared.reportGenerator;

import java.util.Vector;



/**
 * <p>
 * Ein einfacher Report, der neben den Informationen der Superklasse <code>
 * Report</code> eine Tabelle mit "Positionsdaten" aufweist. Die Tabelle greift
 * auf zwei Hilfsklassen namens <code>Reihe</code> und <code>Zeile</code> zurück.
 * 
 * 
 * @see Reihe
 * @see Zeile
 * @author Stefan Oberrieder vgl. Peter Thies
 */
public abstract class EinfacherReport extends Report {

/**
 * Tabelle mit den Positionsdaten welche im <code>Vector</code> abgelegt werden.
 */
private Vector<Reihe> tabelle = new Vector<Reihe>();

/**
 * Hinzufügen einer Zeile.
 * 
 * @param r die neue Zeile
 */

public void hinzufuegenReihe (Reihe r){
	this.tabelle.addElement(r);
}
/**
 * löschen einer Zeile
 * @param r die zu löschende Zeile.
 */
public void loescheReihe (Reihe r){
	this.tabelle.removeElement(r);
}
/** 
 * Auslesen aller Positionsdaten aus der <code>tabelle</code>
 * @return die Tabelle mit den Positionsdaten.
 */
public Vector<Reihe> getReihe(){
	return this.tabelle;
}


}
