<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="page-list">

<!-- 이전 -->
<c:if test='${page.curBlock gt 1}'>
<a onclick="toPage(1)"><i class="fa-solid fa-angles-left"></i></a>
<a onclick="toPage(${page.beginPage -page.blockPage})"><i class="fa-solid fa-chevron-left"></i></a>
</c:if>


<!--10 목록 10페이지  -->
<c:forEach var='no' begin="${page.beginPage}" end='${page.endPage}' step="1">
	<a onclick='toPage(${no});' id="a${no}">${no}</a>
</c:forEach>

<!-- 다음 -->
<c:if test='${page.curBlock < page.totalBlock}'>
<a onclick="toPage(${page.endPage+1})"><i class="fa-solid fa-chevron-right"></i></a>
<a onclick="toPage(${page.totalPage})"><i class="fa-solid fa-angles-right"></i></a>
</c:if>
</div>


<script>

/* list.jsp 상단에 히든 태그를 넣어서 현재 페이지를 날려준다!! */

function toPage( pageNum) {
	for (var i = 1 ; i <= $("#page-list").children().length ; i ++){
		if(i == pageNum){
			$('[id=a'+pageNum + "").attr('onClick' , '');
			$('[id=a'+pageNum + "").css('background-color','#99cc00');
		}else{
			$('[id=a'+pageNum + "").attr('onClick' , 'toPage(' + i + ')');
		}
	}
		$("#ea-list").remove();
        $.ajax({
           url : "${pageContext.request.contextPath}/write_sign_list.ea",
           type : 'POST',
           data : {
              'eapage' : pageNum
          },
           dataType:"json",
           success : function(data) {
        	  var a = '';
              var list = data.list;
              console.log(list);
			$.each(list, function(key, value) {
				a += '<a href="#" class="list-group-item"><input type="hidden" value="'+value.emp_no+'" /><p>'+value.department_name+' | '+value.rank_name+'|</p><small>사원번호.</small>'+value.emp_no+ '<br/>'+value.emp_name +'<input type="checkbox" class="pull-right"></a>';
				
			});
			
			$('.ea-list').html(a);
			
			
/*            var page = data.page;
              var startpage = data.startpage;
              var endpage = data.endpage;
              var boardList = data.boardList;
 */
   /*            $.each(boardList, function(key, value) {
                 console.log("data : " + boardList);
                 console.log(boardList);
                 console.log(page + "," + startpage + "," + endpage);
                 console.log("start : " + startpage);
                 console.log("end : " + endpage);
                 a += '<div class="commentArea" style="boarder-bottom:1px solid darkgray; margin-bottom: 15px;">';
                 a += '<div class="commentInfo'+value.board_re_id+'">'+'댓글번호 : '+value.board_re_id+' / 작성자 : '+value.mem_id;
                 a += '<a onclick="commentUpdate('+value.board_re_id+',\''+value.board_re_content+'\');"> 수정 </a>';
                 a += '<a onclick="commentDelete('+value.board_re_id+');"> 삭제 </a> </div>';
                 a += '<div class="commentContent'+value.board_re_id+'"> <p> 내용 : '+value.board_re_content +'</p>';
                 a += '</div></div>';
              });
              
              for (var num=startpage; num<=endpage; num++) {
                 if (num == page) {
                      a += '<a href="#" onclick="commentList('+ num + '); return false;" class="page-btn">' + num + '</a>';
                 } else {
                      a += '<a href="#" onclick="commentList(' + num + '); return false;" class="page-btn">' + num + '</a>';
                 }
              }
            $('.commentList').html(a); */
         }
      });
   }

</script>