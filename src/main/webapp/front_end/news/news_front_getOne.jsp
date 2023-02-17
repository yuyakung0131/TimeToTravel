<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
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
	href="<%=request.getContextPath()%>/front_end/news/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/news/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/news/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/news/css/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/news/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/news/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/news/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/news/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/news/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/news/css/styleforheader_footerNEW.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/member_center.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/home_main.css"
	type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"></script>

<style>

a:hover{
 text-decoration:none;
}

div.news_range {
	/* border:1px solid black; */
	margin: 5px 300px;
}

tr {
	border: 3px solid black;
	height: 20px;
	overflow: auto;
}

div.news_range a {
	color: #dfa974;
}

div.news_range a:hover {
	color: #dfa974;
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
		<!-- 最新消息前台 start -->
		<div class="news_range">
			<h3>
    <a href="news_front_getAll.jsp" style="margin: auto; ">點擊回上一頁</a>
   </h3>
			<table data-toggle="table" data-sortable="true"
				data-sort-class="table-active" data-show-toggle="true">
				<thead>
					<tr class="row">
						
						<th class="col-3 fht-cell" data-field="news_title"
							data-sortable="true">標題</th>
						<th class="col-5 fht-cell" data-field="news_content"
							data-sortable="true">詳細內容</th>
						<th class="col-2 fht-cell" data-field="news_time"
							data-sortable="true">發表時間</th>
						<th class="col-2 fht-cell" data-field="news_pic"
							data-sortable="true">照片</th>
					</tr>
				</thead>
				<tbody class=" newscol">
					<tr>
						
						<td>${newsVO.news_title}</td>
						<td>${newsVO.news_content}</td>
						<td><fmt:formatDate value="${newsVO.news_time}" type="both"
								dateStyle="long" timeStyle="DEFAULT" /></td>
						<td><img
							src="${pageContext.request.contextPath}/front_end/news/news.do?action=getImg&news_no=${newsVO.news_no}"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 最新消息前台 end -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->

	<!-- Js Plugins -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script
		src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/news/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/news/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/news/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/news/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/news/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/news/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/news/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/news/js/main.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/front_end/member/js/meberCenter.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/homeMember.js"></script>
</body>

</html>