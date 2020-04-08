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
					<h1 class="to-animate hero-animate-1">Slant. Free HTML5
						Template</h1>
					<h2 class="to-animate hero-animate-2">
						Lovely Made by <a href="http://freehtml5.co" target="_blank">FREEHTML5.co</a>
					</h2>
					<p class="to-animate hero-animate-3">
						<a href="#" class="btn btn-outline btn-lg">Get Started</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- START fhtco-main -->
<div id="fh5co-main">
	<!-- START container -->
	<div class="container">

		<div class="row animate-box">
			<h2 class="fh5co-uppercase-heading-sm text-center">회계 관리</h2>

		</div>


		<div class="row animate-box">
			<div class="fh5co-spacer fh5co-spacer-md"></div>
			<div id="fh5co-tab-feature-center" class="fh5co-tab text-center">
				<ul class="resp-tabs-list hor_1">
					<li><i class="fh5co-tab-menu-icon"></i>동아리 연합회</li>
					<li><i class="fh5co-tab-menu-icon"></i>멋쟁이사자처럼</li>
					<li><i class="fh5co-tab-menu-icon"></i>소울</li>
				</ul>
				<div class="resp-tabs-container hor_1">
					<div>
						<div class="row">
							<form action="#" method="post">
								<div class="col-md-8"></div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="search_term" class="sr-only">학기</label> <select
											class="form-control input-lg" id="l_search_term">
											<option>2019-1학기</option>
											<option>2019-2학기</option>
											<option selected>2020-1학기</option>
										</select>

										<!--<input type="submit" class="btn btn-primary btn-lg " value="검색">-->
										<a class="btn btn-primary btn-lg" id="l_search_term_btn">검색</a>
									</div>
								</div>
							</form>
						</div>
						<div class="row">
							<div class="panel panel-default">
								<table class="table text-center l_account_table">
									<thead>
										<tr>
											<th>날짜</th>
											<th>지원금 사용내역</th>
											<th>동아리회비 사용내역</th>
											<th>비고</th>
											<th>잔액</th>
											<th>영수증 첨부</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>2020.3.2</td>
											<td>+200,000</td>
											<td>-</td>
											<td>동아리 지원금</td>
											<td>200,000</td>
											<td><a href="#" class="btn btn-primary">영수증</a></td>
											<td><a href="#">x</a></td>
										</tr>
									</tbody>
									<tbody>
										<tr>
											<td>2020.3.2</td>
											<td>-</td>
											<td>+20,0000</td>
											<td>동아리 회비</td>
											<td>400,000</td>
											<td><a href="#" class="btn btn-primary">영수증</a></td>
											<td><a href="#">x</a></td>
										</tr>
									</tbody>
									<tbody>
										<tr>
											<td>2020.3.12</td>
											<td>-100,0000</td>
											<td>-</td>
											<td>동아리 비품구입</td>
											<td>300,000</td>
											<td><a href="#" class="btn btn-primary">영수증</a></td>
											<td><a href="#">x</a></td>
										</tr>
									</tbody>
									<tbody>
										<tr>
											<td>2020.3.12</td>
											<td>-</td>
											<td>-200,000</td>
											<td>동아리 회식비</td>
											<td>100,000</td>
											<td><a href="#" class="btn btn-primary">영수증</a></td>
											<td><a href="#">x</a></td>
										</tr>
									</tbody>
									<tbody>
										<tr>
											<td><input type="date" class="form-control input-lg"></td>
											<td><input type="number" class="form-control input-lg"></td>
											<td><input type="number" class="form-control input-lg"></td>
											<td><input type="text" class="form-control input-lg"></td>
											<td></td>
											<td><a href="#" class="btn btn-primary">영수증</a></td>
											<td><a href="#">x</a></td>
										</tr>
									</tbody>
									<tbody id="addTd"></tbody>
									<tbody>
										<tr>
											<td colspan="6"><button onclick="attachAddr(this)"
													class="btn btn-primary col-md" id="pls-btn">+</button></td>
										</tr>
									</tbody>
								</table>
							</div>

							<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-4">
									<button class="btn btn-primary col-md-offset-7 btn-lg"
										id="l_account_save">회계 저장</button>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div>
							<div class="row">
								<form action="#" method="post">
									<div class="col-md-8"></div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="search_term" class="sr-only">학기</label> <select
												class="form-control input-lg" id="l_search_term">
												<option>2019-1학기</option>
												<option>2019-2학기</option>
												<option selected>2020-1학기</option>
											</select>

											<!--<input type="submit" class="btn btn-primary btn-lg " value="검색">-->
											<a class="btn btn-primary btn-lg" id="l_search_term_btn">검색</a>
										</div>
									</div>
								</form>
							</div>
							<div class="row">
								<div class="panel panel-default">
									<table class="table text-center l_account_table">
										<thead>
											<tr>
												<th>날짜</th>
												<th>지원금 사용내역</th>
												<th>동아리회비 사용내역</th>
												<th>비고</th>
												<th>잔액</th>
												<th>영수증 첨부</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>2020.3.2</td>
												<td>+200,000</td>
												<td>-</td>
												<td>동아리 지원금</td>
												<td>200,000</td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody>
											<tr>
												<td>2020.3.2</td>
												<td>-</td>
												<td>+20,0000</td>
												<td>동아리 회비</td>
												<td>400,000</td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody>
											<tr>
												<td>2020.3.12</td>
												<td>-100,0000</td>
												<td>-</td>
												<td>동아리 비품구입</td>
												<td>300,000</td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody>
											<tr>
												<td>2020.3.12</td>
												<td>-</td>
												<td>-200,000</td>
												<td>동아리 회식비</td>
												<td>100,000</td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody>
											<tr>
												<td><input type="date" class="form-control input-lg"></td>
												<td><input type="number" class="form-control input-lg"></td>
												<td><input type="number" class="form-control input-lg"></td>
												<td><input type="text" class="form-control input-lg"></td>
												<td></td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody id="addTd"></tbody>
										<tbody>
											<tr>
												<td colspan="6"><button
														onclick="attachAddr(this); return false;"
														class="btn btn-primary col-md">+</button></td>
											</tr>
										</tbody>
									</table>
								</div>

								<div class="row">
									<div class="col-md-8"></div>
									<div class="col-md-4">
										<button class="btn btn-primary col-md-offset-7 btn-lg"
											id="l_account_save">회계 저장</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div>
							<div class="row">
								<form action="#" method="post">
									<div class="col-md-8"></div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="search_term" class="sr-only">학기</label> <select
												class="form-control input-lg" id="l_search_term">
												<option>2019-1학기</option>
												<option>2019-2학기</option>
												<option selected>2020-1학기</option>
											</select>

											<!--<input type="submit" class="btn btn-primary btn-lg " value="검색">-->
											<a class="btn btn-primary btn-lg" id="l_search_term_btn">검색</a>
										</div>
									</div>
								</form>
							</div>
							<div class="row">
								<div class="panel panel-default">
									<table class="table text-center l_account_table">
										<thead>
											<tr>
												<th>날짜</th>
												<th>지원금 사용내역</th>
												<th>동아리회비 사용내역</th>
												<th>비고</th>
												<th>잔액</th>
												<th>영수증 첨부</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>2020.3.2</td>
												<td>+200,000</td>
												<td>-</td>
												<td>동아리 지원금</td>
												<td>200,000</td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody>
											<tr>
												<td>2020.3.2</td>
												<td>-</td>
												<td>+20,0000</td>
												<td>동아리 회비</td>
												<td>400,000</td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody>
											<tr>
												<td>2020.3.12</td>
												<td>-100,0000</td>
												<td>-</td>
												<td>동아리 비품구입</td>
												<td>300,000</td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody>
											<tr>
												<td>2020.3.12</td>
												<td>-</td>
												<td>-200,000</td>
												<td>동아리 회식비</td>
												<td>100,000</td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody>
											<tr>
												<td><input type="date" class="form-control input-lg"></td>
												<td><input type="number" class="form-control input-lg"></td>
												<td><input type="number" class="form-control input-lg"></td>
												<td><input type="text" class="form-control input-lg"></td>
												<td></td>
												<td><a href="#" class="btn btn-primary">영수증</a></td>
												<td><a href="#">x</a></td>
											</tr>
										</tbody>
										<tbody id="addTd"></tbody>
										<tbody>
											<tr>
												<td colspan="6"><button
														onclick="attachAddr(this); return false;"
														class="btn btn-primary col-md">+</button></td>
											</tr>
										</tbody>
									</table>
								</div>

								<div class="row">
									<div class="col-md-8"></div>
									<div class="col-md-4">
										<button class="btn btn-primary col-md-offset-7 btn-lg"
											id="l_account_save">회계 저장</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="fh5co-spacer fh5co-spacer-md"></div>

		<div class="row"></div>
		<!-- // END row -->
		<div class="fh5co-spacer fh5co-spacer-md"></div>

	</div>
	<!-- // END container -->


</div>