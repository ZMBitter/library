package com.zm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

@WebServlet("/delBatchReturnBook")
public class DelBatchReturnBook extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String[] rIds = req.getParameterValues("rId");
		int userId = Integer.parseInt(req.getParameter("userId"));
		
		for(String rId:rIds){
			System.out.println("rId="+rId);
		}
		System.out.println("userId="+userId);
		
		UserService service = new UserServiceImpl();
		service.delBatchReturnBook(rIds);
		req.getRequestDispatcher("/selReturnBookServlet").forward(req, resp);
		
	}
	
	

}
