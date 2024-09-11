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
		<table>
			<caption>상세보기</caption>
			<tr>
				<th>작성자</th>
				<td>
					${info.user_name}
				</td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td>
					${info.subject}
				</td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td>
					${info.content}		
				</td>
			</tr>
			
			<tr>					
				<th colspan = "2">
					<a href="list">리스트</a>
					&nbsp;&nbsp;&nbsp;&nbsp;	
					<a href="updateForm?idx=${info.idx}">수정</a>
				</th>
			</tr>
		</table>
	</body>
	<script>

	</script>
</html>