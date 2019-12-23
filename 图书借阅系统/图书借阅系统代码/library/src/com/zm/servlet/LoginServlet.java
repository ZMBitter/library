package com.zm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import com.zm.bean.User;
import com.zm.dao.UserDAO;
import com.zm.dao.impl.UserDAOImpl;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//获取用户名和密码
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//判断用户是否存在
		
		UserService service = new UserServiceImpl();
		User loginUser = service.loginUser(username, password);
		
		if(loginUser!=null){//登陆成功
			HttpSession session = req.getSession();
			session.setAttribute("user", loginUser);
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		}else{//登陆失败
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
	

}
