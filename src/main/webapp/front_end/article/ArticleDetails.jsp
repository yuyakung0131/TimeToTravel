<%@page import="com.article.model.*"%>
<%@page import="com.comment.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%
CommentVO commentVO_data = (CommentVO) request.getAttribute("commentVO_data");
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

#comment_member {
	width: 100px;
	height: 100px;
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
		<!-- article details -->
		<div class="article_details mt-3 mb-3">
			<div style="position: sticky; top: 180px; left: 10vw; width: 40px;">
				<a
					href="<%=request.getContextPath()%>/front_end/article/addArticle.jsp">
					<button
						style="width: 40px; height: 40px; margin-bottom: 5px; border: 0; border-radius: 5px; background-color: rgb(249, 202, 143);">
						<i class="fa-solid fa-pen-to-square"
							style="color: rgb(133, 133, 133);"></i>
					</button>
				</a>
				<button id="comment_btn" type="submit"
					style="width: 40px; height: 40px; margin-bottom: 5px; border: 0; border-radius: 5px; background-color: rgb(249, 202, 143);">
					<i class="fa-solid fa-comment-dots" style="color: gray;"></i>
				</button>
			</div>
			<header class="masthead">
				<div class="container position-relative px-4 px-lg-5">
					<div
						class="row gx-4 gx-lg-5 justify-content-center align-items-center">
						<div class="col-md-10 col-lg-8 col-xl-7">
							<div class="post-heading">
								<h4 class="mb-3 mt-3">${articleVO.article_title }</h4>
							</div>
						</div>
						<div class="col-md-10 col-lg-8"
							style="border: 1px solid rgb(178, 177, 177);"></div>
					</div>
				</div>
			</header>
			<article class="mb-4 mt-4">
				<div class="container px-4 px-lg-5">
					<div class="row justify-content-end">
						<div class="col-2">
							<c:choose>
								<c:when
									test="${articleVO.articleReportVO.article_reportprocess_state!=1}">
									<form action="<%=request.getContextPath()%>/front_end/article/article.do">
										<input
											type="${(memberVO.member_id==memberVO_post.member_id)?'submit':'hidden' }"
											value="編輯"
											style="width: 60px; height: 40px; right: 3%; bottom: 4%; border-radius: 10px; background-color: rgb(243, 194, 135); color: white; border: 0px;">
										<input type="hidden" name="action" value="update_data">
										<input type="hidden" name="article_id"
											value="${articleVO.article_id }"> <input
											type="hidden" name="post_member_id"
											value="${ memberVO_post.member_id}">
									</form>
								</c:when>
							</c:choose>
						</div>
					</div>
					<div class="row gx-4 gx-lg-5 justify-content-center">
						<div class="col-8">
							<p style="font-size: 18px;">${articleVO.articleReportVO.article_reportprocess_state==1?"此文章已被使用者申訴，已被管理員屏蔽":articleVO.article_content }</p>
						</div>
					</div>
					<div class="row">
						<div class="col-6"></div>
						<div class="col-4">
							<c:choose>
								<c:when
									test="${articleVO.articleReportVO.article_reportprocess_state!=1}">
									<form action="article.do">
										<br> 由 <input type="hidden" name="action"
											value=getMemberId> <input type="hidden"
											name="post_member_id" value="${memberVO_post.member_id }">
										<input
											style="color: gray; border: 0px; background-color: white;"
											type="submit" value="${memberVO_post.member_name}"
											style="border: 0px; background-color: white;"> 發佈於
										<fmt:formatDate value="${articleVO.article_time }"
											pattern="yyyy/MM/dd HH:mm:ss" />
									</form>
								</c:when>
							</c:choose>
						</div>
						<div class="col-2">
							<c:choose>
								<c:when
									test="${articleVO.articleReportVO.article_reportprocess_state!=1}">
									<form method="post" action="<%=request.getContextPath()%>/front_end/article/articleReport.do">
										<input type="hidden" name="action" value="articleReport">
										<select name="reportReason" class="articleReportReason">
											<option value="-1" disabled selected>檢舉此文章</option>
											<option value="0">惡意洗版、重複張貼</option>
											<option value="1">包含未成年、裸露、色情內容</option>
											<option value="2">仇恨言論</option>
											<option value="3">廣告商業宣傳</option>
											<option value="4">其他</option>
										</select> <input type="hidden" name="member_id" class="member_id"
											value="${memberVO.member_id}"> <input type="hidden"
											name="article_id" value="${articleVO.article_id }"> <input
											type="hidden" name="post_member_id"
											value="${memberVO_post.member_id }"> <br> <br>
										<input class="articleReportSubmit" type="submit" value="確定"
											style="width: 60px; height: 40px; right: 3%; bottom: 4%; border-radius: 10px; background-color: rgb(243, 194, 135); color: white; border: 0px;">
									</form>
								</c:when>
							</c:choose>
						</div>
					</div>
					<div class="row justify-content-center mt-4 mb-4">
						<div class="col-md-10 col-lg-8"
							style="border: 1px solid rgb(178, 177, 177);"></div>
					</div>
				</div>
			</article>
			<div class="article_comment">
				<div class="container px-4 px-lg-5">
					<c:forEach var="commentVO" items="${commentVOList}">
						<div class="row justify-content-end">
							<div class="col-2">
								<c:choose>
									<c:when
										test="${commentVO.commentReportVO.comment_reportprocess_state!=1}">
										<form action="<%=request.getContextPath()%>/front_end/article/article.do">
											<input type="hidden" name="comment_id"
												value="${commentVO.comment_id }"> <input
												type="hidden" name="post_member_id"
												value="${memberVO_post.member_id }"> <input
												type="hidden" name="login_member_id"
												value="${memberVO.member_id }"> <input type="hidden"
												name="article_id" value="${articleVO.article_id }">
											<input type="hidden" name="action"
												value="comment_update_data"> <input
												type="${memberVO.member_id== commentVO.member_id_byMember.member_id?'submit':'hidden'}"
												value="編輯"
												style="width: 60px; height: 40px; right: 3%; bottom: 4%; border-radius: 10px; background-color: rgb(243, 194, 135); color: white; border: 0px;">
										</form>
									</c:when>
								</c:choose>
							</div>
						</div>
						<div class="row gx-4 gx-lg-5 justify-content-center">
							<div class="col-1"></div>
							<div class="col-2">
								<img
									src="${pageContext.request.contextPath}/front_end/member/member.do?action=getImg&member_id=${commentVO.member_id}"
									id="comment_member">
							</div>
							<div class="col-6">
								<p class="mb-0">${commentVO.commentReportVO.comment_reportprocess_state==1?"此留言已被使用者申訴，已被管理員屏蔽":commentVO.comment_content }</p>
							</div>
							<div class="col-1"></div>
						</div>
						<div class="row">
							<div class="col-6"></div>
							<div class="col-4">
								<p style="margin-top: 15px; margin-bottom: 0;">
									<c:choose>
										<c:when
											test="${commentVO.commentReportVO.comment_reportprocess_state!=1}">
											<form action="<%=request.getContextPath()%>/front_end/article/article.do">
												<br> 由 <input type="hidden" name="action"
													value=getMemberId_comment> <input type="hidden"
													name="comment_member_id" value="${commentVO.member_id }">
												<input type="submit"
													style="color: gray; border: 0px; background-color: white;"
													value="${commentVO.member_id_byMember.member_name }"
													style="border:0px; background-color:white;"> 留言於
												<fmt:formatDate value="${commentVO.comment_time }"
													pattern="yyyy/MM/dd HH:mm:ss" />
											</form>
										</c:when>
									</c:choose>
								</p>
							</div>
							<div class="col-2">
								<c:choose>
									<c:when
										test="${commentVO.commentReportVO.comment_reportprocess_state!=1}">
										<form action="<%=request.getContextPath()%>/front_end/article/commentReport.do">
											<input type="hidden" name="action" value="commentReport">
											<select name="reportReason" class="commentReportReason">
												<option value="-1" disabled selected>檢舉此留言</option>
												<option value="0">惡意洗版、重複張貼</option>
												<option value="1">包含未成年、裸露、色情內容</option>
												<option value="2">仇恨言論</option>
												<option value="3">廣告商業宣傳</option>
												<option value="4">其他</option>
											</select> <input type="hidden" name="member_id" class="member_id"
												value="${memberVO.member_id}"> <input type="hidden"
												name="comment_id" value="${commentVO.comment_id }">
											<input type="hidden" name="post_member_id"
												value="${memberVO_post.member_id }"> <input
												type="hidden" name="article_id"
												value="${articleVO.article_id }"> <br> <br>
											<input type="submit" class="commentReportSubmit" value="確定"
												style="width: 60px; height: 40px; right: 3%; bottom: 4%; border-radius: 10px; background-color: rgb(243, 194, 135); color: white; border: 0px;">
										</form>
									</c:when>
								</c:choose>
							</div>
						</div>
						<div class="row justify-content-center mt-0 mb-4">
							<div class="col-md-10 col-lg-8"
								style="border: 1px solid rgb(178, 177, 177);"></div>
						</div>
					</c:forEach>
					<div id="comment_area" style="display: none;">
						<div class="row gx-4 gx-lg-5 justify-content-center">
							<div>
								<form action="<%=request.getContextPath()%>/front_end/article/article.do">
									<textarea name="comment_content" style="resize: none;"
										cols="60" rows="5" placeholder="說點甚麼..."></textarea>
									<br> <input type="hidden" name="login_member_id"
										value="${memberVO.member_id }"> <input type="hidden"
										name="article_id" value="${articleVO.article_id }"> <input
										type="hidden" name="post_member_id"
										value="${memberVO_post.member_id}">
									<button type="submit" name="action" value="comment_insert"
										style="border-radius: 5px; color: white; background-color: #f7c892; border: 0px;">送出留言</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
		if (commentVO_data != null) {
		%>
		<jsp:include page="updateComment.jsp" />
		<%
		}
		%>
		<!-- Article Details  End-->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->

	<!-- Js Plugins -->
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery.magnific-popup.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery.nice-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/jquery.slicknav.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/sweetalert2.all.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/article/js/report.js"></script>
	<script>
						$(document).ready(function() {
							$("#comment_btn").click(function() {
								$("#comment_area").toggle();
							});
						});
					</script>
</body>

</html>