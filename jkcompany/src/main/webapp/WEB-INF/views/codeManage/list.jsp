<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
i{
color: red;
}</style>
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


		<div class="row mx-0">
			<div class="col-sm-5 p-md-0">


			</div>
			<div
				class="col-sm-7 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
				<button type="button" class="btn btn-primary"
					onclick="location='new.code'">신규 상위코드 등록</button>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-12 card p-0">
				<div class="card-header">
					<h3 class="card-title text-center" style="font-weight: bold;">상위 코드 목록</h3>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-hover table-responsive-sm"
							style="color: black; text-align: center;">
							<thead>
								<tr>
									<th scope="col" width="100px">상위코드</th>
									<th scope="col" width="200px">코드값</th>
									<th scope="col" width="200px">생성일</th>
									<th scope="col" width="150px">생성자</th>
									<th scope="col" width="20px">비고</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="vo" items="${ list }">
									<tr>
										<td class='text-center'>${vo.code_category }</td>
										<td><a href='bottomCodeList.code?code=${vo.code_category}'>${vo.code_name}</a></td>
										<td>${vo.create_date}</td>
										<td>${vo.emp_name eq null ? vo.creater : vo.emp_name }</td>
										<!-- x표시  -->
										<td>
										<c:if test="${vo.creater != 'admin'}"><i class='bi bi-x' onclick="deleteTop('${vo.code_category}')"></i></c:if>
										</td>
									</tr>
								</c:forEach>
								<tr>
								<td><input type="text" class="form-control chk" title="상위코드란" name="code_category"/></td>
								<td><input type="text" class="form-control chk" title="상위코드값란" name="code_name"/></td>
								<td class="today"></td>
								<td>${loginInfo.emp_name}</td>
								<td><i class="bi bi-plus-circle addTop"></i></td>
							
							
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
function deleteTop(code_category){
	if (confirm('하위코드까지 모두 삭제됩니다.\n계속 하시겠습니까?')) {
		location = 'deleteTop.code?id='+code_category;
	}
}

$('.addTop').click(function(){

	if (emptyCheck()){
		const regex = /^[a-z|A-Z]+$/;
		let str= $('[name=code_category]').val();
		console.log(regex.test(str));
		if(str.length>1){
			alert('길이초과');
			return;
		}
		if(!regex.test(str)){
			alert('A-Z만 입력할 수 있습니다.');
			return;
		}
		$.ajax({
			url : 'addTop.code',
			dataType:'json',
			data : {
				code_category : $('[name=code_category]').val().toUpperCase(),
				code_name : $('[name=code_name]').val(),
				emp_no : '${loginInfo.emp_no}'
			},
			success : function(response) {
				console.log(response)
				if (response) {
					location = 'list.code';
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


function getTodayType() {
	  var date = new Date();
	  return date.getFullYear() + "-" + ("0"+(date.getMonth()+1)).slice(-2) + "-" + ("0"+date.getDate()).slice(-2);
	}
	
	//console.log(typeof(getTodayType()));
	$('.today').text(getTodayType());


</script>





