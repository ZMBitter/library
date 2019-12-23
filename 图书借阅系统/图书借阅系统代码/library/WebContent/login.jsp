<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>登陆</title>
<style type="text/css">
#mydiv1 {
	width: 400px;
	height: 250px;
	background-color: #ccc;
	text-align: center;
	margin-top: 10px;
	position: relative;
	border-radius: 1%;
	left: 500px;
	top: 150px;
}

#mydiv2 {
	width: 350px;
	height: 200px;
	text-align: center;
	line-height: 40px;
	position: absolute;
	margin-top: 20px;
}

.inp {
	width: 200px;
	height: 30px;
	border-radius: 5%;
	outline: none;
}

a {
	text-decoration: none;
}

body{
   background-image:url(static/img/bg.jpg);
   background-repeat: no-repeat
}
</style>
</head>
<body>
	<div align="right">
		<a href="register.jsp">去注册</a>
	</div>

	<div id="mydiv1">
		<div id="mydiv2">
			<form action="${pageContext.request.contextPath}/loginServlet"
				method="post">
				<p>
					<label>用户名：</label><input type="text" name="username"
						placeholder="请输入用户名" class="inp" />
				</p>
				<p>
					<label>密&nbsp;&nbsp;码：</label><input type="password"
						name="password" placeholder="请输入密码" class="inp" />
				</p>
				<p>
					<input type="submit" name="btn1" value="登陆" /> <input type="reset"
						name="btn2" value="取消" />
				</p>
			</form>
			<p>
				<a href="register.jsp">还没有注册?去注册</a>
			</p>
		</div>
	</div>
</body>
</html>