<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.tktimg.model.*"%>
<%@ page import="com.tktlist.model.*"%>
<%@ page import="com.tktorder.model.*"%>
<%@ page import="com.tktitem.model.*"%>
<%@ page import="com.ticketcomment.model.*"%>
<%@ page import="com.shoppinglist.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
Vector<ShoppingList> cart = (Vector<ShoppingList>) session.getAttribute("cart");
pageContext.setAttribute("cart", cart);
// Ticket ticket = (Ticket) request.getAttribute("ticket");
TicketService ticketSvc = new TicketService();
// Integer tkt_id = 2;
Integer tkt_id = (Integer) request.getAttribute("tkt_id");
//Ticket ticket = (Ticket) request.getAttribute("ticket"); //later replaced by this code 
Ticket ticket = ticketSvc.getOneTicket(tkt_id);
MemberVO member = (MemberVO) session.getAttribute("memberVO");
TicketCommentService ticketcommentSvc = new TicketCommentService();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
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
<!-- <link rel="stylesheet" href="css/styleforheader_footer..css" type="text/css"> -->
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

.blog-details-text .comment-option {
	margin-bottom: 75px;
}

.blog-details-text .comment-option h4 {
	color: #19191a;
	margin-bottom: 35px;
}

.blog-details-text .comment-option .single-comment-item {
	margin-bottom: 30px;
}

.blog-details-text .comment-option .single-comment-item.first-comment .sc-text
	{
	padding-left: 22px;
}

.blog-details-text .comment-option .single-comment-item.first-comment .sc-text:before
	{
	/*  	position: absolute;  */
	left: 0;
	top: 0;
	width: 1px;
	height: 255px;
	background: #ebebeb;
	content: "";
}

.blog-details-text .comment-option .single-comment-item.reply-comment {
	padding-left: 120px;
	margin-bottom: 52px;
}

.blog-details-text .comment-option .single-comment-item.second-comment .sc-text
	{
	padding-left: 22px;
}

.blog-details-text .comment-option .single-comment-item.second-comment .sc-text:before
	{
	position: absolute;
	left: 0;
	top: 0;
	width: 1px;
	height: 100px;
	background: #ebebeb;
	content: "";
}

.blog-details-text .comment-option .single-comment-item .sc-author {
	float: left;
	margin-right: 28px;
}

.blog-details-text .comment-option .single-comment-item .sc-author img {
	height: 70px;
	width: 70px;
	border-radius: 50%;
}

.blog-details-text .comment-option .single-comment-item .sc-text {
	display: table;
	position: relative;
}

.blog-details-text .comment-option .single-comment-item .sc-text span {
	font-size: 12px;
	color: #dfa974;
	text-transform: uppercase;
	letter-spacing: 2px;
}

.blog-details-text .comment-option .single-comment-item .sc-text h5 {
	font-size: 20px;
	color: #19191a;
	margin-top: 4px;
	margin-bottom: 8px;
}

.blog-details-text .comment-option .single-comment-item .sc-text p {
	color: #707079;
	margin-bottom: 18px;
}

.blog-details-text .comment-option .single-comment-item .sc-text a {
	display: inline-block;
	color: #19191a;
	font-size: 14px;
	font-weight: 700;
	text-transform: uppercase;
	letter-spacing: 1px;
	padding: 5px 24px;
	border: 1px solid #F9EEE3;
	border-radius: 50px;
	-webkit-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
	margin-right: 10px;
}

.blog-details-text .comment-option .single-comment-item .sc-text a:hover
	{
	background: #dfa974;
	color: #ffffff; */
	border-color: #dfa974;
}

.blog-details-text .leave-comment h4 {
	color: #19191a;
	margin-bottom: 35px;
}

.blog-details-text .leave-comment .comment-form input {
	width: 100%;
	height: 50px;
	color: #707079;
	font-size: 16px;
	padding-left: 20px;
	border: 1px solid #e5e5e5;
	border-radius: 2px;
	margin-bottom: 20px;
	-webkit-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
}

.blog-details-text .leave-comment .comment-form input:focus {
	border-color: #dfa974;
}

