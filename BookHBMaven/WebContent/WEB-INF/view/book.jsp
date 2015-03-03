<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
	int k = 0;
%>
<body>
	<form method="post" action="BookController">
		<table border="1">
			<tr>
				<td> id: </td>
				<td>
					<input id="bookId" name="bookId" readonly value="<c:out value="${book.id }" />" />
				</td>
			</tr>	
			<tr>
				<td>Title: </td>
				<td>
					<input type="text" id="title" name="title" value="<c:out value="${book.title}" />" />
				</td>
			</tr>
			<c:if test="${empty book.authors}">
				<tr>
					<td>Author: </td>
					<td> 
						<input type="text" id="author" name="author" value="<c:out value="${author.fullName }" />" />
					</td>
			    </tr>
			</c:if>
			<c:forEach items="${book.authors}" var="author">
			<% ++k; %>
		    <tr>
				<td>Author: </td>
				<td> 
					<input type="text" id="author-<%=k %>" name="author-<%=k %>" value="<c:out value="${author.fullName }" />" />
				</td>
		    </tr>
		</c:forEach>
		<tr>
			<td> Catalog: </td>
			<td><input type="text" id="catalog" name="catalog" value="<c:out value="${book.catalog }" />" />
			</td>
		</tr>
		<input type="hidden" name="k" id="k" value="<%= k %>" />
		<tr>
			<td><input type="submit" value="Submit" /></td>
		</tr>
		</table>
	</form>
</body>
</html>