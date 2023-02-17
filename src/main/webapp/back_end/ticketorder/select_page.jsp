<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tktorder.model.*"%>

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
<body bgcolor='white'>

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
								<div class="col-md-12">
									<FORM METHOD="post" ACTION="tktorder.do">
										<label for="listonetktorder">�q��s���G</label> <input type="text"
											name="number"> <input type="hidden" name="action"
											value="getTktOrderId_For_Display"> <input
											type="submit" class="btn btn-dark btn-sm" value="�e�X">
									</FORM>
									<FORM METHOD="post" ACTION="tktorder.do">
										<label for="listonememberid">�|���s���G</label> <input type="text"
											name="number"> <input type="hidden" name="action"
											value="getMemberId_For_Display"> <input type="submit" class="btn btn-dark btn-sm"
											value="�e�X">
									</FORM>
								</div>
							</div>
							<jsp:useBean id="tktorderSvc" scope="page"
								class="com.tktorder.model.TktOrderService" />
							<div class="row">
								<div class="col-md-12">
									<FORM METHOD="post" ACTION="tktorder.do">
										<label for="listonetktorderstate">�q�檬�A�G</label> <select
											size="1" name="tktstate_action">
											<option value="0" style="text-align: center;">����</option>
											<option value="1" style="text-align: center;">����</option>
										</select> <input type="hidden" name="action" 
											value="getTktOrderState_For_Search1"> <input
											type="submit" class="btn btn-dark btn-sm" value="�e�X">
									</FORM>
								</div>
								
								<div class="row">
									<div class="col-md-12">
										<FORM METHOD="post" ACTION="tktorder.do">
											<select name="action">
											   <option selected="selected" style="text-align: center;">&equiv;&nbsp;�U�Ԧ��j�M</option>
												<option value="getTktOrderId_For_Search" style="text-align: center;">�q��s��</option>
												<option value="getMemberId_For_Search" style="text-align: center;">�|���s��</option>
											</select> <input type="text"  name="number"> <input
												type="submit" class="btn btn-dark btn-sm" value="�e�X">
										</FORM>
									</div>

								</div>
							</div>

						</div>

					</div>
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