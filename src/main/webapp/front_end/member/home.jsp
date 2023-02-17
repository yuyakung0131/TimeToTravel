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

<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>
		<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-ui.min.js"></script>
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
				<jsp:include page="../header_nonmemberforhomepage.jsp"></jsp:include>
			</c:when>
			<c:when test="${memberVO.member_id != null}">
				<!-- header_member -->
				<jsp:include page="../header_memberforhomepage.jsp"></jsp:include>
			</c:when>
		</c:choose>
		<!-- Header End -->
		<!--carousel section Begin -->
		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<div id="search">
				<form METHOD="post" ACTION="" class="search-model-form">
				<div id="search-wrap" class="search-icon search-switch">
				<input id="search1" class="bar ui-autocomplete-input" name="search" type="text"
					style="border: 2px solid white; " placeholder="探索..." autocomplete="off" />
					<span class="icon"><img class="searchicon"
						src="img/magnifying-glass.png" alt="">
				</div>
				</form>
			</div>
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img class="d-block w-100" src="img/home2.JPG" alt="First slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="img/home4.JPG" alt="Second slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="img/home6.JPG" alt="Third slide">
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
		<!--carousel section End  -->
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
						<a href="<%=request.getContextPath()%>/front_end/member/aboutus.jsp" class="primary-btn about-btn" style="font-size:20px;margin: 10px;">了解更多</a>
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
		</section>
		<!--about us End-->
		<!-- main function Section Begin -->
		<div style="height: 80px; margin:30px;">
			<span class="mainfunctiontitle">活動分類</span>
		</div>
		<section class="hp-mainFunction-section">
			<div class="container-fluid">
				<div class="hp-mainFunction-items">
					<div class="row">
						<div class="col-lg-3 col-md-6">
							<div class="hp-mainFunction-item set-bg"
								data-setbg="img/homesection1.jpg">
								<div class="hr-text">
									<h2 class="main">住宿</h2>
									<h6 class="functionmore">看更多旅遊度假住宿</h6>
									<a href="<%=request.getContextPath()%>/front_end/roomtype/listAllRoomFirm.jsp" class="primary-btn">了解更多......</a>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="hp-mainFunction-item set-bg"
								data-setbg="img/homesection3.jpg">
								<div class="hr-text">
									<h2 class="main">票券</h2>
									<h6 class="functionmore">人氣精選票券推薦!</h6>
									<a href="<%=request.getContextPath()%>/front_end/ticket/browseTicket.jsp" class="primary-btn">了解更多......</a>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="hp-mainFunction-item set-bg"
								data-setbg="img/homesection2.jpg">
								<div class="hr-text">
									<h2 class="main">行程</h2>
									<h6 class="functionmore">旅遊行程精選懶人包</h6>
									<a href="<%=request.getContextPath()%>/front_end/itinerarytype/itinerary_list.jsp" class="primary-btn">了解更多......</a>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="hp-mainFunction-item set-bg"
								data-setbg="img/homesection4.jpg"">
								<div class="hr-text">
									<h2 class="main">文章</h2>
									<h6 class="functionmore">與大家分享你的旅遊大小事</h6>
									<a href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp" class="primary-btn">了解更多......</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<div style="height: 50px;"></div>
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
		src="<%=request.getContextPath()%>/front_end/member/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.nice-select.min.js"></script>
	
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/member/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/member/js/homeMember.js"></script>
	<button type="button" id="BackTop" class="toTop-arrow"></button>
	<script type="text/javascript">

$(document).ready(function() {

$.get("<%=request.getContextPath()%>/front_end/homesearch.do?action=getSearch_For_Display",{},function(data){
	var search = []
	var source = []
	var productId = null;
	var href = null;
	for (var i = 0 ; i < data.length;i++){
		if(data[i].firm_type == 1 ){
			
			productId = data[i].product;
			href="<%=request.getContextPath()%>/front_end/ticket/ticket.do?action=getTicketDetailed_For_Display&tkt_id=" +productId;
			console.log(productId);
		} else if(data[i].firm_type == 2){
			productId = data[i].product;
			href="<%=request.getContextPath()%>/front_end/roomtype/room.do?room_firm_id="+productId+"&action=roomType_view";
			console.log(productId);
		}else if(data[i].firm_type == 3){
			productId = data[i].product;
			href="<%=request.getContextPath()%>/front_end/itinerarytype/do?itinerary_type_id="+ productId +"&action=showDetail";
			console.log(productId);
		}
		console.log(href);
		source.push({
            href: href,
			
			label: data[i].title,
		});
	}
	$("#search1").autocomplete({
		minLength:1,
	    source :source,
	    create:function(){
	    	$(this).data('ui-autocomplete')._renderItem = function(ul,item){
	    		return $('<li>').append('<a class="searchone" href="' + item.href + '"> <span  style="margin-left:10px">'+ item.label + '</span></a>').appendTo(ul);};

	}
	    
	})

},"json");
});
</script>
</body>

</html>