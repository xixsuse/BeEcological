package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class UnloadDAO {
	
	private static String USER = "root";
    private static String PASS = "root";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
   

    public static void saveUnload(Unload unload) {
    	Statement stmt = null;
        Connection conn = null;
        
        try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            String insertStatement = String.format("INSERT INTO beecological.unload (user, center, date, time) VALUES ('%s' ,'%s' ,'%s' ,"
            		+ "'%s')", unload.getUser(), unload.getCenter(), unload.getDate(), unload.getTime());
            stmt.executeUpdate(insertStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void deleteUnload(Unload unload) {
    	Statement stmt = null;
        Connection conn = null;
        
    	try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
        	String deleteStatement = String.format("DELETE FROM beecological.unload WHERE beecological.unload.user = '%s' AND "
        			+ "beecological.unload.center = '%s' AND beecological.unload.date = '%s' AND beecological.unload.time = '%s';", 
        			unload.getUser(), unload.getCenter(), unload.getDate(), unload.getTime());
        	stmt.executeUpdate(deleteStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
}