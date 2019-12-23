package com.zm.dao;

import java.util.List;

import com.zm.bean.Borrow;

public interface BorrowDAO {
   //根据用户id查询借阅信息
	List<Borrow> getBorrowList(Integer userId);
	
	//插入借阅信息
	boolean insertBorrow(Borrow borrow);
	
	//还书操作
	boolean deleBookById(int userId,String bookNo);
    
	//根据用户Id和索书号bookNo从借阅表(borrow)删除借阅信息
	boolean deleByBId(int bId);
	
}
