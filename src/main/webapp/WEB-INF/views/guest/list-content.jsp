<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ taglib tagdir="/WEB-INF/tags" prefix="my" %> --%>

<div id="fh5co-hero">
	<a href="#fh5co-main"
		class="smoothscroll fh5co-arrow to-animate hero-animate-4"><i
		class="ti-angle-down"></i></a>
	<!-- End fh5co-arrow -->
	<div class="container">
		<div class="col-md-8 col-md-offset-2">
			<div class="fh5co-hero-wrap">
				<form>
					<div class="fh5co-hero-intro">
						<h1 class="to-animate hero-animate-1">${club.club_name}</h1>
						<h2 class="to-animate hero-animate-2">${club.content}</h2>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div id="fh5co-main">
	<div class="container">
		<div class="row">
			<div class="col-md-12 animate-box">
				<div id="fh5co-tab-feature-vertical" class="fh5co-tab">
					<ul class="resp-tabs-list hor_1">
						<li><i class="fh5co-tab-menu-icon ti-comment"></i>소개글</li>
						<li><i class="fh5co-tab-menu-icon ti-announcement"></i> 홍보</li>
						<li><i class="fh5co-tab-menu-icon ti-write"></i> 모집</li>
					</ul>
					<div class="resp-tabs-container hor_1">
						<div>
							<div class="row">
								<div class="col-md-12">
									<h4 style="color: #CCCCCC;  text-align : center;">소개글</h4>
								</div>
								<div class="col-md-6">
									<img src="${R}/getClubImage?id=${club.id}" id="clubImg"
										style="width: 270px; height: 250px" />
								</div>
								<form>
									<div class="col-md-6">
										<p>${club.content}</p>
									</div>
								</form>
							</div>
						</div>
						<div>
							<div class="row">
								<div class="col-md-12 animate-box">
									<div class="col-md-12" style="margin-bottom: 20px">
										<h4 style="color: #CCCCCC;  text-align : center; margin-left: 50px;">홍보 게시판</h4>
									</div>
									<div class="col-md-12">
										<div>
											<table class="table table-striped " style="width: 620px">
												<tr class="text-center">
													<th style="text-align: center">제목</th>
													<th style="text-align: center">등록일</th>
												</tr>
												<c:forEach var="board" items="${boards_p}">
													<tr>
														<td><a href="p_content?id=${board.id}">${board.title}</a></td>
														<!-- <td>아노미7</td>  -->
														<td style="text-align: center"><fmt:formatDate
																pattern="yyyy-MM-dd" value="${ board.date }" /></td>
													</tr>
												</c:forEach>
											</table>
											<a href="publicity" style="color: #8b969c">전체보기</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div>
							<div class="row">
								<div class="col-md-12 animate-box">
									<div class="col-md-12" style="margin-bottom: 20px">
										<h4 style="color: #CCCCCC;  text-align : center; margin-left: 50px;">모집 게시판</h4>
									</div>
									<div class="col-md-12">
										<div>
											<table class="table table-striped " style="width: 620px">
												<tr class="text-center">
													<th style="text-align: center">제목</th>
													<th style="text-align: center">모집 기간</th>
													<th style="text-align: center">마감 기간</th>
												</tr>
												<c:forEach var="board" items="${boards_r}">
													<tr>
														<td><a href="r_content?id=${board.id}">${board.title}</a></td>
														<!-- <td>아노미7</td>  -->
														<td style="text-align: center"><fmt:formatDate
																pattern="yyyy-MM-dd" value="${ board.start_date }" /></td>
														<td style="text-align: center"><fmt:formatDate
																pattern="yyyy-MM-dd" value="${ board.end_date }" /></td>
													</tr>
												</c:forEach>
											</table>
											<a href="recruit" style="color: #8b969c">전체보기</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