.blog-details-text .leave-comment .comment-form textarea {
	width: 100%;
	height: 116px;
	color: #707079;
	font-size: 16px;
	padding-left: 20px;
	border: 1px solid #e5e5e5;
	border-radius: 2px;
	-webkit-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
	margin-bottom: 42px;
	resize: none;
	padding-top: 12px;
}

.blog-details-text .leave-comment .comment-form textarea:focus {
	border-color: #dfa974;
}

.blog-details-text .leave-comment .comment-form button {
	font-size: 13px;
	font-weight: 700;
	text-transform: uppercase;
	color: #ffffff;
	letter-spacing: 2px;
	background: #dfa974;
	border: none;
	padding: 14px 42px 13px;
	display: inline-block;
	border-radius: 2px;
}

.carousel-inner img {
	width: 100%;
	height: 100%;
	margin-top: 10px;
}

#custCarousel .carousel-indicators {
	position: static;
	margin-top: 10px;
}

#custCarousel .carousel-indicators>li {
	width: 100px;
}

#custCarousel .carousel-indicators li img {
	display: block;
	opacity: 0.5;
}

#custCarousel .carousel-indicators li.active img {
	opacity: 1;
}

#custCarousel .carousel-indicators li:hover img {
	opacity: 0.75;
}

.carousel-item img {
	width: 60%;
}

.button-like {
	border: 2px solid #8a8a8a;
	background-color: transparent;
	text-decoration: none;
	padding: 10px;
	position: relative;
	vertical-align: middle;
	text-align: center;
	display: inline-block;
	border-radius: 3rem;
	color: #8a8a8a;
	transition: all ease 0.4s;
	cursor: pointer;
}

.button-like span {
	margin-left: 0.5rem;
}

.button-like .fa, .button-like span {
	transition: all ease 0.4s;
}

.button-like:hover {
	border-color: #cc4b37;
}

.button-like:hover .fa {
	color: #cc4b37;
}

.button-like:hover #aaa {
	color: #cc4b37;
}

/* ------------ */
.liked {
	background-color: #cc4b37;
	border-color: #cc4b37;
}

.liked .fa, .liked span {
	color: #fefefe;
}

/* ------------------- */
.liked:focus {
	background-color: #cc4b37;
}

.liked:focus .fa, .liked:focus #aaa {
	color: #fefefe;
}

.modal-overlay {
	position: fixed;
	bottom: 5%;
	left: 50%;
	width: 150px;
	height: 50px;
	background: lightblue;
	display: grid;
	transition: 0;
	visibility: hidden;
	z-index: -10;
	color: white;
}

.close-btn {
	text-align: right;
	margin-left: 5px;
}

.modal-container {
	display: flex;
	justify-content: center;
	align-items: center;
}

