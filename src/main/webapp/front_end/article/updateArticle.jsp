<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
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
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/flaticon.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/styleforheader_footerNEW.css"
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
		<!-- Article Post -->
		<div class="article_post">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-10"
						style="background-color: rgb(255, 255, 255); height: 100vh; margin: 20px 0px; padding: 20px; border-radius: 10px; box-shadow: 1px 3px 10px gray;">
						<form action="<%=request.getContextPath()%>/front_end/article/article.do" method="post"
							enctype="multipart/form-data">
							<div class="title_post">
								<input type="text" name="article_title"
									value="${articleVO.article_title }" placeholder="標題"
									autocomplete="off"
									style="width: 100%; height: 8vh; font-size: 20px; padding: 15px; margin: 10px 0px; border-radius: 10px; border: 2px solid rgb(248, 239, 210);">
							</div>
							${errorMsgs.article_title }
							<div class="content_post">
								<textarea name="article_content" cols="30" rows="10"
									placeholder="內容"
									style="width: 100%; height: 70vh; padding: 15px; margin: 10px 0px; border-radius: 10px; border: 2px solid rgb(248, 239, 210); resize: none;">${articleVO.article_content }</textarea>
							</div>
							${errorMsgs.article_content } <input type="hidden"
								name="article_id" value="${articleVO.article_id }">
							<button type="submit" name="action" value="update"
								style="position: absolute; width: 60px; height: 40px; right: 3%; bottom: 4%; border-radius: 10px; background-color: rgb(243, 194, 135); color: white; border: 0px;">修改</button>
							 <input
								type="hidden" name="article_id" value="${articleVO.article_id }">
							<input type="hidden" name="post_member_id"
								value="${memberVO_post.member_id }">
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Article Post End -->
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