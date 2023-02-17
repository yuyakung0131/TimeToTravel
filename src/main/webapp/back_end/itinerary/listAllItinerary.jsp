<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itinerary.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<!-- GET_ALL -->
<%
List<ItineraryVO> list = (List<ItineraryVO>) request.getAttribute("list");
if (list == null) {
	ItineraryService itinerarySvc = new ItineraryService();
	// �ե�ItineraryService��getAll��k ���olist<>
	list = itinerarySvc.getAll();
}
// �s�JpageContext�줤 
pageContext.setAttribute("list", list);
%>

<!-- ADD -->
<%
//
// ItineraryVO itineraryVO = (ItineraryVO) request.getAttribute("itineraryVO");
%>

<%-- --<%= itineraryVO==null %>--${itineraryVO.itinerary_type_id}-- <!-- line 100 --> --%>

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
							<a
								href="<%=request.getContextPath()%>/back_end/itinerary/listAllItinerary.jsp"><h1>��{�޲z</h1></a>
						</div>

					</div>

					<!-- �q2 tab -->
					<div class="nav nav-tabs">
						<!-- ����1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#itr-2-1" aria-selected="true" id="itr-title-1">
							�d�ݦ�{</button>
						<!-- ����2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#itr-2-2" aria-selected="false" id="itr-title-2">
							�s�W��{</button>

					</div>
					<!-- �q2 ���e -->
					<div class="tab-content">
						<!-- ����1 ���e -->
						<div class="tab-pane show active" id="itr-2-1">

							<jsp:useBean id="itinerarytypeSvc" scope="page"
								class="com.itinerarytype.model.ItineraryTypeService" />
							<div class="row" style="margin-top: 10px">
								<div class="col-md-12 text-left">
									
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back_end/itinerary/itinerary.do">

											<b>��ܦ�{�W��:</b> <select style="width: 350px" size="1"
												name="itinerary_type_id">
												<c:forEach var="itineraryTypeVO"
													items="${itinerarytypeSvc.all}">
													<option value="${itineraryTypeVO.itinerary_type_id}"
														${(itineraryVO.itinerary_type_id==itineraryTypeVO.itinerary_type_id)? 'selected':'' }>${itineraryTypeVO.itinerary_title}</option>
												</c:forEach>
											</select> <input type="hidden" name="action"
												value="getItineraryType_For_Display"> <input
												style="width: 180px" type="submit" value="�e�X">
										</FORM>

									
								</div>
							</div>
							<form action="" class="search">
								<input type="text" name="" id="myInput" placeholder="�j�M...">
							</form>


							<table id="myTable" class="table tablesorter">
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
										<th>�ק�</th>
									</tr>
								</thead>

								<c:forEach var="itineraryVO" items="${list}">
									<tbody id="myBody">
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
											<c:if test="${itineraryVO.itinerary_state == 0}">
												<td>���`</td>
											</c:if>
											<c:if test="${itineraryVO.itinerary_state == 1}">
												<td>�B��</td>
											</c:if>
											<c:if test="${itineraryVO.itinerary_state == 2}">
												<td>����</td>
											</c:if>
											<c:if test="${itineraryVO.itinerary_state == 3}">
												<td>���}����W</td>
											</c:if>

											<td>
												<form method="post"
													action="<%=request.getContextPath()%>/back_end/itinerary/itinerary.do">
													<input type="hidden" name="action"
														value="getOne_For_Update"> <input type="hidden"
														name="itinerary_id" value="${itineraryVO.itinerary_id}">
													<button class="edit btn btn-info" type="submit">�ק�</button>
												</form>

											</td>

										</tr>
									</tbody>
								</c:forEach>
							</table>


							<c:if test="${openModal!=null}">





								<div class="modal fade show" style="display: block;"
									id="basicModal">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">

											<div class="modal-body">
												��{��ƭק�

												<jsp:include page="update_itinerary_input.jsp" />

											</div>

										</div>
									</div>
								</div>

							</c:if>

						</div>
						<!-- ����2 ���e -->
						<div class="tab-pane" id="itr-2-2">
							<jsp:include page="addItinerary.jsp" />
							<%-- 				<c:if test="${not empty errorMsgs}"> --%>
							<!-- 					<font style="color: red">�Эץ��H�U���~:</font> -->
							<!-- 					<ul> -->
							<%-- 						<c:forEach var="message" items="${errorMsgs}"> --%>
							<%-- 							<li id="ermsg" style="color: red">${message}</li> --%>
							<%-- 						</c:forEach> --%>
							<!-- 					</ul> -->
							<%-- 				</c:if> --%>

							<!-- 				<FORM METHOD="post" ACTION="itinerary.do" name="form1"> -->

							<!-- 					<table> -->

							<%-- 												<jsp:useBean id="itinerarytypeSvc" scope="page" --%>
							<%-- 													class="com.itinerarytype.model.ItineraryTypeService" /> --%>
							<!-- 						<tr> -->
							<!-- 							<td>�п�ܦ�{����:<font color=red><b>*</b></font></td> -->
							<!-- 							<td><select size="1" name="itinerary_type_id"> -->
							<%-- 									<c:forEach var="itineraryTypeVO" --%>
							<%-- 										items="${itinerarytypeSvc.all}"> --%>
							<%-- 										<option value="${itineraryTypeVO.itinerary_type_id}" --%>
							<%-- 											${(itineraryVO.itinerary_type_id==itineraryTypeVO.itinerary_type_id)? 'selected':'' }>${itineraryTypeVO.itinerary_title} --%>
							<%-- 									</c:forEach> --%>
							<!-- 							</select></td> -->
							<!-- 						</tr> -->
							<!-- 						<tr> -->
							<!-- 							<td>���W�}�l��:</td> -->
							<!-- 							<td><input name="entered_start" id="A_date1" type="text"></td> -->
							<!-- 						</tr> -->
							<!-- 						<tr> -->
							<!-- 							<td>���W������:</td> -->
							<!-- 							<td><input name="entered_end" id="B_date2" type="text"></td> -->
							<!-- 						</tr> -->
							<!-- 						<tr> -->
							<!-- 							<td>��{�}�l��:</td> -->
							<!-- 							<td><input name="itinerary_start" id="C_date3" type="text"></td> -->
							<!-- 						</tr> -->
							<!-- 						<tr> -->
							<!-- 							<td>��{������:</td> -->
							<!-- 							<td><input name="itinerary_end" id="D_date4" type="text"></td> -->
							<!-- 						</tr> -->
							<!-- 						<tr> -->
							<!-- 							<td>��{���A:<font color=red><b>*</b></font></td> -->
							<!-- 							<td><select size="1" name="itinerary_state"> -->
							<!-- 									<option value="3" -->
							<%-- 										${(itineraryVO.itinerary_state==3)? 'selected':'' }>���}�l���W --%>




							<!-- 									<option value="0" -->
							<%-- 										${(itineraryVO.itinerary_state==0)? 'selected':'' }>���` --%>




							<!-- 									<option value="1" -->
							<%-- 										${(itineraryVO.itinerary_state==1)? 'selected':'' }>�B�� --%>




							<!-- 									<option value="2" -->
							<%-- 										${(itineraryVO.itinerary_state==2)? 'selected':'' }>���� --%>




							<!-- 							</select></td> -->
							<!-- 						</tr> -->
							<!-- 						<tr> -->
							<!-- 							<td>�ثe���W�H��:</td> -->
							<!-- 							<td><input type="TEXT" name="itinerary_now" size="10" -->
							<%-- 								value="<%=(itineraryVO == null) ? "0" : itineraryVO.getItinerary_now()%>" /></td> --%>
							<!-- 						</tr> -->

							<!-- 						<tr> -->
							<!-- 							<td>��{�O��:</td> -->
							<!-- 							<td><input type="TEXT" name="itinerary_price" size="10" -->
							<%-- 								value="<%=(itineraryVO == null) ? "" : itineraryVO.getItinerary_price()%>" /></td> --%>
							<!-- 						</tr> -->

							<!-- 						<tr> -->
							<!-- 							<td>�̤֦���H��:</td> -->
							<!-- 							<td><input type="TEXT" name="itinerary_min" size="10" -->
							<%-- 								value="<%=(itineraryVO == null) ? "" : itineraryVO.getItinerary_min()%>" /></td> --%>
							<!-- 						</tr> -->

							<!-- 						<tr> -->
							<!-- 							<td>�̦h�i���W�H��:</td> -->

							<!-- 							<td><input type="TEXT" name="itinerary_max" size="10" -->
							<%-- 								value="<%=(itineraryVO == null) ? "0" : itineraryVO.getItinerary_max()%>" /></td> --%>
							<!-- 						</tr> -->

							<!-- 					</table> -->


							<!-- 					<br> <input type="hidden" name="action" value="insert"> -->
							<!-- 					<input type="submit" value="�e�X�s�W"> -->
							<!-- 				</FORM> -->



						</div>
						<!-- ����3 ���e -->
						<div class="tab-pane" id="itr-2-3"></div>

					</div>

				</div>
			</section>


		</div>

	</div>

	<!--     <script src="js/sweetaler2.all.min.js"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/css/theme.blue.min.css"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/js/jquery.tablesorter.min.js"></script>
	<script src="js/back.js"></script>
</body>

<%
java.sql.Date entered_start = null;
try {
	entered_start = java.sql.Date.valueOf(request.getParameter("entered_start").trim());
} catch (Exception e) {
	entered_start = new java.sql.Date(System.currentTimeMillis());
}

java.sql.Date entered_end = null;
try {
	entered_end = java.sql.Date.valueOf(request.getParameter("entered_end").trim());
} catch (Exception e) {
	entered_end = new java.sql.Date(System.currentTimeMillis());
}

java.sql.Date itinerary_start = null;
try {
	itinerary_start = java.sql.Date.valueOf(request.getParameter("itinerary_start").trim());
} catch (Exception e) {
	itinerary_start = new java.sql.Date(System.currentTimeMillis());
}

java.sql.Date itinerary_end = null;
try {
	itinerary_end = java.sql.Date.valueOf(request.getParameter("itinerary_end").trim());
} catch (Exception e) {
	itinerary_end = new java.sql.Date(System.currentTimeMillis());
}
%>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>


<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#A_date1').datetimepicker({
	  format:'Y-m-d',
	  value: '<%=entered_start%>',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#B_date2').val()?$('#B_date2').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#B_date2').datetimepicker({
	  format:'Y-m-d',
	  value: '<%=entered_end%>',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#A_date1').val()?$('#A_date1').val():false,
	    maxDate:$('#C_date3').val()?$('#C_date3').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#C_date3').datetimepicker({
		  format:'Y-m-d',
	  value: '<%=itinerary_start%>',
		  onShow:function(){
		   this.setOptions({
		    maxDate:$('#D_date4').val()?$('#D_date4').val():false,
		    minDate:$('#B_date2').val()?$('#B_date2').val():false
		   })
		  },
		  timepicker:false
		 });
		 
	 $('#D_date4').datetimepicker({
		  format:'Y-m-d',
	  value: '<%=itinerary_end%>
	',
			onShow : function() {
				this.setOptions({
					minDate : $('#C_date3').val() ? $('#C_date3').val() : false
				})
			},
			timepicker : false
		});
	});


</script>


</html>