<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
.buttons {
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
									<tr style="vertical-align: middle;">
										<td class='text-center'>${top.code_category }</td>
										<td><input type="text" class="form-control chk"
											title="코드값란" value="${top.code_name }" name="code_name" /></td>
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
									<th scope="col" width="100px">상위코드</th>
									<th scope="col" width="100px">하위코드</th>
									<th scope="col" width="250px">코드값</th>
									<th scope="col" width="250px">코드값2</th>
									<th scope="col" >생성일</th>
									<th scope="col" width="150px">생성자</th>
									<th scope="col" width="80px">비고</th>
								</tr>
							</thead>
							<tbody>
							
								<c:forEach var="vo" items="${ list }">
									<tr style="vertical-align: middle;">
										<td class='text-center'>${vo.code_category }</td>
										<td class='text-center'>${vo.code_num }</td>
										<td><a href='bottomCodeInfo.code?code=${vo.code_num}'>${vo.code_value }</a></td>
										<td><a href='bottomCodeInfo.code?code=${vo.code_num}'>${vo.code_value2 }</a></td>
										<td>${vo.create_date}</td>
										<td>${vo.emp_name eq null ? vo.creater : vo.emp_name }</td>
										<td>
										<c:if test="${vo.creater != 'admin'}"><i class='bi bi-x' onclick="deleteBottom('${vo.code_category }','${vo.code_num}')"></i></c:if>
										</td>
									</tr>
								</c:forEach>
						<c:if test="${empty list}"><tr style="height:150px; vertical-align:middle"><td colspan="7">하위 코드가 없습니다.</td></tr></c:if>
						
						
						<tr style="vertical-align: middle;">
						<td>${top.code_category }</td>
						<td><input type="text" class="form-control chk" name="code_num" /></td>
						<td><input type="text" class="form-control chk" name="code_value" /></td>
						<td><input type="text" class="form-control" name="code_value2" /></td>
						<td class="today"></td>
						<td>${loginInfo.emp_name}</td>
						<td><i class="bi bi-plus-circle addBottom"></i></td>
						
						
						</tr>
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
			url : 'insertBottom.code',
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
	function getTodayType() {
	  var date = new Date();
	  return date.getFullYear() + "-" + ("0"+(date.getMonth()+1)).slice(-2) + "-" + ("0"+date.getDate()).slice(-2);
	}
	
	//console.log(typeof(getTodayType()));
	$('.today').text(getTodayType());

	
	
	

	$('.addBottom').click(function(){

		if (emptyCheck()){
			const regex = /^[0-9]+$/;
			let str= $('[name=code_num]').val();
			if(str.length>2){
				alert('길이초과');
				return;
			}
			if(!regex.test(str)){
				alert('0-99만 입력할 수 있습니다.');
				return;
			}
			$.ajax({
				url : 'addBottom.code',
				dataType:'json',
				data : {
					code_category :'${top.code_category}', 
					code_num : $('[name=code_num]').val(),
					code_value:$('[name=code_value]').val(),
					code_value2:$('[name=code_value2]').val(),
					creater : '${loginInfo.emp_no}'
				},
				success : function(response) {
					if (response) {
						location = 'bottomCodeList.code?code='+'${top.code_category}';
					} else {
						alert('오류) 중복된 코드명 또는 입력 오류');
					}
				},
				error : function(req, text) {
					alert(text + ':' + req.status);
				}
			});
		}	
	});
	
	function deleteBottom(code_category,code_num){
		if (confirm('정말로 삭제하시겠습니까?')) {
			location = 'deleteBottom.code?code_category='+code_category+'&code_num='+code_num;
		}
	}

</script>




