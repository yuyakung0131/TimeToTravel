<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.itinerarytype.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- <c:set var="list" value="${req.getParameter('itineraryTypeVO')}" scope="page"/> --%>

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
							<h1>行程種類管理</h1>
						</div>

					</div>

					<!-- 段2 tab -->
					<div class="nav nav-tabs nav-justified">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#itr-1-1" aria-selected="true">查看行程種類</button>



					</div>
					<!-- 段2 內容 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="itr-1-1">
							<div>
								<table table border=1 rules="rows" class="table ">
									<tr>
										<th>行程種類編號</th>
										<th>行程類別</th>
										<th>廠商編號</th>
										<th>行程標題</th>
										<th>行程資訊</th>
										<th>其他說明</th>
										<th>行程費用</th>
										<th>最少成行人數</th>
										<th>最多可報名人數</th>
										<th>行程狀態</th>
										<th>修改</th>
										<th>刪除</th>
									</tr>
									<c:forEach var="itineraryTypeVO" items="${itineraryTypeVO}">

										<tr>
										<tr>
											<td>${itineraryTypeVO.itinerary_type_id}</td>
											<td>${itineraryTypeVO.itineraryClassVO.itinerary_class_name}</td>
											<td>${itineraryTypeVO.firmVO.firm_name}</td>
											<td>${itineraryTypeVO.itinerary_title}</td>
											<td
												style="max-width: 200px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${itineraryTypeVO.itinerary_info}</td>
											<td
												style="max-width: 200px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${itineraryTypeVO.itinerary_other}</td>
											<td>${itineraryTypeVO.itinerary_price}</td>
											<td>${itineraryTypeVO.itinerary_min}</td>
											<td>${itineraryTypeVO.itinerary_max}</td>
											<td>${itineraryTypeVO.itinerary_type_state == 0 ? "上架" : "下架"}</td>

											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/back_end/itinerarytype/do"
													style="margin-bottom: 0px;">
													<button type="submit" name="" class="btn btn-edit"
														style="padding: 0px; border: 0px;">
														<i class="bi bi-pencil-square"></i>
													</button>
													<input type="hidden" name="itinerary_type_id"
														value="${itineraryTypeVO.itinerary_type_id}"> <input
														type="hidden" name="action" value="getOne_For_Update">
												</FORM>
											</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/back_end/itinerarytype/do"
													style="margin-bottom: 0px;">
													<button type="submit" class="btn btn-dele"
														style="padding: 0px; border: 0px">
														<i class="bi bi-trash-fill"></i>
													</button>
													<input type="hidden" name="itinerary_type_id"
														value="${itineraryTypeVO.itinerary_type_id}"> <input
														type="hidden" name="action" value="delete">
												</FORM>
											</td>


										</tr>
									</c:forEach>
								</table>
							</div>
							<!-- 分頁2 內容 -->
							<div class="tab-pane" id="itr-1-2"></div>
							<!-- 分頁3 內容 -->
							<div class="tab-pane" id="itr-1-3"></div>

						</div>

					</div>
				</div>
			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="js/back.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>