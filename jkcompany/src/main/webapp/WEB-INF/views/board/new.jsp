<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->

  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2> 글 등록</h2>
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
     <h3>익명글등록</h3>
<form method='post' id='insert' action='insert.bo' enctype='multipart/form-data'>
<input type='hidden' name='emp_no' value='${loginInfo.emp_no}'>
<input type='hidden' name='board_cate' value='O1'>
<table class='table table-hover'>
<tr><th>제목</th>
	<td><input type='text' name='board_title' class='full chk' title='제목'></td>
</tr>
<tr><th>내용</th>
	<td><textarea name='board_content' class='full chk' title='내용'></textarea></td>
</tr>
<tr><th>첨부파일</th>
	<td class='text-left'>
		<div class='align'>
		<label>
			<input type='file' name='file' id='attach-file'>
			<a><i class="font-b fa-solid fa-file-arrow-up"></i></a>
		</label>
		<span id='file-name'></span>
		<span id='preview'></span>
		<a id='delete-file'><i class="font-r fa-regular fa-trash-can"></i></a>
		</div>
	</td>
</tr>
</table>
</form>
<div class='btnSet'>
	<a class='btn btn-primary save'>저장</a>
	<a class='btn btn-primary' href='list.bo'>취소</a>
</div>
<script>
$('.save').click(function(){
	if( emptyCheck() ){
		$('#insert').submit(); 
		consol.log('여기옴?');
	}
});
</script>
      
      
      
    </section><!-- End Section -->

   

  </main><!-- End #main -->
