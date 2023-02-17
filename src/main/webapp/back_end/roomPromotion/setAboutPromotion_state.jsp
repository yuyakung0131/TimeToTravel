<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotion.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
Byte promotion_state = (Byte) request.getAttribute("promotion_state");
PromotionService proSvc = new PromotionService();
List<PromotionVO> list = proSvc.getPromotionByState(promotion_state);
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/roomPromotion/css/bootstrap.min.css"
	type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/roomPromotion/css/back.css"
	type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">


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
							<h1>房型促銷管理</h1>
						</div>

					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#rpt-1-1" aria-selected="true">查看促銷專案</button>

					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">

						<!-- =============================分頁1 =========================內容 -->
						<div class="tab-pane show active" id="rpt-1-1">
							<div class="row search">
								<div class="col-md-12">

									<h4>
										<a href="select_pagePromotion.jsp" style="color: orange;">回前頁</a>

									</h4>
									<br>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back_end/roomPromotion/promotion.do">
										<b>輸入促銷編號:</b> <input type="text" name="promotion_id">
										<input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出">
									</FORM>
								</div>
							</div>


							<div>
								<table border=1 rules="rows" class="table ">
									<thead>
										<tr>
											<th>編號</th>
											<th>促銷專案名稱</th>
											<th>開始日期</th>
											<th>結束日期</th>
											<th>促銷狀態</th>
											<th>修改</th>

										</tr>
									</thead>
									<tbody>
										<%-- 										<%@ include file="page1.file"%> --%>
										<!--跌代出list -->
										<c:forEach var="promotionVO" items="${list}">

											<tr>
												<td>${promotionVO.promotion_id}</td>
												<td>${promotionVO.promotion_name}</td>
												<td>${promotionVO.promotion_startdate}</td>
												<td>${promotionVO.promotion_enddate}</td>
												<td>${(promotionVO.promotion_state==0)?"上架":"下架"}</td>
												<td><FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/back_end/roomPromotion/promotion.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="修改"> <input
															type="hidden" name="promotion_id"
															value="${promotionVO.promotion_id}"> <input
															type="hidden" name="action" value="getOne_For_Update">
													</FORM></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
								<%-- 								<%@ include file="page2.file"%> --%>

							</div>
						</div>
						<!-- =======================以上區塊可自訂======================= -->
					</div>
				</div>
			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/roomPromotion/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/roomPromotion/js/bootstrap.min.js"></script>
</body>

</html>