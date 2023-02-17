<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itinerarytype.model.*"%>
<%@ page import="java.util.*"%>

<%
ItineraryTypeService itinerarytypeSvc = new ItineraryTypeService();
List<ItineraryTypeVO> list = itinerarytypeSvc.getAll();
pageContext.setAttribute("list", list);
%>


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

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/flaticon.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
<link rel="stylesheet" href="css/styleforheader_footerNEW.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>
<style>
a {
	color: #000;
}

#col4 {
	height: 480px;
}

.blog-section {
	padding-top: 25px;
	padding-bottom: 25px;
}

.img1 {
	border-radius: 8px;
	overflow: hidden;
	box-shadow: 3px 3px 3px;
	width: 100%;
	height: 100%;
	object-fit: cover;
	transition: 1s;
	cursor: pointer;
}

.img1:hover {
	opacity: 1;
	transform: scale(1.2);
	overflow: hidden;
}

.blog-item {
	overflow: hidden;
}

.search {
	width: 70%;
	height: 36px;
	font-size: 15px;
	text-transform: uppercase;
	background: #ffffff;
	border-radius: 10px 0px 0px 10px;
}

.search-btn {
	width: 36px;
	height: 36px;
	background-color: #ffffff;
	outline: none;
	cursor: pointer;
	position: absolute;
	border-radius: 0px 10px 10px 0px;
}

.price {
	color: #ffffff;
}

.icon_heart_alt {
	cursor: pointer;
}

.star {
	color: #ffffff;
}

.bi-text {
	cursor: pointer;
}

.t-h {
	display: flex;
	flex-wrap: nowrap;
}

.heart {
	color: #ffffff;
	justify-content: center;
	align-items: center;
	text-align: center;
	opacity: 0.6;
	transition: 0.3s;
}

.heart:hover {
	opacity: 1;
	color: rgb(255, 0, 0);
}

.b-time {
	width: 95%;
	justify-content: center;
	align-items: center;
}

.blog-item {
	height: 450px;
	position: relative;
	margin-bottom: 30px;
	border-radius: 10px;
}

.blog-item.small-size {
	height: 400px;
}

.blog-item .bi-text {
	position: absolute;
	left: 0;
	bottom: 25px;
	width: 100%;
	padding-left: 30px;
	padding-right: 30px;
}

.blog-item .bi-text .b-tag {
	display: inline-block;
	color: #ffffff;
	font-size: 12px;
	text-transform: uppercase;
	letter-spacing: 1px;
	background: #dfa974;
	padding: 3px 10px;
	border-radius: 2px;
}

.blog-item .bi-text h4 {
	margin-top: 18px;
	margin-bottom: 18px;
}

.blog-item .bi-text h4 a {
	color: #ffffff;
}

.blog-item .bi-text .b-time {
	font-size: 12px;
	color: #ffffff;
	text-transform: uppercase;
	letter-spacing: 3px;
}

.co-text a {
	color: #007bff;
}

.co-text a:hover, a:focus {
	color: rgb(255, 255, 255);
}
</style>

