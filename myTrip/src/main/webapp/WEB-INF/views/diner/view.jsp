<%@page import="com.mytrip.diner.service.DinerViewService"%>
<%@page import="com.mytrip.main.controller.ExecuteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<meta charset="UTF-8">
	<title>맛집 자세히 보기</title>
<style type="text/css">
  .img{
    position: relative;                                                           
    height: 50vh;
    background-size: cover;
  }
  .img-cover{
     position: absolute;
     height: 100%;
     width: 100%;
     background-color: rgba(0, 0, 0, 0.7);
     background-size: cover;                                                              
     z-index:1;
  }
  .img .content{
     position: absolute;
     bottom: 5%;
     left: 5%;                                                                 
     font-size: 5rem;
     color: white;
     z-index: 2;     
  }
  .tag {
  	font-weight:bold;
  	font-style:italic;
  	color: #808080;
  }
</style>
<script type="text/javascript">
//jqeury() - html 문서가 로딩이 되면 함수를 호출해서 처리
$(function() {
	$('[data-toggle="tooltip"]').tooltip();
	$("#updateBtn").click(function(){
		location = "updateForm.do?no=${vo.no}&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
			+ "&area=${param.area}&kind=${param.kind}";
	});
	$("#deleteBtn").click(function(){
		if(confirm("정말 삭제하시겠습니까?")) { location = "delete.do?no=${vo.no}&del=${vo.mainImage}"; }
	});
	$("#listBtn").click(function(){
		location = "list.do?page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
			+ "&area=${param.area}&kind=${param.kind}";
	});
});
</script>
</head>
<body>
   	<input type="hidden" name="page" value="${pageObject.page }">
   	<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
   	<input type="hidden" name="key" value="${pageObject.key }">
   	<input type="hidden" name="word" value="${pageObject.word }">
   	<input type="hidden" name="del" value="${vo.mainImage }">
	<div class="container">
	<!-- 맛집 내용 출력 -->
<!-- 			<h1>맛집 소개</h1>		 -->
<%-- 			<p>${vo.no }</p> --%>
	<div class="container">
		<div class="img" style="background-image: url('${vo.mainImage}');">
	        <div class="content">
	        <h1>${vo.name }</h1>
	        <h3>${vo.introduce }</h3>
	        <h4>
	        	${vo.areaName } - ${vo.kindName } <br>
	        	주소 : ${vo.address } <br>
	        	연락처 : ${vo.tel } <br>
	        	<c:if test="${!empty vo.openTime }">
	        	영업시간 : ${vo.openTime } <br>
	        	</c:if>
	        	<c:if test="${!empty vo.restDay }">
	        	휴일 : ${vo.restDay } <br>
	        	</c:if>
	        </h4>        
	        </div>
	        <div class="img-cover"></div>
		</div>
	</div>
	
	<hr style="border:solid; color=silver">
	
	<div class="container">		
		<div class="container">
			<p><img src="${vo.subImage }" class="img"></p>
			<p>${vo.content }</p>
			<c:if test="${!empty vo.tag }">
				<p style="color: blue">#${vo.tag }</p>
			</c:if>
			<p>영업시간 : ${vo.openTime }</p>
			<p>휴일 : ${vo.restDay }</p>
		</div>		
	</div>

		
		<!-- 좋아요, 찜하기 버튼 -->
		<div style="text-align: center;">
			<div style="display: inline-block;">
				<c:if test="${empty login}">
					<!-- 로그인을 하지 않은 경우 - 좋아요 기능 이용 불가 -->
					<i class="fa fa-thumbs-o-up" style="font-size:30px; color:grey;" data-toggle="tooltip" title="로그인후에 사용하실 수 있습니다.">좋아요</i>
				</c:if>
				<c:if test="${!empty login}">
					<c:if test="${empty vo.myLiked }">
						<!--  myLiked가 비어있다면 = 좋아요를 누른 적이 없다면 좋아요를 누를 수 있다. -->
						<a href="like.do?<%= request.getQueryString() %>">
							<i class="fa fa-thumbs-o-up" style="font-size:30px;" data-toggle="tooltip" title="클릭하여 좋아요를 할 수 있습니다.">좋아요</i>
						</a>
					</c:if>
					<c:if test="${!empty vo.myLiked }">
						<!-- myLiked가 비어있지 않으면 ('LIKED')면 = 이미 좋아요를 누른적이 있다면 좋아요를 취소할 수 있다.  -->
						<a href="likeCancel.do?<%= request.getQueryString() %>">
							<i class="fa fa-thumbs-o-up" style="font-size:30px; color:blue;" data-toggle="tooltip" title="클릭하여 좋아요를 할 수 있습니다.">취소</i>
						</a>
					</c:if>				
				</c:if>
				<div>
					<p style="font-weight: bold">${vo.likeCnt }</p>
				</div>
			</div>
			<div style="display: inline-block;">
				<c:if test="${empty login}">
					<i class="fa fa-bookmark-o" style="font-size:30px; color:grey;" data-toggle="tooltip" title="로그인후에 사용하실 수 있습니다.">찜하기</i>				
				</c:if>
				<c:if test="${!empty login}">
					<c:if test="${empty vo.myFav }">
						<a href="fav.do?<%= request.getQueryString() %>">
							<i class="fa fa-bookmark-o" style="font-size:30px;" data-toggle="tooltip" title="클릭하여 찜하기를 할 수 있습니다.">찜하기</i>
						</a>
					</c:if>
					<c:if test="${!empty vo.myFav }">
						<a href="favCancel.do?<%= request.getQueryString() %>">
							<i class="fa fa-bookmark-o" style="font-size:30px; color:blue;" data-toggle="tooltip" title="클릭하여 찜하기를 취소 할 수 있습니다.">취소</i>
						</a>
					</c:if>				
				</c:if>
				<div  style="font-weight: bold">
					<p>${vo.favCnt }</p>
				</div>
			</div>
		</div>
		<hr style="border:solid; color=silver;">
		<!-- 그 외 기능 버튼 위치 -->
		<c:if test="${!empty login && login.gradeNo == 9 }"> <!-- 관리자 계정일때 수정, 삭제 표시 -->
			<button id="updateBtn" class="btn-default" data-toggle="tooltip" title="제목과 내용만 수정">수정</button>
			<button id="deleteBtn" class="btn-default">삭제</button>
		</c:if>
			<button id="listBtn" class="btn-default">리스트</button>
			
	</div>
</body>
</html>