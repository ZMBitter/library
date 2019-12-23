package com.zm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

/*删除历史记录信息*/
@WebServlet("/delReturnBookServlet")
public class DelReturnBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置字符编码
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		UserService service = new UserServiceImpl();

		int rId = Integer.parseInt(req.getParameter("rId"));
		String userId = req.getParameter("userId");
		System.out.println("rId=" + rId + ",userId=" + userId);

		// 从记录还书信息表中删除还书记录信息
		boolean flag = service.deleById(rId);
		if (flag) {
			System.out.println("删除成功!");
			req.getRequestDispatcher("/selReturnBookServlet").forward(req, resp);
		} else {
			System.out.println("删除失败!");
			// 重定向到returnBook.jsp页面
			resp.sendRedirect(req.getContextPath() + "/returnBook.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
