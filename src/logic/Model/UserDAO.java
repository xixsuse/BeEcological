package logic.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class UserDAO {
	
	private static String USER = "root";
    private static String PASS = "root";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
   
    
    public static boolean checkUsername(String username) {
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
        	
        	String selectStatement = "SELECT count(*) FROM beecological.User WHERE Username = '" + username + "';";
        	res = stmt.executeQuery(selectStatement);
        	res.next();				//res.next � la prima riga del risultato della query
        	count = res.getInt(1);	//ottengo la prima colonna del risultato della query
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        if(count == 1) {
        	return false;	//username gia utilizzato da altro utente, non permetto registrazione(insert del nuovo utente)
        }
        return true;		//username disponibile
    }
    
    public static void saveUser(User user) {
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
            String insertStatement = String.format("INSERT INTO beecological.User (Username, Password, Name, Surname, Email, "
            		+ "Phone, Ecopoints) VALUES ('%s' ,'%s' ,'%s' ,'%s' ,'%s' ,'%s' , 0)", user.getUsername(), user.getPassword(), 
            		user.getName(), user.getSurname(), user.getEmailAddress(), user.getPhoneNumber());
            stmt.executeUpdate(insertStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static boolean verifyLogin(User user) {
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
        	
        	String selectStatement = "SELECT count(*) FROM beecological.User WHERE Username = '" + user.getUsername() + 
        			"' and Password = '" + user.getPassword() + "';";
        	res = stmt.executeQuery(selectStatement);
        	res.next();
        	count = res.getInt(1);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        if (count == 0) {
        	return false;	//utente immesso non esiste
        }
        return true; //count=1 nel db, l'utente matcha una registrazizone
    }


    public static List<String> userInfo(User user) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet res;
        List<String> listInfo = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String selectStatement = "SELECT * FROM beecological.User WHERE Username = '" + user.getUsername() + "';";
        	res = stmt.executeQuery(selectStatement);   
    		res.next();
    		listInfo.add(res.getString("Name"));
        	listInfo.add(res.getString("Surname"));
        	listInfo.add(res.getString("Email"));
        	listInfo.add(res.getString("Phone"));
        	listInfo.add(res.getString("EcoPoints"));
        	
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    	return listInfo;
    }
    
    public static void deleteUserAccount(String username) {
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
        	String deleteStatement = String.format("DELETE FROM beecological.user WHERE beecological.user.username = '" +username+ "';");
        	stmt.executeUpdate(deleteStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void updateUserEcoPoints(String username,int ecoPoints) {
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
            
        	String updateStatement = String.format("UPDATE beecological.user SET beecological.user.ecoPoints = '%s' "
        			+ "WHERE beecological.user.username = '" +username+ "';", ecoPoints);
            stmt.executeUpdate(updateStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
}