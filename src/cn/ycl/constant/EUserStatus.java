package cn.ycl.constant;

public enum EUserStatus {
	ONLINE(1,"在线"),
	OFFLINE(2,"离线"),
	ALL(3,"在线和离线");
	
	private EUserStatus(Integer id, String deString) {
		this.id = id;
		this.deString = deString;
	}
	private Integer id;
	private String deString;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeString() {
		return deString;
	}
	public void setDeString(String deString) {
		this.deString = deString;
	}
	
}
