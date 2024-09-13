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
		<button onclick="location.href='writeForm'">글쓰기</button>
		<%-- <jsp:include page="loginBox.jsp"></jsp:include> --%>
		<h3>회원 리스트</h3>
		<table>
			<thead>
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>이름</th>
					<th>조회수</th>
					<th>게시날짜</th>
					<th>삭제</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${list}" var="bbs">
				<tr>
					<td>${bbs.idx}</td>
					<td><a href="detail?idx=${bbs.idx}">${bbs.subject}</a></td>
					<td>${bbs.user_name}</td>					
					<td>${bbs.bHit}</td>
					<td>${bbs.reg_date}</td>
					<td><a href="del?idx=${bbs.idx}">삭제</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</body>
	<script>

	</script>
</html>