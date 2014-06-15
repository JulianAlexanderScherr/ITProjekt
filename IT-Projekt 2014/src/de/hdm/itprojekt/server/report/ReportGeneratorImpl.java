package de.hdm.itprojekt.server.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.VerwaltungsklasseImpl;
import de.hdm.itprojekt.shared.ReportGenerator;
import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.bo.Beitrag;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.shared.bo.Like;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.report.EinfacherParagraph;
import de.hdm.itprojekt.shared.report.MehrereBeitraegeReport;
import de.hdm.itprojekt.shared.report.MehrnutzerReport;
import de.hdm.itprojekt.shared.report.Report;
import de.hdm.itprojekt.shared.report.Spalte;
import de.hdm.itprojekt.shared.report.Zeile;
import de.hdm.itprojekt.shared.report.ZusammengesetzterParagraph;


/**
 * Implementierung des <code>ReportGenerator</code>-Interface.
 * @author Stefan
 *
 */
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
	
	/**
	 * Erstellen eines Reports über einen Nutzer hierzu werden der Nutzer n sowie zwei Daten mit denen 
	 * die Suchauswahl eingegrenzt werden kann. Außerdem wird ein String auswahl übergeben. Mit diesem wird
	 * entschieden nach was der Report gefiltert werden soll.
	 * 
	 */
	public MehrereBeitraegeReport createMehrereBeitraegeReport(Date az, Date ez, Nutzer n, String auswahl)
			throws IllegalArgumentException {
		if (this.getVerwaltung() == null){
			return null;
		}
		
		/*
		 * Leerer Report wird angelegt.
		 */
		MehrereBeitraegeReport result = new MehrereBeitraegeReport();
		//Überschrift des Reports
		result.setTitel("Report von "+ n.getNickname());
		
		this.addKopfDaten (result, n);

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten.
		 */
		ZusammengesetzterParagraph titel = new ZusammengesetzterParagraph();
		
		//Name und Vorname des Nutzers aufnehmen.
		titel.hinzufuegenUnterParagraph(new EinfacherParagraph (n.getVorname() + "," + n.getNachname()));
		
		//Hinzufügen der Kopfdaten zum Report
		result.setKopfDaten(titel);
		
		
		
		
		
		/**
		 * Beitrags Vector in dem alle Beiträge des gefählten Nutzers stehen wird übergeben.
		 * über Date az und Date ez werden die Beiträge auf die gewünschte Zeit eingeschränkt und in
		 * den neuen Vector <code>beitragSort</code> übergeben.
		 * Diesen Vector übergeben wir der <code>VerwaltungsklasseImpl</code> um die Beiträge in sortierter 
		 * Reihenfolge zu erhalten.
		 */
		
		Vector<Beitrag> beitrag = this.administration.getBeitraegeByNutzer(n);
		Vector<Beitrag> beitragSort = new Vector<Beitrag>();
		for (Beitrag b: beitrag){
			if(b.getErstellungszeitpunkt().after(az)){
				if(b.getErstellungszeitpunkt().before(ez)){
					beitragSort.addElement(b);
				}
			}
		}
		this.administration.sortBeitraege(beitragSort);
		
		
		
		/**
		 * Befüllen des Reports mit Anzahl der Likes
		 * Zuerst Anlegen einer Variable zum Ablegen der Anzahl eines Likes
		 */
		int anzahlLikes = 0;
/**
 * Schleife die jeden Beitrag anspricht und die Länge des Like-Vectors abfrägt.
 */
		for (Beitrag b : beitragSort) {
			 Vector<Like> like = this.administration.getLikeByBeitrag(b);
			 /**
			  * Die länge des Like-Vectors wird mit 1 addiert da der Vector bei 0 beginnt.
			  * zusätzlich wird der Aktuelle Stand der anzahlLike-Variable hinzu addiert um so auf die gesamtanzahl der Likes eines
			  * Nutzers zu kommen.
			  */
			 anzahlLikes = anzahlLikes + like.size();			 
		 }
		/**
		 * Das Ergebniss wird nun in eine Zeile geschrieben.
		 */
		Zeile likeZeile = new Zeile();
		likeZeile.addSpalte(new Spalte(String.valueOf("Anzahl erhaltener Likes: ")));
		likeZeile.addSpalte(new Spalte(String.valueOf(anzahlLikes)));
		
		
		
		/**
		 * Befüllen des Reports mit der Anzahl aller Beiträge die ein Nutzer gemacht hat.
		 */
		Zeile anzBeitragZeile = new Zeile();
		anzBeitragZeile.addSpalte(new Spalte(String.valueOf("Anzahl Beiträge: ")));
		anzBeitragZeile.addSpalte(new Spalte(String.valueOf(beitragSort.size())));
		
		/**
		 * Befüllen des Reports mit Anzahl der Kommentare die ein Nutzer für seine Beiträge bekommen hat.
		 */
		int anzahlKommentare = 0;

		for (Beitrag b : beitragSort) {
			 Vector<Kommentar> kommentar = this.administration.getKommentarByBeitrag(b);
			 anzahlKommentare = anzahlKommentare + kommentar.size();			 
		 }
		
		Zeile anzkommentarZeile = new Zeile();
		anzkommentarZeile.addSpalte(new Spalte(String.valueOf("Anzahl erhaltener Kommentare: ")));
		anzkommentarZeile.addSpalte(new Spalte(String.valueOf(anzahlKommentare)));
		
		/**
		 * Ab hier wird ausgewähl nach was der Report Generator asugeben soll.
		 * Die Auswahl besteht aus Likes, Kommentar, Beitrag oder Alles
		 * Je nachdem was gewählt wurde wird die gewünschte Zeile an den Report angehängt.
		 */
		String like = "Likes";
		String kommentar = "Kommentar";
		String beitr = "Beitrag";
		if(auswahl.equals(like)){
			result.hinzufuegenZeile(likeZeile);
		}
		else {if (auswahl.equals(kommentar)){
			result.hinzufuegenZeile(anzkommentarZeile);
			/**
			 * Es wird jeder Beitrag des Beitrags-Vector nach einem Kommentar-Vector befragt.
			 */
			for (Beitrag b : beitragSort) {
				Vector<Kommentar> kommentar2 = this.administration.getKommentarByBeitrag(b);
				/**
				 * Aus dem Kommentar-Vector wird dann jeder Kommentartext ausgelesen und an die <code>kommentarZeile</code> gehängt.
				 */
				for (Kommentar k : kommentar2){
					Zeile kommentarZeile = new Zeile();
					kommentarZeile.addSpalte( new Spalte(String.valueOf(k.getKommentartext())));
					/**
					 * Zusätzlich wird über die am Kommentar hängende Nutzer-ID des Kommentar-erstellers der Vor- und Nachname des 
					 * Erstellers der Zeile beigefügt. 
					 */
					Nutzer nutzer = this.administration.getNutzerByID(k.getNutzerID());
					kommentarZeile.addSpalte(new Spalte("von "+nutzer.getVorname()+" "+nutzer.getNachname()));
					/**
					 * Anhängen des Erstellungszeitpunktes des Kommentars.
					 */
				
						//Timestamp konvertieren
					  long time = k.getErstellungszeitpunkt().getTime();
			
					  //Zeitformat erstellen
					  SimpleDateFormat zeitFormat = new SimpleDateFormat("dd.MM.yyyy - hh:mm");
			
					  //Beitragsdatum setzen
					  String bdatum = zeitFormat.format(new Date(time));
				      // Zweite Spalte: Erstellungszeitpunkt hinzufügen
				      kommentarZeile.addSpalte(new Spalte(bdatum));
				      
				      
			result.hinzufuegenZeile(kommentarZeile);
				}
			}
			
		}
		
		
		else {if (auswahl.equals(beitr)){
			result.hinzufuegenZeile(anzBeitragZeile);
		/**
		 * Wie zuvor beim Kommentar werden hier die Beiträge ausgelesen und dem Report-Objekt angehängt.
		 */
			for (Beitrag b : beitragSort) {
		      // Eine leere Zeile anlegen.
		      Zeile beitragZeile = new Zeile();

		      // Erste Spalte: Beitragstext hinzufügen
		      beitragZeile.addSpalte(new Spalte(String.valueOf(b.getBeitragstext())));

		      //Timestamp konvertieren
			  long time = b.getErstellungszeitpunkt().getTime();
	
			  //Zeitformat erstellen
			  SimpleDateFormat zeitFormat = new SimpleDateFormat("dd.MM.yyyy - hh:mm");
	
			  //Beitragsdatum setzen
			  String bdatum = zeitFormat.format(new Date(time));
		      // Zweite Spalte: Erstellungszeitpunkt hinzufügen
		      beitragZeile.addSpalte(new Spalte(bdatum));

		      // und schließlich die Zeile dem Report hinzufügen.
		      result.hinzufuegenZeile(beitragZeile);
		}
		}
		else { 
			/** 
			 * Falls "Alles" als Auswahlkritärium gewählt wurde Ruft die if-Abfrage diesen Absatz 
			 * auf und fügt dem Report alle möglichen Ausgaben bei.
			 */
			result.hinzufuegenZeile(likeZeile);
			result.hinzufuegenZeile(anzkommentarZeile);
			result.hinzufuegenZeile(anzBeitragZeile);
			
			for (Beitrag b : beitragSort) {
		      // Eine leere Zeile anlegen.
		      Zeile beitragZeile = new Zeile();

		      // Erste Spalte: Beitragstext hinzufügen
		      beitragZeile.addSpalte(new Spalte(String.valueOf(b.getBeitragstext())));
		      
		      //Timestamp konvertieren
			  long time = b.getErstellungszeitpunkt().getTime();
	
			  //Zeitformat erstellen
			  SimpleDateFormat zeitFormat = new SimpleDateFormat("dd.MM.yyyy - hh:mm");
	
			  //Beitragsdatum setzen
			  String bdatum = zeitFormat.format(new Date(time));
		      // Zweite Spalte: Erstellungszeitpunkt hinzufügen
		      beitragZeile.addSpalte(new Spalte(bdatum));

		      // und schließlich die Zeile dem Report hinzufügen.
		      result.hinzufuegenZeile(beitragZeile);
		      Vector<Kommentar> kommentar2 = this.administration.getKommentarByBeitrag(b);
				for (Kommentar k : kommentar2){
					Zeile kommentarZeile = new Zeile();
					Nutzer nutzer = this.administration.getNutzerByID(k.getNutzerID());
					kommentarZeile.addSpalte( new Spalte(String.valueOf(k.getKommentartext())));
					kommentarZeile.addSpalte(new Spalte("Kommentar von "+nutzer.getVorname()+" "+nutzer.getNachname()+":"));
					
					
					
				
						//Timestamp konvertieren
					  long time1 = k.getErstellungszeitpunkt().getTime();
			
					  //Zeitformat erstellen
					  SimpleDateFormat zeitFormat1 = new SimpleDateFormat("dd.MM.yyyy - hh:mm");
			
					  //Beitragsdatum setzen
					  String kdatum = zeitFormat1.format(new Date(time1));
				      // Zweite Spalte: Erstellungszeitpunkt hinzufügen
				      kommentarZeile.addSpalte(new Spalte(kdatum));
				
				      
			result.hinzufuegenZeile(kommentarZeile);
				}
		}
		}}
		
		
		}
		
		return result;
	    }
		
		
		
		
		
		
		
		
	
	
	/**
	 * Erstellen von Alle Beiträge aller Nutzer
	 * 
	 */

	public MehrnutzerReport createMehrnutzerReport(Date az, Date ez, String auswahl)
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
		result.setTitel("Report aller Nutzer");
		
	
		/*
		 * Die Beiträge aller Nutzer erstellen. Dafür werden alle Beiträge eines Nutzers 
		 * <code>MehrereBeitraegeReport</code> Ausgelesen und nacheinander aufgelistet.
		 */
		Vector<Nutzer> alleNutzer = this.administration.getAlleNutzer();
		
		
		for (Nutzer n : alleNutzer){
			
			/*
			 * Aufrufen jedes Teilreports
			 */
			result.hinzufuegenTeilReport(this.createMehrereBeitraegeReport(az, ez, n, auswahl));
		}
		/*
		 * Report zurückgeben
		 */
		return result;
	}
	
	




	

}
