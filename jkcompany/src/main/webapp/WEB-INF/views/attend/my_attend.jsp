
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
	font-size: 45px;
	margin-top: 90px;
	color: #fd5c28;
	justify-content: center;
	text-align: center;
	margin-bottom: 50px;
}

#img {
	margin-top: 30px;
	width: 450px;
	height: 200px;
	margin-bottom: 18px;
}

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

#o li {
	font-size: 20px;
	margin: 15px;
}

#aa {
	margin-top: 45px;
}

.a li {
	font-size: 20px;
}

#lt {
	font-size: 20px;
	text-align: center;
	font-weight: bold;
	color: #fff;
	text-align: center;
}

.rounded-circle {
	border-radius: 50% !important;
}

#s {
	float: left;
	display: flex;
	justify-content: center;
	display: flex;
}

#btn1 {
	margin-top: 10px;
	margin-left: 50px;
	width: 150px;
	margin-left: 50px;
}

#btn2 {
	margin-top: 10px;
	width: 150px;
}

#b {
	margin-top: 17px;
}

#o {
	margin-top: 30px;
}

#h3 {
	display: block;
	font-size: 2 rem;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	font-weight: bold;
}

#h{
margin-top:15px;
font-size: 25px;


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
					<div class="box aos-init aos-animate" data-aos="fade-right" id="aa">
						<h3>내 정보</h3>
						<img src="${loginInfo.profile_path ne null ? loginInfo.profile_path:'assets/img/user_profile.png'}"
							class="testimonial-img img-fluid rounded-circle" alt=""
							width="150px" height="150px">
						<h4 id="h">	${loginInfo.emp_name}				</h4>
						<ul>
							<li>${loginInfo.branch_name}</li>
							<li>${loginInfo.department_name}</li>
							<li>${loginInfo.rank_name}</li>
							<li>${loginInfo.email}</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 mt-4 mt-md-0" id="a">
					<h3 id="time">
						<%=sf.format(nowTime)%>
					</h3>
					<div class="box featured aos-init aos-animate" data-aos="fade-up"
						id="o">
						<ul>
							<li>${loginInfo.emp_name}님의 출·퇴근 현황을 확인하세요</li>
							<li id="lt">${today.att_state}</li>
						</ul>

						<div class="row" id="s">
							<div class="btn-wrap" id="btn1">
								<a href="#" class="btn-buy">출근</a>
							</div>
							<div class="btn-wrap" id="btn2">
								<a href="#" class="btn-buy">퇴근</a>
							</div>
						</div>

					</div>
				</div>
				<div class="col-lg-4 col-md-6 mt-4 mt-lg-0">
					<img src="assets/img/img_att.JPG" alt="" class="img-fluid" id="img">
					<div class="box aos-init aos-animate" data-aos="fade-left"
						style="height: 250px" id="o">
						<h3>업무 상태 수정 바로가기</h3>
						<div clas="row" id="s">
							<div class="btn-wrap">
								<a href="#" class="btn-buy" id="btn-on-cancel">출근 취소</a>
							</div>
							<div class="btn-wrap">
								<a href="#" class="btn-buy" id="btn-off-cancel">퇴근 취소</a>
							</div>
						</div>
						<div class="btn-wrap">
							<a href="my_attend_edit.at" class="btn-buy" id="b">연차 신청</a>
						</div>
					</div>

				</div>
			</div>


		</div>
		</div>
	</section>
	<section id="services" class="services">
		<div class="container">
			<h3 data-aos="fade-up" id="h3">이번 주 나의 근무 현황 요약</h3>
			<div class="row">

				<!-- 코드값 w4인 건수 보여주기 -->
				<div class="col-lg-4 col-md-6">
					<div class="icon-box aos-init aos-animate" data-aos="fade-up"
						data-aos-delay="100">
						<div class="icon">
							<i class="bi bi-check-circle-fill"></i>
						</div>
						<h3 class="title">
							<a href="my_attend_a.at">정상 업무 종료 조회</a>
						</h3>
						<h4>${code}건</h4>
						<a href="my_attend_a.at"><p class="description">자세히보기</p></a>

					</div>
				</div>
				<!-- 코드값 w3인 건수 보여주기 -->
				<div class="col-lg-4 col-md-6">
					<div class="icon-box aos-init aos-animate" data-aos="fade-up"
						data-aos-delay="200">
						<div class="icon">
							<i class="bi bi-alarm"></i>
						</div>
						<h3 class="title">
							<a href="my_attend_late.at">지각 조회</a>
						</h3>
						<h4>${code2}건</h4>
						<a href="my_attend_late.at"><p class="description">자세히보기</p></a>
					</div>
				</div>
				<!-- 코드값 2인 건수 보여주기 -->
				<div class="col-lg-4 col-md-6">
					<div class="icon-box aos-init aos-animate" data-aos="fade-up"
						data-aos-delay="200">
						<div class="icon">
							<i class="bi bi-emoji-sunglasses"></i>
						</div>
						<h3 class="title">
							<a href="my_attend_n.at">결근 조회</a>
						</h3>
						<h4>${code3}건</h4>
						<a href="my_attend_n.at"><p class="description">자세히보기</p></a>
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

/*출근 버튼 클릭시  */
$('#btn1').on('click',function(){
	if( ${today.attend_on ne null}){
		alert('출근 처리가 완료되었습니다')
	}
	else{			
		/*today의 attend_on이 없으면 : 매퍼의 attend_on 실행  */
		location.href = "attend_on.at";
	}
});
/*퇴근 버튼 클릭시  */
$('#btn2').on('click',function(){
	if( ${today.attend_off ne null}){
		alert('퇴근 처리가 완료되었습니다')
	}else{	
		location.href = "attend_off.at";
	}
});

/* 출근 취소 버튼 클릭시 */	
$('#btn-on-cancel').on('click',function(){	
	location.href="on_cancel.at";
});

/* 퇴근 취소 버튼 클릭시 */
$('#btn-off-cancel').on('click',function(){	
	alert('퇴근 취소시 현재 상태가 "출근"으로 표시됩니다.')
	location.href="off_cancel.at";
});
</script>
