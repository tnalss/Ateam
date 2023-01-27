<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 글자 수 자르기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				<h2>공지사항</h2>
				<ol>
					<li><a href="<c:url value='/'/>">홈</a></li>
					<li>관리자</li>
					<li>공지사항</li>
				</ol>
			</div>

		</div>
	</section>
	
	<!-- End Breadcrumbs -->

	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<!-- End Breadcrumbs -->

	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<section id="" class="container">
	
		<div id='list-top'>
			<button type="button" class="btn btn-primary mb-3"
				onclick="location='new.no'">글 작성</button>
		</div>
		
		<!-- 검색 -->
		<form method='post' action='list.no' id="list">
			<div id='list-top mt-3' class='w-px1200'>

				<select class='w-px100' name='search'>
					<option value='all' ${page.search eq 'all' ? 'selected':''}>전체</option>
					<option value='board_title' ${page.search eq 'board_title' ? 'selected':''}>제목</option>
					<option value='emp_name'
						${page.search eq 'content' ? 'selected':''}>작성자</option>
				</select> <input type='text' class='w-px300' name='keyword'
					value='${page.keyword}'>

				<button type="button" class="btn btn-primary btn-search">
					검색</button>
			</div>
			<input type='hidden' name='curPage' value='1'>
		</form>
		
	<!-- 리스트 -->
		<form method='post' action='list.no' id="list">
		<input type='hidden' name='curPage' value='1'> 
		</form>
		<div class="row mt-3 ">
			<div class="col-12 card p-0">
				<div class="card-header">
					<h3 class="card-title text-center" style="font-weight: bold;">공지사항</h3>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class='table table-hover'>
			<colgroup>
				<col width='80px'>
				<col class="no_title" >
				<col width="250px">
				<col width='120px'>
				<col width='120px'>
			</colgroup>
			<tr class='text-center '> 
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			<c:forEach items='${page.list}' var='vo'>
				<tr class='text-center '>
					<td class='text-center'>${vo.rnum }</td>
					<td class="no_title"><a href='info.no?id=${vo.board_no}'>${vo.board_title }</a></td>
					<td >${vo.emp_name}</td> 
					<td><fmt:formatDate pattern="yyyy/MM/dd"
							value="${vo.write_date }" /></td>
							<td>${vo.board_hits}</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/views/include/page.jsp" />

					</div>
				</div>
			</div>
		</div>

		
	</section>
	<!-- End Section -->

<script>
$('.btn-search').click(function() {
	$('#list').submit()
});
</script>

</main>
<!-- End #main -->
