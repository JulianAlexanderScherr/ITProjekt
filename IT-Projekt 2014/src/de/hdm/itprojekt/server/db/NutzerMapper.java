package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.marian.server.db.Customer;
import de.hdm.marian.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Nutzer</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 */
public class NutzerMapper {

  /**
   * Die Klasse NutzerMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   */
  private static NutzerMapper nutzerMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected NutzerMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>NutzerMapper.nutzerMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>NutzerMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> NutzerMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   */
  
  public static NutzerMapper nutzerMapper() {
    if (nutzerMapper == null) {
      nutzerMapper = new NutzerMapper();
    }

    return nutzerMapper;
  }

  /**
   * Suchen eines Nutzers mit vorgegebener nutzerID. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Nutzer-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Nutzer suchenID(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname FROM nutzer "
          + "WHERE nutzerID= " + id );

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Nutzer n = new Nutzer();
        n.setId(rs.getInt("id"));
        n.setNutzerID(rs.getInt("nutzer"));
        return n;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  
  public Nutzer suchenVorname() {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname FROM nutzer "
	              + "WHERE vorname= " + vorname );
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }

	    return null;
	  }
  
  public Nutzer suchenNachname() {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname FROM nutzer "
	              + "WHERE nachname= " + nachname );
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }

	    return null;
	  }
  
  
  public Nutzer suchenNickname() {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname FROM nutzer "
	              + "WHERE nickname= " + nickname );
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }

	    return null;
	  }
  
  
  /**
   * Auslesen aller Konten.
   * 
   * @return Ein Vektor mit Account-Objekten, die sämtliche Konten
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Nutzer> suchenAlle() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Nutzer> result = new Vector<Nutzer>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname FROM nutzer "
          + " ORDER BY nachname");

      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
      while (rs.next()) {
          Nutzer n = new Nutzer();
          n.setNutzerID(rs.getInt("nutzerID"));
          n.setVorname(rs.getString("vorname"));
          n.setNachname(rs.getString("nachname"));
          n.setNickname(rs.getString("nickname"));
          
        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(n);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  
  /**
   * Einfügen eines <code>Nutzer</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param a das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  
  public Nutzer anlegen(Nutzer n) {
    Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(nutzerID) AS maxid "
              + "FROM nutzer ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * c erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
            n.setNutzerID(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO nutzer (nutzerID, vorname, nachname, nickname) "
                + "VALUES (" + n.getnutzerID() + ",'" + n.getVorname() + "','"
                + n.getNachname() + "', '" + n.getNickname() + "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * Rückgabe, des evtl. korrigierten Nutzers.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte übergeben werden, wäre die Anpassung des Nutzer-Objekts auch
         * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
         * explizite Rückgabe von n ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return n;
      }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param a das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public Nutzer aendern(Nutzer n) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE nutzer " + "SET vorname=\""
              + n.getVorname() + "\", " + "nachanme=\"" + n.getNachname() + "\",  " + "nickname=\"" + n.getNickname() + "\"
              + "WHERE nutzerID=" + n.getNutzerID());
      
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu anlegen(Nutzer n) zu wahren, geben wir n zurück
    return ;
  }

}
