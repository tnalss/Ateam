<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<style>
select {
	margin-top: 10px;
}
</style>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->

<main id="main">

	<!-- ======= Breadcrumbs ======= -->
	<!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
	<section id="breadcrumbs" class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>문서대장</h2>
				<ol>
					<li><a href="<c:url value='/'/>">홈</a></li>
					<li>문서대장</li>
				</ol>
			</div>

		</div>
	</section>
	<!-- End Breadcrumbs -->

	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<section id="" class="container">
		<!-- 섹션의 id와 class는 알아서 추가 지정해주세요 -->
		<!-- 실질적으로 내용이 들어가는 부분 -->
		<div class="row">
			<div class="col-12 col-lg-3">
				<div class="card">
					<div class="card-body">
						<div class="d-grid">
							<a href="write.ea" class="btn btn-primary">+ 새 상신 작성</a>
						</div>
						<h5 class="my-3">전자 결재</h5>
						<div class="fm-menu">
							<div class="list-group list-group-flush">
								<a href="draft.ea" class="list-group-item py-1"><i
									class="bx bx-folder me-2"></i><span>기안함</span></a> <a
									href="sign.ea" class="list-group-item py-1"><i
									class="bx bx-devices me-2"></i><span>결재함</span></a> <a
									href="retry.ea" class="list-group-item py-1"><i
									class="bx bx-analyse me-2"></i><span>회수함</span></a> <a
									href="javascript:;" class="list-group-item py-1"><i
									class="bx bx-plug me-2"></i><span>공란함</span></a> <a
									href="javascript:;" class="list-group-item py-1"><i
									class="bx bx-trash-alt me-2"></i><span>참조함</span></a> <a
									href="javascript:;" class="list-group-item py-1"><i
									class="bx bx-image me-2"></i><span>부서수신함</span></a> <a
									href="document.ea" class="list-group-item py-1"><i
									class="bx bx-file me-2"></i><span>문서대장</span></a>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<h5 class="mb-0 text-secondary font-weight-bold">
							21건<span class="float-end text-primary">2건</span>
						</h5>
						<p class="mb-0 mt-2">
							<span class="text-secondary">결재완료</span><span
								class="float-end text-primary">결재 대기</span>
						</p>
						<div class="progress mt-3" style="height: 7px;">
							<div class="progress-bar" role="progressbar" style="width: 15%"
								aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
							<div class="progress-bar bg-warning" role="progressbar"
								style="width: 30%" aria-valuenow="30" aria-valuemin="0"
								aria-valuemax="100"></div>
							<div class="progress-bar bg-danger" role="progressbar"
								style="width: 20%" aria-valuenow="20" aria-valuemin="0"
								aria-valuemax="100"></div>
						</div>
						<div class="mt-3"></div>
						<div class="d-flex align-items-center">
							<div class="flex-grow-1 ms-2">
								<h6 class="mb-0">휴가신청</h6>
								<p class="mb-0 text-secondary">3건</p>
							</div>
							<h6 class="text-primary mb-0">0건</h6>
						</div>
						<div class="d-flex align-items-center mt-3">
							<div class="flex-grow-1 ms-2">
								<h6 class="mb-0">지원요청</h6>
								<p class="mb-0 text-secondary">13건</p>
							</div>
							<h6 class="text-primary mb-0">1건</h6>
						</div>
						<div class="d-flex align-items-center mt-3">
							<div class="flex-grow-1 ms-2">
								<h6 class="mb-0">어떤 문서</h6>
								<p class="mb-0 text-secondary">458건</p>
							</div>
							<h6 class="text-primary mb-0">0건</h6>
						</div>
						<div class="d-flex align-items-center mt-3">
							<div class="flex-grow-1 ms-2">
								<h6 class="mb-0">무슨 문서</h6>
								<p class="mb-0 text-secondary">57건</p>
							</div>
							<h6 class="text-primary mb-0">1건</h6>
						</div>
					</div>
				</div>
			</div>

			<div class="col-12 col-lg-9">
				<div class="div1">
					<div class="container">
						<div class="row">
							<div class="col-2">
								<ul>
									<li><select name='search' class='w-px100'>
											<option value='all'>전체</option>
											<option value='title'>제목</option>
											<option value='content'>내용</option>
											<option value='writer'>작성자</option>
									</select></li>
								</ul>
							</div>
							<div class="col-7">
								<input class="form-control-lg d-inline float-left" type="text"
									name="Youtube-url" required="" placeholder="검색할 단어를 입력하세요."
									style="width: 100%; height: 36px;">
							</div>
							<div class="col">
								<button class="btn btn-primary" type="button"
									style="width: 150px; height: 36px;">
									검색  <i class="icon ion-ios-arrow-thin-down"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<c:forEach items='${flist}' var='vo'>
					<div class="div1">
						<div class="container">
							<div class="row">
								<div class="col-1 d-inline float-none align-self-center">
									<p>1</p>
								</div>
								<div class="col-8">
									<p>
										<strong>${vo.file_name}</strong>
									</p>
									<p>파일명 | 2023-01-22</p>
								</div>
								<div class="col-1 align-self-center">
									<button class="btn btn-primary" type="button"
										style="width: 150px; height: 36px;">
										다운로드  <i class="icon ion-ios-arrow-thin-down"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- End Section -->



</main>
<!-- End #main -->
