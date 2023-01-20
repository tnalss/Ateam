<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
.page-list {
	text-align: center;
	column-gap: 1rem;
}
</style>
<main id="main">

	<!-- ======= Breadcrumbs ======= -->
	<!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
	<section id="breadcrumbs" class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>사원관리</h2>
				<ol>
					<li><a href="<c:url value='/'/>">홈</a></li>
					<li>관리자</li>
					<li>사원관리</li>
				</ol>
			</div>

		</div>
	</section>
	<!-- End Breadcrumbs -->

	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<section id="blog" class="container blog">
		<!-- 섹션의 id와 class는 알아서 지정해주세요 -->
		<!-- 실질적으로 내용이 들어가는 부분 -->


		<div class="row mx-0">
			<div class="col-sm-5 p-md-0" data-aos="fade-up">
				<div class="card">
					<div class="card-body p-3 ">
						<span style="color: black; margin-right: 100px;"><b>총
								사원 수</b></span> <span style="color: black; margin-right: 50px;"><b>${ page.totalList-countRetired }</b>명(재직)</span>
						<span style="color: black;"> 퇴사자: <span> ${ countRetired }</span>명
						</span>
					</div>
				</div>

			</div>
			<div
				class="col-sm-7 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex"
				data-aos="fade-left">
				<button type="button" class="btn btn-primary"
					onclick="location='new.emp'">신규 사원 등록</button>
			</div>


		</div>

		<!-- 검색 -->
		<form method='post' action='list.emp' id="list">
			<div id='list-top' class=" mt-3" >

				<select class='w-px100' name='search'>
					<option value='all' ${page.search eq 'all' ? 'selected':''}>전체</option>
					<option value='emp_no' ${page.search eq 'emp_no' ? 'selected':''}>사번</option>
					<option value='emp_name'
						${page.search eq 'content' ? 'selected':''}>이름</option>
				</select> <input type='text' class='w-px300' name='keyword'
					value='${page.keyword}'>

				<button type="button" class="btn btn-primary btn-search">
					검색</button>


			</div>
			<input type='hidden' name='curPage' value='1'>
		</form>



		<div class="row mt-3">
			<div class="col-12 card">
				<div class="card-header">
					<h3 class="card-title text-center" style="font-weight: bold;">사원
						목록</h3>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-hover table-responsive-sm"
							style="color: black; text-align: center;">
							<thead>
								<tr>
									<th scope="col" width="50px">사번</th>
									<th scope="col" width="100px">사원명</th>
									<th scope="col" width="100px">지점</th>
									<th scope="col" width="150px">부서</th>
									<th scope="col" width="120px">직위</th>
									<th scope="col" width="180px">이메일</th>
									<th scope="col" width="130px">입사일</th>
									<th scope="col" width="120px">상태</th>

								</tr>
							</thead>
							<tbody>
								<c:if test='${empty page.list}'>
									<tr>
										<td colspan='8'>검색결과가 없습니다.</td>
									</tr>
								</c:if>
								<c:forEach var="vo" items="${ page.list }">
									<tr>
										<td class='text-center'>${vo.emp_no }</td>
										<td><a href='info.emp?id=${vo.emp_no}'>${vo.emp_name }</a></td>
										<td>${vo.branch_name }</td>
										<td>${vo.department_name}</td>
										<td>${vo.rank_name }</td>
										<td>${vo.email }</td>
										<td>${vo.hire_date }</td>
										<td style="color: #f03c02;"><c:if
												test="${ vo.admin eq 'X0' }">퇴사 </c:if></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
						<jsp:include page="/WEB-INF/views/include/page.jsp" />

					</div>
				</div>
			</div>
		</div>

	</section>
	<!-- End Section -->



</main>
<!-- End #main -->
<script>
	$('.btn-search').click(function() {
		$('#list').submit()
	});
</script>


