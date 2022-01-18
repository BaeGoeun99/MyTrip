<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>축제 정보 보기</title>

<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&display=swap" rel="stylesheet">

<script type="text/javascript">
$(function(){
	$("#updateBtn").click(function(){
		location = "updateForm.do?no=${vo.no}&page=${param.page}&perPageNum=${param.perPageNum}";
	});
	$("#deleteBtn").click(function(){
		if(confirm("축제 정보가 완전히 사라집니다. 정말 삭제하시겠습니까?"))
			location = "delete.do?no=${vo.no}&del=${vo.photo}";
	});
	$("#listBtn").click(function(){
		location = "list.do?page=${param.page}&perPageNum=${param.perPageNum}";
	});
		
	$("#reform").attr("action", "repwrite.do");
	
	$(".deleteRepBtn").click(function(){
		var rno = $(this).closest("div").find(".rno").val();
		alert("리뷰가 삭제됩니다. 정말 삭제하시겠습니까? : " + rno);
			location = "repdelete.do?no=${vo.no}&rno=" + rno;
	});
	
});

$(document).ready(function(){
	  $("a").on('click', function(event) {
	    if (this.hash !== "") {
	      event.preventDefault();
	      var hash = this.hash;
	      $('html, body').animate({
	        scrollTop: $(hash).offset().top
	      }, 600, function(){
	        window.location.hash = hash;
	      });
	    }
	  });
	});
</script>

<style type="text/css">
th{
	width: 15%;
	text-align: center;
	background: #eee;
}
.like{
	text-align: right;
}
.fa{
	padding: 1px 5px 1px 5px;
}
.titleType, .contentType, .reviewType{
	width: 100%;
	padding: 10px;
	font-family: 'IBM Plex Sans KR', sans-serif;
	text-align: center;
}
.contentType{
	clear:both;
	padding: 10px 25px 10px 25px;
    text-align: left;
    font-size: 17px;
}
.title-rgn{
    font-weight: bold;
}
.btn_kt{
	vertical-align: super;
	padding: 5px;
}
.ctc-css{
	font-size: 14px;
	color: #333;
	line-height: 22px;
}
.bottomType{
	clear: both;
	width: 100%;
	padding: 10px;
	font-family: 'IBM Plex Sans KR', sans-serif;
}
.top_admin_area{
	padding: 5px;
}
.top_admin_area button{
	margin-left: 5px;
}
ul{
	list-style: none;
}
.ctc-nv_ul li{
	float: none;
    display: inline-block;
    padding: 8px 13px 8px 13px;
    font-size: 18px;
    color: black;
    width: 20%;
}
.ctc-nv_ul a{
	color: #4d4d4d;
}
.ctc-nv_ul{
	list-style: none;
	clear: both;
	margin-bottom: 0px;
	border-bottom: 1px solid #e6e6e6;
    border-top: 1px solid #e6e6e6;
    
}
.re-wit{
	background: #f7f7f7;
    border: 1px solid #e5e5e5;
    padding: 15px 10px 15px 10px;
}
.re-wit textarea{
	width: 100%;
	resize: none;
}
.logbtn{
	border: none;
    border-radius: unset;
    background: #13294b;
    color: white;
    font-weight: bold;
}
.setbtn{
/* 	float: right; */
	border: none;
    border-radius: unset;
    background: #13294b;
    color: white;
    font-weight: bold;
}
.writeForm{
    width: 90%;
    display: inline-block;
}
.setbtn-box{
	width: 100%;
    display: inline-block;
    text-align: right;
}
.wrap_reply{
	width: 100%;
    height: auto;
}
.list_reply{
	width: 100%;
	text-align: left;
}
.list_reply li{
	width: 100%;
	padding: 5px;
	border-bottom: 1px solid #bbb;
}
.txt_rep{
	padding-left: 10px;
    display: inline-block;
    width: 80%;
}
.ico_rep{
	float: left;
	width: 52px;
}
.h4-css {
    font-weight: bold;
    border-bottom: 2px solid #666;
    padding: 2px;
}
.up-del-btn-box{
	width: 10%;
	display: inline-block;
}
.up-btn, .del-btn{
	float: right;
    border-radius: unset;
    background: white;
    color: #13294b;
    font-weight: bold;
}

</style>

</head>
<body>
<div class="container">

