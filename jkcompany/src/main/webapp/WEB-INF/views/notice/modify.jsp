<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>

<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
.h-px300 {
height: 300px;
}
</style>


  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>메뉴이름</h2>
          <ol>
            <li><a href="<c:url value='/'/>">홈</a></li>
            <li>메뉴이름</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->

		<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
    <!-- ======= Section ======= -->
    <section id="" class="container">
      <h3>공지글 수정</h3>
<form method='post' id='modify' action='update.no' enctype='multipart/form-data'>
<input type='hidden' name='board_no' value='${vo.board_no}'>
<table class='table'>
<tr><th class='w-px140'>제목</th>
	<td><input type='text' name='board_title' class='w-px300' title='제목' 
				value="${vo.board_title}"></td>
</tr>
<tr>
	<th>작성자</th>
	<td>${vo.emp_name }</td>
	</tr>
	<tr>
	<th>작성일</th>
	<td>${vo.write_date }</td>
	</tr>
	
<tr><th>내용</th>
	<td><textarea name='board_content' class='w-px1200 h-px300' title="내용">${vo.board_content}</textarea></td>
</tr>

</table>

</form>
<div class='btnSet'>
	<a class='btn btn-primary save'>저장</a>
	<a class='btn btn-secondary' href='info.no?id=${vo.board_no}&curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}'>취소</a>
</div>
<script>

$('.save').click(function(){
	if( emptyCheck() )  {
	
		$('#modify').submit();
	}
});
</script>
      
      
      
    </section><!-- End Section -->

   

  </main><!-- End #main -->
