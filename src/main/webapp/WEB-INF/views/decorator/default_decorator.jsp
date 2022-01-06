<!-- sitemesh 사용을 위한 설정 파일 -->
<!-- 작성자 : 배고은 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% // project의 path를 "/"로 설정하지만, 그렇지 않을때 사용
	request.setAttribute("path", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ANITOP:<decorator:title /></title>

<!-- google font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Rampart+One&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<!-- BootStrap 라이브러리 등록 전체적으로 진행을 했다. filter가 적용이 되면 개별적으로 한것은 다 지워야 한다. -->
<meta name="viewport" content="width=device-width, initial-scale=1">
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
header, footer {
	background: AntiqueWhite;
}

pre {
	background: white;
	border: 0px;
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: black;
	padding: 25px;
	color: #ddd;
	height: 50px;
	text-align: center;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
	/* 	#log_image{ */
	/*  		display: none;  */
	/* 	} */
}

article {
	min-height: 600px;
	margin-top: 50px; /* 메뉴부분만큼의 마진 적용 - 데이터가 메뉴에 가리는 것은 해결 */
	margin-bottom: 60px;
	/* copyRight 부분의 마진 적용 - 데이터가 copyRight에 가리는 것은 해결 */
}

#welcome {
	color: grey;
	margin: 0 auto;
}
.navbar-brand, .navbar-nav>a{
	color: white;
}
</style>

<c:if test="${!empty msg}">
<script type="text/javascript">
$(document).ready(function() {
	
	var isMsg = true;
		if(isMsg){
			alert("${msg}");
			isMsg = false;
		}
});
</script>
</c:if>

<decorator:head />
</head>
<body>
	<header>
		<!-- <div id="log_image"><img src="/upload/image/dog01.jpg"/></div> -->
		<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #706ea8; border: unset;">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${path }/main/list.do" style="color: white; font-size:22px; font-family: 'Rampart One', cursive;">ANITOP</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li><a href="${path }/notice/list.do" style="color: white;">공지사항</a></li>
							<!-- 로그인이 되어 있는 경우의 메뉴 -->
							<c:if test="${!empty login && login.gradeNo == 9}">
								<li>
									<a href="${path }/member/list.do" style="color: white;">
										회원관리
									</a>
								</li>
								<li>
									<a href="${path }/main/write.do" style="color: white;">작품등록</a>
								</li>
							</c:if>
					</ul>
					<!-- 메인 메뉴 부분의 로그인 사용자 정보 -->
					<ul class="nav navbar-nav navbar-right">
						<c:if test="${empty login }">
							<!-- 로그인이 안되어 있는 경우의 메뉴 -->
							<li>
								<a href="${path }/member/write.do" style="color: white;">
<!-- 								<span class="glyphicon glyphicon-user"></span> -->
								회원가입
								</a>
							</li>
							<li>
								<a href="${path }/member/login.do" style="color: white;">
<!-- 								<span class="glyphicon glyphicon-log-in"></span> -->
								로그인</a>
							</li>
						</c:if>
						<c:if test="${!empty login }">
							<!-- 로그인이 되어 있는 경우의 메뉴 -->
							<li>
								<a href="/member/view.do?id=${login.id }" style="color: white;">
<!-- 								<span class="glyphicon glyphicon-user"></span> -->
									마이페이지(<small>${login.name }</small>)
								</a>
							</li>
							<li>
								<a href="${path }/member/logout.do" style="color: white;">
<!-- 								<span class="glyphicon glyphicon-log-out"></span> -->
								로그아웃</a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<article>
		<decorator:body />
	</article>
	<footer class="container-fluid text-center navbar navbar-inverse" style="margin-bottom: unset; border: unset; border-radius: unset; background-color: #706ea8">
		<p style="margin: -6px 0 10px;">CopyrightⓒBAEGOEUN corp. All rights Reserved.</p>
	</footer>
</body>
</html>