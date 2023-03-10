<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!-- ======= Footer ======= -->
  <footer id="footer">

    <div class="footer-top">
      <div class="container">
        <div class="row">

          <div class="col-lg-3 col-md-6 footer-contact">
            <h3>JK Company</h3>
            <p>
              광주광역시 서구 경열로 3 <br>
              202호 JK Company<br><br>
              <strong>Phone:</strong> +82 062-362-7798<br>
              <strong>Email:</strong> hanul_jk@gmail.com<br>
            </p>
          </div>

          <div class="col-lg-5 col-md-6 footer-links">
            <h4>Useful Links</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="<c:url value='/'/>">홈</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="about">회사소개</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="list.no">공지사항</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="main.ea">전자결재</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="list.bo">익명게시판</a></li>
            </ul>
          </div>


          <div class="col-lg-4 col-md-6 footer-newsletter">
            <h4>JK Company 구독</h4>
            <p>JK Company의 각종 소식을 받아 보실 수 있습니다!</p><p>지금 구독하시고 최신 소식을 받아보세요!</p>
            <form action="" method="post">
              <input type="email" name="email"><input type="submit" value="구독">
            </form>
          </div>

        </div>
      </div>
    </div>

    <div class="container d-md-flex py-4">

      <div class="me-md-auto text-center text-md-start">
        <div class="copyright">
          &copy; Copyright <strong><span>JK Company</span></strong>. All Rights Reserved
        </div>
        <div class="credits">
          <!-- All the links in the footer should remain intact. -->
          <!-- You can delete the links only if you purchased the pro version. -->
          <!-- Licensing information: https://bootstrapmade.com/license/ -->
          <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/flattern-multipurpose-bootstrap-template/ -->
          Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
      </div>
      <div class="social-links text-center text-md-right pt-3 pt-md-0">
        <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
        <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
        <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
        <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
        <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
      </div>
    </div>
  </footer><!-- End Footer -->