</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Offcanvas Menu Section Begin -->
	<div class="offcanvas-menu-overlay"></div>
	<div class="yuya">
		<img class="tttlogo1" src="img/ttt-logo.png" alt="">
		<div>Time to Travel</div>
	</div>
	<div class="canvas-open">
		<i class="icon_menu"></i>
	</div>
	<div class="offcanvas-menu-wrapper">
		<div class="canvas-close">
			<i class="icon_close"></i>
		</div>

		<div class="search-icon search-switch">
			<span class="icon"><img class="searchicon"
				src="img/magnifying-glass.png" alt=""></span> <input type="search"
				style="border: 2px solid black" id="search" placeholder="探索..." />
		</div>
		<div class="top-social">
			<a href="./member.html"> <img class="account"
				src="img/account.png" alt="">
			</a>
		</div>


		<nav class="mainmenu mobile-menu">
			<ul>
				<li><a href="./index.html">首頁</a></li>
				<li><a href="./rooms.html">住宿</a></li>
				<li><a href="./ticket.html">票券</a></li>
				<li><a href="./itinerary.html">行程</a></li>
				<li><a href="./article.html">文章</a></li>
				<li><a href="./firm.html">廠商</a></li>
				<li><a href="./lastnews.html">最新消息</a></li>
				<li><a href="./qa.html">常見問題</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>

		<div class="footeritem">
			<a href="./aboutus.html">關於我們</a> <a href="./contactus.html">聯繫我們</a>
			<!-- <a href="./QA.html">常見問題</a> -->
		</div>
	</div>
	<!-- Offcanvas Menu Section End -->
	<!-- Header Section Begin -->
	<div class="toolbarFixed">
		<header class="header-section header-normal"> </header>
		<div class="top-nav">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<ul class="tn-left">
							<img class="tttlogo" src="img/ttt-logo.png" alt="">
							<div>Time to Travel</div>
							<div class="search-icon search-switch">
								<span class="icon"><img class="searchicon"
									src="img/magnifying-glass.png" alt=""></span> <input
									type="search" style="border: 2px solid black" id="search"
									placeholder="探索..." />
							</div>
						</ul>
					</div>
					<div class="col-lg-6">
						<div class="tn-right">
							<div class="top-social">
								<a href="./tktShoppingCart.html"><img
									class="shoppingCartIMG" src="img/shopping-cart_1.png" alt="">
								</a> <a class="userIcon " href="./member.html"> <img
									class="account" src="img/account.png" alt="">
								</a>
								<div class="language-option">
									<img class="flag" src="img/taiwan.png" alt=""> <span>TW
									</span>
								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="menu-item">
			<div class="container">
				<div class="row">
					<div class="col-lg-10">
						<div class="nav-menu">
							<nav class="mainmenu">
								<ul>
									<li><a href="./index.html">|&nbsp;&nbsp;首頁</a></li>
									<li><a href="./rooms.html">|&nbsp;&nbsp;住宿</a></li>
									<li><a href="./ticket.html">|&nbsp;&nbsp;票券</a></li>
									<li><a href="./itinerary.html">|&nbsp;&nbsp;行程</a></li>
									<li><a href="./article.html">|&nbsp;&nbsp;文章</a></li>
									<li><a href="./firm.html">|&nbsp;&nbsp;廠商</a></li>
									<li><a href="./lastnews.html">|&nbsp;&nbsp;最新消息</a></li>
									<li><a href="./qa.html">|&nbsp;&nbsp;常見問題</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Header End -->

		<!-- Breadcrumb Section Begin -->
		<!--     <div class="breadcrumb-section"> -->
		<!--       <div class="container"> -->
		<!--         <div class="row"> -->
		<!--           <div class="col-lg-12"> -->
		<!--             <div class="breadcrumb-text"> -->
		<!--               <h2>觀光行程</h2> -->
		<!--               <div class="bt-option"> -->
		<!--                 <input class="search" placeholder="Search..." /> -->
		<!--                 <button class="search-btn"> -->
		<!--                   <i class="fa fa-search"></i> -->
		<!--                 </button> -->
		<!--               </div> -->
		<!--             </div> -->
		<!--           </div> -->
		<!--         </div> -->
		<!--       </div> -->
		<!--     </div> -->
		<FORM METHOD="post" ACTION="do">
			<div class="row search">
				<div class="col-md-12">
					<b>輸入行程種類編號:</b> <input type="text" name="itinerary_type_id">
					<input type="hidden" name="action" value="getOne_For_Display">
					<button id="btn-search">搜尋</button>
				</div>
			</div>
		</FORM>



		<!-- Rooms Section Begin -->
		<section class="blog-section blog-page spad">
			<div class="container">
				<div class="row">
					<c:forEach var="itineraryTypeVO" items="${list}">
					<c:if test ="${itineraryTypeVO.itinerary_type_state ==0 }">
						<div id="col4" class="col-lg-4 col-md-6">
							<div class="blog-item set-bg">
										
								<!-- 							行程明細連結 -->
								<a href="<%=request.getContextPath() %>/front_end/itinerarytype/do?itinerary_type_id=${itineraryTypeVO.itinerary_type_id}&action=showDetail">
									<img class="img1" src="./img/blog/blog-1.jpg" alt="" />
								</a>
								<div class="bi-text">

									<h4 class="title"  >
										<!-- 							行程明細連結 -->
											<a href="./blog-details.html">${itineraryTypeVO.itinerary_title}</a>
									</h4>
									<span class="price">TWD${itineraryTypeVO.itinerary_price}起</span>
									<div class="star">
										<i class="fa fa-star-o"></i><span>${itineraryTypeVO.itinerary_total_score}(${itineraryTypeVO.itinerary_total_people})</span>
									</div>
									<div class="t-h">

										<div class="heart">
											<i class="fa fa-heart fa-2x"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</section>

		<!-- Rooms Section End -->

		<!-- Footer Section Begin -->
		<footer class="footer-section">
			<div class="footer-sectioncontainer">
				<div class="container">
					<div class="footer-text">
						<div class="row">
							<div class="col-lg-4">
								<div class="ft-about">
									<div class="social">
										<h6>認識 Time to Travel</h6>
										<div class="footeritem">
											<a href="./aboutus.html">關於我們</a> <a href="./contactus.html">聯繫我們</a>
											<a href="./QA.html">常見問題</a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-3 offset-lg-1">
								<div class="ft-service">
									<h6>Time to Travel 服務</h6>
									<div class="footerservice">
										<a href="./rooms.html">|&nbsp;&nbsp;住宿</a> <a
											href="./ticket.html">|&nbsp;&nbsp;票券</a> <a
											href="./itinerary.html">|&nbsp;&nbsp;行程</a> <a
											href="./article.html">|&nbsp;&nbsp;文章</a>
									</div>
								</div>
							</div>
							<div class="col-lg-3 offset-lg-1">
								<div class="ft-newslatter">
									<h6>取得最新資訊</h6>

									<form action="#" class="fn-form">
										<input type="text" placeholder="Email">
										<button type="submit">
											<i class="fa fa-send"></i>
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="copyright-option">
						<div class="container">
							<div class="row">
								<div class="col-lg-7">
									<ul>

									</ul>
								</div>
								<div class="col-lg-5">
									<div class="co-text">
										<p>
											<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
											Copyright &copy;
											<script>
												document.write(new Date()
														.getFullYear());
											</script>
											Time To Travel All rights reserved. <br> | This is made
											with <i class="fa fa-heart" aria-hidden="true"></i> by <a
												href="#" target="_blank">Tibame CGA105G3</a>
											<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<!-- Footer Section End -->

	<!-- Search model Begin -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch">
				<i class="icon_close"></i>
			</div>
			<form class="search-model-form">
				<input type="text" id="search-input" placeholder="Search here.....">
			</form>
		</div>
	</div>
	<!-- Search model end -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	<script >
	$(document).ready(function() {
	$(".details_btn").click(function() {
		$(this).parent("form").submit();
	})
})
</script>
</body>

</html>