<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->

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


		<div class="row mx-0" >
			<div class="col-sm-5 p-md-0" data-aos="fade-up">
				<div class="card">
					<div class="card-body">
						<span style="color: black; margin-right: 100px;"><b>총
								사원 수</b></span> <span style="color: black; margin-right: 50px;"><b>${ countAll-countRetired }</b>명(재직)</span>
						<span style="color: black;"> 퇴사자: <span> ${ countRetired }</span>명
						</span>
					</div>
				</div>

			</div>
			<div
				class="col-sm-7 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex" data-aos="fade-left">
				<button type="button" class="btn btn-primary"
					onclick="location='new.emp'">신규 사원 등록</button>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-12 card">
				<div class="card-header">
					<h3 class="card-title text-center" style="font-weight: bold;">사원 목록</h3>
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
								<c:forEach var="vo" items="${ list }">
									<tr>
										<td class='text-center'>${vo.emp_no }</td>
										<td><a href='info.emp?id=${vo.emp_no}'>${vo.emp_name }</a></td>
										<td>${vo.branch_name }</td>
										<td>${vo.department_name}</td>
										<td>${vo.rank_name }</td>
										<td>${vo.email }</td>
										<td>${vo.hire_date }</td>
										<td style="color:#f03c02;"><c:if test="${ vo.admin eq 'X0' }">퇴사 </c:if></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</section>
	<!-- End Section -->



</main>
<!-- End #main -->






