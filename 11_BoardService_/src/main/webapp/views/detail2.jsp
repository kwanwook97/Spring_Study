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
		<jsp:include page="loginBox.jsp"/>
		<form action="" method="post">
			<table>
				<tr>
					<th>No.</th>
					<td>
						<input type="text" name="idx" value="${info.idx}"/>
					</td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="user_name" value="${info.user_name}"/>
					</td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="subject" value="${info.subject}"/>
					</td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td>
						<input type="text" name="content" value="${info.content}"/>
					</td>
				</tr>
				
				<tr>
					<th>조회수</th>
					<td>
						<input type="text" name="bHit" value="${info.bHit}"/>		
					</td>
				</tr>
				
				<tr>
					<th>게시날짜</th>
					<td>
						<input type="text" name="reg_date" value="${info.reg_date}"/>
					</td>
				</tr>
				
				<tr>
				<th colspan="2">
						<a href="list">뒤로가기</a>
				</th>
				</tr>
			</table>
		</form>
	</body>
	<script>

	</script>
</html>