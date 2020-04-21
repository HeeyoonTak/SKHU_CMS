<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url var="R" value="/" />
<div id="fh5co-hero">
	<a href="#fh5co-main"
		class="smoothscroll fh5co-arrow to-animate hero-animate-4"><i
		class="ti-angle-down"></i></a>
	<!-- End fh5co-arrow -->
	<div class="container">
		<div class="col-md-8 col-md-offset-2">
			<div class="fh5co-hero-wrap">
				<div class="fh5co-hero-intro">
					<h1 class="to-animate hero-animate-1">동아리 연합회</h1>
					<h2 class="to-animate hero-animate-2">모든 동아리의 행사와 복지를 관리, 지원하는
						학생 자치기구</h2>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="fh5co-main">

	<div class="container">
		<div class="row animate-box">
			<h2 class="fh5co-uppercase-heading-sm text-right">출석체크</h2>
		</div>

		<div class="row1">
			<div class="col-md-9 col-md-push-3" id="fh5co-content">


				<div class="content-box animate-box">
					<form action="/club_union/attendanceInsert" id="insertform" method="get">
						<table class="table text-center attendance_check_table">
							<tr>
								<td>출석 날짜</td>
								<td><input type="date" class="form-control input-md"
									style="width: 200px" name="date" id="chdate"></td>
							</tr>
							<c:forEach var="adminUser" items="${adminUser}">
								<tr>
									<td>${adminUser}</td>
									<td><input type='checkbox' class='form-control input-sm'
										name='updateck'></td>
								</tr>
							</c:forEach>
						</table>
						<button id="submit" onClick="insert()" type="button"
							class="btn btn-primary col-md">저장</button>
					</form>

				</div>
			</div>

			<div class="col-md-3 col-md-pull-9" id="fh5co-sidebar">
				<ul class="attendance_check-list hor_1">
					<li><a href="${R}notice">공지사항</a></li>
					<li><a href="${R}account">회계 관리</a></li>
					<li>동아리 관리</li>
					<li><a href="${R}attendance">출석체크</a></li>
					<li>회의록</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<script>
function insert(){
	console.log("dddd");
	var date = document.getElementById('chdate').value;
	console.log("date:"+date);
	var arrNumber = new Array();
	var cnt = $("input[name='updateck']").length;
	console.log("cnt:"+cnt);
	for(var i=0; i<cnt; i++){
		
	}
	/* 
	var obj = document.getElementById('insertform');
	obj.submit(); */
}
</script>