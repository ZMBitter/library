package com.zm.dao;

import java.util.List;

import com.zm.bean.Book;

public interface BookDAO {
  
	//查询所有图书信息
	List<Book> getAllBook();
	
	//根据图书编号删除图书
	boolean deleBookById(String bookNo);
	
	//新增图书
	boolean insertBook(Book book);
	
	//根据索书号查询图书
	Book getBookById(String bookNo);
	
    //根据图书编号修改馆藏数量
	boolean updateById(String bookNo,String key);
	
	//获取馆藏的总记录条数
	int getTotalCount(String bookNameWithKey);
	
	//分页查询
	List<Book> getBookByPage(int start,int rows,String bookNameWithKey);

}
