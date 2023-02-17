<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ticket.model.*"%>

<%
TicketService ticketSvc = new TicketService();
List<Ticket> list = ticketSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/ticket/css/bootstrap.min.css"
	type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/ticket/css/back.css"
	type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<style>
table {
	/* 	table-layout: fixed; */
	width: 1300px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}
/*   table, th, td { */
/*     border: 1px solid #CCCCFF; */
/*   } */
th, td {
	padding: 5px;
	text-align: center;
	width: 100%;
}
</style>

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
							<h1>����޲z</h1>
						</div>

					</div>
					<!-- =======================�H�U�϶��i�ۭq======================= -->
					<!-- �������ҡA�i�H�s�W�����A�Y���n�����i�H����R�� -->
					<div class="nav nav-tabs">
						<!-- ����1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">�����Ƭd��</button>
						<!-- ����2 -->
						<!--                         <button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab-2" aria-selected="false"> -->
						<!--                             �����ƺ޲z -->
						<!--                         </button> -->
						<!-- ����3 -->
						<!-- <button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab-3" aria-selected="false">
                            tab3
                        </button> -->

					</div>
					<!--  �������e�A�Y���n�����i�H����R�� -->
					<div class="tab-content">
						<!-- ����1 ���e -->
						<div class="tab-pane show active" id="tab-1">
													<div>
								<input type="button" onclick="history.go(-1)" class="btn btn-secondary btn-sm" value="�^��W�@��"></input>
							</div>
							<br>
							<table table border=1 rules="rows" class="table " id="myTable"
									class="tablesorter">
								<tr>
									<th>����s��</th>
									<th>���馳�Ĥ��</th>
									<th>�������</th>
									<th>����W��</th>
									<th>�������</th>
									<th>����o�椽�q</th>
									<th>����w�s�q</th>
									<th>���黡��</th>
									<th>�������</th>
									<th>����H��</th>
								</tr>
								<c:forEach var="ticket" items="${list}" begin="0"
									end="${list.size()-1}">
									<tr>
										<td>${ticket.tkt_id}</td>
										<td>${ticket.tkt_date}</td>
										<td>${ticket.getTicketType().tkt_type_name}</td>
										<td>${ticket.tkt_name}</td>
										<td>${ticket.tkt_price}</td>
										<td>${ticket.getFirm().firm_name}</td>
										<td>${ticket.tkt_amount}</td>
										<td>${ticket.instruction}</td>
										<td>${ticket.tkt_total_score}</td>
										<td>${ticket.tkt_total_people}</td>
										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back_end/ticket/ticket.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="�ק�"> <input
													type="hidden" name="tkt_id" value="${ticket.tkt_id}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</form>
										</td>
										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back_end/ticket/ticket.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="�R��"> <input
													type="hidden" name="tkt_id" value="${ticket.tkt_id}">
												<input type="hidden" name="action" value="delete">
											</form>
										</td>
									</tr>
								</c:forEach>

							</table>




						</div>
						<!-- ����2 ���e -->


					</div>


				</div>
			</section>
			<!-- =======================�H�W�϶��i�ۭq======================= -->
		</div>



	</div>









	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticket/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/ticket/js/bootstrap.min.js"></script>
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