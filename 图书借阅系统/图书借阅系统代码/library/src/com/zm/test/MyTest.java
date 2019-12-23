package com.zm.test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import com.zm.bean.Book;
import com.zm.bean.Borrow;
import com.zm.bean.User;
import com.zm.dao.BookDAO;
import com.zm.dao.UserDAO;
import com.zm.dao.impl.BookDAOImpl;
import com.zm.dao.impl.UserDAOImpl;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;
import com.zm.util.ConvertUtil;

public class MyTest {

	@Test
	public void test01() {
		UserService service = new UserServiceImpl();
		List<Book> bookList = service.getAll();
		for (Book book : bookList) {
			System.out.println(book);
		}
	}

	//借书测试
	@Test
	public void test02() {
	   UserService service = new UserServiceImpl();
	   boolean flag = service.borrow(1001, "TH1001");
	   System.out.println("借书成功了吗?"+flag);
	}
	
	//归还测试
	@Test
	public void test04(){
		UserService service = new UserServiceImpl();
		boolean flag = service.returnBook(1001, "TH1001");
		System.out.println("归还了吗?"+flag);
	}

	//查看借阅信息      
	@Test
	public void test05(){
		UserService service = new UserServiceImpl();
		List<Borrow> borrowList = service.getBorrowList(1001);
		System.out.println("用户1001的借阅信息如下：");
		System.out.println(borrowList.size());
		for(Borrow borrow:borrowList){
			System.out.println(borrow);
		}
	}
	
	//n天之后的时间测试
	@Test
	public void test06() throws ParseException{
		String datePlus = ConvertUtil.datePlus(new Date(),20);
		System.out.println(datePlus);
	}
	
	//String转换成date类型
	@Test
	public void test07(){
		Date str2Date = ConvertUtil.str2Date("2019-11-23");
		System.out.println(str2Date);
	}
	
	//批量删除测试
	@Test
	public void test08(){
		UserService service = new UserServiceImpl();
		String[] rIds = {"29","30"};
		/*boolean flag = service.delBatchReturnBook(rIds);
		System.out.println("删除成功了吗?"+flag);*/
	}
	
	//获取总的记录数
	@Test
	public void test09(){
		/*BookDAO bookDAO = new BookDAOImpl();
		int totalCount = bookDAO.getTotalCount();
		System.out.println(totalCount);*/
	}
	
	//分页与按条件查询测试
	@Test
	public void test10(){
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.getBookByPage(1, 10, "数据库");
	}
	
	//根据用户名查询用户信息
	@Test
	public void test11(){
		UserService service = new UserServiceImpl();
		boolean flag = service.getUserByName("admina");
		System.out.println(flag);
	}
}
