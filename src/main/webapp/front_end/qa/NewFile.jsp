<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.qaclass.model.*"%>
<%@ page import="java.util.*"%>

<%
QaclassService qaclassSvc = new QaclassService();
List<QaclassVO> list = qaclassSvc.getAll();
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/slicknav.min.css"
	type="text/css">
<!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/qa/css/styleforheader_footerNEW.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.3/font/bootstrap-icons.min.css">
<style>
img.tttlogo {
	width: 60px;
	height: 60px;
}

.useriocn {
	border: 1px solid red;
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

.qa {
	margin-right: auto;
	margin-left: auto;
	padding-right: 15px;
	padding-left: 15px;
	padding-bottom: 80px;
	width: 100%;
	max-width: 1170px;
}

#qa1 {
	height: 150px;
	padding: 15px 15px 15px 15px;
}

.qa1 {
	display: block;
	border: 1px solid #eaeaea;
	border-radius: 10px;
	height: 100%;
	padding: 45px;
	transition: box-shadow .3s;
	background: #fff;
	-webkit-transition: margin 0.5s ease-out;
	-moz-transition: margin 0.5s ease-out;
	-o-transition: margin 0.5s ease-out;
	background-color: #DFA974;
}

.qa1:hover {
	box-shadow: 0 0 11px rgba(33, 33, 33, .2);
	cursor: pointer;
	margin-top: -7px;
}

#h4 {
	color: white;
}

#i {
	padding-right: 3%;
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

		<!-- Qa Section Begin -->
		<div class="breadcrumb-section">
			<div class="container">
				<div class="row">
					<div class="qa">
						<h3>常見問題分類</h3>
						<div class="row">
							<c:forEach var="qaClassVO" items="${list}">
								<div id="qa1" class="col-lg-6">
									<a
										href="<%=request.getContextPath() %>/front_end/qa/qa.do?qa_class_id=${qaClassVO.qa_class_id}&action=showDetail">
										<div class="qa1">
											<h4 id="h4">${qaClassVO.qa_class_name}</h4>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Qa Section End -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->
	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/front_end/qa/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/qa/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/qa/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/qa/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/qa/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/qa/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/qa/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/qa/js/main.js"></script>
</body>

</html>