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
							<h2 class="h3" style="margin-bottom: 20px">${club.club_name}
								모집</h2>
						</div>
						<div class="col-xs-12" style="margin-bottom: 0px">
							<form method="post" enctype="multipart/form-data"
								name="apply_submit" id="apply_submit">
								<input type="hidden" name="club_id" value="${club.id}">
								<div class="panel panel-info">
									<div class="panel-heading">
										<h3 class="panel-title">질문</h3>
									</div>
									<div class="panel-body">
										<table style="width: 100%;">
											<thead>

											</thead>
											<tbody>
												<c:forEach var="q" items="${applyQ}">
													<tr height="10">
														<td><p style="word-break: break-all; margin-bottom: 3px;">${q.content}</p></td>
													</tr>
													<tr height="40">
														<input type="hidden" name="Qs" value=${q.id}>
														<td><input type="text" name="answers"
															class="form-control input"
															style="margin-bottom: 20px;"
															placeholder="답변을 입력해주세요."></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<div>
											<div style="float: right;">
												<input type="submit" class="btn btn-primary"
													id="l_applyA_save" name="l_applyA_save"
													style="margin-top: 30;" value="질문 저장"
													formaction="apply_a_save">
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-md-pull-9" id="fh5co-sidebar">
					<c:import url="../tiles/tiles_club_sidebar.jsp" />
				</div>
			</div>
		</div>
	</div>
</div>