.open-modal {
	visibility: visible;
	z-index: 10;
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
		<!-- browse ticket detail Section Begin -->
		<br>
		<h3 style="text-align: center;"><%=ticket.getTkt_name()%></h3>
		<br>
		<div class="breadcrumb-section">
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<div class="breadcrumb-text">

							<table class="table">
								<tr>
									<td>票券種類</td>
									<td><%=ticket.getTicketType().getTkt_type_name()%></td>
								</tr>
								<tr>
									<td>票券價錢</td>
									<td><%=ticket.getTkt_price()%>元</td>
								</tr>
								<tr>
									<td>票券發行公司</td>
									<td><%=ticket.getFirm().getFirm_name()%></td>
								</tr>
								<tr>
									<td>票券庫存量</td>
									<td><%=ticket.getTkt_amount()%>張</td>
								</tr>
								<tr>
									<td>票券期限</td>
									<td><%=ticket.getTkt_date()%>個月</td>
								</tr>
							</table>
						</div>
					</div>
					<jsp:useBean id="tktimgSvc" scope="page"
						class="com.tktimg.model.TktImgService" />
					<%
					List<TktImg> tktimg = tktimgSvc.findByTktId(tkt_id);
					pageContext.setAttribute("tktimglist", tktimg);
					%>
					<div class="col-md-9">
						<div id="custCarousel" class="carousel slide" data-ride="carousel"
							align="center">
							<!-- slides -->
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img
										src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimglist.get(0).tkt_img_id}"
										alt="Hills">
								</div>
								<c:forEach var="tktimg" items="${tktimglist}" begin="1"
									end="${tktimglist.size()-1}">

									<div class="carousel-item">
										<img
											src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimg.tkt_img_id}"
											width=400px height=300px alt="Hills">
									</div>
								</c:forEach>
							</div>
							<!-- Thumbnails -->
							<ol class="carousel-indicators list-inline">
								<li class="list-inline-item active"><a
									id="carousel-selector-0" class="selected" data-slide-to="0"
									data-target="#custCarousel"> <img
										src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimglist.get(0).tkt_img_id}"
										class="img-fluid">
								</a></li>
								<c:forEach var="tktimg" items="${tktimglist}" begin="1"
									end="${tktimglist.size()-1}">
									<li class="list-inline-item"><a
										id="carousel-selector-${tktimglist.indexOf(tktimg)}"
										data-slide-to="${tktimglist.indexOf(tktimg)}"
										data-target="#custCarousel"> <img
											src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimg.tkt_img_id}"
											class="img-fluid">
									</a></li>
								</c:forEach>
							</ol>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br> <br>
		<!-- Breadcrumb Section End -->

		<!-- Rooms Section Begin -->
		<div class="breadcrumb-section">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<FORM METHOD="post" ACTION="list.do">
							<!-- 						<div style="display: inline-block"> -->
							<!-- 							數量 <input type="number" id="number" value="1" min="1" max="10" -->
							<!-- 								step="1"> -->
							<!-- 						</div> -->
							<button type="submit" id="list" class="button button-like"
								style="display: inline-block">
								<i class="fas fa-heart"></i>加入收藏 <input type="hidden"
									name="action" value="addList"> <input type="hidden"
									name="tkt_id" value="<%=ticket.getTkt_id()%>">
							</button>
						</FORM>
						<!-- 					<FORM METHOD="post" ACTION="list.do"> -->
						<!-- 						<button type="submit" id="list" class="button button-like" -->
						<!-- 							style="display: inline-block"> -->
						<!-- 							<i class="fas fa-heart"></i>加入Redis收藏 <input type="hidden" -->
						<!-- 								name="action" value="addListRedis"> <input type="hidden" -->
						<%-- 								name="tkt_id" value="<%=ticket.getTkt_id()%>"> --%>
						<!-- 						</button> -->
						<!-- 					</FORM> -->
						<br>
						<FORM METHOD="post" ACTION="shoppinglist.do">
							<button type="submit" id="cart" class="button button-like"
								style="display: inline-block">
								<i class="fas fa-cart-plus"></i> 加入購物車 <input type="hidden"
									name="action" value="addCart"> <input type="hidden"
									name="tkt_id" value="<%=ticket.getTkt_id()%>">
							</button>
							<input type="text" name="tkt_amount" placeholder="請輸入數量">
						</FORM>
					</div>
					<br>
					<!-- 				<FORM METHOD="post" ACTION="emp.do"> -->
					<!-- 					<button type="submit" id="cart" class="button button-like" -->
					<!-- 						style="display: inline-block"> -->
					<!-- 						<i class="fas fa-cart-plus"></i> 加入購物車Jedis <input type="hidden" -->
					<!-- 							name="action" value="addCartJedis"> <input type="hidden" -->
					<%-- 							name="tkt_id" value="<%=ticket.getTkt_id()%>"> --%>
					<!-- 					</button> -->
					<!-- 				</FORM> -->
				</div>
			</div>
		</div>
		<div class="jumbotron">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<%=ticket.getInstruction()%>
					</div>
				</div>
			</div>

		</div>
		<div class="col-lg-10 offset-lg-1">
			<div class="blog-details-text">

				<%
				List<TicketComment> all_comment = ticketcommentSvc.getAll();
				List<TicketComment> one_ticket_comment = new ArrayList<TicketComment>();

				for (int i = 0; i < all_comment.size(); i++) {
					TicketComment comment = all_comment.get(i);
					if (comment.getTkt_id().equals(ticket.getTkt_id())) {
						one_ticket_comment.add(comment);

					}
				}
				pageContext.setAttribute("one_ticket_comment", one_ticket_comment);

				Double score = ticketcommentSvc.getAllCommentScore(tkt_id);
				pageContext.setAttribute("score", score);
				%>


				<div class="comment-option">
					<h4><%=one_ticket_comment.size()%>
						則評論
					</h4>

					<%
					if (!(score == 0)) {
					%>
					<h5>
						總評論分數為
						<fmt:formatNumber type="number" maxFractionDigits="1"
							value="${score.intValue()}" />
						分

					</h5>
					<%
					}
					%>
					<br>
					<c:if test="${not empty one_ticket_comment}">
						<c:forEach var="tktcomment" items="${one_ticket_comment}"
							begin="0" end="${one_ticket_comment.size()-1}">
							<div class="single-comment-item first-comment">
								<div class="sc-author">
									<img
										src="<%=request.getContextPath()%>/front_end/member/member.do?action=getImg&member_id=${tktcomment.getMember_id().toString()}"
										alt="">
								</div>
								<div class="sc-text">
									<span><fmt:formatDate
											value="${tktcomment.ticket_comment_time}"
											pattern="yyyy-MM-dd HH:mm:ss" /></span>
									<h5>${tktcomment.getMember().member_name}</h5>
									<p>${tktcomment.ticket_comment_content}</p>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>

				<%
				if (!(member == null)) {
				%>
				<%
				// 			比較tktitem是否有買過才能評論
				TktOrderService tktorderSvc = new TktOrderService();
				TktItemService tktitemSvc = new TktItemService();
				Integer member_id = member.getMember_id();
				Integer order_id = null;
				List<TktItem> list_tktitem = null;
				List<TktOrder> list_order = tktorderSvc.findByMemberId(member_id);
				Set<Ticket> list = new HashSet();

				for (int i = 0; i < list_order.size(); i++) {
					order_id = list_order.get(i).getTkt_order_id();
					list_tktitem = tktitemSvc.getAllOrder(order_id);
					for (int j = 0; j < list_tktitem.size(); j++) {
						Ticket item = list_tktitem.get(j).getTicket();
						list.add(item);
					}
				}
				%>
				<%
				if (list.contains(ticket)) {
				%>
				<div class="leave-comment">
					<h4 style="text-align: center;">給個評論吧</h4>
					<form method="post" action="comment.do" class="comment-form">
						<div class="row">
							<div class="col-lg-4">
								<input type="text" name="tkt_id" value="<%=ticket.getTkt_id()%>"
									readonly>
							</div>
							<div class="col-lg-4">
								<input type="text" name="member_id"
									value="<%=member.getMember_id()%>">
							</div>
							<div class="col-lg-4">
								<input type="text" name="tkt_score" placeholder="請輸入評論分數">
							</div>
							<div class="col-lg-12 text-center">
								<textarea name="instruction" placeholder="請輸入留言"></textarea>
								<input type="hidden" name="action" value="insert_ticket_comment">
								<!-- 							<input type="submit" class="site-btn" value="送出"> -->
								<button type="submit" class="site-btn">Send Message</button>
								<br> <br>
							</div>
						</div>
					</form>
				</div>
				<%
				}
				%>
				<%
				}
				%>
			</div>
		</div>
		<br> <br>
		<!-- browse ticket detail Section End -->
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

	<script>
		const modalBtn = document.querySelector(".button");
		const modal = document.querySelector(".modal-overlay");

		modalBtn.addEventListener('click', function() {
			modal.classList.add("ain");
			modal.classList.add("open-modal");
		});

		const button_list = document.querySelector('#list');
		button_list.addEventListener('click', function() {
			button_list.classList.toggle('liked')
		})

		const aaa = document.querySelector('#aaa');

		button_list.addEventListener('click', function() {
			if (aaa.innerHTML == "加入收藏")
				aaa.innerHTML = "已收藏";
			else
				aaa.innerHTML = "加入收藏";
		})
	</script>
</body>

</html>