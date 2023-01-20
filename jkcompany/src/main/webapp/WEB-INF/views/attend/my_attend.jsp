<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
.pricing .container button[type=submit] {
    background: #f03c02;
    border: 0;
    padding: 10px 24px;
    color: #fff;
    transition: 0.4s;
    border-radius: 4px;
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
	<div class='container'>
		<h2>내 출퇴근 기록 조회</h2>
		<section id="pricing" class="pricing">
			<div class="container">
				<div class="text-center">
					<div class="col-lg-6 aos-init aos-animate" data-aos="fade-up" float:left width:50%>
						<div class="box featured aos-init aos-animate" data-aos="fade-up">
							<h3>로그인한 회원의 이름</h3> 							
						</div>
					</div>
					<div class="col-lg-6 aos-init aos-animate" data-aos="fade-up" float:left  width:50%> 
					<img src="assets/img/img_att.JPG" alt="" class="img-fluid"
						width="320px" height="175px">
					</div>					
				</div>
				<button type="submit">Send Message</button>
			</div>
		</section>
	</div>
<section id="blog" class="blog">
		<div class="container">
			<div class="blog-author d-flex align-items-center">
				<img src="assets/img/blog/blog-author.jpg"
					class="rounded-circle float-left" alt="">
				<div>
					<h4>Jane Smith</h4>
					<div class="social-links">
						<a href="https://twitters.com/#"><i class="bi bi-twitter"></i></a>
						<a href="https://facebook.com/#"><i class="bi bi-facebook"></i></a>
						<a href="https://instagram.com/#"><i class="biu bi-instagram"></i></a>
					</div>
					<p>Itaque quidem optio quia voluptatibus dolorem dolor. Modi
						eum sed possimus accusantium. Quas repellat voluptatem officia
						numquam sint aspernatur voluptas. Esse et accusantium ut unde
						voluptas.</p>
				</div>
			</div>
			<!-- End blog author bio -->
		</div>
	</section>
	<section id="services" class="services">
		<div class="container">
			<div class="row">
				<!-- 이번주 근무시간  -->
				<div class="col-lg-4 col-md-6">
					<div class="icon-box aos-init aos-animate" data-aos="fade-up">
						<div class="icon">
							<i class="bi bi-briefcase"></i>
						</div>
						<h3 class="title">
							<a href="">이번주 총 근무 시간</a>
						</h3>
						<h4>이번주 근무시간 보여줍니다</h4>
						<p class="description">자세히보기</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="icon-box aos-init aos-animate" data-aos="fade-up"
						data-aos-delay="100">
						<div class="icon">
							<i class="bi bi-card-checklist"></i>
						</div>
						<h3 class="title">
							<a href="">정상 처리</a>
						</h3>
						<h4>출퇴근코드 =W4 인 값 건수보여줍니다</h4>
						<p class="description">자세히보기</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="icon-box aos-init aos-animate" data-aos="fade-up"
						data-aos-delay="200">
						<div class="icon">
							<i class="bi bi-bar-chart"></i>
						</div>
						<h3 class="title">
							<a href="">미처리</a>
						</h3>
						<h4>출퇴근코드 =W4 아닌 값 건수 보여줍니다</h4>
						<p class="description">자세히보기</p>
						
					</div>
				</div>
			</div>
		</div>
	</section>	
</main>
<!-- End main -->


<a href="#"
	class="back-to-top d-flex align-items-center justify-content-center"><i
	class="bi bi-arrow-up-short"></i></a>