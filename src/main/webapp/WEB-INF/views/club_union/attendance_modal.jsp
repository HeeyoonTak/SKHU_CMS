<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Modal -->
<div class="modal fade" id="attendanceModal" role="dialog">
	<div class="modal-dialog modal-md">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 id="modal-title" class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<table class="table attendance_check_table" id="modalTable">
					<tr>
						<td>출석 날짜</td>
						<td><input type="date" class="form-control input-md"
							style="width: 200px"></td>
					</tr>
					<c:forEach var="adminUser" items="${ adminUser }">
						<tr>
							<td>${adminUser}</td>
							<td><input type="checkbox" class="form-control input-sm"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="modal-footer">
				<button id="modalSubmit" type="button" class="btn btn-primary col-md">저장</button>
				<button id="closeModal" type="button" class="btn btn-primary col-md" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>