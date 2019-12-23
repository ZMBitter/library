package com.zm.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zm.bean.Book;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

@WebServlet("/selectBookServlet") //查看馆藏信息
public class SelectBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		UserService service = new UserServiceImpl();
		List<Book> bookList = service.getAll();
		
		HttpSession session = req.getSession();
		
		if (bookList != null) {
			session.setAttribute("bookList", bookList);
			req.getRequestDispatcher("/book.jsp").forward(req, resp);
		} else {
			session.setAttribute("msg", "还没有记录!");
			req.getRequestDispatcher("/book.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
