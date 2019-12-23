<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>

</head>

	<frameset rows="5%,95%">
		<frame src="head.jsp" noresize="noresize" scrolling="no" style="background-image:url(static/img/bg.jpg)"/>
		<frameset cols="10%,90%">
			<frame src="left.jsp" name="left" style="background-image:url(static/img/bg.jpg)"/>
			<frame
				src="${pageContext.request.contextPath }/selectBookByPage?curPage=1&rows=10"
				name="right" style="background-image:url(static/img/bg.jpg)"/>
		</frameset>
	</frameset>
</html>