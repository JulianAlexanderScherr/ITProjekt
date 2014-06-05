package de.hdm.itprojekt.shared.report;

import java.util.Vector;

/**
 * Klasse zum ausgeben und auslesen des Reports in HTML
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
	public void resetReportText(){
		this.reportText = ""; 
	}
/**
 * Umwandeln eines Paragraphen in ein HTML-Objekt
 */
	public String paragraph2HTML (Paragraph p){
		if (p instanceof ZusammengesetzterParagraph){
			return this.paragraph2HTML((ZusammengesetzterParagraph) p);
			}
		else {
			return this.paragraph2HTML((EinfacherParagraph)p);
		}
	}
	/** 
	 * Umwandeln eines Zusammengesetzen Paragraphen in HTML.	
	 */
		public String paragraph2HTML (ZusammengesetzterParagraph p){
			StringBuffer result = new StringBuffer();
			
			for (int i =0; i<p.anazahlParagraphen(); i++){
				result.append("<p>"+ p.getParagraphAt(i)+"</p>");
			}
			return result.toString();
		}
	/**
	 * Umwandeln eines EinfachenParagraphs in HTML
	 */
	public String paragraph2HTML(EinfacherParagraph p) {
		return "<p>"+ p.toString()+ "</p>";
	}
	/**
	 * Generieren des HTML-Headers
	 */
	public String getHeader(){
		StringBuffer result = new StringBuffer();
		result.append("<html><head><title></titel></head><body>");
		return result.toString();
	}
	/**
	 * Generieren des HTML-Trailers
	 * 
	 */
	public String getTrailer(){
		return "</body></html>";
	}
	/**
	 * Ablegen des übergebenen Reports im Zielformat
	 */
	public void process(MehrereBeitraegeReport r) {
		// Aufräumen des vorhergehenden Prozesses
		this.resetReportText();
		StringBuffer result = new StringBuffer();
		result.append("<H1>"+r.getTitel()+"</H1>");
		result.append("<table style=\"width:400px;border:1px solidsilver\"><tr>");
		result.append("td align=\"top\"><b>"+paragraph2HTML(r.getKopfDaten())+"</b><{td<");
		//result.append("<td valign=\"top\">"+"blabla"+"</td>");
		result.append("</tr><tr><td></td><td>"+r.getErstellungszeit().toString()+"</td></tr></table>");
		
		Vector<Zeile> zeilen = r.getZeile();
		result.append("<table style=\"width:400px\">");
		for (int i=0; i < zeilen.size(); i++){
			Zeile zeile= zeilen.elementAt(i);
			result.append("tr>");
			for (int k=0; k<zeile.getAnzahlSpalten(); k++){
				if(i==0){
					result.append("<td style=\"backround:silver;font-weight:bold\">"+zeile.getSpalteAt(k)+"</td>");
				}
				else{ if (i>1){
					result.append("<td style=\"border-top:1px solid silver\">"+ zeile.getSpalteAt(k)+ "</td>");
				}
				else{
					result.append("<td valign=\"top\">"+zeile.getSpalteAt(k)+"</td>");
				}
			}
		}
		result.append("</tr>");	
	    }
		result.append("</table>");
		this.reportText = result.toString();
	}
	
	/**
	 * Ablegen des übergebenen Reports im Zielformat
	 */
	public void process(MehrnutzerReport r) {
		// Aufräumen des vorhergehenden Prozesses
		this.resetReportText();
				
		StringBuffer result = new StringBuffer();
		
		result.append("<H1>"+r.getTitel()+"</H1>");
		result.append("<table style=\"width:400px;border:1px solidsilver\"><tr>");
		result.append("td align=\"top\"><b>"+paragraph2HTML(r.getKopfDaten())+"</b><{td<");
		//result.append("<td valign=\"top\">"+"blabla"+"</td>");
		result.append("</tr><tr><td></td><td>"+r.getErstellungszeit().toString()+"</td></tr></table>");
		/**
		 * Da MerhnutzerReport ein ZusammengesetzterReport aus mehreren MehrereBeiträgeReport ist wird jedesmal der 
		 * Prozess(MehrereBeiträgeReport) aufgerufen und in einem Buffer zusammengefügt. 		
		 */
		for (int i= 0; i< r.anzahlTeilReport(); i++){
		MehrereBeitraegeReport unterReport = (MehrereBeitraegeReport) r.getTeilReportAt(i);
		this.process(unterReport);
		result.append(this.reportText + "\n");
		this.resetReportText();
		}
		this.reportText = result.toString();
	}
	 /**
	   * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
	   */
	public String getReportText(){
		return this.getHeader()+ this.reportText +this.getTrailer();
	}
	
}
