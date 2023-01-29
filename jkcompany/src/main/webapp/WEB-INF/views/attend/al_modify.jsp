<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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

		<section>
			<!-- W99(신청)에서 선택 코드로 바꾸는 업데이트처리  -->
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
									<th>빠른 수정</th>

								</tr>
								<c:forEach items='${page_al.al_list}' var='vo2'>
									<tr style="margin: 20px;">

										<td>${vo2.branch_name}</td>
										<td>${vo2.department_name}</td>
										<td>${vo2.rank_name}</td>
										<td>${vo2.emp_name}</td>
										<td>${vo2.al_reg_date}</td>
										<td>${vo2.al_code}</td>
										<c:choose>
											<c:when test="${vo2.al_approved eq 'false'}">
												<td>미승인</td>
											</c:when>
											<c:when test="${vo2.al_approved eq 'true'}">
												<td>승인</td>
											</c:when>
										</c:choose>
										<td><button id="btn-edit">수정하기</button></td>


									</tr>
								</c:forEach>
								</div>
							</table>
							<jsp:include page="/WEB-INF/views/include/page.jsp" />
						</div>
					</div>

				</div>
			</div>

		</section>

	</main>
</body>
</html>