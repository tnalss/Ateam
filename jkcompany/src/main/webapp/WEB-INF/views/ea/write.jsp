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
          <h2>기안작성</h2>
          <ol>
            <li><a href="<c:url value='/'/>">홈</a></li>
            <li>전자결재</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->

		<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
    <!-- ======= Section ======= -->
    <section id="" class="container">
      <!-- 섹션의 id와 class는 알아서 추가 지정해주세요 -->
      <!-- 실질적으로 내용이 들어가는 부분 -->
      <div class="row">
	    <div class="col-12 col-lg-3">
			<div class="card">
				<div class="card-body">
					<div class="d-grid"> <a href="write.ea" class="btn btn-primary">+ 새 상신 작성</a>
					</div>
					<h5 class="my-3">전자 결재</h5>
					<div class="fm-menu">
						<div class="list-group list-group-flush"> <a href="javascript:;" class="list-group-item py-1"><i class="bx bx-folder me-2"></i><span>기안함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-devices me-2"></i><span>결재함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-analyse me-2"></i><span>회수함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-plug me-2"></i><span>공란함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-trash-alt me-2"></i><span>참조함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-image me-2"></i><span>부서수신함</span></a>
							<a href="javascript:;" class="list-group-item py-1"><i class="bx bx-file me-2"></i><span>문서대장</span></a>
						</div>
					</div>
				</div>
			</div>
      
      
      
    </section><!-- End Section -->

   

  </main><!-- End #main -->
