package logic.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class WasteUnloadedDAO {
	
	private static String USER = "root";
    private static String PASS = "root";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
   

    public static void registerWasteForUnload(WasteUnloaded wasteUnloaded) {
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
            
        	String insertStatement = String.format("INSERT INTO beecological.wasteunloaded(user,center,date,time,waste,wasteQuantity)"
        			+ " VALUES ('%s' ,'%s' ,'%s' ,'%s' ,'%s' ,'%s')", wasteUnloaded.getUser(), wasteUnloaded.getCenter(), wasteUnloaded.getDate(), 
        			wasteUnloaded.getTime(), wasteUnloaded.getWaste(), wasteUnloaded.getWasteQuantity());
            stmt.executeUpdate(insertStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static ArrayList<WasteUnloaded> listOfUnloadRegisteredByCenter(String center) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res;
        ArrayList<WasteUnloaded> listUnload = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	
        	String selectStatement = "SELECT * FROM beecological.wasteunloaded JOIN beecological.unload JOIN beecological.waste "
        			+ "WHERE beecological.wasteunloaded.user = beecological.unload.user AND beecological.wasteunloaded.date = beecological.unload.date AND "
        			+ "beecological.wasteunloaded.time = beecological.unload.time AND beecological.wasteunloaded.waste = beecological.waste.name "
        			+ "AND beecological.wasteunloaded.center = beecological.unload.center AND beecological.wasteunloaded.center = '" + center + "';";
        	res = stmt.executeQuery(selectStatement);
        	while (res.next()) {
        		listUnload.add(new WasteUnloaded(res.getString("user"), res.getString("center"), res.getString("date"), 
        				res.getString("time"), res.getString("name"), res.getInt("wasteQuantity"), res.getInt("ecoPoints")));
        	}
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return listUnload;
    }
    
    public static ArrayList<WasteUnloaded> listOfUnloadRegisteredByUser(String user) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res;
        ArrayList<WasteUnloaded> listUnload = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
        	
        	String selectStatement = "SELECT * FROM beecological.wasteunloaded JOIN beecological.unload JOIN beecological.waste "
        			+ "WHERE beecological.wasteunloaded.center = beecological.unload.center AND beecological.wasteunloaded.date = beecological.unload.date AND "
        			+ "beecological.wasteunloaded.time = beecological.unload.time AND beecological.wasteunloaded.waste = beecological.waste.name "
        			+ "AND beecological.wasteunloaded.user = beecological.unload.user AND beecological.wasteunloaded.user = '" + user + "';";
        	res = stmt.executeQuery(selectStatement);
        	while (res.next()) {
        		listUnload.add(new WasteUnloaded(res.getString("user"), res.getString("center"), res.getString("date"), 
        				res.getString("time"), res.getString("name"), res.getInt("wasteQuantity"), res.getInt("ecoPoints")));
        	}
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return listUnload;
    }
    
    public static void deleteWasteUnloaded(WasteUnloaded wasteUnloaded) {
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

        	String deleteStatement = String.format("DELETE FROM beecological.wasteunloaded WHERE beecological.wasteunloaded.user = '%s' "
        			+ "AND beecological.wasteunloaded.center = '%s' AND beecological.wasteunloaded.date = '%s' AND beecological.wasteunloaded.time = '%s' "
        			+ "AND beecological.wasteunloaded.waste = '%s';", 
        			wasteUnloaded.getUser(), wasteUnloaded.getCenter(), wasteUnloaded.getDate(), wasteUnloaded.getTime(), wasteUnloaded.getWaste());
        	stmt.executeUpdate(deleteStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static int wasteForAnUnload(WasteUnloaded wasteUnloaded) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res;
        int count = 1;
        
    	try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String selectStatement = "SELECT count(*) FROM beecological.wasteunloaded WHERE beecological.wasteunloaded.user = '"+wasteUnloaded.getUser()+"' "
        			+ "AND beecological.wasteunloaded.center = '"+wasteUnloaded.getCenter()+"' AND beecological.wasteunloaded.date = '"+wasteUnloaded.getDate()+"' "
        					+ "AND beecological.wasteunloaded.time = '"+wasteUnloaded.getTime()+"';";
        	res = stmt.executeQuery(selectStatement);
        	res.next();
        	
        	count = res.getInt(1);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    	return count;
    }
}