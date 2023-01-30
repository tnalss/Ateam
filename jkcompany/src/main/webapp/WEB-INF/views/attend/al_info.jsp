<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<style>
#btn-edit {
	margin-top: 5px;
	padding: 15px;
	margin: -1px;
	background: #f03c02;
	color: #fff;
	border-radius: 4px;
	line-height: 0;
	border: 0;
	padding: 15px;
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
					<li><a href="admin_attend.at">근태 관리</a></li>
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
						<table class='table table-hover text-center'
							style='margin: 0 auto; max-width: 1200px;'>
							<colgroup>
								<col width="150px">
								<col width="150px">
								<col width="100px">
								<col width="200px">
								<col width="150px">
							</colgroup>
							<tr class='text-center'>
								<th>신청 날짜</th>
								<th>사용 날짜</th>
								<th>구분</th>
								<th>승인상태</th>
								<th>업무상태 변경</th>
							</tr>
							<c:forEach items='${info}' var='vo'>
								<tr style="margin: 20px;">
									<td>${vo.al_reg_date}</td>
									<td>${vo.al_start_date}</td>
									<td>${vo.al_code_value}</td>
									<td>
									<c:choose>
										<c:when test="${vo.al_approved eq 'false'}">
											미승인
										</c:when>
										<c:when test="${vo.al_approved eq 'true'}">
											승인
										</c:when>
									</c:choose>
									</td>
									<td><select class="form-select" name='search_att'
										aria-label="Default select example">
											<option value="-1">승인 처리</option>
											<c:forEach items="${al}" var="a">
												<option
													<c:if test ="${vo.al_approved eq a.code}">selected="selected"</c:if>
													value="${a.code}">${a.code_value eq 'false' ? '미승인' : '' }${a.code_value eq 'true' ? '승인' : '' }</option>
											</c:forEach>
									</select>
									</td>
								</tr>
							</c:forEach>
						
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>

</main>
<script>
$('[name=search_att]').on('change', function(){	
	console.log( 'option:selected>' ,$(this).children('option:selected').val() )
	var code = $(this).children('option:selected').val();
	

	$.ajax({
		url: 'updateAlCode.at',
		data: { emp_no: ${param.id}, al_start_date: $(this).closest('tr').children('td:eq(0)').text(), al_approved:code },
		dataType : "json",		
		success: function (response){
				console.log(response)
			
			if( response ){
				history.go(0);
			}else
				alert('상태변경 오류')
		}

	})

})



</script>