<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="https://code.jquery.com/jquery-3.7.1.slim.min.js"></script>
<style>
	table,td,th{
		border : 1px solid black;
		border-collapse: collapse;
		padding :5px 10px;
	}


</style>

</head>
<body>
	<form action="login.do" method="post"> <!-- id와 pw 노출되지 않게 -->
		<table>
			<tr>
				<th>ID</th>
				<td><input type = "text" name = "id"/></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type = "password" name = "pw"/></td>
			</tr>
			<tr>
				<th colspan = "2">
					<button>LOGIN</button>
					<a href = "join.go">회원가입</a>
					
				</th>
			</tr>
		</table>
	
	</form>
</body>
<script>
	var msg = "${result}";  //스크립트에 쓸경우는 "'에 감싸준다
	if(msg !=''){
		alert(msg); //회원가입했을때 로그인 페이지로 이동 + 뭔가 내용이 있으면 alert로 뜨게
	}
	
</script>
</html>