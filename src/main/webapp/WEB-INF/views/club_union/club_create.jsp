<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<h2 class="to-animate hero-animate-2">동아리 연합회의 세부사항을 확인해 보세요!</h2>
				</div>
			</div>
		</div>
	</div> 
</div>

<div id="fh5co-main">
	<div class="container">
		<div class="row">
			<div class="col-md-12 animate-box">
				<div id="fh5co-tab-feature-vertical" class="fh5co-tab">
					<ul class="resp-tabs-list hor_1">
						<li><i class="fh5co-tab-menu-icon ti-announcement"></i>동아리 관리</li>
						<li><i class="fh5co-tab-menu-icon ti-comment"></i>회계 관리</li>
						<li><i class="fh5co-tab-menu-icon ti-write"></i>공지 사항</li>
						<li><i class="fh5co-tab-menu-icon ti-announcement"></i>출석 체크</li>
						<li><i class="fh5co-tab-menu-icon ti-comment"></i>회의록</li>
					</ul>
					<div class="resp-tabs-container hor_1">
						<div>
							<div class="row">
								<div class="col-xs-12" style="margin-bottom: 0px">
									<h2 class="h3" style="margin-bottom: 0px">동아리 관리</h2>
									<ul class="pagination" style="margin-bottom: 0px">
										<li><a href="club_manage"
											class="btn btn-primary btn-lg" id="l_search_term_btn">목록</a></li>
										<li><a href="club_create"
											class="btn btn-primary btn-lg" id="l_search_term_btn">개설</a></li>
									</ul>
								</div>
								<div class="col-xs-12" style="margin-bottom: 0px">
									<div class="panel panel-info">
										<div class="panel-heading">
											<h3 class="panel-title">신규 동아리 개설</h3>
										</div>
										<div class="panel-body">
											<p style="margin-bottom: 14px;">동아리 이름</p>
											<input type="text" class="form-control"
												style="margin-bottom: 14px;" placeholder="신규 동아리 이름을 입력해주세요">
											<p style="margin-bottom: 14px;">동아리 성격</p>
											<div style="margin-bottom: 14px;">
												<input type="radio" name="chk_info" value="종교/학습">종교/학습
												<input type="radio" name="chk_info" value="종교/학습">문화기획체육
												<input type="radio" name="chk_info" value="종교/학습">공연 1 
												<input type="radio" name="chk_info" value="종교/학습">공연 2
											</div>
											<p style="margin-bottom: 14px;">동아리 ID</p>
											<input type="text" class="form-control"
												style="margin-bottom: 14px;" placeholder="ID를 입력해주세요">
											<p style="margin-bottom: 14px;">동아리 비밀번호</p>
											<input type="text" class="form-control"
												style="margin-bottom: 14px;" placeholder="비밀번호를 입력해주세요">
											<button class="btn btn-primary" href="#"
												style="float: right; margin-top: 10px; margin-right: 0; margin-right: 0px; margin-bottom: 0px;">변경</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>