<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- fullcalendar CDN -->
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'
	rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<!-- fullcalendar 언어 CDN -->
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<style>
.has-shadow {
   -webkit-box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1), -1px 0 2px
      rgba(0, 0, 0, 0.05);
   box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1), -1px 0 2px
      rgba(0, 0, 0, 0.05);
  
}

</style>
<main id="main">

	<!-- ======= Breadcrumbs ======= -->
	<!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
	<section id="breadcrumbs" class="breadcrumbs">
		<div class="container">
			<div class="d-flex justify-content-between align-items-center">
				<h2>일정관리</h2>
				<ol>
					<li><a href="<c:url value='/'/>">홈</a></li>
					<li>일정관리</li>
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
			<div class="col-lg-4 card p-0 has-shadow">
				<div class="card-header"><h4>${loginInfo.emp_name} 님의 일정관리</h4></div>
				<div class="row card m-3 p-2 mt-5" data-aos="fade-right" data-aos-delay="0">
				<ul class='d-flex mb-0 justify-content-around align-items-center'>
				<li>회사 일정</li>
				<li> <span class="text-primary m-0">${ countCompany}</span> 건</li>
				</ul>
				</div>
				<div class="row card m-3 p-2"  data-aos="fade-right" data-aos-delay="500">
				<ul class='d-flex mb-0 justify-content-around align-items-center'>
				<li>${loginInfo.department_name} 일정</li>
				<li> <span class="text-primary m-0">${countDept }</span> 건</li>
				</ul>
				</div>
				<div class="row card m-3 p-2" data-aos="fade-right" data-aos-delay="1000">
				<ul class='d-flex mb-0 justify-content-around align-items-center'>
				<li>개인 일정</li>
				<li> <span class="text-primary m-0">${countPersonal}</span> 건</li>
				</ul>
				</div>
			</div>			
			<div class="col-lg-8">
				<div class="card">
					<div id='calendar'></div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Section -->
</main>
<!-- End #main -->

<script>
	document.addEventListener('DOMContentLoaded',function() {
		var calendarEl = document.getElementById('calendar');
		// new FullCalendar.Calendar(대상 DOM객체, {속성:속성값, 속성2:속성값2..})
		var calendar = new FullCalendar.Calendar(calendarEl,{
				headerToolbar : {
					left : 'prev,next',
					center : 'title',
					right : 'today'},
				locale : "ko",
				navLinks : false, // can click day/week names to navigate views
				selectable : true,
				selectMirror : true,
				// 이벤트명 : function(){} : 각 날짜에 대한 이벤트를 통해 처리할 내용..

				select : function(arg) {
					console.log(arg);
					var title = prompt('입력할 일정:');// title 값이 있을때, 화면에 calendar.addEvent() json형식으로 일정을 추가
						if (title) {
							calendar.addEvent({
							title : title,
							start : arg.start,
							end : arg.end,
							allDay : arg.allDay,
							backgroundColor : "yellow",
							textColor : "blue"})
							}
							calendar.unselect()},
				eventClick : function(arg) {
					// 있는 일정 클릭시,
					console.log("#등록된 일정 클릭#");
					console.log(arg.event);

					if (confirm('일정을 삭제하시겠습니까?')) {
						//arg.event.remove()
					//console.log(arg.event.extendedProps.sche_no);
						location="${pageContext.request.contextPath }/deleteOne.sche?sche_no="+arg.event.extendedProps.sche_no;
					}else{
					
						return;
					}
				},
				editable : true,
				dayMaxEvents : true, // allow "more" link when too many events
				events : function(info, successCallback,
					failureCallback) {	// ajax 처리로 데이터를 로딩 시킨다.
							$.ajax({
								type : "get",
								url : "${pageContext.request.contextPath}/calendars?id="+${loginInfo.emp_no},
								dataType : "json",
								success : function(result) {
									console.log(result);
												var events = [];
												if (result != null) {$.each(result,function(index,element) {
													

													events.push({
														title : element.title,
														start : element.start,
														end : element.end,
														sche_type : element.ep1,
														sche_no : element.ep2,
														//url : "${pageContext.request.contextPath }/deleteOne.sche?sche_no="+element.ep2,
														color : element.color}); //.push()
													}); //.each()

													console.log(events);
													}//if end                           
														successCallback(events);
													}//success: function end        
												});
									}

								});
						calendar.render();
					});
</script>