<!-- 관리자용 버튼 -->
<c:if test="${!empty login && login.gradeNo == 9 }">
	<div class="top_admin_area">
		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">사진수정</button>
		<button id="updateBtn" class="btn btn-default">글수정</button>
		<button id="deleteBtn" class="btn btn-default">삭제</button>
		<button id="listBtn" class="btn btn-default">뒤로가기</button>
		<button type="button" class="btn btn-default">[No:${vo.no }]</button>
	</div>
</c:if>
<!-- 바디 상단 부분 -->
<div class="titleType">

<div class="title-rgn">
<!-- 타이틀 -->
<p style="font-size: 36px; color: #333;">${vo.title }</p>
<!-- 지역 | 축제기간 -->
<p style="font-size: 14px; color: #666;">${vo.rgn } | ${vo.startDate } ~ ${vo.endDate }</p>

</div>

<!-- 북마크 표시 처리 -->
<div class="like">
	<!-- 카톡공유 -->
	<a href="https://open.kakao.com/o/gtMRgzcd" class="btn_kt">
		<img alt="카톡공유" src="/upload/ico_kakao.png">
	</a>
<!-- 로그인을 하지 않은 경우 북마크 -->
	<c:if test="${empty login}">
			<i class="fa fa-bookmark-o" style="font-size:30px; color: gray;" data-toggle="tooltip" title="로그인이 필요한 서비스입니다"></i>
	</c:if>
<!-- 로그인을 한 경우 북마크 -->
	<c:if test="${!empty login}">
		<!-- mylike에 데이터가 없으면 북마크 가능 -->
		<c:if test="${empty vo.mylike }">
			<a href="like.do?<%= request.getQueryString() %>">
				<i class="fa fa-bookmark-o" style="font-size:30px"></i>
			</a>
		</c:if>
		<!-- mylike에 데이터가 있으면 북마크 취소 가능 -->
		<c:if test="${!empty vo.mylike }">
			<a href="likeCancel.do?<%= request.getQueryString() %>">
				<i class="fa fa-bookmark" style="font-size:30px"></i>
			</a>
		</c:if>
	</c:if>
</div>
<!-- 컨텐츠 네비바 -->
<div class="ctc-nv" >
	<ul class="ctc-nv_ul" style="margin: unset; padding: unset;">
		<li ><a href="#">축제</a></li> | 
		<li ><a href="#contentType">사진구경</a></li> | 
		<li ><a href="#bottomType">상세정보</a></li> | 
		<li ><a href="#reviewType">리뷰보기</a></li>
	</ul>
</div>
</div>

<!-- 바디 중간 부분 -->
<div class="contentType" id="contentType">
<!-- 이미지 -->
<p style="text-align: center;"><img src="${vo.photo }" alt="${vo.title }"></p>
<!-- 내용 -->

${fn:replace(vo.content, replaceChar, "<br/>" )}

</div>

<!-- 바디 하단 부분 -->
<div class="bottomType" id="bottomType">
<h4 class="h4-css">상세정보</h4>
<ul class="ctc-css">
	<li><strong>주소 : </strong><span>${vo.address }</span></li>
	<li><strong>연락처 : </strong><span>${vo.tel }</span></li>
	<li><strong>홈페이지 : </strong><span><a href="${vo.website }">${vo.website }</a></span></li>
	<li><strong>축제 시작일 : </strong><span>${vo.startDate }</span></li>
	<li><strong>축제 종료일 : </strong><span>${vo.endDate }</span></li>
	<li><strong>주최 : </strong><span>${vo.host }</span></li>

</ul>

</div>


<!-- 리뷰 부분 -->
<div class="reviewType" id="reviewType">
<h4 class="h4-css" style="text-align: left;">리뷰</h4>

<div style="width: 85%; margin: auto;">


<!-- 로그인 전 리뷰 등록폼 -->
<%-- <c:if test="${!empty login && vo.id == login.id }"> --%>
<c:if test="${empty login }">
<div class="re-wit">
	<form name="reform" id="reform">
		<span class="writeForm">
			<input name="content" class="form-control" value="로그인 후 소중한 댓글을 남겨주세요." type="text" readonly style="border-radius: unset;">
		</span>
		<a href="/member/loginForm.do" class="btn btn-default setbtn">로그인</a>
	</form>
