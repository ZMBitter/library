<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<script src="static/js/jquery-3.3.1.js"></script>

<style type="text/css">

body{
   background-image:url(static/img/bg.jpg);
}
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
	padding: 0px 5px;
}

a {
	text-decoration: none;
}

</style>

</head>

<body>
   <div align="right">
       <a href="login.jsp">返回登陆</a>
   </div>
	<div id="mydiv1">
		<div id="mydiv2">
			<form action="${pageContext.request.contextPath}/registerServlet"
				method="post">
				<p>
					<label>用户名：</label><input type="text" name="username"
						placeholder="请输入用户名" class="inp" id="username" />
				</p>
				<p>
					<label>密&nbsp;&nbsp;码：</label><input type="password"
						name="password" placeholder="请输入密码" class="inp" id="pwd" />
				</p>
				<p>
					<label>密&nbsp;&nbsp;码：</label><input type="email" name="email"
						placeholder="请输入邮箱" class="inp" id="email" />
				</p>
				<p>
					<input type="submit" name="btn1" value="提交" /> <input type="reset"
						name="btn2" value="重置" />
				</p>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$("#username")
			.blur(
					function() {
						var val = $(this).val();
						if (val == null || val == "") {
							alert("用户名不能为空！");
						} else {
							$
									.ajax({
										type : "get",
										url : "${pageContext.request.contextPath}/checkName?username="
												+ val,
										dataType:"json",
										async : true,
										success : function(result) {
											if(result.userExist){
												alert("该用户名太受欢迎了，换一个!");
											}else{
												alert("该用户名可用!");
											}
										}
									});
						}
					});

	$("#pwd").blur(function() {
		var val = $(this).val();
		if (val == null || val == "") {
			alert("密码不能为空!");
		} else if (val.length < 6) {
			alert("密码太短了!");
		}
	});
</script>
</html>