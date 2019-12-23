<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.zm.bean.*"%>

<!-- 此页面功能：展示借阅历史信息，单项删除，批量删除 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>还书成功信息页面</title>
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
	<%
		List<ReturnBook> returnBooks = (List<ReturnBook>) session.getAttribute("returnBooks");
	%>
	<h2>归还信息</h2>

	<c:if test="${returnBooks.size()>0}">
		<div id="div1">
			<input type="button" value="批量删除" id="delBatch">
		</div>


		<form action="${pageContext.request.contextPath }/delBatchReturnBook"
			method="post" id="myform">
			<table id="tb" width="90%" height="100%" border="1px solid"
				cellspacing="0" cellpadding="0" class="table table-bordered">
				<thead>
					<tr>
						<td><input type="checkbox" name="allchks" id="allchks" /></td>
						<td>序号</td>
						<td>索书号</td>
						<td>借阅日期</td>
						<td>实际归还日期</td>
						<td>罚金</td>
						<td>操作</td>
					</tr>
				</thead>

				<tbody id="tb2">

					<input type="hidden" name="userId" value="1001">

					<c:forEach items="${returnBooks}" var="returnBook"
						varStatus="status">
						<tr>
							<td><input type="checkbox" name="rId" class="chks"
								value="${returnBook.rId}" /></td>
							<td>${status.count}</td>
							<td>${returnBook.bookNo}</td>
							<td>${returnBook.borrowTime}</td>
							<td>${returnBook.realReturnTime}</td>
							<td>12.3</td>
							<td><a type="button" class="delBtn"
								href="javascript:delById(${returnBook.rId},'${returnBook.userId}')">删除</a>
							</td>
						</tr>
					</c:forEach>
			</table>
		</form>

		<script type="text/javascript">

			/*批量删除与单项删除*/

			//1. 单项删除
			function delById(rId, userId) {
				alert("rId=" + rId + ",userId=" + userId);
				if (confirm("确定删除该条记录吗?")) {
					location.href = "${pageContext.request.contextPath}/delReturnBookServlet?rId="
							+ rId + "&userId=" + userId;
				}
			}

			//2. 批量删除
			window.onload = function() {
				
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
				
				//全选全不选
				//1.获取全选按钮
				var allchks = document.getElementById("allchks");
				//2.获取除全选按钮以外的复选框
				var chks = document.getElementsByClassName("chks");
				allchks.onclick = function() {
					var allchksStatus = allchks.checked;
					for (var i = 0; i < chks.length; i++) {
						chks[i].checked = allchksStatus;
					}
				}
				
				document.getElementById("delBatch").onclick = function() {
					if (confirm("确定要删除吗?")) {
						document.getElementById("myform").submit();
					}
				}
			}
		</script>
	</c:if>
	<br />
	<c:if test="${returnBooks.size()==0}">
		<font style="color: grey; text-align: center; font-size: 20px">历史信息已清空!</font>
	</c:if>
</body>

</html>