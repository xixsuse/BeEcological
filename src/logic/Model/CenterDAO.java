package logic.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CenterDAO {
	
	private static String USER = "root";
    private static String PASS = "root";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
   
    private CenterDAO(String userConn) {
    	CenterDAO.USER = userConn;
    }
    
    public static ArrayList<Center> verifyCenter(String name) {
    	Statement stmt = null;
        Connection conn = null;
    	ResultSet res;
        ArrayList<Center> listCenter = new ArrayList<>();
        
        try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String selectStatement = "SELECT * FROM beecological.center WHERE beecological.center.centerName = '" + name + "' or "
        			+ "beecological.center.city = '" + name + "' or beecological.center.address = '" + name + "';";
        	res = stmt.executeQuery(selectStatement);
        	while (res.next()) {
        		listCenter.add(new Center(res.getString("centerName"), res.getString("city"), res.getString("CAP"), 
        				res.getString("address") +" "+ res.getString("num"), res.getString("centerPhone")));
        	}
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return listCenter;
    }
    
    public static CenterOwner ownerOfTheCenter(Center center) {
    	Statement stmt = null;
        Connection conn = null;
    	ResultSet res;
        CenterOwner owner = null;
        
    	try {
            //caricamento driver mysql
        	Class.forName(DRIVER_CLASS_NAME);
            
        	//apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        	String selectStatement = "SELECT * FROM beecological.owner WHERE beecological.owner.center = '" + center.getcName() + "';";
        	res = stmt.executeQuery(selectStatement);
        	res.next();
        	owner = new CenterOwner(res.getString("name"), res.getString("surname"), res.getString("email"), res.getString("phone"), 
        			res.getString("username"), res.getString("password"), res.getString("center"));
            
            stmt.close();
            conn.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return owner;
    }
}