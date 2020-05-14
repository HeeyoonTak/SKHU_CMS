<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
					<h1 class="to-animate hero-animate-1">${club.name}</h1>
					<h2 class="to-animate hero-animate-2">동아리 연합회의 세부사항을 확인해 보세요!</h2>
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
						<div class="col-xs-12" style="margin-bottom: 0px">
							<h2 class="h3" style="margin-bottom: 0px">동아리 관리</h2>
							<ul class="pagination" style="margin-bottom: 0px">
								<li><a href="" class="btn btn-primary btn-lg"
									id="l_search_term_btn">지원 질문 작성</a></li>
							</ul>
						</div>
						<div class="col-xs-12" style="margin-bottom: 0px">
							<div class="panel panel-info">
								<div class="panel-heading">
									<h3 class="panel-title">지원 질문 관리</h3>
								</div>
								<div class="panel-body">
									<c:forEach var="q" items="${applyQ}">
										<p style="margin-bottom: 14px;">${q.content}</p>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-md-pull-9" id="fh5co-sidebar">
					<ul class="attendance_check-list hor_1">
						<li><a href="${R}club_admin/notice?club_id=${club_id}">공지사항</a></li>
						<li><a href="${R}club_admin/account?club_id=${club_id}">회계
								관리</a></li>
						<li><a href="${R}club_admin/minutes?club_id=${club_id}">회의록</a></li>
						<li><a href="${R}club_admin/publicity?club_id=${club_id}">홍보게시판</a></li>
						<li><a href="${R}club_admin/recruit?club_id=${club_id}">모집게시판</a></li>
						<li><a href="${R}club_admin/attendance?club_id=${club_id}">출석체크</a></li>
						<li><a href="${R}club_admin/acceptance?club_id=${club_id}">회원
								관리</a></li>
						<sec:authorize access="hasRole('ROLE_ClubAdmin')">
							<li><a href="${R}club_admin/apply_q_list">지원 폼</a></li>
							<li><a href="${R}club_admin/apply_q_make">지원
									폼 만들기</a></li>
						</sec:authorize>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
