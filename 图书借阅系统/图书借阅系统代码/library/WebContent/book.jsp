<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.zm.bean.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示页面-全选全不选</title>
<script src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.js"
	type="text/javascript"></script>
<%-- <link href="<%=request.getContextPath() %>/static/bootstrap.min.css" rel="stylesheet">
    <script src="$<%=request.getContextPath() %>/static/bootstrap.min.js"></script> --%>

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

/* 分页条 */

ul.pagination {
    display: inline-block;
    padding: 0;
    margin: 0;
}

ul.pagination li {display: inline;}

ul.pagination li a {
    color: black;
    float: left;
    padding: 8px 16px;
    text-decoration: none;
    transition: background-color .3s;
    border: 1px solid #ddd;
}

.pagination li:first-child a {
    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;
}

.pagination li:last-child a {
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
}

ul.pagination li a.active {
    background-color: #4CAF50;
    background-color：green;
    color: white;
    border: 1px solid #4CAF50;
}

ul.pagination li a:hover:not(.active) {background-color: #ddd;}
#pageInfo{
   margin-right:60px;
   margin-top:50px;
}

</style>

</head>

<body>

	<%-- <%
		List<Book> bookList = (List<Book>) session.getAttribute("bookList");
	%> --%>
	<h2>书籍信息</h2>
	<form action="${pageContext.request.contextPath}/selectBookByPage" method="get">
		<div id="div1">
			<input type="text" name="bookNameWithKey" placeholder="请输入关键字或书名"
				id="search_inp"> <input type="submit" id="search" value="查询">
		</div>
	</form>
	<div>
		<table id="tb" width="90%" height="100%" border="1px solid"
			cellspacing="0" cellpadding="0" class="table table-bordered">
			<thead>
				<tr>
					<td>序号</td>
					<td>索书号</td>
					<td>书籍名称</td>
					<td>出版社</td>
					<td>数量</td>
					<td>馆藏地址</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>

			<tbody id="tb2">
				<c:forEach items="${pageBean.list}" var="book" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${book.bookNo}</td>
						<td>${book.bookName}</td>
						<td>${book.publishAddr}</td>
						<td>${book.bookNum}</td>
						<td>${book.roomAddr}</td>
						<td><c:if test="${book.bookNum <= 0}">
								<font color="red">不可借</font>
							</c:if> <c:if test="${book.bookNum > 0}">
								<font color="green">可借</font>
							</c:if></td>
						<td><a type="button" class="borrowBtn"
							href="javascript:borrow(${user.userId },'${book.bookNo}')"> 借阅 </a></td>
					</tr>
				</c:forEach>
		</table>
		
		<div width="100%" height="200px" border="1px" id="pageInfo">
		     <ul class="pagination">
		        
		         <c:if test="${pageBean.curPage>1}">
		            <li>
		               <a href="${pageContext.request.contextPath}/selectBookByPage?curPage=${pageBean.curPage-1}&rows=10">&laquo</a>
		            </li>
		        </c:if>
		        
		        <c:forEach begin="1" end="${pageBean.totalPage }" var="i">
		          <li> 
		             <c:if test="${pageBean.curPage == i}">
		                  <a href="${pageContext.request.contextPath}/selectBookByPage?curPage=${i}&rows=10" class="active">${i}</a>
		             </c:if>
		              <c:if test="${pageBean.curPage != i}">
		                  <a href="${pageContext.request.contextPath}/selectBookByPage?curPage=${i}&rows=10">${i}</a>
		             </c:if>
		           </li>
		        </c:forEach>
		         <c:if test="${pageBean.curPage<pageBean.totalPage}">
		            <li>
		               <a href="${pageContext.request.contextPath}/selectBookByPage?curPage=${pageBean.curPage+1}&rows=10">&raquo</a>
		            </li>
		        </c:if>
		     </ul>
		</div>

	</div>


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

	function borrow(userId, bookNo) {
		if (confirm("确定要借阅吗?")) {
			location.href = "${pageContext.request.contextPath}/borrowBook?userId="
					+ userId + "&bookNo=" + bookNo;
		}
	}
</script>

</html>