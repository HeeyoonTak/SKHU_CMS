<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />

<meta charset="UTF-8">
<title>SKHU CMS</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
<meta name="author" content="FREEHTML5.CO" />

<!--
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE
	DESIGNED & DEVELOPED by FREEHTML5.CO

	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 	https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico">

<!-- Google Webfont -->
<link rel="stylesheet" href="${R}fonts/nanum/NanumSquare_acB.ttf">

<!--	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css'> -->
<!-- Themify Icons -->
<link rel="stylesheet" href="${R}css/themify-icons.css">
<!-- Bootstrap -->
<link rel="stylesheet" href="${R}css/bootstrap.css">
<!-- Owl Carousel -->
<link rel="stylesheet" href="${R}css/owl.carousel.min.css">
<link rel="stylesheet" href="${R}css/owl.theme.default.min.css">
<!-- Magnific Popup -->
<link rel="stylesheet" href="${R}css/magnific-popup.css">
<!-- Superfish -->
<link rel="stylesheet" href="${R}css/superfish.css">
<!-- Easy Responsive Tabs -->
<link rel="stylesheet" href="${R}css/easy-responsive-tabs.css">
<!-- Animate.css -->
<link rel="stylesheet" href="${R}css/animate.css">
<!-- Theme Style -->
<link rel="stylesheet" href="${R}css/style.css">

<link rel="stylesheet" href="${R}css/hm_style.css">

<link rel="stylesheet" href="${R}css/yj_style.css">


<!-- Modernizr JS -->
<script src="${R}js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
<!-- import CSS -->
<link rel="stylesheet"
	href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>

<script>
        function attachAddr(obj) {
            const str = `<tr> 
                                <td><input type="date" class="form-control input-lg"></td>
                                <td><input type="number" class="form-control input-lg"></td>
                                <td><input type="number" class="form-control input-lg"></td>
                                <td><input type="text" class="form-control input-lg"></td>
                                <td></td>
                                <td><a href="#" class="btn btn-primary col-md">영수증</a></td>
                                <td><a href="#">x</a></td>                            </tr>`;
            $(obj).parents('tbody').prev('#addTd').append(str);
        }
</script>

<script>
        function attachAddrAtt(obj) {
            const str = `<tr>
		<td><input type="date" class="form-control input-md"
			style="width: 160px"></td>
		<c:forEach var="clubs" items="${ clubs }">
			<td><input type="checkbox" class="form-control input-sm"></td>
		</c:forEach>
		<td><a href="#">x</a></td>
	</tr>`;
    $(obj).parents('tbody').prev('#addTd').append(str);
    }
</script>