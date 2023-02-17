<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.tktimg.model.*"%>
<%@ page import="com.tktlist.model.*"%>
<%@ page import="com.shoppinglist.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
Vector<ShoppingList> cart = (Vector<ShoppingList>) session.getAttribute("cart");
pageContext.setAttribute("cart", cart);
MemberVO member = (MemberVO) session.getAttribute("memberVO");
pageContext.setAttribute("member", member);
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

		<!-- CartList Section Begin -->
		<link rel="stylesheet" type="text/css"
			href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
		<div class="container bootstrap snippets bootdey">
			<div class="col-md-12 col-sm-12 content">
				<div class="row">
					<div class="col-md-12">
						<ol class="breadcrumb">
							<li><a
								href="<%=request.getContextPath()%>/front_end/ticket/browseTicket.jsp">票券首頁</a></li>
							<li class="active">/購物車</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-info panel-shadow">
							<div class="panel-heading">
								<h3>
									<img class="img-circle img-thumbnail"
										src="<%=request.getContextPath()%>/front_end/member/member.do?action=getImg&member_id=${member.getMember_id().toString()}"
										width=120px height=120px>
									<%=member.getMember_name()%>
								</h3>
							</div>
							<br>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th>產品名稱</th>
												<th>產品圖片</th>
												<th>產品數量</th>
												<th>產品單價</th>
												<th>產品小計</th>
												<th></th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<%
											if (cart != null && (cart.size() > 0)) {
											%>
											<jsp:useBean id="tktimgSvc2" scope="session"
												class="com.tktimg.model.TktImgService" />
											<c:forEach var="shoppingList" items="${cart}" begin="0"
												end="${cart.size()-1}">
												<tr id="item1">
													<%-- 												<td><strong><%=cart.get(0).getTicket().getTkt_name() %></strong> --%>
													<td><strong>${shoppingList.getTicket().tkt_name}</strong>
													<td><img
														src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimgSvc2.findByTktId(shoppingList.getTicket().getTkt_id()).get(0).tkt_img_id}"
														class="img-cart" width=300px height=250px></td>
													<td>${shoppingList.tkt_amount}張</td>
													<td>${shoppingList.getTicket().tkt_price }</td>
													<td>${shoppingList.getTicket().getTkt_price()*(shoppingList.getTkt_amount())}</td>
													<td>
														<form method="post" action="shoppinglist.do"
															style="margin-bottom: 0px;">
															<input type="submit" value="修改票券數量"> <br> <br>
															<input type="text" name="tkt_amount"
																placeholder="請輸入票券數量"><input type="hidden"
																name="tkt_id" value="${shoppingList.tkt_id}"><input
																type="hidden" name="action" value="getOne_For_Update">
														</form>
													</td>
													<td>
														<form method="post" action="shoppinglist.do"
															style="margin-bottom: 0px;">
															<input type="submit" value="刪除票券"> <input
																type="hidden" name="tkt_id"
																value="${shoppingList.tkt_id}"> <input
																type="hidden" name="tkt_amount"
																value="${shoppingList.tkt_amount}"> <input
																type="hidden" name="action" value="delete">
														</form>
													</td>
												</tr>
												<!-- 												<tr> -->
												<!-- 													<td colspan="6">&nbsp;</td> -->
												<!-- 												</tr> -->
											</c:forEach>
											<%
											}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<a
							href="<%=request.getContextPath()%>/front_end/ticket/browseTicket.jsp"
							class="btn btn-success"><span
							class="glyphicon glyphicon-arrow-left"></span>&nbsp;回商品瀏覽頁面</a> <a
							href="<%=request.getContextPath()%>/front_end/ticket/cartDB_Checkout.jsp"
							class="btn btn-primary pull-right">下一步<span
							class="glyphicon glyphicon-chevron-right"></span></a> <br> <br>
						<br>
					</div>
				</div>
			</div>
		</div>
		<!-- CartList Section End -->
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