</div>
</c:if>
<!-- 로그인 후 리뷰 등록폼 -->
<c:if test="${!empty login && login.gradeNo == 9 }">
	<c:if test="${empty vo.reply }">
	<div class="re-wit login-re-wit">
		<div class="form">
			<form name="reform" id="reform" action="repwrite.do" method="post">
			<input type="hidden" name="no" id="no" class="rno" value="${vo.no }">
			<input type="hidden" name="writeDate" id="writeDate" value="${rvo.writeDate }">
				<span class="writeForm" style="width: 100%;">
					<textarea name="content" rows="4" id="content" placeholder="소중한 댓글을 남겨주세요." style="width: 100%;"></textarea>
				</span>		
				<div class="setbtn-box">
					<button class="btn btn-default setbtn">등록</button>
				</div>
			</form>
		</div>
	</div>
	</c:if>	
</c:if>	
<!-- 로그인 후 리뷰 등록폼 -->
<c:if test="${!empty login && login.gradeNo == 1 }">
	<c:if test="${empty vo.reply }">
	<div class="re-wit login-re-wit">
		<div class="form">
			<form name="reform" id="reform" action="repwrite.do" method="post">
			<input type="hidden" name="no" id="no" class="rno" value="${vo.no }">
			<input type="hidden" name="writeDate" id="writeDate" value="${rvo.writeDate }">
				<span class="writeForm" style="width: 100%;">
					<textarea name="content" rows="4" id="content" placeholder="소중한 댓글을 남겨주세요." style="width: 100%;"></textarea>
				</span>		
				<div class="setbtn-box">
					<button class="btn btn-default setbtn">등록</button>
				</div>
			</form>
		</div>
	</div>
	</c:if>	
</c:if>	
<!-- 리뷰가 없을 경우 -->
<c:if test="${empty replist }">
	<div class="wrap_reply">
		<p class="rep_none" style="padding: 10px;">등록된 댓글이 없습니다.</p>
	</div>
</c:if>

<!-- 리뷰가 있을 경우 -->
<c:if test="${!empty replist }">
<div class="wrap_reply">
	<div class="list_reply">
		<ul style="padding-left: unset; padding: 8px;">
		<c:forEach items="${replist }" var="vo">
			<li class="dataRow">
				<div class="ico_rep">
					<i class="fa fa-user-circle" style="font-size:40px;color:gray"></i>
				</div>
				<div class="txt_rep">			
					<em class="name">${vo.id }</em>
					<p>${vo.content }</p>
					<span style="font-size: 12px;">
					<input type="hidden" name="writeDate" id="writeDate" value="${vo.writeDate }">
					${vo.writeDate }
					</span>
				</div>
				<c:if test="${!empty login && vo.id == login.id }">
					<div class="up-del-btn-box pull-right">
	<!-- 				<button class="btn btn-default up-btn btn-sm">수정</button> -->
						<input type="hidden" name="rno" class="rno" value="${vo.rno }">
						<button class="btn btn-default del-btn btn-sm deleteRepBtn">삭제</button>
					</div>
				</c:if>
			</li>
		</c:forEach>
		</ul>
	</div>	
</div>
</c:if>
</div>

</div>

</div><!-- 바디끝부분의div입니다 -->

<!-- 사진 단독 수정 Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
	<!-- Modal content-->
		<form action="updatePhoto.do" method="post" enctype="multipart/form-data">
		<!-- 숨겨서 넘어갈 데이타 : 페이지 정보와 검색 정보 -->
		<input type="hidden" name="page" value="${pageObject.page }">
		<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
		<input type="hidden" name="del" value="${vo.photo }">
		    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">축제 사진 변경</h4>
				</div>
			    <div class="modal-body">
			    	<div class="form-group">
				    	<label for="no">축제 정보 번호</label>
						<input type="text" class="form-control" id="no" name="no" readonly value="${vo.no }">
			   		</div>
			    	<div class="form-group">
				    	<label for="imagefile">첨부파일 선택</label>
						<input type="file" class="form-control" id="photo" name="photo">
			   		</div>
			    </div>
			    <div class="modal-footer">
			    	<button class="btn btn-default">업로드</button>
			    	<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			    </div>
		    </div>
		</form>
	</div>
</div>

</body>
</html>