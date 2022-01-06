<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 ${vo.name }</title>

<style type="text/css">
#delBtn:hover{
	background: #f11b1be8;
	color: white;
}
.upBtn:hover{
	background: #42d142;
	color: white;
}
.table>tbody>tr>th{
	width: 100px;
	text-align: center;
	background: #f9f9f9;
}
.btn-xs{
	margin-bottom: 3px;
}
.btnBox{
	width: 100%;
	padding: 5px;
	margin-top: 5px;
}
.back{
	padding: 3px;
	color: black;
}
.back:hover {
    color: black;
    font-weight: bold;
}
</style>

<script type="text/javascript">
$(function() {
	$("#delBtn").click(function() {
		var name = $(".table").find(".name").text();
		if(!confirm("정말 탈퇴하시겠습니까? AniTop은 아직 "+name+"님을 보낼 준비가 되지 않았어요ㅠㅠ")) return false;
	});
});
</script>

</head>
<body>
<div class="container">
<c:if test="${!empty login && login.gradeNo == 9}">	
<div class="btnBox">
	<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}" class="pull-right back">
	<span class="glyphicon glyphicon-arrow-left" style="padding-right: 3px;"></span>뒤로가기</a>
</div>
</c:if>
<c:if test="${!empty login && login.gradeNo == 1}">	
<div class="btnBox">
	<a href="/main/list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}" class="pull-right back">
	<span class="glyphicon glyphicon-arrow-left" style="padding-right: 3px;"></span>뒤로가기</a>
</div>
</c:if>
<h3 style="border-bottom: 3px solid; padding: 5px;">${vo.id } 님의 회원 정보</h3>

<table class="table">
	<tbody>
		<tr>
			<th>이름</th>
			<td class="name">${vo.name }</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${vo.id }</td>
		</tr>
		<tr>
			<th>생일</th>
			<td><fmt:formatDate value="${vo.birth }" pattern="yyyy.MM.dd" /></td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${vo.gender }</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>${vo.tel }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${vo.email }</td>
		</tr>
		<!-- 관리자용 상태부분 -->
		<c:if test="${!empty login && login.gradeNo == 9}">	
		<tr>
			<th>상태
				<div class="form-group">
					<form action="statusUpdate.do" method="post">
					<input type="hidden" name="id" value="${vo.id }">
						<select name="status">
							<option value="정상" id="normal">정상</option>
							<option value="휴면" id="dormant">휴면</option>
							<option value="탈퇴" id="withdrawal">탈퇴</option>
							<option value="강퇴" id="eliminated">강퇴</option>
						</select>
				   		<button type="submit" class="btn btn-default btn-xs sign" style="text-align: center;">상태변경</button>
					</form>
				</div>
			</th>
			<td style="line-height: 5.428571;">${vo.status }</td>
		</tr>
		</c:if>
		<!-- 일반회원용 상태 부분 -->
		<c:if test="${!empty login && login.gradeNo == 1}">	
		<tr>
			<th>상태</th>
			<td>${vo.status }</td>
		</tr>
		</c:if>
		<tr>
			<th>등급</th>
			<td>${vo.gradeName }</td>
		</tr>
		<c:if test="${!empty login && login.gradeNo == 9}">	
			<tr>
				<th>등급번호</th>
				<td>${vo.gradeNo }</td>
			</tr>
			<tr>
				<th>최근접속일</th>
				<td><fmt:formatDate value="${vo.conDate }" pattern="yyyy.MM.dd / hh:mm:ss" /></td>
			</tr>
		</c:if>
		<tr>
			<th>가입일</th>
			<td><fmt:formatDate value="${vo.regDate }" pattern="yyyy.MM.dd" /></td>
		</tr>
		<tr>
			<th>사진<br>
				<button type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">사진수정</button>
			</th>
			<td><img src="${vo.photo }" alt="[${vo.id }]경로:${vo.photo }" ></td>
		</tr>
	</tbody>
</table>
<!-- 자신의 정보만 수정 가능한 버튼 -->
<c:if test="${!empty login && login.id == vo.id}">
	<a href="update.do?id=${vo.id }&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}" class="btn btn-default upBtn">수정</a>
</c:if>
	<a href="delete.do?id=${vo.id }&prePageNum=${pageObject.perPageNum}&deleteFileName=${vo.photo}" class="btn btn-default pull-right" id="delBtn">회원탈퇴</a>
</div>

<!-- 회원 사진 단독 수정 Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
		<form action="updatePhoto.do" method="post" enctype="multipart/form-data">
			<!-- 숨겨서 넘겨줘야하는 데이터 = 페이지정보,파일명 -->
			<input type="hidden" name="page" value="${pageObject.page }">
			<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
			<input type="hidden" name="deleteFileName" value="${vo.photo }">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">회원 사진 수정</h4>
			</div>
		    <div class="modal-body">
		    	<div class="form-group">
			    	<label for="id">회원 아이디</label>
					<input type="text" class="form-control" id="id" name="id" readonly value="${vo.id }">
		   		</div>
		    <div class="form-group">
			    <label for="imagefile">첨부파일 선택</label>
				<input type="file" class="form-control" id="imagefile" name="imageFile" required>
		   	</div>
		    </div>
		    <div class="modal-footer">
		    	<button class="btn btn-default pull-left">수정</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</form>
		</div>
	</div>
</div>

</body>
</html>