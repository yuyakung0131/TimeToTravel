<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tktimg.model.*"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="java.util.*"%>


<%
TktImg tktimg = (TktImg) request.getAttribute("tktimg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/tktimg/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/tktimg/css/back.css" type="text/css">
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
							<h1>����޲z</h1>
						</div>

					</div>
					<!-- =======================�H�U�϶��i�ۭq======================= -->
					<!-- �������ҡA�i�H�s�W�����A�Y���n�����i�H����R�� -->
					<div class="nav nav-tabs">
						<!-- ����1 -->

						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">����Ϥ���ƺ޲z</button>

					</div>
					<!--  �������e�A�Y���n�����i�H����R�� -->
					<div class="tab-content">


						<div class="tab-pane show active" id="tab-1">
	<div>
								<input type="button" onclick="history.go(-1)" class="btn btn-secondary btn-sm" value="�^��W�@��"></input>
							</div>
							<br>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">�Эץ��H�U���~:</font>
								<c:forEach var="message" items="${errorMsgs}">
									<font style="color: red">${message}</font>
								</c:forEach>
							</c:if>
							<br> <a
								href="<%=request.getContextPath()%>/back_end/ticket/select_ticket.jsp">�^����</a>
							<!-- 								</h4> -->
							<!-- 								<h3>��Ʒs�W:</h3> -->
							<FORM METHOD="post" ACTION="ticketIMG.do" name="form1"
								enctype="multipart/form-data">
								<table>
									<tr>
										<td>����Ϥ�:<font color=red><b>*</b></font></td>
										<td><input type="file" name="upfile1"></td>
									</tr>
									<br>
									<tr>
										<jsp:useBean id="ticketSvc" scope="page"
											class="com.ticket.model.TicketService" />
										<td>����W��:</td>
										<td><select size="1" name="tkt_id">
												<c:forEach var="ticket" items="${ticketSvc.all}">
													<option value="${ticket.tkt_id}">${ticket.tkt_name}
												</c:forEach>
										</select></td>
									</tr>

								</table>
								<input type="hidden" name="action" value="insert"> <input
									type="submit" value="�s�W">
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
	<script src="<%=request.getContextPath()%>/back_end/tktimg/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/tktimg/js/bootstrap.min.js"></script>
</body>
</body>
</html>