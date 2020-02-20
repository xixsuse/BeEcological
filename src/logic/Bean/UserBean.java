package logic.bean;

public class UserBean {
	public static UserBean instance = null;
	
    private String username;
    private String password;
    private String name;
    private String surname;
    private String emailAddress;
    private String phoneNumber;
    private String center;
    private int ecoPoints;
    
    
	public static UserBean getUserInstance(String username) {
		if (UserBean.instance == null) {
			UserBean.instance = new UserBean();
			UserBean.instance.username = username;
		}
		return instance;
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
        return this.center;
    }


    public void setCenter(String center) {
        this.center = center;
    }
    
    public int getEcopoints() {
        return this.ecoPoints;
    }


    public void setEcopoints(int ecoPoints) {
        this.ecoPoints = ecoPoints;
    }
}