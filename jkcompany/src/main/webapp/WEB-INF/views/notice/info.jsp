<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<style>
#comment-list span { float: right; }
</style>
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
      		
      		
      		<table class='w-px1200'>
<colgroup>
	<col width='140px'>
	<col>
	<col width='160px'>
	<col width='160px'>
	<col width='100px'>
	<col width='100px'>
</colgroup>
      		<tr>
      		<th>제목</th>
      		<td colspan='5'>${vo.title }</td>
      		</tr>
      		<tr><th>작성자</th>
	<td>${vo.emp_name}</td>
	<th>작성일자</th>
	<td>${vo.write_date}</td>
	<th>조회수</th>
	<td>${vo.readcnt}</td>
</tr>
<tr><th>내용</th>
	<td>${vo.content }</td>
</tr>
<c:forEach items='${info}' var='vo'>
      		<tr  class='text-center'><td class='text-center'>${vo.board_no }</td>
      			<td><a href='info.no?id=${vo.board_no}'>${vo.board_title }</a></td>
      			<td>${vo.board_content }</td>
      			<td>${vo.emp_name}</td>
      			<td><fmt:formatDate pattern = "yyyy/MM/dd" value="${vo.write_date }"/></td>
      		</tr>
      		
      		</c:forEach>
      		</table>
      
      <button type="button" class="btn btn-primary"
		onclick="location='modify.notice?id=${vo.board_no}'">수정</button>
  

		<button type="button" class="btn btn-primary"
				 onclick="history.go(-1)">취소</button>

      
    </section><!-- End Section -->

   

  </main><!-- End #main -->
