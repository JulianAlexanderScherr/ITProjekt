package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.server.db.DBConnection;

import de.hdm.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Nutzer</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe Objekte gesucht, angelegt und geändert 
 * werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Thies, Schrempf, Scherr
 */
public class NutzerMapper {

  /**
   * Die Klasse NutzerMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>. Das Singleton ist also ein Entwurfstmuster welches sicherstellt,
   * dass nur eine Instanz eines Objekts existiert.  
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @author Thies, Schrempf, Scherr
   */
  private static NutzerMapper nutzerMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   * 
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
   * wird genau ein Objekt zurückgegeben.
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
      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname, email, erstellungszeitpunkt, pinnwandID FROM nutzer "
          + "WHERE nutzerID= " + id);
      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
  
      if (rs.next()) {
        // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekt umgewandelt
        Nutzer n = new Nutzer();
        n.setId(rs.getInt("nutzerID"));
        n.setVorname(rs.getString("vorname"));
        n.setNachname(rs.getString("nachname"));
        n.setNickname(rs.getString("nickname"));
        n.seteMail(rs.getString("email"));
        n.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
        n.setPinnwandID(rs.getInt("pinnwandID"));
        return n;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Nutzer nach Vorname.
   * 
   * @return Ein Vektor mit Nutzer-Objekten, die sämtliche Nutzer mit gleichem Vornamen
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vektor zurückgeliefert.
   */
  
  public Vector<Nutzer> suchenVorname(String vorname) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    // Ergebnisvektor vorbereiten, da mehrere Objekte zurückgegeben werden
	    Vector<Nutzer> result = new Vector<Nutzer>();
	    
	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname, email, "
	      		  + "erstellungszeitpunkt, pinnwandID " + "FROM nutzer "
	              + "WHERE vorname LIKE '%" + vorname + "%' ORDER BY vorname");

	      while (rs.next()) {
	          // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst, und in Objekte umgewandelt 
	          Nutzer n = new Nutzer();
	          n.setId(rs.getInt("nutzerID"));
	          n.setVorname(rs.getString("vorname"));
	          n.setNachname(rs.getString("nachname"));
	          n.setNickname(rs.getString("nickname"));
	          n.seteMail(rs.getString("email"));
	          n.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
	          n.setPinnwandID(rs.getInt("pinnwandID"));
	          
	          // Hinzufügen des neuen Objekts zum Ergebnisvektor
	          result.addElement(n);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }
	    
	    // Ergebnisvektor zurückgeben
	    return result;
	  }
  
  /**
   * Auslesen aller Nutzer nach Nachname.
   * 
   * @return Ein Vektor mit Nutzer-Objekten, die sämtliche Nutzer mit  gleichem Nachnamen
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vektor zurückgeliefert.
   */
  
  public Vector<Nutzer> suchenNachname(String nachname) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();
	    
	    // Ergebnisvektor vorbereiten
	    Vector<Nutzer> result = new Vector<Nutzer>();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname, email, "
	      		  + " erstellungszeitpunkt, pinnwandID " + "FROM nutzer "
	              + "WHERE nachname LIKE '" + nachname + "' ORDER BY nachname");
	      
	      while (rs.next()) {
	          // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekte umgewandelt 
	          Nutzer n = new Nutzer();
	          n.setId(rs.getInt("nutzerID"));
	          n.setVorname(rs.getString("vorname"));
	          n.setNachname(rs.getString("nachname"));
	          n.setNickname(rs.getString("nickname"));
	          n.seteMail(rs.getString("email"));
	          n.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
	          n.setPinnwandID(rs.getInt("pinnwandID"));
	          
	          // Hinzufügen des neuen Objekts zum Ergebnisvektor
	          result.addElement(n);
	        }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }
	    
	 // Ergebnisvektor zurückgeben
	    return result;
	  }
  
  /**
   * Auslesen aller Nutzer nach Nickname.
   * 
   * @return Ein Vektor mit Nutzer-Objekten, die sämtliche Nutzer mit gleichem Nickname
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vektor zurückgeliefert.
   */
  
  public  Vector<Nutzer> suchenNickname(String nickname) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();
	    
	    // Ergebnisvektor vorbereiten
	    Vector<Nutzer> result = new Vector<Nutzer>();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname, email, "
	      		  + "erstellungszeitpunkt, pinnwandID " 
	    		  + "FROM nutzer "
	              + "WHERE nickname LIKE '" + nickname + "' ORDER BY nickname");
	      while (rs.next()) {
	          // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekte umgewandelt 
	          Nutzer n = new Nutzer();
	          n.setId(rs.getInt("nutzerID"));
	          n.setVorname(rs.getString("vorname"));
	          n.setNachname(rs.getString("nachname"));
	          n.setNickname(rs.getString("nickname"));
	          n.seteMail(rs.getString("email"));
	          n.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
	          n.setPinnwandID(rs.getInt("pinnwandID"));
	          
	          // Hinzufügen des neuen Objekts zum Ergebnisvektor
	          result.addElement(n);
	        }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }

	 // Ergebnisvektor zurückgeben
	    return result;
	  }
  
  
  /**
   * Auslesen aller Nutzer.
   * 
   * @return Ein Vektor mit Nutzer-Objekten, die sämtliche Nutzer
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vektor zurückgeliefert.
   */
  public Vector<Nutzer> suchenAlle() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Nutzer> result = new Vector<Nutzer>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname, email, "
      	  + "erstellungszeitpunkt, pinnwandID FROM nutzer "
          + " ORDER BY nachname");

   // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekte umgewandelt 
      while (rs.next()) {
          Nutzer n = new Nutzer();
          n.setId(rs.getInt("nutzerID"));
          n.setVorname(rs.getString("vorname"));
          n.setNachname(rs.getString("nachname"));
          n.setNickname(rs.getString("nickname"));
          n.seteMail(rs.getString("email"));
          n.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
          n.setPinnwandID(rs.getInt("pinnwandID"));
          
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
   *
   */
  
  public Nutzer pruefenEmail(String mail) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT nutzerID, vorname, nachname, nickname, email, "
	    		  + "erstellungszeitpunkt, pinnwandID FROM nutzer WHERE email='" + mail + "'");
	      
	      if (rs.next()) {
	    	  Nutzer n = new Nutzer();
	          n.setId(rs.getInt("nutzerID"));
	          n.setVorname(rs.getString("vorname"));
	          n.setNachname(rs.getString("nachname"));
	          n.setNickname(rs.getString("nickname"));
	          n.seteMail(rs.getString("email"));
	          n.setErstellungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
	          n.setPinnwandID(rs.getInt("pinnwandID"));

	          return n;
	      }

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      
	    }
	    return null;
  }
  
  /**
   * Anlegen eines <code>Nutzer</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param n das zu speichernde Objekt
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
             * n erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
            n.setId(rs.getInt("maxid") + 1);
            n.setPinnwandID(rs.getInt("maxid") + 1);

            stmt = con.createStatement();
            
            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO nutzer (nutzerID, vorname, nachname, nickname, email, erstellungszeitpunkt, pinnwandID) "
                + "VALUES (" + n.getId() + ",'" + n.getVorname() + "','"
                + n.getNachname() + "', '" + n.getNickname() + "', '" + n.geteMail() + "', '" + n.getErstellungszeitpunkt() + "', " + n.getPinnwandID() + ")");
            return n;
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        return null;
      }

  /**
   * Änderung eines Objekts und Schreiben in die Datenbank.
   * 
   * @param n das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  
  public void aendern(Nutzer n) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE nutzer " + "SET vorname='"
              + n.getVorname() + "', " + "nachname='" + n.getNachname() + "',  " + "nickname='" + n.getNickname() + "',  " 
    		  + "email='" + n.geteMail() + "',  " + "erstellungszeitpunkt= '" + n.getErstellungszeitpunkt() + "' "
              + "WHERE nutzerID=" + n.getId());
      
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

  }

}
