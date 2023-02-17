<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itineraryimg.model.*"%>
<%@ page import="java.util.*"%>


<%
ItineraryImgVO itineraryImgVO = (ItineraryImgVO) request.getAttribute("itineraryImgVO");
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
							data-bs-target="#itr-1-1" aria-selected="true">�ק��{�����Ϥ�</button>



					</div>
					<!-- �q2 ���e -->
					<div class="tab-content">
						<!-- ����1 ���e -->
						<div class="tab-pane show active" id="itr-1-1">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back_end/itineraryimg/do"
								name="form1" enctype="multipart/form-data">
								<table>

									<tr>
										<td>��{�Ϥ��s��:</td>
										<td><%=itineraryImgVO.getItinerary_img_id()%></td>
									</tr>

									<tr>
										<td>��{�Ϥ�</td>
										<td><input type="file" name="upfile1" /></td>
									</tr>

									<jsp:useBean id="itineraryTypeSvc" scope="page"
										class="com.itinerarytype.model.ItineraryTypeService" />
									<tr>
										<td>��{�W��:</td>
										<td><select size="1" name="itinerary_type_id">
												<c:forEach var="itineraryTypeVO"
													items="${itineraryTypeSvc.all}">
													<option value="${itineraryTypeVO.itinerary_type_id}"
														${(itineraryImgVO.itinerary_type_id==itineraryTypeVO.itinerary_type_id)? 'selected':'' }>${itineraryTypeVO.itinerary_title}
												</c:forEach>
										</select></td>
									</tr>
									<tr>
								</table>
								<input type="hidden" name="action" value="update"> <input
									type="hidden" name="itinerary_img_id"
									value="<%=itineraryImgVO.getItinerary_img_id()%>">
								<button type="submit" class="btn btn-info">�e�X�ק�</button>
							</FORM>


						</div>



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