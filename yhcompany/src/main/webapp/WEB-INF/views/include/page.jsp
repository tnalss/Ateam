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
<c:if test='${page.curPage eq no}'><span>${no}</span></c:if>
<c:if test='${page.curPage ne no}'><a onclick='toPage(${no});'>${no}</a></c:if>
</c:forEach>

<!-- 다음 -->
<c:if test='${page.curBlock < page.totalBlock}'>
<a onclick="toPage(${page.endPage+1})"><i class="fa-solid fa-chevron-right"></i></a>
<a onclick="toPage(${page.totalPage})"><i class="fa-solid fa-angles-right"></i></a>
</c:if>
</div>


<script>
function toPage(no){
	$('[name=curPage]').val(no);
	$('form').submit();
}
/* list.jsp 상단에 히든 태그를 넣어서 현재 페이지를 날려준다!! */




</script>