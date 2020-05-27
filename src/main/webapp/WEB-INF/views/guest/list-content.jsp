<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
									<h2 class="h3">소개글</h2>
								</div>
								<div class="col-md-6">
									<img src="${R}/getClubImage?id=${club.id}" id="clubImg" />
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
										<h2 class="h3">홍보 게시판</h2>
									</div>
									<div class="col-md-12">
										<div style="margin-left: 50px">
											<table class="table table-striped " style="width: 700px">
												<tr class="text-center">
													<th style="text-align: center">제목</th>
													<th style="text-align: center">등록일</th>
												</tr>
												<c:forEach var="board" items="${boards}">
													<tr>
														<td><a href="p_content?id=${board.id}">${board.title}</a></td>
														<!-- <td>아노미7</td>  -->
														<td style="text-align: center"><fmt:formatDate
																pattern="yyyy-MM-dd" value="${ board.date }" /></td>
													</tr>
												</c:forEach>
											</table>
											<div class="col-md-offset-7" style="margin-left: 50%;">
												<ul class="pagination">
													<li><a href="#" style="color: #90D7EA">이전</a></li>
													<li><a href="#" style="color: #90D7EA">1</a></li>
													<li><a href="#" style="color: #90D7EA">다음</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div>
							<div class="row">
								<div class="col-md-12 animate-box">
									<form action="#" method="post">
										<div class="col-md-6">
											<div class="form-group">
												<h4 style="margin-bottom: -1px">이름:</h4>
												<input placeholder="Name" id="name" type="text"
													class="form-control input-md">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<h4 style="margin-bottom: -1px">학번:</h4>
												<input placeholder="ex)201631234" id="number" type="text"
													class="form-control input-md">
											</div>
										</div>
										<div class="col-md-7">
											<div class="form-group">
												<h4 style="margin-bottom: -1px">학과:</h4>
												<select class="form-control input-md" id="department">
													<option>소프트웨어공학과</option>
													<option>정보통신공학과</option>
													<option>글로컬it학과</option>
													<option>컴퓨터공학과</option>
												</select>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<h4 style="margin-bottom: -1px">지원 동기:</h4>
												<textarea placeholder="Message" id="message"
													class="form-control input-lg" rows="3"></textarea>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<h4 style="margin-bottom: -1px">마지막으로 하고 싶은 말:</h4>
												<textarea placeholder="Message" id="message"
													class="form-control input-lg" rows="3"></textarea>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<input type="submit" class="btn btn-primary btn-lg "
													value="Send"> <input type="reset"
													class="btn btn-outline btn-lg " value="Reset">
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
