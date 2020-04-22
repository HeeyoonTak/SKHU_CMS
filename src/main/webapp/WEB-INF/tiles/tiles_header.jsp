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
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
        function attachAddr(obj) {
            const str = `<tr id="default">
							<td><input type="date" name="date"
								class="form-control input-lg"></td>
							<td><select class="form-control input-lg"
								name="account_type">
									<c:forEach var="at" items="${ account_type }"
										varStatus="i">
										<option value="${i.index}">${ at }</option>
									</c:forEach>
							</select></td>
							<td><input type="number" name="price"
								class="form-control input-lg" placeholder="사용금액"></td>
							<td><input type="text" name="remark"
								class="form-control input-lg" placeholder="사용내용 및 비고"></td>
							<!-- <td></td> -->
							<td><input type="file" name="file"
								class="btn btn-primary" id="uploadImage"
								onchange="fileChange(this);"> <label
								for="uploadImage" class="fileName" style="display: none"></label>
							</td>
							<td><a onclick="return delete_row(this);">x</a></td>
						</tr>`;
            $(obj).parents('tr').before(str);
            return false;
        }
    </script>
    <script>
        function delete_row(obj) {
            $(obj).parents('tbody tr').remove();
            return false;
        }
    </script>

	<script>
		 function check(ci_count){
				var isValid=true;
				var str="";

				$('.table_'+ci_count).find('input[type=date]').each(function(i,e){
					if($(e).val()=="") {
						if(str!="" && !str.includes("날짜")) str +="날짜";
						else if(str=="") str+="날짜";
						
			           // alert("날짜를 입력해주세요");
			            $(e).focus();
			            isValid=false;
			        }
			     });

				$('.table_'+ci_count).find('input[name=price]').each(function(i,e){
					if($(e).val()=="") {
						if(str!="" && !str.includes("사용 금액")){
						str +=",사용 금액";}
						else if(str=="") str+="사용 금액";
						
			           // alert("사용 금액을 입력해주세요");
			            $(e).focus();
			            isValid=false;
			        }
			     });

				$('.table_'+ci_count).find('input[type=text]').each(function(i,e){
					if($(e).val()=="") {
						if(str!="" && !str.includes("사용 내역")){
							str +=",사용 내역";}
						else if(str=="") str+="사용 내역";
						
			           // alert("사용 내역을 입력해주세요");
			            $(e).focus();
			            isValid=false;
			        }
			     });

				$('.table_'+ci_count).find('input[type=file]').each(function(i,e){
					if($(e).val()=="") {
						if(str!="" && !str.includes("영수증")){
							str +=",영수증";}
						else if(str=="") str+="영수증";
			            //alert("영수증을 첨부해주세요");
			            $(e).focus();
			            isValid=false;
			        }
			     });
			    if(!isValid && str=="날짜") alert(str+"를 입력해주세요");
			    else if(!isValid) alert(str+"을 입력해주세요");

			 return isValid; 
			 
			/*  var titleElement = $('.table_'+ci_count).find('input[type=file]');
			 
			 console.log(titleElement.length);
			 
			 if($('.table_'+ci_count).find('input[type=file]').val()=="") {
		            alert("영수증을 첨부해주세요");
		            return false;		        
		       		} */
		    } 
	</script>

<script>
    function fileChange(obj){
            var fileName = obj.files[0].name;
            $(obj).siblings('.fileName').css("display","inline-block");
            $(obj).siblings('.fileName').text(fileName);   
            return false;         
        };
    
	</script>
