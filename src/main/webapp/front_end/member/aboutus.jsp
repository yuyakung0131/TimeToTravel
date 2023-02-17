<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--要用for each之程式碼匯入-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Time To Travel">
<meta name="keywords" content="Sona, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Time To Travel</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css"
	rel="stylesheet">


<!-- Css Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/styleforheader_footerNEW.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/member_center.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/home_main.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/about_us.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>


</head>

<body>
	<!-- Page Preloder Begin -->
	<jsp:include page="../preLoder.jsp"></jsp:include>
	<!-- Page Preloder End -->
	<!-- Offcanvas Menu Section Begin -->
	<c:choose>
		<c:when test="${memberVO.member_id == null}">
			<!-- offcanvasMenuSection_nonmember -->
			<jsp:include page="../offcanvasMenuSection_nonmember.jsp"></jsp:include>
		</c:when>
		<c:when test="${memberVO.member_id != null}">
			<!-- offcanvasMenuSection_member -->
			<jsp:include page="../offcanvasMenuSection_member.jsp"></jsp:include>
		</c:when>
	</c:choose>
	<!-- Offcanvas Menu Section End -->
	<div class="toolbarFixed">
		<!-- header Begin -->
		<c:choose>
			<c:when test="${memberVO.member_id == null}">
				<!-- header_nonmember -->
				<jsp:include page="../header_nonmember.jsp"></jsp:include>
			</c:when>
			<c:when test="${memberVO.member_id != null}">
				<!-- header_member -->
				<jsp:include page="../header_member.jsp"></jsp:include>
			</c:when>
		</c:choose>
		<!-- Header End -->

		<!--about us Begin-->
		<section class="aboutus-section spad">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<div class="about-text">
							<div class="section-title">
								<span class="aboutus" style="font-size: 35px;">關於我們</span>
								<h2>Time To Travel</h2>
							</div>
							<p class="f-para">在後疫情時代，人們對環境轉換、旅遊需求大量提升，因此提升旅遊品質也是一種趨勢，針對國內旅遊提供訂房服務、多樣票券商品、多種行程推薦等多元資訊，搭配個人旅遊文章分享，目標呈現一個能滿足旅客全方位需求且便利的網站。</p>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="about-pic">
							<div class="row">
								<div class="col-sm-6">
									<img src="img/aboutus1.jpg" alt="">
								</div>
								<div class="col-sm-6">
									<img src="img/aboutus2.jpg" alt="">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container1">
			<div style="height:50px;margin-top:45px; margin-bottom: 20px;">
			<h3 class="aboutus" style="font-size: 35px;">為何選擇我們?</h3></div>
				<div class="wrapper" >
					<ul class="stage">
						<li class="scene">
							<div class="about" onclick="return true">
								<div class="poster"></div>
								<div class="info">
									<header> </header>
									<h4>
										<i class="icon_check"></i>誠實牢靠的評價
									</h4>
									<p>與最可信賴的服務供應商合作，以及會員真實回饋及文章分享，放輕鬆去探索!</p>
								</div>
							</div>
						</li>
						<li class="scene">
							<div class="about" onclick="return true">
								<div class="poster"></div>
								<div class="info">
									<header> </header>
									<h4>
										<i class="icon_check"></i>隱私保障及安全交易
									</h4>
									<p>隱私及安全是我們的第一考量，透過我們安全的交易平台完成付款及訂購程序。</p>
								</div>
							</div>
						</li>
						<li class="scene">
							<div class="about" onclick="return true">
								<div class="poster"></div>
								<div class="info">
									<header> </header>
									<h4>
										<i class="icon_check"></i>便利的旅遊服務平台
									</h4>
									<p>隨時隨地，為人們帶來高價值和回報豐厚的旅行體驗。</p>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="space"></div>
		</section>
		

		<!--about us End-->
		<!-- main function Section Begin -->

		<!-- main function Section End -->



		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
	<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->


	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/member/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/homeMember.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/modernizr.custom.js"></script>
	<button type="button" id="BackTop" class="toTop-arrow"></button>

</body>

</html>