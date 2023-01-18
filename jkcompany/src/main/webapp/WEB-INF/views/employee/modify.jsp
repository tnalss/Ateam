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
          <h2>메뉴이름</h2>
          <ol>
            <li><a href="<c:url value='/'/>">홈</a></li>
          	<li>관리자</li>
          	<li>사원관리</li>
            <li>정보수정</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->

		<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
		
    <!-- ======= Section ======= -->
    <section id="" class="container">
      <!-- 섹션의 id와 class는 알아서 추가 지정해주세요 -->
      <!-- 실질적으로 내용이 들어가는 부분 -->
 <h2>${vo.emp_name} 사원의 정보 수정</h2>
				<form action="update.emp" method="post">
					<table class="table">
						<tbody>
						<tr>
						<th>사번</th>
						<td>${vo.emp_no}
						<input type="hidden" name="employee_id" value="${vo.emp_no}" />
						</td>
						</tr>
							<tr>
								<th scope="col">성명</th>
								<td><input type="text" name="emp_name" value="${vo.emp_name}"/></td>
							</tr>	
							<tr>
								<th scope="col">이메일</th>
								<td><input type="text" name="email" value="${vo.email}" /></td>
							</tr>
							<tr>

							<tr>
								<th scope="col">전화번호</th>
								<td><input type="text" name="phone_number" value="${vo.phone}" /></td>
							</tr>
							
							<tr>
								<th scope="col">고용일</th>
								<td><input type="date" name="hire_date" value="${vo.hire_date}" /></td>
							</tr>
							<tr>
								<th scope="col">급여</th>
								<td><input type="text" name="salary" value="${vo.salary}" /></td>
							</tr>
							<tr>
								<th scope="col">지점</th>
								<td>
								<select name="branch_code" id="">
								<c:forEach var="b" items="${branches}">
								<option value="${b.code}"
									 ${vo.branch_name eq b.code_value ? ' selected':''}>
								${b.code_value}</option>
								</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<th scope="col">부서</th>
								<td>
								<select name="department_code" id="">
								<c:forEach var="d" items="${departments}">
								<option value="${b.code}"
									 ${vo.department_name eq d.code_value ? ' selected':''}>
								${d.code_value}</option>
								</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<th scope="col">직위</th>
								<td>
								<select name="rank_code" id="">
								<c:forEach var="r" items="${ranks}">
								<option value="${r.code}"
									 ${vo.rank_name eq r.code_value ? ' selected':''}>
								${r.code_value}</option>
								</c:forEach>
								</select>
								</td>
							</tr>

						</tbody>
					</table>
				</form>
                   
				<button type="button" class="btn btn-primary modify">확인</button>
                    <button type="button" class="btn btn-secondary"
					onclick="history.go(-1)">취소</button>
      
      
      
    </section><!-- End Section -->

   

  </main><!-- End #main -->
     <script>
     $('.modify').click(function(){
  	
     	if(emptyCheck())
     		$('form').submit();
     });
	</script>