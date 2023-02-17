<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.firm.model.*"%>

<%
RoomTypeVO roomTypeVO = (RoomTypeVO) request.getAttribute("roomTypeVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/roomtype/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/roomtype/css/back.css" type="text/css">
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
							<h1>房型管理</h1>
						</div>
					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs ">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">新增房型</button>
						<!-- 分頁2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-2" aria-selected="false">搜尋房型</button>
						<!-- 分頁3 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-3" aria-selected="false"></button>

					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="tab-1">
							<div class="container">
								<div class="row" style="text-align: initial;">
									<form method="post" action="<%=request.getContextPath()%>/back_end/roomtype/room.do" name="form1">
										<jsp:useBean id="firmSvc" scope="page"
											class="com.firm.model.FirmService" />
										<table>

											<tr>
												<td>廠商名稱:</td>
												<td><select size=1 name="firm_id">
														<c:forEach var="firmVO" items="${firmSvc.allRoomFirm }">
															<option value="${firmVO.firm_id}"
																${(param.firm_id==firmVO.firm_id)? 'selected':'' }>${firmVO.firm_name}</option>
														</c:forEach>
												</select></td>
											</tr>
											<tr>
												<td>房型名稱:</td>
												<td><input type="text" name="room_type_name" size="45"
													value="${param.room_type_name }"></td>
												<td>${errorMsgs.room_type_name }</td>
											</tr>
											<tr>
												<td>房型數量:</td>
												<td><input type="number" name="room_type_amount"
													size="45" value="${param.room_type_amount }"></td>
												<td>${errorMsgs.room_type_amount }</td>
											</tr>
											<tr>
												<td>房型說明:</td>
												<td><input type="text" name="room_type_content"
													size="45" value="${param.room_type_content }"></td>
												<td>${errorMsgs.room_type_content}</td>
											</tr>
											<tr>
												<td>房型狀態:</td>
												<td>
												<select name="room_type_state">
												<option value="1" selected>下架</option>
												<option value="0" >上架</option>
												</select>
													</td>
												<td>${errorMsgs.room_type_state}</td>
											</tr>
											<tr>
												<td>房型價格:</td>
												<td><input type="text" name="room_type_price" size="45"
													value="${param.room_type_price }"></td>
												<td>${errorMsgs.room_type_price }</td>
											</tr>
										</table>
										<br> <input type="hidden" name="action" value="insert">
										<input type="submit" value="送出新增">
									</FORM>
								</div>
							</div>
							
						</div>
						<!-- 分頁2 內容 -->
						<div class="tab-pane" id="tab-2">
						<div class="container">
								<div class="row" style="text-align: initial;">
									<form action="<%=request.getContextPath()%>/back_end/roomtype/room.do" method="post">
										<table>
											<tr>
												<td>請輸入房型編號:</td>
												<td style="font-color: red;"><input type="text"
													name="room_type_id" value="${param.room_type_id}">
													<input type="hidden" name="action" value="getOne_room_type">
													<input type="submit" value="查詢">${errorMsgs.room_type_id}
												</td>
											</tr>
										</table>
									</form>
									<jsp:useBean id="roomTypeSvc" scope="page"
										class="com.roomtype.model.RoomTypeService" />
									<form action="<%=request.getContextPath()%>/back_end/roomtype/room.do" method="post">
										<table>
											<tr>
												<td>請輸入房型編號:</td>
												<td><select size=1 name="room_type_id">
														<c:forEach var="roomTypeVO" items="${roomTypeSvc.all}">
															<option value="${roomTypeVO.room_type_id }">${roomTypeVO.room_type_id }</option>
														</c:forEach>
												</select></td>
												<td><input type="hidden" name="room_type_id"
													value="${roomTypeVO.room_type_id }"> <input
													type="hidden" name="action" value="getOne_room_type">
													<input type="submit" value="查詢"></td>
											</tr>
										</table>
									</form>
									<form action="room.do" method="post">
										<table>
											<tr>
												<td>請輸入房型名稱:</td>
												<td><select name="room_type_id" size=1>
														<c:forEach var="roomTypeVO" items="${roomTypeSvc.all }">
															<option value="${roomTypeVO.room_type_id }">${roomTypeVO.room_type_name }</option>
														</c:forEach>
												</select></td>
												<td><input type="hidden" name="room_type_name"
													value="${roomTypeVO.room_type_name }"> <input
													type="hidden" name="action" value="getOne_room_type">
													<input type="submit" value="查詢"></td>
											</tr>
										</table>
									</form>
									
									<%
									if (request.getAttribute("roomTypeVO") != null) {
									%>
									<jsp:include page="listOneRoomType.jsp" />

									<%
									}
									%>

								</div>
							</div>
						</div>
						<!-- 分頁3 內容 -->
						<div class="tab-pane" id="tab-3"></div>

					</div>
					<!-- =======================以上區塊可自訂======================= -->
				</div>
			</section>

			<a
				href="${pageContext.request.contextPath }/back_end/roomtype/select_page.jsp">首頁</a>
			<a
				href="${pageContext.request.contextPath }/back_end/roomtype/addRoomType.jsp">新增</a>
			<a
				href="${pageContext.request.contextPath }/back_end/roomtype/listAllRoomType.jsp">查全部</a>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/roomtype/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/roomtype/js/bootstrap.min.js"></script>
</body>

</html>