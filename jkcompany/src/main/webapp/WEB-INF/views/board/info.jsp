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
.h-px500 {
height: 400px;
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
  <div class="card-body h-px500">
    <p class="card-text text-start" >${fn: replace(  fn:replace( vo.board_content, lf, '<br>' )  , crlf, '<br>')}</p>
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
	<a class='btn btn-primary reply_btn'>답글쓰기</a>
	</div>
	</form>
	</c:if>
</div>

	<!-- 댓글 -->
	<div id='reply.no' class="mt-4">
   <p style='font-size: 22px;'>댓글</p>
   <div class="row">
   <c:forEach items='${board }' var='reply'  varStatus='num'>
<form action="reply_update.bo" method="post" id="updateReply${num.count }">
    <div class="col-12 mt-2">
          <div class="card">
            <div class="row no-gutters">
             <div class="col-2">
               <img src="assets/img/user_profile.png" class="img-fluid p-2 profile" alt="">
              </div>
               <div class="col-10">
                <div class="card-body">
                <p class="card-text  reply_content${ num.count}" style="margin-left: -30px;">${reply.reply_content } </p>
                	<input type="hidden" name="reply_no" value="${reply.reply_no }"/>
                	<input type="hidden" name="board_no" value="${ vo.board_no}"/>
                <input type="text" class="w-px800 h-px80 reply_incontent${ num.count}" name="reply_content" value="${reply.reply_content }" style="display: none; margin-left: -30px;"/>
                </div>
                <div class="text-end" style='margin: 10px;'>
                 <p class="card-text"><fmt:formatDate pattern="yyyy/MM/dd"
							value="${reply.reply_create_date}" /></p>
					<!-- 수정 전 화면 -->		
				<c:if test='${loginInfo.emp_no eq reply.emp_no}'>
				<div id="modify_off${num.count }" >			
                <a class='btn btn-primary modify_reply' href="javascript:modify_reply(${num.count})">수정</a>
				<a class='btn btn-danger' href="javascript:delete_reply(${reply.reply_no},${vo.board_no })">삭제</a>
				</div>
				</c:if>
				<div id="modify_on${num.count }" style="display: none">
				<!-- 수정 화면 -->		
					<a class='btn btn-primary'  href="javascript:modify_ok(${num.count})">저장</a>
				<a class='btn btn-danger' href="javascript:modify_cancel(${num.count})">취소</a>
				</div>
				</div>
              </div>
            </div>
          </div>
        </div>
</form>
          </c:forEach>
        </div>  
          </div>
    </section><!-- End Section -->
   
<script type="text/javascript">
//수정 취소
function modify_cancel(no){
	$('#modify_on'+no).css({"display":"none"});
	$('#modify_off'+no).css({"display":"block"});
	$('.reply_incontent'+no).css({"display":"none"});
	$('.reply_content'+no).css({"display":"block"});
}

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
function modify_reply(no){
	$('#modify_on'+no).css({"display":"block"});
	$('#modify_off'+no).css({"display":"none"});
	$('.reply_content'+no).css({"display":"none"});
	$('.reply_incontent'+no).css({"display":"block"});
}
function modify_ok(no){
	$('#modify_on'+no).css({"display":"none"});
	$('#modify_off'+no).css({"display":"block"});
	$('.reply_incontent'+no).css({"display":"none"});
	$('.reply_content'+no).css({"display":"block"});
	$('.reply_content'+no).text($('.reply_incontent'+no).val());
	$('#updateReply'+no).submit();
}
</script>
  </main><!-- End #main -->
