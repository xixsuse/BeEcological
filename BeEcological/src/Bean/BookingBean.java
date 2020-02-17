package Bean;

public class BookingBean {
	private int ID;
	private String user;
	private String center;
	private String date;
	private String time;
	private String status;
	
	
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