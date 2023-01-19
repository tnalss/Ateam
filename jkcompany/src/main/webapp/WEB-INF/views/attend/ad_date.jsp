<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
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
					<li><a href="admin_attend.at">근태 관리</a></li>
				</ol>
			</div>
		</div>
	</section>
	<!-- End Breadcrumbs -->

	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<section id="a" class="container">
		<h2 class="entry-title">날짜별 조회화면인데요.</h2>



		<div class="select-menu">
			<ul class="dropdown-active">
				<li><a href="admin_attend?id={ad_date}">날짜 선택</a></li>
				<li><a href="#">지점 선택</a></li>
				<li><a href="#">부서 선택</a></li>

			</ul>
		</div>
		<div class="search">
			<form action="">
				<input type="text">
				
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
			<c:forEach items='${ad_date}' var='vo'>
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
</main>
<!-- End #main -->
