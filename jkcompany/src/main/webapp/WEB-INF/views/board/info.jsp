<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
.h-px80 {
height: 80px;
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
     <h3>익명 글 안내</h3>
     
     <div class="card text-center">
  <div class="card-header">
 <h5> 제목 : ${vo.board_title} </h5> 
  </div>
  <div class="card-body">
    <p class="card-text">${fn: replace(  fn:replace( vo.board_content, lf, '<br>' )  , crlf, '<br>')}</p>
  </div>
  <div class="card-footer text-muted">
   작성일자 : ${vo.write_date} <span style='margin-left: 3rem;'>조회수 : ${vo.board_hits} </span> 
  </div>
</div>
<c:set var='params' value='curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}'/>
<div class='btnSet mt-3'>
	<button type="button" class="btn btn-primary"
				onclick="location='list.bo'">글 목록 </button>
	<!-- 작성자가 로그인한 경우만 수정/삭제 가능 -->
	<c:if test='${loginInfo.emp_name eq vo.emp_name}'>
	<a class='btn btn-primary' href='modify.bo?id=${vo.board_no}'>수정</a>
	<a class='btn btn-danger btn-delete'>글삭제</a>
	</c:if>
	<!-- 로그인한 경우 답글쓰기 가능 -->
	<c:if test='${ ! empty loginInfo }'>
	<form action="reply_insert.bo" method="post" id="insertReply">
	<div class='mt-4'>
	<input type='text' size="60" placeholder="댓글을 작성하세요"  name='reply_content'>
	<input type="hidden" name="board_no" value="${ vo.board_no}"/>
	<input type="hidden" name="emp_no" value="${loginInfo.emp_no }"/>
	<input type="hidden" name="reply_content" value="${reply.reply_content }"/>
	<a class='btn btn-primary reply_btn'>답글쓰기</a>
	</div>
	</form>
	</c:if>
</div>

<form action="reply_update.bo" method="post" id="updateReply">
	<!-- 댓글 -->
	<div id='reply.no' class="mt-4">
   <p style='font-size: 22px;'>댓글</p>
   <div class="row">
   <c:forEach items='${board }' var='reply'>
    <div class="col-12 mt-2">
          <div class="card">
            <div class="row no-gutters">
             <div class="col-2">
               <img src="assets/img/user_profile.png" class="img-fluid p-2 profile" alt="">
              </div>
               <div class="col-10">
                <div class="card-body">
                <p class="card-text" id="reply_content" style="margin-left: -30px;">${reply.reply_content } </p>
                					
                <input type="text" class="w-px800 h-px80" id="reply_incontent" value="${reply.reply_content }" style="display: none"/>
                </div>
                <div class="text-end" style='margin: 10px;'>
                 <p class="card-text"><fmt:formatDate pattern="yyyy/MM/dd"
							value="${reply.reply_create_date}" /></p>
				<div id="modify_off" >			
					<!-- 수정 전 화면 -->		
                <a class='btn btn-primary modify_reply'>수정</a>
				<a class='btn btn-danger' href="javascript:delete_reply(${reply.reply_no},${vo.board_no })">삭제</a>
				</div>
				
				<div id="modify_on" style="display: none">
				<!-- 수정 화면 -->		
					<a class='btn btn-primary modify_ok'>저장</a>
				<a class='btn btn-danger' id="modify_cancel">취소</a>
				</div>
				</div>
              </div>
            </div>
          </div>
        </div>
          </c:forEach>
        </div>  
          </div>
</form>
    </section><!-- End Section -->
   
<script type="text/javascript">

// 수정 저장 버튼
$('.modify_ok').on('click', function(){
	$('#updateReply').submit();
	$('#modify_on').css({"display":"none"});
	$('#modify_off').css({"display":"block"});
	$('#reply_incontent').css({"display":"none"});
	$('#reply_content').css({"display":"block"});
	$('#reply_content').text($('#reply_incontent').val());
});

//수정 화면
$('.modify_reply').on('click', function(){
	$('#modify_on').css({"display":"block"});
	$('#modify_off').css({"display":"none"});
	$('#reply_content').css({"display":"none"});
	$('#reply_incontent').css({"display":"block"});
});
//수정 취소
$('#modify_cancel').on('click', function(){
	$('#modify_on').css({"display":"none"});
	$('#modify_off').css({"display":"block"});
	$('#reply_incontent').css({"display":"none"});
	$('#reply_content').css({"display":"block"});
	
});

$('.btn-delete').on('click', function(){
	if( confirm('게시글을 삭제하시겠습니까?') ){
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
	if(confirm('댓글을 삭제하시겠습니까?')){
    location = 'reply_delete.bo?id=' + board_no +'&reply_no=' +no;}
 };
</script>
  </main><!-- End #main -->
