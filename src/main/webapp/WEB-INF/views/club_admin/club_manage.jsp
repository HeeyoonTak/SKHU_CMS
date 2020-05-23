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
                     <h4 style="color: #CCCCCC;">동아리 관리</h4>
                  </div>
                  <div>
                     <a href="manage?club_id=${club_id}" class="btn btn-primary btn-lg"
                                 id="l_search_term_btn">동아리 정보 편집</a>
                     <a href="acceptance?club_id=${club_id}" class="btn btn-primary btn-lg"
                                 id="l_search_term_btn" >회원 관리</a>
                     <a href="apply_q_list" class="btn btn-primary btn-lg"
                                 id="l_search_term_btn" >지원 폼 작성</a>
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