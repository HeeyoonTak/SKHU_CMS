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
		<div class="row animate-box">
		
		<div class="row1">
			<div class="col-md-9 col-md-push-3" id="fh5co-content">
				<div class="row">
				<div style="margin-bottom: 20px">
			<h4 style="color: #CCCCCC; text-align:center" id="heading">출석체크</h4>
			</div>
					<form action="#" method="get">
						<input type="hidden" name="club_id" value="${club_id}">
						<div class="col-md-9"></div>
						<div class="col-md-3">
							<div class="form-group">
								<form:form method="get" modelAttribute="semdate">
									<form:select path="id" class="form-control input-lg autosubmit"
										id="l_search_term">
										<form:options value="${ id }" itemValue="id"
											itemLabel="sem_name" items="${ sems }" id="selectSemId" />
									</form:select>
								</form:form>
							</div>
						</div>
					</form>
				</div>

				<div class="content-box animate-box">
					<table class="table text-center attendance_check_table">
						<thead>
							<tr>
								<th></th>
								<c:forEach var="adminUser" items="${adminUser}">
									<th>${adminUser}</th>
								</c:forEach>
								<sec:authorize access="hasRole('ROLE_ClubAdmin')">
									<c:if test="${selectSemId eq sem}">
										<th></th>
									</c:if>
								</sec:authorize>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="findDate" items="${ findDate }">
								<tr>
									<sec:authorize access="hasRole('ROLE_ClubAdmin')">
										<c:choose>
											<c:when test="${selectSemId eq sem}">
												<!-- 날짜 클릭시 해당 날짜에 따른 출석체크 수정 모달  -->
												<td id="attendanceUpdate" find="${findDate}">${ findDate }</td>
											</c:when>
											<c:otherwise>
												<td>${ findDate }</td>
											</c:otherwise>
										</c:choose>
									</sec:authorize>
									<sec:authorize
										access="hasAnyRole('ROLE_ClubUnion','ROLE_Member')">
										<td>${ findDate }</td>
									</sec:authorize>
									<c:forEach var="attendance" items="${ attendance }"
										varStatus="status">
										<c:if test="${attendance.date eq findDate}">
											<c:choose>
												<c:when test="${ attendance.check eq 0 }">
													<td>-</td>
												</c:when>
												<c:otherwise>
													<td>O</td>
												</c:otherwise>
											</c:choose>
											<sec:authorize access="hasRole('ROLE_ClubAdmin')">
												<c:if test="${selectSemId eq sem}">
													<c:if test="${status.count % fn:length(adminUser) eq 0}">
														<!--출석체크 삭제-->
														<td><a
															href="attendance_delete?date=${attendance.date}&club_id=${club_id}">x</a></td>
													</c:if>
												</c:if>
											</sec:authorize>
										</c:if>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
						<sec:authorize access="hasRole('ROLE_ClubAdmin')">
							<c:if test="${selectSemId eq sem}">
								<tbody>
									<tr>
										<!--출석체크 삽입 모달-->
										<td colspan="${fn:length(adminUser) + 2}">
											<button id="createBtn" class="btn btn-primary col-md"
												onclick="return attendanceCreate();">+</button>
										</td>
									</tr>
								</tbody>
							</c:if>
						</sec:authorize>
					</table>
				</div>
			</div>
			<div class="col-md-3 col-md-pull-9" id="fh5co-sidebar">
				<c:import url="../tiles/tiles_club_sidebar.jsp" />
			</div>
		</div>
		</div>
	</div>
</div>

<!-- Modal -->
<form:form method="post" action="" id="modalForm">
	<input type="hidden" name="club_id" value="${club_id}">
	<div class="modal fade" id="modal" role="dialog">
		<div class="modal-dialog modal-md">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 id="modal-title" class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<table class="table attendance_check_table" id="updateModalTable">
						<tr>
							<td>출석 날짜</td>
							<td><input type="date" class="form-control input-md"
								style="width: 200px" name="date" id="date"></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button id="ModalSubmit" type="submit"
						class="btn btn-primary col-md">저장</button>
					<button id="closeModal" type="button"
						class="btn btn-primary col-md" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</form:form>
<!-- 끝 -->

<script type="text/javascript">
		window.onload = function() {
		$("#l_search_term").val(${selectSemId}).prop("selected", true);
		};

	    //출석체크 수정 모달
	    $("[id=attendanceUpdate]").each(function(){
	    		$(this).click(function(){
	    			$('#modal').find('tr:gt(0)').remove();
	    			$('#modal #modal-title').text('출석체크 수정');
	    			$('#date').attr('readonly', true);
	    			$('#modalForm').attr('action', 'attendance');
	    			$('#modalForm').attr('onsubmit','');
	    			$('#modal').modal('show');
				    var find = $(this).attr("find");
				    var club_id = '${club_id}';
			    	var obj;
		    	    jQuery.ajax({
			    	   type:"POST", 
			    	   url : "/club_admin/update?find="+find+"&club_id="+club_id,
			    	   dataType:"json",
			    	   success : function(data) { 
			    	      obj = data;
			    	      if(obj.testlist.length>0){
			    	    	  $("#date").val(find);
			    	    	  for(var i=0; i<obj.testlist.length; i++) {
			    	    		  var ck;
			    	    		  var id = obj.testlist[i].id;
			    	    		  if(obj.testlist[i].check == 0){
			    	    		  ck="<td><input type='checkbox' class='form-control input-sm' name='updateck' value='"+id+"'></td>";
			    	    		      }
			    	    		  else{
			    	    		  ck="<td><input type='checkbox' class='form-control input-sm' name='updateck' value='"+id+"' checked></td>";
			    	    		      }
			    	    		  $("#updateModalTable").append("<tr> <td>"+ obj.testlist[i].name +"</td>"+ ck +"</tr>");
			    	    		  }
			    	         }
			    	     },
			    	     complete : function(data) { },
			    	     error : function(xhr, status , error) {console.log("ajax ERROR!!! : " );}
			    		})
					})
			   });
</script>