package com.zm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zm.bean.Book;
import com.zm.bean.PageBean;
import com.zm.service.UserService;
import com.zm.service.impl.UserServiceImpl;

@WebServlet("/selectBookByPage")
public class SelectBookByPage extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//获取url上携带的参数信息curPage,rows
		String strCurPage = req.getParameter("curPage");
		String strRows = req.getParameter("rows");
		//获取模糊查询条件
		String bookNameWithKey = req.getParameter("bookNameWithKey");
		//System.out.println(bookName);
		
		//程序做健壮性判断
		if(strCurPage==null||"".equals(strCurPage)||Integer.parseInt(strCurPage)<=1){
			strCurPage = "1";
		}
		
		if(strRows == null||"".equals(strRows)){
			strRows = "10";
		}
		
		//获取PageBean对象
		UserService service = new UserServiceImpl();
		PageBean<Book> pageBean = service.findBookByPage(Integer.parseInt(strCurPage), Integer.parseInt(strRows),bookNameWithKey);
	
		HttpSession session = req.getSession();
		session.setAttribute("pageBean", pageBean);
		req.getRequestDispatcher("/book.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
