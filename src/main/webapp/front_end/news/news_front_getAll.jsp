<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
NewsService newsSvc = new NewsService();
List<NewsVO> list = newsSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
// NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
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
<link href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css"
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
	margin: 20px 200px;
}

tbody.newscol {
	max-height: 20px;
	overflow: auto;
}

tr {
	border: 3px solid black;
	height: 20px;
	overflow: auto;
}

.detail_1 {
	color: black;
	overflow: hidden; /* 單行..省略 */
	white-space: nowrap; /* 單行..省略 */
	text-overflow: ellipsis; /* 單行..省略 */
}

.detail_1:hover {
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
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/front_end/news/news.do">
				<div class="row">
					<div class="12" style="margin: ">
						<b>搜尋編號:</b> <input name="news_no" type="search"
							class="rounded-pill" style="border: 1px solid black" id="search"
							placeholder="搜索..." /> <input type="submit" class="rounded-pill"
							value="搜尋"> <input type="hidden" name="action"
							value="getOne_For_FrontDisplay">

					</div>
				</div>
			</FORM>
<div class="row">
<div col-12>
			<table data-toggle="table" data-sortable="true"
				data-sort-class="table-active" data-show-toggle="true"
				data-show-columns-toggle-all="true" enctype="multipart/form-data">
				<thead>

					<tr class="row">

						<th class="col-1 fht-cell" data-field="news_id"
							data-sortable="true">編號</th>
						<th class="col-4 fht-cell" data-field="news_title"
							data-sortable="true">標題</th>
						<th class="col-1 fht-cell" data-field="news_content"
							data-sortable="true">詳細內容</th>
						<th class="col-2 fht-cell" data-field="news_pic"
							data-sortable="true">圖片</th>
						<th class="col-2 fht-cell" data-field="news_time"
							data-sortable="true">發表時間</th>


					</tr>
				</thead>

				<tbody class=" newscol">
					<%@ include file="page1.file"%>
					<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${newsVO.news_no}</td>
							<td><a href="" class="detail_1">${newsVO.news_title}</a></td>

							<td><FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/front_end/news/news.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="詳細"> <input type="hidden"
										name="news_no" value="${newsVO.news_no}"> <input
										type="hidden" name="action" value="getOne_For_FrontDisplay">
								</FORM></td>

							<td><img
								src="${pageContext.request.contextPath}/front_end/news/news.do?action=getImg&news_no=${newsVO.news_no}"></td>

							<td><fmt:formatDate value="${newsVO.news_time}" type="both"
									dateStyle="long" timeStyle="DEFAULT" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<%@ include file="page2.file"%>
		</div>
		</div>
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

</body>

</html>