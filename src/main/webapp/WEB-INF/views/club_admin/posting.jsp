<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<div id="fh5co-main" style="padding-top: 0px">
	<div class="container">
		<div class="row">
			<div class="col-md-12 animate-box">
				<div class="row">
					<div class="col-md-12" style="margin-bottom: 20px">
						<h2 class="h3"></h2>
					</div>
					<div class="col-md-12">
						<div style="margin-left: 50px">
							<form:form method="post" modelAttribute="board">
								<table class="table table-striped ">

									<tr style="text-align: center">
										<th style="text-align: center;"><form:input
												placeholder="제목" path="title" class="form-control input-md" /></th>
									</tr>
									<tr>
										<td><textarea id="summernote" name="content">${board.content }</textarea>
											<input type="hidden" name="content" /></td>
									</tr>
									<tr style="text-align: center">
										<td>신청 기간: <form:input type="date" path="start_date"
												class="form-control input-sm"
												style="width: 130px; display :inline" autocomplete="off" />
											~ <form:input type="date" path="end_date"
												class="form-control input-sm"
												style="width: 130px; display :inline" autocomplete="off" /></td>
									</tr>
								</table>
								<div class="row">
									<div class="col-md-12">
										<form:hidden path="board_name_id"
											class="form-control input-md" />
										<form:hidden path="club_id" class="form-control input-md" />
										<a class="btn btn-primary btn-lg" id="l_search_term_btn"
											style="float: right;" href="javascript:history.back();">목록</a>
										<button class="btn btn-lg btn-primary" type="submit"
											style="float: right;" onclick="goWrite(this.form)">저장</button>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
   $('#summernote').summernote({
      height : 300
   });

   function save() {
      var s = $('#summernote').summernote('code');
      $('input[name=content]').val(s);
      $('form').submit();
   }
</script>