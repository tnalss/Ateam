<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>사원목록</h3>
<div id='list-top'>
<ul>
	<li><a class='btn-fill' href='new.hr'>신규사원등록</a></li>
</ul>
</div>
<div class='tb-wrap w-px1000'>
<table class='tb-list'>
<colgroup>
	<col width='80px'>
	<col width='200px'>
	<col width='300px'>
	<col>
	<col width='120px'>
</colgroup>
<tr><th>사번</th>
	<th>사원명</th>
	<th>부서</th>
	<th>업무</th>
	<th>입사일자</th>
</tr>
<c:forEach items='${list}' var='vo'>
<tr><td>${vo.emp_no }</td>
	<td><a href='info.hr?id=${vo.emp_no}'>${vo.emp_name }</a></td>
	<td>${vo.department_name}</td>
	<td>${vo.branch_name }</td>
	<td>${vo.rank_name }</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>