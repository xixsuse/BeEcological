package logic.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class BookingDAO {
	
	private static String USER = "root";
    private static String PASS = "root";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
   
    
    public static void makeBooking(Booking booking) {
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
            
            String insertStatement = String.format("INSERT INTO beecological.bookingrequest (user, center, date, time, status)"
            		+ " VALUES ('%s' ,'%s' ,'%s' ,'%s' , '%s')", booking.getUser(), booking.getCenter(), booking.getDate(), 
            		booking.getTime(), booking.getStatus());
            stmt.executeUpdate(insertStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void updateBooking(Booking booking) {
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

            String updateStatement = String.format("UPDATE beecological.bookingrequest SET beecological.bookingrequest.status = '%s' "
            		+ "WHERE beecological.bookingrequest.user = '%s' AND beecological.bookingrequest.center ='%s' AND "
            		+ "beecological.bookingrequest.date = '%s' AND beecological.bookingrequest.time = '%s';", booking.getStatus(),
            		booking.getUser(), booking.getCenter(), booking.getDate(), booking.getTime());
            stmt.executeUpdate(updateStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static int existingBooking(Booking booking) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res;
        int count = 0;
        
        try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String selectStatement = "SELECT count(*) FROM beecological.bookingrequest WHERE beecological.bookingrequest.user = '" + booking.getUser() +"' "
        			+ "and beecological.bookingrequest.center = '" + booking.getCenter() + "' and beecological.bookingrequest.date = '" + booking.getDate() + "' "
        					+ "and beecological.bookingrequest.time = '" + booking.getTime() + "' and beecological.bookingrequest.status = '" + booking.getStatus() + "';";
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
    
    public static ArrayList<Booking> listOfBookingByCenter(String center, String status) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res;
        ArrayList<Booking> listBooking = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
        	String selectStatement = "SELECT * FROM beecological.bookingrequest WHERE beecological.bookingrequest.center = '" + center +"'"
        			+ " and beecological.bookingrequest.status = '" + status + "' ORDER BY beecological.bookingrequest.date;";
        	res = stmt.executeQuery(selectStatement);
        	while (res.next()) {
        		listBooking.add(new Booking(res.getInt("ID"), res.getString("user"), res.getString("center"), 
        				res.getString("date"), res.getString("time"), res.getString("status")));
        	}
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return listBooking;
    }
    
    public static ArrayList<Booking> listOfBookingByUser(String user, String status) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res;
        ArrayList<Booking> listBooking = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String selectStatement = "SELECT * FROM beecological.bookingrequest WHERE beecological.bookingrequest.user = '" + user +"'"
        			+ " and beecological.bookingrequest.status = '" + status + "' ORDER BY beecological.bookingrequest.date;";
        	res = stmt.executeQuery(selectStatement);
        	while (res.next()) {
        		listBooking.add(new Booking(res.getInt("ID"), res.getString("user"), res.getString("center"), 
        				res.getString("date"), res.getString("time"), res.getString("status")));
        	}
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return listBooking;
    }
}