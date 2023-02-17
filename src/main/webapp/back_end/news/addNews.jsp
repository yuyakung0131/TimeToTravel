<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
NewsService newsSvc = new NewsService();
List<NewsVO> list = newsSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
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
img {
	max-width: 100%;
	max-height: 100%;
}
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
					<div class="row">
						<div class="col-md-12 h1">
							<h1>最新消息管理</h1>
						</div>

					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#rpt-1-1" aria-selected="true">查看最新消息</button>
						<!-- 分頁2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#rpt-1-2" aria-selected="false">新增最新消息</button>
						<!-- 分頁3 -->
<!-- 						<button class="nav-link" data-bs-toggle="tab" -->
<!-- 							data-bs-target="#rpt-1-3" aria-selected="false">tab3</button> -->

					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">

						<!-- =============================分頁1 =========================內容 -->
						<div class="tab-pane show active" id="rpt-1-1">
							<div class="row search">
								<div class="col-md-12">

									<h4>
										<h4>
											<a href="listAllNews.jsp" style="color: orange;">列出</a>所有資料
										</h4>
									</h4>
									<br>
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
										ACTION="<%=request.getContextPath()%>/back_end/news/news.do">
										<b>輸入最新消息編號:</b> <input type="text" name="news_no"> <input
											type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出">
									</FORM>
								</div>
							</div>


							<div>
								<table table border=1 rules="rows" class="table ">
									<tbody>

									</tbody>
								</table>


							</div>
						</div>

						<!-- 分頁2  資料新增 -->

						<div class="tab-pane" id="rpt-1-2">

							<!-- 無敵路徑 -->
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back_end/news/news.do"
								name="form1" enctype="multipart/form-data">

								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>

								<!-- 標題 -->

								<div class="md-3" style="margin:0px 10%;min-width: 150px;">
									<label for="validationTextarea" class="form-label"><strong>標題:</strong></label>
									<textarea name="news_title"
										value="<%=(newsVO == null) ? "" : newsVO.getNews_title()%>"
										class="form-control" id="validationTextarea"
										placeholder="標題填寫 ..." required></textarea>
								</div>


								<!-- 內文 -->
								<div class="md-3" style="margin:0px 10%;min-width: 150px;">
									<label for="validationTextarea" class="form-label"><strong>內文:</strong></label>
									<textarea name="news_content"
										value="<%=(newsVO == null) ? "" : newsVO.getNews_content()%>"
										class="form-control" id="validationTextarea"
										placeholder="內文填寫 ..." style="min-height: 200px;" required></textarea>
								</div>
								<div class="row">
									<div class="col-md-3"></div>

									<p>上傳圖片:</p>
									<input type="file" name="news_pic" style ="margin:0px 40%;">
								</div>
								<div class="row"style="margin:10px;">
										<!-- 									送出新增請求 -->
										<input type="hidden" name="action" value="insert">
										<button class="btn btn-primary" type="submit"
											style="width:100px;margin:0px 45%;">確認送出</button>
									
								</div>
							</form>
							<hr>
						</div>
						<!-- 分頁3 內容 -->
<!-- 						<div class="tab-pane" id="rpt-1-3"></div> -->
					</div>
					<!-- =======================以上區塊可自訂======================= -->
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