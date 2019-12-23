package com.zm.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.zm.bean.User;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码格式
		req.setCharacterEncoding("utf-8");
		
		Map<String, String[]> map = req.getParameterMap();
		
		//封装User对象
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		UserService service = new UserServiceImpl();
		boolean flag = service.addUser(user);
		if(flag){
			//注册成功，去登陆
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
		}else{
			//注册失败，继续注册
			resp.sendRedirect(req.getContextPath()+"/register.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
	

}
