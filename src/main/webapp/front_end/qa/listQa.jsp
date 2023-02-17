<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.qa.model.*"%>
<%@ page import="java.util.*"%>

<%
// QaService qaSvc = new QaService();
// List<QaVO> list = qaSvc.getAll();
// pageContext.setAttribute("list", list);
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

.breadcrumb-section {
	padding-bottom: 50px;
}

.ti {
	padding-bottom: 3%;
}

#a {
	color: #000;
}

#a:hover, a:focus {
	text-decoration: none;
	outline: none;
	color: #dfa974;
}

.qa {
	margin-right: auto;
	margin-left: auto;
	margin-bottom: 100px;
	padding-right: 15px;
	padding-left: 15px;
	width: 100%;
	max-width: 1170px;
	/* border-style: solid; */
}

.back {
	margin-right: auto;
	margin-left: auto;
	padding-right: 15px;
	padding-left: 15px;
	padding-bottom: 15px;
	width: 100%;
	max-width: 1170px;
	/* border-style: solid; */
}

#right {
	border: 1px solid #e0e0e0;
	border-radius: 10px;
}

.TibameCGA105G3{
    color: #007bff;
    text-decoration: none;
    background-color: transparent;
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
					<div class="col-lg-12">
						<div class="ti">
							<h3>${qaClassVO.qa_class_name}相關</h3>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="back">
			<a id="a"
				href="<%=request.getContextPath()%>/front_end/qa/NewFile.jsp">常見問題</a>
			<i class="bi bi-chevron-right"></i> <span>${qaClassVO.qa_class_name}</span>
		</div>

		<div class="qa">
			<div class="row">
				<div id="left" class="col-lg-4">
					<div class="accordion" id="accordionExample">
						<div class="card">
							<div class="card-header" id="headingOne">
								<h2 class="mb-0">
									<button class="btn" type="button" data-toggle="collapse"
										data-target="#collapseOne" aria-expanded="true"
										aria-controls="collapseOne">
										<a id="a"
											href="<%=request.getContextPath()%>/front_end/qaclass/qaclass.do?qa_class_id=1&action=showDetail">帳號相關</a>
									</button>
								</h2>
							</div>


							<div class="card-header" id="headingTwo">
								<h2 class="mb-0">
									<button class="btn collapsed" type="button"
										data-toggle="collapse" data-target="#collapseTwo"
										aria-expanded="false" aria-controls="collapseTwo">
										<a id="a"
											href="<%=request.getContextPath()%>/front_end/qaclass/qaclass.do?qa_class_id=2&action=showDetail">住宿相關</a>
									</button>
								</h2>
							</div>


							<div class="card-header" id="headingThree">
								<h2 class="mb-0">
									<button class="btn collapsed" type="button"
										data-toggle="collapse" data-target="#collapseThree"
										aria-expanded="false" aria-controls="collapseThree">
										<a id="a"
											href="<%=request.getContextPath()%>/front_end/qaclass/qaclass.do?qa_class_id=3&action=showDetail">票券相關</a>
									</button>
								</h2>
							</div>
							<div class="card-header" id="headingFour">
								<h2 class="mb-0">
									<button class="btn collapsed" type="button"
										data-toggle="collapse" data-target="#collapseFour"
										aria-expanded="false" aria-controls="collapseFour">
										<a id="a"
											href="<%=request.getContextPath()%>/front_end/qaclass/qaclass.do?qa_class_id=4&action=showDetail">行程相關</a>
									</button>
								</h2>
							</div>
							<div class="card-header" id="headingFive">
								<h2 class="mb-0">
									<button class="btn collapsed" type="button"
										data-toggle="collapse" data-target="#collapseFive"
										aria-expanded="false" aria-controls="collapseFive">
										<a id="a"
											href="<%=request.getContextPath()%>/front_end/qaclass/qaclass.do?qa_class_id=5&action=showDetail">付款相關</a>
									</button>
								</h2>
							</div>
							<div class="card-header" id="headingSix">
								<h2 class="mb-0">
									<button class="btn collapsed" type="button"
										data-toggle="collapse" data-target="#collapseSix"
										aria-expanded="false" aria-controls="collapseSix">
										<a id="a"
											href="<%=request.getContextPath()%>/front_end/qaclass/qaclass.do?qa_class_id=6&action=showDetail">退款相關</a>
									</button>
								</h2>
							</div>
						</div>
					</div>
				</div>
				<div id="right" class="col-lg-8">
					<div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">
						<div class="tab-pane fade show active" id="qa-1-1" role="tabpanel"
							aria-labelledby="nav-mc-qa-1">
							<c:forEach var="qaVO" items="${qaList}">

								<h4>${qaVO.question}</h4>
								<p>${qaVO.answer}</p>

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