<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="R" value="/" />
<script src="https://cdn.jsdelivr.net/npm/vue"></script>  
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.min.js"></script>

<div id="fh5co-hero">
	<a href="#fh5co-main"
		class="smoothscroll fh5co-arrow to-animate hero-animate-4"><i
		class="ti-angle-down"></i></a>
	<!-- End fh5co-arrow -->
	<div class="container">
		<div class="col-md-8 col-md-offset-2">
			<div class="fh5co-hero-wrap">
				<div class="fh5co-hero-intro">
					<h1 class="to-animate hero-animate-1">멋쟁이 사자처럼</h1>
					<h2 class="to-animate hero-animate-2">웹프로그래밍을 기반으로 한 개발 동아리</h2>
				</div>
			</div>
		</div>
	</div>
</div>
		<div id="fh5co-main" id="app">
			<div class="container">
				<div class="row">
					<div class="content-box animate-box">
						<div class="col-md-9 col-md-push-3" id="fh5co-content">
							<div class="row">
								<div style="margin-bottom: 20px">
									<h3>회원 관리</h3>
								</div>
									<div class="col-md-5">
										<table class="table table-striped ">
											
											<tr class="text-center">
												<th style="text-align: center">지원자</th>
											</tr>
												<c:forEach var="user" items="${acceptanceNo}">
													<tr>
														<td>
														<input type="hidden" name="user_id" value="${userClub.user_id}"/>
														<input type="hidden" name="club_id" value="${userClub.club_id}"/>
														<label><input type="checkbox" style="margin-right: 10px; width: 18px; height: 18px;" name="cmd" value="yes">${user.name}</label>
														<i id="app" class="btn btn-primary el-icon-document" style="margin-left: 78.5px" data-toggle="modal" data-target="#formModal"></i>
														</td>
													</tr>
												</c:forEach>
										</table>
									</div>
									<div class="col-md-2">
										<form method="post">
											<input type="hidden" name="user_id" value="${userClub.user_id}"/>
											<input type="hidden" name="club_id" value="${userClub.club_id}"/>
											<button class="btn btn-lg btn-primary" type="submit"
													name="cmd" value="yes">&gt;</button>
											<button class="btn btn-lg btn-primary" type="submit"
													name="cmd" value="no">&lt;</button>
										</form>
									</div>
									<div class="col-md-5">
										<table class="table table-striped ">
											
											<tr class="text-center">
												<th style="text-align: center">회원</th>
											</tr>
												<c:forEach var="user" items="${acceptanceYes}">
													<tr>
														<td><label><input type="checkbox" style="margin-right: 10px; width: 18px; height: 18px;" value="">${user.name}</label></td>
													</tr>
												</c:forEach>
										</table>
									</div>
								    
							</div>
						</div>
					</div>
					<div class="col-md-3 col-md-pull-9" id="fh5co-sidebar">
						<ul class="attendance_check-list hor_1">
                           <li><a href="${R}club_admin/notice?club_id=${club_id}">공지사항</a></li>
                           <li><a href="${R}club_admin/account?club_id=${club_id}">회계 관리</a></li>
                           <li><a href="${R}club_admin/minutes?club_id=${club_id}">회의록</a></li>
                           <li><a href="${R}club_admin/publicity?club_id=${club_id}">홍보게시판</a></li>
                           <li><a href="${R}club_admin/recruit?club_id=${club_id}">모집게시판</a></li>
                           <li><a href="${R}club_admin/attendance?club_id=${club_id}">출석체크</a></li>
                           <li><a href="${R}club_admin/acceptance?club_id=${club_id}">회원 관리</a></li>
                           <li><a href="${R}club_admin/apply_q_form?club_id=${club_id}">지원 폼</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>


<!-- 모달 띄우기 -->		
<div class="modal fade" id="formModal" role="dialog" tabindex="-1">
	<div class="modal-dialog modal-md">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 id="modal-title" class="modal-title">동아리 지원서</h4>
			</div>
			<div class="modal-body" style="overflow:scroll">
			<table class="table table-striped ">
				<thead>
					<tr>
					  <th>질문</th>
					  <th>답변</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="apply_a" items="${answerList}">
					<tr>
						<td style="margin-right: 10px; width: 18px; height: 18px;">${apply_q.content}</td>
						<td style="margin-right: 10px; width: 18px; height: 18px;">${apply_a.content}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<div class="modal-footer">
				<button id="closeModal" type="button" class="btn btn-primary col-md"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>