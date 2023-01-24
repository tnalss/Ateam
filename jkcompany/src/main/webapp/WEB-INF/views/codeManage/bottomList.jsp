<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
.buttons{
column-gap: 2rem;
}
</style>
<main id="main">

	<!-- ======= Breadcrumbs ======= -->
	<!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
	<section id="breadcrumbs" class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>코드관리</h2>
				<ol>
					<li><a href="<c:url value='/'/>">홈</a></li>
					<li>관리자</li>
					<li>상위 코드 관리</li>
					<li>하위 코드 관리</li>
				</ol>
			</div>

		</div>
	</section>
	<!-- End Breadcrumbs -->

	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<section id="" class="container">
		<!-- 섹션의 id와 class는 알아서 지정해주세요 -->
		<!-- 실질적으로 내용이 들어가는 부분 -->
		<div class="row">
			<div class="col-12 card p-0">
				<div class="card-header">
					<h3 class="card-title text-center" style="font-weight: bold;">상위
						코드 상세</h3>
				</div>
				<div class="card-body">
				<form action="updateTop.code" method="post" id="updateTop">
				<input type="hidden" name="emp_no" value="${loginInfo.emp_no }" />
					<!-- 상위코드 상세부분 -->
					<table class="table table-hover table-responsive-sm"
						style="color: black; text-align: center;">
						<thead>
							<tr>
								<th scope="col" width="50px">상위코드</th>
								<th scope="col" width="100px">코드값</th>
								<th scope="col" width="100px">생성일</th>
								<th scope="col" width="150px">생성자</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${top.creater eq 'admin' }">
								<tr>
									<td class='text-center'>${top.code_category }</td>
									<td>${top.code_name }</td>
									<td>${top.create_date}</td>
									<td>${top.emp_name eq null ? top.creater : top.emp_name }</td>
								</tr>
							</c:if>
							<c:if test="${top.creater ne 'admin' }">
								<tr>
									<td class='text-center'>${top.code_category }</td>
									<td><input type="text" class="form-control chk" title="코드값란" value="${top.code_name }" name="code_name"/></td>
									<td>${top.create_date}</td>
									<td>${top.emp_name eq null ? top.creater : top.emp_name }</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<c:if test="${top.creater ne 'admin' }">	
		<div class="row mx-0 mt-3">

			<div class="col-sm-12 d-flex justify-content-center buttons">
				<button type="button" class="btn btn-primary topModify">수정</button>
				<button type="button" class="btn btn-danger deleteTop">삭제</button>
			</div>
		</div>
		</c:if>
				</form>
				</div>
			</div>
		</div>


		<div class="row mt-3">
			<div class="col-12 card p-0">
				<div class="card-header">
					<h3 class="card-title text-center" style="font-weight: bold;">하위
						코드 목록</h3>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-hover table-responsive-sm"
							style="color: black; text-align: center;">
							<thead>
								<tr>
									<th scope="col" width="50px">상위코드</th>
									<th scope="col" width="50px">하위코드</th>
									<th scope="col" width="100px">코드값</th>
									<th scope="col" width="100px">코드값2</th>
									<th scope="col" width="100px">생성일</th>
									<th scope="col" width="150px">생성자</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="vo" items="${ list }">
									<tr>
										<td class='text-center'>${vo.code_category }</td>
										<td class='text-center'>${vo.code_num }</td>
										<td><a href='bottomCodeInfo.code?code=${vo.code_num}'>${vo.code_value }</a></td>
										<td><a href='bottomCodeInfo.code?code=${vo.code_num}'>${vo.code_value2 }</a></td>
										<td>${vo.create_date}</td>
										<td>${vo.creater}</td>
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

<script>
$('.topModify').click(function(){
	if (emptyCheck()){
		$.ajax({
			url : 'updateTop.code',
			dataType:'json',
			data : {
				code_category : '${top.code_category}',
				code_name : $('[name=code_name]').val(),
				emp_no : $('[name=emp_no]').val()
			},
			success : function(response) {
				console.log(response)
				if (response) {
					location = 'bottomCodeList.code?code='+'${top.code_category}';
				} else {
					alert('오류) 업데이트 실패');
				}
			},
			error : function(req, text) {
				alert(text + ':' + req.status);
			}
		});
	}	
});
$('.deleteTop').click(function() {
	if (confirm('하위코드까지 모두 삭제됩니다.\n계속 하시겠습니까?')) {
		location = 'deleteTop.code?id=${top.code_category}';
	}
});
</script>




