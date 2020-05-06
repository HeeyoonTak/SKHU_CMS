<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
		<div id="fh5co-main">
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
												<th style="text-align: center">비회원</th>
											</tr>
												<c:forEach var="<%-- lecture --%>" items="<%-- ${acceptanceNo} --%>">
													<tr>
														<td><label><input type="checkbox" style="margin-right: 10px; width: 18px; height: 18px;" value="">지원자 학번/이름</label></td>
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
												<c:forEach var="club" items="${acceptanceYes}">
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
							<li><a href="">공지사항</a></li>
							<li><a href="">회계 관리</a></li>
							<li><a href="">회의록</a></li>
							<li><a href="">출석체크</a></li>
							<li><a href="">회원 관리</a></li>
							<li><a href="">지원 폼</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>