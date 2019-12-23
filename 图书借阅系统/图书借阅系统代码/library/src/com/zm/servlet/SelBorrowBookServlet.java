package com.zm.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zm.bean.Borrow;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

/*查看借阅信息*/
@WebServlet("/selBorrowBookServlet")
public class SelBorrowBookServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//查看借阅信息操作
		System.out.println("查看借阅信息!");
		int userId = Integer.parseInt(req.getParameter("userId"));
		System.out.println("userId="+userId);
		
		UserService service = new UserServiceImpl();
		List<Borrow> borrowList = service.getBorrowList(userId);
		
		HttpSession session = req.getSession();
		session.setAttribute("borrowList", borrowList);
		req.getRequestDispatcher("/borrow.jsp").forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
	

}
