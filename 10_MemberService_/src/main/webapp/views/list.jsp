<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
		<style>
			table, th, td{
				border: 1px solid black;
				border-collapse: collapse;
				padding: 5px 10px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="loginBox.jsp"></jsp:include>
		<h3>회원 리스트</h3>
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>나이</th>
					<th>삭제</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${list}" var="member">
				<tr>
					<td>${member.id}</td>
					<td><a href="detail?id=${member.id}">${member.name}</a></td>
					<td>${member.age}</td>
					<td><a href="del?id=${member.id}">삭제</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</body>
	<script>

	</script>
</html>