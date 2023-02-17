<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tktimg.model.*"%>
<%@ page import="java.util.*"%>


<%
Integer tkt_id = (Integer) request.getAttribute("tkt_id");
TktImgService tktimgSvc = new TktImgService();
List<TktImg> list = tktimgSvc.findByTktId(tkt_id);
pageContext.setAttribute("list", list);
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
							data-bs-target="#tab-1" aria-selected="true">����Ϥ���ƺ޲z</button>

					</div>
					<!--  �������e�A�Y���n�����i�H����R�� -->
					<div class="tab-content">
	<div>
								<input type="button" onclick="history.go(-1)" class="btn btn-secondary btn-sm" value="�^��W�@��"></input>
							</div>
							<br>

						<div class="tab-pane show active" id="tab-1">

							<table>
								<tr>
									<th>����s��</th>
									<th>����Ϥ��s��</th>
									<th>����W��</th>
									<th>�������</th>
									<th>����o�椽�q</th>
									<th>����Ϥ�</th>
								</tr>
								<c:forEach var="tktimg" items="${list}" begin="0"
									end="${list.size()-1}">
									<tr>
										<td>${tktimg.tkt_id}</td>
										<td>${tktimg.tkt_img_id}</td>
										<td>${tktimg.getTicket().tkt_name}</td>
										<td>${tktimg.getTicket().getTicketType().getTkt_type_name()}</td>
										<td>${tktimg.getTicket().getFirm().getFirm_name()}</td>
										<td><img
											src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimg.tkt_img_id}"
											width=300px height=250px></td>
									</tr>
								</c:forEach>
							</table>











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