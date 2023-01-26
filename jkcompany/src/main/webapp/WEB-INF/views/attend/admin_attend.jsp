<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<style>
.page-list {
	text-align: center;
	column-gap: 1rem;
}

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

#tab-1 { 700px;	
}

.tb-list, .text-center {
	margin-top :20px;
	align-content: center;
	text-align: center;
}

.tt {
	margin-top: 30px;
	padding: 15px;
}

.tt>li {
	height: 25px;
}

.nav-link {
	color: #495057;
}

.nav !important { -
	-bs-nav-link-hover-color: #fd5c28; -
	-bs-nav-link-color: #495057;
}

.nav-link:focus, .nav-link:hover {
	color: #fd5c28;
}

.nav-link show {
	color: #fd5c28;
}

.search-form form button {
	margin-right: 10px;
	border: 0;
	background: none;
	padding: 10px;
	margin: -1px;
	background: #f03c02;
	color: #fff;
	border-radius: 4px;
	line-height: 0;
	border: 0;
}

.nav nav-tabs {
	margin-top: 40px;
}

.page-list span {
	border: 1px solid #fff;
	color: #fd5c28;
}

#btn-search {
	margin-right: 10px;
}

#btn {
	border-color: #fff;
	background-color: #fd5c28;
}

#list-top {
	
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
		<div class="row">
			<form method='post' action='admin_attend.at' id="list">
				<input type='hidden' name='curPage' value='1'>
			</form>
			<div id="tt"
				class="col-10  col-lg-3 mb-5 mb-lg-0 aos-init aos-animate padding_"
				data-aos="fade-right">
				<ul class="nav nav-tabs  flex-column" role="tablist">
					<li class="nav-item" role="presentation"><a
						class="nav-link  active show" data-bs-toggle="tab" href="#tab-1"
						aria-selected="true" role="tab" tabindex="-1"
						style="margin-top: 17px;">
							<h4>근태 조회</h4>
							<p>전체 사원의 근태 상황을 확인할 수 있습니다.</p>
					</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link show" data-bs-toggle="tab" href="#tab-2"
						aria-selected="false" role="tab" style="margin-top: 17px;">
							<h4>근태 수정</h4>
							<p>전체 사원의 근태 상황을 수정할 수 있습니다.</p>
					</a></li>
				</ul>
			</div>



			<div class="col-12 col-lg-9 ml-auto aos-init aos-animate"
				data-aos="fade-left" data-aos-delay="100">
				<div id="t" class="tab-content">
					<div class="tab-pane active show" id="tab-1" role="tabpanel">
						<figure>
							<div class="row mt-3">
								<div class="col-12 card p-0">
									<div class="card-header">
										<h3 class="card-title text-center" style="font-weight: bold;">전체
											사원 목록</h3>
									</div>
									<div class="card-body p-0">
										<div class="table-responsive">
											<table class='table table-hover text-center'>
												<colgroup>
													<col width='80px'>
													<col width='100px'>
													<col width='100px'>
													<col width='100px'>
													<col width='100px'>
													<col width='90px'>
													<col width='90px'>
													<col width='100px'>
												</colgroup>
												<tr class='text-center'>
													<th>날짜</th>
													<th>지점명</th>
													<th>부서명</th>
													<th>직급명</th>
													<th>이름</th>
													<th>출근 시간</th>
													<th>퇴근 시간</th>
													<th>상태</th>

												</tr>
												<c:if test='${empty page.list}'>
													<tr>
														<td colspan='5'>검색결과가 없습니다</td>
													</tr>
												</c:if>
												<c:forEach items='${page.list}' var='vo'>
													<tr style="margin: 20px;">
														<td>${vo.attend_date}</td>
														<td>${vo.branch_name}</td>
														<td>${vo.department_name}</td>
														<td>${vo.rank_name}</td>
														<td>${vo.emp_name}</td>
														<td>${vo.attend_on}</td>
														<td>${vo.attend_off}</td>
														<td>${vo.att_state}</td>

													</tr>
												</c:forEach>
											</table>
											<jsp:include page="/WEB-INF/views/include/page.jsp" />
										</div>
									</div>
									<div class="search-form text-end">
										<form style="margin-left: 550px; margin-bottom: 20px;"
											action="">
											<input type="text" name='keyword' class='w-px200'
												style="margin-right: 20px;" value='${keyword}'>
											<button id="btn-search" type="submit">
												<a href="#">검색 <i class="bi bi-search"></i></a>
											</button>
										</form>
									</div>

								</div>
							</div>
						</figure>
					</div>
					<div class="tab-pane  " id="tab-2" role="tabpanel">
						<figure>
							<div class="row justify-content-center aos-init aos-animate"
								data-aos="fade-up" style="justify-content: center;"
								>						
							</div>
							<div class="row" style="margin-top: 20px; margin-left: 70px;">
								<div class="col-2">
									<select class="form-select" aria-label="Default select example">
										<option selected>전체</option>
										<option value="1">One</option>
										<option value="2">Two</option>
										<option value="3">Three</option>
									</select>
								</div>
								<div class="col-2">
									<select class="form-select" aria-label="Default select example">
										<option selected>지점별</option>
										<option value="1">One</option>
										<option value="2">Two</option>
										<option value="3">Three</option>
									</select>
								</div>
								<div class="col-2 ">
									<select class="form-select" aria-label="Default select example">
										<option selected>부서별</option>
										<option value="1">One</option>
										<option value="2">Two</option>
										<option value="3">Three</option>
									</select>
								</div>

								<div id="list-top" class="col-6">
									<input type="text" class="w-px300" name="keyword" value="">
									<button type="button" class="btn btn-primary btn-search"
										id="btn">검색</button>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-12 card p-0">
									<div class="card-body p-0">
										<div class="table-responsive">
											<table class='table table-hover text-center'>
												<colgroup>
													<col width='80px'>
													<col width='100px'>
													<col width='100px'>
													<col width='100px'>
													<col width='100px'>
													<col width='90px'>
													<col width='90px'>
													<col width='100px'>
												</colgroup>
												<tr class='text-center'>
													<th>날짜</th>
													<th>지점명</th>
													<th>부서명</th>
													<th>직급명</th>
													<th>이름</th>
													<th>출근 시간</th>
													<th>퇴근 시간</th>
													<th>상태</th>

												</tr>
												<c:if test='${empty page.list}'>
													<tr>
														<td colspan='5'>검색결과가 없습니다</td>
													</tr>
												</c:if>
												<c:forEach items='${page.list}' var='vo'>
													<tr style="margin: 20px;">
														<td>${vo.attend_date}</td>
														<td>${vo.branch_name}</td>
														<td>${vo.department_name}</td>
														<td>${vo.rank_name}</td>
														<td>${vo.emp_name}</td>
														<td>${vo.attend_on}</td>
														<td>${vo.attend_off}</td>
														<td>${vo.att_state}</td>

													</tr>
												</c:forEach>
											</table>
											<jsp:include page="/WEB-INF/views/include/page.jsp" />
										</div>
									</div>


								</div>
							</div>
						</figure>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Section -->


</main>
<!-- End #main -->
<script>
function fn_submit( id ){
	$('[name=curPage]').val( ${page.curPage} );
	$('[name=id]').val( id );
	$('form').attr('action', 'info.bo');
	$('form').submit();
}

$(function(){
	$('[name=search]').val( '${page.search}' );
	$('[name=viewType]').val( '${page.viewType}' );
	
$('.btn-search').on('click', function(){
	$('form').attr('action', 'list.bo');
	$('form').submit();
});
$('[name=pageList], [name=viewType]').on('change', function(){
	$('form').attr('action', 'list.bo');
	$('form').submit();
});
</script>

