<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotion.model.*"%>
<%@ page import="com.promotionitem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
PromotionService promotionSvc = new PromotionService();
List<PromotionVO> list = promotionSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
PromotionItemService promotionItemService = new PromotionItemService();
List<PromotionItemVO> list1 = promotionItemService.getAll();
pageContext.setAttribute("list1", list1);
%>
<%
PromotionVO promotionVO = (PromotionVO) request.getAttribute("promotionVO");
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
						<!-- 分頁2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#rpt-1-2" aria-selected="false">新增促銷專案</button>
						<!-- 分頁3 -->
						<a
							href="<%=request.getContextPath()%>/back_end/roomPromotion/listAllPromotionItem.jsp"
							style="text-decoration: none; color: black;">
							<button class="nav-link" data-bs-toggle="tab"
								data-bs-target="#rpt-1-3" aria-selected="false">促銷折扣列表</button>
						</a>
						<!-- 分頁4 -->

						<a
							href="<%=request.getContextPath()%>/back_end/roomPromotion/addPromotionItem.jsp"
							style="text-decoration: none; color: black;">
							<button class="nav-link" data-bs-toggle="tab"
								data-bs-target="#rpt-1-4" aria-selected="false">新增促銷折扣</button>
						</a>

					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">

						<!-- =============================分頁1 =========================內容 -->
						<div class="tab-pane show active" id="rpt-1-1">
							<div class="row search">
								<div class="col-md-12">


									<!-------------------單一查詢開始 --------------->
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
										ACTION="<%=request.getContextPath()%>/back_end/roomPromotion/promotion.do">
										<b>輸入促銷編號:</b> <input type="text" name="promotion_id"
											placeholder="請輸入編號..."> <input type="hidden"
											name="action" value="getOne_For_Display"> <input
											type="submit" value="送出">
									</FORM>
								</div>

								<!-- ------------以上單一查詢 -------------->


								<!-- ------------以下選單查詢 -------------->
								<jsp:useBean id="proSvc" scope="page"
									class="com.promotion.model.PromotionService" />

								<div>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back_end/roomPromotion/promotion.do">
										<b>搜尋上下架: </b> <select size="1" name="promotion_state">
											<option value="0">上架</option>
											<option value="1">下架</option>
										</select> <input type="submit" value="送出"> <input type="hidden"
											name="action" value="listPromotion_ByState_01">
									</FORM>
								</div>
								<!-- ------------以上選單查詢 -------------->
								<div>
									<form action="" class="seach">
										<b>請輸入關鍵字: </b><input type="text" name="" id="myInput"
											placeholder="搜尋...">
									</form>

									<table id="myTable" table border=1 rules="rows"
										class="table tablesorter">
										<tr>
											<th>編號</th>
											<th>促銷專案名稱</th>
											<th>開始日期</th>
											<th>結束日期</th>
											<th>促銷狀態</th>
											<th>修改</th>

										</tr>
										<c:forEach var="promotionVO" items="${list}">
											<tbody id="myBody">
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
											</tbody>
										</c:forEach>
									</table>

								</div>

							</div>

						</div>

						<!-- 分頁2  資料新增 -->

						<div class="tab-pane" id="rpt-1-2">
							<hr>
							<h4>
								<a href="select_pagePromotion.jsp" style="color: orange;">回前頁</a>
							</h4>
							<br>
							<!-- 無敵路徑 -->
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back_end/roomPromotion/promotion.do"
								name="form1">
								<div class="md-3" style="margin: 0px 10%; width: auto;">
									<label for="validationTextarea" class="form-label"><strong>促銷標題:</strong></label>
									<textarea name="promotion_name"
										value="<%=(promotionVO == null) ? "" : promotionVO.getPromotion_name()%>"
										class="form-control" id="validationTextarea"
										placeholder="標題填寫 ..." required></textarea>
								</div>

								<br> <br> <label> 促銷開始: <input
									name="promotion_startdate" id="start_date" type="text">
								</label> <br> <br> <label> 促銷結束: <input
									name="promotion_enddate" id="end_date" type="text">
								</label> <br> <br>
								<div class="row">
									<div class="col-md-4"></div>
									<div class="col-md-4">
										<input type="hidden" name="action" value="insert"> <input
											type="hidden" name="promotion_state" value="1">
										<button class="btn btn-primary" type="submit"
											style="margin: 5px; margin-bottom: 20px;">確認送出</button>
									</div>
								</div>
							</form>
							<hr>
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
						<!-- 						分頁3 內容 -->


						<div class="tab-pane" id="rpt-1-3">3333</div>
						<!-- 						分頁3 內容結束 -->
						<!-- 						分頁4 內容 -->
						<div class="tab-pane" id="rpt-1-4">222</div>
						<!-- 						分頁4 內容結束 -->

						<!-- 下面這DIV 包整塊的喔!! -->
					</div>
					<!-- =======================以上區塊可自訂======================= -->
				</div>
			</section>
		</div>
	</div>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/js/back.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/roomPromotion/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function() {
		$('#start_date').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function() {
						this.setOptions({
							maxDate : $('#end_date').val() ? $('#end_date')
									.val() : false
						})
					},
					timepicker : false
				});

		$('#end_date').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function() {
						this.setOptions({
							minDate : $('#start_date').val() ? $('#start_date')
									.val() : false
						})
					},
					timepicker : false
				});
	});
</script>

<script>
	$(document).ready(
			function() {
				$("#myInput").on(
						"keyup",
						function() {
							//抓搜尋關鍵字
							let value = $(this).val().toLowerCase();
							//抓table符合
							$("#myBody tr").filter(
									function() {
										$(this).toggle(
												$(this).text()
														.toLocaleLowerCase()
														.indexOf(value) > -1)
									});
						});
			});
</script>
</html>