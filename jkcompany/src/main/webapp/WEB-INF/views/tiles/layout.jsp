<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<tiles:insertAttribute name="header" />
<title>JK Company ${title}</title>
<link rel='icon' type='image/x-icon' href='img/hanul.ico'>
<link href='css/common.css?<%=new java.util.Date() %>' rel="stylesheet" type="text/css">
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"  />

<script type="text/javascript" 
		src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src='js/common.js?<%=new java.util.Date() %>'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/js/all.min.js"></script>


</head>
<body>

<tiles:insertAttribute name="topbar" />

<div>
<tiles:insertAttribute name="container" />

</div>
<tiles:insertAttribute name="footer" />

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/aos/aos.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>
  <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
  
  
  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
  
<!-- 자바스크립트 -->
<script type="text/javascript" 
		src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src='js/common.js?<%=new java.util.Date() %>'></script>

  
</body>
</html>







