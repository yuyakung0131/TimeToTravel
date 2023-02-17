<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.qa.model.*"%>
<%@ page import="java.util.*"%>

<%
QaVO qa=(QaVO)request.getAttribute("qa"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/qa/css/bootstrap.min.css"
	type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/qa/css/back.css"
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
							<h1>�`�����D�޲z</h1>
						</div>

					</div>
					<!-- =======================�H�U�϶��i�ۭq======================= -->
					<!-- �������ҡA�i�H�s�W�����A�Y���n�����i�H����R�� -->
					<div class="nav nav-tabs">
						<!-- ����1 -->

						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">�`�����D�޲z�d��</button>

					</div>
					<!--  �������e�A�Y���n�����i�H����R�� -->
					<div class="tab-content">


						<div class="tab-pane show active" id="tab-1">

							<table>
								<tr>
									<th>�ݵ��s��</th>
									<th>��ܶ���</th>
									<th>�`�����D</th>
									<th>�`�����D����</th>
									<th>�`�����D���A</th>
									<th>�`�����D��������</th>
								</tr>

								<tr>
									<td><%=qa.getQa_no() %></td>
									<td><%=qa.getQa_show_no() %></td>
									<td><%=qa.getQuestion()%></td>
									<td><%=qa.getAnswer() %></td>
									<td><%=qa.getQa_state() %></td>
									<td><%=qa.getQaclass().getQa_class_name() %></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/qa/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/qa/js/bootstrap.min.js"></script>




</body>
</html>