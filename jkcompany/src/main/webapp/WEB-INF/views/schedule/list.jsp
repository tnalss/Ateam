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

<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->

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



		
				<div id='calendar'></div>





	</section>
	<!-- End Section -->



</main>
<!-- End #main -->


<script>
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
	// new FullCalendar.Calendar(대상 DOM객체, {속성:속성값, 속성2:속성값2..})
	
    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: ''
      },
      
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      // 이벤트명 : function(){} : 각 날짜에 대한 이벤트를 통해 처리할 내용..
      
      
      
      select: function(arg) {
    	  console.log(arg);

        var title = prompt('입력할 일정:');
    // title 값이 있을때, 화면에 calendar.addEvent() json형식으로 일정을 추가
        if (title) {
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay,
            backgroundColor:"yellow",
            textColor:"blue"
          })
        }
        calendar.unselect()
      },
      eventClick: function(arg) {
    	  // 있는 일정 클릭시,
    	  console.log("#등록된 일정 클릭#");
    	  console.log(arg.event);
    	  
        if (confirm('Are you sure you want to delete this event?')) {
          arg.event.remove()
        }
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: function(info, successCallback, failureCallback){
    	  // ajax 처리로 데이터를 로딩 시킨다.
    	  $.ajax({
    		 type:"get",
    		 url:"${pageContext.request.contextPath}/calendars",
    		dataType:"json"  ,
			success: 
                function(result) {
					console.log(result);
                    var events = [];
                   
                    if(result!=null){
                        
                            $.each(result, function(index, element) {
                            var enddate=element.enddate;
                             if(enddate==null){
                                 enddate=element.startdate;
                             }
                             
                             var startdate=element.startdate;
                             var enddate=enddate;
                             var realmname = element.realmname;
                             
                             
                                 events.push({
                                        title: element.title,
                                        start: element.start,
                                        end: element.end,
                                        url: "${pageContext.request.contextPath }/detail.do?seq="+element.seq,
                                        color:"#6937a1"                                                   
                                     }); //.push()
                            
                             
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

