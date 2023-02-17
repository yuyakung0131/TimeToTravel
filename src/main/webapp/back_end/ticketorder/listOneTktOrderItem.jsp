<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tktorder.model.*"%>
<%@ page import="com.tktitem.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
// TktOrderService tktorderSvc = new TktOrderService();
// List<TktOrder> list = tktorderSvc.getAll();
// pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/ticketorder/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/ticketorder/css/back.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/ticketorder/css/backtktorder.css">
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
							<h1>票券訂單管理</h1>
						</div>

					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs ">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">查看訂單</button>
						<!-- 						分頁2 -->
						<!-- 						<button class="nav-link" data-bs-toggle="tab" -->
						<!-- 							data-bs-target="#tab-2" aria-selected="false">tab2</button> -->
						<!-- 						分頁3 -->
						<!-- 						<button class="nav-link" data-bs-toggle="tab" -->
						<!-- 							data-bs-target="#tab-3" aria-selected="false">tab3</button> -->

					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="tab-1">
							<div>
								<input type="button" onclick="history.go(-1)" class="btn btn-secondary btn-sm" value="回到上一頁"></input>
							</div>
							<br>
							<div class="listAll">
								<button id="btn-search">
									<a class="selecttktAllorder" href="listAllTktOrder.jsp">查詢全部訂單</a>
								</button>
							</div>
							<br>
							<div class="row">

								<table table border=1 rules="rows" class="table " id="myTable"
									class="tablesorter">
									<thead>

										<tr>

											<th class="sortable">票券名稱</th>
											<th class="sortable">票券數量</th>
											<th class="sortable">票券價格</th>
											<th class="sortable">票券截止日期</th>
											<th class="sortable">訂單日期</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="tktOrderList" items="${list}">
											<tr>
												<td>${tktOrderList.ticket.tkt_name}</td>
												<td>${tktOrderList.amount}</td>
												<td>${tktOrderList.tkt_price}</td>
												<td><fmt:parseDate
														value="${ tktOrderList.tkt_deadline }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
														type="both" /> <fmt:formatDate pattern="yyyy-MM-dd HH:mm"
														value="${ parsedDateTime }" /></td>
												<td><fmt:formatDate
														value="${tktOrderList.getTktOrder().order_date}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

							<!-- 分頁2 內容 -->
							<div class="tab-pane" id="tab-2"></div>
							<!-- 分頁3 內容 -->
							<div class="tab-pane" id="tab-3"></div>
						</div>
					</div>
					<!-- =======================以上區塊可自訂======================= -->
				</div>
			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/ticketorder/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/ticketorder/js/bootstrap.min.js"></script>
	<script
		src='//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js'></script>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/css/theme.blue.min.css">
	</link>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/js/jquery.tablesorter.min.js"></script>
	<script>
		$("#myTable").tablesorter({
			theme : "blue",
			widgets : [ 'zebra' ]
		});
	</script>
</body>
</html>