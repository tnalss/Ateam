<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
  <link href="css/loginStyle.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.3.min.js" ></script> 
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
					<h2 class="heading-section">JK Company</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
		      
		      	
						<form action="" class="login-form">
		      		<div class="form-group">
		      			<input type="text" class="form-control rounded-left" id="id" placeholder="ID" required>
		      		</div>
	            <div class="form-group d-flex">
	              <input type="password" class="form-control rounded-left" id="pw" placeholder="Password" required>
	            </div>
	            <div class="form-group d-md-flex">
	            	<div class="w-50">
	            		<label class="checkbox-wrap checkbox-primary">아이디 저장
									  <input type="checkbox" checked>
									  <span class="checkmark"></span>
									</label>
								</div>
								<div class="w-50 text-md-right">
									<a href="#">패스워드 찾기</a>
								</div>
	            </div>
	            <div class="form-group">
	            	<a class="login btn btn-primary rounded submit p-3 px-5">로그인</a>
	            </div>
	          </form>
	        </div>
	        
				</div>
			</div>
		</div>
	</section>

 <script>
 $('.login').click(function(){
		login();
	});
 function login(){
	
	if( emptyCheck() ){
		
		$.ajax({
			url: 'jklogin',
			data: { id:$('#id').val(), pw:$('#pw').val() },
			dataType : "json", 
			success: function( response ){
				
				if( response ){
					location = '<c:url value="/"/>';
				}else{
					alert('아이디나 비밀번호가 일치하지 않습니다');
				}
			},error: function(req,text){
				alert(text+':'+req.status);
			}
		});
	}
}
</script>

  </main><!-- End #main -->
