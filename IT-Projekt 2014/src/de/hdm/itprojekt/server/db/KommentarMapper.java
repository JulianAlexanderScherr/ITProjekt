package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.marian.server.db.Customer;
import de.hdm.marian.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Beitrag</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 */
public class KommentarMapper {

  /**
   * Die Klasse BeitragMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
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
      ResultSet rs = stmt.executeQuery("SELECT kommentarID, nutzerID, text, erstellungszeitpunkt FROM Kommentar "
          + "WHERE kommentarID= " + id );

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Kommentar k = new Kommentar();
        k.setId(rs.getInt("id"));
        k.setKommentarID(rs.getInt("kommentar"));
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

      ResultSet rs = stmt.executeQuery("SELECT kommentarID, nutzerID, text, erstellungszeitpunkt FROM beitrag "
          + " ORDER BY erstellungszeitpunkt");

      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
      while (rs.next()) {
    	  Kommentar k = new Kommentar();
          k.setKommentarID(rs.getInt("kommentarID"));
          k.setText(rs.getString("text"));
          
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
            k.setKommentarID(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO kommentar (KommentarID, nutzerID, text, erstellungszeitpunkt) "
                + "VALUES (" + k.getKommentarID() + ",'" + k.getText() + "','" + k.getNutzerID() + "','"
                + k.getErstellungszeitpunkt() + "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * Rückgabe, des evtl. korrigierten Kommentars.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte übergeben werden, wäre die Anpassung des Beitrag-Objekts auch
         * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
         * explizite Rückgabe von k ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return k;
      }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param k das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public Kommentar aendern(Kommentar k) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE kommentar " + "SET text=\""
              + n.getText() "\" "
              + "WHERE kommentarID=" + k.getKommentarID());
      
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu anlegen(Kommentar k) zu wahren, geben wir n zurück
    return ;
  }



/**
 * Löschen der Daten eines <code>Kommentar</code>-Objekts aus der Datenbank.
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

}


