<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.zm.bean.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借阅信息页面</title>

<script src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.js"
	type="text/javascript"></script>

<style type="text/css">
body {
	text-align: center;
}

#tb {
	margin: auto;
}

#tb tr td, h2 {
	text-align: center;
}

#div1 {
	width: 93%;
	text-align: right;
	margin-bottom: 20px;
}

#search_inp {
	width: 200px;
	height: 25px;
	border-radius: 5%;
	outline: none;
}

#tb tr {
	height: 40px;
}
</style>

</head>

<body>
	<%-- <%
		List<Borrow> borrowList = (List<Borrow>) session.getAttribute("borrowList");
		User user = (User) session.getAttribute("user");
	%> --%>
	<h2>借阅信息</h2>
	<c:if test="${borrowList.size()>0}">
		<table id="tb" width="90%" height="100%" border="1px solid"
			cellspacing="0" cellpadding="0" class="table table-bordered">
			<thead>
				<tr>
					<td>索书号</td>
					<td>借阅日期</td>
					<td>预计归还日期</td>
					<td>操作</td>
				</tr>
			</thead>

			<tbody id="tb2">
				<c:forEach items="${borrowList}" var="borrow">
					<tr>
						<td>${borrow.bookNo}</td>
						<td>${borrow.borrowTime}</td>
						<!-- 预计归还时间 -->
						<td>${borrow.returnTime}</td>
						<td><a type="button" class="returnBtn"
							href="javascript:returnBook(${borrow.bId},${borrow.userId},'${borrow.bookNo}')">归还</a>
						</td>
					</tr>
				</c:forEach>
		</table>
	</c:if>

    <br>
	<c:if test="${borrowList.size()==0 }">
		<font style="color: grey;text-align:center;font-size:20px">还没有借阅信息哦!</font>
	    <a href="book.jsp">查看馆藏</a>
	</c:if>
</body>

<script type="text/javascript">

	var tbRow = document.getElementById("tb2").rows;

	for (var i = 0; i < tbRow.length; i++) {
		if (tbRow[i].rowIndex % 2 == 1) {
			tbRow[i].style.backgroundColor = "lightblue";
			tbRow[i].onmouseover = function() {
				this.style.backgroundColor = "#bbf";
			}

			tbRow[i].onmouseout = function() {
				this.style.backgroundColor = "lightblue";
			}
		} else {
			tbRow[i].style.backgroundColor = "#ae9";
			tbRow[i].onmouseover = function() {
				this.style.backgroundColor = "#88b";
			}

			tbRow[i].onmouseout = function() {
				this.style.backgroundColor = "#ae9";
			}
		}
	}

	//归还书籍:从借阅表中删除借阅信息，向归还信息表中插入一条信息
	function returnBook(bId,userId, bookNo) {
		if (confirm("确定要归还吗?")) {
			//alert("bId="+bId+"userId=" + userId + ",bookNo=" + bookNo);
			location.href = "${pageContext.request.contextPath}/returnBookServlet?bId="+bId+"&userId="
					+ userId + "&bookNo=" + bookNo;
		}
	}
</script>
</html>