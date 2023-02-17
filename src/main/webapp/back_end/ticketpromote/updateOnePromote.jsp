<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketpromote.model.*"%>
<%@ page import="java.util.*"%>


<%
TicketPromote ticketpromote = (TicketPromote) request.getAttribute("ticketpromote");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/ticketpromote/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/ticketpromote/css/back.css" type="text/css">
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
							data-bs-target="#tab-1" aria-selected="true">����P�P�M�׸�ƭק�</button>

					</div>
					<!--  �������e�A�Y���n�����i�H����R�� -->
					<div class="tab-content">


						<div class="tab-pane show active" id="tab-1">
							<FORM METHOD="post" ACTION="ticketpromote.do" name="form1">
								<table>
									<tr>
										<td>����P�P�M�׽s�� :</td>
										<td><%=ticketpromote.getProm_id()%></td>
									</tr>
									<tr>
										<td>����P�P�M�צW�� :</td>
										<td><input type="TEXT" name="prom_name" size="45"
											value="<%=ticketpromote.getProm_name()%>" /></td>
									</tr>
									<tr>
										<td>����P�P�M�ת��A	 :</td>
										<td><input type="TEXT" name="prom_state" size="45"
											value="<%=ticketpromote.getProm_state()%>" /></td>
									</tr>
									<tr>
										<td>�M�׫P�P�馩��	 :</td>
										<td><input type="TEXT" name="discount_amount" size="45"
											value="<%=ticketpromote.getDiscount_amount()%>" /></td>
									</tr>
									<tr>
										<td>�M�׹F�л��� :</td>
										<td><input type="TEXT" name="prom_achieve_number" size="45"
											value="<%=ticketpromote.getProm_achieve_number()%>" /></td>
									</tr>

								</table>
								<input type="hidden" name="action" value="update"> <input
									type="hidden" name="prom_id"
									value="<%=ticketpromote.getProm_id()%>"> <input
									type="submit" value="�e�X�ק�">
							</FORM>










						</div>


					</div>


					<!-- =======================�H�W�϶��i�ۭq======================= -->
				</div>

			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticketpromote/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticketpromote/js/bootstrap.min.js"></script>
</body>
</body>
</html>