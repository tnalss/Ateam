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
          <h2>로그인</h2>
          <ol>
            <li><a href="<c:url value='/'/>">홈</a></li>
            <li>로그인</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->

		<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
    <!-- ======= Section ======= -->
   <section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section">로그인</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-12 col-lg-10">
					<div class="wrap d-md-flex">
						<div class="img" style="background-size: auto; background-image: url(assets/img/bg-1.jpg);">
			      </div>
						<div class="login-wrap p-4 p-md-5">
			      	<div class="d-flex">
			      		<div class="w-100">
			      			<h3 class="mb-4">로그인</h3>
			      		</div>
								
			      	</div>
							<form action="#" class="signin-form">
			      		<div class="form-group mb-3">
			      			<label class="label" for="name">사원번호</label>
			      			<input type="text" class="form-control" placeholder="ID" required>
			      		</div>
		            <div class="form-group mb-3">
		            	<label class="label" for="password">패스워드</label>
		              <input type="password" class="form-control" placeholder="Password" required>
		            </div>
		            <div class="form-group">
		            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Sign In</button>
		            </div>
		            <div class="form-group d-md-flex">
		            	<div class="w-50 text-left">
			            	<label class="checkbox-wrap checkbox-primary mb-0">아이디저장
									  <input type="checkbox" checked>
									  <span class="checkmark"></span>
										</label>
									</div>
									<div class="w-50 text-md-right">
										<a href="#">패스워드 찾기</a>
									</div>
		            </div>
		          </form>
		        </div>
		      </div>
				</div>
			</div>
		</div>
	</section>
     
     <!-- 
     <script src="js/jquery.min.js"></script>
  <script src="js/popper.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>
    -->

  </main><!-- End #main -->
