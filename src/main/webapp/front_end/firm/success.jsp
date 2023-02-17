<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.firm.model.*"%>
<%@ page import="com.firmtype.model.*"%>

<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
 <meta http-equiv="refresh" content="5;url= <%=request.getContextPath()%>/front_end/member/home.jsp">
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
	href="<%=request.getContextPath()%>/front_end/firm/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/firm/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/firm/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/firm/css/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/firm/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/firm/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/firm/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/firm/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/firm/css/slicknav.min.css"
	type="text/css">
<!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/firm/css/styleforheader_footerNEW.css"
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
		<!-- Rooms Section Begin -->
		<div class="container">

			<div class="row justify-content-center">
				<div class="col-6">
					<div class="text">
						<br> <br> <br>
						<p style="font-size: 30px; font-weight: 500; color: #dfa974;">${successViewMsgs}</p>
						<br> <br> <br>
					</div>
				</div>
			</div>
		</div>
		<!-- Rooms Section End -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->

	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/firm/js/main.js"></script>
</body>

</html>