<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>#</th>
				<th>Title</th>
				<th>Author(s)</th>
				<th>Catalog</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${books}" var ="book">
			<tr>
				<td>
					<c:out value="${book.id}" />
				</td>
				<td>
					<c:out value="${book.title}" />
				</td>
				<td>
					<c:forEach items="${book.authors}" var="author">
						<c:out value="${author}"/>,
					</c:forEach>
				</td>
				<td>
					<c:out value="${book.catalog.name}" />
				</td>
				<td><a href="BookController?action=edit&bookId=<c:out value="${book.id}"/>">Update</a></td>
                <td><a href="BookController?action=delete&bookId=<c:out value="${book.id}"/>">Delete</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="BookController?action=add" >Add new book</a>
</body>
</html>