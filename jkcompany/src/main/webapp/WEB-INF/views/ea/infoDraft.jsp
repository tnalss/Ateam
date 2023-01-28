<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
table{
	margin-top: 20px;
}
.table-light{
	color: #999;
}
table th:first-child,
table td:first-child {
	border-left: 0;
}
table th:last-child,
table td:last-child {
	border-right: 0;
}
h5{
	margin-top: 4rem;
}
.card-deck .card{
	margin: 2rem;
}
card-deck .card-title{
	margin-top: .7rem;
}
</style>
<main id="main">

	<!-- ======= Breadcrumbs ======= -->
	<!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
	<section id="breadcrumbs" class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>상신함</h2>
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
				<h3>${map["title"]}</h3>
				<table class="table table-bordered">
						<tr>
							<td class="table-light">기안양식</td>
 							<td>${map["form"]}</td> 
							<td class="table-light">문서번호</td>
							<td>${map["ea_num"]}</td>
						</tr>
						<tr>
							<td class="table-light">보존연한</td>
 							<td>2년</td> 
							<td  class="table-light">공개여부</td>
							<td>부서공개</td>
						</tr>
						<tr>
							<td class="table-light">기안자</td>
 							<td>${map["emp_name"]}</td> 
							<td class="table-light">기안부서</td>
							<td>${map["emp_dep"]}</td>
						</tr>
				</table>
				
				<h5>결재선</h5><hr>
				
				 <div class="card-deck row">
                	<div class="card text-center col-3">
                    	<div class="card-block">
                        	<p class="card-title">기안</p>
                        	<hr>
                        	<p class="card-text">
                           		${map["emp_name"]}<br />
                           		(${map["rank_name"]})<br />
                            	${map["emp_dep"]}<br />
                       	 </p>
                   	 </div>
                    	<div class="card-footer">
                       	 	${map["date"]}
                    	</div>
                	</div>
				<c:forEach items="${info_list}" var="list">
                	<div class="card text-center col-3">
                    	<div class="card-block">
                        	<p class="card-title">결재</p>
                        	<hr>
                        	<p class="card-text">
                           		${list.ea_receiver_name}<br />
                           		(${list.ea_receiver_rank})<br />
                            	${list.ea_receiver_dep}<br />
                       	 </p>
                   	 </div>
                    	<div class="card-footer">
 							${list.ea_r_statuas eq '결재완료' ? list.ea_a_date :''}
 							<p>${list.ea_r_statuas}<p>
                    	</div>
                	</div>
				</c:forEach>
				</div>
				<h5>기안내용</h5><hr>
				<table class="table table-bordered">
					<tr>
						<td class="table-light">기안내용</td>
 						<td class="col-10">${map["content"]}</td>
					</tr>
					<tr>
						<td class="table-light">파일첨부</td>
 						<td></td>  
					</tr>
				</table>
			</div>
		</div>


	</section>
	<!-- End Section -->



</main>
<!-- End #main -->
