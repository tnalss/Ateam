<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 글자 수 자르기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->

  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>익명게시판</h2>
          <ol>
            <li><a href="<c:url value='/'/>">홈</a></li>
            <li>익명게시판</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->

		<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
    <!-- ======= Section ======= -->
   <section id="" class="container">
		<!-- 섹션의 id와 class는 알아서 지정해주세요 -->
		<!-- 실질적으로 내용이 들어가는 부분 -->
		<h3 class="text-center">익명게시판</h3>
		<div id='list-top' style='margin-bottom: 1rem;'>
			<button type="button" class="btn btn-primary" onclick="location='new.no'" >글 작성</button>
		</div>

		<div class="row">
			<c:forEach items='${list}' var='vo'>
				 <div class="col-4">
         		 <div class="card" style='margin-top: 1rem;'>
         		   <div class="card-header">
            		 익명게시판
           		 </div>
           		 <div class="card-body">
             	<p style='font-size: 23px;'><a href='info.notice?id=${vo.emp_no}'>${vo.board_title }</a></p>
              <a href='info.notice?id=${vo.emp_no}'><span class="card-text">${vo.board_content }</span></a>
            </div>
          </div>
        </div>
			</c:forEach>
        </div>

	</section>
      

   

  </main><!-- End #main -->
