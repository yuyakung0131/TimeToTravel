<%@page import="com.article.model.*"%>
<%@page import="com.comment.model.*"%>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/nice-select.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/article/css/styleforheader_footerNEW.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>
<style>
.article_block {
	background-color: rgb(255, 255, 255);
	border: 1px solid rgb(210, 210, 210);
	margin-bottom: 5px;
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
	<div class="row gx-4 gx-lg-5 mb-3 justify-content-center">
		<form action="<%=request.getContextPath()%>/front_end/article/article.do">
			<div>
				<textarea name="comment_content" id="" cols="60" rows="5"
					autocomplete="off" style="resize: none;" placeholder="說點甚麼...">${commentVO_data.comment_content }</textarea>
				<br> <input type="hidden" name="login_member_id"
					value="${memberVO.member_id }"> <input type="hidden"
					name="post_member_id" value="${ memberVO_post.member_id}">
				<input type="hidden" name="article_id"
					value="${ articleVO.article_id}"> <input type="hidden"
					name="action" value="comment_update"> <input type="hidden"
					name="comment_id" value="${commentVO_data.comment_id}">
			</div>
			<div style="display: flex;">
				<button type="submit"
					style="margin-right: 10px; border-radius: 5px; color: white; background-color: #f7c892; border: 0px;">送出留言</button>
				<a
					href="<%=request.getContextPath()%>/front_end/article/article.do?action=article_data&post_member_id=${memberVO_post.member_id}&article_id=${articleVO.article_id}"
					style="padding: 5px; border-radius: 5px; color: white; background-color: #f7c892; border: 0px;">取消</a>
			</div>
	</form>
	</div>
	<!-- Js Plugins -->
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery.magnific-popup.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery.nice-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery.slicknav.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/main.js"></script>
</body>

</html>