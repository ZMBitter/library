package com.zm.service.impl;

import java.util.Date;
import java.util.List;
import com.zm.bean.Book;
import com.zm.bean.Borrow;
import com.zm.bean.PageBean;
import com.zm.bean.User;
import com.zm.dao.BookDAO;
import com.zm.dao.BorrowDAO;
import com.zm.dao.ReturnBookDAO;
import com.zm.dao.UserDAO;
import com.zm.dao.impl.BookDAOImpl;
import com.zm.dao.impl.BorrowDAOImpl;
import com.zm.dao.impl.ReturnBookDAOImpl;
import com.zm.dao.impl.UserDAOImpl;
import com.zm.service.UserService;

public class UserServiceImpl implements UserService{
    BookDAO bookDAO = new BookDAOImpl();
    BorrowDAO borrowDAO = new BorrowDAOImpl();
    ReturnBookDAO rBookDAO = new ReturnBookDAOImpl();
    UserDAO userDAO = new UserDAOImpl();
	
	//查询所有图书信息
	@Override
	public List<Book> getAll() {
		List<Book> bookList = bookDAO.getAllBook();
		return bookList;
	}

	
	/*借阅书籍。功能实现步骤：
	 *  1. 向借阅表里面插入一条借阅信息
	 *  2. 修改book表里面的馆藏数量信息
	 * */
	@Override
	public boolean borrow(int userId, String bookNo) {
		
		Borrow borrow = new Borrow();
		borrow.setUserId(userId);
		borrow.setBookNo(bookNo);
		borrow.setBorrowTime(new Date().toString());
		/*borrow.setReturnTime(ConvertUtil.datePlus(30));*/
		
		boolean flag = borrowDAO.insertBorrow(borrow);
		boolean update = false;
		if(flag){//向借阅表中成功插入一条数据
			update = bookDAO.updateById(bookNo,"借阅");
		}
		return update;
	}

   /* 还书功能的实现：
    *  1. 根据用户名和书籍Id从借阅表（borrow）中删除一条借阅信息
    *  2. 在归还记录表（returnbook）中插入一条信息
    *  3. 修改book表中的馆藏数量  (馆藏数量=馆藏数量+1)
    *  
    *      在数据库的borrow表上建立一个由delete事件触发的触发器，在从借阅表中删除一条记录的同时，向
    *  还书记录表中插入一条信息。
    *  操作成功后，修改book表里面的馆藏数量
    * */
	@Override
	public boolean returnBook(int bId, String bookNo) {
		boolean flag = borrowDAO.deleByBId(bId);
		boolean update = false;
		if(flag){
			update = bookDAO.updateById(bookNo,"归还");
		}
		return update;
	}

	
    /*根据用户Id查询用户借阅信息*/
	@Override
	public List<Borrow> getBorrowList(int userId) {
		List<Borrow> borrowList = borrowDAO.getBorrowList(userId);
		return borrowList;
	}

	/*
	 * 根据主键rId删除历史记录信息
	 * @param rId returnbook表中的主键
	*/
	@Override
	public boolean deleById(int rId) {
		boolean flag = rBookDAO.deleById(rId);
		return flag;
	}


	/*
	 * 批量删除
	*/
	@Override
	public void delBatchReturnBook(String[] rIds) {
		rBookDAO.deleteBatch(rIds);
	}

   /*
    * 显示分页信息
    */
	@Override
	public PageBean<Book> findBookByPage(int curPage,int rows,String bookNameWithKey) {
		//1. 创建一个空的PageBean对象
		PageBean<Book> pageBean = new PageBean<Book>();
		//2. 获取总的记录数
		int totalCount = bookDAO.getTotalCount(bookNameWithKey);
		//3. 获取总页码数
		int totalPage = totalCount%rows==0?totalCount/rows:totalCount/rows+1;
		//4.每一页显示的信息
		//4.1 获取每一页的开始记录数
		int start = (curPage-1)*rows;
		//4.2 获取每一页显示的信息集合
		List<Book> list = bookDAO.getBookByPage(start, rows,bookNameWithKey);
		
		//封装PageBean对象
		pageBean.setTotalCount(totalCount);
		pageBean.setCurPage(curPage);
		pageBean.setRows(rows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		return pageBean;
	}

	
//根据用户名查询用户列表
	@Override
	public boolean getUserByName(String username) {
		List<User> list = userDAO.getUserByName(username);
		boolean flag = false;
		if(list.size()>0){
			flag = true;
		}
		return flag;
	}


	//用户注册
	@Override
	public boolean addUser(User user) {
		boolean flag = userDAO.addUser(user);
		return flag;
	}

	/*用户登陆*/

	@Override
	public User loginUser(String username, String password) {
		User loginUser = userDAO.loginUser(username, password);
		return loginUser;
	}

}
