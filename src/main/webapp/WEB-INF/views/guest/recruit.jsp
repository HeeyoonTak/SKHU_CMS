<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div id="fh5co-hero">
   <a href="#fh5co-main"
      class="smoothscroll fh5co-arrow to-animate hero-animate-4"><i
      class="ti-angle-down"></i></a>
   <!-- End fh5co-arrow -->
   <div class="container">
      <div class="col-md-8 col-md-offset-2">
         <div class="fh5co-hero-wrap">
         <form>
            <div class="fh5co-hero-intro">
               <h1 class="to-animate hero-animate-1">멋쟁이 사자처럼</h1>
               <h2 class="to-animate hero-animate-2">웹프로그래밍을 기반으로 한 개발 동아리</h2>
            </div>
            </form>
         </div>
      </div>
   </div>
</div>

<div id="fh5co-main">

         <div class="container">
            <div class="row">
               <div class="col-md-12 animate-box">                     
                        <div>
                           <div class="row">
                              <div class="col-md-12 animate-box">
                              <div class="col-md-12" style="margin-bottom: 20px">
                                 <h2 class="h3" >모집 게시판</h2>   
                                 <!-- 모집 게시판 동일 -->
                              </div>
                              <div class="col-md-12">
                                 <div style="margin-left:50px"> 
                                       <table class="table table-striped " style="width:820px"> 
                                          <tr class="text-center"> 
                                             <th style="text-align: center">제목</th> 
                                             <th style="text-align: center">작성자</th> 
                                             <th style="text-align: center">모집 기간</th>
                                             <th style="text-align: center">마감 기간</th>  
                                                <!-- <th>조회</th>  -->
                                          </tr>
                                           <c:forEach var="board" items="${boards}">
                                          <tr> 
                                             <td><a href="content?id=${board.id}">${board.title}</a></td> 
                                             <td style="text-align: center">${board.club.club_name}</td>
                                             <td style="text-align: center"><fmt:formatDate pattern="yyyy-MM-dd" value="${ board.start_date }" /></td>
                                             <td style="text-align: center"><fmt:formatDate pattern="yyyy-MM-dd" value="${ board.end_date }" /></td> 
                                                <!-- <td class="col-sm-1">10</td>  -->
                                          </tr>
                                          </c:forEach>
                                          
                                       </table> 
                                       <div class="col-md-offset-7" style="margin-left:40%;"> 
                                       <ul class="pagination"> 
                                          <li><a href="#" style="color: #90D7EA">이전</a></li> 
                                          <li><a href="#" style="color: #90D7EA">1</a></li> 
                                          <li><a href="#" style="color: #90D7EA">다음</a></li> 
                                       </ul> 
                                       </div>
                                 </div>   
                              </div>
                             </div>
                            </div>
                        </div>
                     
                  
               </div>            
            </div>
         </div>


      </div>
      
      