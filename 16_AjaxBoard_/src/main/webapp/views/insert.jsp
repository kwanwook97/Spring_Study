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
	div.career{
		border: 1px solid lightgray;
		margin-top : 10px;
		width: 645px;
		padding: 10px;
	}
	
	ul li{
		list-style-type: none;
		margin-left: -40px;
		border-bottom: 1px solid gray;
		width : 645px;
		padding: 10px;
	}
	
	ul li:nth_child(odd){
		background-color: lightgray;
	}
	
	.row2{
		height: 45px;
		width: 60px;
	}
	
</style>
</head>
<body>
	
</body>
<script>
	// jquery 에서는 다른 페이지를 불러오는 기능을 제공 한다.
	// $('표현할 위치').load(불러올페이지경로 셀렉터, 콜백);
	$('body').load('html/card.html #career_card',function(res,stat){
		console.log(res); // 가져온 전체 소스
		console.log(stat); // 성공여부 상태
	});
	
	function addList(elem){
		var html = $(elem).parent().html();
		//console.log(html);
		$('ul.career').append('<li>'+html+'</li>');
	}
	
	function regiest(){
		// 기본정보
		var name = $('input[name="name"]').val();
		var gender = $('input[name="gender"]:checked').val();
		var birth_date = $('input[name="birth_date"]').val();
		var hire_date = $('input[name="hire_date"]').val();
		console.log(name,gender,birth_date,hire_date);		
		
		// 커리어정보(회사별 정보를 object 에 담고, list 에 추가)
		var list = [];
		$('ul.career li').each(function(idx,item){
			var career = {};
			$(item).find('input').each(function(idx, elem){
				career[$(elem).attr('name')] = $(elem).val();
			});
			list.push(career)
		});
		console.log(list);
		
		var param = {};// 서버로 전송할 파라메터
		param.name = name;
		param.gender = gender;
		param.birth_date = birth_date;
		param.hire_date = hire_date;
		param.list = list;
		console.log('서버에 전송할 데이터 : ',param);
		
		// {'key':'value'} 형태가 아닌 복잡한 형태의 경우 이전에 쓰던 방식으로는 전송이 불가능 하다.
		// 복잡한 형태? {key:value, key:[{key:value},...]}
		// 복잡한 JSON 형태는 어떻게 전송하나?
		$.ajax({
			type:'POST',//1. POST 로 전송해야 한다.
			url:'insert.ajax',
			data:JSON.stringify(param),//2. 보내는 파라메터는 json 형태의 문자열 이어야 할것
			dataType:'JSON',
			contentType:'application/json; charset=UTF-8', // content-type 이 json 이라고 명시해줘야 함
			success:function(data){
				console.log(data);
			},error:function(e){
				console.log(e);
			}
		});		
		
	}
	
</script>
</html>










