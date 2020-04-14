<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />

<div id="fh5co-hero" style="margin-bottom: 0px; height: 700px">

	<!-- End fh5co-arrow -->
	<div class="container">
		<div class="col-md-8 col-md-offset-2">
			<div class="fh5co-hero-wrap" style="padding-top: 180px">
				<div class="fh5co-hero-intro">

					<div class="col-md-12"
						style="background: #f3f8f9; border-radius: 15px; padding-top: 100px; padding-bottom: 30px;">
						<form action="login_processing" method="post">
							<div class="col-md-1"></div>
							<div class="col-md-10">
								<div class="form-group">
									<div>
										<p style="color: #444444">종합정보시스템 계정으로 로그인 해주세요.</p>
									</div>
									<label for="inputId" class="sr-only">ID</label> <input
										type="text" name="login_id" class="form-control"
										placeholder="Id" autocomplete="off" required autofocus>
								</div>
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-10">
								<div class="form-group">
									<label for="inputPassword" class="sr-only">Password</label> <input
										type="password" name="password" class="form-control"
										autocomplete="off" placeholder="Password" required>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<c:if test="${ param.error != null }">
										<div class="mt5" style="color:red;">ID 혹은 비밀번호를
											재확인 바랍니다.</div>
									</c:if>
									<li></li>
									<button class="btn btn-lg btn-primary" type="submit">로그인</button>

								</div>
							</div>


						</form>

					</div>

				</div>
			</div>
		</div>
	</div>
</div>