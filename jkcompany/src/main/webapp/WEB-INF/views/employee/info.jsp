<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>


  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>메뉴이름</h2>
          <ol>
            <li><a href="<c:url value='/'/>">홈</a></li>
          	<li>관리자</li>
          	<li>사원관리</li>
            <li>상세정보</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->

    <!-- ======= Section ======= -->
    <section id="" class="container">
      <!-- 섹션의 id와 class는 알아서 추가 지정해주세요 -->
      <!-- 실질적으로 내용이 들어가는 부분 -->
      
      <h2 class="text-center">${vo.emp_name} 님의 상세 정보</h2>
				<table class="table">
					<tbody>
						<tr>
							<th scope="col" class="text-center">사번</th>
							<td>${vo.emp_no}</td>
						</tr>
						<tr>
							<th scope="col"  class="text-center">이름</th>
							<td>${vo.emp_name}</td>
						</tr>
					
						<tr>
							<th scope="col" class="text-center">이메일</th>
							<td>${vo.email}</td>
						</tr>
						<tr>
							<th scope="col" class="text-center">전화번호</th>
							<td>${vo.phone}</td>
						</tr>
						
						<tr>
							<th scope="col" class="text-center">고용일</th>
							<td><fmt:formatDate value="${vo.hire_date}" pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<th scope="col" class="text-center">급여</th>
							<td>${vo.salary}</td>
						</tr>
						<tr>
							<th scope="col" class="text-center">지점</th>
							<td>${vo.branch_name}</td>
						</tr>
						<tr>
							<th scope="col" class="text-center">부서명</th>
							<td>${vo.department_name}</td>
						</tr>
						<tr>
							<th scope="col" class="text-center">직위</th>
							<td>${vo.rank_name}</td>
						</tr>
						
					</tbody>
				</table>
      
                 <button type="button" class="btn btn-secondary"
					onclick="history.go(-1)">이전으로</button>
				<button type="button" class="btn btn-primary"
					onclick="location='modify.emp?id=${vo.emp_no}'">수정</button>
				<button type="button" class="btn btn-danger hr-delete">삭제</button>
    </section><!-- End Section -->

   

  </main><!-- End #main -->
