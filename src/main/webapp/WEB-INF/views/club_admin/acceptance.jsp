<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="fh5co-hero">
	<div class="container">
		<div class="col-md-8 col-md-offset-2">
			<div class="fh5co-hero-wrap" style="padding-top: 100px; padding-bottom: 100px">
				<div class="fh5co-hero-intro">
					<div class="col-md-12"
						style="background: #f3f8f9; border-radius: 15px; padding-top: 30px; padding-bottom: 30px;">
						<div class="my-3 p-3 bg-white rounded shadow-sm">
							<label style="color: black">지원자 목록</label>
							<hr class="mb-4">
							<c:forEach var="<%-- lecture --%>" items="<%-- ${acceptanceNo} --%>">
								<div class="media text-muted pt-3">
									<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
										<div class="d-flex justify-content-between align-items-center w-100">
											<strong class="text-gray-dark"><%-- ${lecture.lecture_name} --%></strong>
											<form method="post">
												<input type="hidden" name="id" value="<%-- ${ lecture.lecture_no } --%>" />
												<button type="submit" name="cmd" value="yes" class="btn btn-primary">승인</button>
											</form>
											<hr class="mb-4">
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<br></br>
					<div class="col-md-12" style="background: #f3f8f9; border-radius: 15px; padding-top: 30px; padding-bottom: 30px;">
						<div class="my-3 p-3 bg-white rounded shadow-sm">
							<label style="color: black">합격자 목록</label>
							<hr class="mb-4">
							<c:forEach var="<%-- lecture --%>" items="<%-- ${acceptanceYes} --%>">
								<div class="media text-muted pt-3">
									<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
										<div class="d-flex justify-content-between align-items-center w-100">
											<strong class="text-gray-dark"><%-- ${lecture.lecture_name} --%></strong>
											<form method="post">
                  								<input type="hidden" name="id" value="<%-- ${ lecture.lecture_no } --%>" />
                  								<button type="submit" name="cmd" value="no" class="btn btn-outline-primary">취소</button>
                  							</form>
                  							<hr class="mb-4">
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>