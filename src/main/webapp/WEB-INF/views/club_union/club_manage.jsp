<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="row">
			<div class="content-box animate-box">
				<div class="col-md-9 col-md-push-3" id="fh5co-content">
					<div class="row">
						<div class="row">
							<div class="col-xs-12" style="margin-bottom: 0px">
								<h2 class="h3" style="margin-bottom: 0px">동아리 관리</h2>
								<ul class="pagination" style="margin-bottom: 0px">
									<li><sec:authorize access="hasRole('ROLE_ClubUnion')">
											<a href="club_create" class="btn btn-primary btn-lg"
												id="l_search_term_btn">개설</a>
										</sec:authorize></li>

								</ul>
							</div>
							<div class="col-xs-12" style="margin-left: 0px">
								<table class="table table-striped " style="width: 650px">
									<tr class="text-center">
										<th colspan="3" style="text-align: center">목록</th>
									</tr>
									<c:forEach var="user" items="${users}">
										<tr style="text-align: center">
											<td style="text-align: left">${user.name}</td>
											<td><a href="club_delete?user_id=${user.id}"
												data-confirm-delete style="color: #ff0000">삭제</a>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-md-pull-9" id="fh5co-sidebar">
				<ul class="attendance_check-list hor_1">
					<li><a href="${R}club_union/notice">공지사항</a></li>
					<li><a href="${R}club_union/account">회계 관리</a></li>
					<sec:authorize access="hasRole('ROLE_ClubUnion')">
						<li><a href="${R}club_union/club_list">동아리 관리</a></li>
					</sec:authorize>
					<li><a href="${R}club_union/attendance">출석체크</a></li>
					<li><a href="${R}club_union/minutes">회의록</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>