package de.hdm.itprojekt.shared.reportGenerator;

import java.util.Vector;
import java.io.Serializable;


/**
 * Klasse ermöglicht das Zusammenstellen eines Reports der aus mehreren Teilreports besteht.
 * @author Stefan
 *
 */
public class ZusammengesetzterReport extends Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Gibt die Menge der Teilreports an.
	 */
private Vector<Report> teilReport = new Vector<Report>();

/**
 * Hinzufügen eines <code>Report</code> wird also zu einem Teilreport.
 * @param r der hinzugefügte Report
 */
public void hinzufügenTeilReport(Report r){
	this.teilReport.addElement(r);
}
/**
 * löschen eines Reports aus dem zusammengesetzten Report.
 * @param r zu löschender Report
 */
public void loeschenTeilReport(Report r){
 this.teilReport.remove(r);	
}

/**
 * Rückgabe der Anzahl der Report innerhalb eines Zusammengesetzten Reports
 * @return
 */

public int anzahlTeilReport(){
	return this.teilReport.size();
}

/**
 * Rückgabe eines einzelnen Reports der an einer Bestimmten Stelle 
 * innerhalb eines Zusammengesetzten Reports steht.
 * @param i Position des Reports in der Vectorliste
 * @return gibt Position innerhalb des Vectors zurück.
 */
public Report getTeilReportAt( int i){
	return this.teilReport.elementAt (i);
}

}
