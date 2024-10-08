<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
		<!-- controller에 css라는 요청이 없으면 static폴더 밑에 css를 찾는다. -->
		<link rel="stylesheet" href="css/common.css" type="text/css">
	</head>
	<body>
		<jsp:include page="loginBox.jsp"/>
		<form action="update" method="post">
		<input type="hidden" name="idx" value="${info.idx}">
			<table>
				<caption>수정하기</caption>
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" name="user_name" value="${info.user_name}" readonly/>
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
						<textarea name="content">${info.content}</textarea>
					</td>
				</tr>
				
				<tr>					
					<th colspan = "2">
						<input type="submit" value="수정"/>
					</th>
				</tr>
			</table>
		</form>
	</body>
	<script>

	</script>
</html>