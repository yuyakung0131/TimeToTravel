<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.firm.model.*"%>

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

table, th, td {
	border: 1px solid #CCCCFF;
}

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
							data-bs-target="#tab-1" aria-selected="true"></button>
							<table id="table-1">
								<tr>
									<td>
										<h3>�����Ʒs�W</h3>
									</td>
									<td>
										<h4>
											<a href="select_ticket.jsp">�^����</a>
										</h4>
									</td>
								</tr>
							</table>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">�Эץ��H�U���~:</font>
								<c:forEach var="message" items="${errorMsgs}">
									<font style="color: red">${message}</font>
								</c:forEach>
							</c:if>

<!-- 							<h3>��Ʒs�W:</h3> -->
							<FORM METHOD="post" ACTION="ticket.do" name="form1">
								<table>
									<tr>
										<td>���馳�Ĥ��:</td>
										<td><input type="TEXT" name="tkt_date" size="45"
											value="<%=(ticket == null) ? "3" : ticket.getTkt_date()%>" /></td>
									</tr>
									<jsp:useBean id="tickettypeSvc" scope="page"
										class="com.tickettype.model.TicketTypeService" />
									<tr>
										<td>�������:<font color=red><b>*</b></font></td>
										<td><select size="1" name="tkt_type_id">
												<c:forEach var="tkt_type" items="${tickettypeSvc.all}">
													<option value="${tkt_type.tkt_type_id}"
														${(ticket.tkt_type_id==tkt_type.tkt_type_id)? 'selected':' ' }>${tkt_type.tkt_type_name}
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td>����W��:</td>
										<td><input type="TEXT" name="tkt_name" size="45"
											value="<%=(ticket == null) ? "TibaMe Java�Z�d�çӷR�A" : ticket.getTkt_name()%>" /></td>
									</tr>
									<tr>
										<td>�������:</td>
										<td><input type="TEXT" name="tkt_price" size="45"
											value="<%=(ticket == null) ? "100000" : ticket.getTkt_price()%>" /></td>
									</tr>
									<jsp:useBean id="firmSvc" scope="page"
										class="com.firm.model.FirmService" />


									<%
									List<FirmVO> list = firmSvc.getAll_Ticket();
									List<FirmVO> list_ticket = new ArrayList<FirmVO>();

									for (int i = 0; i < list.size(); i++) {
										FirmVO vo = list.get(i); //�ŧi�ܼƥh��
										if (vo.getFirmtype_id().equals(1) && vo.getFirm_review_state().equals((byte) 1)) {//�n�`�N�]�˫��O�A��equals
											list_ticket.add(vo);
										}
									}

									pageContext.setAttribute("list_ticket", list_ticket);
									%>

									<%-- <%=list_ticket.size() %> --%>
									<%-- <%=list.size() %> --%>



									<tr>
										<td>����o�椽�q:</td>
										<td><select size="1" name="firm_id">
												<c:forEach var="firm" items="${list_ticket}">
													<option value="${firm.firm_id}">${firm.firm_name}
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td>����w�s�q:</td>
										<td><input type="TEXT" name="tkt_amount" size="45"
											value="<%=(ticket == null) ? "54" : ticket.getFirm_id()%>" /></td>
									</tr>

									<tr>
										<td>���黡��:</td>
										<td><input type="TEXT" name="instruction" size="45"
											value="<%=(ticket == null) ? "�çӧڷR�A" : ticket.getInstruction()%>" /></td>
									</tr>

									<tr>
										<td>�������:</td>
										<td><input type="TEXT" name="tkt_total_score" size="45"
											value="<%=(ticket == null) ? "5" : ticket.getTkt_total_score()%>" /></td>
									</tr>

									<tr>
										<td>����H��:</td>
										<td><input type="TEXT" name="tkt_total_people" size="45"
											value="<%=(ticket == null) ? "54" : ticket.getTkt_total_people()%>" /></td>
									</tr>


								</table>
								<input type="hidden" name="action" value="insert"> <input
									type="submit" value="�e�X�s�W">
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