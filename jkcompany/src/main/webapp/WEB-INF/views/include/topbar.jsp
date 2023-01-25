<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- ======= Top Bar ======= -->
<section id="topbar" class="d-flex align-items-center">
	<div
		class="container d-flex justify-content-center justify-content-md-between">
		<div class="contact-info d-flex align-items-center">
			<i class="bi bi-envelope d-flex align-items-center"><a
				href="mailto:contact@example.com">hanul_jk@gmail.com</a></i> <i
				class="bi bi-phone d-flex align-items-center ms-4"><span>062-362-7798</span></i>
		</div>
		<div class="social-links d-none d-md-flex align-items-center">
			<a href="#" class="twitter"><i class="bi bi-twitter"></i></a> <a
				href="#" class="facebook"><i class="bi bi-facebook"></i></a> <a
				href="#" class="instagram"><i class="bi bi-instagram"></i></a> <a
				href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
		</div>
	</div>
</section>



<!-- ======= Header ======= -->
<header id="header" class="d-flex align-items-center">
	<div class="container d-flex justify-content-between">

		<div class="logo">
			<a href="<c:url value='/'/>"><img src="assets/img/logo.png"
				alt="" class="img-fluid"></a>
		</div>

		<nav id="navbar" class="navbar">
			<ul>
				<li><a class="active" href="<c:url value='/'/>">홈</a></li>
				<li><a href="about">회사소개</a></li>
				<li><a href="list.no">공지사항</a></li>
				<li class="dropdown"><a href="#"><span>근태관리</span><!-- 이름만 근태관리임! -->
				<i class="bi bi-chevron-down"></i></a>
					<ul>
						<li><a href="myattend">나의 출·퇴근기록</a></li>
						<li><a href="myholiday">휴가 관리</a></li>
					</ul>
				</li>
				<li><a href="main.ea">전자결재</a></li>
				<li><a href="list.bo">익명 게시판</a></li>
			<c:if test="${loginInfo.admin eq 'L1' }">
				<li class="dropdown"><a href="#"><span>관리자</span>
				<i class="bi bi-chevron-down"></i></a>
					<ul>
						<li><a href="list.emp">사원관리</a></li>
						<li><a href="admin_attend.at">근태관리</a></li>  <!-- 관리자모드 근태관리로 연결 -->
						<li><a href="list.sche">일정관리</a></li>
						<li><a href="list.code">코드관리</a></li>
					</ul>
				</li>
				</c:if>
				<!-- 로그인하지 않은 경우 -->
				<c:if test="${empty loginInfo}">
				<li class="dropdown"><a href="login"><span>로그인</span>
				<i class="bi bi-chevron-down"></i></a>
					<ul>
						<li><a href="myInfo">패스워드 찾기</a></li>
						<li><a href="logout">회원가입</a></li>
					</ul>
				</li>
				<i class="bi bi-list mobile-nav-toggle"></i>
				</c:if>
				<!-- 로그인한 경우 -->
				<c:if test="${not empty loginInfo}">
				<li class="dropdown"><a href="#"><strong>${loginInfo.emp_name}</strong>
				<i class="bi bi-chevron-down"></i></a>
					<ul>
						<li><a href="#">패스워드 변경</a></li>
						<li><a href="logout">로그아웃</a></li>
					</ul>
				</li>
				<i class="bi bi-list mobile-nav-toggle"></i>
				</c:if>
			</ul>
			
		</nav>
		<!-- .navbar -->

	</div>
</header>
<!-- End Header -->