package de.hdm.itprojekt.server.db;

import java.sql.*;

public class DBConnectionLocal { 
    
    public static void main(String[] args) { 
 
        // Diese Eintraege werden zum 
        // Verbindungsaufbau benoetigt. 
        final String hostname = "localhost"; 
        final String port = "3306"; 
        final String dbname = "socialmediapinnwand"; 
        final String user = "root@localhost"; 
        final String password = "";
    }
        private static Connection con = null
	
        try { 
	    System.out.println("* Treiber laden"); 
      	    Class.forName(("com.mysql.jdbc.Driver").newInstance(); 
        } 
        catch (Exception e) { 
            System.err.println("Treiber kann nicht geladen werden!"); 
            e.printStackTrace(); 
        } 
        try { 
	    System.out.println("* Verbindung aufbauen"); 
	    con = DriverManager.getConnection("jdbc:mysql://localhost/socialmediapinnwand","",""); 
	    
	    System.out.println("* Datenbank-Verbindung beenden"); 
	    conn.close(); 
        } 
        catch (Exception e) { 
            System.out.println("Exception Fehler: " + e.getMessage()); 
         
        } 
        return con;
		
     } 
    

