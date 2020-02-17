package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CenterOwnerDAO {
	
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

        	String selectStatement = "SELECT count(*) FROM beecological.owner WHERE Username = '" + username + "';";
        	res = stmt.executeQuery(selectStatement);
        	res.next();				//res.next è la prima riga del risultato della query
        	count = res.getInt(1);	//ottengo la prima colonna del risultato della query
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        if(count == 1) {
        	return false;	//username gia utilizzato oppure errore non permetto registrazione(insert del nuovo utente)
        }
        return true;		//username disponibile
    }
    
    public static boolean verifyLogin(CenterOwner owner) {
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
            
        	String selectStatement = "SELECT count(*) FROM beecological.Owner WHERE Username = '" + owner.getUsername() + 
        			"' and Password = '" + owner.getPassword() + "';";
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
    
    public static List<String> ownerInfo(CenterOwner owner) {
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

        	String selectStatement = "SELECT * FROM beecological.owner JOIN beecological.center ON beecological.owner.center = "
        			+ "beecological.center.centerName WHERE beecological.owner.username = '" + owner.getUsername() + "';";
        	res = stmt.executeQuery(selectStatement);
    		res.next();
    		listInfo.add(res.getString("name"));
    		listInfo.add(res.getString("surname"));
    		listInfo.add(res.getString("email"));
        	listInfo.add(res.getString("phone"));
        	listInfo.add(res.getString("centerName"));
        	listInfo.add(res.getString("centerPhone"));
        	listInfo.add(res.getString("city"));
        	listInfo.add(res.getString("address")+" "+res.getString("num"));
        	listInfo.add(res.getString("CAP"));
        	listInfo.add(res.getString("num"));
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    	return listInfo;
    }
    
    public static void deleteOwnerAccount(String username) {
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
        	String deleteStatement = String.format("DELETE FROM beecological.owner WHERE beecological.owner.username = '" +username+ "';");
        	stmt.executeUpdate(deleteStatement);
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
}