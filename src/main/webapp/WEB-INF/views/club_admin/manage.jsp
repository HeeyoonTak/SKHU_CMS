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
               <h1 class="to-animate hero-animate-1">내 동아리 이름 받아오기</h1>
               <h2 class="to-animate hero-animate-2">동아리 소개글 받아오기</h2>
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
                     <h3>동아리 정보 편집</h3>
                  </div>
                   <div>
                   <form:form method="post" modelAttribute="club">
                   <h4 style="color: #8B969C; font-weight: bold">소개글</h4>
                   <textarea name="content" class="form-control input-md" style="width: 500px; height: 250px">${club.content }</textarea>
                   <input type="hidden" name="content" />
                   <button class="btn btn-lg btn-primary" type="submit"
                                 style="margin-left: 400px; margin-top: 15px">저장</button>
                   </form:form>
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