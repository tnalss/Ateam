<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 이 파일을 탬플릿으로 만들어 쓰시면 됩니다. -->
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"
	rel="stylesheet">
<style>
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

.text-danger {
	margin: 30px auto;
	text-align: center;
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
								<p>결재자(참조) 입력</p>
							</div>
							<div class="stepwizard-step">
								<a href="#step-2" type="button"
									class="btn btn-default btn-circle" disabled="disabled">2</a>
								<p>제목/내용 작성</p>
							</div>
							<div class="stepwizard-step">
								<a href="#step-3" type="button"
									class="btn btn-default btn-circle" disabled="disabled">3</a>
								<p>파일첨부/검토</p>
							</div>
						</div>
					</div>
					<form role="form">
						<div class="row setup-content" id="step-1">
							<div class="col-xs-12">
								<div class="col-md-12">
									<h3>Step 1</h3>
									<!-- <div class="form-group">
										<label class="control-label">결재자</label> <input
											maxlength="100" type="text" required="required"
											class="form-control" placeholder="결재자를 입력하세요" />
									</div> -->
									<div class="container form-group">
										<div class="row clearfix">
											<div class="col-md-12 column">
												<table class="table table-bordered table-hover"
													id="tab_logic">
													<thead>
														<tr>
															<th class="text-center">#</th>
															<th class="text-center control-label">결재자</th>
														</tr>
													</thead>
													<tbody>
														<tr id='addr0'>
															<td>1</td>
															<td><input type="text" name='name0'
																required="required" placeholder='결재자를 입력하세요'
																class="form-control" /></td>
														</tr>
														<tr id='addr1'></tr>
													</tbody>
												</table>
											</div>
										</div>
										<a id="add_row" class="btn btn-default pull-left">+ 결재라인
											추가</a><a id='delete_row' class="pull-left btn btn-default">-
											결재라인삭제 </a>
									</div>
									<!-- 	<div class="form-group">
										<label class="control-label">참조자(선택)</label> <input
											maxlength="100" type="text" required="required"
											class="form-control" placeholder="참조자를 입력하세요.(선택)" />
									</div> -->
									<button class="btn btn-primary nextBtn btn-lg pull-right"
										type="button">다음</button>
								</div>
							</div>
						</div>
						<div class="row setup-content" id="step-2">
							<div class="col-xs-12">
								<div class="col-md-12">
									<h3>Step 2</h3>
									<div class="form-group">
										<label class="control-label">기안제목</label> <input
											maxlength="200" type="text" required="required"
											class="form-control" placeholder="기안제목을 입력하세요" />
									</div>
									<div class="form-group">
										<label class="control-label">내용</label> <input maxlength="200"
											type="text" required="required" class="form-control"
											placeholder="내용을 입력하세요" />
									</div>
									<button class="btn btn-primary nextBtn btn-lg pull-right"
										type="button">다음</button>
									<button class="btn btn-primary prevBtn btn-lg pull-left"
										type="button">이전</button>
								</div>
							</div>
						</div>
						<div class="row setup-content" id="step-3">
							<div class="col-xs-12">
								<div class="col-md-12">
									<h3>Step 3</h3>
									<h3 class="text-danger">업무지원요청합니다(제목)</h3>
									<table class="table table-bordered success">
										<thead>
											<tr>
												<th>기안양식</th>
												<td>업무지원요청</td>
											</tr>
											<tr>
												<th class="info">기안자</th>
												<td>박문수</td>
											</tr>
											<tr>
												<th class="info">부서</th>
												<td>영업관리부</td>
											</tr>
											<tr>
												<th class="info">보존연한</th>
												<td>2년</td>
											</tr>
											<tr>
												<th class="info">공개여부</th>
												<td>전체공개</td>
											</tr>

											<tr>
												<th valign="top" class="info">결재자</th>
												<td>심청이,지현우,조현보</td>
											</tr>

											<tr>
												<th class="info">상신날짜</th>
												<td>2023-01-23</td>
											</tr>
											<tr>
												<th class="info">내용</th>
												<td>이러저러한 이유로 결재서 올리니 빠른시일 내에 결재해주세요</td>
											</tr>
											<tr>
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
									<button class="btn btn-success btn-lg pull-right" type="submit">상신하기</button>
									<button class="btn btn-primary prevBtn btn-lg pull-left"
										type="button">이전</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<%-- <form method="post" action='insert.ea' enctype='multipart/form-data'>
					<table class='w-px900'>
						<tr>
							<th class='w-px150'>문서번호</th>
							<td clas='w-px300'>자동채번</td>
							<th class='w-px150'>기안일자</th>
							<td clas='w-px300'>2023-01-23</td>
						</tr>
						<tr>
							<th>기안자</th>
							<td>박문수</td>
							<th>기안부서</th>
							<td>시설관리부</td>
						</tr>
						<tr>
							<th>결재자</th>
							<td><button> 찾기</button></td>
							<th>참조자</th>
							<td><button> 찾기</button></td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type='text' name='title' class='full chk'
								title='제목'></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name='content' class='full chk' title='내용'></textarea></td>
						</tr>
						<tr>
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
					</table>
					<input type='hidden' name='writer' value='${loginInfo.userid}'>
				</form>
				<div class='btnSet'>
					<a class='btn-fill save'>저장</a> <a class='btn-empty cancel'>취소</a>
				</div>
				
			</div> --%>
	</section>
	<!-- End Section -->



</main>
<script>
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

						var i = 1;
						$("#add_row")
								.click(
										function() {
											$('#addr' + i)
													.html(
															"<td>"
																	+ (i + 1)
																	+ "</td><td><input name='name"+i+"' type='text' placeholder='결재자를 입력하세요'' class='form-control input-md'/>");

											$('#tab_logic').append(
													'<tr id="addr' + (i + 1)
															+ '"></tr>');
											i++;
										});
						$("#delete_row").click(function() {
							if (i > 1) {
								$("#addr" + (i - 1)).html('');
								i--;
							}
						});

					});
</script>
<!-- End #main -->
