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

<style>
.firm-info select {
	margin-left: 12px;
	margin-bottom: 10px;
	width: 90%;
	height: 40px;
	border-radius: 5px;
	border: 1px solid #b3b3b3b3;
	text-indent: 2px;
}

.firm-info select:focus {
	border: 2px solid #dfa974;
}

.firm-info select option {
	width: 180px;
	border-radius: 5px;
	border: 1px solid #b3b3b3b3;
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
		<!-- Firm Section Begin -->
		<div class="container">
			<div class="row justify-content-center mt-3">
				<div class="col-12">
					<div class="text">
						<h3>廠商申請</h3>
						<p>輸入以下資料，通過審核後，即可與本平台合作</p>
					</div>
				</div>
			</div>
			<form method="post"
				action="<%=request.getContextPath()%>/front_end/firm/firm.do"
				enctype="multipart/form-data">
				<div class="row mt-0 mb-2 justify-content-center">
					<div class="col-12">
						<table>
							<tr>
								<td>公司名稱:</td>
								<td><input type="text" name="firm_name" size="55"
									value="${param.firm_name}"></td>
								<td style="color: red;">${errorMsgs.firm_name}</td>
							</tr>
							<tr>
								<td>負責人姓名:</td>
								<td><input type="text" name="firm_prim" size="55"
									value="${param.firm_prim}"></td>
								<td style="color: red;">${errorMsgs.firm_prim}</td>
							</tr>
							<tr>
								<td>聯絡人姓名:</td>
								<td><input type="text" name="firm_poc" size="55"
									value="${param.firm_poc}"></td>
								<td style="color: red;">${errorMsgs.firm_poc}</td>
							</tr>
							<tr>
								<td>聯絡人電話:</td>
								<td><input type="text" name="firm_phone" size="55"
									value="${param.firm_phone}"></td>
								<td style="color: red;">${errorMsgs.firm_phone}</td>
							</tr>
							<tr>
								<td>聯絡人e-mail:</td>
								<td><input type="text" name="firm_email" size="55"
									value="${param.firm_email}"></td>
								<td style="color: red;">${errorMsgs.firm_email}</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row mt-0 mb-2 justify-content-center">
					<div class="col-12">
						<table class="firm-info">
							<tr>
								<td>公司登記地址:</td>
								<td><input type="hidden" name="selectedLabel_city"
									id="selectedLabel_city"> <select id="city" name="city"
									onchange="javascript:getSelectedLabel_city(this);">
										<option value="not-choose" disabled selected>-請選擇-</option>
								</select></td>
								<td><input type="hidden" name="selectedLabel_area"
									id="selectedLabel_area"> <select id="area" name="area"
									onchange="javascript:getSelectedLabel_area(this);">
										<option value="not-choose" disabled selected>-請先選擇縣市-</option>
								</select></td>
								<td><input type="text" name="firm_regist_add" size="22"
									placeholder="詳細地址" value="${param.firm_regist_add}"></td>
								<td style="color: red;">${errorMsgs.firm_regist_add}</td>
							</tr>
							<tr>
								<td>公司營業地址:</td>
								<td><input type="hidden" name="selectedLabel_city2"
									id="selectedLabel_city2"> <select id="city2"
									name="city2"
									onchange="javascript:getSelectedLabel_city2(this);">
										<option value="not-choose" disabled selected>-請選擇-</option>
								</select></td>
								<td><input type="hidden" name="selectedLabel_area2"
									id="selectedLabel_area2"> <select id="area2"
									name="area2"
									onchange="javascript:getSelectedLabel_area2(this);">
										<option value="not-choose" disabled selected>-請先選擇縣市-</option>
								</select>
								<td><input type="text" name="firm_operate_add" size="22"
									placeholder="詳細地址" value="${param.firm_operate_add}"></td>
								<td style="color: red;">${errorMsgs.firm_operate_add}</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row mb-2 justify-content-cente">
					<div class="col-12 mx-auto">
						申請書: <input type="file" name="firm_review_petition">
					</div>
				</div>
				<jsp:useBean id="firmTypeSvc" scope="page"
					class="com.firmtype.model.FirmTypeService" />
				<div class="row mb-5 justify-content-center">
					<div class="col-12">
						<p>申請類別:</p>
						<select name="firmtype_id">
							<c:forEach var="firmTypeVO" items="${firmTypeSvc.all}">
								<option value="${firmTypeVO.firmtype_id}"
									${(param.firmtype_id==firmTypeVO.firmtype_id)? 'selected':'' }>${firmTypeVO.firmtype_name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row mb-2 justify-content-center">
					<div class="col-2">
						<input type="hidden" name="action" value="insert"> <input
							type="submit" value="送出"
							style="width: 60px; height: 40px; right: 3%; bottom: 4%; border-radius: 10px; background-color: rgb(243, 194, 135); color: white; border: 0px;">
					</div>
				</div>
			</form>
		</div>
		<!-- Firm Section End -->
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
<%-- 	    <script src="<%=request.getContextPath()%>/front_end/firm/js/jquery.nice-select.min.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/firm/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/firm/js/main.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/front_end/firm/js/firm.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</body>

</html>