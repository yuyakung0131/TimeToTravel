<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.roomorderitem.model.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
Byte room_order_state=(Byte) request.getAttribute("room_order_state");
RoomOrderService roomOrderService = new RoomOrderService();
List<RoomOrderVO> list = roomOrderService.getRoomOrderState(room_order_state);
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
							<div class="row search">
								<div class="col-md-12">
									<br> <a
										href="<%=request.getContextPath()%>/back_end/roomOrder/listAllRoomOrder.jsp"><input
										class="rounded-pill" type="submit" value="上一頁"></a>

								</div>
							</div>


							<div>
								<table table border=1 rules="rows" class="table ">
									<thead>
										<tr>
											<th>訂單編號</th>
											<th>會員姓名</th>
											<th>訂單成立日期</th>
											<th>訂單狀態</th>
											<th>總金額</th>


										</tr>
									</thead>
									<tbody>
										<%@ include file="page1.file"%>
										<!-- 										跌代出list -->
										<c:forEach var="roomOrderVO" items="${list}"
											begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

											<tr>
												<td>${roomOrderVO.room_order_id}</td>
												<td>${roomOrderVO.memberVO.member_name}</td>
												<td><fmt:formatDate
														value="${roomOrderVO.room_order_date}" type="both"
														dateStyle="long" timeStyle="DEFAULT" /></td>


												<c:if test="${roomOrderVO.room_order_state == 0}" var="true">
													<td class="myState"><b>訂單成立</b></td>
												</c:if>
												<c:if test="${roomOrderVO.room_order_state == 1}" var="true">
													<td class="myState"><b>取消訂單</b></td>
												</c:if>
												<c:if test="${roomOrderVO.room_order_state == 2}" var="true">
													<td class="myState"><b>訂單完成</b></td>
												</c:if>


												<td>${roomOrderVO.room_order_ttl_price}/NT$</td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
								<%@ include file="page2.file"%>
							</div>
						</div>
						<!-- 分頁2  資料新增 -->
						<!-- =======================以上區塊可自訂======================= -->
					</div>
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