package com.zm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zm.bean.User;

/*登陆验证的过滤器*/
@WebFilter()  //拦截所有请求
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		
		//获取用户请求的URI
		String uri = req.getRequestURI();
		//放行用户登陆页面
		if(uri.contains("login.jsp")){
			doFilter(req, resp, chain);
		}
		
		//获取session对象
		HttpSession session = req.getSession();
		//获取登陆用户信息
		User user = (User)session.getAttribute("user");
		if(user==null){//当用户对象为null，表示用户还没有登陆，跳转到登陆页面
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
		}else{
		//表示用户登陆成功，放行
		doFilter(req, resp, chain);
		}
	}

}
