<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
.project .row {
	margin: 0;
	padding: 15px 0;
	margin-bottom: 15px
}
.has-shadow.text-center {
    width: 60px;
}
.project div[class*='col-'] {
	border-right: 1px solid #eee
}

.project .text h3 {
	margin-bottom: 0;
	color: #555;
	font-size: 1.0em;
}

.project .text {
	margin-left: 20px;
}

.project .text small {
	color: #aaa;
	font-size: 0.75em;
}

.project .project-date span {
	font-size: 0.9em;
	color: #999
}

.project .time, .project .comments, .project .project-progress {
	color: #999;
	font-size: 0.9em;
	margin-right: 20px
}

.project .time i, .project .comments i, .project .project-progress i {
	margin-right: 5px
}

.project .project-progress {
	width: 200px
}

.project .project-progress .progress {
	height: 4px
}

.project .card {
	margin-bottom: 0
}

@media ( max-width : 991px) {
	.project .right-col {
		margin-top: 20px;
		margin-left: 65px
	}
}

.has-shadow {
	-webkit-box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1), -1px 0 2px
		rgba(0, 0, 0, 0.05);
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1), -1px 0 2px
		rgba(0, 0, 0, 0.05);
	margin: 5px;
}
.project>.has-shadow:hover, .project>.has-shadow:focus {
	box-shadow: 2px 2px 2px rgba(20, 120, 130, 0.4), -1px 0 2px
		rgba(20, 120, 130, 0.4) !important;
}
</style>
<main id="main">

	<!-- ======= Breadcrumbs ======= -->
	<!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
	<section id="breadcrumbs" class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>결재함</h2>
				<ol>
					<li><a href="<c:url value='/'/>">홈</a></li>
					<li>전자결재</li>
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
									class="bx bx-folder me-2"></i><span>상신함</span></a> <a
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
				<c:forEach items='${sign_list}' var='vo'>
					<div class="project">
						<div class="row bg-white has-shadow">
							<div class="left-col col-lg-8 d-flex align-items-center justify-content-between"  onclick="info('${vo.ea_num}')">
								<div class="project-title d-flex align-items-center">
									<div class="has-shadow text-center">
										<small>${vo.ea_r_statuas}</small>
									</div>
									<div class="text">
										<small>기안번호 ${vo.ea_num}</small>
										<h3 class="h4">${vo.ea_title}</h3>
									</div>
								</div>
								<div class="project-date">
									<span class="hidden-sm-down">${vo.ea_date}</span>
								</div>
							</div>
							<div class="right-col col-lg-4 d-flex align-items-center">
								<div class="time">
									<span class="hidden-sm-down">${vo.ea_receiver_dep}</span>
								</div>
								<div class="comments">
									<span class="hidden-sm-down">${vo.ea_receiver_rank}</span>
								</div>
								<div class="name">
									<span class="hidden-sm-down">${vo.emp_name}</span>
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
<script>
function info(c){
	location.href = "info.ea?ea_num="+c+"&&cnt=2";
}
</script>
<!-- End #main -->
