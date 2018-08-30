package cn.ycl.entity;

public class UserKey {
    private String email;

    private String imei;
    
    public UserKey() {
    	
	}
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }
}