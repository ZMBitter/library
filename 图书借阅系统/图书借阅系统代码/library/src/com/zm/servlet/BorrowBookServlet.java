package com.zm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

/*借书操作*/
@WebServlet("/borrowBook")
public class BorrowBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//借阅书籍操作
		String bookNo = req.getParameter("bookNo");
		String userId_str = req.getParameter("userId");
		int userId = Integer.parseInt(userId_str);
		System.out.println("bookNo="+bookNo+",userId="+userId);
		
		
		UserService service = new UserServiceImpl();
		boolean flag = service.borrow(userId,bookNo);
		
		if(flag){
			//借书成功：跳转到查询所有馆藏信息的servlet
			System.out.println("借书成功!");
			resp.sendRedirect(req.getContextPath()+"/selectBookByPage");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
