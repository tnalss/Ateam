<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>


<main id="main">

	<!-- ======= Breadcrumbs ======= -->
	<!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
	<section id="breadcrumbs" class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>메뉴이름</h2>
				<ol>
					<li><a href="<c:url value='/'/>">홈</a></li>
					<li>관리자</li>
					<li>사원관리</li>
					<li>상세정보</li>
				</ol>
			</div>

		</div>
	</section>
	<!-- End Breadcrumbs -->
	<%-- 
	<!-- ======= Section ======= -->
	<section id="" class="container">
		<!-- 섹션의 id와 class는 알아서 추가 지정해주세요 -->
		<!-- 실질적으로 내용이 들어가는 부분 -->

		<h2 class="text-center">${vo.emp_name}님의 상세 정보</h2>
		<table class="table">
			<tbody>
				<tr>
					<th scope="col" class="text-center">사번</th>
					<td>${vo.emp_no}</td>
				</tr>
				<tr>
					<th scope="col" class="text-center">이름</th>
					<td>${vo.emp_name}</td>
				</tr>

				<tr>
					<th scope="col" class="text-center">이메일</th>
					<td>${vo.email}</td>
				</tr>
				<tr>
					<th scope="col" class="text-center">전화번호</th>
					<td>${vo.phone}</td>
				</tr>

				<tr>
					<th scope="col" class="text-center">고용일</th>
					<td></td>
				</tr>
				<tr>
					<th scope="col" class="text-center">급여</th>
					<td>${vo.salary}</td>
				</tr>
				<tr>
					<th scope="col" class="text-center">지점</th>
					<td>${vo.branch_name}</td>
				</tr>
				<tr>
					<th scope="col" class="text-center">부서명</th>
					<td>${vo.department_name}</td>
				</tr>
				<tr>
					<th scope="col" class="text-center">직위</th>
					<td>${vo.rank_name}</td>
				</tr>

			</tbody>
		</table>

		<button type="button" class="btn btn-secondary"
			onclick="history.go(-1)">이전으로</button>
		<button type="button" class="btn btn-primary"
			onclick="location='modify.emp?id=${vo.emp_no}'">수정</button>
		<button type="button" class="btn btn-danger hr-delete">삭제</button>
	</section> --%>
	<!-- End Section -->

	<section>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="card">

						<div class="card-body">
							<div class="card-title mb-4">
								<div class="d-flex justify-content-start"
									style="column-gap: 2rem;">
									<div class="image-container">
										<img
											src="${vo.profile_path ne null ? vo.profile_path : 'assets/img/user_profile.png'}"
											id="imgProfile" style="width: 150px; height: 150px"
											class="img-thumbnail" />

									</div>
									<div class="userData align-self-center">
										<h2 class="d-block"
											style="font-size: 1.5rem; font-weight: bold">
											<a href="javascript:void(0);">${vo.emp_name }</a>
										</h2>
										<h6 class="d-block">${vo.branch_name }</h6>
										<h6 class="d-block">${vo.department_name }</h6>
										<h6 class="d-block">${vo.rank_name }</h6>
									</div>
									<div class="ml-auto">
										<input type="button" class="btn btn-primary d-none"
											id="btnDiscard" value="Discard Changes" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-12">
									<ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
										<li class="nav-item"><a class="nav-link active"
											id="basicInfo-tab" data-toggle="tab" href="#basicInfo"
											role="tab" aria-controls="basicInfo" aria-selected="true">정보</a>
										</li>

									</ul>
									<div class="tab-content ml-1" id="myTabContent">
										<div class="tab-pane fade show active" id="basicInfo"
											role="tabpanel" aria-labelledby="basicInfo-tab">


											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">사번</label>
												</div>
												<div class="col-md-8 col-6">${vo.emp_no }</div>
											</div>
											<hr />

											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">성명</label>
												</div>
												<div class="col-md-8 col-6">${vo.emp_name }</div>
											</div>
											<hr />
											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">성별</label>
												</div>
												<div class="col-md-8 col-6">${vo.gender }</div>
											</div>
											<hr />

											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">전화번호</label>
												</div>
												<div class="col-md-8 col-6">${vo.phone }</div>
											</div>
											<hr />
											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">이메일</label>
												</div>
												<div class="col-md-8 col-6">${vo.email }</div>
											</div>
											<hr />
											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">고용일</label>
												</div>
												<div class="col-md-8 col-6">
													<fmt:formatDate value="${vo.hire_date}"
														pattern="yyyy-MM-dd" />
												</div>
											</div>
											<hr />
											<%--
<div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">지점</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                               <fmt:formatDate value="${vo.hire_date}"
							pattern="yyyy-MM-dd" />
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">부서</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                               <fmt:formatDate value="${vo.hire_date}"
							pattern="yyyy-MM-dd" />
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">직위</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                               <fmt:formatDate value="${vo.hire_date}"
							pattern="yyyy-MM-dd" />
                                            </div>
                                        </div>
                                        <hr /> --%>
										</div>
										<div class="row">
										<div class="col-6"></div>
											<div class="col-6">
												<div class="mr-0">
													<button type="button" class="btn btn-secondary"
														onclick="history.go(-1)">이전으로</button>
													<button type="button" class="btn btn-primary"
														onclick="location='modify.emp?id=${vo.emp_no}'">수정</button>
													<button type="button" class="btn btn-danger hr-delete">삭제</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>


						</div>

					</div>
				</div>
			</div>
		</div>
	</section>



</main>
<!-- End #main -->
