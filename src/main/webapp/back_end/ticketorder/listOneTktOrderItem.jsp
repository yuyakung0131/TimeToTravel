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
		<!-- ���� -->
		<div class="container-left">
			<!-- �����C -->
			<jsp:include page="../sidebar.jsp"></jsp:include>
		</div>
		<!-- �k�� -->
		<div class="container-right">
			<!-- �k�W -->
			<jsp:include page="../header.jsp"></jsp:include>
			<!-- �k�U -->
			<section id="first">
				<div class="container right-down">
					<!-- �q1 -->
					<div class="row">
						<div class="col-md-12 h1">
							<h1>����q��޲z</h1>
						</div>

					</div>
					<!-- =======================�H�U�϶��i�ۭq======================= -->
					<!-- �������ҡA�i�H�s�W�����A�Y���n�����i�H����R�� -->
					<div class="nav nav-tabs ">
						<!-- ����1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">�d�ݭq��</button>
						<!-- 						����2 -->
						<!-- 						<button class="nav-link" data-bs-toggle="tab" -->
						<!-- 							data-bs-target="#tab-2" aria-selected="false">tab2</button> -->
						<!-- 						����3 -->
						<!-- 						<button class="nav-link" data-bs-toggle="tab" -->
						<!-- 							data-bs-target="#tab-3" aria-selected="false">tab3</button> -->

					</div>
					<!--  �������e�A�Y���n�����i�H����R�� -->
					<div class="tab-content">
						<!-- ����1 ���e -->
						<div class="tab-pane show active" id="tab-1">
							<div>
								<input type="button" onclick="history.go(-1)" class="btn btn-secondary btn-sm" value="�^��W�@��"></input>
							</div>
							<br>
							<div class="listAll">
								<button id="btn-search">
									<a class="selecttktAllorder" href="listAllTktOrder.jsp">�d�ߥ����q��</a>
								</button>
							</div>
							<br>
							<div class="row">

								<table table border=1 rules="rows" class="table " id="myTable"
									class="tablesorter">
									<thead>

										<tr>

											<th class="sortable">����W��</th>
											<th class="sortable">����ƶq</th>
											<th class="sortable">�������</th>
											<th class="sortable">����I����</th>
											<th class="sortable">�q����</th>
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

							<!-- ����2 ���e -->
							<div class="tab-pane" id="tab-2"></div>
							<!-- ����3 ���e -->
							<div class="tab-pane" id="tab-3"></div>
						</div>
					</div>
					<!-- =======================�H�W�϶��i�ۭq======================= -->
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