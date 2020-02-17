package Model;

public class Unload {
	private String user;
    private String center;
    private String date;
    private String time;

    public Unload(String user, String center, String date, String time){
    	this.user = user;
        this.center = center;
        this.date = date;
        this.time = time;
    }

    public String getUser() {
        return this.user;
    }
     
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getCenter() {
        return this.center;
    }


    public void setCenter(String center) {
        this.center = center;
    }
    
    public String getDate() {
        return this.date;
    }


    public void setDate(String date) {
        this.date = date;
    }
    
    public String getTime() {
        return this.time;
    }


    public void setTime(String time) {
        this.time = time;
    }
}