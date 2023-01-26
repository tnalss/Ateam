<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<%
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss");
%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"
	rel="stylesheet">
<style>
#result {
	position: absolute;
	width: 100%;
	max-width: 870px;
	cursor: pointer;
	overflow-y: auto;
	max-height: 400px;
	box-sizing: border-box;
	z-index: 1001;
}

.link-class:hover {
	background-color: #f1f1f1;
}

table {
	border-collapse: collapse;
	margin: 0 auto;
}

table tr {
	height: 46px;
}

table.tb-list tr {
	height: 38px;
}

table th, table td {
	border: 1px solid #b0b0b0;
}

table td {
	padding: 5px 10px;
}

table th {
	background-color: #f6f6f6;
}

table a:hover, ul a:hover {
	font-weight: bold;
	text-decoration: underline;
}

/* 제목이 길어질때 말줄임으로 표시 */
table.tb-list {
	table-layout: fixed;
}

table.tb-list td {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

input {
	height: 30px;
	padding: 0 10px;
	font-size: 16px;
	border: 1px solid #b0b0b0;
}

input[type=radio], input[type=checkbox] {
	width: 18px;
	margin: 0 5px 2px;
	vertical-align: middle;
}

input:focus, select:focus, textarea:focus {
	outline: none;
	border: 1px solid #3367d6;
}

textarea {
	resize: none;
	padding: 0 10px;
	height: 150px;
	font-size: 16px;
	border: 1px solid #b0b0b0;
	font-family: sans-serif;
}

.tb-wrap .tb-list {
	width: 100%;
}

.stepwizard-step p {
	margin-top: 10px;
}

.stepwizard-row {
	display: table-row;
}

.stepwizard {
	display: table;
	width: 100%;
	position: relative;
}

.stepwizard-step button[disabled] {
	opacity: 1 !important;
	filter: alpha(opacity = 100) !important;
}

.stepwizard-row:before {
	top: 14px;
	bottom: 0;
	position: absolute;
	content: " ";
	width: 100%;
	height: 1px;
	background-color: #ccc;
	z-order: 0;
}

.stepwizard-step {
	display: table-cell;
	text-align: center;
	position: relative;
}

.btn-circle {
	width: 30px;
	height: 30px;
	text-align: center;
	padding: 6px 0;
	font-size: 12px;
	line-height: 1.428571429;
	border-radius: 15px;
}

.pull-right {
	margin-top: 20px;
}

.pull-left {
	margin-top: 20px;
}

.text-danger {
	margin: 30px auto;
	text-align: center;
}

.v-center {
	min-height: 200px;
	display: flex;
	justify-content: center;
	flex-flow: column wrap;
}
</style>
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
	</section>
	<!-- End Breadcrumbs -->

	<!-- 섹션을 나누어서 내용을 작성해주시면됩니다. 별다른 내용이 없다면 하나의 섹션만 써도 됨 -->
	<!-- ======= Section ======= -->
	<section id="" class="container">
		<!-- 섹션의 id와 class는 알아서 추가 지정해주세요 -->
		<!-- 실질적으로 내용이 들어가는 부분 -->
		<div class="row">
			<div class="col-12 col-lg-3">
				<div class="card">
					<div class="card-body">
						<div class="d-grid">
							<a href="write.ea" class="btn btn-primary">+ 새 상신 작성</a>
						</div>
						<h5 class="my-3">전자 결재</h5>
						<div class="fm-menu">
							<div class="list-group list-group-flush">
								<a href="draft.ea" class="list-group-item py-1"><i
									class="bx bx-folder me-2"></i><span>기안함</span></a> <a
									href="sign.ea" class="list-group-item py-1"><i
									class="bx bx-devices me-2"></i><span>결재함</span></a> <a
									href="retry.ea" class="list-group-item py-1"><i
									class="bx bx-analyse me-2"></i><span>회수함</span></a> <a
									href="javascript:;" class="list-group-item py-1"><i
									class="bx bx-plug me-2"></i><span>공란함</span></a> <a
									href="javascript:;" class="list-group-item py-1"><i
									class="bx bx-trash-alt me-2"></i><span>참조함</span></a> <a
									href="javascript:;" class="list-group-item py-1"><i
									class="bx bx-image me-2"></i><span>부서수신함</span></a> <a
									href="document.ea" class="list-group-item py-1"><i
									class="bx bx-file me-2"></i><span>문서대장</span></a>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<h5 class="mb-0 text-secondary font-weight-bold">
							21건<span class="float-end text-primary">2건</span>
						</h5>
						<p class="mb-0 mt-2">
							<span class="text-secondary">결재완료</span><span
								class="float-end text-primary">결재 대기</span>
						</p>
						<div class="progress mt-3" style="height: 7px;">
							<div class="progress-bar" role="progressbar" style="width: 15%"
								aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
							<div class="progress-bar bg-warning" role="progressbar"
								style="width: 30%" aria-valuenow="30" aria-valuemin="0"
								aria-valuemax="100"></div>
							<div class="progress-bar bg-danger" role="progressbar"
								style="width: 20%" aria-valuenow="20" aria-valuemin="0"
								aria-valuemax="100"></div>
						</div>
						<div class="mt-3"></div>
						<div class="d-flex align-items-center">
							<div class="flex-grow-1 ms-2">
								<h6 class="mb-0">휴가신청</h6>
								<p class="mb-0 text-secondary">3건</p>
							</div>
							<h6 class="text-primary mb-0">0건</h6>
						</div>
						<div class="d-flex align-items-center mt-3">
							<div class="flex-grow-1 ms-2">
								<h6 class="mb-0">지원요청</h6>
								<p class="mb-0 text-secondary">13건</p>
							</div>
							<h6 class="text-primary mb-0">1건</h6>
						</div>
						<div class="d-flex align-items-center mt-3">
							<div class="flex-grow-1 ms-2">
								<h6 class="mb-0">어떤 문서</h6>
								<p class="mb-0 text-secondary">458건</p>
							</div>
							<h6 class="text-primary mb-0">0건</h6>
						</div>
						<div class="d-flex align-items-center mt-3">
							<div class="flex-grow-1 ms-2">
								<h6 class="mb-0">무슨 문서</h6>
								<p class="mb-0 text-secondary">57건</p>
							</div>
							<h6 class="text-primary mb-0">1건</h6>
						</div>
					</div>
				</div>
			</div>
			<div class="col-12 col-lg-9">
				<div class="container">
					<div class="stepwizard">
						<div class="stepwizard-row setup-panel">
							<div class="stepwizard-step">
								<a href="#step-1" type="button"
									class="btn btn-primary btn-circle">1</a>
								<p>기안양식 선택</p>
							</div>
							<div class="stepwizard-step">
								<a href="#step-2" type="button"
									class="btn btn-default btn-circle" disabled="disabled">2</a>
								<p>결재자(참조) 입력</p>
							</div>
							<div class="stepwizard-step">
								<a href="#step-3" type="button"
									class="btn btn-default btn-circle" disabled="disabled">3</a>
								<p>제목/내용 작성</p>
							</div>
							<div class="stepwizard-step">
								<a href="#step-4" type="button"
									class="btn btn-default btn-circle" disabled="disabled">4</a>
								<p>파일첨부/검토</p>
							</div>
						</div>
					</div>
					<input type='hidden' name='curPage' value='1'> 
						<div class="row setup-content" id="step-1">
							<div class="col-xs-12">
								<div class="col-md-12">
									<h3>Step 1</h3>
									<div class="list-group" id="doc_list">
													<p class="list-group-item active text-center">기안양식목록</p>
												<c:forEach items='${doc_list}' var='vo'>		
													<a href='#;return false;' class="list-group-item">${vo.code_value}<input
														type="checkbox" name="chk" class="pull-right" onclick='checkOnlyOne(this)'></a>
												</c:forEach>
									</div>
									<button class="btn btn-primary nextBtn btn-lg pull-right doc-botton"
										type="button">다음</button>
								</div>
							</div>
						</div>
						<div class="row setup-content" id="step-2">
							<div class="col-xs-12">
								<div class="col-md-12">
									<h3>Step 2</h3>
										<div class="row">
											<div class="col-sm-4 col-sm-offset-1">
												<div class="list-group" id="list1">
													 <a href="#" class="list-group-item active"><span
														class="glyphicon glyphicon-th-large"></span>전체 목록<input
														title="toggle all" type="checkbox" class="all pull-right"></a>
														<div class="ea-list"></div>
												</div>
												<jsp:include page="/WEB-INF/views/include/eapage.jsp" />
											</div>
											<div class="col-md-2 v-center">
												<button title="Send to list 2"
													class="btn btn-default center-block add">
													<i class="bi bi-chevron-compact-right"></i>
												</button>
												<button title="Send to list 1"
													class="btn btn-default center-block remove">
													<i class="bi bi-chevron-compact-left"></i>
												</button>
											</div>
											<div class="col-sm-4">
												<div class="list-group" id="list2">
													<a href="#" class="list-group-item active"><span
														class="glyphicon glyphicon-th-large"></span>결재자 목록 <input
														title="toggle all" type="checkbox" class="all pull-right"></a>
												</div>
											</div>
										</div>
									<button class="btn btn-primary nextBtn btn-lg pull-right sign-btn"
										type="button">다음</button>
								</div>
							</div>
						</div>
						<div class="row setup-content" id="step-3">
							<div class="col-xs-12">
								<div class="col-md-12">
									<h3>Step 3</h3>
									<div class="form-group">
										<label class="control-label">기안제목</label> <input id="ea_title"
											maxlength="200" type="text" required="required"
											class="form-control" placeholder="기안제목을 입력하세요" />
									</div>
									<div class="form-group">
										<label class="control-label">내용</label>
										<textarea id="ea_contents" class="form-control col-sm-5" rows="5" maxlength="200" required="required" placeholder="내용을 입력하세요"></textarea>
									</div>
									<button class="btn btn-primary nextBtn btn-lg pull-right asdf"
										type="button">다음</button>
									<button class="btn btn-primary prevBtn btn-lg pull-left"
										type="button">이전</button>
								</div>
							</div>
						</div>
						<div class="row setup-content" id="step-4">
							<div class="col-xs-12">
								<div class="col-md-12">
									<h3>Step 4</h3>
									<h3 class="text-danger" id="e_title">업무지원요청합니다(제목)</h3>
									<table class="table table-bordered success">
										<thead>
											<tr class="text-center">
												<th>기안양식</th>
												<td id="doc">업무지원요청</td>
											</tr>
											<tr class="text-center">
												<th class="info">기안자</th>
												<td>${loginInfo.emp_name}</td>
											</tr>
											<tr class="text-center">
												<th class="info">부서</th>
												<td>${loginInfo.department_name}</td>
											</tr>
											<tr class="text-center">
												<th class="info">보존연한</th>
												<td>2년</td>
											</tr>
											<tr class="text-center">
												<th class="info">공개여부</th>
												<td>전체공개</td>
											</tr>

											<tr class="text-center">
												<th valign="top" class="info">결재자</th>
												<td id="sign">심청이,지현우,조현보</td>
											</tr>

											<tr class="text-center">
												<th class="info">상신날짜</th>
												<td><%=sf.format(nowTime)%></td>
											</tr>
											<tr class="text-center">
												<th class="info" style="vertical-align: middle;">내용</th>
												<td id="e_contents">이러저러한 이유로 결재서 올리니 빠른시일 내에 결재해주세요</td>
											</tr>
											<tr class="text-center">
												<th>첨부파일</th>
												<td>
													<div class='align'>
														<label> <input type='file' name='file'
															class='attach-file'> <a><i
																class="font-b fa-solid fa-file-arrow-up"></i></a>
														</label> <span class='file-name'></span> <span class='preview'></span>
														<a class='delete-file'><i
															class="font-r fa-regular fa-trash-can"></i></a>
													</div>
												</td>
											</tr>
										</thead>
									</table>
									<button class="btn btn-success btn-lg pull-right" type="button" id="ea_submit">상신하기</button>
									<button class="btn btn-primary prevBtn btn-lg pull-left"
										type="button">이전</button>
								</div>
							</div>
						</div>
				</div>
			</div>
	</section>
	<!-- End Section -->



</main>
<script>
var title, contents, doc;
var sign = [];
var sign_no = [];

$('.asdf').on('click',function(){
	title = $('#ea_title').val();
	contents = $('#ea_contents').val().replaceAll("/\r\n|\r|\n/", "<br/>");
	document.getElementById("e_title").innerText="["+doc+"]"+title;
	document.getElementById("e_contents").innerText=contents;
});

$('.doc-botton').on('click',function(){
	doc = $("input:checkbox[name='chk']:checked").closest("a").text();
	document.getElementById("doc").innerText=doc;
});

$('.sign-btn').on('click', function(){
	var length = $('#list2').children('a').siblings().length;
	
	for(var i=1; i<length ; i++){
		console.log($('#list2').children('a').eq(i).find('input[type=hidden]').val());
		sign.push($('#list2').children('a').eq(i).text()+"<br>");
		sign_no.push( $('#list2').children('a').eq(i).find('input[type=hidden]').val() );
	}
	document.getElementById("sign").innerHTML=sign;
	
});
$("#ea_submit").click(function(){
	
	sendRegData();
});

function sendRegData(){
	$.ajax({
		type:"POST",
		url:"write_insert.ea",
		traditional : true,
		data:{ea_title: title,
			  ea_contents: contents,
			  ea_doc:doc,
			  ea_signer: sign_no
			},
		dataType:"json",
		success: function(data){
			console.log("success");
			console.log(data);
			alert("상신완료");
			var url = "${pageContext.request.contextPath}/draft.ea"
			console.log(url);
			location.replace(url);
		}
	})
}











	/* $('.save').on('click', function() {
		if (emptyCheck())
			$('form').submit();
	}); */
	$('.cancel').on('click', function() {
		history.go(-1);
	})

	$(document)
			.ready(
					function() {

						var navListItems = $('div.setup-panel div a'), allWells = $('.setup-content'), allNextBtn = $('.nextBtn');
						PrevBtn = $('.prevBtn');

						allWells.hide();

						navListItems
								.click(function(e) {
									e.preventDefault();
									var $target = $($(this).attr('href')), $item = $(this);

									if (!$item.hasClass('disabled')) {
										navListItems.removeClass('btn-primary')
												.addClass('btn-default');
										$item.addClass('btn-primary');
										allWells.hide();
										$target.show();
										$target.find('input:eq(0)').focus();
									}
								});

						allNextBtn
								.click(function() {
									var curStep = $(this).closest(
											".setup-content"), curStepBtn = curStep
											.attr("id"), nextStepWizard = $(
											'div.setup-panel div a[href="#'
													+ curStepBtn + '"]')
											.parent().next().children("a"), curInputs = curStep
											.find("input[type='text'],input[type='url']"), isValid = true;

									$(".form-group").removeClass("has-error");
									for (var i = 0; i < curInputs.length; i++) {
										if (!curInputs[i].validity.valid) {
											isValid = false;
											$(curInputs[i]).closest(
													".form-group").addClass(
													"has-error");
										}
									}

									if (isValid)
										nextStepWizard.removeAttr('disabled')
												.trigger('click');
								});

						$('div.setup-panel div a.btn-primary').trigger('click');

						//결재자 선택
						$('.add').click(function(){
						    $('.all').prop("checked",false);
						    var items = $("#list1 input:checked:not('.all')");
						    var n = items.length;
						  	if (n > 0) {
						      items.each(function(idx,item){
						        var choice = $(item);
						        choice.prop("checked",false);
						        choice.parent().appendTo("#list2");
						      });
						  	}
						    else {
						  		alert("한명이라도 선택해주세요.");
						    }
						});

						$('.remove').click(function(){
						    $('.all').prop("checked",false);
						    var items = $("#list2 input:checked:not('.all')");
							items.each(function(idx,item){
						      var choice = $(item);
						      choice.prop("checked",false);
						      choice.parent().appendTo("#list1");
						    });
						});

						/* toggle all checkboxes in group */
						$('.all').click(function(e){
							e.stopPropagation();
							var $this = $(this);
						    if($this.is(":checked")) {
						    	$this.parents('.list-group').find("[type=checkbox]").prop("checked",true);
						    }
						    else {
						    	$this.parents('.list-group').find("[type=checkbox]").prop("checked",false);
						        $this.prop("checked",false);
						    }
						});

						$('[type=checkbox]').click(function(e){
						  e.stopPropagation();
						});

						/* toggle checkbox when list group item is clicked */
						$('.list-group a').click(function(e){
						  
						    e.stopPropagation();
						  
						  	var $this = $(this).find("[type=checkbox]");
						    if($this.is(":checked")) {
						    	$this.prop("checked",false);
						    }
						    else {
						    	$this.prop("checked",true);
						    }
						  
						    if ($this.hasClass("all")) {
						    	$this.trigger('click');
						    }
						});
						
					});
	

	function checkOnlyOne(element) {
		  const checkboxes 
		      = document.getElementsByName("chk");
		  
		  checkboxes.forEach((cb) => {
		    cb.checked = false;
		  })
		  element.checked = true;
		}
	toPage(1);
</script>
<!-- End #main -->
