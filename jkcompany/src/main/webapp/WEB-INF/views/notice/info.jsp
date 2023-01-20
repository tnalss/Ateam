<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->

  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>상세내</h2>
          <ol>
            <li><a href="<c:url value='/'/>">홈</a></li>
            <li>메뉴이름</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->

		<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
    <!-- ======= Section ======= -->
    <section id="" class="container">
      <h3 class="text-center">공지</h3>
      		<div id='list-top'>
		<button type="button" class="btn btn-primary"
				 onclick="location='new.no'">공지 글 작성</button>
      		</div>
      		
      		<table class='mx-auto'>
      		<colgroup>
      			<col width='80px'>
      			<col width='200px'>
      			<col width="250px">
      			<col width='120px'>
      			<col width='120px'>
      		</colgroup>
      		<tr class='text-center'><th >번호</th>
      			<th>제목</th>
      			<th>내용</th>
      			<th>작성자</th>
      			<th>날짜</th>
      		</tr>
      		<c:forEach items='${info}' var='vo'>
      		<tr  class='text-center'><td class='text-center'>${vo.board_no }</td>
      			<td><a href='info.notice?id=${vo.emp_no}'>${vo.board_title }</a></td>
      			<td>${vo.board_content }</td>
      			<td>${vo.emp_name}</td>
      			<td><fmt:formatDate pattern = "yyyy/MM/dd" value="${vo.write_date }"/></td>
      		</tr>
      		</c:forEach>
      		</table>
      
      
      
      
    </section><!-- End Section -->

   

  </main><!-- End #main -->
