<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
<style>
select {
	width: 200px;
}
</style>
<!-- cdn for file style -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/2.1.0/bootstrap-filestyle.min.js" integrity="sha512-HfRdzrvve5p31VKjxBhIaDhBqreRXt4SX3i3Iv7bhuoeJY47gJtFTRWKUpjk8RUkLtKZUhf87ONcKONAROhvIw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>

<main id="main">

	<!-- ======= Breadcrumbs ======= -->
	<!-- 경로를 나타내주는 부분입니다 해당하는 내용을 작성해주세요 -->
	<section id="breadcrumbs" class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>사원등록</h2>
				<ol>
					<li><a href="<c:url value='/'/>">홈</a></li>
					<li>관리자</li>
					<li>사원관리</li>
					<li>사원등록</li>
				</ol>
			</div>

		</div>
	</section>
	<!-- End Breadcrumbs -->

	<section>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title text-center" style="font-weight: bold;">사원등록</h3>
						</div>
						<form action="insert.emp" method="post" id="insert" enctype='multipart/form-data'>
							<div class="card-body">
								<div class="card-title mb-4">
									<div class="d-flex justify-content-start"
										style="column-gap: 2rem;">
										<div class="image-container">
											<img
												src="${vo.profile_path ne null ? vo.profile_path : 'assets/img/user_profile.png'}"
												id="imgProfile" style="width: 150px; height: 150px"
												class="img-thumbnail" />

										</div>
										<div class="userData align-self-center">
											<h2 class="d-block"
												style="font-size: 1.5rem; font-weight: bold">
												${vo.emp_name }</h2>
										<h6 class="d-block">${vo.branch_name } ${vo.department_name }</h6>
										<h6 class="d-block">${vo.rank_name }</h6>
										</div>
										
									</div>
								</div>
<div class="row">
<div class="col-12 mb-4">
    <div class="form-group">
        <input type="file" name="file" id="imageUpload">
        
    </div>
