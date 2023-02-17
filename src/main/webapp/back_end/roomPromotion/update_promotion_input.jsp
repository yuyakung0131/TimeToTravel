<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotion.model.*"%>
<%
PromotionVO promotionVO = (PromotionVO) request.getAttribute("promotionVO");
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
							<br>
							<h2>修改資料</h2>
							<h4>
								<a href="select_pagePromotion.jsp" style="color: orange;">回首頁</a>
							</h4>
						</div>

					</div>
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
						ACTION="<%=request.getContextPath()%>/back_end/roomPromotion/promotion.do"
						name="form1">

						<div class="row">
							<div>
								<table table border=1 rules="rows" class="table ">
									<thead>
										<tr>
											<th>編號<font color=red><b>*</b></font></th>
											<th>促銷專案名稱<font color=red><b>*</b></font></th>
											<th>開始日期<font color=red><b>*</b></font></th>
											<th>結束日期<font color=red><b>*</b></font></th>
											<th>促銷狀態</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><%=promotionVO.getPromotion_id()%></td>
											<td><%=promotionVO.getPromotion_name()%></td>
											<td><%=promotionVO.getPromotion_startdate()%></td>
											<td><%=promotionVO.getPromotion_enddate()%></td>
											<td>${(promotionVO.promotion_state==0)?"上架":"下架"}<select
												size="1" name="promotion_state">
													<option value="0"
														${(promotionVO.promotion_state==0)?'selected':'1' }>上架


													
													<option value="1"
														${(promotionVO.promotion_state==1)?'selected':'0' }>下架


													
											</select></td>
										</tr>
									</tbody>
								</table>
								<br> <input type="hidden" name="action" value="update">
								<input type="hidden" name="promotion_id"
									value="<%=promotionVO.getPromotion_id()%>"> <input
									type="hidden" name="promotion_name"
									value="<%=promotionVO.getPromotion_name()%>"> <input
									type="hidden" name="promotion_startdate"
									value="<%=promotionVO.getPromotion_startdate()%>"> <input
									type="hidden" name="promotion_enddate"
									value="<%=promotionVO.getPromotion_enddate()%>"> <input
									type="submit" value="送出修改">
							</div>
						</div>
					</FORM>
				</div>
			</section>
		</div>
		<!--  分頁內容，若不要分頁可以整塊刪掉 -->

		<!-- =======================以上區塊可自訂======================= -->
	</div>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/roomPromotion/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/datetimepicker/jquery.datetimepicker.full.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/js/back.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/js/bootstrap.min.js"></script>
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
</html>