<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<style>
#h3 {
	display: block;
	font-size: 2 rem;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	font-weight: bold;
}

#combobox2 {
	margin-top: 20px;
}

#submit {
	background: #f03c02;
	border: 0;
	padding: 10px 24px;
	color: #fff;
	transition: 0.4s;
	border-radius: 4px;
}

#inputDate {
	
}

#l:hover {
	color: #fd5c28;
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
					<li>나의 출·퇴근</li>
			</div>
			</ol>
		</div>
	</section>
	<!-- End Breadcrumbs -->

	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<section id="features" , class="features">
		<div class="container">
			<div class="row mt-5 justify-content-center aos-init aos-animate"
				data-aos="fade-up">
				<div class="col-lg-10">
					<div>
						<i class="bi bi-person"></i> <a id="me">${loginInfo.emp_name}&nbsp;</a><a
							id="me">${loginInfo.rank_name}님의</a>
					</div>
					<h2 class="entry-title">
						<a>업무 상태 수정 신청</a>
					</h2>
					<form action="forms/contact.php" method="post" role="form"
						class="php-email-form">
						<div class="row">
							<div class="col-md-3 form-group mt-2 mt-md-0">
								<input class="form-control" id="inputDate" type="date" name="date"
									placeholder="날짜 선택">
							</div>
							<div class="col-md-4 form-group mt-2 mt-md-0 ">
							<div  class="form-control status"> 업무 상태</div>
							<!-- 날짜를 선택하면 해당 날짜의 로그인한 사원의 업무 상태 뜨도록  -->							
							</div>
							
							
							<div class="col-md-2 form-group mt-2 mt-md-0">
								<select class="form-select" name='search_rank'
									aria-label="Default select example">
									<option value="-1">업무 상태</option>
									<c:forEach items="${att_code}" var="a">
										<option
											<c:if test ="${a.code eq page.search_code}">selected="selected"</c:if>
											value="${a.code}">${a.code_value}</option>
									</c:forEach>
								</select>
							</div>



							<div class="form-group mt-3">
								<input type="text" class="form-control" name="subject"
									id="subject" placeholder="제목" required="">
							</div>
							<div class="form-group mt-3">
								<textarea class="form-control" name="message" rows="5"
									placeholder="신청 사유를 입력하세요" required=""></textarea>
							</div>
							<div class="input-group mb-3" style="margin-top: 20px;">
								<input type="file" class="form-control" id="inputGroupFile02">
								<label id="l" class="input-group-text" for="inputGroupFile02">첨부하기</label>
							</div>
							<div class="my-3">
								<div class="loading"></div>
								<div class="error-message"></div>
								<div class="sent-message"></div>
							</div>
							<div class="text-center ">
								<button type="submit" id="submit">신청하기</button>
								<button type="submit" id="submit">취소하기</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</main>

<script>
$('[name=date]').on('change', function() {
	$.ajax({
	      type:"POST",
	      url:"attend_date.at",
	
	      data:{emp_no: ${loginInfo.emp_no},
	           attend_date: $('[name=date]').val()
	         },
	      dataType:"json",
	      success: function(data){
	    	  if(data != null){
	    		  
	    		  
	         $('.status').text(data.att_state);
	    	  }else{
	    		  $('.status').text("업무 기록이 없습니다.");
	    	  }
	         //console.log(data);
	         //console.log(data.att_state);
	      }
	   })
});
</script>