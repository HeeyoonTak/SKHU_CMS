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
				<div class="row">
					<form action="#" method="get">
						<div class="col-md-9"></div>
						<div class="col-md-3">
							<div class="form-group">
								<form:select class="form-control input-lg autosubmit"
									path="sems" items="${ sems }" name="semId" id="selectSemId" />
							</div>
						</div>
					</form>
				</div>
				<div class="content-box animate-box">
					<table class="table text-center attendance_check_table">
						<thead>
							<tr>
								<th></th>
								<c:forEach var="adminUser" items="${adminUser}">
									<th>${adminUser}</th>
								</c:forEach>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="findDate" items="${ findDate }">
								<tr>
									<td id="attendanceUpdate" data-toggle="modal"
										data-target="#attendanceModal">${ findDate }</a></td>
									<c:forEach var="attendance" items="${ attendance }"
										varStatus="status">
										<c:if test="${attendance.date eq findDate}">
											<c:choose>
												<c:when test="${ attendance.check eq 0 }">
													<td>-</td>
												</c:when>
												<c:otherwise>
													<td>O</td>
												</c:otherwise>
											</c:choose>
											<c:if test="${status.count % fn:length(adminUser) eq 0}">
												<td><a href="attendance_delete?date=${attendance.date}">x</a></td>
								</tr>
						</tbody>
						</c:if>
						</c:if>
						</c:forEach>
						</c:forEach>
						<tbody>
							<tr>
								<!--출석체크 삽입 모달-->
								<td colspan="${fn:length(adminUser) + 2}">
									<button id="createBtn" type="button"
										class="btn btn-primary col-md" data-toggle="modal"
										data-target="#attendanceModal">+</button>
							</tr>
						</tbody>
					</table>
					<jsp:include page="../club_union/attendance_modal.jsp" />
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

<script type="text/javascript">
	window.onload = function() {
		$("#selectSemId").val(${selectSemId}).prop("selected", true);
	}

</script>