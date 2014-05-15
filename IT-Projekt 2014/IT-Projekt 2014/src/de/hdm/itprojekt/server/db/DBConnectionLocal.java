package de.hdm.itprojekt.server.db;

import java.sql.*;

public class DBConnectionLocal{ 
    
	private static Connection con = null;
	
	public static Connection connection() {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/socialmediapinnwand", "smp", "");
			con.setReadOnly(false);
		}
		catch(Exception e){
			System.out.println("Fehler beim Verbinden der lokalen Datenbank! ->"+e);
		}
		
		return con;
	}
}