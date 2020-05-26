<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
   prefix="sec"%>
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
      <div class="row">

         <div class="content-box animate-box">
            <div class="col-md-9 col-md-push-3" id="fh5co-content">
               <div class="row">
                  <div style="margin-bottom: 20px">
                     <h4 style="color: #CCCCCC; margin-left: 50px">동아리 정보 편집</h4>
                  </div>
                   <div>
                   <form method="post" modelAttribute="club" enctype="multipart/form-data">
                   <div class="row">
                   <div class="col-md-1"></div>
                   <div style="float:left;" class="col-md-5">
                   <h4 style="color: #8B969C; font-weight: bold">사진 등록</h4>
                   <input type="file" name="file_id" id="uploadImage"
					onchange="fileChange(this);"> 
					</div>
					<div style="float:right;" class="col-md-5">
                   <h4 style="color: #8B969C; font-weight: bold">소개글</h4>
                   <textarea name="content" class="form-control input-md" style="width: 270px; height: 250px">${club.content }</textarea>
                   </div>   
                   </div>
                   <div>
                   <button class="btn btn-lg btn-primary" type="submit" style="float:right; margin-top: 20px; ">저장</button>
                   </form>
                   </div>
                   </div>
               </div>
            </div>
         </div>
         <div class="col-md-3 col-md-pull-9" id="fh5co-sidebar">
                <c:import url="../tiles/tiles_club_sidebar.jsp" />
         </div>
      </div>
   </div>


</div>
<!-- END fhtco-main -->