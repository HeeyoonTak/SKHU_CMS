<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />

<!-- START #fh5co-header -->
<header id="fh5co-header-section" role="header" class="">
	<div class="container">



		<!-- <div id="fh5co-menu-logo"> -->
		<!-- START #fh5co-logo -->
		<h1 id="fh5co-logo" class="pull-left">
			<a href="${R}"><i class="el-icon-s-opportunity">Club System</i></a>
		</h1>

		<!-- START #fh5co-menu-wrap -->
		<nav id="fh5co-menu-wrap" role="navigation">

			<ul class="sf-menu" id="fh5co-primary-menu">
				<li><sec:authorize access="authenticated">
						<a href="${R}club_union/notice">동아리 연합회</a>
					</sec:authorize></li>
				<li><a href="${R}" class="fh5co-sub-ddown">동아리</a>
					<ul class="fh5co-sub-menu">
						<c:forEach var="club" items="${clubs}">
							<li><a href="${R}list-content?id=${club.id}">${club.club_name}</a></li>
						</c:forEach>
					</ul></li>
				<li><a href="${R}publicity">홍보</a></li>
				<li><a href="${R}recruit">모집</a></li>
				<sec:authorize access="hasAnyRole('ROLE_ClubAdmin','ROLE_Member')">
					<li><a href="${R}" class="fh5co-sub-ddown">내 동아리</a>
						<ul class="fh5co-sub-menu">
							<c:forEach var="club" items="${user_clubs}">
								<li><a href="${R}club_admin/notice?club_id=${club.id}">${club.club_name}</a></li>
							</c:forEach>
						</ul></li>
				</sec:authorize>

				<li><sec:authorize access="authenticated">
						<a href="${R}myPage"><i class="el-icon-user-solid">마이페이지</i></a>
					</sec:authorize></li>

				<li class="fh5co-special" style="padding-left: 20px"><sec:authorize
						access="not authenticated">
						<a class=" btn btn-default" href="${R}login">로그인</a>
					</sec:authorize> <sec:authorize access="authenticated">
						<a class="btn btn-default" href="${R}logout_processing">로그아웃</a>
					</sec:authorize></li>

			</ul>
		</nav>
		<!-- </div> -->

	</div>
</header>