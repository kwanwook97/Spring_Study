<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/common.css" type="text/css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src="js/jquery.twbsPagination.js" type="text/javascript"></script>
<style>

</style>
</head>
<body>
	<button onclick="location.href='writeForm'">글쓰기</button>
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody id="list">
		
		</tbody>
		<!-- 페이징이 표시될 곳 -->
		<tr>
			<th colspan="6">
				<div class="container">
				    <nav aria-label="Page navigation">
				        <ul class="pagination" id="pagination"></ul>
				    </nav>
				</div>
			</th>
		</tr>
	</table>
</body>
<script>
var showPage = 1; // 기본 보여줄 페이지

pageCall(showPage);

function pageCall(page){
	console.log('page Call');
    $.ajax({
        type: 'GET',
        url: 'list.ajax', 
        data:{
        	'page' : page, // 몇페이지 보여줘?
        	'cnt' : 20 // 페이지당 몇개의 게시물을 보여줘?
        },
        dataType: 'json', 
        success: function(data) {
			console.log(data);
			listPrint(data.list);
			
			// 페이징 플러그인 처리
			$('#pagination').twbsPagination({
				startPage: 1,//현재 보여지는 페이지
				totalPages: data.totalPages, // 최대 페이지 수
				visiblePages : 5, // 보여줄 페이지의 수
				onPageClick:function(evt, page){
					console.log('evt',evt); // 클릭 이벤트의 모든 내용
					console.log('page',page); // 클릭한 페이지 번호
					pageCall(page);
				}
			});
			
			
        },
        error: function(e) {
            console.log("오류 발생", e);            
        }       
    });
}

function listPrint(list){
	var content = '';
	for (var item of list) {
		content += '<tr>';
		content += '<td>'+item.idx+'</td>';
		content += '<td>'+item.subject+'</td>';
		content += '<td>'+item.user_name+'</td>';
		content += '<td>'+item.bHit+'</td>';
		content += '<td>'+item.reg_date+'</td>';
		content += '<td><a href="#">삭제</a></td>';
		content += '</tr>';
	}
	$('#list').html(content);
	
}

</script>
</html>











