package com.zm.service;

import java.util.List;

import com.zm.bean.Book;
import com.zm.bean.Borrow;
import com.zm.bean.PageBean;
import com.zm.bean.User;

//用户业务逻辑层
public interface UserService {
	//查询馆藏信息(查询所有图书列表)
	public List<Book> getAll();
    //借阅书籍
	public boolean borrow(int userId, String bookNo);
	//还书
	public boolean returnBook(int bId,String bookNo);
	//根据用户Id查看用户的借阅信息
	public List<Borrow> getBorrowList(int userId);
	//根据Id删除历史记录信息
	public boolean deleById(int rId);
	//批量删除历史记录信息
	public void delBatchReturnBook(String[] rIds);
	//分页查询
	public PageBean<Book> findBookByPage(int curPage,int rows,String bookNameWithKey);
	//根据用户名查询用户信息
	public boolean getUserByName(String username);
	//用户注册
	public boolean addUser(User user);
	//用户登陆
	public User loginUser(String username, String password);
}
