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
<title>${vo.title }</title>

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
.adminnav{
	border: 1px solid;
	background: white;
	padding: 5px;
}
</style>

<script type="text/javascript">
//     document.title = ${vo.title };
</script>

<script type="text/javascript">
$(function() {
	$("#delBtn").click(function() {
		if(!confirm("데이터가 전부 삭제됩니다. 정말 삭제하시겠습니까?")) return false;
	});
});
</script>
<!-- 타이틀css -->
<style type="text/css">
body{background: rgb(249, 249, 249);}
.topbox{
	width: 100%;
	height: 400px;
	background: rgb(40, 42, 53);
/* 	padding-top: 70px; */
}
.box1{
	width: 100%;
	height: 400px;
	padding-top: 70px;
/* 	background: #a94442; */
}
.imagebox{
	width: 290px;
    height: 370px;
    position: relative;
	background: black;
	margin-left: 70px;
	display: inline-block;
}
.titlebox{
	color: white;
	display: inline-block;
}
.titlecss{
	position: absolute;
	margin-top: -145px;
	padding-left: 10px;
	font-family: 'Jua', sans-serif;
	font-size: 38px;
}
</style>
<!-- 바디css -->
<style type="text/css">
.bodybox{
	width: 100%;
	height: auto;
	margin-top: 60px;
	min-height: 770px;
/* 	background: green; */
}
.conbox{
	width: 65%;
	height: 200px;
	display: inline-block;
	float: left;
	padding: 12px;
	border: 1px solid #ddd;
	background: white;
	border-radius: 3px;
}
.infobox{
	width: 33%;
	height: 200px;
	display: inline-block;
	float: right;
	padding: 12px;
	border: 1px solid #ddd;
	background: white;
	border-radius: 3px;
}
.bp{
	font-size: 16px;
    font-weight: bold;
    border-bottom: 1px solid gray;
}
.dbcss{
	line-height: 2;
}
.bodybox1, .bodybox2{ width: 100%; float: left;}
.vodbox{
	margin-top: 30px;
	width: 100%;
	padding: 5px;
	padding-bottom: 30px;
	background: white;
	border-radius: 3px;
	border: 1px solid #ddd;
	min-height: 300px;
	text-align: center;
}
.vodbox>h4{
	border-bottom: 1px solid #777;
	font-weight: bold;
/* 	color: #7975df; */
	margin-left: 10px;
	margin-right: 15px;
	text-align: left;
}
</style>
</head>
<body>
<div class="container">

<div class="topbox" style="background:url(${vo.fileName }) no-repeat; object-fit:cover; background-size: cover; ">
<div class="topbox-blur" style="background:#0000004d; ">
<div class="box1">
	<div class="imagebox">
		<img src="${vo.fileName }" alt="${vo.title }" style=" width:100%; object-fit:cover; height:-webkit-fill-available;">
	</div>
	<div class="titlebox">
		<span class="titlecss">${vo.title }</span>
	</div>
</div>
</div>
</div>

<div class="bodybox">
	<div class="bodybox1">
		<div class="conbox">
			<p class="bp">줄거리</p>
			<div style="background: white;overflow: overlay; height: 150px; padding: 5px 15px 5px 5px;">
				<span>${fn:replace(vo.content, replaceChar, "<br/>" )}</span>
			</div>
		</div>
		<div class="infobox">
			<div class="datebox">
				<p class="bp">작품 정보</p>
				<span class="dbcss"> - 제작사 : ${vo.company }</span><br>
				<span class="dbcss"> - 작ㅤ가 : ${vo.author }</span><br>
				<span class="dbcss"> - 개봉일 : ${vo.launchDate }</span><br>
			</div>
		</div>
	</div>
	<div class="bodybox2">
		<div class="vodbox">
		<h4>에피소드</h4>
			<c:if test="${empty login}">
				<span style="width: 100%; float: left; margin-top: 90px;">로그인 후 에피소드 감상이 가능합니다.</span>
			</c:if>
			<c:if test="${!empty login}">
				<span>
					<iframe width="100%" height="450" src="${vo.vod }" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				</span>
			</c:if>
		</div>
	</div>
	
</div>



<!-- 관리자용 영역 -->
<c:if test="${!empty login && login.gradeNo == 9}">
<div class="adminnav">
	<table class="table">
		<tbody>
			<tr>
				<th>No.</th>
				<td>${vo.no }</td>
			</tr>
			<tr>
				<th>사진</th>
				<td>
					<button type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">사진수정</button>
				</td>
			</tr>
			<tr>
				<th>작성자(ID)</th>
				<td>${vo.name }(${vo.id })</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td><fmt:formatDate value="${vo.writeDate }" pattern="yyyy.MM.dd" /></td>
			</tr>
		</tbody>
	</table>
	
<a href="update.do?no=${vo.no }&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${param.key}&word=${param.word}" class="btn btn-default upBtn">수정</a>
<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key }&word=${pageObject.word }" class="btn btn-default">리스트</a>
<a href="delete.do?no=${vo.no }&prePageNum=${pageObject.perPageNum}&deleteFileName=${vo.fileName}&key=${param.key}&word=${param.word}" class="btn btn-default pull-right" id="delBtn">삭제</a>

</div>
</c:if>
</div>
<!-- 이미지 단독 수정 Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
		<form action="updateImage.do" method="post" enctype="multipart/form-data">
			<!-- 숨겨서 넘겨줘야하는 데이터 = 페이지정보,파일명 -->
			<input type="hidden" name="page" value="${pageObject.page }">
			<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
			<input type="hidden" name="key" value="${pageObject.key }">
			<input type="hidden" name="word" value="${pageObject.word }">
			<input type="hidden" name="deleteFileName" value="${vo.fileName }">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">썸네일 수정</h4>
			</div>
		    <div class="modal-body">
		    	<div class="form-group">
			    	<label for="no">컨텐츠 번호</label>
					<input type="text" class="form-control" id="no" name="no" readonly value="${vo.no }">
		   		</div>
		    <div class="form-group">
			    <label for="imagefile">첨부파일 선택</label>
				<input type="file" class="form-control" id="imagefile" name="imageFile" required>
		   	</div>
		    </div>
		    <div class="modal-footer">
		    	<button class="btn btn-default pull-left">업로드</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</form>
		</div>
	</div>
</div>
</body>
</html>