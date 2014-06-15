package de.hdm.itprojekt.server.db;
import java.sql.*;
import com.google.appengine.api.rdbms.AppEngineDriver;


public class DBConnection{

	private static Connection con = null;

	/**
	 * Handles the database-connections for the GWT-Framework
	 * @return The database-connection object
	 */
	public static Connection connection() {

	
			try {
				DriverManager.registerDriver(new AppEngineDriver()); 

		
				  con = DriverManager.getConnection("jdbc:google:rdbms://socialmediapinnwand2:instanz/socialmediapinnwand", "root", "");
			} 
			catch (SQLException e1) { 
				con = null; 

				e1.printStackTrace();

		}

		return con; 
	}
	}

