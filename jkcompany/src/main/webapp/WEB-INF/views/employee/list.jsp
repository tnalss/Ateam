<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->

  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>사원관리</h2>
          <ol>
            <li><a href="<c:url value='/'/>">홈</a></li>
            <li>관리자</li>
            <li>사원관리</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->

		<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
    <!-- ======= Section ======= -->
    <section id="" class="container">
      <!-- 섹션의 id와 class는 알아서 지정해주세요 -->
  <!-- 실질적으로 내용이 들어가는 부분 -->
      	<h3 class="text-center">사원목록</h3>
      		<div id='list-top'>
      		<ul class="">
      			<li><a class='btn-fill' href='new.hr'>신규사원등록</a></li>
      		</ul>
      		</div>
      		
      		<table class='mx-auto'>
      		<colgroup>
      			<col width='80px'>
      			<col width='200px'>
      			<col width='300px'>
      			<col>
      			<col width='120px'>
      		</colgroup>
      		<tr><th>사번</th>
      			<th>사원명</th>
      			<th>부서</th>
      			<th>업무</th>
      			<th>입사일자</th>
      		</tr>
      		<c:forEach items='${list}' var='vo'>
      		<tr><td>${vo.emp_no }</td>
      			<td><a href='info.hr?id=${vo.emp_no}'>${vo.emp_name }</a></td>
      			<td>${vo.department_name}</td>
      			<td>${vo.branch_name }</td>
      			<td>${vo.rank_name }</td>
      		</tr>
      		</c:forEach>
      		</table>


    </section><!-- End Section -->

   

  </main><!-- End #main -->






