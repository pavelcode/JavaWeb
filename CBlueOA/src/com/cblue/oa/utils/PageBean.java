package com.cblue.oa.utils;

import java.util.List;

/**
 * 页次：7/13页   每页显示：30条   总记录数：385条  3 4 5 6 7 8 9 10 11 12   转到： 
 * 页次：currentPage/pageCount页   每页显示：pageSize条   总记录数：recordCount条  
 * startIndex 4 5 6 7 8 9 10 11 endIndex   转到： 
 * @author pavel
 *
 */
public class PageBean {
	
	//当前页
	private int currentPage;
	//保存当前页的数据
	private List recordList;
	
	//总页数
	private int pageCount;
	//每页显示的条数
	private int pageSize;
	
	//总记录数
	private int recordCount;
	
	//开始索引
	private int beginIndex;
	//结束索引
	private int endIndex;
	
	
		
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public PageBean() {

	}

	public PageBean(int currentPage,List recordList,int pageSize,int recordCount){
		this.currentPage = currentPage;
		this.recordList = recordList;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		
		//计算总页数
		// 91条    一页为10条   10页  107/10
		pageCount = (recordCount+pageSize-1)/pageSize;
		//pageCount = (recordCount%pageSize==0)?(recordCount/pageSize):(recordCount/pageSize)+1;
		
		//计算开始索引和结束索引
		  //1如果总页数小于10
		if(pageCount<10){
			this.beginIndex=1;
			this.endIndex=pageCount;
		}else{
			//2如果总页数大于10，正常显示
			// 22    18 19 20 21 [22] 23 24 25 26 27
			this.beginIndex= this.currentPage-4;
			this.endIndex= this.currentPage+5;
			//左侧开始的位置小于1，左侧位置应该是1，右侧位置是10
			if(this.beginIndex<1){
				this.beginIndex=1;
				this.endIndex=10;
			}
			//右侧的位置大于总页数,结束位置应该是总页数，开始位置应该是总页数-9
			if(this.endIndex>this.pageCount){
				this.endIndex = this.pageCount;
				this.beginIndex = this.pageCount-9;
			}
			
		}
		
	
	}
	
	

}
