<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 글자 수 자르기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
.page-list {
	text-align: center;
	column-gap: .5rem;
}

.text_over {
margin-top : -8px;
  width: 340px;
  height: 105px;
   text-overflow: ellipsis;
   overflow: hidden;
   word-break: break-word;
   display: -webkit-box;
   -webkit-line-clamp: 3; 
   -webkit-box-orient: vertical
}

.text_overheader {
height : 45px;
   text-overflow: ellipsis;
   overflow: hidden;
   word-break: break-word;
   display: -webkit-box;
   -webkit-line-clamp: 1; 
   -webkit-box-orient: vertical
}
</style>
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
			<button type="button" class="btn btn-primary" onclick="location='new.bo'" >글 작성</button>
		</div>

		<!-- 검색 -->
 <form method='post' action='list.bo' id="list">
			<div id='list-top mt-3' class='w-px1200'>

				<select class='w-px100' name='search'>
					<option value='all' ${page.search eq 'all' ? 'selected':''}>전체</option>
					<option value='board_title' ${page.search eq 'board_title' ? 'selected':''}>제목</option>
					<option value='board_content'
						${page.search eq 'content' ? 'selected':''}>내용</option>
				</select> <input type='text' class='w-px300' name='keyword'
					value='${page.keyword}'>

				<button type="button" class="btn btn-primary btn-search">
					검색</button>
			</div>
			<input type='hidden' name='curPage' value='1'>
		</form>

		<div class="row">
			<c:forEach items='${page.list}' var='vo'>
				 <div class="col-4">
         		 <div class="card" style='margin-top: 1rem;'>
         		   <div class="card-header text_overheader">
            		 <p style='font-size: 20px; text-overflow: ellipsis;'><a href='info.bo?id=${vo.board_no}'>${vo.board_title }</a></p>
           		 </div>
           		 <div class="card-body text_over">
              <a href='info.bo?id=${vo.board_no}'><span class="card-text">${vo.board_content }</span></a>
            </div>
          </div>
        </div>
			</c:forEach>
        </div>
        <jsp:include page="/WEB-INF/views/include/page.jsp" />

	</section>
      

   

  </main><!-- End #main -->
