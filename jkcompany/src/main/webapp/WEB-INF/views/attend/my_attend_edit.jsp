<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	width: 100px;
	height: 30px;
	background: #fd5c28;
	color: #fff;
	border: #fff;
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
							<div class="col-md-2 form-group ">
								<select class="form-select" aria-label="Default select example"
									id="combobox1">
									<option selected>지점 선택</option>
									<option value="1">One</option>
									<option value="2">Two</option>
									<option value="3">Three</option>
								</select>
							</div>
							<div class="col-md-2 form-group ">
								<select class="form-select" aria-label="Default select example"
									id="combobox1">
									<option selected>부서 선택</option>
									<option value="1">One</option>
									<option value="2">Two</option>
									<option value="3">Three</option>
								</select>
							</div>
							<div class="col-md-3 form-group ">
								<input type="number" class="form-control" name="emp_no" id="no"
									placeholder="사번을 입력하세요" required="">
							</div>
							<div class="col-md-4 form-group mt-3 mt-md-0">
								<input type="name" class="form-control" name="name" id="name"
									placeholder="이름을 입력하세요" required="">
							</div>
						</div>
						<div class="col-md-2 form-group mt-2 mt-md-0">
							<select class="form-select" aria-label="Default select example"
								id="combobox2">
								<option selected>업무 상태</option>
								<option value="1">One</option>
								<option value="2">Two</option>
								<option value="3">Three</option>
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
					</form>
				</div>
			</div>
		</div>
	</section>
</main>