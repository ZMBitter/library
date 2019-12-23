<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			body{
				text-align: right;
			}
			
			/*
			  :active向活动的链接添加特殊的样式。当你点击一个链接时它变成活动链接。

                                  提示: :link 选择器设置了未访问过的页面链接样式
                 :visited 选择器设置访问过的页面链接的样式
                 :hover 选择器当有鼠标悬停在其上的链接样式。

                                  注意: 为了产生预期的效果，在CSS定义中，:active必须位于:hover之后！！
			
			*/
			
			a{
			    text-decoration:none;
			}
			
			a:link{
			  color:black;
			}
			
			a:visited{
			  color:black;
			}
			a:hover{
			  color:red;
			}
			
			a:active{
			  blue;
			}
			
		</style>
	</head>
	<body>
		${sessionScope.user.username}，欢迎您!|<a href="${pageContext.request.contextPath}/loginoutServlet" target="_praint">退出</a>
	</body>
</html>
