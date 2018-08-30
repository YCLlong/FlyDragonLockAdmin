package cn.ycl.entity;

public class Blacklist {
    private String ip;
    
    public Blacklist() {
    	
	}
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}