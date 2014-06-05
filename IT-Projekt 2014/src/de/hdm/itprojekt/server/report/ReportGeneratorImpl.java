package de.hdm.itprojekt.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.VerwaltungsklasseImpl;
import de.hdm.itprojekt.shared.ReportGenerator;
import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.report.EinfacherParagraph;
import de.hdm.itprojekt.shared.report.MehrereBeitraegeReport;
import de.hdm.itprojekt.shared.report.MehrnutzerReport;
import de.hdm.itprojekt.shared.report.Report;
import de.hdm.itprojekt.shared.report.ZusammengesetzterParagraph;

/**
 * Implementierung des <code>ReportGenerator</code>-Interface.
 * @author Stefan
 *
 */
@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ein ReportGenerator benoetigt Zugriff auf die Verwaltungsklasse.
	 */
	private Verwaltungsklasse administration = null;
	/**
	 * No-Argument-Konstruktor zur erstellung eines RemoteServiceServlets.
	 * @throws IllegalArgumentException
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {
		
	}
	/**
	   * Initialsierungsmethode.
	   */
	public void init() throws IllegalArgumentException {
		VerwaltungsklasseImpl a = new VerwaltungsklasseImpl();
		a.init();
		this.administration = a;
	}
	
	/**
	 * Auslesen der dazugehörenden Verwarltungsklasse,
	 * @return
	 */
	protected Verwaltungsklasse getVerwaltung(){
	return this.administration;
	}
	
	/**
	 * Setzen der zugehörenden Pinnwand/Nutzer
	 */

	
	protected void addKopfDaten(Report r, Nutzer n){
	
		
	
		// Nutzer nutzer = this.administration.

		ZusammengesetzterParagraph kopfDaten = new ZusammengesetzterParagraph();
		kopfDaten.hinzufuegenUnterParagraph(new EinfacherParagraph(n.getVorname() + " " + n.getNachname()));;
		
		r.setKopfDaten(kopfDaten);
	}

	public MehrereBeitraegeReport createMehrereBeitraegeReport(Nutzer n)
			throws IllegalArgumentException {
		if (this.getVerwaltung() == null)
		return null;
		/*
		 * Leerer Report wird angelegt.
		 */
		MehrereBeitraegeReport result = new MehrereBeitraegeReport();
		//Überschrift des Reports
		result.setTitel("Alle Beiträge eines Nutzers");
		
		this.addKopfDaten (result, n);
		/*
		 * Erstelldatum des Reports
		 */
		result.setErstellungszeit(new Date());
		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten.
		 */
		ZusammengesetzterParagraph titel = new ZusammengesetzterParagraph();
		
		//Name und Vorname des Kunden aufnehmen.
		titel.hinzufuegenUnterParagraph(new EinfacherParagraph (n.getVorname() + "," + n.getNachname()));
		
		//Hinzufügen der Kopfdaten zum Report
		result.setKopfDaten(titel);
		
	
		/*
		 * Befüllen des Reports was musss rein?
		 */
		return result;
		
	}
	
	/**
	 * Erstellen von Alle Beiträge aller Nutzer
	 */

	public MehrnutzerReport createMehrnutzerReport()
			throws IllegalArgumentException {
		if (this.getVerwaltung() == null)
		return null;
		/*
		 * Leeren Report anlegen
		 */
		MehrnutzerReport result = new MehrnutzerReport();
		
		
	
		/**
		 * Titel anlegen.
		 */
		result.setTitel("Alle Beiträge aller Nutzer");
		
		/*
		 * Datum Erstellzeitpunkt
		 */
		result.setErstellungszeit(new Date());
		/*
		 * Die Beiträge aller Nutzer erstellen. Dafür werden alle Beiträge eines Nutzers 
		 * <code>MehrereBeitraegeReport</code> Ausgelesen und nacheinander aufgelistet.
		 */
		Vector<Nutzer> alleNutzer = this.administration.getAlleNutzer();
		
		
		for (Nutzer n : alleNutzer){
			/*
			 * Aufrufen jedes Teilreports
			 */
			result.hinzufuegenTeilReport(this.createMehrereBeitraegeReport(n));
		}
		/*
		 * Report zurückgeben
		 */
		return result;
	}




	

}
