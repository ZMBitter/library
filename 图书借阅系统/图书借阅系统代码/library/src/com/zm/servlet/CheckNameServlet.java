package com.zm.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

@WebServlet("/checkName")
public class CheckNameServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置字符编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		
		//获取文本框中的name
		String username = req.getParameter("username");
		//根据用户名查询用户信息
		UserService service = new UserServiceImpl();
		boolean result = service.getUserByName(username);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userExist", result);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getWriter(), map);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
