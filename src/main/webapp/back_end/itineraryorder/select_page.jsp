<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itineraryorder.model.*"%>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="com.itinerarytype.model.*"%>
<%@ page import="java.util.*"%>

<%
ItineraryOrderService itineraryorderSvc = new ItineraryOrderService();
List<ItineraryOrderVO> list = itineraryorderSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
ItineraryOrderVO itineraryOrderVO = (ItineraryOrderVO) request.getAttribute("itineraryOrderVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
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
	href="<%=request.getContextPath()%>/back_end/itineraryorder/css/bootstrap.min.css"
	type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/itineraryorder/css/back.css"
	type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<style>
#itr-3-2 {
	text-align: left;
	padding: 10px;
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
							<h1>��{�q��޲z</h1>
						</div>

					</div>

					<!-- �q2 tab -->
					<div class="nav nav-tabs nav-justified">
						<!-- ����1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#itr-3-1" aria-selected="true">�d�ݦ�{�q��</button>
						<!-- ����2 -->
						<!--                         <button class="nav-link" data-bs-toggle="tab" data-bs-target="#itr-3-2" aria-selected="false"> -->
						<!--                             �s�W��{���� -->
						<!--                         </button> -->
						<!-- ����3 -->
						<!--                         <button class="nav-link" data-bs-toggle="tab" data-bs-target="#itr-3-3" aria-selected="false"> -->
						<!--                             �s�W��{�����Ϥ� -->
						<!--                         </button> -->

					</div>
					<!-- �q2 ���e -->
					<div class="tab-content">
						<!-- ����1 ���e -->
						<div class="tab-pane show active" id="itr-3-1">

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
								ACTION="<%=request.getContextPath()%>/back_end/itineraryorder/order.do">
								<div class="row search">
									<div class="col-md-12">
										<b>��J�q��s��:</b> <input type="text" name="itinerary_order_id">
										<input type="hidden" name="action" value="getOne_For_Display">
										<button id="btn-search">�j�M</button>
									</div>
								</div>
							</FORM>
							<jsp:useBean id="memberSvc" scope="page"
								class="com.member.model.MemberService" />
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back_end/itineraryorder/order.do">
								<div class="row search">
									<div class="col-md-12">
										<b>�j�M�|���m�W:</b> <select size="1" name="member_id">
											<c:forEach var="memberVO" items="${memberSvc.ALL}">
												<option value="${memberVO.member_id}"
													${(itineraryOrderVO.member_id==memberVO.member_id)? 'selected':'' }>${memberVO.member_name}
											</c:forEach>
										</select> <input type="hidden" name="action"
											value="getMember_For_Display">
										<button id="btn-search">�j�M</button>
									</div>
								</div>
							</FORM>

							<form action="" class="seach">
								<input type="text" name="" id="myInput" placeholder="�j�M...">
							</form>

							<div>
								<table id="myTable" table border=1 rules="rows"
									class="table tablesorter">
									<thead>
										<tr>
											<th>�q��s��</th>
											<th>�|���m�W</th>
											<th>��{���D</th>
											<th>�q����</th>
											<th>���W�H��</th>
											<th>�q���`���B</th>
											<th>�q�檬�A</th>
											<th>�h�ڪ��A</th>
											<th>�q��Ƶ�</th>
											<th>�����q��</th>

										</tr>
									</thead>
									<c:forEach var="itineraryOrderVO" items="${list}">
										<tbody id="myBody">

											<tr>
												<td>${itineraryOrderVO.itinerary_order_id}</td>
												<td>${itineraryOrderVO.memberVO.member_name}</td>
												<td>${itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_title}</td>
												<td>${itineraryOrderVO.itinerary_order_time}</td>
												<td>${itineraryOrderVO.itinerary_people_num}</td>
												<td>${itineraryOrderVO.itinerary_ttl_price}</td>
												<td>${itineraryOrderVO.itinerary_order_state == 0 ? "�q�榨��" : "�q�����"}</td>
												<c:if test="${itineraryOrderVO.itinerary_refund_state == 0}">
													<td>�L�h��</td>
												</c:if>
												<c:if test="${itineraryOrderVO.itinerary_refund_state == 1}">
													<td>�h�ڤ�</td>
												</c:if>
												<c:if test="${itineraryOrderVO.itinerary_refund_state == 2}">
													<td>�����h��</td>
												</c:if>
												<td>${itineraryOrderVO.itinerary_order_memo}</td>



												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/back_end/itineraryorder/order.do"
														style="margin-bottom: 0px;">

														<input type="hidden" name="itinerary_id"
															value="${itineraryOrderVO.itinerary_id} " /> <input
															type="hidden" name="itinerary_order_id"
															value="${itineraryOrderVO.itinerary_order_id}" /> <input
															type="hidden" name="itinerary_people_num"
															value="${itineraryOrderVO.itinerary_people_num} " /> <input
															type="hidden" name="action" value="updateItrOrder">
														<c:if
															test="${itineraryOrderVO.itinerary_order_state  == 1}">
															<button type="submit" class="btn btn-light"
																disabled="disabled">�����q��</button>
														</c:if>
														<c:if
															test="${itineraryOrderVO.itinerary_order_state  == 0}">
															<button type="submit" class="btn btn-light">�����q��</button>
														</c:if>
													</FORM>
												</td>

											</tr>
										</tbody>
									</c:forEach>
								</table>
							</div>
						</div>
						<!-- ����2 ���e -->
						<div class="tab-pane" id="itr-3-2">
							<div>
								<label for="ITINERARY_CLASS_ID">��{���O�G</label> <select name=""
									id="ITINERARY_CLASS_ID">
									<option value="">���Ȧ�</option>
									<option value="">�`�׹C</option>
								</select>
								<p>
									<label for="FIRM_ID">�t�ӡG</label> <select name="" id="FIRM_ID">
										<option value="">������Ȧ��2</option>
										<option value="">������Ȧ��1</option>
									</select>
								<p>
									<label for="ITINERARY_TITLE">��{���D�G</label> <input type="text"
										value="" placeholder="��{���D" id="ITINERARY_TITLE">
								<p>
									<label for="ITINERARY_INFO">��{��T�G</label> <input type="text"
										value="" placeholder="��{��T" id="ITINERARY_INFO">
							</div>
						</div>
						<!-- ����3 ���e -->
						<div class="tab-pane" id="itr-3-3">
							<div>Ltur, adipisicing elit. Dolores, vel! Quam est facere
								expedita. Iusto voluptate quo ducimus necessitatibus, quos fuga
								autem sequi maxime, eveniet consequatur dolore voluptatum odit
								ad!</div>
						</div>

					</div>

				</div>
			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/itineraryorder/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/itineraryorder/js/bootstrap.min.js"></script>
	<script>
	 $(document).ready(function(){
         $("#myInput").on("keyup", function(){
             //��j�M����r
             let value = $(this).val().toLowerCase();
             //��table�ŦX
             $("#myBody tr").filter(function(){
                 $(this).toggle($(this).text().toLocaleLowerCase().indexOf(value)>-1)
             });
         });
    });
	
	</script>
</body>

</html>