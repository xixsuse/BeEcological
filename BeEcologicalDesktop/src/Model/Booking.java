package Model;

public class Booking {
	private int ID;
	private String user;
	private String center;
	private String date;
	private String time;
	private String status;
	
	public Booking() {}
	
	public Booking(int ID, String user, String center, String date, String time, String status) {
		this.ID = ID;
		this.user = user;
		this.center = center;
		this.date = date;
		this.time = time;
		this.status = status;
	}
	
	public Booking(String user, String center, String date, String time, String status) {
		this.user = user;
		this.center = center;
		this.date = date;
		this.time = time;
		this.status = status;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
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
    
    public String getStatus() {
        return this.status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}