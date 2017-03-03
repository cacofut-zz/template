<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="css"
	href='<c:url value="/resources/style.css" />'>

</head>
<body>
	<h1>Welcome to Spittr</h1>
	<a href="<c:url value="/spittles" />">Spittles</a> |
	<a href="<c:url value="/spitter/register" />">Register</a>

	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Sobrenome</th>
				<th>Golpe</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${narutos}" var="naruto">
				<tr>
				
					<td><c:out value="${naruto.nome}" /></td>
					<td>${naruto.sobrenome}</td>
					<td>${naruto.golpe}</td>
					
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
		</tfoot>
	</table>

</body>
</html>