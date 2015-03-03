<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/main.css" />" type="text/css" rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/main.js" />" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<form method="post" action="BookController">
		<table border="1" id="add_table">
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
					<tr class="author_row">
						<td>Author: </td>
						<td> 
							<input type="text" id="author-1" name="author-1" value="<c:out value="${author.fullName }" />" />
						</td>
				    </tr>
		<tr>
			<td> Catalog: </td>
			<td><input type="text" id="catalog" name="catalog" value="<c:out value="${book.catalog }" />" />
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" /></td>
			<td><button type="button" id="author_plus">Author +</button> <button type="button" id="author_minus">Author -</button></td>
		</tr>
		<input type="hidden" name="k" id="k" value="1" />
		</table>
	</form>
</body>
</html>