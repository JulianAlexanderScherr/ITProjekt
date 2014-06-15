package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.shared.bo.*;


/**
 * Mapper-Klasse, die <code>Abonnement</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe Objekte gesucht, angelegt, und
 * entfernt werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Thies, Schrempf, Scherr
 */
public class AbonnementMapper {

  /**
   * Die Klasse AbonnementMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>. Das Singleton ist also ein Entwurfstmuster welches sicherstellt,
   * dass nur eine Instanz eines Objekts existiert.  
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   */
  private static AbonnementMapper abonnementMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected AbonnementMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>AbonnementMapper.abonnementMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>AbonnementMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> AbonnementMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   */
  
  public static AbonnementMapper abonnementMapper() {
    if (abonnementMapper == null) {
    	abonnementMapper = new AbonnementMapper();
    }

    return abonnementMapper;
  }

  /**
   * Suchen eines Abonnements mit vorgegebener abonnementID. Da diese eindeutig ist,
   * wird genau ein Objekt zurückgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Abonnement-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Abonnement suchenID(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT abonnementID, nutzerID, pinnwandID FROM Abonnement "
          + "WHERE abonnementID= " + id );

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
    	// Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekt umgewandelt
    	Abonnement a = new Abonnement();
        a.setNutzerID(rs.getInt("nutzerID"));
        a.setId(rs.getInt("abonnementID"));
        a.setPinnwandID(rs.getInt("pinnwandID"));
        return a;
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
  
   public Vector<Abonnement> suchenNutzer(Nutzer n) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    // Ergebnisvektor vorbereiten
	    Vector<Abonnement> result = new Vector<Abonnement>();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt.executeQuery("SELECT abonnementID, pinnwandID, nutzerID FROM abonnement "
	          + "WHERE pinnwandID= " + n.getId());


	      while (rs.next()) {
	        // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst, und in Objekte umgewandelt 
	        Abonnement a = new Abonnement();
	        a.setId(rs.getInt("abonnementID"));
	        a.setPinnwandID(rs.getInt("pinnwandID"));
	        a.setNutzerID(rs.getInt("nutzerID"));
	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(a);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
        // Ergebnisvektor zurückgeben
	    return result;
	  }
   
   /**
    * Auslesen eines Abonnement anhand der Kombination der Pinnwand-ID und der Nutzer-ID
    * 
    * @return einzelnes Abonnenten Object
    */
   
    public Abonnement suchenNutzerIDPinnwandID(int pID, int nID) {
 	    // DB-Verbindung holen
 	    Connection con = DBConnection.connection();

 	    try {
 	      // Leeres SQL-Statement (JDBC) anlegen
 	      Statement stmt = con.createStatement();

 	      // Statement ausfüllen und als Query an die DB schicken
 	      ResultSet rs = stmt.executeQuery("SELECT abonnementID, pinnwandID, nutzerID FROM abonnement "
 	          + "WHERE pinnwandID= " + pID + " AND (nutzerID= " + nID +")");


 	      if (rs.next()) {
 	        Abonnement a = new Abonnement();
 	        a.setId(rs.getInt("abonnementID"));
 	        a.setPinnwandID(rs.getInt("pinnwandID"));
 	        a.setNutzerID(rs.getInt("nutzerID"));
 	        //Abonnenten Objekt zurückgeben
 	        return a;
 	      }
 	    }
 	    catch (SQLException e2) {
 	      e2.printStackTrace();
 	    }
 	    return null;
 	  }

  
  /**
   * Auslesen aller Abonnements.
   * 
   * @return Ein Vektor mit Abonnement-Objekten, die sämtliche Abonnements
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Abonnement> suchenAlle() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Abonnement> result = new Vector<Abonnement>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT abonnementID, nutzerID, pinnwandID FROM Abonnement"
          + " ORDER BY nutzerID");

      // Datenbankergebnis wird als Ergebnis-Tupel zusammengefasst und in Objekte umgewandelt
      while (rs.next()) {
    	  Abonnement a = new Abonnement();
          a.setId(rs.getInt("abonnementID"));
          a.setPinnwandID(rs.getInt("pinnwandID"));
          a.setNutzerID(rs.getInt("nutzerID"));
          
        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(a);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  
  /**
   * Einfügen eines <code>Abonnement</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param a das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  
  public Abonnement anlegen(Abonnement a) {
    Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(abonnementID) AS maxid "
              + "FROM abonnement ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * a erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
            a.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO abonnement (abonnementID, nutzerID, pinnwandID) "
                + "VALUES (" + a.getId() + ",'" + a.getNutzerID() + "','" + a.getPinnwandID() + "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
        return a;
      }

 
/**
 * Entfernen der Daten eines <code>Abonnement</code>-Objekts aus der Datenbank.
 * 
 * @param a das aus der DB zu löschende "Objekt"
 */
public void entfernen(Abonnement a) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    stmt.executeUpdate("DELETE FROM abonnement " + "WHERE abonnementID=" + a.getId());
  }
  catch (SQLException e) {
    e.printStackTrace();
  }
}

}


