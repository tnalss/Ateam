<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <!-- ======= Hero Section ======= -->
  <section id="hero">
    <div id="heroCarousel" data-bs-interval="5000" class="carousel slide carousel-fade" data-bs-ride="carousel">

      <div class="carousel-inner" role="listbox">

        <!-- Slide 1 -->
        <div class="carousel-item active" style="background-image: url(assets/img/slide/slide-1.jpg);">
          <div class="carousel-container">
            <div class="carousel-content animate__animated animate__fadeInUp">
              <h2><span>JK Company</span></h2>
              <p>창조적 도전과 혁신으로 새로운 내일을 만들어 갑니다.</p>
              <div class="text-center"><a href="" class="btn-get-started">Read More</a></div>
            </div>
          </div>
        </div>

        <!-- Slide 2 -->
        <div class="carousel-item" style="background-image: url(assets/img/slide/slide-2.jpg);">
          <div class="carousel-container">
            <div class="carousel-content animate__animated animate__fadeInUp">
              <h2>JK Company</h2>
              <p>자연 친화적인 휴계공간. 숲속에 있는 것 같은 휴계공간. 바쁜 업무중 잠깐의 휴식을 자연과 함께.</p>
              <div class="text-center"><a href="" class="btn-get-started">Read More</a></div>
            </div>
          </div>
        </div>

        <!-- Slide 3 -->
        <div class="carousel-item" style="background-image: url(assets/img/slide/slide-3.jpg);">
          <div class="carousel-container">
            <div class="carousel-content animate__animated animate__fadeInUp">
              <h2>JK Company</h2>
              <p>팀 회의는 카페 같은 분위기의 회의실에서. 커피는 무료입니다</p>
              <div class="text-center"><a href="" class="btn-get-started">Read More</a></div>
            </div>
          </div>
        </div>

      </div>

      <a class="carousel-control-prev" href="#heroCarousel" role="button" data-bs-slide="prev">
        <span class="carousel-control-prev-icon bx bx-left-arrow" aria-hidden="true"></span>
      </a>

      <a class="carousel-control-next" href="#heroCarousel" role="button" data-bs-slide="next">
        <span class="carousel-control-next-icon bx bx-right-arrow" aria-hidden="true"></span>
      </a>

      <ol class="carousel-indicators" id="hero-carousel-indicators"></ol>

    </div>
  </section><!-- End Hero -->

  <main id="main">

    <!-- ======= Cta Section ======= -->
    <section id="cta" class="cta">
      <div class="container">

        <div class="row">
          <div class="col-lg-9 text-center text-lg-left">
            <h3>저희는 끊임없이 앞으로 나아갑니다.</h3>
            <p>고객과의 소통. 발전된 기술. 원하는 디자인. 지금 JKCompany와 함께 하세요</p>
          </div>
          <div class="col-lg-3 cta-btn-container text-center">
            <a class="cta-btn align-middle" href="#">문의</a>
          </div>
        </div>

      </div>
    </section><!-- End Cta Section -->


    <!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio">
      <div class="container">

        <div class="row" data-aos="fade-up">
          <div class="col-lg-12 d-flex justify-content-center">
            <ul id="portfolio-flters">
              <li data-filter="*" class="filter-active">All</li>
              <li data-filter=".filter-app">App</li>
              <li data-filter=".filter-web">Web</li>
            </ul>
          </div>
        </div>

        <div class="row portfolio-container" data-aos="fade-up">

          <div class="col-lg-4 col-md-6 portfolio-item filter-app">
            <img src="assets/img/portfolio/dang.png" class="img-fluid" alt="">
            <div class="portfolio-info">
              <h4>당근마켓 클론</h4>
              <p>중고거래는 당근마켓!</p>
              <a href="assets/img/portfolio/dang.png" data-gallery="portfolioGallery" class="portfolio-lightbox preview-link" title="App 1"><i class="bx bx-plus"></i></a>
              <a href="https://github.com/ekzm2302" class="details-link" title="More Details"><i class="bx bx-link"></i></a>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-web">
            <img src="assets/img/portfolio/ji.PNG" class="img-fluid" alt="">
            <div class="portfolio-info">
              <h4>JI Play</h4>
              <p>노래는 역시 JI play!</p>
              <a href="assets/img/portfolio/ji.PNG" data-gallery="portfolioGallery" class="portfolio-lightbox preview-link" title="Web 3"><i class="bx bx-plus"></i></a>
              <a href="https://jihyeunwoo.github.io/jiplay/" class="details-link" title="More Details"><i class="bx bx-link"></i></a>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-app">
            <img src="assets/img/portfolio/pizza.png" class="img-fluid" alt="">
            <div class="portfolio-info">
              <h4>메가커피 클론</h4>
              <p>커피는 메가에서!</p>
              <a href="assets/img/portfolio/pizza.png" data-gallery="portfolioGallery" class="portfolio-lightbox preview-link" title="App 2"><i class="bx bx-plus"></i></a>
              <a href="https://github.com/tnalss" class="details-link" title="More Details"><i class="bx bx-link"></i></a>
            </div>
          </div>
          
          <div class="col-lg-4 col-md-6 portfolio-item filter-web">
            <img src="assets/img/portfolio/tae.PNG" class="img-fluid" alt="">
            <div class="portfolio-info">
              <h4>태권도</h4>
              <p>태권도 정보 모두 여기!</p>
              <a href="assets/img/portfolio/tae.PNG" data-gallery="portfolioGallery" class="portfolio-lightbox preview-link" title="Web 4"><i class="bx bx-plus"></i></a>
              <a href="https://ekzm2302.github.io/miniproject/" class="details-link" title="More Details"><i class="bx bx-link"></i></a>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-web">
            <img src="assets/img/portfolio/onoma.PNG" class="img-fluid" alt="">
            <div class="portfolio-info">
              <h4>오노마 호텔</h4>
              <p>호텔은 오노마!</p>
              <a href="assets/img/portfolio/onoma.PNG" data-gallery="portfolioGallery" class="portfolio-lightbox preview-link" title="Web 2"><i class="bx bx-plus"></i></a>
              <a href="https://github.com/luna-jy/miniproject_1" class="details-link" title="More Details"><i class="bx bx-link"></i></a>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-app">
            <img src="assets/img/portfolio/look.png" class="img-fluid" alt="">
            <div class="portfolio-info">
              <h4>온더룩 클론</h4>
              <p>패션의 완성 온더룩!</p>
              <a href="assets/img/portfolio/look.png" data-gallery="portfolioGallery" class="portfolio-lightbox preview-link" title="App 3"><i class="bx bx-plus"></i></a>
              <a href="https://github.com/jihyeunwoo" class="details-link" title="More Details"><i class="bx bx-link"></i></a>
            </div>
          </div>


          <div class="col-lg-4 col-md-6 portfolio-item filter-web">
            <img src="assets/img/portfolio/maroo.PNG" class="img-fluid" alt="">
            <div class="portfolio-info">
              <h4>마루과학</h4>
              <p>마루과학 쇼핑몰</p>
              <a href="assets/img/portfolio/maroo.PNG" data-gallery="portfolioGallery" class="portfolio-lightbox preview-link" title="Web 3"><i class="bx bx-plus"></i></a>
              <a href="https://tnalss.github.io/miniproject/" class="details-link" title="More Details"><i class="bx bx-link"></i></a>
            </div>
          </div>
          
          <div class="col-lg-4 col-md-6 portfolio-item filter-app">
            <img src="assets/img/portfolio/nike.png" class="img-fluid" alt="">
            <div class="portfolio-info">
              <h4>나이키 러닝 클론</h4>
              <p>건강한 습관을 만드세요!</p>
              <a href="assets/img/portfolio/nike.png" data-gallery="portfolioGallery" class="portfolio-lightbox preview-link" title="App 4"><i class="bx bx-plus"></i></a>
              <a href="https://github.com/luna-jy" class="details-link" title="More Details"><i class="bx bx-link"></i></a>
            </div>
          </div>
          

        </div>

      </div>
    </section><!-- End Portfolio Section -->

    <!-- ======= Our Clients Section ======= -->
    <section id="clients" class="clients">
      <div class="container">

        <div class="section-title" data-aos="fade-up">
          <h2><strong>협력사</strong></h2>
          <p><strong>'팀워크의 좋은 점은 항상 지원해줄 사람들이 있다는 것이다.'</strong>  -Margaret Carty</p><p>JKCompany의 도움이 되어주셔서 항상 감사합니다.</p><p>앞으로도 잘 부탁드립니다.</p>
        </div>

        <div class="row no-gutters clients-wrap clearfix" data-aos="fade-up">

          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="assets/img/clients/client-1.png" class="img-fluid" alt="">
            </div>
          </div>

          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="assets/img/clients/client-2.png" class="img-fluid" alt="">
            </div>
          </div>

          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="assets/img/clients/client-3.png" class="img-fluid" alt="">
            </div>
          </div>

          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="assets/img/clients/client-4.png" class="img-fluid" alt="">
            </div>
          </div>

          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="assets/img/clients/client-5.png" class="img-fluid" alt="">
            </div>
          </div>

          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="assets/img/clients/client-6.png" class="img-fluid" alt="">
            </div>
          </div>

          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="assets/img/clients/client-7.png" class="img-fluid" alt="">
            </div>
          </div>

          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="assets/img/clients/client-8.png" class="img-fluid" alt="">
            </div>
          </div>

        </div>

      </div>
    </section><!-- End Our Clients Section -->
    </main>
