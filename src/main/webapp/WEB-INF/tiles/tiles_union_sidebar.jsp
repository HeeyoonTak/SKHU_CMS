<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />

<div id="divst">
	<ul class="attendance_check-list hor_1" style="margin:0px; padding: 0px">
		<li id="lis"><button type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_union/notice'">
				<b id="bst">공지사항</b>
			</button></li>
		<sec:authorize access="hasAnyRole('ROLE_ClubUnion, ROLE_ClubAdmin')">
			<hr id="hrs">
		<li id="lis"><button type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_union/account'">
				<b id="bst">회계 관리</b>
			</button></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ClubUnion')">	
			<hr id="hrs">
		<li id="lis"><button
				type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_union/club_list'">
				<b id="bst">동아리 관리</b>
			</button></li>
		</sec:authorize>	
			<hr id="hrs">
		<li id="lis"><button
				type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_union/attendance'">
				<b id="bst">출석체크</b>
			</button></li>
			<hr id="hrs">
		<li id="lis"><button
				type="button" class="btn btn-block" id="bts"
				onclick="location.href='${R}club_union/minutes'">
				<b id="bst">회의록</b>
			</button></li>
	</ul>
</div>