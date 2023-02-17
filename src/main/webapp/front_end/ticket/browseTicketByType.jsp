<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.tktimg.model.*"%>
<%@ page import="com.tickettype.model.*"%>
<%@ page import="com.shoppinglist.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
Vector<ShoppingList> cart = (Vector<ShoppingList>) session.getAttribute("cart");
pageContext.setAttribute("cart", cart);
TktImgService tktimgSvc = new TktImgService();
List<TktImg> all_imge_list = tktimgSvc.getAll();
List<TktImg> all_type_image_list = new ArrayList<TktImg>();

Byte tkt_type_id = Byte.valueOf(request.getParameter("tkt_type_id")); //a標籤送不過去

for (int i = 0; i < all_imge_list.size(); i++) {
	TktImg tktimg = all_imge_list.get(i);
	Ticket ticket = tktimg.getTicket();
	TicketType tickettype = ticket.getTicketType();
	if (tickettype.getTkt_type_id().equals(tkt_type_id)) {
		all_type_image_list.add(tktimg);
	}

}
%>




<!DOCTYPE html>
<html>
<head>
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
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/slicknav.min.css"
	type="text/css">
<!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/styleforheader_footerNEW.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>
<style>
img.tttlogo {
	width: 60px;
	height: 60px;
}

.useriocn {
	border: 1px solid red;
}

.gallery-section {
	padding-bottom: 80px;
}

.gallery-section .section-title {
	margin-bottom: 38px;
}

.gallery-item {
	position: relative;
	height: 279px;
	margin-bottom: 20px;
	border-radius: 5px;
	position: relative;
	z-index: 1;
}

.gallery-item:hover:after {
	opacity: 1;
}

.gallery-item:hover .gi-text {
	opacity: 1;
}

.gallery-item:after {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background: rgba(25, 25, 26, 0.3);
	content: "";
	z-index: -1;
	border-radius: 5px;
	-webkit-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
	opacity: 0;
}

.gallery-item.large-item {
	height: 576px;
}

.gallery-item .gi-text {
	display: -webkit-box;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-align: center;
	-ms-flex-align: center;
	align-items: center;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	justify-content: center;
	width: 100%;
	height: 100%;
	-webkit-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
	opacity: 0;
}

.gallery-item .gi-text h3 {
	color: #ffffff;
}

/*---------------------
  Room Section
-----------------------*/
.rooms-section {
	padding-top: 0;
	padding-bottom: 80px;
}

.room-item {
	margin-bottom: 30px;
}

.room-item img {
	min-width: 100%;
}

.room-item .ri-text {
	border: 1px solid #ebebeb;
	border-top: none;
	padding: 24px 24px 30px 28px;
}

.room-item .ri-text h4 {
	color: #19191a;
	margin-bottom: 17px;
}

.room-item .ri-text h3 {
	color: #dfa974;
	font-weight: 700;
	margin-bottom: 14px;
}

.room-item .ri-text h3 span {
	font-family: "Cabin", sans-serif;
	font-size: 14px;
	font-weight: 400;
	color: #19191a;
}

.room-item .ri-text table {
	margin-bottom: 18px;
}

.room-item .ri-text table tbody tr td {
	font-size: 16px;
	color: #707079;
	line-height: 36px;
}

.room-item .ri-text table tbody tr td.r-o {
	width: 125px;
}

.room-item .ri-text .primary-btn {
	color: #19191a;
}

.room-pagination {
	text-align: center;
	padding-top: 20px;
}

.room-pagination a {
	font-size: 16px;
	color: #707079;
	border: 1px solid #EFD4B9;
	border-radius: 2px;
	padding: 7px 13px 5px;
	margin-right: 7px;
	display: inline-block;
	-webkit-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
}

.room-pagination a:last-child {
	margin-right: 0;
}

.room-pagination a:hover {
	background: #dfa974;
	color: #ffffff;
}

/*-----------------------
  Room Details Section
-------------------------*/
.room-details-section {
	padding-top: 0;
	padding-bottom: 80px;
}

.room-details-item {
	margin-bottom: 50px;
}

.room-details-item img {
	margin-bottom: 40px;
}

.room-details-item .rd-text .rd-title {
	overflow: hidden;
	margin-bottom: 8px;
}

.room-details-item .rd-text .rd-title h3 {
	color: #19191a;
	float: left;
}

.room-details-item .rd-text .rd-title .rdt-right {
	float: right;
	text-align: right;
}

