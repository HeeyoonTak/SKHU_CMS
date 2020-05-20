<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
				<div class="row">
					<div class="col-md-12" style="margin-bottom: 20px">
						<h2 class="h3">홍보 게시판</h2>
					</div>
					<div class="col-md-12">
						<div style="margin-left: 50px">
							<table class="table table-striped ">
								<tr style="text-align: center">
									<th style="text-align: center;">${board.title}</th>
								</tr>
								<tr>
									<td style="height: 300px">${board.content}</td>
								</tr>
								<tr style="text-align: center">
									<td><fmt:formatDate pattern="yyyy-MM-dd"
											value="${ board.date }" /></td>
								</tr>
								<c:if
									test="${board.start_date != null && board.end_date != null}">
									<tr style="text-align: center">
										<td>기간 : <fmt:formatDate pattern="yyyy/MM/dd"
												value="${ board.start_date }" /> ~ <fmt:formatDate
												pattern="yyyy/MM/dd" value="${ board.end_date }" /></td>
									</tr>
								</c:if>
							</table>
							<div class="row">
								<div class="col-md-12">
									<form:form method="post" modelAttribute="board">
										<form:hidden path="id" class="form-control input-md" />
										<form:hidden path="club_id" class="form-control input-md" />
									</form:form>
									<a class="btn btn-primary btn-lg" id="l_search_term_btn"
										style="float: right;"
										href="publicity?club_id=${board.club_id}">목록</a>
									<sec:authorize access="hasRole('ROLE_ClubAdmin')">
										<a href="p_edit?id=${board.id}" class="btn btn-primary btn-lg"
											id="l_search_term_btn" style="float: right;">편집</a>
									</sec:authorize>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>