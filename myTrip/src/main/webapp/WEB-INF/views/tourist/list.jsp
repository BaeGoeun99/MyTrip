<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<%
// java / jsp
// 데이터를 가져오는 것은 BoardController에서 해결했다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관광지 게시판 리스트</title>

  <!-- bootstrap 라이브러리 CDN 방식 등록 - default_decorator.jsp에서 작성 -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

<!-- css - 꾸미기 -->
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>

<script type="text/javascript">
// jquery ( == $)  - HTML의 로딩이 끝나면 동작
$(function(){
	// 데이터 한 줄을 클릭하면 글번호, inc와 함께 글보기로 이동한다.
	$(".dataRow").click(function(){
		//alert("글보기 이동 클릭");
		var no = $(this).find(".no").text();
		// var no1 = $(this).data("no"); // data-no의 설정 데이터 가져오기
		// alert(no + "/" + no1);
		// js : 페이지 이동 location.href = location
		location = "view.do?no=" + no
				+ "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
				+ "&key=${pageObject.key}&word=${pageObject.word}";
	});
});
</script>

</head>
<body>
<!-- html tag 안에 클래스를 이용한 디자인 : BootStrap -->
<div class="container">
<h1>관광지 게시판 리스트</h1>

<!-- 게시판 검색 -->
<div>
	<form action="list.do">
		<!-- 페이지 정보를 다시 그대로 보내준다. -->
		<input name="page" type="hidden" value="1">
		<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
		<div class="input-group">
			<span class="input-group-addon">
				<!-- 검색 종류 선택 (pulldown 메뉴 - select) : key -->
				<select name="key">
					<option value="t" ${(pageObject.key == "t")?"selected":"" }>제목</option>
					<option value="c" ${(pageObject.key == "c")?"selected":"" }>내용</option>
					<option value="i" ${(pageObject.key == "i")?"selected":"" }>ID</option>
					<option value="tc" ${(pageObject.key == "tc")?"selected":"" }>제목/내용</option>
					<option value="ti" ${(pageObject.key == "ti")?"selected":"" }>제목/ID</option>
					<option value="ci" ${(pageObject.key == "ci")?"selected":"" }>내용/ID</option>
					<option value="tci" ${(pageObject.key == "tci")?"selected":"" }>모두</option>
				</select>
			</span>
			<input id="word" type="text" class="form-control" name="word" placeholder="검색 문장"
			value="${param.word }">
		    <div class="input-group-btn">
		      <button class="btn btn-default" type="submit">
		        <i class="glyphicon glyphicon-search"></i>
		      </button>
		    </div>
		</div>
		
	</form>
</div>

<!-- BS : class="row" -> 한줄을 의미한다. -->
 <div class="row">
  <!-- 관광지 데이터가 있는 만큼 반복 처리 -->
  <c:forEach items="${list }" var="vo" varStatus="vs">
  <c:if test="${vs.index != 0 && vs.index % 4 == 0 }">
  	<!-- varStatus : index(0부터 실행 차수)와 count(1부터 실행 차수)가 있다. -->
  	<!-- 관광지 인덱스가 0보다 크면서 4배의 배수이면 줄바꿈 코드를 추가한다. -->
  	${"</div><div class='row'>"}
  </c:if>
  <!-- 하나의 관광지 표시
  		BS grid : 한 줄을 전체 12로 나눈다. class="col-md-3" : col-해상도-칸 : 칸을 더해서 12를 넘기지 못한다. -->
  <div class="col-md-3">
  	<!-- BS image : class="thumbnail" | img-rounded | img-circle
  		div class를 관광지로 지정해서 관광지와 글자를 셋트로 디자인시킨다. 관광지 태그에 적용할 수도 있다. -->
    <div class="thumbnail dataRow" data-no = "${vo.no }">
        <img src="${vo.touristImage }" alt="${vo.name }" style="width:100%">
        <div class="caption">
          <p>[<span class="no">${vo.no }</span>] ${vo.name }</p>
          <div>${vo.name }</div>
        </div>
    </div>
  </div>
  </c:forEach>
</div>

<!-- 페이지네이션 -->
<div>
<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" 
 query="&key=${pageObject.key }&word=${pageObject.word }" />
</div>

<!-- html tag 안에 클래스를 이용한 디자인 : BootStrap -->
<a href="writeForm.do" class="btn btn-default">글쓰기</a>
</div>
</body>
</html>