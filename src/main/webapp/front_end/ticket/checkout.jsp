<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.tktimg.model.*"%>
<%@ page import="com.tktlist.model.*"%>
<%@ page import="com.shoppinglist.model.*"%>
<%@ page import="com.ticketpromote.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
Vector<ShoppingList> cart = (Vector<ShoppingList>) session.getAttribute("cart");
Integer total_amount = (Integer) session.getAttribute("total_amount");
pageContext.setAttribute("cart", cart);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Time To Travel</title>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/createTktOrder.css"
	type="text/css">
<!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
<!-- <link rel="stylesheet" href="css/styleforheader_footer..css" type="text/css"> -->
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

.img-cart {
	display: block;
	max-width: 200px;
	height: auto;
	margin-left: auto;
	margin-right: auto;
}

table tr td {
	border: 1px solid #FFFFFF;
}

table tr th {
	background: #eee;
}

.panel-shadow {
	box-shadow: rgba(0, 0, 0, 0.3) 7px 7px 7px;
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
		<!-- CheckOut Section Begin -->
		<section class="creattktorder">
			<div class="container">
				<div class="tktorder-item">
					<div class="row">
						<div class="col-lg-6 py-3">
							<div class="creattktorder-menu">
								<!-- 								<nav class="tktordermenu"> -->
								<!-- 									<ul> -->
								<!-- 										<li><a href="./tktShoppingCart.html">�ʪ���</a></li> -->
								<!-- 										<li><img class="next" src="img/next.png" alt=""></li> -->
								<!-- 										<li class="active">�I��</li> -->
								<!-- 										<li><img class="next" src="img/next.png" alt=""></li> -->
								<!-- 										<li><a href="./mc_order.html">�q�ʧ���</a></li> -->
								<!-- 									</ul> -->
								<!-- 								</nav> -->
							</div>
						</div>
					</div>
				</div>

				<div id="buyer-board" class="board active">
					<div data-toggle="collapse" class="board-title">
						<div class="row">
							<div class="col-12 ">
								<h2>�q�椺�e</h2>
								<table class="table">
									<thead>
										<tr>
											<th>���~�W��</th>
											<th>���~�ƶq</th>
											<th>���~���</th>
											<th>���~�p�p</th>
											<th>���~�`��</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="shoppingList" items="${cart}" begin="0"
											end="${cart.size()-1}">
											<tr id="item1">
												<td><strong>${shoppingList.getTicket().tkt_name}</strong>
												<td>${shoppingList.tkt_amount}�i</td>
												<td>${shoppingList.getTicket().tkt_price }</td>
												<td>${shoppingList.getTicket().getTkt_price()*(shoppingList.getTkt_amount())}</td>
										</c:forEach>
										<jsp:useBean id="ticketPromoteSvc" scope="page"
											class="com.ticketpromote.model.TicketPromoteService" />
										<%
										List<TicketPromote> promoteList = ticketPromoteSvc.findByNumber(total_amount);
										pageContext.setAttribute("promoteList", promoteList);
										%>
										<tr>
											<td><font color="red">�i�ϥ��u�f��� </font></td>
										</tr>
										<FORM METHOD="post" ACTION="shoppinglist.do">
											<tr>
												<td style="text-align: right;"><select name="prom_id">
														<c:forEach var="promote" items="${promoteList}" begin="0"
															end="${promoteList.size()-1}">
															<option value="${promote.prom_id }">${promote.prom_name }</option>
														</c:forEach>
												</select> <br>
													<button type="submit" id="list" class="button button-like"
														style="display: inline-block">
														�ϥ�<input type="hidden" name="action" value="usepromote">
													</button></td>
											</tr>
										</FORM>
										<tr>
											<td></td>
										</tr>
										<tr>
											<td colspan="6" style="text-align: right;"><font
												size="+1">�`���B�G
													<h5>NTD$ ${total_amount}��</h5>
											</font></td>
										</tr>
										<%
										TicketPromote ticketpromote = (TicketPromote) session.getAttribute("ticketpromote");
										pageContext.setAttribute("ticketpromote", ticketpromote);
										%>

										<tr>
											<td colspan="6" style="text-align: right;"><font
												size="+2">�馩����B�G
													<h4>NTD${total_amount*ticketpromote.discount_amount} ��</h4>
											</font></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-12 col-lg-6 py-3">
								<h2>��g�q���p����T</h2>
								<form class="row g-3 needs-validation" METHOD="post"
									ACTION="shoppinglist.do">
									<div class="col-12">
										<label for="validationCustom01" class="form-label">�m�W</label>
										<input type="text" class="form-control" id="input_name"
											placeholder="�п�J�m�W" required>
									</div>
									<div class="col-12">
										<label for="validationCustom02" class="form-label">�q��</label>
										<input type="tel" class="form-control" id="input_tel"
											placeholder="�п�J�q��" required>
									</div>
									<div class="col-12">
										<label for="validationCustom03" class="form-label">�q�l�H�c</label>
										<input type="email" class="form-control" id="input_email"
											name="input_email" placeholder="�п�J�q�l�H�c" required>
									</div>
									<div class="col-12">
										<label for="validationCustom03" class="form-label">�a�}</label>
										<input type="text" class="form-control" id="input_address"
											placeholder="�п�J�a�}" required>
									</div>
									<div class="col-12">
										<select class="form-select" id="input_payment" required>
											<option selected disabled>�п�ܥ���覡</option>
											<option value="�H�Υd">�H�Υd</option>
										</select>
									</div>
									<div class="col-12">
										<button class="btn btn-primary sub_btn" type="submit"
											id="orderlist">
											�e�X<input type="hidden" name="action" value="orderlist">
											<input type="hidden" name="ServiceURL"
												value="https://payment-stage.ecpay.com.tw/Cashier/AioCheckOut/V5"
												class="form-control" /> <input type="hidden"
												name="MerchantTradeNo" value="oikidA0000001"
												class="form-control" /> <input type="hidden"
												name="MerchantTradeDate" value="2017/06/30 00:00:00"
												class="form-control" /> <input type="hidden"
												name="PaymentType" value="aio" class="form-control" /> <input
												type="hidden" name="TotalAmount" value="29999"
												class="form-control" /> <input type="hidden"
												name="TradeDesc" value="Desc" class="form-control" /> <input
												type="hidden" name="ItemName" value="aaa"
												class="form-control" /> <input type="hidden"
												name="ReturnURL"
												value="http://tn.sly-ha.com.tw/demo/hoyo/ECPay.php"
												class="form-control" /> <input type="hidden"
												name="ChoosePayment" value="ALL" />
										</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				</div>
		</section>
		<!-- CheckOut Section End -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->

		<!-- Js Plugins -->
		<script>
			// 			const DB_save = document.querySelector(`#save`);
			// 			DB_save.addEventListener('click', function() {
			// 				window.alert('�w�[�J�ʪ������òM��');
			// 			});

			const order = document.querySelector(`#orderlist`);
			order.addEventListener('click', function() {
				window.alert('�q��w����');
			});
		</script>
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




		<!--     <script> -->











		<!--     </script> -->
</body>

</html>