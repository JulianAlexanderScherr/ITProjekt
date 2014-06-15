package de.hdm.itprojekt.shared.report;

import java.io.Serializable;
import java.util.Date;


/**
 * Basisklasse für Reports
 * @author Stefan
 *
 */

public class Report implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Kopfdaten des Berichts
	 */
	
private Paragraph kopfDaten = null;
/**
 * Titel des Berichts
 */
private String titel = "";
/**
 * Zeitpunkt zu dem der Bericht erstellt wurde
 */
private Date erstellungszeit = new Date(System.currentTimeMillis());

/**
 * Auslesen der KopfDaten
 * @return
 */
public Paragraph getKopfDaten() {
	return kopfDaten;
}
/**
 * Setzen der Kopfdaten
 * @param kopfDaten
 */
public void setKopfDaten(Paragraph kopfDaten) {
	this.kopfDaten = kopfDaten;
}
/**
 * Auslesen des Titels
 * @return
 */
public String getTitel() {
	return titel;
}
/**
 * Setzen des Titels
 * @param titel
 */
public void setTitel(String titel) {
	this.titel = titel;
}
/**
 * Auslesen des Erstellzeitpunktes
 * @return
 */
public Date getErstellungszeit() {
	return erstellungszeit;
}
/**
 * Setzen des Erstellzeitpunktes
 * @param erstellungszeit
 */
public void setErstellungszeit(Date erstellungszeit) {
	this.erstellungszeit = erstellungszeit;
}




}
