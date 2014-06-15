package de.hdm.itprojekt.server.db;

import java.sql.*;

import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.shared.bo.Pinnwand;


/**
 * Mapper-Klasse, die <code>Pinnwand</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe Objekte gesucht, und
 * angelegt werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 */
public class PinnwandMapper {

  /**
   * Die Klasse PinnwandMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>. Das Singleton ist also ein Entwurfstmuster welches sicherstellt,
   * dass nur eine Instanz eines Objekts existiert.  
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
   * wird genau ein Objekt zurückgegeben.
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
    	// Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekt umgewandelt
        Pinnwand p = new Pinnwand();
        p.setId(rs.getInt("pinnwandID"));
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
          stmt = con.createStatement();

          // Jetzt erst erfolgt die tatsächliche Einfügeoperation
          stmt.executeUpdate("INSERT INTO pinnwand (pinnwandID) "
              + "VALUES (" + p.getId() + ")");
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
        return p;
      }
}
