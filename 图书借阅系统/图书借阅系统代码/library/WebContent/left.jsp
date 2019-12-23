<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<ul>
		<li><a href="<%=request.getContextPath()%>/selectBookByPage?curPage=1&rows=10"
			target="right"> <font size="2">馆藏查询</font>
		</a></li>

		<li>
		<a href="<%=request.getContextPath()%>/selBorrowBookServlet?userId=${user.userId}" target="right">
		<font size="2">借阅书籍信息</font>
		</a>
		</li>

		<li><a href="<%=request.getContextPath()%>/selReturnBookServlet?userId=${user.userId}" target="right"> <font size="2">归还书籍信息</font>
		</a></li>
	</ul>
</body>
</html>
