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
									<form method="post" enctype="multipart/form-data"
										onsubmit="return check(1);" name="apply_submit"
										id="apply_submit">
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
														<!-- 삭제 버튼 -->
														<td style="text-align: center;"><c:if
																test="${q.count eq 0}">
																<a href="${R}club_admin/applyQ_delete?id=${q.id}"
																	style="border-bottom: 0px; color: red; text-decoration: none;"
																	onclick="return deleteAlert();">삭제 </a>
															</c:if></td>
														<!-- 수정  -->
														<td style="text-align: center;">
															<!-- 모달을 열기 위한 버튼 -->
															<button type="button"
																style="border: none; background: transparent; color: blue;"
																data-toggle="modal" data-target="#Q_Modal_${q.id}">수정</button>
															<!-- 모달 영역 -->
															<div class="modal fade" id="Q_Modal_${q.id}"
																tabindex="-1" role="dialog"
																aria-labelledby="myModalLabel">
																<div class="modal-dialog" role="document">
																	<div class="modal-content">
																		<div class="modal-header">
																			<button type="button" class="close"
																				data-dismiss="modal" aria-label="Close">
																				<span aria-hidden="true">×</span>
																			</button>
																			<h4 class="modal-title" id="myModalLabel">질문 수정</h4>
																		</div>
																		<form method="post" enctype="multipart/form-data"
																			onsubmit="return check(1);" name="apply_edit_submit"
																			id="apply_edit_submit">
																			<input type="hidden" name="id" value="${q.id}">
																			<div class="modal-body">
																				<input type="text" name="edited_question"
																					style="margin: auto" class="form-control input"
																					style="margin-bottom: 3px; width: 100%"
																					placeholder="${q.content}" />
																			</div>
																			<div class="modal-footer">
																				<button type="submit" class="btn btn-primary"
																					formaction="apply_q_edit">수정 완료</button>
																				<button type="button" class="btn btn-default"
																					data-dismiss="modal">취소</button>
																			</div>
																		</form>
																	</div>
																</div>
															</div>
														</td>
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
									</form>
									<div>
										<div style="float: right">
											<input type="submit" class="btn btn-primary"
												id="l_applyQ_save" name="l_applyQ_save" style="margin: auto"
												value="질문 저장" formaction="apply_q_save">
										</div>
										<div style="marginfloat: left">
											<p style="padding-top: 10px; margin-bottom: 0px;">이미 지원이
												시작된 질문은 삭제하실 수 없습니다.</p>
											<p style="padding-top: 10px; margin-bottom: 0px;">(삭제 버튼이
												없는 질문은 이미 모집이 시작된(답변이 달린) 질문입니다.)</p>
										</div>
									</div>
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



