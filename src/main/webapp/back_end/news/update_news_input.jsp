<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
//PromotionServlet.java (Concroller) 存入req的promotionVO物件 
//(包括幫忙取出的promotionVO, 也包括輸入資料錯誤時的promotionVO物件)
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"> -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="css/back.css" type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<style>
</style>
</head>

<body>

	<div class="container-all">
		<!-- 左邊 -->
		<div class="container-left">
			<!-- 導覽列 -->
			<jsp:include page="../sidebar.jsp"></jsp:include>
		</div>
		<!-- 右邊 -->
		<div class="container-right">
			<!-- 右上 -->
			<jsp:include page="../header.jsp"></jsp:include>
			<!-- 右下 -->
			<section id="first">
				<div class="container right-down">
					<!-- 段1 -->
					<div>
						<br>
						<div>
							<h1>修改最新消息</h1>
							<a href="select_pageNews.jsp" style="color: orange;">回首頁</a>
						</div>
					</div>
					<br>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back_end/news/news.do"
						name="form1" enctype="multipart/form-data">
						<div class="row">
							<div class="col-4" style="margin-left: 20%;">
								<table>
									<tr>
										<td>消息編號:<font color=red><b>*</b></font></td>
										<td>${newsVO.news_no}</td>
									</tr>
									<tr>
										<td>消息標題:</td>
										<td><input type="TEXT" name="news_title" size="100%"
											value="<%=newsVO.getNews_title()%>" /></td>
									</tr>
									<tr>
										<td>消息內容:</td>
										<td><textarea name="news_content"
												style="width: 100%; height: 150px;"><%=newsVO.getNews_content()%></textarea></td>
									</tr>
									<tr>
										<td>圖片更新:</td>
										<td><img
											src="${pageContext.request.contextPath}/back_end/news/news.do?action=getImg&news_no=${newsVO.news_no}"
											style="width: 300px;"> <br> <input type="file"
											name="news_pic"></td>
									</tr>
								</table>
								<br> <input type="hidden" name="action" value="update">
								<!-- 								回傳原本抓到的值 -->
								<input type="hidden" name="news_no"
									value="<%=newsVO.getNews_no()%>">
								<!-- 									 資料送出表單 -->
								<input type="submit" value="送出修改">
							</div>
						</div>
					</FORM>
				</div>
			</section>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="js/back.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>