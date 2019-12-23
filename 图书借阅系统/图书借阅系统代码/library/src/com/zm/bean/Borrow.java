package com.zm.bean;

import java.io.Serializable;

public class Borrow implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer bId; //主键，递增
	private Integer userId; // 用户编号
	private String bookNo; // 索书号
	private String borrowTime; // 借阅时间
	private String returnTime; // 预计归还时间

	public Borrow() {

	}
	
	public Borrow(Integer bId, Integer userId, String bookNo, String borrowTime, String returnTime) {
		this.bId = bId;
		this.userId = userId;
		this.bookNo = bookNo;
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
	}
	
	public Integer getbId() {
		return bId;
	}


	public void setbId(Integer bId) {
		this.bId = bId;
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


	public String getReturnTime() {
		return returnTime;
	}


	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}


	@Override
	public String toString() {
		return "Borrow [bId=" + bId + ", userId=" + userId + ", bookNo=" + bookNo + ", borrowTime=" + borrowTime
				+ ", returnTime=" + returnTime + "]";
	}
}
