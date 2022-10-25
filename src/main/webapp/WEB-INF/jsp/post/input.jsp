<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스나 갤러리-글작성화면</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
  	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
  	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
  	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
  	<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
  	
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
			<div class="col-9 my-5">
				<h2 class="text-center">글 작성</h2>
				<div>
					<select id="topicInput">
						<option selected>게시판을 선택해주세요</option>
						<option value="noti" >공지사항</option>
						<option value="new" >뉴스</option>
						<option value="commu" >커뮤니티</option>
					  </select>
					 <select  id="topicInput2">
						<option  selected>게시판을 선택해주세요</option>
						<option >운동갤러리</option>
						<option >게임갤러리</option>
					</select>
					
				</div> 
				<div class="d-flex mt-3">
					<label class="col-2">제목 : </label> 
					<input type="text" class="form-control col-10" id="titleInput">
				</div> <br>
				<textarea class="form-control mt-2" rows="7" id="summernoteInput"></textarea>
				<input type="file" class="mt-2" id="fileInput">
				
				<div class="d-flex justify-content-between mt-3">
					<a class="btn btn-primary" id="backBtn">목록으로</a>
					<button type="button" class="btn btn-primary" id="saveBtn">저장</button>
				</div>
			
			</div>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
</body>
	<script>
	$(document).ready(function() {
		
		$("#topicInput2").hide();
		
		$('#topicInput').val('noti').prop("selected",true);
		
		$('#topicInput').val('new').prop("selected",true);
		
		$('#topicInput').val('commun').prop("selected",true);
		
		 $('#summernoteInput').summernote({
	            lang: 'ko-KR',
	            height: 300,
	            placeholder: '내용을 입력하세요',
	            toolbar: [
	                ['fontname', ['fontname']],
	                ['fontsize', ['fontsize']],
	                ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
	                ['color', ['forecolor', 'color']],
	                ['table', ['table']],
	                ['para', ['ul', 'ol', 'paragraph']],
	                ['height', ['height']],
	                ['insert', ['picture', 'link', 'video']],
	                ['view', ['fullscreen', 'help']]
	              ],
	              fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체',
	                '굴림', '돋음체', '바탕체'],
	              fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36',
	                '50', '72']
	           
	            
	        });
		 
     	$("#backBtn").on("click", function() {
			let back = $("#backBtn").val();
			if(back == "") {
				history.back();
				return ;
				}	
			});
		
     	$("#topicInput").change(function() {
    		let topic = $("#topicInput option:selected").val();
    		if(topic == "commu") {
    			$("#topicInput2").show();
    		}else {
    			$("#topicInput2").hide();
    		}
    	});
     	
     	 $("#saveBtn").on("click", function() {
     			let topic = $("#topicInput").val();
				let title = $("#titleInput").val();
				let summernote = $("#summernoteInput").val();
				
				
				if(topic == "게시판을 선택해주세요") {
					alert("주제를 선택하세요");
					return ;
				}
					
			if(title == "") {
					alert("제목을 입력하세요");
					return ;
				}
				
				if(summernote == "") {
					alert("내용을 입력하세요");
					return ;
				}
				
				var formData = new FormData();
				formData.append("topic", topic);
				formData.append("title", title);
				formData.append("content", summernote);
				formData.append("file", $("#fileInput")[0].files[0]);
				
				$.ajax({
					type:"post"
					, url:"/post/create"
					, data:formData
					, enctype:"multipart/form-data"  // 파일 업로드 필수 옵션
					, processData:false  // 파일 업로드 필수 옵션
					, contentType:false  // 파일 업로드 필수 옵션
					, success:function(data) {
	 				
						if(data.result == "success") {
							//location.href="/post/new/view";
							history.back();
						} else {
							alert("입력 실패");
						}
						
					}
					, error:function() {
						alert("입력 에러");
					}
				})
				
				
		 		});
		 });
	
	</script>
</html>