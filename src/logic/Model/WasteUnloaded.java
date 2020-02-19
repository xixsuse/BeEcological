package logic.Model;

public class WasteUnloaded {
    private String user;
    private String center;
    private String date;
    private String time;
    private String waste;
    private int wasteQuantity;
    private int ecoPoints;

    public WasteUnloaded(String user, String center, String date, String time, String waste, int wasteQuantity){
        this.user = user;
        this.center = center;
        this.date = date;
        this.time = time;
        this.waste = waste;
        this.wasteQuantity = wasteQuantity;
    }
    
    public WasteUnloaded(String user, String center, String date, String time, String waste, int wasteQuantity, int ecoPoints){
        this.user = user;
        this.center = center;
        this.date = date;
        this.time = time;
        this.waste = waste;
        this.wasteQuantity = wasteQuantity;
        this.ecoPoints = ecoPoints;
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
    
    public String getWaste() {
        return this.waste;
    }
     
    public void setWaste(String waste) {
        this.waste = waste;
    }
    
    public int getWasteQuantity() {
        return this.wasteQuantity;
    }


    public void setWasteQuantity(int wasteQuantity) {
        this.wasteQuantity = wasteQuantity;
    }
    
    public int getEcoPoints() {
        return this.ecoPoints;
    }


    public void setEcoPoints(int ecoPoints) {
        this.ecoPoints = ecoPoints;
    }
}