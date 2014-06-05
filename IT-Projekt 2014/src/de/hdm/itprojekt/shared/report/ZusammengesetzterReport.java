package de.hdm.itprojekt.shared.report;


import java.io.Serializable;
import java.util.Vector;



/**
 * Klasse erm�glicht das Zusammenstellen eines Reports der aus mehreren Teilreports besteht.
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
 * Hinzuf�gen eines <code>Report</code> wird also zu einem Teilreport.
 * @param r der hinzugef�gte Report
 */
public void hinzufuegenTeilReport(Report r){
	this.teilReport.addElement(r);
}
/**
 * l�schen eines Reports aus dem zusammengesetzten Report.
 * @param r zu l�schender Report
 */
public void loeschenTeilReport(Report r){
 this.teilReport.remove(r);	
}

/**
 * R�ckgabe der Anzahl der Report innerhalb eines Zusammengesetzten Reports
 * @return
 */

public int anzahlTeilReport(){
	return this.teilReport.size();
}

/**
 * R�ckgabe eines einzelnen Reports der an einer Bestimmten Stelle 
 * innerhalb eines Zusammengesetzten Reports steht.
 * @param i Position des Reports in der Vectorliste
 * @return gibt Position innerhalb des Vectors zur�ck.
 */
public Report getTeilReportAt( int i){
	return this.teilReport.elementAt (i);
}

}
