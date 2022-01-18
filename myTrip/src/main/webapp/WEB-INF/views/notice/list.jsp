<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>

<script type="text/javascript">
$(function(){
	// tr:태그 , .tr:클래스, #tr:아이디
	$(".dataRow").click(function(){
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no + "&inc=1"
		  + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}";
	});  
	
	
});
</script>
</head>
<body>
<div class="container">
<h1>공지사항 리스트</h1>
<form action="list.do" method="post" id="multidelete">
		<!-- 페이지 정보를 다시 그대로 보내준다 -->
		<input name="page" type="hidden" value="1">
		<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
		<div class="input-group">
		
	</div>
</form>

<table class="table">
 <tr>
	<th class="w10">번호</th>
	<th class="expand">제목</th>
	<th class="w100">작성일</th>
	<th class="w30">조회수</th>
	<c:if test="${login.gradeNo == 9 }">
	<th class="w30">공개여부</th>
	</c:if>
</tr>
<c:if test="${empty list }">
	<tr>
		<td colspan="5">데이터가 존재하지 않습니다.</td>
	</tr>
</c:if>
<c:if test="${!empty list }">
	<c:forEach items="${requestScope.list }" var="vo">
		<tr class="dataRow">
			<c:if test="${login.gradeNo == 9 }">
                <td class="no" ><c:out value="${vo.no}"/></td>            
  	 		     <td><c:out value="${vo.title}"/></td>            
   			     <td><c:out value="${vo.writeDate}"/></td>            
      			 <td><c:out value="${vo.hit}"/></td>
      			 <td><c:out value="${vo.open}"/></td>
      			 
        </c:if>
        </tr>
        </c:forEach>
     </c:if>
<c:if test="${!empty list }">
 <c:forEach items="${requestScope.list }" var="vo">
		<tr class="dataRow">
        <c:if test='${vo.open == "Y" && login.gradeNo != 9}'>
        
         <td class="no" ><c:out value="${vo.no}"/></td>            
  	 	 <td><c:out value="${vo.title}"/></td>            
   		 <td><c:out value="${vo.writeDate}"/></td> 
   		 <td><c:out value="${vo.hit}"/></td>
          
        </c:if>
    </tr>
		
	</c:forEach>
</c:if>

</table>

<div>
<pageObject:pageNav listURI="list.do" pageObject="${pageObject }"></pageObject:pageNav>
</div>
<c:if test="${login.gradeNo == 9 }">
<a href="writeForm.do" class="btn btn-default">글쓰기</a>
</c:if>
</div>
</body>
</html>