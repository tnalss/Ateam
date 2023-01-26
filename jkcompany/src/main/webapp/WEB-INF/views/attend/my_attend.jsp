<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss");
%>

<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
#time {
	color: #fff;
}
#img{
margin-top:50px;
width:450px;
height: 200px;
margin-bottom: 30px;}

.blog .blog-author img {
	width: 120px;
	height: 120px;
}

#box {
	width: 700px;
	margin-top: 55px;
}

#container2 {
	display: table-cell;
	vertical-align: middle;
}

#btn-set {
	margin-left: 130px;
	margin-bottom: 30px;
}

#on {
	margin-left: 20px;
}

#on, #off {
	border-color: #fff;
	margin-left: 10px;
	width: 200px;
	height: 50px;
	background: #fd5c28;
}
.a li {
font-size: 20px;

}
#lt {
	font-size:20px;
	text-align: center;
	font-weight: bold;
	color: #fff;
}
.rounded-circle {
    border-radius: 50%!important;}
    
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
	<section id="pricing" class="pricing">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="box aos-init aos-animate" data-aos="fade-right">
					<h3>내 정보</h3>
						<img src=${loginInfo.profile_path
							}		class="testimonial-img img-fluid rounded-circle" alt="" width="150px"  
}
							height="150px">
						<h4>
							<sup>${loginInfo.emp_name}</sup>
						</h4>
						<ul>
							<li>${loginInfo.branch_name}</li>
							<li>${loginInfo.department_name}</li>
							<li>${loginInfo.rank_name}</li>
							<li>${loginInfo.email}</li>
						</ul>						
					</div>
				</div>
				<div class="col-lg-4 col-md-6 mt-4 mt-md-0" id="a">
				<img src="assets/img/img_att.JPG" alt="" class="img-fluid" id="img">
					<div class="box featured aos-init aos-animate" data-aos="fade-up">
						<h3 id="time">
							<%=sf.format(nowTime)%>
						</h3>
						<ul>
							<li>${loginInfo.emp_name}님의출·퇴근현황을확인하세요</li>
							<li id="lt">${today.att_state}</li>							
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">자세히 보기</a>
						</div>
					</div>					
				</div>
				<div class="col-lg-4 col-md-6 mt-4 mt-lg-0">
					<div class="box aos-init aos-animate" data-aos="fade-left">
						<h3>Developer</h3>
						<h4>
							
						</h4>
						<ul>
							<li>Aida dere</li>
							<li>Nec feugiat nisl</li>
							<li>Nulla at volutpat dola</li>
							<li>Pharetra massa</li>
							<li>Massa ultricies mi</li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section id="services" class="services">
		<div class="container">
			<div class="row">

				<!-- 코드값 w4인 건수 보여주기 -->
				<div class="col-lg-4 col-md-6">
					<div class="icon-box aos-init aos-animate" data-aos="fade-up"
						data-aos-delay="100">
						<div class="icon">
							<i class="bi bi-check-circle-fill"></i>
						</div>
						<a href="my_attend_detail.at">정상 출퇴근 조회</a>
						</h3>
						<h4>${code}건</h4>
						<p class="description">자세히보기</p>

					</div>
				</div>
				<!-- 코드값 w3 & w7 인 건수 보여주기 -->
				<div class="col-lg-4 col-md-6">
					<div class="icon-box aos-init aos-animate" data-aos="fade-up"
						data-aos-delay="200">
						<div class="icon">
							<i class="bi bi-alarm"></i>
						</div>
						<h3 class="title">
							<a href="">지각 및 결근 조회</a>
						</h3>
						<h4>${code2}건</h4>
						<p class="description">자세히보기</p>
					</div>
				</div>
				<!-- 코드값 인 건수 보여주기 -->
				<div class="col-lg-4 col-md-6">
					<div class="icon-box aos-init aos-animate" data-aos="fade-up"
						data-aos-delay="200">
						<div class="icon">
							<i class="bi bi-emoji-sunglasses"></i>
						</div>
						<h3 class="title">
							<a href="">조퇴 및 연차 사용조회</a>
						</h3>
						<h4>${code2}건</h4>
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

<script>

</script>
