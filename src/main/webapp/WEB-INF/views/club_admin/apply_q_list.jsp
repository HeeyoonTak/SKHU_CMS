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
						<div class="col-xs-12" style="margin-bottom: 0px">
							<h2 class="h3" style="margin-bottom: 0px">동아리 관리</h2>
							<br>
						</div>
						<div class="col-xs-12" style="margin-bottom: 0px">
							<div class="panel panel-info">
								<div class="panel-heading">
									<h3 class="panel-title">지원 질문 관리</h3>
								</div>
								<div class="panel-body">
									<form action="apply_q_save" method="post"
										enctype="multipart/form-data" onsubmit="return check(1);"
										name="apply_submit" id="apply_submit">
										<table style="width: 100%;">
											<thead>
												<tr height="40">
													<th style="text-align: center;" width=84%>질문 목록</th>
													<th style="text-align: center;" width=8%></th>
													<th style="text-align: center;" width=8%></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="q" items="${applyQ}">
													<tr height="40">
														<td><p style="word-break: break-all">${q.content}</p></td>
														<td style="text-align: center;"><a
															href="${R}club_admin/applyQ_delete?id=${q.id}"
															onclick="return deleteAlert();">삭제</a></td>
														<td style="text-align: center;">수정</td>
													</tr>
												</c:forEach>
												<tr height="40">
													<td colspan=2><input type="text" name="question"
														style="margin: auto" class="form-control input"
														style="margin-bottom: 3px; width: 100%"
														placeholder="질문을 입력해주세요." /></td>
													<td style="text-align: center;"><button
															style="margin: auto" class="btn"
															onclick="
															return delete_row(this);">x</button></td>
												</tr>
												<tr height="40">
													<td colspan=3 style="text-align: center;">
														<div style="margin: auto">
															<button onclick="return attachApplyQ(this);"
																class="btn btn-primary col-md" id="pls-btn"
																style="margin: auto">질문 추가</button>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
										<div>
											<div style="float: right">
												<input type="submit" class="btn btn-primary"
													id="l_applyQ_save" name="l_applyQ_save"
													style="margin: auto" value="질문 저장">
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-md-pull-9" id="fh5co-sidebar">
					<ul class="attendance_check-list hor_1">
						<li><a href="${R}club_admin/notice?club_id=${club.id}">공지사항</a></li>
						<li><a href="${R}club_admin/account?club_id=${club.id}">회계
								관리</a></li>
						<li><a href="${R}club_admin/minutes?club_id=${club.id}">회의록</a></li>
						<li><a href="${R}club_admin/publicity?club_id=${club.id}">홍보게시판</a></li>
						<li><a href="${R}club_admin/recruit?club_id=${club.id}">모집게시판</a></li>
						<li><a href="${R}club_admin/attendance?club_id=${club.id}">출석체크</a></li>
						<li><a href="${R}club_admin/acceptance?club_id=${club.id}">회원
								관리</a></li>
						<sec:authorize access="hasRole('ROLE_ClubAdmin')">
							<li><a href="${R}club_admin/apply_q_list">모집 질문 작성</a></li>
						</sec:authorize>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
