package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Beitrag;
import de.hdm.itprojekt.shared.bo.Kommentar;
import de.hdm.itprojekt.server.db.DBConnection;

/**
 * Mapper-Klasse, die <code>Kommentar</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, angelegt, geändert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 *  @author Thies, Schrempf, Scherr
 */
public class KommentarMapper {

  /**
   * Die Klasse BeitragMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>. Das Singleton ist also ein Entwurfstmuster welches sicherstellt,
   * dass nur eine Instanz eines Objekts existiert.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   */
  private static KommentarMapper kommentarMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected KommentarMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>KommentarMapper.kommentarMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>KommentarMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> KommentarMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   */
  
  public static KommentarMapper kommentarMapper() {
    if (kommentarMapper == null) {
    	kommentarMapper = new KommentarMapper();
    }

    return kommentarMapper;
  }

  /**
   * Suchen eines Kommentars mit vorgegebener kommentarID. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Kommentar-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Kommentar suchenID(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT kommentarID, beitragID, nutzerID, text, erstellungszeitpunkt FROM kommentar "
          + "WHERE kommentarID= " + id );

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
    	// Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekt umgewandelt
        Kommentar k = new Kommentar();
        k.setId(rs.getInt("kommentarID"));
        k.setBeitragID(rs.getInt("beitragID"));
        k.setNutzerID(rs.getInt("nutzerID"));
        k.setKommentartext(rs.getString("text"));
        k.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
        return k;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Kommentare eines Beitrages.
   * 
   * @return Ein Vektor mit Kommentar-Objekten, die sämtliche Kommentare des gleichen Beitrages
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vektor zurückgeliefert.
   */
  
  public Vector<Kommentar> suchenBeitrag(Beitrag b) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    //Ergebnisvektor vorbereiten
	    Vector<Kommentar> result = new Vector <Kommentar>();
	    
	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT kommentarID, nutzerID, text, erstellungszeitpunkt FROM kommentar "
	          + "WHERE beitragID= " + b.getId() );


	      while (rs.next()) {
	    	// Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst, und in Objekte umgewandelt 
	        Kommentar k = new Kommentar();
	        k.setId(rs.getInt("kommentarID"));
	        k.setNutzerID(rs.getInt("nutzerID"));
	        k.setKommentartext(rs.getString("text"));
	        k.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
	        
	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(k);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	    // Ergebnisvektor zurückgeben
	    return result;
	  }
  /**
   * Auslesen aller Kommentare.
   * 
   * @return Ein Vektor mit Kommentar-Objekten, die sämtliche Konten
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Kommentar> suchenAlle() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Kommentar> result = new Vector<Kommentar>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT kommentarID, beitragID, nutzerID, text, erstellungszeitpunkt FROM kommentar "
          + " ORDER BY erstellungszeitpunkt");

      // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekte umgewandelt 
      while (rs.next()) {
    	  Kommentar k = new Kommentar();
          k.setId(rs.getInt("kommentarID"));
          k.setBeitragID(rs.getInt("beitragID"));
          k.setNutzerID(rs.getInt("nutzerID"));
          k.setKommentartext(rs.getString("text"));
          k.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
          
        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(k);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  
  /**
   * Einfügen eines <code>Kommentar</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param k das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  
  public Kommentar anlegen(Kommentar k) {
    Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(kommentarID) AS maxid "
              + "FROM kommentar ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * k erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
            k.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO kommentar (kommentarID, beitragID, nutzerID, text, erstellungszeitpunkt) "
                + "VALUES (" + k.getId() + ",'" + k.getBeitragID() + "','" + k.getNutzerID() + "','" 
            	+ k.getKommentartext() + "','" + k.getErstellungszeitpunkt() + "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        return k;
      }

  /**
   * Änderung eines Objekts und Schreiben in die Datenbank.
   * 
   * @param k das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public void aendern(Kommentar k) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE kommentar " + "SET text='"
              + k.getKommentartext()
              + "' WHERE kommentarID=" + k.getId());
      
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }



/**
 * Löschen eines <code>Kommentar</code>-Objekts mithilfe der KommentarID aus der Datenbank.
 * 
 * @param k das aus der DB zu löschende "Objekt"
 */
  
public void entfernen(Kommentar k) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    stmt.executeUpdate("DELETE FROM kommentar " + "WHERE kommentarID=" + k.getId());
  }
  catch (SQLException e) {
    e.printStackTrace();
  }
}

/**
 * Löschen aller <code>Kommentar</code>-Objekte eines Beitrages aus der Datenbank.
 * 
 * @param k das aus der DB zu löschende "Objekt"
 */

public void entfernenKommentarVon(Beitrag b) {
	  Connection con = DBConnection.connection();

	  try {
	    Statement stmt = con.createStatement();

	    stmt.executeUpdate("DELETE FROM kommentar " + "WHERE beitragID=" + b.getId());
	  }
	  catch (SQLException e) {
	    e.printStackTrace();
	  }
	}

}


