<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="fh5co-hero" style="margin-bottom: 0px; height: 700px">

	<!-- End fh5co-arrow -->
	<div class="container">
		<div class="col-md-8 col-md-offset-2">
			<div class="fh5co-hero-wrap" style="padding-top: 150px;">
				<div class="fh5co-hero-intro">
					<div class="col-md-12"
						style="background: #f3f8f9; border-radius: 15px; padding-top: 50px; padding-bottom: 30px;">
						<div class="my-3 p-3 bg-white rounded shadow-sm">
							<label style="color:black">마이페이지</label>
							<form method="post" name="user" modelAttribute="user"
								class="needs-validation" novalidate>
								
								<hr class="mb-4">
								<div class="mb-3">
									<label for="username" style="color:black">이름:<span class="text-muted"></span></label>
										<span class="user_name" style="color:black">${user.name}</span>
								</div>
								
								<hr class="mb-4">
								<div class="mb-3">
										<label for="user_login_id" style="color:black">아이디:</label>
										<span class="user_login_id" style="color:black">${user.login_id}</span>
								</div>

								<hr class="mb-4">
								<div class="mb-3">
									<label for="email" style="color:black">이메일</label> <input type="email"
										class="form-control" value="${user.email}"
										name="email" placeholder="abcg1234@gmail.com"
										required>
										<form:errors path="email" class="error" />
								</div>

								<hr class="mb-4">
								<div class="mb-3">
									<label for="address" style="color:black">전화번호<span class="text-muted">&nbsp(-없이숫자만
											입력해주세요.)</span></label> <input type="text" class="form-control"
										name="phone" value="${user.phone}"
										placeholder="01012341234" required>
								</div>

								<hr class="mb-4">
								<div class="mb-3">
									<label for="address" style="color:black">비밀번호</label> <input type="password"
										class="form-control" name="password" id="password"
										value="${user.password}" placeholder="Password" required>
								</div>
								
								<hr class="mb-4">
								<button class="btn btn-primary btn-lg btn-block" type="submit">개인정보 수정</button>
							</form>
						</div>

						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/vendor/holder.min.js"></script>
    <script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function() {
        'use strict';
        window.addEventListener('load', function() {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');
          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
              if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
      })();
    </script>