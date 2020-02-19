package logic.Model;

public class Center {	
    private String name;
    private String centerPhone;
    private String city;
    private String address;
    private String CAP;
    private String num;
    
    public Center(String name) {
        this.name = name;
    }
    
    public Center(String name, String city, String CAP, String address, String centerPhone) {
        this.name = name;
        this.centerPhone = centerPhone;
        this.city = city;
        this.address = address;
    	this.CAP = CAP;
    }

    public Center(String name, String city, String CAP, String address, String num, String centerPhone) {
        this.name = name;
        this.centerPhone = centerPhone;
        this.city = city;
        this.address = address;
        this.num = num;
    	this.CAP = CAP;
    }
    
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getCenterPhone() {
        return this.centerPhone;
    }

        
    public void setCenterPhone(String centerPhone) {
        this.centerPhone = centerPhone;
    }
    
    public String getCity() {
        return this.city;
    }


    public void setCity(String city) {
        this.city = city;
    }
    
    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCAP() {
        return this.CAP;
    }


    public void setCAP(String CAP) {
        this.CAP = CAP;
    }
    
    public String getNum() {
        return this.num;
    }


    public void setNum(String num) {
        this.num = num;
    }
    
    @Override
    public String toString(){
		return this.name + " \"" + this.address + "\" (" + this.num + ")";    	
    }
}