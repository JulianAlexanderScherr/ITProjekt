package de.hdm.itprojekt.shared.report;

/**
 *  Diese Klasse wird benÃ¶tigt, um auf dem Client die ihm vom Server zur
 * VerfÃ¼gung gestellten <code>Report</code>-Objekte in ein menschenlesbares
 * Format zu Ã¼berfÃ¼hren.
 * </p>
 * <p>
 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
 * das Zielformat Ã¼berfÃ¼hrten Information wird den Subklassen Ã¼berlassen. In
 * dieser Klasse werden die Signaturen der Methoden deklariert, die fÃ¼r die
 * Prozessierung der Quellinformation zustÃ¤ndig sind. ( Thies)
 * @author Stefan
 *
 */

public abstract class ReportWriter {

	/**
	 * Die beiden in ein Zielformat umzuwandelnden Reports.
	 * @param r Die Report-Instanz welche übersetzt werden soll
	 */
	public abstract void process( MehrereBeitraegeReport r);
	
	public abstract void process (MehrnutzerReport r);
}
