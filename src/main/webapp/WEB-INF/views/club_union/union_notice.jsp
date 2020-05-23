<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<div style="margin-bottom: 20px">
							<h4 style="color: #CCCCCC; margin-left: 50px">공지 사항</h4>
						</div>
						<div>
							<div style="margin-left: 50px">
								<table class="table table-striped " style="width: 650px">
									<tr class="text-center">
										<th style="text-align: center">제목</th>
										<th style="text-align: center">등록일</th>
										<sec:authorize access="hasRole('ROLE_ClubUnion')">
											<th></th>
										</sec:authorize>
									</tr>
									<c:forEach var="board" items="${boards}">
										<tr>
											<td><a href="n_content?id=${board.id}">${board.title}</a></td>
											<td style="text-align: center; width: 200px;"><fmt:formatDate
													pattern="yyyy-MM-dd" value="${ board.date }" /></td>
											<sec:authorize access="hasRole('ROLE_ClubUnion')">
												<td style="width: 50px"><a href="n_delete?id=${board.id}"
													style="color: #ff0000">x</a></td>
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
										<sec:authorize access="hasRole('ROLE_ClubUnion')">
											<a href="n_create" class="btn btn-primary btn-lg"
												id="l_search_term_btn" style="float: right;">작성</a>
										</sec:authorize>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-md-pull-9" id="fh5co-sidebar">
           <c:import url="../tiles/tiles_union_sidebar.jsp" />
			</div>
		</div>
	</div>
</div>