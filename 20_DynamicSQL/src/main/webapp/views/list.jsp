<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/common.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<form action="list.do" method="GET">
		<select name="opt">
			<option value="id">아이디</option>
			<option value="name">이름</option>
			<option value="email">이메일</option>
		</select>
		<input type="text" name="keyword" placeholder="검색어를 입력하세요.">
		<button>검색</button>
	</form>


	<h3 class="inline">회원 리스트</h3>
	<button onclick="location.href='join.go'">회원가입</button>
	<form action="join.do">	
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>이름</th>
					<th>이메일</th>
					<th>삭제</th>
				</tr>
			</thead>		
			<tbody>
			<!-- c태그는 자식요소로 인식되지 않는다. -->
			<c:forEach items="${list}" var="member">
				<tr>
					<td>${member.id}</td>
					<td><a href="detail.do?id=${member.id}">${member.name}</a></td>
					<td>${member.email}</td>
					<td><a href="del.do?id=${member.id}">삭제</a></td>
				</tr>			  
			</c:forEach>		
			</tbody>
		</table>
	</form>	
		<div class="search_layer">
			<form action = "multi.do" method="post">
				이름이
				<ul>
					<li>
						<input type="text" name="userName"/>
						<input type="button" value="또는" onclick="add(this)"/>
					</li>
				</ul>
				인 회원 찾기
				<button>찾기</button> 
			</form>		
		</div>
</body>
<script>

	function add(elem){
		$(elem).parent().parent().append('<li>' + $(elem).parent().html() + '</li>');
	}
	
</script>
</html>