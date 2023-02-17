<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itineraryorder.model.*"%>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%
MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");

ItineraryOrderService itineraryorderSvc = new ItineraryOrderService();
List<ItineraryOrderVO> list = itineraryorderSvc.getAll();
pageContext.setAttribute("list", list);

ItineraryOrderVO itineraryOrderVO = (ItineraryOrderVO) request.getAttribute("itineraryOrderVO"); 

ItineraryVO itineraryVO = (ItineraryVO) request.getAttribute("itineraryVO"); 

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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/order-detail.css"
	type="text/css">
<!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/styleforheader_footerNEW.css"
	type="text/css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/card.css">

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
		<!-- Section Begin -->
		<div id="row" class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/front_end/itinerarytype/order.do"
					name="form1" id="orderForm">
					<header>
						<h4 style="font-weight: bold; margin-bottom: 10px;">訂購人資料</h4>
					</header>
					<div id="con" class="con">
						<div class="row">
							<div class="col-lg-6">
								<i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;<label>名字<span
									class="text-danger">*</span></label><br> <input type="TEXT"
									name="member_name" value="${memberVO.member_name}" readonly
									disabled="disabled" /> <input type="hidden" name="member_id"
									value="${memberVO.member_id}" />
							</div>
							<div class="col-lg-6">
								<i class="fa fa-phone" aria-hidden="true"></i>&nbsp;&nbsp;<label>聯絡電話</label><br>
								<input type="TEXT" name="member_name"
									value="${memberVO.member_phone}" readonly disabled="disabled" />
							</div>

						</div>
						<div class="row">
							<div class="col-lg-12">
								<i class="fa fa-envelope" aria-hidden="true"></i>&nbsp;&nbsp;<label
									style="margin-top: 10px;">電子郵件信箱<span
									class="text-danger">*</span></label><br> <input type="TEXT"
									name="member_name" value="${memberVO.member_email}" readonly
									disabled="disabled" />
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<p style="margin-top: 10px; font-size: 0.9rem;">
									<span class="text-danger">*</span>如有需要修改訂購人資料，請前往會員中心進行修改。
								</p>
							</div>
						</div>
					</div>
					<div class="t2">
						<header>
							<h4 style="font-weight: bold; margin-bottom: 10px;">訂單資料</h4>
						</header>
					</div>
					<div id="con" class="con">
						<div>
							<h5 style="font-weight: bold; margin-bottom: 10px;">行程</h5>
						</div>
						<div class="row">
							<div class="col-lg-4">
								<img style="width: 100%; height: 160px"
									src="<%=request.getContextPath()%>/ItineraryImgReader.do?itinerary_type_id=${itineraryVO.itineraryTypeVO.itinerary_type_id}&offset=0">
							</div>
							<div class="col-lg-8">
								<div class="form-group">
									<p>${itineraryVO.itineraryTypeVO.itinerary_title}</p>
									<p
										style="max-width: 200px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${itineraryVO.itineraryTypeVO.itinerary_info}</p>
									<p>
										<i class="fa fa-calendar" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;&nbsp;${itineraryVO.itinerary_start}
									</p>
									<input type="hidden" name="itinerary_id"
										value="${itineraryVO.itinerary_id}" readonly />
								</div>
							</div>
						</div>
						<div></div>
						<div class="row">
							<div class="col-lg-6" style="margin-top: 15px;">
								<h5 style="margin-bottom: 5px;">報名人數</h5>
								<div class="form-group">
									<input type="number" name="itinerary_people_num" id="people"
										value="1" onclick="newprice()" onkeydown="return false"
										min="1"
										max="${itineraryVO.itinerary_max-itineraryVO.itinerary_now}">人
								</div>
							</div>
							<div class="col-lg-6" style="margin-top: 15px;">
								<h5>訂單總金額</h5>
								<div class="form-group">
									TWD&nbsp;&nbsp;<input
										style="border-style: none; font-size: 24px; background-color: #F5F5F5; color: rgba(239, 44, 73, 0.984)"
										type="TEXT" name="itinerary_ttl_price" id="price"
										value="${itineraryVO.itinerary_price}" readonly />
								</div>
							</div>
						</div>

					</div>
					<div class="t2">
						<header>
							<h4 style="font-weight: bold;">特殊需求備註</h4>
							<p>
								<font color=red><b>*</b></font>此欄位僅限資料備註。
							</p>
						</header>
					</div>
					<div id="con" class="con">
						<div class="row">
							<div class="col-lg-12">
								<div id="form" class="form-group">
									<textarea class="form-control" id="myTextarea"
										name="itinerary_order_memo" rows="7"></textarea>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="action" value="insert"> <input
						type="hidden" name="ServiceURL"
						value="https://payment-stage.ecpay.com.tw/Cashier/AioCheckOut/V5"
						class="form-control" /> <input type="hidden"
						name="MerchantTradeNo" value="oikidA0000001" class="form-control" />
					<input type="hidden" name="MerchantTradeDate"
						value="2017/06/30 00:00:00" class="form-control" /> <input
						type="hidden" name="PaymentType" value="aio" class="form-control" />
					<input type="hidden" name="TotalAmount" value="29999"
						class="form-control" /> <input type="hidden" name="TradeDesc"
						value="Desc" class="form-control" /> <input type="hidden"
						name="aaa" value="aaa" class="form-control" /> <input
						type="hidden" name="ReturnURL"
						value="http://tn.sly-ha.com.tw/demo/hoyo/ECPay.php"
						class="form-control" /> <input type="hidden" name="ChoosePayment"
						value="ALL" />
					<button id="btn" class="submit-btn">送出</button>
				</FORM>
			</div>
		</div>
		<!-- Section End -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->

	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/owl.carousel.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/card.js"></script>
	<script>
        let price = ${itineraryVO.itinerary_price};
        function newprice(){
            let people = $("#people").val();
            let total = price*people;
            $("#price").val(total);
        }
	</script>
</body>

</html>