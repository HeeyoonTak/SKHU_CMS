<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<h1 class="to-animate hero-animate-1">성공회대 동아리 관리 시스템</h1>
					<h2 class="to-animate hero-animate-2">
						SKHU CMS<br> <a target="_blank">Club Management System</a>
					</h2>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="fh5co-main">
	<div class="container">
		<div class="col-md-12 animate-box">
			<div class="row" id="fh5co-features" style="float: left">
				<table class="table table-striped " style="width: 430px">
					<tr class="text-center">
						<th colspan="3" style="text-align: center">일반 홍보 게시판</th>
					</tr>
					<c:forEach var="board_p" items="${boards_p}">
						<tr style="text-align: center">
							<td style="text-align: left"><a
								href="c_p_content?id=${board_p.id}">${board_p.title}</a></td>
						</tr>
					</c:forEach>
					<tr>
						<th colspan="2" style="text-align: right"><a href="publicity">더
								보기</a></th>
					</tr>
				</table>
			</div>
			<div class="row" id="fh5co-features" style="float: right">
				<table class="table table-striped " style="width: 430px">
					<tr class="text-center">
						<th colspan="2" style="text-align: center">모집 게시판</th>
					</tr>
					<c:forEach var="board_r" items="${boards_r}">
						<tr style="text-align: center">
							<jsp:useBean id="now" class="java.util.Date" />
							<fmt:formatDate value="${now}" pattern="yyyyMMddhhmm"
								var="nowDate" />
							<%-- 오늘날짜 --%>
							<fmt:formatDate value="${board_r.start_date}"
								pattern="yyyyMMddHHmm" var="openDate" />
							<%-- 시작날짜 --%>
							<fmt:formatDate value="${board_r.end_date}"
								pattern="yyyyMMddHHmm" var="closeDate" />
							<%-- 마감날짜 --%>
							<c:if test="${openDate < nowDate && closeDate < nowDate}">
								<td style="text-align: left; width: 70%"><a
									href="c_p_content?id=${board_r.id}">${board_r.title}</a></td>
								<td style="text-align: left">모집 마감</td>
							</c:if>
							<c:if test="${openDate < nowDate && closeDate > nowDate}">
								<td style="text-align: left"><a
									href="c_p_content?id=${board_r.id}">${board_r.title}</a></td>
								<td style="text-align: left">모집 진행중</td>
							</c:if>
							<c:if test="${openDate > nowDate && closeDate > nowDate}">
								<td style="text-align: left"><a
									href="c_p_content?id=${board_r.id}">${board_r.title}</a></td>
								<td style="text-align: left">모집 예정</td>
							</c:if>
						</tr>
					</c:forEach>
					<tr>
						<th colspan="2" style="text-align: right"><a href="recurit">더
								보기</a></th>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>