package cn.ycl.entity;

public class OnlineKey {
    private String qq;

    private String ip;
    
    public OnlineKey() {
    	
	}
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}