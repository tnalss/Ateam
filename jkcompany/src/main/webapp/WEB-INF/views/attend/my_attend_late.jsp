<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<style>
#top-list {
	color: #fd5c28;
}

#me {
	font-size: 20px;
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
					<li><a href="myattend.at">나의 출·퇴근</a></li>
				</ol>
			</div>
		</div>
	</section>
	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<section>
		<section id="blog" class="blog">
			<div class="container aos-init aos-animate" data-aos="fade-up">
				<div class="row">

					<form method='post' action='admin_attend.at' id="list">
						<input type='hidden' name='curPage' value='1'>
					</form>
					<div class="col-lg-8 entries">
						<article class="entry entry-single">
							<div class="entry-meta">
								<ul>
									<li class="d-flex align-items-center"><i
										class="bi bi-person"></i> <a id="me">${loginInfo.emp_name}&nbsp;</a><a
										id="me">${loginInfo.rank_name}님의</a></li>
								</ul>
							</div>
							<h2 class="entry-title">
								<a href="blog-single.html">지각 조회</a>
							</h2>

							<div class="entry-content">
								<div class="row mt-3">
									<div class="col-12 card p-0">
										<div class="card-body p-0">
											<div class="table-responsive">
												<table class='table table-hover text-center'>
													<colgroup>
														<col width='100px'>
														<col width='100px'>
														<col width='100px'>
														<col width='100px'>
													</colgroup>
													<tr class='text-center' id="top-list">
														<th>날짜</th>
														<th>출근 시간</th>
														<th>퇴근 시간</th>
														<th>상태</th>
													</tr>
													<c:forEach items='${list}' var='vo'>
														<tr style="margin: 20px;">
															<td>${vo.attend_date}</td>
															<td>${vo.attend_on}</td>
															<td>${vo.attend_off}</td>
															<td>${vo.att_state}</td>
														</tr>
													</c:forEach>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</article>



					</div>
					<div class="col-lg-4">
						<div class="sidebar">
							
							<h3 class="sidebar-title">목록</h3>
							<div class="sidebar-item categories">
								<ul>
									<li><a href="my_attend_a.at">정상 </a></li>
									<li><a href="my_attend_late.at">지각 </a></li>
									<li><a href="my_attend_n.at">결근 조회</a></li>
									<li><a href="my_attend_o.at">기타 업무 상황 조회</a></li>
									<li><a href="my_attend_edit.at">연차 신청<</a></li>
								</ul>
							</div>
							<h3 class="sidebar-title">최근 내역</h3>
							<div class="sidebar-item recent-posts">
								<c:forEach items="${since}" var="vo">

									<div class="post-item clearfix" id="b">
										<c:if test="${vo.att_code eq 'W4'}">
											<img src="assets/img/icon_check.png" alt="">
										</c:if>
										<c:if test="${vo.att_code ne 'W4'}">
											<img src="assets/img/icon_clock.png" alt="">
										</c:if>
										<h4>${vo.att_state}</h4>
										<time datetime="2020-01-01">${vo.attend_date}</time>
									</div>

								</c:forEach>
							</div>

						</div>
					</div>
				</div>
			</div>
		</section>
	</section>
</main>