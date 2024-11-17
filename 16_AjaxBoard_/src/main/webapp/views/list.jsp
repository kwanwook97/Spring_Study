<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
	table, th, td{
		border : 1px solid black;
		border-collapse: collapse;
		padding : 5px 10px;
	}
</style>
</head>
<body>
	<button onclick="location.href='insert.go'">입사자 등록</button>
	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>나이</th>
				<th>이메일</th>
			</tr>		
		</thead>
		<tbody id="list">
		
		</tbody>
	</table>
</body>
<script>
	$.ajax({
		type:'GET',
		url:'list.ajax',
		data:{},
		dataType:'JSON',
		success:function(data){
			console.log(data);
			if(data.login){
				drawList(data.list);				
			}else{
				alert('로그인이 필요한 서비스 입니다.');
				location.href='./';
			}			
		},error:function(e){
			console.log(e);
		}
	});
	
	function drawList(list){
		var content = '';
		list.forEach(function(item,idx){
			content += '<tr>';
			content += '<td>'+item.id+'</td>';
			content += '<td>'+item.name+'</td>';
			content += '<td>'+item.age+'</td>';
			content += '<td>'+item.email+'</td>';
			content += '</tr>';
		});
		$('#list').html(content);
	}
</script>
</html>













