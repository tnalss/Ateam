<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<link href="https://cdn.lineicons.com/3.0/lineicons.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/boxicons@2.0.7/css/boxicons.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script> 
<script src="https://threejs.org/examples/js/libs/stats.min.js"></script>
<style>
.margin{margin: 30px;}
.card{margin : 20px; padding: 10px;}
</style>
  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>전자결재</h2>
          <ol>
            <li><a href="<c:url value='/'/>">홈</a></li>
            <li>전자결재</li>
          </ol>
        </div>
      </div>
      <!-- ////////////////////////////////////////////////////////////////////////////////////////// -->
      
      
    </section><!-- End Breadcrumbs -->
	<section id="header" >
		
	</section>
		<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
    <!-- ======= Section ======= -->
    <section id="main" class="container">
      <!-- 섹션의 id와 class는 알아서 추가 지정해주세요 -->
      <!-- 실질적으로 내용이 들어가는 부분 -->
    
	<div class="row">
	    <div class="col-12 col-lg-3">
			<div class="card">
				<div class="card-body">
					<div class="d-grid"> <a href="write.ea" class="btn btn-primary">+ 새 상신 작성</a>
					</div>
					<h5 class="my-3">전자 결재</h5>
					<div class="fm-menu">
						<div class="list-group list-group-flush"> <a href="javascript:;" class="list-group-item py-1"><i class="bx bx-folder me-2"></i><span>기안함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-devices me-2"></i><span>결재함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-analyse me-2"></i><span>회수함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-plug me-2"></i><span>공란함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-trash-alt me-2"></i><span>참조함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-image me-2"></i><span>부서수신함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-file me-2"></i><span>문서대장</span></a>
						</div>
					</div>
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<h5 class="mb-0 text-secondary font-weight-bold">21건<span class="float-end text-primary">2건</span></h5>
					<p class="mb-0 mt-2"><span class="text-secondary">결재완료</span><span class="float-end text-primary">결재 대기</span>
					</p>
					<div class="progress mt-3" style="height:7px;">
						<div class="progress-bar" role="progressbar" style="width: 15%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
						<div class="progress-bar bg-warning" role="progressbar" style="width: 30%" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"></div>
						<div class="progress-bar bg-danger" role="progressbar" style="width: 20%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
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
			<div class="card">
					<div class="row mt-3">
						<div class="col-12 col-lg-4">
							<div class="card shadow-none border radius-15">
								<div class="card-body text-center">
									<h5 class="mt-3 mb-0 ">상신한 문서</h5>
									<p class="mb-1 mt-4"><span>10건</span>
									</p>
								</div>
							</div>
						</div>
						<div class="col-12 col-lg-4">
							<div class="card shadow-none border radius-15">
								<div class="card-body text-center">
									<h5 class="mt-3 mb-0">반려된 문서</h5>
									<p class="mb-1 mt-4"><span>0건</span>
									</p>
								</div>
							</div>
						</div>
						<div class="col-12 col-lg-4">
							<div class="card shadow-none border radius-15">
								<div class="card-body text-center">
									<h5 class="mt-3 mb-0">회수한 문서</h5>
									<p class="mb-1 mt-4"><span>3건</span>
									</p>
								</div>
							</div>
						</div>
					</div>
					<!--end row-->
					<h4 class="margin">자주쓰는 문서</h4>
					<div class="row mt-3">
						<div class="col-12 col-lg-4">
							<div class="card shadow-none border radius-15">
								<div class="card-body">
									<div class="d-flex align-items-center">
										<div class="font-30 text-primary"><i class="bx bx-file"></i>
										</div>
									</div>
									<h6 class="mb-0 text-primary">휴가신청서</h6>
									<small>바로가기</small>
								</div>
							</div>
						</div>
						<div class="col-12 col-lg-4">
							<div class="card shadow-none border radius-15">
								<div class="card-body">
									<div class="d-flex align-items-center">
										<div class="font-30 text-primary"><i class="bx bx-file"></i>
										</div>
									</div>
									<h6 class="mb-0 text-primary">외근신청서</h6>
									<small>바로가기</small>
								</div>
							</div>
						</div>
						<div class="col-12 col-lg-4">
							<div class="card shadow-none border radius-15">
								<div class="card-body">
									<div class="d-flex align-items-center">
										<div class="font-30 text-primary"><i class="bx bx-file"></i>
										</div>
									</div>
									<h6 class="mb-0 text-primary">주간업무보고서</h6>
									<small>바로가기</small>
								</div>
							</div>
						</div>
					</div>
					<!--end row-->
					<div class="d-flex align-items-center margin">
						<div>
							<h4 class="mb-0">최근 상신한 문서</h4>
						</div>
						<div class="ms-auto"><a href="javascript:;" class="btn btn-sm btn-outline-secondary">모두 보기</a>
						</div>
					</div>
					<div class="table-responsive mt-3">
						<table class="table table-striped table-hover table-sm mb-0  ">
					<thead>
								<tr>
									<th>기안번호<i class="bx bx-up-arrow-alt ms-2"></i></th>
									<th>제목</th>
									<th>날짜</th>
									<th>상태</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items='${list}' var='vo'>
								<tr>
									<td>
										<div class="font-weight-bold text-danger">${vo.ea_num }</div>
									</td>
									<td><a href='info.ea?id=${vo.ea_num}'>${vo.ea_title }</a></td>
									<td>${vo.ea_date}</td>
									<td>${vo.ea_status }</td>
									<td><i class="fa fa-ellipsis-h font-24"></i>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
    </section><!-- End Section -->

   

  </main><!-- End #main -->
