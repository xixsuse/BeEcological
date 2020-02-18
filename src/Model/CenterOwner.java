package Model;

public class CenterOwner {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String emailAddress;
    private String phoneNumber;
    private String centerName;
    
    public CenterOwner(String username){
        this.username = username;
    }
    
    public CenterOwner(String name, String surname, String emailAddress, String phoneNumber, String username, String password,
    		String centerName){
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    	this.username = username;
        this.password = password;
        this.centerName = centerName;
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
    
    public String getCenter() {
        return this.centerName;
    }


    public void setCenter(String centerName) {
        this.centerName = centerName;
    }
    
    @Override
    public String toString(){
		return this.username + " \"" + this.password + "\" (" + this.centerName + ")";    	
    }
}