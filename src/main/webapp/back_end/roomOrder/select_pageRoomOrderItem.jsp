<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.roomorderitem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
RoomOrderItemService roomOrderItemService = new RoomOrderItemService();
List<RoomOrderItemVO> list = roomOrderItemService.getAllLong();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
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
						<a
							href="<%=request.getContextPath()%>/back_end/roomOrder/listAllRoomOrder.jsp"
							style="text-decoration: none; color: black;">
							<button class="nav-link " data-bs-toggle="tab"
								data-bs-target="#rpt-1-1" aria-selected="true">查看訂房訂單</button>
						</a>
						<!-- 分頁2 -->
						<a
							href="<%=request.getContextPath()%>/back_end/roomOrder/select_pageRoomOrderItem.jsp"
							style="text-decoration: none; color: black;">
							<button class="nav-link active" data-bs-toggle="tab"
								data-bs-target="#rpt-1-2" aria-selected="false">查看訂房訂單明細</button>
						</a>
					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">

						<!-- =============================分頁1 =========================內容 -->
						<div class="tab-pane " id="rpt-1-1"></div>

						<!-- 分頁2  資料新增 -->
						<div class="tab-pane show active" id="rpt-1-2">

							<div class="row ">

								<div class="col-md-12">
									<br>
									<!-- 開始 -->

									<form action="" class="seach">
										<b>請輸入關鍵字: </b><input type="text" name="" id="myInput"
											placeholder="搜尋...">
									</form>
									<hr>
									<table id="myTable" table border=1 rules="rows"
										class="table tablesorter">
										<tr>
											<th>訂單明細序號</th>
											<th>房型類別</th>
											<th>房間數量</th>
											<th>價格</th>
											<th>折扣額度</th>
											<th>特殊要求</th>
											<th>入住人數</th>
											<th>入住時間</th>
											<th>退房時間</th>
											<th>住房人種</th>
										</tr>
										<c:forEach var="roomOrderItemVO" items="${list}">
											<tbody id="myBody">
												<tr>
													<td>${roomOrderItemVO.room_order_id}</td>
													<td>${roomOrderItemVO.roomTypeVO.room_type_name}</td>
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
											</tbody>
										</c:forEach>
									</table>
									<!-- 結束 -->



								</div>


							</div>
						</div>

						<!-- =======================以上區塊可自訂======================= -->
					</div>

				</div>
			</section>
		</div>
	</div>

</body>



<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomOrder/js/back.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomOrder/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script
	src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script>
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