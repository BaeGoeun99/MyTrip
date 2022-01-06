<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageObject" required="true" type="com.webjjang.util.PageObject" %>
<%@ attribute name="listURI" required="true" type="java.lang.String" %>
<%@ attribute name="query" required="false" type="java.lang.String" %>
<% request.setAttribute("noLinkColor", "#999"); %>
<% request.setAttribute("tooltip", " data-toggle=\"tooltip\" data-placement=\"top\" "); %>
<% request.setAttribute("noMove", " title=\"no move page!\" "); %>

<% /** PageNation을 위한 사용자 JSP 태그  *
	 * 작성자 배고은
	 * 작성일 2021.10.19
	 * 사용방법 :<pageObject:pageNav listURI="호출 List URL" pageObject= "페이지 객체" query="댓글 페이지, 검색등 뒤에 붙이는 쿼리" />
*/ %>

<ul class="pagination">
	<!-- 맨 앞으로 버튼 -->
  	<li data-page=1>
		<c:if test="${pageObject.page > 1 }">
	  		<a href="${listURI }?page=1&perPageNum=${pageObject.perPageNum}${query}" style="border: unset; border-radius: 20px;">
	  			<i class="glyphicon glyphicon-hand-left"></i>
	  		</a>
  		</c:if>
		<c:if test="${pageObject.page == 1 }">
	  		<a href="" onclick="return false" ${noMove } style="border: unset; border-radius: 20px;">
	  			<i class="glyphicon glyphicon-hand-left" style="color: ${noLinkColor};"></i>
	  		</a>
	  	</c:if>
	</li>
	
	<!-- 한 칸 앞으로 버튼 -->
<%-- 	<li data-page=${pageObject.startPage -1 }> --%>
<%-- 		<c:if test="${pageObject.startPage > 1 }"> --%>
<%-- 	  		<a href="${listURI }?page=${pageObject.startPage - 1 }&perPageNum=${pageObject.perPageNum}${query}"> --%>
<!-- 	  			<i class="glyphicon glyphicon-step-backward"></i> -->
<!-- 	  		</a> -->
<%-- 	  	</c:if> --%>
<%-- 		<c:if test="${pageObject.startPage == 1 }"> --%>
<%-- 	  		<a href="" onclick="return false" ${noMove }> --%>
<%-- 	  			<i class="glyphicon glyphicon-step-backward" style="color: ${noLinkColor};"></i> --%>
<!-- 	  		</a> -->
<%-- 	  	</c:if> --%>
<!--   	</li> -->

  	<!-- 페이지 번호 버튼 -->
	<c:forEach begin="${pageObject.startPage }" end="${pageObject.endPage }" var="cnt">
  	<li ${(pageObject.page == cnt)?"class=\"active\"":"" } data-page=${cnt }>
  	 	<!-- 페이지와 cnt가 같으면 링크가 없음 -->
  	 	<c:if test="${pageObject.page == cnt }">
  			<a href="" onclick="return false" ${noMove } style="border: unset; border-radius: 20px;">${cnt}</a>
  	 	</c:if>
  	 	<!-- 페이지와 cnt가 같지 않으면 링크가 있음 -->
  	 	<c:if test="${pageObject.page != cnt }">
  			<a href="${listURI }?page=${cnt }&perPageNum=${pageObject.perPageNum}${query}" style="border: unset; border-radius: 20px;">${cnt}</a>
  		</c:if>
  	</li>
	</c:forEach>
	
	<!-- 한 칸 뒤로 버튼 -->
<%-- 	<c:if test="${pageObject.endPage < pageObject.totalPage }"> --%>
<%-- 	  	<li data-page=${pageObject.endPage + 1 }> --%>
<%-- 	  		<a href="${listURI }?page=${pageObject.endPage + 1 }&perPageNum=${pageObject.perPageNum}${query}"> --%>
<!-- 	  			<i class="glyphicon glyphicon-step-forward"></i> -->
<!-- 	  		</a> -->
<!-- 	  	</li> -->
<%--   	</c:if> --%>
<%-- 	<c:if test="${pageObject.endPage == pageObject.totalPage }"> --%>
<%-- 	  	<li data-page=${pageObject.endPage + 1 }> --%>
<%-- 	  		<a href="" onclick="return false" ${noMove }> --%>
<%-- 	  			<i class="glyphicon glyphicon-step-forward" style="color: ${noLinkColor};"></i> --%>
<!-- 	  		</a> -->
<!-- 	  	</li> -->
<%--   	</c:if> --%>

  	<!-- 맨 뒤로 버튼 -->
	<c:if test="${pageObject.page < pageObject.totalPage }">
	  	<li data-page=${pageObject.totalPage }>
	  		<a href="${listURI }?page=${pageObject.totalPage }&perPageNum=${pageObject.perPageNum}${query}" style="border: unset; border-radius: 20px;">
		  		<i class="glyphicon glyphicon-hand-right"></i>
	  		</a>
	  	</li>
  	</c:if>
  	
	<c:if test="${pageObject.page == pageObject.totalPage }">
	  	<li data-page=${pageObject.totalPage }>
	  		<a href="" onclick="return false" ${noMove } style="border: unset; border-radius: 20px;">
		  		<i class="glyphicon glyphicon-hand-right" style="color: ${noLinkColor};"></i>
	  		</a>
	  	</li>
  	</c:if>
</ul> 

<script>
$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
  $(".pagination").mouseover(function(){
//   		$(".tooltip > *:last").css({"background-color": "red", "border": "1px dotted #444"});   
	});
});
</script>
