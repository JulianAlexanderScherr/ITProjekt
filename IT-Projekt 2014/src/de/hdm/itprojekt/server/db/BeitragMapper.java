package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Beitrag</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, angelegt, geändert,
 * gelöscht und geliked werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 *  @author Thies, Schrempf, Scherr
 */
public class BeitragMapper {

  /**
   * Die Klasse BeitragMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>. Das Singleton ist also ein Entwurfstmuster welches sicherstellt,
   * dass nur eine Instanz eines Objekts existiert.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   */
  private static BeitragMapper beitragMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected BeitragMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>BeitragMapper.beitragMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>BeitragMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> BeitragMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   */
  
  public static BeitragMapper beitragMapper() {
    if (beitragMapper == null) {
      beitragMapper = new BeitragMapper();
    }

    return beitragMapper;
  }

  /**
   * Suchen eines Beitrags mit vorgegebener beitragID. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Beitrag-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Beitrag suchenID(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT beitragID, nutzerID, text, erstellungszeitpunkt FROM beitrag "
          + "WHERE beitragID= " + id );

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
    	// Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekt umgewandelt
        Beitrag b = new Beitrag();
        b.setId(rs.getInt("beitragID"));
        b.setNutzerID(rs.getInt("nutzerID"));
        b.setBeitragstext(rs.getString("text"));
        b.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
        return b;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Beiträge eines Nutzers.
   * 
   * @return Ein Vektor mit Beitrags-Objekten, die sämtliche Beiträge des gleichen Nutzers
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vektor zurückgeliefert.
   */
  
  public Vector<Beitrag> suchenNutzer(Nutzer n) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    // Ergebnisvektor vorbereiten
	    Vector<Beitrag> result = new Vector<Beitrag>();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT beitragID, text, nutzerID, erstellungszeitpunkt FROM beitrag "
	          + "WHERE nutzerID= " + n.getId());


	      while (rs.next()) {
	        // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst, und in Objekte umgewandelt 
	        Beitrag b = new Beitrag();
	        b.setId(rs.getInt("beitragID"));
	        b.setBeitragstext(rs.getString("text"));
	        b.setNutzerID(rs.getInt("nutzerID"));
	        b.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
	        
	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(b);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
        // Ergebnisvektor zurückgeben
	    return result;
	  }
  /**
   * Auslesen aller Beitr�ge.
   * 
   * @return Ein Vektor mit Beitrag-Objekten, die sämtliche Konten
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Beitrag> suchenAlle() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Beitrag> result = new Vector<Beitrag>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT beitragID, text, erstellungszeitpunkt FROM beitrag ");

      // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekte umgewandelt 
      while (rs.next()) {
          Beitrag b = new Beitrag();
          b.setId(rs.getInt("beitragID"));
          b.setBeitragstext(rs.getString("text"));
          b.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
          
        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(b);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  
  /**
   * Einfügen eines <code>Beitrag</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param b das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  
  public Beitrag anlegen(Beitrag b) {
    Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(beitragID) AS maxid "
              + "FROM beitrag ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * b erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
            b.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO beitrag (beitragID, nutzerID, text, erstellungszeitpunkt) "
                + "VALUES (" + b.getId() + ", '" + b.getNutzerID() + "', '" + b.getBeitragstext() + "', '"
                + b.getErstellungszeitpunkt() + "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        return b;
      }

  /**
   * Änderung eines Objekts und Schreiben in die Datenbank.
   * 
   * @param b das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public void aendern(Beitrag b) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE beitrag " + "SET text='" + b.getBeitragstext() 
              + "' WHERE beitragID=" + b.getId());
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }



/**
 * Löschen eines <code>Beitrag</code>-Objekts aus der Datenbank.
 * 
 * @param b das aus der DB zu löschende "Objekt"
 */
public void entfernen(Beitrag b) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    stmt.executeUpdate("DELETE FROM beitrag " + "WHERE beitragID= " + b.getId());
  }
  catch (SQLException e) {
    e.printStackTrace();
  }
}

}


