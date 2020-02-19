package logic.Model;

public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String emailAddress;
    private String phoneNumber;
    private int ecoPoints;

    public User(String username){
        this.username = username;
    }
    
    public User(String name, String surname, String emailAddress, String phoneNumber, String username, String password, 
    		int ecoPoints){
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    	this.username = username;
        this.password = password;
        this.ecoPoints = ecoPoints;
    }
    
    public String getUsername() {
        return this.username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

        
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return this.surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getEmailAddress() {
        return this.emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public int getEcopoints() {
        return this.ecoPoints;
    }


    public void setEcopoints(int ecoPoints) {
        this.ecoPoints = ecoPoints;
    }
    
    @Override
    public String toString(){
		return this.username + " \"" + this.password + "\" (" + this.ecoPoints + ")";    	
    }

}