<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h3>사원정보</h3>
			<table class='w-px600'>
				<tr>
					<th class='w-px140'>사번</th>
					<td>${vo.emp_no}</td>
				</tr>
				<tr>
					<th>사원명</th>
					<td>${vo.emp_name }</td>
				</tr>
				<tr>
					<th>근무 날짜</th>
					<td>${vo.attend_date}</td>
				</tr>
				
				<tr>
					<th>부서</th>
					<td>${vo.department_name }</td>
				</tr>
				<tr>
					<th>직급</th>
					<td>${vo.rank_name}</td>
				</tr>
				<tr>
					<th>신청 날짜</th>
					<td><fmt:formatNumber value="${vo.al_reg_date }" /></td>
				</tr>
				
			</table>
			<div class='btnSet'>
				<a class='btn-fill' href='list.hr'>사원목록</a> <a class='btn-fill'
					href='modify.hr?id=${vo.employee_id}'>정보수정</a> <a
					class='btn-fill btn-delete'>정보삭제</a>
			</div>
			<script>
				$('.btn-delete').click(function() {
					if (confirm('사원 [${vo.name}] 를 삭제?')) {
						location = 'delete.hr?id=${vo.employee_id }';
					}
				});
				// $('.btn-delete').on('click',function(){
				// });
				// $(document).on('click','.btn-delete',function(){
				// });
				</section>

				</main>
				</body>
				</html>
</body>
</html>