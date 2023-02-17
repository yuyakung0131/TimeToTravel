<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorderitem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 傳要找的東西 做為 list  -->
<%
Integer room_order_id = (Integer) request.getAttribute("room_order_id");
%>
<% 
RoomOrderItemService roomOrderItemService = new RoomOrderItemService();
List<RoomOrderItemVO> list = roomOrderItemService.getOneForOrderId(room_order_id);
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
	href="<%=request.getContextPath()%>/back_end/roomOrder/css/bootstrap.min.css"
	type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/roomOrder/css/back.css"
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
							<h1>訂房訂單管理</h1>
						</div>
					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#rpt-1-1" aria-selected="true">查看訂房訂單</button>
						<!-- 分頁2 -->
						<a
							href="<%=request.getContextPath()%>/back_end/roomOrder/select_pageRoomOrderItem.jsp"
							style="text-decoration: none; color: black;">
							<button class="nav-link" data-bs-toggle="tab"
								data-bs-target="#rpt-1-2" aria-selected="false">查看訂房訂單明細</button>
						</a>
					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">

						<!-- =============================分頁1 =========================內容 -->
						<div class="tab-pane show active" id="rpt-1-1">

							<div class="news_range">
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								<div class="row">
									<br>
									<div class="container">
										<table border=1 rules="rows" class="table ">
											<thead>

												<tr>

													<th>訂單編號</th>
													<th>房型</th>
													<th>房間數量</th>
													<th>價格</th>
													<th>折扣額度</th>
													<th>特殊要求</th>
													<th>入住人數</th>
													<th>入住時間</th>
													<th>退房時間</th>
													<th>住房人種</th>


												</tr>
											</thead>

											<tbody class=" newscol">
												<%@ include file="page1.file"%>
												<c:forEach var="roomOrderItemVO" items="${list}"
													begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
													<tr>
														<td>${roomOrderItemVO.room_order_id}</td>
														<td>${roomOrderItemVO.room_type_id}</td>
														<td>${roomOrderItemVO.room_amount}</td>
														<td>${roomOrderItemVO.room_type_price}</td>
														<td>${roomOrderItemVO.room_sale_price}</td>
														<td>${roomOrderItemVO.special_req}</td>
														<td>${roomOrderItemVO.checkin_amount}</td>
														<td><fmt:formatDate
																value="${roomOrderItemVO.room_order_checkin_date}"
																type="both" pattern="yyyy-MM-dd" /></td>
														<td><fmt:formatDate
																value="${roomOrderItemVO.room_order_checkout_date}"
																type="both" pattern="yyyy-MM-dd" /></td>
														<td>${roomOrderItemVO.room_guest_name}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<%@ include file="page2.file"%>
									</div>
								</div>
							</div>

							<!-- 分頁2  資料新增 -->


							<!-- =======================以上區塊可自訂======================= -->
						</div>
					</div>
				</div>
			</section>

		</div>

	</div>




</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link rel="stylesheet" type="text/css"
	href="datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back_end/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/datetimepicker/jquery.datetimepicker.full.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomOrder/js/back.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomOrder/js/bootstrap.min.js"></script>

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