<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<style>
.search .cta-btn {
	font-family: "Poppins", sans-serif;
	text-transform: uppercase;
	font-weight: 500;
	font-size: 14px;
	letter-spacing: 1px;
	display: inline-block;
	padding: 10px 25px;
	border-radius: 2px;
	transition: 0.4s;
	margin: 10px;
	border-radius: 4px;
	border: 2px solid #f03c02;
	color: #f03c02;
	background: #fff;
}
</style>
<main id="main">

	<!-- ======= Breadcrumbs ======= -->
	<!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
	<section id="breadcrumbs" class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>근태 관리</h2>
				<ol>
					<li><a href="<c:url value='/'/>">홈</a></li>
					<li><a href="#">근태 관리</a></li>
				</ol>
			</div>
		</div>
	</section>
	<!-- End Breadcrumbs -->

	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<section id="a" class="container">
		<h2 class="entry-title">전 사원의 출퇴근 목록을 조회 합니다.</h2>





		<!--날짜지정-->



		<div class="search">
			<form action="">
				<input type="text" name='keyword' class='w-px300' value='${keyword}'> 
				<a class="cta-btn" href="#">검색<i class="bi bi-search"></i></a>
			</form>
		</div>


		<table class='tb-list'>
			<colgroup>
				<col width='120px'>
				<col width='120px'>
				<col width='120px'>
				<col width='120px'>
				<col width='120px'>
				<col width='120px'>
				<col width='120px'>
			</colgroup>
			<tr>
				<th>날짜</th>
				<th>지점명</th>
				<th>부서명</th>
				<th>직급명</th>
				<th>사원명</th>
				<th>출근 시간</th>
				<th>퇴근 시간</th>
				<th>상태</th>
			</tr>
			<c:forEach items='${admin_attend}' var='vo'>
				<tr>
					<td>${vo.attend_date}</td>
					<td>${vo.branch_name}</td>
					<td>${vo.dept_name}</td>
					<td>${vo.rank_name}</td>
					<td>${vo.emp_name}</td>
					<td>${vo.attend_on}</td>
					<td>${vo.attend_off}</td>
					<td>${vo.att_state}</td>
				</tr>
			</c:forEach>
		</table>



	</section>
	<!-- End Section -->
$('.cta-btn').on('click', function(){
	$('form').attr('action', 'ad_date.at');
	$('form').submit();
});
</main>
<!-- End #main -->
