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
						<a href="select_pageNews.jsp" style="color: orange;">回首頁</a>
					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<!-- 最新消息開始 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">最新消息列表</button>
					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="tab-1">
							<div class="news_range">
								<table>
									<thead>
										<div class="search-news">
											<div class="row">
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
													<div class="row">
														<div class="col-12" style="margin: 15px 45px;">
															<b>搜尋編號:</b>  <input name="news_no"
																type="search" class="rounded-pill"
																style="border: 1px solid black" id="search"
																placeholder="搜索..." /> <input type="hidden"
																name="action" value="getOne_For_Display"> <input
																type="submit" value="送出">

														</div>
													</div>
												</FORM>
											</div>
										</div>
										<tr style="border: 1px solid black; width: 400px;"">
											<div class="row">
												<th class=" col-1 fht-cell" data-field="news_id"
													data-sortable="true">編號</th>
												<th class=" col-2 fht-cell" data-field="news_title"
													data-sortable="true">標題</th>
												<th class=" col-3 fht-cell" data-field="news_content"
													data-sortable="true">內容</th>
												<th class=" col-2 fht-cell" data-field="news_time"
													data-sortable="true">發表時間</th>
												<th class=" col-2 fht-cell" data-field="news_time"
													data-sortable="true">圖片</th>
												<th class=" col-1 fht-cell" data-field="news_title"
													data-sortable="true">修改</th>
												<th class=" col-1 fht-cell" data-field="news_time"
													data-sortable="true">刪除</th>

											</div>
										</tr>
									</thead>

									<tbody class=" newscol">
										<%@ include file="page1.file"%>
										<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>"
											end="<%=pageIndex+rowsPerPage-1%>">

											<tr>
												<td>${newsVO.news_no}</td>
												<td style="white-space: normal;">${newsVO.news_title}</td>
												<td style="white-space: normal;">${newsVO.news_content}</td>
												<td><fmt:formatDate value="${newsVO.news_time}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>

												<td style="height: 400px;"><img
													src="${pageContext.request.contextPath}/back_end/news/news.do?action=getImg&news_no=${newsVO.news_no}"></td>
												<!-- 圖片 -->

												<td><FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/back_end/news/news.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="修改"> <input
															type="hidden" name="news_no" value="${newsVO.news_no}">
														<input type="hidden" name="action"
															value="getOne_For_Update">
													</FORM></td>
												<td><FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/back_end/news/news.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="刪除"> <input
															type="hidden" name="news_no" value="${newsVO.news_no}">
														<input type="hidden" name="action" value="delete">
													</FORM></td>
											</tr>
										</c:forEach>

									</tbody>


								</table>
								<%@ include file="page2.file"%>
							</div>
						</div>
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

	<!-- 最新消息結束 -->
</body>

</html>