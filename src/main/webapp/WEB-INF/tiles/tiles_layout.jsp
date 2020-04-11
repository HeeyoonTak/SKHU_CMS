<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />



<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="header" />


</head>
<body>
	<tiles:insertAttribute name="nav" />

	<tiles:insertAttribute name="body" />

	<tiles:insertAttribute name="footer" />

	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
	<!-- jQuery Easing -->
	<script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<!-- Owl carousel -->
	<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
	<!-- Magnific Popup -->
	<script src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js"></script>
	<!-- Superfish -->
	<script src="${pageContext.request.contextPath}/js/hoverIntent.js"></script>
	<script src="${pageContext.request.contextPath}/js/superfish.js"></script>
	<!-- Easy Responsive Tabs -->
	<script src="${pageContext.request.contextPath}/js/easyResponsiveTabs.js"></script>
	<!-- FastClick for Mobile/Tablets -->
	<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
	<!-- Parallax -->
	<script src="${pageContext.request.contextPath}/js/jquery.parallax-scroll.min.js"></script>
	<!-- Waypoints -->
	<script src="${pageContext.request.contextPath}/js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>