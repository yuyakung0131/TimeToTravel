<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorderitem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
RoomOrderItemService roomOrderItemService = new RoomOrderItemService();
List<RoomOrderItemVO> list = roomOrderItemService.getAll();
pageContext.setAttribute("list", list);
%>
<%
RoomOrderItemVO roomOrderVO = (RoomOrderItemVO) request.getAttribute("roomOrderItemVO");
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

<style>
div.news_range {
	/* border:1px solid black; */
	margin: 20px 300px;
}

tbody.newscol {
	max-height: 20px;
	overflow: auto;
}

tr {
	border: 3px solid black;
	height: 20px;
	overflow: auto;
}

.detail_1 {
	color: black;
	overflow: hidden; /* 單行..省略 */
	white-space: nowrap; /* 單行..省略 */
	text-overflow: ellipsis; /* 單行..省略 */
}

.detail_1:hover {
	color: #dfa974;
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
							<h1>訂房訂單管理</h1>
						</div>
					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<!-- 分頁1 -->
					<h4>
						<a
							href="<%=request.getContextPath()%>/back_end/roomOrder/listAllRoomOrder.jsp"
							style="margin: auto;">點擊回上一頁</a>
					</h4>
					<table data-toggle="table" data-sortable="true"
						data-sort-class="table-active" data-show-toggle="true">
						<thead>
							<tr class="row">
								<th class="col-1 fht-cell" data-field="room_order_id"
									data-sortable="true">訂單編號</th>
								<th class="col-1 fht-cell" data-field="room_type_id"
									data-sortable="true">房型編號</th>
								<th class="col-1 fht-cell" data-field="room_amount"
									data-sortable="true">訂房數量</th>
								<th class="col-1 fht-cell" data-field="room_type_price"
									data-sortable="true">房型價格</th>
								<th class="col-1 fht-cell" data-field="room_sale_price"
									data-sortable="true">優惠價格</th>
								<th class="col-3 fht-cell" data-field="special_req"
									data-sortable="true">特殊要求</th>
								<th class="col-1 fht-cell" data-field="checkin_amount"
									data-sortable="true">入住人數</th>
								<th class="col-1 fht-cell" data-field="room_order_checkin_date"
									data-sortable="true">入住日期</th>
								<th class="col-1 fht-cell" data-field="room_order_checkout_date"
									data-sortable="true">退房日期</th>
								<th class="col-1 fht-cell" data-field="room_guest_name"
									data-sortable="true">住客名單</th>
						</thead>
						<tbody class=" newscol">
							<tr>
								<td>${roomOrderItemVO.room_order_id}</td>
								<td>${roomOrderItemVO.room_type_id}</td>
								<td>${roomOrderItemVO.room_amount}</td>
								<td>${roomOrderItemVO.room_type_price}</td>
								<td>${roomOrderItemVO.room_sale_price}</td>
								<td>${roomOrderItemVO.special_req}</td>
								<td>${roomOrderItemVO.checkin_amount}</td>
								<td>${roomOrderItemVO.room_order_checkin_date}</td>
								<td>${roomOrderItemVO.room_order_checkout_date}</td>
								<td>${roomOrderItemVO.room_guest_name}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- =======================以上區塊可自訂======================= -->
			</section>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script
	src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/js/back.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/roomPromotion/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script
	src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script>

</html>