package com.zm.bean;

import java.io.Serializable;

public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	private String bookNo; //索书号
	private String bookName; //书籍名称
	private String publishAddr; //图书出版社
	private Integer bookNum; //馆藏数量
	private String author; //作者
	private BookRoom room; //馆藏对象
	private Integer roomId; //馆藏编号
	private String roomAddr; //馆藏地址
	private String statusName; //状态

	public Book() {
		
	}

	public Book(String bookNo, String bookName, String publishAddr, Integer bookNum, String author,
			Integer roomId) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.publishAddr = publishAddr;
		this.bookNum = bookNum;
		this.author = author;
		this.roomId = roomId;
	}

	

	public Book(String bookNo, String bookName, String publishAddr, Integer bookNum, String author, BookRoom room,
			Integer roomId) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.publishAddr = publishAddr;
		this.bookNum = bookNum;
		this.author = author;
		this.room = room;
		this.roomId = roomId;
		
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPublishAddr() {
		return publishAddr;
	}

	public void setPublishAddr(String publishAddr) {
		this.publishAddr = publishAddr;
	}

	public Integer getBookNum() {
		return bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public BookRoom getRoom() {
		return room;
	}

	public void setRoom(BookRoom room) {
		this.room = room;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
     
	
	public String getRoomAddr() {
		return roomAddr;
	}

	public void setRoomAddr(String roomAddr) {
		this.roomAddr = roomAddr;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", bookName=" + bookName + ", publishAddr=" + publishAddr + ", bookNum="
				+ bookNum + ", author=" + author + ", roomAddr=" + roomAddr + "]";
	}

	
}
