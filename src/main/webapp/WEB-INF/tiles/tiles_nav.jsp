<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- START #fh5co-header -->
<header id="fh5co-header-section" role="header" class="">
	<div class="container">



		<!-- <div id="fh5co-menu-logo"> -->
		<!-- START #fh5co-logo -->
		<h1 id="fh5co-logo" class="pull-left">
			<a href="index.html"><i class="el-icon-s-opportunity">Club
					System</i></a>
		</h1>

		<!-- START #fh5co-menu-wrap -->
		<nav id="fh5co-menu-wrap" role="navigation">


			<ul class="sf-menu" id="fh5co-primary-menu">
				<li class="active"><a href="index.html">동아리 연합회</a></li>
				<li><a href="#" class="fh5co-sub-ddown">동아리</a>
					<ul class="fh5co-sub-menu">
						<li><a href="left-sidebar.html">멋쟁이 사자처럼</a></li>
						<li><a href="right-sidebar.html">개발자들</a></li>
						<li><a href="#">소울</a></li>
					</ul></li>
				<li><a href="elements.html">홍보</a></li>
				<li><a href="elements.html">모집</a></li>
				<li><a href="#" class="fh5co-sub-ddown">내 동아리</a>
					<ul class="fh5co-sub-menu">
						<li><a href="left-sidebar.html">멋쟁이 사자처럼</a></li>
						<li><a href="right-sidebar.html">개발자들</a></li>
					</ul></li>
				<li class="fh5co-special" style="padding-left: 20px">
				<sec:authorize access="not authenticated">
						<a class="btn btn-default" href="${R}/login">로그인</a>
					</sec:authorize> 
					</li>
			</ul>
		</nav>
		<!-- </div> -->

	</div>
</header>