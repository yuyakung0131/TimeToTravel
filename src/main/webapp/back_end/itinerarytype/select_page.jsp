<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itinerarytype.model.*"%>
<%@ page import="com.itineraryclass.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="itineraryLocSvc" scope="page"
	class="com.itineraryloc.model.ItineraryLocService" />

<%
ItineraryTypeService itinerarytypeSvc = new ItineraryTypeService();
List<ItineraryTypeVO> list = itinerarytypeSvc.getAll();
pageContext.setAttribute("list", list);

ItineraryClassService itineraryclassSvc1 = new ItineraryClassService();
List<ItineraryClassVO> classlist = itineraryclassSvc1.getAll();
pageContext.setAttribute("classlist", classlist);

ItineraryTypeVO itineraryTypeVO = (ItineraryTypeVO) request.getAttribute("itineraryTypeVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
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
							data-bs-target="#itr-1-1" aria-selected="true">�d�ݦ�{����</button>
						<!-- ����2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#itr-1-2" aria-selected="false">�s�W��{����</button>
						<!-- ����3 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#itr-1-3" aria-selected="false">��{�Ϥ��޲z</button>
						<!-- ����4 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#itr-1-4" aria-selected="false">�s�W��{�Ϥ�</button>
						<!-- ����5 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#itr-1-5" aria-selected="false">��{���O</button>


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


							<div class="row search">
								<div class="col-md-12">
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back_end/itinerarytype/do">
										<b>��ܦ�{���D:</b> <select size=1 name="itinerary_type_id">
											<c:forEach var="itineraryTypeVO" items="${list}">
												<option value="${itineraryTypeVO.itinerary_type_id}">${itineraryTypeVO.itinerary_title}
											</c:forEach>
										</select><input type="hidden" name="action" value="getOne_For_Display">
										<button id="btn-search">�j�M</button>
									</FORM>
								</div>
							</div>
							<jsp:useBean id="itineraryclassSvc" scope="page"
								class="com.itineraryclass.model.ItineraryClassService" />
							<div class="row search">
								<div class="col-md-12">
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back_end/itinerarytype/do">
										<b>��ܦ�{���O:</b> <select size="1" name="itinerary_class_id">
											<c:forEach var="itineraryClassVO"
												items="${itineraryclassSvc.all}">
												<option value="${itineraryClassVO.itinerary_class_id}"
													${(itineraryTypeVO.itinerary_class_id==itineraryClassVO.itinerary_class_id)? 'selected':'' }>${itineraryClassVO.itinerary_class_name}
											</c:forEach>
										</select> <input type="hidden" name="action"
											value="getItineraryClass_For_Display">
										<button id="btn-search">�j�M</button>
									</FORM>
								</div>
							</div>
							<jsp:useBean id="firmSvc" scope="page"
								class="com.firm.model.FirmService" />
							<div class="row search">
								<div class="col-md-12">
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back_end/itinerarytype/do">
										<b>��ܼt��:</b> <select size="1" name="firm_id">
											<c:forEach var="firmVO" items="${firmSvc.itrGetItrFirm}">
												<option value="${firmVO.firm_id}"
													${(itineraryTypeVO.firm_id==firmVO.firm_id)? 'selected':'' }>${firmVO.firm_name}
												</option>
											</c:forEach>
										</select> <input type="hidden" name="action"
											value="getFirm_For_Display">
										<button id="btn-search">�j�M</button>
									</FORM>
								</div>
							</div>

							<form action="" class="seach">
								<input type="text" name="" id="myInput" placeholder="�j�M...">
							</form>

							<table id="myTable" table border=1 rules="rows"
								class="table tablesorter">
								<tr>
									<th>��{�����s��</th>
									<th>��{���O</th>
									<th>�t�ӽs��</th>
									<th>��{���D</th>
									<th>��{��T</th>
									<th>��L����</th>
									<th>��{�O��</th>
									<th>�̤֦���H��</th>
									<th>�̦h�i���W�H��</th>
									<th>��{���A</th>
									<th>�ק�</th>
									<th>�R��</th>
									<th>�d�߹Ϥ�</th>

								</tr>
								<c:forEach var="itineraryTypeVO" items="${list}">
									<tbody id="myBody">
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
											<td>${itineraryTypeVO.itinerary_type_state == 0 ? "�W�[" : "�U�["}</td>

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
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/back_end/itineraryimg/do"
													style="margin-bottom: 0px;">
													<button type="submit" class="btn btn-dele"
														style="padding: 0px; border: 0px">
														<i class="bi bi-image"></i>
													</button>
													<input type="hidden" name="itinerary_type_id"
														value="${itineraryTypeVO.itinerary_type_id}"> <input
														type="hidden" name="action" value="getOne_For_Display">
												</FORM>
											</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</div>
						<!-- ����2 ���e -->
						<div class="tab-pane" id="itr-1-2">

							<%-- ���~��C --%>
							<c:if test="${not empty errorMsgs2}">
								<font style="color: red">�Эץ��H�U���~:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs2}">
										<li style="color: red">${message2}</li>
									</c:forEach>
								</ul>
							</c:if>


							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back_end/itinerarytype/do"
								name="form1">

								<table table border=1 rules="rows" class="table tablesorter">
									<tr>
										<td>�п�ܦ�{����:<font color=red><b>*</b></font></td>
										<td><select size="1" name="itinerary_class_id">
												<c:forEach var="itineraryClassVO"
													items="${itineraryclassSvc.all}">
													<option value="${itineraryClassVO.itinerary_class_id}"
														${(itineraryTypeVO.itinerary_class_id==itineraryClassVO.itinerary_class_id)? 'selected':'' }>${itineraryClassVO.itinerary_class_name}
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
										<td><input type="TEXT" name="itinerary_title" /></td>
									</tr>
									<tr>
										<td>��{����:</td>
										<td><c:forEach var="itinerarylocVO"
												items="${itineraryLocSvc.all}">

												<input type="checkbox"
													value="${itinerarylocVO.itinerary_loc_id}"
													name="itinerary_loc_id">
												<label for="locId">${itinerarylocVO.itinerary_loc_name}</label>

											</c:forEach></td>
									</tr>


									<tr>
										<td>��{��T:</td>
										<td><textarea class="form-control" id="myTextarea"
												name="itinerary_info" rows="7"></textarea></td>
									</tr>
									<tr>
										<td>��L����:</td>
										<td><textarea class="form-control" id="myTextarea"
												name="itinerary_other" rows="7"></textarea></td>
									</tr>
									<tr>
										<td>��{�O��:</td>
										<td><input type="TEXT" name="itinerary_price" /></td>
									</tr>
									<tr>
										<td>�̤֦���H��:</td>
										<td><input type="TEXT" name="itinerary_min" /></td>
									</tr>
									<tr>
										<td>�̦h�i���W�H��:</td>
										<td><input type="TEXT" name="itinerary_max" /></td>
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



								</table>
								<br> <input type="hidden" name="action" value="insert">
								<button type="submit" class="btn btn-info">�e�X�s�W</button>
							</FORM>
						</div>

						<!-- ����3 ���e -->
						<div class="tab-pane" id="itr-1-3">
							<div class="row search">
								<div class="col-md-12">
									<a
										href="<%=request.getContextPath()%>/back_end/itineraryimg/listAllItineraryImg.jsp">�d��</a>�Ҧ���{�Ϥ�
								</div>
							</div>
						</div>

						<!-- ����4 ���e -->
						<div class="tab-pane" id="itr-1-4">
							<div class="row search">
								<div class="col-md-12">
									<a
										href="<%=request.getContextPath()%>/back_end/itineraryimg/addItineraryImg.jsp">�s�W</a>��{�Ϥ�
								</div>
							</div>
						</div>

						<!-- ����5 ���e -->
						<div class="tab-pane" id="itr-1-5">
							<%-- ���~��C --%>
							<c:if test="${not empty errorMsgs2}">
								<font style="color: red">�Эץ��H�U���~:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs2}">
										<li style="color: red">${message2}</li>
									</c:forEach>
								</ul>
							</c:if>


							<form METHOD="post"
								ACTION="<%=request.getContextPath()%>/back_end/itinerarytype/class.do"
								name="form1" class="d-flex">
								<table>
									<tr>
										<td>��{���O�W��</td>
										<td><input type="TEXT" name="itinerary_class_name" /></td>
									</tr>
								</table>
								<input type="hidden" name="action" value="insert">
								<button id="btn-search" type="submit">�e�X�s�W</button>
							</form>

							<table table border=1 rules="rows" class="table ">
								<tr>
									<th>��{���O�s��</th>
									<th>��{���O�W��</th>
									<th>�ק�</th>
								</tr>
								<c:forEach var="itineraryClassVO" items="${classlist}">
									<tr>
										<td>${itineraryClassVO.itinerary_class_id }</td>
										<td>${itineraryClassVO.itinerary_class_name }</td>


										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back_end/itinerarytype/class.do"
												style="margin-bottom: 0px;">
												<button type="submit" name="" class="btn btn-edit"
													style="padding: 0px; border: 0px;">
													<i class="bi bi-pencil-square"></i>
												</button>
												<input type="hidden" name="itinerary_class_id"
													value="${itineraryClassVO.itinerary_class_id}"> <input
													type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>
									</tr>
								</c:forEach>
							</table>
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