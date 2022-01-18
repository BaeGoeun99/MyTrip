<!-- sitemesh 사용을 위한 설정 파일 -->
<!-- 작성자 : 이영환 -->
<!-- 작성일 : 2020-06-30 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyTrip:<decorator:title /></title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- google font -->
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Icon : Awesome 5 Icons 라이브러리 등록 -->
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
<!-- 파비콘 -->
<link rel="shortcut icon" href="/upload/favicon.ico" type="image/x-icon" />
<link rel="icon" href="/upload/favicon.ico" type="image/x-icon" />
	
<style type="text/css">
/* 주석 : BootStrap이 먼저 적용이 된다. 태그안에 style -> id -> class -> tag -> *(모든 태그)
	BoardStap은 디자인을 class를 사용. tag 보다 우선 적용이 된다. 원하는 CSS는 바로 태그안에 적용시킨다. */
header, footer {
	background: AntiqueWhite;
}

pre {
	background: white;
	border: 0px; /* 테두리를 없앤다. */
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0; /* 아래쪽(bottom) 바깥 여백(margin) */
	border-radius: 0; /* 테두리 모서리의 둥근 정도 반지름으로 표시 */
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: black;
	padding: 15px;
	color: #ddd;
	height: 50px;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* media 쿼리 - 반응형 웹 */
/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
	#logoImageDiv{
		/* 화면에서 사라지게 만든다. 데이터가 가져오지 않는다. */
		display: none;
	}
}

article {
	min-height: 600px;
	margin-top: 60px; /* 메뉴와 내용이 겹치지 않게 하기 위한 여백 */
	margin-bottom: 60px; /* 회사소개와 내용이 겹치지 않게 하기 위한 여백 */
}

#welcome {
	color: grey;
	margin: 0 auto; /* block 속성의 태그의 가운데 정렬 */
}
.navbar-inverse {
    background-color: #ea8508;
    border-color: #f4ad0f;
}
.navbar-inverse .navbar-nav>li>a {
    color: #ffffff;    
}
</style>
<script type="text/javascript">

	// 창이 보여지면 동작을 하는 이벤트 처리
	window.onpageshow = function(event) {
// 		alert(event.persisted);
// 		alert(window.performance);
// 		alert(window.performance.navigation.type);
		// 페이지 뒤로가기나 앞으로 가기를 했을 때의 처리
		// window.performance.navigation.type : 0 - 새로운 페이지 이동, 1 - 새로고침. 2 - 뒤로 / 앞으로 
		if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
			location.reload();
			return;
		}
	}
		
	$(document).ready(function() {

		<c:if test="${!empty msg }">
			// 약간(0.5초) 기다렸다가 경고창 띄우기 : js setTimeout
			setTimeout(
				function(){
					alert("${msg}");
				},
				200 // 0.2 초 : 1000 -> 1초
			);
		</c:if>
		
// 		<c:if test="${!empty login}">
// 			// Ajax를 이용해서 새로운 메시지의 갯수를 가져오는 처리
// 			// URL은 바뀌지 않는다.(페이지 이동이 일어나지 않는다.) - 서버에서 데이터를 받아온다.
// 			// 선택한 것에 데이터를 올려 놓는다. 태그 사이의 데이터를 바꾼다.
// 			$("#newMsgCnt").load("/ajax/msgCount.do");
			
// 			// 10초 마다 한번 씩 새로운 메시지를 불러오는 처리하는 메서드
// 			setInterval(function(){ 
// 				$("#newMsgCnt").load("/ajax/msgCount.do",
// 						function(response, status, xhr){
// 							// 로그인 되지 않는 상태이면 response가 null이 된다. 페이지 새로고침시킨다.
// 							// 10초마다 한번씩 되기 때문에 바로 적용이되지 않는다.
// 							// alert(response + "/" + status);
// 							if(response == "null")
// 								location.reload();
// 				});
// 			}, 10000);
// 		</c:if>
		
		// tooltip 처리 - 안내 풍선 도움말 - tag 안에 title 속성의 텍스트가 나타난다.
		$('[data-toggle="tooltip"]').tooltip();
		
	});
	
</script>
<decorator:head/>
</head>
<body>
	<!-- 위에 메뉴나 로고가 들어 있는 태그 - header : div로 사용 가능 -->
	<header>
<!-- 		<div id="logoImageDiv"><img src="/img/logo.jpg" style="width: 100%; height: 300px"/></div> -->
		<!-- nav(navigation) : 클릭해서 페이지 이동이되는 메뉴 부분
				navbar-fixed-top : 다른 내용과 상관없이 맨 위에 항상 고정으로 보임 -->
		<nav class="navbar navbar-inverse navbar-fixed-top" >
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/" id="logoImageDiv"><img src="/upload/logo.png" style="width: 50px; height: 30px;"></a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<!-- ul(unorder list)안에 li(list)tag는 한줄이여야 하고 앞에 기호가 붙어야 하지만
						CSS로 기호를 없애고 한줄안에 같이 어우러지게 만들 수 있다.
						*** 주메뉴 시작 -->
					<ul class="nav navbar-nav">
						<li><a href="/tourist/list.do">관광지</a></li>
						<li><a href="/festa/list.do">축제</a></li>
						<li><a href="/diner/list.do">맛집</a></li>
						<li><a href="/notice/list.do">공지사항</a></li>
<!-- 						<li><a href="/member/login.do">로그인</a></li>						 -->
<!-- 						<li><a href="/member/adminLogin.do">관리자로그인</a></li>					 -->
<!-- 						<li><a href="/member/logout.do">로그아웃</a></li>					 -->
						<c:if test="${!empty login }">							
							<c:if test="${login.gradeNo == 9 }">
								<li><a href="/member/list.do">회원관리</a></li>
							</c:if>
						</c:if>
					</ul>
					<!-- 주메뉴의 끝 -->
					<!-- 오른쪽의 회원관리 메뉴 시작 -->
					<ul class="nav navbar-nav navbar-right">
						<c:if test="${empty login }">
					    	<li><a href="/member/loginForm.do"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
					    	<li><a href="/member/clouse.do"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
   								
					    </c:if>
						<c:if test="${!empty login }">
					    	<li><a href="/member/logout.do"><span class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
					    	<li>
					    		<a href="/member/myPage.do">
						    		<img alt="회원이미지" src="${login.photo }" style="width: 25px; height: 25px;">
						    		 ${login.name }(${login.gradeName })
					    		</a>
					    	</li>
					    	<li>
					    		<a href="/message/list.do">
					    			<span class="badge" style="background: red;" id="newMsgCnt">0</span>
					    		</a>
					    	</li>
					    </c:if>
				    </ul>
					<!-- 오른쪽의 회원관리 메뉴 끝 -->
					
					
				</div>
			</div>
		</nav>
	</header>
	<article>
		<decorator:body />
	</article>
	<!-- navbar-fixed-bottom : 다른 내용과 상관없이 맨 아래에 항상 고정으로 보임 -->
	<footer class="container-fluid text-center navbar navbar-inverse">
		<p>JSP & Servlet Project Team2 : 배고은 박지수 김한성 이진우 이민구</p>
	</footer>
</body>
</html>

<%
 session.removeAttribute("msg");
%>