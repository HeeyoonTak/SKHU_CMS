<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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

<link rel="stylesheet" href="${R}css/sidebar.css">
<!-- summernote 서식 -->


<!-- <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
 -->
<!-- 세연 js -->
<!--  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> -->
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="${R}res/summernote/lang/summernote-ko-KR.js"></script>



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

<!--  질문 입력 추가 삽입 -->
<script>
        function attachApplyQ(obj) {
        	const str = 
               `<tr height="40">
				<td colspan=2><input type="text" name="question"
					style="margin: auto" class="form-control input"
					style="margin-bottom: 3px; width: 100%"
					placeholder="질문을 입력해주세요." /></td>
				<td style="text-align: center;"><button
						style="margin: auto" class="btn"
						onclick="
						return delete_row(this);">x</button></td>
			</tr>`;
            $(obj).parents('tr').before(str);;
            return false;
        }

</script>
<!--  회계 입력 tr 삽입 -->
<!-- <script>
        function attachAddr(obj) {
            const str = `
                <input type="text" name="remark" class="form-control input-lg"
				placeholder="질문을 입력해주세요."> <a
				onclick="return delete_row(this);"></a>`;
            $(obj).parents('tr').before(str);
            return false;
        }

</script> -->

<!-- summernote -->
<!-- <script>
    $(document).ready(function() {
          $('#summernote').summernote({
                 placeholder: 'content',
                minHeight: 370,
                maxHeight: null,
                focus: true, 
                lang : 'ko-KR'
          });
        });
    </script> -->

<!-- 회계 입력 tr 삭제 -->
<script>
        function delete_row(obj) {
            $(obj).parents('tbody tr').remove();
            return false;
        }
    </script>

<!-- 회계 입력 빈 값 체크 -->
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

<!--  영수증 첨부 파일 선택 시 파일명 나타내기 -->
<script>
    	function fileChange(obj){
            var fileName = obj.files[0].name;
            $(obj).siblings('.fileName').css("display","inline-block");
            $(obj).siblings('.fileName').text(fileName);   
            return false;         
        };
    
	</script>

<!-- 영수증 첨부 사진 볼 수 있는 modal 띄우기 -->
<script>
		function showReceipt(str){
			var imgurl="${R}club_union/getImage?id="+str;
			$('#receiptImg').attr("src",imgurl);
	   		 $('#createModal').modal('show');
	   			console.log("click open");
		 };
		/* $('#closeModal').on('click', function(){
			$('#modalBox').modal('hide');
			}); */
	</script>

<!-- 회계 내역 삭제 시 확인 alert -->
<script>
		function deleteAlert(){
			alert("삭제되었습니다");
		}
	</script>

<!-- 출석체크 추가 모달 생성 -->
<script>
		function attendanceCreate(){
	    	$('#modal').find('tr:gt(0)').remove();
	    	$('#modal #modal-title').text('출석체크');
	    	$('#date').val('default');
	    	$('#date').attr('readonly', false);
	    	$('#modalForm').attr('action', 'create');
	    	$('#modalForm').attr('onsubmit','return checkForm();');
		    $('#modal').modal('show');
		}
	</script>

<!-- 출석체크 생성시 기존에 존재하는 날짜일 때 에러 메시지 출력, 현재 학기에 해당하는 날짜일때만 삽입 가능 !에러 메시지 출력 -->
<script>
		function checkForm() {
	    	var date = $('#date').val();
	    	var overlap = '';
	    	if ("${findDate}".indexOf(date) != -1) {
				overlap = 'O';
				};
			var start = "${start}";
			var end = "${end}";
			if(start <= date && date <= end) {
				if(overlap == 'O') {
					alert('기존 출석체크 목록에 존재하는 날짜는 삽입할 수 없습니다.');
					return false;
					}
				else return true;
			}
			else {
				alert('해당 학기 날짜를 선택해주세요.');
				return false;
			};
		};
	</script>
