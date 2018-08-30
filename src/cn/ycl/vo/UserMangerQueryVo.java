package cn.ycl.vo;

import cn.ycl.common.PageModel;

public class UserMangerQueryVo {
	private String qq;
	private Integer currentPage;
	private Integer startPage;
	private Integer pageSize;
	private Integer userStatus;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
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
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

}