.room-details-item .rd-text .rd-title .rdt-right .rating {
	display: inline-block;
	margin-right: 25px;
	margin-bottom: 10px;
}

.room-details-item .rd-text .rd-title .rdt-right .rating i {
	color: #f5b917;
}

.room-details-item .rd-text .rd-title .rdt-right a {
	display: inline-block;
	color: #ffffff;
	font-size: 13px;
	text-transform: uppercase;
	font-weight: 700;
	background: #dfa974;
	padding: 14px 28px 13px;
}

.room-details-item .rd-text h2 {
	color: #dfa974;
	font-weight: 700;
	margin-bottom: 25px;
}

.room-details-item .rd-text h2 span {
	font-family: "Cabin", sans-serif;
	font-size: 16px;
	font-weight: 400;
	color: #19191a;
}

.room-details-item .rd-text table {
	margin-bottom: 32px;
}

.room-details-item .rd-text table tbody tr td {
	font-size: 16px;
	color: #19191a;
	line-height: 36px;
}

.room-details-item .rd-text table tbody tr td.r-o {
	width: 120px;
	color: #707079;
}

.room-details-item .rd-text p {
	color: #707079;
}

.room-details-item .rd-text p.f-para {
	margin-bottom: 22px;
}

.rd-reviews {
	padding-top: 55px;
	border-top: 1px solid #e5e5e5;
	margin-bottom: 50px;
}

.rd-reviews h4 {
	color: #19191a;
	letter-spacing: 1px;
	margin-bottom: 45px;
}

.rd-reviews .review-item {
	margin-bottom: 32px;
}

.rd-reviews .review-item .ri-pic {
	float: left;
	margin-right: 30px;
}

.rd-reviews .review-item .ri-pic img {
	height: 70px;
	width: 70px;
	border-radius: 50%;
}

.rd-reviews .review-item .ri-text {
	overflow: hidden;
	position: relative;
	padding-left: 30px;
}

.rd-reviews .review-item .ri-text:before {
	position: absolute;
	left: 0;
	top: 0;
	width: 1px;
	height: 100%;
	background: #e9e9e9;
	content: "";
}

.rd-reviews .review-item .ri-text span {
	font-size: 12px;
	color: #dfa974;
	text-transform: uppercase;
	letter-spacing: 3px;
}

.rd-reviews .review-item .ri-text .rating {
	position: absolute;
	right: 0;
	top: 0;
}

.rd-reviews .review-item .ri-text .rating i {
	color: #f5b917;
}

.rd-reviews .review-item .ri-text h5 {
	color: #19191a;
	margin-top: 4px;
	margin-bottom: 8px;
}

.rd-reviews .review-item .ri-text p {
	color: #707079;
	margin-bottom: 0;
}

.review-add h4 {
	color: #19191a;
	letter-spacing: 1px;
	margin-bottom: 45px;
}

.review-add .ra-form input {
	width: 100%;
	height: 50px;
	border: 1px solid #e5e5e5;
	font-size: 16px;
	color: #aaaab3;
	padding-left: 20px;
	margin-bottom: 25px;
}

.review-add .ra-form input::-webkit-input-placeholder {
	color: #aaaab3;
}

.review-add .ra-form input::-moz-placeholder {
	color: #aaaab3;
}

.review-add .ra-form input:-ms-input-placeholder {
	color: #aaaab3;
}

.review-add .ra-form input::-ms-input-placeholder {
	color: #aaaab3;
}

.review-add .ra-form input::placeholder {
	color: #aaaab3;
}

.review-add .ra-form h5 {
	font-size: 20px;
	color: #19191a;
	margin-bottom: 24px;
	float: left;
	margin-right: 10px;
}

.review-add .ra-form .rating {
	padding-top: 3px;
	display: inline-block;
}

.review-add .ra-form .rating i {
	color: #f5b917;
	font-size: 16px;
}

.review-add .ra-form textarea {
	width: 100%;
	height: 132px;
	border: 1px solid #e5e5e5;
	font-size: 16px;
	color: #aaaab3;
	padding-left: 20px;
	padding-top: 12px;
	margin-bottom: 24px;
	resize: none;
}

.review-add .ra-form textarea::-webkit-input-placeholder {
	color: #aaaab3;
}

.review-add .ra-form textarea::-moz-placeholder {
	color: #aaaab3;
}

.review-add .ra-form textarea:-ms-input-placeholder {
	color: #aaaab3;
}

