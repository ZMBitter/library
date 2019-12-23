package com.zm.dao;

import java.util.List;

import com.zm.bean.ReturnBook;

public interface ReturnBookDAO {
	//向returnbook表中插入归还信息
	// boolean insertReturnBook(String realReturnTime,Integer userId,String bookNo);
	
	//从returnbook表中查询归还记录信息
	List<ReturnBook> selectReturnBook(Integer userId);

	//根据主键删除历史记录信息
	boolean deleById(int rId);

	//根据归还记录表的rId数组删除归还记录信息
	void deleteBatch(String[] rIds);

}
