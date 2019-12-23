package com.zm.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zm.bean.ReturnBook;
import com.zm.dao.ReturnBookDAO;
import com.zm.dao.impl.ReturnBookDAOImpl;

@WebServlet("/selReturnBookServlet")
public class SelReturnBookServlet extends HttpServlet {

	// 查询归还记录信息
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 查询用户还书记录信息
		int userId = Integer.parseInt(req.getParameter("userId"));
		System.out.println("userId=" + userId);
		ReturnBookDAO rBookDAO = new ReturnBookDAOImpl();
		List<ReturnBook> returnBooks = rBookDAO.selectReturnBook(userId);

		System.out.println("查看还书记录!");
		HttpSession session = req.getSession();
		session.setAttribute("returnBooks", returnBooks);
		req.getRequestDispatcher("/returnBook.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
