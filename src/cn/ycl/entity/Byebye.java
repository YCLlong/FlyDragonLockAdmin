package cn.ycl.entity;

public class Byebye {
    private String ip;
    
    public Byebye() {

    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}