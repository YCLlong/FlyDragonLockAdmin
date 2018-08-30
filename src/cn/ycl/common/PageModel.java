package cn.ycl.common;

import java.util.List;
//分页工具
public class PageModel<T> {
	//每一页显示的记录数
	public static final int PAGE_SIZE = 8;
	//分页的数据集
	private List<T> dataList;
	//当前页数
	private int currentPage;
	//总的记录数
	private Long recordNum;
	//总页数
	private int pageCount;
	//开始的页数
	private int pageStart;
	
	//构造函数
	public PageModel(){}
	public PageModel(int currentPage) {
		this.currentPage = currentPage;
		//计算起始页
		pageStart = (currentPage-1) * PAGE_SIZE;
	}
	//set,get
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Long getRecordNum() {
		return recordNum;
	}
	//当设置记录总的记录数时
	public void setRecordNum(Long recordNum) {
		this.recordNum = recordNum;
		pageCount = (int) ((recordNum-1)/PAGE_SIZE) + 1;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}	
}
