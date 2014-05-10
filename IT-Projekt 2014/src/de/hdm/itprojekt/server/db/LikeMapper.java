package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.server.db.DBConnectionLocal;
import de.hdm.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Like</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 */
public class LikeMapper {

  /**
   * Die Klasse LikeMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   */
  private static LikeMapper likeMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected LikeMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>LikeMapper.likeMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>LikeMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> LikeMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   */
  
  public static LikeMapper likeMapper() {
    if (likeMapper == null) {
    	likeMapper = new LikeMapper();
    }

    return likeMapper;
  }

  /**
   * Suchen eines Likes mit vorgegebener likeID. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Like-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Like suchenID(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT likeID, nutzerID, erstellungszeitpunkt FROM Like "
          + "WHERE likeID= " + id );

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
    	Like l = new Like();
        l.setId(rs.getInt("id"));
        l.setLikeID(rs.getInt("like"));
        return l;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  
  /**
   * Auslesen aller Likes.
   * 
   * @return Ein Vektor mit Like-Objekten, die sämtliche Konten
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Like> suchenAlle() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Like> result = new Vector<Like>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT likeID, nutzerID, erstellungszeitpunkt FROM like "
          + " ORDER BY erstellungszeitpunkt");

      // Für jeden Eintrag im Suchergebnis wird nun ein Like-Objekt erstellt.
      while (rs.next()) {
    	  Like l = new Like();
          l.setLikeID(rs.getInt("likeID"));
          
        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(l);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  
  /**
   * Einfügen eines <code>Like</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param l das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  
  public Like anlegen(Like l) {
    Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(likeID) AS maxid "
              + "FROM like ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * l erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
            l.setLikeID(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO like (likeID, nutzerID, erstellungszeitpunkt) "
                + "VALUES (" + l.getLikeID() + l.getNutzerID() + "','"
                + l.getErstellungszeitpunkt() + "')");
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
         * explizite Rückgabe von l ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return l;
      }

 
/**
 * Löschen der Daten eines <code>Like</code>-Objekts aus der Datenbank.
 * 
 * @param l das aus der DB zu löschende "Objekt"
 */
public void entfernen(Like l) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    stmt.executeUpdate("DELETE FROM like " + "WHERE likeID=" + l.getId());
  }
  catch (SQLException e) {
    e.printStackTrace();
  }
}

}


