<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.itinerary.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ItineraryVO itineraryVO = (ItineraryVO) request.getAttribute("itineraryVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
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


<!-- Wang 新增 -->

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


<!-- Wang 新增 -->


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
							<h1>行程管理</h1>
						</div>

					</div>

					<!-- 段2 tab -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#itr-2-1" aria-selected="true">查看行程種類</button>
						<!-- 分頁2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#itr-2-2" aria-selected="false">新增行程種類</button>
						<!-- 分頁3 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#itr-2-3" aria-selected="false">新增行程種類圖片</button>

					</div>
					<!-- 段2 內容 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="itr-2-1">
							<table table border=1 rules="rows" class="table ">
								<thead>
									<tr>
										<th>行程編號</th>
										<th>行程種類</th>
										<th>行程開始日</th>
										<th>行程結束日</th>
										<th>已報名人數</th>
										<th>行程價格</th>
										<th>最少人數</th>
										<th>最多人數</th>
										<th>報名開始日</th>
										<th>報名結束日</th>
										<th>行程狀態</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><%=itineraryVO.getItinerary_id()%></td>
										<td>${itineraryVO.itineraryTypeVO.itinerary_title}</td>
										<td><%=itineraryVO.getItinerary_start()%></td>
										<td><%=itineraryVO.getItinerary_end()%></td>
										<td><%=itineraryVO.getItinerary_now()%></td>
										<td><%=itineraryVO.getItinerary_price()%></td>
										<td><%=itineraryVO.getItinerary_min()%></td>
										<td><%=itineraryVO.getItinerary_max()%></td>
										<td><%=itineraryVO.getEntered_start()%></td>
										<td><%=itineraryVO.getEntered_end()%></td>
										<td><%=itineraryVO.getItinerary_state()%></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- 分頁2 內容 -->
						<div class="tab-pane" id="itr-2-2"></div>
						<!-- 分頁3 內容 -->
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
