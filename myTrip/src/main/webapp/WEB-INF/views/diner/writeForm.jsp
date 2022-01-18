<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 등록 폼</title>	
<script type="text/javascript">
$(function() {
	//이벤트 처리
	$("#cancelBtn").click(function(){
// 		alert("취소 클릭");
		history.back();
	});
// 	$("#check").click(function(){
// 		alert(document.getElementsByName("subImage[]")[0].value);
// 		alert(document.getElementsByName("subImage[]")[1].value);
// 		alert(document.getElementsByName("subImage[]")[2].value);
// 		alert(document.getElementsByName("subImage[]")[3].value);
// 	});
});
//서브 이미지 인풋 추가하기
var cnt = 1;
function addSubImage() {
	var div = document.getElementById("subImageDiv");
	var newP = document.createElement('p');
	newP.innerHTML = "<input name='subImage["+cnt+"]' class='form-control' type='file' id='subImage'><input type='button' class='btn btn-default' value='삭제' onclick='remove(this)'>";
	div.appendChild(newP);
	cnt++
}
// 인풋란 삭제하기
function remove(obj) {
	document.getElementById('subImageDiv').removeChild(obj.parentNode);
	cnt--
}

</script>
</head>
<body>
	<div class="container">
	<h1>맛집 등록 폼</h1>
		<form action="write.do" method="post" enctype="multipart/form-data">
			<table class="table">
				<tr>
					<th>가게명(*)</th>
					<td><input name="name" class="form-control"></td>
				</tr>
				<tr>
					<th>지역(*)</th>
					<td>
						<select name="area" >
							<option value="1">서울특별시</option>
							<option value="2">경기도</option>
							<option value="3">강원도</option>
							<option value="4">충청북도</option>
							<option value="5">충청남도</option>
							<option value="6">전라북도</option>
							<option value="7">전라남도</option>
							<option value="8">경상북도</option>
							<option value="9">경상남도</option>
							<option value="10">제주도</option>
							<option value="11">부산특별시</option>
							<option value="12">대구광역시</option>
							<option value="13">인천광역시</option>
							<option value="14">광주광역시</option>
							<option value="15">대전광역시</option>
							<option value="16">울산광역시</option>
							<option value="17">세종특별시</option>
							<option value="99">기타</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>음식 종류(*)</th>
					<td>
						<select name="kind">
							<option value=1>한식</option>
							<option value="2">양식</option>
							<option value="3">중식</option>
							<option value="4">일식</option>
							<option value="5">향토음식</option>
							<option value="6">카페</option>
							<option value="99">기타</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>주소(*)</th>
					<td><input name="address" class="form-control"></td>
				</tr>
				<tr>
					<th>연락처(*)</th>
					<td><input name="tel" class="form-control"></td>
				</tr>
				<tr>
					<th>한줄소개(*)</th>
					<td><input name="introduce" class="form-control"></td>
				</tr>
				<tr>
					<th>메인 이미지(*)</th>
					<td><input name="mainImage" class="form-control" type="file" required="required"></td>
				</tr>
				<tr>
					<th>내용(*)</th>
					<td><textarea rows="7" name="content" class="form-control"></textarea></td>
				</tr>
				<tr>
					<th>서브 이미지</th>
					<td>
					<div id = "subImageDiv">
						<input name="subImage" class="form-control" type="file">
					</div>
					</td>
<!-- 					<td><input type="button" value="추가" onclick="addSubImage()"></td> -->
				</tr>
				
				<tr>
					<th>해시태그</th>
					<td><input name="tag" class="form-control"></td>
					<!-- **2. 여러개가 등어가도록 설정 -->
				</tr>				
				<tr>
					<th>영업시간</th>
					<td><input name="openTime" class="form-control"></td>
					<!-- **3. 시간 입력 구현 -->
				</tr>
				<tr>
					<th>휴업일</th>
					<td><input name="restDay" class="form-control"></td>
				</tr>
			</table>
<!-- 			<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">이미지 추가</button><br> -->			
			<button type="submit" class="btn btn-default">등록</button>
			<button type="reset" class="btn btn-default">새로입력</button>
			<button type="button" id="cancelBtn" class="btn btn-default">취소</button>
		</form>
<!-- 		<button id="check">확인</button> -->
	</div>
	
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	 	<div class="modal-dialog">	
	    	<!-- Modal content-->
	    	<div class="modal-content">
			    <form action="">
			    	<div class="modal-header">
			    		<button type="button" class="close" data-dismiss="modal">&times;</button>
			    		<h4 class="modal-title">이미지 추가</h4>
			      	</div>
			      	<div class="modal-body">
				      	<div id="subImageDiv">
				      		<input type="button" value="추가" onclick="addSubImage()">
				      		<input type="file" name="subImage" class="form-control">
				      		<br><br>	      		
				      	</div>
			      	</div>
			      	<div class="modal-footer">
			      		<button>확인</button>
			        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      	</div>
			    </form>
	    	</div>	
		</div>	  
	</div>
</body>
</html>