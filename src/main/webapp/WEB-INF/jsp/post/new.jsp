<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스나 갤러리-뉴스화면</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
			<section class="d-flex justify-content-center">
			<div class="col-9 my-5">
				<h2 class="text-center">뉴스</h2>
				
				<table class="table text-center mt-3">
					<thead>
						<tr>
							<th>No.</th>
							<th>제목</th>
							<th>시간</th>
						</tr>
						
					</thead>
					<tbody>
					
						<c:forEach var="post" items="${postList }" >
						<tr>
							<td>${post.id }</td>
							<td><a href="/post/detail/view?id=${post.id }"> ${post.subject } </a></td>
							<td><fmt:formatDate value="${post.createdAt }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						
						</c:forEach>
					
					</tbody>
				
				</table>
				
				<div class="text-right">
					<a href="/post/input/view" class="btn btn-primary" id="textBtn">글쓰기</a>
				</div>
				
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	<script>
	$(document).ready(function() {
		$("#textBtn").on("click", function() {
			let login = $({userLoginId }).val();
			
			if(login == 0) {
				alert("로그인 후 이용가능합니다");
				location.href="/post/signin/view";
				return ;
			}
				
		});

		
	});
	
	</script>
</body>
</html>