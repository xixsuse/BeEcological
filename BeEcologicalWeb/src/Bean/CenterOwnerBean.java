package Bean;

import java.io.Serializable;

public class CenterOwnerBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static CenterOwnerBean instance = null;
	
    private String username;
    private String password;
    private String name;
    private String surname;
    private String emailAddress;
    private String phoneNumber;
    private String centerName;
    
    
	public static CenterOwnerBean getOwnerInstance(String username) {
		if (CenterOwnerBean.instance == null) {
			CenterOwnerBean.instance = new CenterOwnerBean();
			CenterOwnerBean.instance.username = username;
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