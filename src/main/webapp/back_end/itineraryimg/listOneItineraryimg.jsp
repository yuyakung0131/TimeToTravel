<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itineraryimg.model.*"%>
<%@ page import="java.util.*"%>


<%
Integer itinerary_type_id = (Integer) request.getAttribute("itinerary_type_id");
ItineraryImgService itineraryimgSvc = new ItineraryImgService();
List<ItineraryImgVO> list = itineraryimgSvc.findByItineraryTypeId(itinerary_type_id);
pageContext.setAttribute("list", list);

ItineraryImgVO itineraryImgVO = (ItineraryImgVO) request.getAttribute("itineraryImgVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/itineraryimg/css/bootstrap.min.css"
	type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/itineraryimg/css/back.css"
	type="text/css">
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
							<h1>��{�����޲z</h1>
						</div>

					</div>

					<!-- �q2 tab -->
					<div class="nav nav-tabs nav-justified">
						<!-- ����1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#itr-1-1" aria-selected="true">�d�ݦ�{�Ϥ�</button>


					</div>
					<!-- �q2 ���e -->
					<div class="tab-content">
						<!-- ����1 ���e -->
						<div class="tab-pane show active" id="itr-1-1">

							<table table border=1 rules="rows" class="table">
								<tr>
									<th>��{�s��</th>
									<th>��{�Ϥ��s��</th>
									<th>��{�Ϥ�</th>
								</tr>
								<tr>
									<c:forEach var="itineraryImgVO" items="${list}">
										<tr>
											<td>${itineraryImgVO.itineraryTypeVO.itinerary_title}</td>
											<td>${itineraryImgVO.itinerary_img_id}</td>
											<c:if test="${itineraryImgVO.itinerary_img != null}">
												<td><img
													src="data:image/png;base64,${Base64.getEncoder().encodeToString(itineraryImgVO.itinerary_img)}"
													width=300px height=250px></td>
											</c:if>
											<c:if test="${itineraryImgVO.itinerary_img == null}">
												<td><h5>�d�L�Ϥ�</h5></td>
											</c:if>
											<td>
												<form method="post"
													action="<%=request.getContextPath()%>/back_end/itineraryimg/do"
													style="margin-bottom: 0px;">
													<button type="submit" name="" class="btn btn-edit"
														style="padding: 0px; border: 0px;">
														<i class="bi bi-pencil-square"></i>
													</button>
													<input type="hidden" name="itinerary_img_id"
														value="${itineraryImgVO.itinerary_img_id}"> <input
														type="hidden" name="action" value="getOne_For_Update">
												</form>
											</td>
											<td>
												<form method="post"
													action="<%=request.getContextPath()%>/back_end/itineraryimg/do"
													style="margin-bottom: 0px;">
													<button type="submit" class="btn btn-dele"
														style="padding: 0px; border: 0px">
														<i class="bi bi-trash-fill"></i>
													</button>
													<input type="hidden" name="itinerary_img_id"
														value="${itineraryImgVO.itinerary_img_id}"> <input
														type="hidden" name="action" value="delete">
												</form>
											</td>
										</tr>
									</c:forEach>
								</tr>
							</table>
						</div>
						<!-- ����2 ���e -->
						<div class="tab-pane" id="itr-1-2"></div>
						<!-- ����3 ���e -->
						<div class="tab-pane" id="itr-1-3"></div>

						<!-- ����4 ���e -->
						<div class="tab-pane" id="itr-1-4"></div>

					</div>

				</div>
			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/itineraryimg/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/itineraryimg/js/bootstrap.min.js"></script>
</body>

</html>