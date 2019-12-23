package com.zm.bean;

import java.io.Serializable;
import java.util.List;

/*分页信息显示页面*/
public class PageBean<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int totalCount; //总记录数
	private int rows; //每一页的记录数
	private int totalPage; //总页数
	private int curPage; //当前页码
	private List<T> list;
	
	
	public PageBean() {
	
	}


	public PageBean(int totalCount, int rows, int totalPage, int curPage, List<T> list) {
		super();
		this.totalCount = totalCount;
		this.rows = rows;
		this.totalPage = totalPage;
		this.curPage = curPage;
		this.list = list;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getCurPage() {
		return curPage;
	}


	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "PageBean [totalCount=" + totalCount + ", rows=" + rows + ", totalPage=" + totalPage + ", curPage="
				+ curPage + ", list=" + list + "]";
	}
}
