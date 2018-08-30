package cn.ycl.entity;

import java.util.Date;

public class Receive {
    private Integer id;

    private Integer serverid;

    private String receiveip;

    private Date receivetime;
    
    public Receive() {
		
	}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServerid() {
        return serverid;
    }

    public void setServerid(Integer serverid) {
        this.serverid = serverid;
    }

    public String getReceiveip() {
        return receiveip;
    }

    public void setReceiveip(String receiveip) {
        this.receiveip = receiveip == null ? null : receiveip.trim();
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }
}