<%@page import="com.article.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%

%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/flaticon.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/nice-select.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/styleforheader_footerNEW.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>
<style>
.article_block {
	background-color: rgb(255, 255, 255);
	border: 1px solid rgb(210, 210, 210);
	margin-bottom: 10px;
	padding: 10px 20px;
	height: 100px;
	border-radius: 10px;
	line-height: 24px;
	letter-spacing: 3px;
}

.article_block:hover {
	box-shadow: 1px 3px 10px gray;
	cursor: pointer;
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
	<!--Article -->
	<div class="article">
		<a
			href="<%=request.getContextPath()%>/front_end/article/addArticle.jsp">
			<button
				style="width: 40px; height: 40px; top: 180px; left: 150px; border: 0; border-radius: 5px; background-color: rgb(249, 202, 143); position: sticky;">
				<i class="fa-solid fa-pen-to-square"
					style="color: rgb(133, 133, 133);"></i>
			</button>
		</a>
		<div class="container px-4 px-lg-5">
			<div class="row justify-content-center">
				<form action="article.do" method="post">
					<div class="mt-3 mb-3 d-flex align-items-center">
						<div>
							<select size="1" name="action">
								<option value="getArticleTitle">標題</option>
								<option value="getArticleContent">內容</option>
							</select>
						</div>
						<div class="d-flex align-items-center">
							<input type="text" name="choose" placeholder="輸入關鍵字"
								style="width: 40vw; height: 42px; padding: 10px; border-radius: 6px 0px 0px 6px; border: 0.5px solid rgb(209, 209, 209);">
							<button type="submit"
								style="width: 5vw; height: 42px; border-radius: 0px 10px 10px 0px; border: 0.5px solid rgb(209, 209, 209);">
								<i class="fa-sharp fa-solid fa-magnifying-glass"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="container">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-8 col-lg-8">
					<div class="article_body">
						<c:forEach var="articleVO" items="${list}">
							<div class="article_block">
								<div style="font-size: 18px; font-weight: 700; width: 600px;">${articleVO.article_title }</div>
								<div
									style="font-size: 14px; color: gray; text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">${articleVO.article_content }</div>
								<div style="display: flex;">
									<form action="<%=request.getContextPath()%>/front_end/article/article.do">
										<div>
											<input type="submit"
												style="font-size: 12px; color: gray; border: 0px; background-color: white;"
												value="${articleVO.member_id_byMember.member_name}">
											<input type="hidden" name="action" value="getMemberId">
											<input type="hidden" name="post_member_id"
												value="${articleVO.member_id_byMember.member_id}"> <span
												style="font-size: 5px; margin-right: 5px;">於<fmt:formatDate
													value="${articleVO.article_time }"
													pattern="yyyy/MM/dd HH:mm:ss" />發佈
											</span>
										</div>
									</form>
									<form action="<%=request.getContextPath()%>/front_end/article/article.do">
										<div style="position: absolute; right: 5vw;">
											<input type="submit"
												style="border: 0px; background-color: white; color: gray;"
												value="查看文章"> <input type="hidden" name="action"
												value="article_data"> <input type="hidden"
												name="post_member_id"
												value="${articleVO.member_id_byMember.member_id}"> <input
												type="hidden" name="article_id"
												value="${articleVO.article_id }">
										</div>
									</form>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->
	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
</body>

</html>