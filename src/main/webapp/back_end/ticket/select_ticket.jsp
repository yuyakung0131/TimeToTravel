<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/ticket/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/ticket/css/back.css" type="text/css">
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
							data-bs-target="#tab-1" aria-selected="true">�����Ƭd��</button>
						<!-- ����2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-2" aria-selected="false">�����ƺ޲z</button>
						<!-- ����3 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-3" aria-selected="false">����Ϥ���Ƭd��</button>
						<!-- ����4 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-4" aria-selected="false">����Ϥ���ƺ޲z</button>

					</div>




					<!--  �������e�A�Y���n�����i�H����R�� -->
					<div class="tab-content">
						<!-- ����1 ���e -->

						<%-- ���~��C --%>

						<div class="tab-pane show active" id="tab-1">
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">�Эץ��H�U���~:</font>
								<c:forEach var="message" items="${errorMsgs}">
									<font style="color: red">${message}</font>
								</c:forEach>
							</c:if>
							<br>
							<div>
								<div class="col-md-5">
									<a href="listAllTicket.jsp">�C�X</a>�Ҧ����
								</div>
							</div>
							<br>


							<div class="row">
								<div class="col-md-6">
									<form method="post" action="ticket.do">
										<input type="text" placeholder="��J�ӫ~�s��" name="tkt_id">
										<input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="�e�X">

										<!-- 											<i class="fa fa-check-circle" aria-hidden="true" -->
										<!-- 												type="submit">�e�X</i> -->

									</form>
									<!-- 								</div> -->
								</div>
								<br>

								<jsp:useBean id="ticketSvc" scope="page"
									class="com.ticket.model.TicketService" />
								<!-- 							<div class="row"> -->
								<div class="col-md-6">
									<form method="post" action="ticket.do">
										��ܰӫ~�s��<select size=1 name="tkt_id">
											<c:forEach var="ticket" items="${ticketSvc.all}">
												<option value="${ticket.tkt_id}">${ticket.tkt_id}
											</c:forEach>
										</select> <input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="�e�X">
									</form>
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-md-6">
									<form method="post" action="ticket.do">
										��ܰӫ~�W�� <select size=1 name="tkt_id">
											<c:forEach var="ticket" items="${ticketSvc.all}">
												<option value="${ticket.tkt_id}">${ticket.tkt_name}
											</c:forEach>

										</select> <input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="�e�X">
									</form>
								</div>
								<!-- 							</div> -->
								<br> <br>

								<jsp:useBean id="tickettypeSvc" scope="page"
									class="com.tickettype.model.TicketTypeService" />
								<!-- 							<div class="row"> -->
								<div class="col-md-6">
									<form method="post" action="ticket.do">
										�������: <select size="1" name="tkt_type_id">
											<c:forEach var="tkt_type" items="${tickettypeSvc.all}">
												<option value="${tkt_type.tkt_type_id}">${tkt_type.tkt_type_name}
											</c:forEach>
										</select> <input type="hidden" name="action"
											value="getType_For_Display"> <input type="submit"
											value="�e�X">
									</form>
								</div>
							</div>

							<!-- h3/h4? -->
							<!-- �e�x�����çR��?? -->
							<br>
							<div class="row">
								<div class="col-md-7">
									<form method="post" action="ticket.do">
										�������: <input type="text" name="min" placeholder="�п�J�̤p��">
										<input type="text" name="max" placeholder="�п�J�̤j��"> <input
											type="hidden" name="action" value="getPrice_For_Display">
										<input type="submit" value="�e�X">
									</form>
								</div>
								<!-- 							</div> -->



								<br>
								<!-- 							<div class="row"> -->
								<div class="col-md-5">
									<FORM METHOD="post" ACTION="ticket.do">
										<select name="action">
											<option value="getAmount_For_Display">�̤֮w�s�q�p��</option>
											<option value="getTktDate_For_Display">���Ĥ���p��</option>
										</select> <input type="text" name="number"> <input
											type="submit" value="�e�X">
									</FORM>
								</div>
							</div>
							<br> <br>
							<div class="row">
								<div class="col-md-12">
									<FORM METHOD="post" ACTION="ticket.do">
										1 | 2 | 3 | 4 | 5 (��) <br> ������פ���: <input type="range"
											name="tkt_comment_score" min="1" max="5" step="1"> <input
											type="submit" value="�e�X"> <input type="hidden"
											name="action" value="getComment_For_Display">
									</FORM>
								</div>
							</div>
						</div>














						<!-- ����2 ���e -->



						<!-- =======================�H�W�϶��i�ۭq======================= -->

						<div class="tab-pane" id="tab-2">
							<br>
							<div class="row">
								<div class="col-md-3">
									<a href="addTicket.jsp">�s�W</a>�@��������
								</div>
							</div>
							<br>

						</div>

						<div class="tab-pane" id="tab-3">




							<div class="row">
								<div class="col-md-3">
								<br>
									<a
										href="<%=request.getContextPath()%>/back_end/tktimg/listAllTktimg.jsp">�C�X</a>�C�X�Ҧ��Ϥ����
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-md-5">
									<form method="post"
										action="<%=request.getContextPath()%>/back_end/tktimg/ticketIMG.do">
										����W��: <select size="1" name="tkt_id">
											<c:forEach var="ticket" items="${ticketSvc.all}">
												<option value="${ticket.tkt_id}">${ticket.tkt_name}
											</c:forEach>
										</select> <input type="hidden" name="action"
											value="getOnImageId_For_Display"> <input
											type="submit" value="�e�X">
									</form>
								</div>
							</div>



						</div>

						<div class="tab-pane" id="tab-4">



							<div class="row">
								<div class="col-md-3">
									<br>
									<a
										href="<%=request.getContextPath()%>/back_end/tktimg/addTktImg.jsp">�s�W</a>�@������Ϥ����
								</div>
							</div>

							<br>
							<div class="row">
								<div class="col-md-3">
									<!-- 									<a href="addTicket.jsp">�ק�</a>����Ϥ���� -->
								</div>
							</div>

							<br>

						</div>
					</div>


					<!-- =======================�H�W�϶��i�ۭq======================= -->
				</div>

			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticket/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticket/js/bootstrap.min.js"></script>
</body>
</body>
</html>