<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />

<div id="divst">
	<ul class="attendance_check-list hor_1" style="margin:0px; padding: 0px">
		<li id="lis"><button type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_admin/notice?club_id=${club_id}'">
				<b id="bst">공지사항</b>
			</button></li>
			<hr id="hrs">
		<li id="lis"><button type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_admin/account?club_id=${club_id}'">
				<b id="bst">회계 관리</b>
			</button></li>
			<hr id="hrs">
		<li id="lis"><button
				type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_admin/minutes?club_id=${club_id}'">
				<b id="bst">회의록</b>
			</button></li>
			<hr id="hrs">
		<li id="lis"><button
				type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_admin/publicity?club_id=${club_id}'">
				<b id="bst">홍보게시판</b>
			</button></li>
			<hr id="hrs">
		<li id="lis"><button
				type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_admin/recruit?club_id=${club_id}'">
				<b id="bst">모집게시판</b>
			</button></li>
			<hr id="hrs">
		<li id="lis"><button
				type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_admin/attendance?club_id=${club_id}'">
				<b id="bst">출석체크</b>
			</button></li>
		<sec:authorize access="hasRole('ROLE_ClubAdmin')">
		    <hr id="hrs">
			<li id="lis"><button
					type="button" class="btn btn-block" id="bts"
					onclick="location.href='${R}club_admin/club_manage?club_id=${club_id}'">
					<b id="bst">동아리 관리</b>
				</button></li>
		</sec:authorize>
	</ul>
</div>