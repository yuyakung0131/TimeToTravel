<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>

<%
Ticket ticket = (Ticket) request.getAttribute("ticket");
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
	width: 800px;
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
							<h1>票券管理</h1>
						</div>

					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true"></button>
							<table id="table-1">
								<tr>
									<td>
										<h3>票券資料修改</h3>
									</td>
									<td>
										<h4>
											<a href="select_ticket.jsp">回首頁</a>
										</h4>
									</td>
								</tr>
							</table>

<!-- 							<h3>資料修改:</h3> -->
							<FORM METHOD="post" ACTION="ticket.do" name="form1">
								<table>

									<tr>
										<td>票券編號:</td>
										<td><%=ticket.getTkt_id()%></td>
									</tr>
									<tr>
										<td>票券有效日期:</td>
										<td><input type="TEXT" name="tkt_date" size="45"
											value="<%=ticket.getTkt_date()%>" /></td>
									</tr>
									<jsp:useBean id="tickettypeSvc" scope="page"
										class="com.tickettype.model.TicketTypeService" />
									<tr>
										<td>票券種類:<font color=red><b>*</b></font></td>
										<td><select size="1" name="tkt_type_id">
												<c:forEach var="tkt_type" items="${tickettypeSvc.all}">
													<option value="${tkt_type.tkt_type_id}"
														${(ticket.tkt_type_id==tkt_type.tkt_type_id)? 'selected':' ' }>${tkt_type.tkt_type_name}
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td>票券名稱:</td>
										<td><input type="TEXT" name="tkt_name" size="45"
											value="<%=ticket.getTkt_name()%>" /></td>
									</tr>
									<tr>
										<td>票券價錢:</td>
										<td><input type="TEXT" name="tkt_price" size="45"
											value="<%=ticket.getTkt_price()%>" /></td>
									</tr>
									<tr>
										<td>票券發行公司:</td>
										<td><input type="TEXT" name="firm_name" size="45"
											value="${ticket.firm.firm_name }" /></td>
									</tr>


									<tr>
										<td>票券庫存量:</td>
										<td><input type="TEXT" name="tkt_amount" size="45"
											value="${ticket.tkt_amount }" /></td>
									</tr>

									<tr>
										<td>票券說明:</td>
										<td><input type="TEXT" name="instruction" size="45"
											value="<%=ticket.getInstruction()%>" /></td>
									</tr>

									<tr>
										<td>票券評分:</td>
										<td><input type="TEXT" name="tkt_total_score" size="45"
											value="<%=ticket.getTkt_total_score()%>" /></td>
									</tr>

									<tr>
										<td>票券評論人數:</td>
										<td><input type="TEXT" name="tkt_total_people" size="45"
											value="<%=ticket.getTkt_total_people()%>" /></td>
									</tr>
								</table>
								<input type="hidden" name="firm_id" value="${ticket.firm_id }">
								<input type="hidden" name="action" value="update"><input
									type="hidden" name="tkt_id" value="<%=ticket.getTkt_id()%>">
								<input type="submit" value="送出修改">
							</FORM>
					</div>
					</div>
			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticket/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/ticket/js/bootstrap.min.js"></script>

	<br>



</body>
</html>