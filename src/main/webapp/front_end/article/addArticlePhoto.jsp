<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
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
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/styleforheader_footerNEW.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>

</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Offcanvas Menu Section Begin -->
	<div class="offcanvas-menu-overlay"></div>
	<div class="yuya">
		<img class="tttlogo1" src="img/ttt-LOGO.png" alt="">
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
				<li><a
					href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp">文章</a></li>
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
							<img class="tttlogo" src="img/ttt-LOGO.png" alt="">
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
									<li><a
										href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp">|&nbsp;&nbsp;文章</a></li>
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
		<!-- Article Post -->
		<div class="article_post">
			<div class="container">
				
					<div class="row justify-content-center">
						<div class="col-lg-10"
							style="background-color: rgb(255, 255, 255); height: 100vh; margin: 20px 0px; padding: 20px; border-radius: 10px; box-shadow: 1px 3px 10px gray;">
							<form action="article.do" method="post">
							<div class="title_post">
								<input type="text" name="article_title"
									value="${param.article_title }" placeholder="標題"
									autocomplete="off"
									style="width: 100%; height: 8vh; font-size: 20px; padding: 15px; margin: 10px 0px; border-radius: 10px; border: 2px solid rgb(248, 239, 210);">
							</div>
							<div style="font-size: 10px; color: red;">
								${errorMsgs.article_title } ${errorMsgs.article_content }</div>
							<div class="content_post">
								<textarea name="article_content" cols="30" rows="10"
									placeholder="內容"
									style="width: 100%; height: 70vh; padding: 15px; margin: 10px 0px; border-radius: 10px; border: 2px solid rgb(248, 239, 210); resize: none;">${param.article_content }</textarea>
							</div>
							<input type="hidden" name="login_member_id"
								value="${memberVO.member_id }" autocomplete="off">
							<div style="display:flex; align-items:center;">
							<input type="file" name="article_pic_upload" value="">
							<div style="text-align: right;  width:100%;">
								<button type="submit" name="action" value="insert"
									style="width: 60px; height: 40px; right: 3%; bottom: 4%; border-radius: 10px; background-color: rgb(243, 194, 135); color: white; border: 0px;">發文</button>
							</div>
							</div>
							</form>
							
							
						</div>
					</div>
			
			</div>
		</div>
		<!-- Article Post End -->
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
											href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp">|&nbsp;&nbsp;文章</a>
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
</body>

</html>