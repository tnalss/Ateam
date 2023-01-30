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
										<td>${vo.attend_on}</td>
										<td>${vo.attend_off}</td>
										<td>${vo.att_state}</td>

									</tr>
								</c:forEach>
							</table>
							
						</div>
					</div>


				</div>
			</div>

		</section>


	</main>
</body>
</html>