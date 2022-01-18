<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 글보기</title>

  <!-- bootstrap 라이브러리 CDN 방식 등록 - default_decorator.jsp에서 선언했다. -->

<script type="text/javascript">
// jquery() - html 문서가 로딩이 되면 함수를 호출해서 처리해 주세요~.
$(function(){
	$('[data-toggle="tooltip"]').tooltip();
	// 이벤트 처리
	// 대상 선택.이벤트(function(){})
	// 대상 선택 - $("tag"), $(".class"), $("#id")
	$("#updateBtn").click(function(){
		// alert("글 수정 버튼 클릭");
		location = "updateForm.do?no=${vo.no}&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}";
	});
	$("#deleteBtn").click(function(){
		// alert("글 삭제 버튼 클릭");
		// 한번은 물어보자. - 확인을 클릭하면 삭제하러 간다. 그렇지 않으면 아무일도 하지 않는다..
		if(confirm("정말 삭제하시겠습니까?"))
			location = "delete.do?no=${vo.no}&del=${vo.fileName}";
	});
	$("#listBtn").click(function(){
		// alert("글 수업 버튼 클릭");
		location = "list.do?page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}";
	});
});
</script>

</head>
<body>
<div class="container">
<h1>이미지 게시판 글보기</h1>
<table class="table">
<tr>
	<th>번호</th>
	<td>${vo.no }</td>
</tr>
<tr>
	<th>제목</th>
	<td>${vo.title }</td>
</tr>
<tr>
	<th>내용</th>
	<!-- bootstrap으로 디자인을 하게 되면 설정이 안되는 경우가 있다. 디자인의 우선 순위가 있어서 그렇다.
	     개별 태그에 style 속성을 설정하면 가장 먼저 적용이 된다. -->
	<td><pre style="background: none;border: none;">${vo.content }</pre></td>
</tr>
<tr>
	<th>
		이미지
		<c:if test="${!empty login && login.id == vo.id }">
			<!-- EL 객체(디자이너를 위한 처리 코드) 
				  - 문자열 비교시 JAVA equals()비교해야만 하지만 EL에서는 == 비교 지원해 준다. -->
			<!-- 로그인이 되어 있어야 하고 로그인 아이디와 작성자 아이디가 같은 때 보여진다. -->
			<div>
				<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" 
				data-target="#myModal">변경</button>
			</div>
		</c:if>
	</th>
	<td><img src="${vo.fileName }" alt="${vo.title }" class="img-thumbnail"></td>
</tr>
<tr>
	<th>작성자 이름(아이디)</th>
	<td>${vo.name }(${vo.id })</td>
</tr>
<tr>
	<th>작성일</th>
	<td>${vo.writeDate }</td>
</tr>
</table>
<!-- button tag는 form tag 안에 없으면 아무 동작도 하지 않는다. -->
<c:if test="${!empty login && vo.id == login.id }">
	<!-- 로그인이 되어 있어야 하고 로그인한 사람과 작성자가 같은 경우에만 보이게 한다. -->
	<button id="updateBtn" class="btn btn-default"  data-toggle="tooltip" 
	title="제목과 내용만 수정">수정</button>
	<button id="deleteBtn" class="btn btn-default">삭제</button>
</c:if>
<button id="listBtn" class="btn btn-default">리스트</button>
</div>

<!-- 좋아요 표시 처리 -->
<div class="text-center">
<c:if test="${empty login }">
	<!-- 로그인을 하지 않은 경우 - 좋아요와 상관이 없음. -->
	<img alt="좋아요" src="/image/좋아요_myLiked.png" data-toggle="tooltip"
	 title="로그인하셔야 사용할 수 있습니다.">
</c:if>
<c:if test="${!empty login }">
	<c:if test="${empty vo.myLiked }">
		<!-- myLiked가 비어 있으면 좋아요를 누를 수 있다. -->
		<a href="like.do?<%= request.getQueryString() %>">
			<img alt="좋아요" src="/image/좋아요.png" data-toggle="tooltip"
			 title="클릭하면 좋아요 처리가 됩니다.">
		</a>
	</c:if>
	<c:if test="${!empty vo.myLiked }">
		<!-- myLiked가 비어 있지 않으면('LIKED') 좋아요 취소를 누를 수 있다. -->
		<a href="likeCancel.do?<%= request.getQueryString() %>">
			<img alt="좋아요" src="/image/좋아요_취소.png" data-toggle="tooltip"
			 title="클릭하면 좋아요 취소가 됩니다.">
		</a>
	</c:if>
</c:if>
</div>
<div class="text-center">
전체 갯수 : ${vo.likeCnt }
</div>

<!-- Modal : 처음에는 안보이게 설정이 되어 있다. -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <form action="updatePhoto.do" method="post" enctype="multipart/form-data" >
    <!-- 숨겨서 넘어갈 데이터 : 페이지 정보와 검색 정보 -->
    <input type="hidden" name="page" value="${pageObject.page }">
    <input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
    <input type="hidden" name="key" value="${pageObject.key }">
    <input type="hidden" name="word" value="${pageObject.word }">
    <input type="hidden" name="del" value="${vo.fileName }">
    <div class="modal-content">
      <div class="modal-header">
      	<!-- html의 특수기호 : &nbsp; - 공백문자, &lt; - <, &gt - >, &times; - x
      		 BS : Modal class="close" 닫힘 디자인, data-dismiss="modal" 클릭하면 모달이 닫힌다. -->
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">이미지 바꾸기</h4>
      </div>
      <div class="modal-body">
        <p>
          <div class="form-group">
		    <label for="no">이미지 번호</label>
		    <input type="text" class="form-control" id="no" name="no" readonly value="${vo.no }">
		  </div>
          <div class="form-group">
		    <label for="imageFile">변경할 이미지</label>
		    <input type="file" class="form-control" id="imageFile" name="imageFile">
		  </div>
        </p>
      </div>
      <div class="modal-footer">
      	<button class="btn btn-default">전송</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
    </form>

  </div>
</div>

</body>
</html>