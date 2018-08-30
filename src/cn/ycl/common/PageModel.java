package cn.ycl.common;

import java.util.List;
//��ҳ����
public class PageModel<T> {
	//ÿһҳ��ʾ�ļ�¼��
	public static final int PAGE_SIZE = 8;
	//��ҳ�����ݼ�
	private List<T> dataList;
	//��ǰҳ��
	private int currentPage;
	//�ܵļ�¼��
	private Long recordNum;
	//��ҳ��
	private int pageCount;
	//��ʼ��ҳ��
	private int pageStart;
	
	//���캯��
	public PageModel(){}
	public PageModel(int currentPage) {
		this.currentPage = currentPage;
		//������ʼҳ
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
	//�����ü�¼�ܵļ�¼��ʱ
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
