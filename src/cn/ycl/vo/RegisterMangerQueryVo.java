package cn.ycl.vo;

import cn.ycl.common.PageModel;

public class RegisterMangerQueryVo {
	//分页相关
	private Integer currentPage;
	private Integer startPage;
	private Integer pageSize;
	//查询条件
	private String qq;
	private String imei;
	private String startTime;
	private String endTime;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		startPage = (currentPage - 1) * PageModel.PAGE_SIZE;
		pageSize = PageModel.PAGE_SIZE;
	}
	public Integer getStartPage() {
		return startPage;
	}
	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
