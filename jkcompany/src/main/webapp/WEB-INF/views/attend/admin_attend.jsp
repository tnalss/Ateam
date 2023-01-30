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
	margin-top: 20px;
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
	background: none;
	padding: 10px;
	margin: -1px;
	background: #f03c02;
	color: #fff;
	border-radius: 4px;
	line-height: 0;
	border: 0;
}

.btn-edit {
	padding: 10px;
	margin: -1px;
	background: #f03c02;
	color: #fff;
	border-radius: 4px;
	line-height: 0;
	border: 0;
}

#btn {
	border-color: #fff;
	background-color: #fd5c28;
}

#list-top {
	padding-top: 5px;
	padding-bottom: 5px;
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

			<div id="tt"
				class="col-10  col-lg-3 mb-5 mb-lg-0 aos-init aos-animate padding_"
				data-aos="fade-right">
				<ul class="nav nav-tabs  flex-column" role="tablist">
					<li class="nav-item" role="presentation"><a
						class="nav-link  active show" data-bs-toggle="tab" href="#tab-1"
						aria-selected="true" role="tab" tabindex="-1"
						style="margin-top: 17px;">
							<h4>근태 관리</h4>
							<p>전체 사원의 근태 상황을 확인하고 수정할 수 있습니다.</p>
					</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link show" data-bs-toggle="tab" href="#tab-2"
						aria-selected="false" role="tab" style="margin-top: 17px;">
							<h4>연차 관리</h4>
							<p>업무 상태의 수정 신청 현황을 확인할 수 있습니다.</p>
					</a></li>
				</ul>
			</div>



			<div class="col-12 col-lg-9 ml-auto aos-init aos-animate"
				data-aos="fade-left" data-aos-delay="100">
				<div id="t" class="tab-content">
					<div class="tab-pane active show" id="tab-1" role="tabpanel">
						<figure>
							<form method='post' action='admin_attend.at' id="list">
								<input type='hidden' name='curPage' value='1'>
								<div class="row justify-content-center aos-init aos-animate"
									data-aos="fade-up" style="justify-content: center;"></div>
								<div class="row" style="margin-top: 20px;">
									<div class="col-2">
										<select class="form-select" name='search'
											aria-label="Default select example">
											<option value="all">전체</option>
											<c:forEach items="${branches}" var="b">
												<option value="${b.code}"
													<c:if test ="${b.code eq page.search}">selected="selected"</c:if>>${b.code_value}</option>
											</c:forEach>

										</select>
									</div>
									<div class="col-3">
										<select class="form-select" name='search_dept'
											aria-label="Default select example">
											<option value="-1">부서별</option>
											<c:forEach items="${departments}" var="d">
												<option
													<c:if test ="${d.code eq page.search_dept}">selected="selected"</c:if>
													value="${d.code}">${d.code_value}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-2 ">
										<select class="form-select" name='search_rank'
											aria-label="Default select example">
											<option value="-1">직급별</option>
											<c:forEach items="${ranks}" var="r">
												<option
													<c:if test ="${r.code eq page.search_rank}">selected="selected"</c:if>
													value="${r.code}">${r.code_value}</option>
											</c:forEach>
										</select>
									</div>
									<div id="list-top" class="col-5">
										<input type="text" class="w-px270" name="keyword" value=""
											placeholder="사원의 이름을 입력하세요">
										<button id="btn-search" type="submit"
											style="margin-left: 5px;">
											검색 <i class="bi bi-search"></i>
										</button>
									</div>
								</div>
							</form>
							<div class="row mt-3">
								<div class="col-12 card p-0">
									<div class="card-body p-0">
										<div class="table-responsive">
											<table class='table table-hover text-center'>
												<colgroup>
													<col width='80px'>
													<col width='100px'>
													<col width='120px'>
													<col width='100px'>
													<col width='100px'>
													<col width='80px'>
													<col width='80px'>
													<col width='130px'>
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
														<td><a href='attend_info.at?id=${vo.emp_no}'>${vo.emp_name}</a></td>
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
					<div class="tab-pane  " id="tab-2" role="tabpanel">
						<figure>

							<!--수정신청 조회 화면 -->
							<div class="row">
								<!--연차 신청 건수   -->
								<div class="col-lg-4 col-md-6">
									<div class="icon-box aos-init aos-animate" data-aos="fade-up"
										data-aos-delay="200">

										<div class="row">
											<p class="col-6">연차 신청 :</p>
											<p class="col-2" style="color: #fd5c28;">${countV0}</p>
											<p class="col-1">건</p>
										</div>
									</div>
								</div>
								<!--반차 신청 건수  -->
								<div class="col-lg-4 col-md-6">
									<div class="icon-box aos-init aos-animate" data-aos="fade-up"
										data-aos-delay="200">
										<div class="row">
											<p class="col-6">반차 신청 :</p>
											<p class="col-2" style="color: #fd5c28;">${countV1}</p>
											<p class="col-1">건</p>
										</div>
									</div>
								</div>
								<!--기타 신청 건수  -->
								<div class="col-lg-4 col-md-6">
									<div class="icon-box aos-init aos-animate" data-aos="fade-up"
										data-aos-delay="200">
										<div class="row">
											<p class="col-6">기타 신청 :</p>
											<p class="col-2" style="color: #fd5c28;">${countOthers}</p>
											<p class="col-1">건</p>
										</div>
									</div>
								</div>
							</div>
							<!--신청 목록 화면 보이게 -->
							<!--클릭시  사원의 출근 날짜, 시간, 현황 보이게 하고 W99(신청)에서 선택 코드로 바꾸는 업데이트처리  -->
							<div class="row mt-3">
								<div class="col-12 card p-0">
									<div class="card-body p-0">
										<div class="table-responsive">
											<table class='table table-hover text-center'>
												<colgroup>
													<col width='90px'>
													<col width='120px'>
													<col width='120px'>
													<col width='100px'>
													<col width='100px'>
													<col width='120px'>
													<col width='100px'>

												</colgroup>
												<tr class='text-center'>
													<th>지점명</th>
													<th>부서명</th>
													<th>직급명</th>
													<th>이름</th>
													<th>신청 날짜</th>
													<th>신청 내용</th>
													<th>승인 상태</th>
												</tr>										
												<c:forEach items='${page_al.al_list}' var='vo2'>
													<tr style="margin: 20px;">
														<td>${vo2.branch_name}</td>
														<td>${vo2.department_name}</td>
														<td>${vo2.rank_name}</td>
														<td><a href='al_info.at?id=${vo2.emp_no}'>${vo2.emp_name}</a></td>
														<td>${vo2.al_reg_date}</td>											
														<c:choose>
															<c:when test="${vo2.al_code eq 'V0'}">
																<td>연차</td>
															</c:when>
															<c:when test="${vo2.al_code eq 'V1'}">
																<td>반차</td>
															</c:when>
														</c:choose>
														<c:choose>
															<c:when test="${vo2.al_approved eq 'false'}">
																<td>미승인</td>
															</c:when>
															<c:when test="${vo2.al_approved eq 'true'}">
																<td>승인</td>
															</c:when>
														</c:choose>												

													</tr>
												</c:forEach>

											</table>
											<jsp:include page="/WEB-INF/views/include/page.jsp" />
										</div>
									</div>

								</div>
							</div>
							</form>



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
	$('.btn-search').on('click', function() {
		$('form').attr('action', '');
		$('form').submit();
	});

	$('[name=search]').on('change', function() {
		$('#list').attr('action', 'admin_attend.at');
		$('#list').submit();
	});

	$('[name=search_dept]').on('change', function() {
		$('#list').attr('action', 'admin_attend.at');
		$('#list').submit();
	});

	$('[name=search_rank]').on('change', function() {
		$('#list').attr('action', 'admin_attend.at');
		$('#list').submit();
	});

	/* $('.btn-edit').on('click', function() {
		var sss = $(this).siblings('input').val();
		location.href = 'al_modify.at?id='+sss;
	}); */
</script>

