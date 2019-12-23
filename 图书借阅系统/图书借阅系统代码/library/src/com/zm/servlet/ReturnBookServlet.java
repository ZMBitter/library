package com.zm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

/*还书操作*/
@WebServlet("/returnBookServlet")
public class ReturnBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置字符编码
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 获取url路径中的参数
		int bId = Integer.parseInt(req.getParameter("bId"));
		int userId = Integer.parseInt(req.getParameter("userId"));
		String bookNo = req.getParameter("bookNo");
		System.out.println("bId="+bId+" userId=" + userId + ",bookNo=" + bookNo);
		
		UserService service = new UserServiceImpl();
		boolean flag = service.returnBook(bId,bookNo);
		
		if (flag) {
			System.out.println("1111111111111还书成功!");
			    req.getRequestDispatcher("/selBorrowBookServlet").forward(req, resp);
		
		} else {// 还书失败
			System.out.println("222222222222还del书失败!");
			req.getRequestDispatcher("/borrow.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
