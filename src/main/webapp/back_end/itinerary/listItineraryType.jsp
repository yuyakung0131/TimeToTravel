<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itinerary.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"> -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="css/back.css" type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">


<!-- Wang �s�W -->

<style>
h1 {
	text-align: center;
}

.search {
	text-align: center;
}

#myInput {
	width: 80%;
	font-size: 20px;
	padding: 10px;
	background-color: #E7FAFA;
	border: 1px solid gray;
}

li {
	list-style-type: none; /* Remove bullets */
	padding: 0; /* Remove padding */
	margin: 0; /* Remove margins */
}
</style>


<!-- Wang �s�W -->


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
							<h1>��{�޲z</h1>
						</div>

					</div>

					<!-- �q2 tab -->
					<div class="nav nav-tabs">
						<!-- ����1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#itr-2-1" aria-selected="true">�d�ݦ�{����</button>
						<!-- ����2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#itr-2-2" aria-selected="false">�s�W��{����</button>
						<!-- ����3 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#itr-2-3" aria-selected="false">�s�W��{�����Ϥ�</button>

					</div>
					<!-- �q2 ���e -->
					<div class="tab-content">
						<!-- ����1 ���e -->
						<div class="tab-pane show active" id="itr-2-1">
							<table table border=1 rules="rows" class="table ">
								<thead>
									<tr>
										<th>��{�s��</th>
										<th>��{����</th>
										<th>��{�}�l��</th>
										<th>��{������</th>
										<th>�w���W�H��</th>
										<th>��{����</th>
										<th>�̤֤H��</th>
										<th>�̦h�H��</th>
										<th>���W�}�l��</th>
										<th>���W������</th>
										<th>��{���A</th>

									</tr>
									<%-- 			<%@ include file="page1.file" %>  --%>
									<c:forEach var="itineraryVO" items="${itineraryVO}">
										<%-- 		begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>

										<tr>
											<td>${itineraryVO.itinerary_id}</td>
											<td>${itineraryVO.itineraryTypeVO.itinerary_title}</td>
											<td>${itineraryVO.itinerary_start}</td>
											<td>${itineraryVO.itinerary_end}</td>
											<td>${itineraryVO.itinerary_now}</td>
											<td>${itineraryVO.itinerary_price}</td>
											<td>${itineraryVO.itinerary_min}</td>
											<td>${itineraryVO.itinerary_max}</td>
											<td>${itineraryVO.entered_start}</td>
											<td>${itineraryVO.entered_end}</td>
											<td>${itineraryVO.itinerary_state}</td>
										<tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- ����2 ���e -->
						<div class="tab-pane" id="itr-2-2"></div>
						<!-- ����3 ���e -->
						<div class="tab-pane" id="itr-2-3"></div>

					</div>

				</div>
			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/css/theme.blue.min.css"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/js/jquery.tablesorter.min.js"></script>
	<script src="js/back.js"></script>
</body>



</html>
