package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.server.db.DBConnectionLocal;
import de.hdm.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Pinnwand</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 */
public class PinnwandMapper {

  /**
   * Die Klasse PinnwandMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   */
  private static PinnwandMapper pinnwandMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected PinnwandMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>PinnwandMapper.pinnwandMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>PinnwandMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> pinnwandMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   */
  
  public static PinnwandMapper pinnwandMapper() {
    if (pinnwandMapper == null) {
      pinnwandMapper = new PinnwandMapper();
    }

    return pinnwandMapper;
  }

  /**
   * Suchen einer Pinnwand mit vorgegebener pinnwandID. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Pinnwand-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Pinnwand suchenID(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT pinnwandID FROM pinnwand "
          + "WHERE pinnwandID= " + id );

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Pinnwand p = new Pinnwand();
        p.setId(rs.getInt("id"));
        p.setPinnwandID(rs.getInt("pinnwand"));
        return p;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  
 
  /**
   * Einfügen eines <code>Pinnwand</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param p das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  
  public Pinnwand anlegen(Pinnwand p) {
    Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(pinnwandID) AS maxid "
              + "FROM pinnwand ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * c erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
            p.setPinnwandID(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO pinnwand (pinnwandID) "
                + "VALUES (" + p.getPinnwandID() + ")");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * Rückgabe, des evtl. korrigierten Pinnwand.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte übergeben werden, wäre die Anpassung des Nutzer-Objekts auch
         * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
         * explizite Rückgabe von n ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return p;
      }

  

}
