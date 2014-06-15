package de.hdm.itprojekt.shared.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;

/**
 * Klasse zum ausgeben und auslesen des Reports in HTML
 * 
 * @author Stefan
 * 
 */
public class HTMLReportUebersetzer extends ReportWriter {
	/**
	 * Variable die den auszugebenden Report enthält
	 */
	private String reportText = "";

	/**
	 * Zurücksetzen der Variable
	 */
	public void resetReportText() {
		this.reportText = "";
	}

	/**
	 * Umwandeln eines Paragraphen in ein HTML-Objekt
	 */
	public String paragraph2HTML(Paragraph p) {
		if (p instanceof ZusammengesetzterParagraph) {
			return this.paragraph2HTML((ZusammengesetzterParagraph) p);
		} else {
			return this.paragraph2HTML((EinfacherParagraph) p);
		}
	}

	/**
	 * Umwandeln eines Zusammengesetzen Paragraphen in HTML.
	 */
	public String paragraph2HTML(ZusammengesetzterParagraph p) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < p.anazahlParagraphen(); i++) {
			result.append("<p>" + p.getParagraphAt(i) + "</p>");
		}
		return result.toString();
	}

	/**
	 * Umwandeln eines EinfachenParagraphs in HTML
	 */
	public String paragraph2HTML(EinfacherParagraph p) {
		return "<p>" + p.toString() + "</p>";
	}

	/**
	 * Generieren des HTML-Headers
	 */
	public String getHeader() {
		StringBuffer result = new StringBuffer();
		result.append("<html><head><title></title></head><body>");
		return result.toString();
	}

	/**
	 * Generieren des HTML-Trailers
	 * 
	 */
	public String getTrailer() {
		return "</body></html>";
	}

	/**
	 * Ablegen des übergebenen Reports im Zielformat
	 */
	public void process(MehrereBeitraegeReport r) {
		// Aufräumen des vorhergehenden Prozesses
		this.resetReportText();

		StringBuffer result = new StringBuffer();

		result.append("<h2 style=\"padding: 5px; margin-left: 20px;\">" + r.getTitel() + "</h2>");
		result.append("<table style=\"width:590px; margin-left: 20px; border:1px solid silver\"><tr>");
		result.append("<td align=\"top\" style=\"padding: 5px;\"><b>"
				+ paragraph2HTML(r.getKopfDaten()) + "</b></td>");

		//Timestamp konvertieren
		Date time = new Date();
		time.setTime(r.getErstellungszeit().getTime());
		
		//Zeitformat erstellen
		DateTimeFormat zeitFormat = DateTimeFormat.getFormat("dd.MM.yyyy - HH:mm");

		//Zeit setzen
		String bdatum = zeitFormat.format(time, TimeZone.createTimeZone(-120)) + " Uhr";

		result.append("</tr><tr><td style=\"padding: 5px;\">" + bdatum + "</td></tr></table>");

		Vector<Zeile> zeilen = r.getZeile();
		result.append("<table style=\"width:590px; margin-left: 20px;\">");
		for (int i = 0; i < zeilen.size(); i++) {
			if (zeilen.elementAt(i).getAnzahlSpalten() > 2) {
				Zeile zeile = zeilen.elementAt(i);
				result.append("<tr>");
				for (int k = 0; k < zeile.getAnzahlSpalten(); k++) {
					if (i == 0) {
						result.append("<td style=\"padding: 5px;\">" + zeile.getSpalteAt(k) + "</td>");
					} else if (i > 1) {
						result.append("<td style=\"border-top:1px solid silver; padding: 5px\">"
								+ zeile.getSpalteAt(k) + "</td>");
					} else {
						result.append("<td valign=\"top\" style=\"padding: 5px;\">"
								+ zeile.getSpalteAt(k) + "</td>");
					}
				}
			} else {
				Zeile zeile = zeilen.elementAt(i);
				result.append("<tr>");
				for (int k = 0; k < zeile.getAnzahlSpalten(); k++) {
					if (i == 0) {
						result.append("<td style=\"padding: 5px;\">" + zeile.getSpalteAt(k) + "</td>");
					} else if (i > 0) {
						result.append("<td style=\"border-top: 1px solid silver;padding: 5px;font-weight:bold\">"
								+ zeile.getSpalteAt(k) + "</td>");
					}
				}

			}

			result.append("</tr>");

		}
		result.append("</table>");
		reportText = result.toString();
	}

	/**
	 * Ablegen des übergebenen Reports im Zielformat
	 */
	public void process(MehrnutzerReport r) {
		// Aufräumen des vorhergehenden Prozesses
		this.resetReportText();

		StringBuffer result = new StringBuffer();

		result.append("<h2 style=\"color: #32849B;padding: 5px; margin-left: 20px;\">" + r.getTitel() + "</h2>");
		result.append("<table style=\"width:590px; margin-left: 20px; border:1px solid silver\"><tr>");

		result.append("<td align=\"top\" style=\"padding: 5px;\"><b></b></td>");
		System.out.println("d");
		
		//Timestamp konvertieren
		Date time = new Date();
		time.setTime(r.getErstellungszeit().getTime());
		
		//Zeitformat erstellen
		DateTimeFormat zeitFormat = DateTimeFormat.getFormat("dd.MM.yyyy - HH:mm");

		//Zeit setzen
		String bdatum = zeitFormat.format(time, TimeZone.createTimeZone(-120)) + " Uhr";

		result.append("</tr><tr><td style=\"padding: 5px;\">" + bdatum + "</td></tr></table>");

		/**
		 * Da MerhnutzerReport ein ZusammengesetzterReport aus mehreren
		 * MehrereBeiträgeReport ist wird jedesmal der
		 * Prozess(MehrereBeiträgeReport) aufgerufen und in einem Buffer
		 * zusammengefügt.
		 */
		for (int i = 0; i < r.anzahlTeilReport(); i++) {
			MehrereBeitraegeReport unterReport = (MehrereBeitraegeReport) r
					.getTeilReportAt(i);
			process(unterReport);
			result.append(this.reportText + "\n");
			resetReportText();
		}
		this.reportText = result.toString();

	}

	/**
	 * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
	 */
	public String getReportText() {

		return this.getHeader() + this.reportText + this.getTrailer();
	}

}