.review-add .ra-form textarea::-ms-input-placeholder {
	color: #aaaab3;
}

.review-add .ra-form textarea::placeholder {
	color: #aaaab3;
}

.review-add .ra-form button {
	font-size: 13px;
	font-weight: 700;
	text-transform: uppercase;
	color: #ffffff;
	letter-spacing: 2px;
	background: #dfa974;
	border: none;
	padding: 14px 34px 13px;
	display: inline-block;
}

.room-booking {
	padding: 0 30px 0 40px;
}

.room-booking h3 {
	color: #19191a;
	margin-bottom: 30px;
}

.room-booking form .check-date {
	position: relative;
	margin-bottom: 15px;
}

.room-booking form .check-date label {
	font-size: 14px;
	color: #707079;
	display: block;
	margin-bottom: 10px;
}

.room-booking form .check-date input {
	width: 100%;
	height: 50px;
	border: 1px solid #ebebeb;
	border-radius: 2px;
	font-size: 16px;
	color: #19191a;
	text-transform: uppercase;
	font-weight: 500;
	padding-left: 20px;
}

.room-booking form .check-date i {
	color: #dfa974;
	position: absolute;
	right: 18px;
	bottom: 17px;
}

.room-booking form .select-option {
	margin-bottom: 15px;
}

.room-booking form .select-option label {
	font-size: 14px;
	color: #707079;
	display: block;
	margin-bottom: 10px;
}

.room-booking form .select-option .nice-select {
	border-radius: 2px;
	border: 1px solid #ebebeb;
	height: 50px;
	line-height: 50px;
	outline: none;
	padding-left: 20px;
	width: 100%;
	float: none;
}

.room-booking form .select-option .nice-select:after {
	border-bottom: 2px solid #dfa974;
	border-right: 2px solid #dfa974;
	height: 10px;
	margin-top: 0;
	right: 20px;
	width: 10px;
	top: 36%;
}

.room-booking form .select-option .nice-select span {
	font-size: 16px;
	color: #19191a;
	text-transform: uppercase;
	font-weight: 500;
}

.room-booking form .select-option .nice-select .list {
	margin-top: 0;
	width: 100%;
}

.room-booking form button {
	display: block;
	font-size: 14px;
	text-transform: uppercase;
	border: 1px solid #dfa974;
	border-radius: 2px;
	color: #dfa974;
	font-weight: 500;
	background: transparent;
	width: 100%;
	height: 46px;
	margin-top: 30px;
}

#lblCartCount {
	font-size: 14px;
	/*      background: #ff0000;   */
	color: red;
	padding: 0 5px;
	/*     vertical-align: top; */
}
</style>
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
		<!-- TicketType Section Begin -->
		<section class="rooms-section spad">
			<div class="container">
				<div class="row">
					<%
					//為了讓多張照片不要重複show出
					Set<TktImg> set = new HashSet<TktImg>();
					for (int i = 0; i < all_type_image_list.size(); i++) {
						TktImg vo = all_type_image_list.get(i);
						set.add(vo);
					}
					pageContext.setAttribute("set", set);
					%>
					<c:forEach var="tktimg" items="${set}" begin="0"
						end="${set.size()-1 }">
						<div class="col-lg-4 col-md-6">
							<div class="room-item">
								<img
									src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimg.tkt_img_id}"
									alt="" width=400px height=300px>
								<div class="ri-text">
									<h4>${tktimg.getTicket().tkt_name}</h4>
									<h3>
										$${tktimg.getTicket().tkt_price}NTD<span>/一張</span>
									</h3>
									<table>
										<tbody>
											<tr>
												<td class="r-o">票券種類</td>
												<td>${tktimg.getTicket().getTicketType().tkt_type_name}</td>
											</tr>
											<tr>
												<td class="r-o">票券庫存量</td>
												<td>${tktimg.getTicket().getTkt_amount()}</td>
											</tr>
										</tbody>
									</table>
									<FORM METHOD="post" ACTION="ticket.do">
										<input type="submit" value="More Details" class="primary-btn">
										<input type="hidden" name="tkt_id" value="${tktimg.tkt_id }">
										<input type="hidden" name="action"
											value="getTicketDetailed_For_Display">
									</FORM>
								</div>
							</div>
						</div>

					</c:forEach>
				</div>
			</div>
		</section>
		<!-- TicketType Section End -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->

	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/ticket/JS/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/ShoppingCart.js"></script>
</body>

</html>