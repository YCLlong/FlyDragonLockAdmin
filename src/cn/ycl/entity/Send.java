package cn.ycl.entity;

import java.util.Date;

public class Send {
    private Integer id;

    private String sender;

    private String sendip;

    private String receiver;

    private String filename;

    private Long filesize;

    private String filepath;

    private Date senddate;

    private String dealed;
    
    public Send() {
		
	}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getSendip() {
        return sendip;
    }

    public void setSendip(String sendip) {
        this.sendip = sendip == null ? null : sendip.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    public String getDealed() {
        return dealed;
    }

    public void setDealed(String dealed) {
        this.dealed = dealed == null ? null : dealed.trim();
    }
}