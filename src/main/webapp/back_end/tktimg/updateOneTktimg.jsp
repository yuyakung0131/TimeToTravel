<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tktimg.model.*"%>

<%
TktImg tktimg = (TktImg) request.getAttribute("tktimg");
%>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/tktimg/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/tktimg/css/back.css" type="text/css">
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
							<br>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<c:forEach var="message" items="${errorMsgs}">
									<font style="color: red">${message}</font>
								</c:forEach>
							</c:if>
							<!-- 							<table id="table-1"> -->
							<!-- 								<tr> -->
							<!-- 									<td> -->
							<!-- 										<h3>票券資料修改</h3> -->
							<!-- 									</td> -->
							<!-- 									<td> -->
							<!-- 										<h4> -->
							<a href="select_ticket.jsp">回首頁</a>
							<!-- 										</h4> -->
							<!-- 									</td> -->
							<!-- 								</tr> -->
							<!-- 							</table> -->

							<!-- 							<h3>資料修改:</h3> -->
							<FORM METHOD="post" ACTION="ticketIMG.do" name="form1"
								enctype="multipart/form-data">
								<table>

									<tr>
										<td>票券圖片編號:</td>
										<td><%=tktimg.getTkt_img_id()%></td>
									</tr>

									<tr>
										<td>票券圖片</td>
										<td><input type="file" name="upfile1" /></td>
									</tr>
									<jsp:useBean id="ticketSvc" scope="page"
										class="com.ticket.model.TicketService" />
<%-- 									<jsp:useBean id="tktimg" scope="page" --%>
<%-- 										class="com.tktimg.model.TktImg" /> --%>
									<tr>
										<td>票券名稱:</td>
										<td><select size="1" name="tkt_id">
												<c:forEach var="ticket" items="${ticketSvc.all}">
													<option value="${ticket.tkt_id}"
														${(tktimg.tkt_id==ticket.tkt_id)? 'selected':'' }>${ticket.tkt_name}
												</c:forEach>
										</select></td>
									</tr>
<%-- 									<%=tktimg.getTicket().getTkt_name() %> --%>
<!-- 										<tr> -->
<!-- 										<td>票券名稱:</td> -->
<!-- 										<td><select size="1" name="tkt_name"> -->
<%-- 												<c:forEach var="ticket" items="${ticketSvc.all}"> --%>
<%-- 													<option value="${ticket.tkt_name}" --%>
<%-- 														${(tktimg.getTicket().getTkt_name()==ticket.tkt_name)? 'selected':'' }>${ticket.tkt_name} --%>
<%-- 												</c:forEach> --%>
<!-- 										</select></td> -->
<!-- 									</tr> -->
									
									
									
									
								</table>
								<input type="hidden" name="action" value="update"> <input
									type="hidden" name="tkt_img_id"
									value="<%=tktimg.getTkt_img_id()%>"> <input
									type="submit" value="送出修改">
							</FORM>
					</div>
					</div>
			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/tktimg/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/tktimg/js/bootstrap.min.js"></script>

	<br>



</body>
</html>