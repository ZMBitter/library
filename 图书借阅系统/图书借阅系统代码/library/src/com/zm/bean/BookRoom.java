package com.zm.bean;

import java.io.Serializable;

public class BookRoom implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer roomId;
	private String roomAddr;
	
	
	public BookRoom() {
		
	}


	public BookRoom(Integer roomId, String roomAddr) {
		super();
		this.roomId = roomId;
		this.roomAddr = roomAddr;
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


	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomAddr=" + roomAddr + "]";
	}
	

}
