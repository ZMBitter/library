package com.zm.bean;

import java.io.Serializable;

public class ReturnBook implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer rId; //主键，递增
	private Integer userId;  //用户编号
	private String bookNo;  //索书号
	private String borrowTime;  //借阅时间
	private String realReturnTime;  //实际还书时间
	
	public ReturnBook() {
	
	}

	public ReturnBook(Integer rId, Integer userId, String bookNo, String borrowTime, String realReturnTime) {
		super();
		this.rId = rId;
		this.userId = userId;
		this.bookNo = bookNo;
		this.borrowTime = borrowTime;
		this.realReturnTime = realReturnTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getRealReturnTime() {
		return realReturnTime;
	}

	public void setRealReturnTime(String realReturnTime) {
		this.realReturnTime = realReturnTime;
	}
	
	public Integer getrId() {
		return rId;
	}


	public void setrId(Integer rId) {
		this.rId = rId;
	}

	@Override
	public String toString() {
		return "ReturnBook [rId=" + rId + ", userId=" + userId + ", bookNo=" + bookNo + ", borrowTime=" + borrowTime
				+ ", realReturnTime=" + realReturnTime + "]";
	}
}
