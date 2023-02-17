<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itinerarytype.model.*"%>

<%
ItineraryTypeVO itineraryTypeVO = (ItineraryTypeVO) request.getAttribute("itineraryTypeVO");
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
	href="<%=request.getContextPath()%>/back_end/itinerarytype/css/bootstrap.min.css"
	type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/itinerarytype/css/back.css"
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
							data-bs-target="#itr-1-1" aria-selected="true">�ק��{����</button>



					</div>
					<!-- �q2 ���e -->
					<div class="tab-content">
						<!-- ����1 ���e -->
						<div class="tab-pane show active" id="itr-1-1">

							<%-- ���~��C --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">�Эץ��H�U���~:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>



							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back_end/itinerarytype/do"
								name="form1">

								<input type="hidden" name="itinerary_type_id"
									value="<%=itineraryTypeVO.getItinerary_type_id()%>" />
								<table table border=1 rules="rows" class="table tablesorter">
									<jsp:useBean id="itineraryclassSvc" scope="page"
										class="com.itineraryclass.model.ItineraryClassService" />
									<jsp:useBean id="firmSvc" scope="page"
										class="com.firm.model.FirmService" />
									<tr>
										<td>�п�ܦ�{����:<font color=red><b>*</b></font></td>
										<td><select size="1" name="itinerary_class_id">
												<c:forEach var="itineraryClassVO"
													items="${itineraryclassSvc.all}">
													<option value="${itineraryClassVO.itinerary_class_id}"
														${(itineraryTyprVO.itinerary_class_id==itineraryClassVO.itinerary_class_id)? 'selected':'' }>${itineraryClassVO.itinerary_class_name}
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td>�п�ܼt��:<font color=red><b>*</b></font></td>
										<td><select size="1" name="firm_id">
												<c:forEach var="firmVO" items="${firmSvc.itrGetItrFirm}">
													<option value="${firmVO.firm_id}"
														${(itineraryTypeVO.firm_id==firmVO.firm_id)? 'selected':'' }>${firmVO.firm_name}
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td>��{���D:</td>
										<td><input type="TEXT" name="itinerary_title"
											value="<%=itineraryTypeVO.getItinerary_title()%>" /></td>
									</tr>
									<tr>
										<td>��{��T:</td>
										<td><textarea class="form-control" id="myTextarea"
												name="itinerary_info" rows="7"><%=itineraryTypeVO.getItinerary_info()%></textarea>
											<!-- 										<input type="TEXT" name="itinerary_info" --> <%-- 											value="<%=itineraryTypeVO.getItinerary_info()%>" /> --%>

										</td>
									</tr>
									<tr>
										<td>��L����:</td>
										<td><textarea class="form-control" id="myTextarea"
												name="itinerary_other" rows="7"><%=itineraryTypeVO.getItinerary_other()%></textarea>
											<!-- 										<input type="TEXT" name="itinerary_other" -->
											<%-- 											value="<%=itineraryTypeVO.getItinerary_other()%>" /> --%>

										</td>
									</tr>
									<tr>
										<td>��{�O��:</td>
										<td><input type="TEXT" name="itinerary_price"
											value="<%=itineraryTypeVO.getItinerary_price()%>" /></td>
									</tr>
									<tr>
										<td>�̤֦���H��:</td>
										<td><input type="TEXT" name="itinerary_min"
											value="<%=itineraryTypeVO.getItinerary_min()%>" /></td>
									</tr>
									<tr>
										<td>�̦h�i���W�H��:</td>
										<td><input type="TEXT" name="itinerary_max"
											value="<%=itineraryTypeVO.getItinerary_max()%>" /></td>
									</tr>
									<tr>
										<td>��{���A:<font color=red><b>*</b></td>
										<td><select size="1" name="itinerary_type_state">
												<option value="0"
													${(itineraryTypeVO.itinerary_type_state==0)? 'selected':'' }>�W�[
												
												<option value="1"
													${(itineraryTypeVO.itinerary_type_state==1)? 'selected':'' }>�U�[
												
										</select></td>
									</tr>
									<tr>
										<td>��{�`����:</td>
										<td><input type="TEXT" name="itinerary_total_score"
											value="<%=itineraryTypeVO.getItinerary_total_score()%>" /></td>
									</tr>
									<tr>
										<td>��{�����`�H��:</td>
										<td><input type="TEXT" name="itinerary_total_people"
											value="<%=itineraryTypeVO.getItinerary_total_people()%>" /></td>
									</tr>


								</table>
								<br> <input type="hidden" name="action" value="update">
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
		src="<%=request.getContextPath()%>/back_end/itinerarytype/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/itinerarytype/js/bootstrap.min.js"></script>
</body>

</html>