</div>
</div>
								<div class="row">
									<div class="col-12">
										<ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
											<li class="nav-item"><a class="nav-link active"
												id="basicInfo-tab" data-toggle="tab" href="#basicInfo"
												role="tab" aria-controls="basicInfo" aria-selected="true">정보</a>
											</li>

										</ul>
										<div class="tab-content ml-1" id="myTabContent">
											<div class="tab-pane fade show active" id="basicInfo"
												role="tabpanel" aria-labelledby="basicInfo-tab">


											

												<div class="row">
													<div class="col-sm-3 col-md-2 col-5  text-center">
														<label style="font-weight: bold;">성명</label>
													</div>
													<div class="col-md-8 col-6">
														<input type="text" class="chk form-control" title="성명란" name="emp_name" />
													</div>
												</div>
												<hr />
												<div class="row">
													<div class="col-sm-3 col-md-2 col-5  text-center">
														<label style="font-weight: bold;">비밀번호</label>
													</div>
													<div class="col-md-5 col-6">
														<input type="password" class="chk form-control is-valid" title="비밀번호란" id="passwords" name="emp_pw"/>
														
													</div>
													<div class="col-md-4 valid-feedback"></div>
												</div>
												<hr />
												<div class="row">
													<div class="col-sm-3 col-md-2 col-5  text-center">
														<label style="font-weight: bold;">비밀번호 확인</label>
													</div>
													<div class="col-md-5 col-6">
														<input type="password" class="chk form-control is-invalid" title="비밀번호 확인란" id="inputInvalid" />
													</div>
														<div class="col-md-4 invalid-feedback">비밀번호가 일치하지 않습니다.</div>
												</div>
												<hr />
												<div class="row">
													<div class="col-sm-3 col-md-2 col-5 text-center">
														<label style="font-weight: bold;">생년월일</label>
													</div>
													<div class="col-md-8 col-6">
														<input type="date" class= "form-control" name="birth"/>
													</div>
												</div>
												<hr />
												<div class="row">
													<div class="col-sm-3 col-md-2 col-5 text-center">
														<label style="font-weight: bold;">성별</label>
													</div>
													<div class="col-md-8 col-6">
													<label>
														<input type='radio' value='남' name='gender' checked>&nbsp;&nbsp;남&nbsp;&nbsp;</label>&nbsp;&nbsp;&nbsp;
													<label>	<input type='radio' value='여' name='gender'
															>&nbsp;&nbsp;여</label>
													</div>
												</div>
												<hr />

												<div class="row">
													<div class="col-sm-3 col-md-2 col-5 text-center">
														<label style="font-weight: bold;">전화번호</label>
													</div>
													<div class="col-md-8 col-6">
														<input type="text" class="chk form-control" title="전화번호란" name="phone"  />
													</div>
												</div>
												<hr />
												<div class="row">
													<div class="col-sm-3 col-md-2 col-5 text-center">
														<label style="font-weight: bold;">이메일</label>
													</div>
													<div class="col-md-8 col-6">
														<input type="text"  class="chk form-control" title="이메일란" name="email" />
													</div>
												</div>
												<hr />
												<div class="row">
													<div class="col-sm-3 col-md-2 col-5 text-center">
														<label style="font-weight: bold;">주소</label>
													</div>
													<div class="col-md-8 col-6">
														<input type="text" id="post" class="chk form-control" title="주소검색란" name="address1" readonly="readonly" />
														
													</div>
												</div>
												<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
														
													</div>
													<div class="col-md-8 col-6">
														<input type="text" class="chk form-control" title="상세주소 입력란"  name="address2" />
														<input type="hidden" name="address" />
													</div>
												</div>
												<hr />
												<div class="row">
													<div class="col-sm-3 col-md-2 col-5 text-center">
														<label style="font-weight: bold;">고용일</label>
													</div>
													<div class="col-md-8 col-6">
														<input type="date" name="hire_date" class="form-control"
															value="${vo.hire_date}" />
													</div>
												</div>
												<hr />

												<div class="row">
													<div class="col-sm-3 col-md-2 col-5 text-center">
														<label style="font-weight: bold;">지점</label>
													</div>
													<div class="col-md-8 col-6">
														<select name="branch_name" id="">
															<c:forEach var="b" items="${branches}">
																<option value="${b.code}"
																	${b.code eq 'B99' ? ' selected':''}>
																	${b.code_value}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<hr />
												<div class="row">
													<div class="col-sm-3 col-md-2 col-5 text-center">
														<label style="font-weight: bold;">부서</label>
													</div>
													<div class="col-md-8 col-6">
														<select name="department_name" id="">
															<c:forEach var="d" items="${departments}">
																<option value="${d.code}"
																	${d.code eq 'D99' ? ' selected':''}>
																	${d.code_value}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<hr />
												<div class="row">
													<div class="col-sm-3 col-md-2 col-5 text-center">
														<label style="font-weight: bold;">직위</label>
													</div>
													<div class="col-md-8 col-6">
														<select name="rank_name" id="">
															<c:forEach var="r" items="${ranks}">
																<option value="${r.code}"
																	${r.code eq 'R99' ? ' selected':''}>
																	${r.code_value}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<hr />

												
													<div class="row">
														<div class="col-sm-3 col-md-2 col-5 text-center">
															<label style="font-weight: bold;">관리자</label>

														</div>
														<div class="col-md-8 col-6">
															<input type='radio' value='L0' name='admin' checked>일반&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<input type='radio' value='L1' name='admin'
																${vo.admin eq 'L1' ? 'checked' : ''}>관리자
														</div>
													</div>
													<hr />
											
											</div>
											<div class="row">
												<div class="col-6"></div>
												<div class="col-6">
													<div class="mr-0">
														<button type="button" class="btn btn-secondary" onclick="history.go(-1)"
															>취소</button>
														<button type="button" class="btn btn-primary insert">확인</button>
													
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>


							</div>
		<%-- 						
		<input type="hidden" name="curPage" value="${page.curPage}" />
		<input type="hidden" name="search" value="${page.search}" />
		<input type="hidden" name="keyword" value="${page.keyword}"/> --%>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- 다음 주소찾기  -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	<script>

    $("#inputInvalid").keyup(function(){
        let pwd1= $("#passwords").val();
        let pwd2= $("#inputInvalid").val();

        if(pwd1 != "" || pwd2 != ""){
            if(pwd1 == pwd2){
                $("#inputInvalid").removeClass('is-invalid');
                $("#inputInvalid").addClass('is-valid')
            }else{
                $("#inputInvalid").addClass('is-invalid');
                $("#inputInvalid").removeClass('is-valid')
            }    
        }
    })


    $("#passwords").keyup(function(){
        let pwd1= $("#passwords").val();
        let pwd2= $("#inputInvalid").val();

        if(pwd1 != "" || pwd2 != ""){
            if(pwd1 == pwd2){
                $("#inputInvalid").removeClass('is-invalid');
                $("#inputInvalid").addClass('is-valid')
            }else{
                $("#inputInvalid").addClass('is-invalid');
                $("#inputInvalid").removeClass('is-valid')
            }    
        }
    })
	$('#imageUpload').filestyle({
		buttonName : 'btn-info',
        buttonText : '사진 선택'
	});
	
		$('.insert').click(function() {
			let pwd1= $("#passwords").val();
	        let pwd2= $("#inputInvalid").val();
	        if(pwd1 == pwd2){
				if (emptyCheck()&&fn_emailChk($('[name=email]').val())){
				var addFound = $('[name=address1]').val();
				var addTyped = $('[name=address2]').val();
				$('[name=address]').val(addFound + '/' +addTyped);
				
				$('#insert').submit();
				
			}
	        }else{
	        	alert('패스워드를 확인해주세요.');
	        }
		});
	
		
		
		$('#post').click(function(){
			//우편번호 찾기 다음 api로 우편번호와 기본주소를 조회해온다
		    new daum.Postcode({
		        oncomplete: function(data) {
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		            console.log(data);
		            $('[name=post]').val(data.zonecode);
		            var address =  data.userSelectedType=='R' ? data.roadAddress :data.jibunAddress
		 			if(data.buildingName !='' )address += ' ('+data.buildingName+')';
					$('[name=address1]').eq(0).val(address);
		    }
		    }).open();	
		});
		
		
		/* 이미지 판단 메소드 */
		function isImage( filename ){
			//파일의 확장자로 이미지파일인지 판단 : abc.png, abc.JPG
			var ext = filename.substring( filename.lastIndexOf('.')+1 ).toLowerCase();
			var imgs = [ 'png', 'jpg', 'jpeg', 'bmp', 'gif', 'webp' ];
			if( imgs.indexOf( ext )==-1 ) return false;
			else return true;
		}

		function fn_emailChk(email){
		  var regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
		  if(!regExp.test(email)){
		    alert("이메일 형식을 확인하세요.");
		    return false;
		  }
		  return true;
		}
		
		
	</script>


</main>
<!-- End #main -->
