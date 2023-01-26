<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>

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
     <h3>익명 글 안내</h3>
<table class='table table-hover'>
<tr><th class='w-px140'>제목</th>
	<td colspan='5'>${vo.board_title}</td>
</tr>
<tr>
	<th class='w-px160'>작성일자</th>
	<td class='w-px160'>${vo.write_date}</td>
	<th class='w-px140'>조회수</th>
	<td class='w-px140'>${vo.board_hits}</td>
</tr>
<tr><th>내용</th>
	<td colspan='5'>${fn: replace(  fn:replace( vo.board_content, lf, '<br>' )  , crlf, '<br>')}</td>
</tr>

</table>
<c:set var='params' value='curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}'/>
<div class='btnSet'>
	<button type="button" class="btn btn-primary"
				onclick="location='list.bo'">글 목록 </button>
	<!-- 작성자가 로그인한 경우만 수정/삭제 가능 -->
	<c:if test='${loginInfo.emp_name eq vo.emp_name}'>
	<a class='btn btn-primary' href='modify.bo?id=${vo.board_no}'>정보수정</a>
	<a class='btn btn-danger btn-delete'>정보삭제</a>
	</c:if>
	<!-- 로그인한 경우 답글쓰기 가능 -->
	
	<c:if test='${ ! empty loginInfo }'>
	<form action="reply_insert.bo" method="post" id="insertReply">
	<div class='mt-4'>
	<input type='text' size="60" placeholder="댓글을 작성하세요"  name='reply_content'>
	<input type="hidden" name="board_no" value="${ vo.board_no}"/>
	<input type="hidden" name="emp_no" value="${loginInfo.emp_no }"/>
	<a class='btn btn-primary reply_btn'>답글쓰기</a>
	</div>
	</form>
	</c:if>
</div>

	<!-- 댓글 -->
	<div id='reply.no' class="mt-4">
   <p style='font-size: 22px;'>댓글</p>
   <div class="row">
   <c:forEach items='${board }' var='reply'>
    <div class="col-5 mt-2">
          <div class="card">
            <div class="row no-gutters">
             <div class="col-3 mt-3">
               <img src="assets/img/user_profile.png" class="img-fluid p-2 profile" alt="">
              </div>
               <div class="col-9">
                <div class="card-body">
                  <p class="card-text" style="margin-left: -30px;">${reply.reply_content }</p>
                </div>
                <div class="text-end" style='margin: 10px;'>
                 <p class="card-text"><fmt:formatDate pattern="yyyy/MM/dd"
							value="${reply.reply_create_date}" /></p>
                <a class='btn btn-primary' href='modify_reply.bo'>수정</a>
				<a class='btn btn-danger' href="javascript:delete_reply(${reply.reply_no},${vo.board_no })">삭제</a>
				</div>
              </div>
            </div>
          </div>
        </div>

          </c:forEach>
        </div>  
          </div>

    </section><!-- End Section -->
   
<script type="text/javascript">
$('.btn-delete').on('click', function(){
	if( confirm('정말 삭제?') ){
		location = 'delete.bo?id=${vo.board_no}';
	}
});

$('.reply_btn').on('click', function(){
	if( confirm('댓글을 등록하시겠습니까?') ) {
		$('#insertReply').submit();
		//location = 'reply_insert.no'
	}
});

function delete_reply(no,board_no){
	if(confirm('delete?')){
    location = 'reply_delete.bo?id=' + board_no +'&reply_no=' +no;}
 };
</script>
  </main><!-- End #main -->
