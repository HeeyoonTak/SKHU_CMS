<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
					<h1 class="to-animate hero-animate-1">${club.club_name}</h1>
					<h2 class="to-animate hero-animate-2">${club.content}</h2>
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
						<div style="margin-bottom: 20px">
							<h3>공지사항</h3>
						</div>
						<div>
							<div style="margin-left: 50px">
								<table class="table table-striped " style="width: 650px">
									<tr class="text-center">
										<th style="text-align: center">제목</th>
										<th style="text-align: center">등록일</th>
										<sec:authorize access="hasRole('ROLE_ClubAdmin')">
											<th></th>
										</sec:authorize>
									</tr>
									<c:forEach var="board" items="${boards}">
										<tr>
											<td><a
												href="n_content?club_id=${board.club_id}&id=${board.id}">${board.title}</a></td>
											<td style="text-align: center; width: 200px;"><fmt:formatDate
													pattern="yyyy-MM-dd" value="${ board.date }" /></td>
											<sec:authorize access="hasRole('ROLE_ClubAdmin')">
												<td style="width: 50px"><a
													href="n_delete?id=${board.id}" style="color: #ff0000">x</a></td>
											</sec:authorize>
										</tr>
									</c:forEach>
								</table>
								<div style="margin-left: 35%;">
									<ul class="pagination">
										<li><a href="#" style="color: #90D7EA">이전</a></li>
										<li><a href="#" style="color: #90D7EA">1</a></li>
										<li><a href="#" style="color: #90D7EA">다음</a></li>
									</ul>
								</div>
								<div class="row">
									<div class="col-md-12">
										<sec:authorize access="hasRole('ROLE_ClubAdmin')">
											<a href="n_create?club_id=${club_id}"
												class="btn btn-primary btn-lg" id="l_search_term_btn"
												style="float: right;">작성</a>
										</sec:authorize>
									</div>
								</div>
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
						<li><a href="${R}club_admin/apply_q_list">모집 질문 작성</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>


</div>
<!-- END fhtco-main -->