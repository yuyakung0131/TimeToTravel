<%@page import="com.firm.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%
FirmService firmSvc = new FirmService();
List<FirmVO> list = firmSvc.getAllRoomFirm();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Time To Travel</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap"
	rel="stylesheet">

<!-- Css Styles -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/daterangepicker.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/styleforheader_footerNEW.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>
<style>
.firm_block {
	background-color: rgb(255, 255, 255);
	border: 1px solid rgb(210, 210, 210);
	margin-bottom: 10px;
	height: 150px;
	border-radius: 10px;
	line-height: 25px;
	letter-spacing: 3px;
}

.firm_block:hover {
 box-shadow: 1px 3px 10px gray;
 cursor: pointer;
 transition:0.3s;
 transform:translate(0px,-5px);
}

.firm_block a {
	font-weight: 700;
	color: #272727;
}

.firm_block a:hover {
	color: #959da5;
}

.form-control:focus {
	color: #495057;
	background-color: #fff;
	border-color: #e8b15c;
	outline: 0;
	box-shadow: 0 0 0 0.2rem rgb(249 141 15/ 25%);
}

.switch_btn_add, .switch_btn_name:hover {
	cursor: pointer;
}
</style>
</head>

<body>
	<!-- Page Preloder Begin -->
	<jsp:include page="../preLoder.jsp"></jsp:include>
	<!-- Page Preloder End -->
	<!-- Offcanvas Menu Section Begin -->
	<c:choose>
		<c:when test="${memberVO.member_id == null}">
			<!-- offcanvasMenuSection_nonmember -->
			<jsp:include page="../offcanvasMenuSection_nonmember.jsp"></jsp:include>
		</c:when>
		<c:when test="${memberVO.member_id != null}">
			<!-- offcanvasMenuSection_member -->
			<jsp:include page="../offcanvasMenuSection_member.jsp"></jsp:include>
		</c:when>
	</c:choose>
	<!-- Offcanvas Menu Section End -->
	<div class="toolbarFixed">
		<!-- header Begin -->
		<c:choose>
			<c:when test="${memberVO.member_id == null}">
				<!-- header_nonmember -->
				<jsp:include page="../header_nonmember.jsp"></jsp:include>
			</c:when>
			<c:when test="${memberVO.member_id != null}">
				<!-- header_member -->
				<jsp:include page="../header_member.jsp"></jsp:include>
			</c:when>
		</c:choose>
		<!-- Header End -->
		<!--Search Start-->
		<div class="search_by_name container"
			style="margin-top: 15px; margin-bottom: 15px; border-radius: 10px 10px 10px 10px; box-shadow: 0px 1px 3px #888888; position: sticky; top: 79px; background-color: white; z-index: 1;">
			<div class="search_body">
				<form method="post"
					action="<%=request.getContextPath()%>/front_end/roomtype/roomSearch.do">
					<div class="row"
						style="padding: 20px 0px; justify-content: center;">
						<div class="col-lg-2"
							style="display: flex; justify-content: center; align-items: center;">
							<div class="switch_btn_name"
								style="border: 1px solid #c8b9b9; border-radius: 5px 5px 5px 5px; padding: 5px 5px; font-weight: 600; color: white; background-color: #81a3a9;">找飯店</div>
						</div>
						<div class="col-lg-4">
							<div class="location ">
								<input class="form-control" style="font-size: 14px;"
									name="search_words_name" placeholder="請輸入飯店" autocomplete="off"
									value="${search_words_name }" required>
							</div>
						</div>
						<div class="col-lg-3 ">
							<div class="input-group">
								<input class="form-control" style="font-size: 14px;" id="from"
									name="start_date" placeholder="入住" autocomplete="off"
									value="${start_date }" required> <input
									class="form-control" style="font-size: 14px;" id="to"
									name="end_date" placeholder="退房" autocomplete="off"
									value="${end_date }" required>
							</div>
						</div>

						<div class="col-lg-1">
							<button class="date_search_btn" type="submit"
								style="width: 100%; height: 100%; border: 0px; background-color: #dfa974; border-radius: 10px; color: white; font-weight: 600;">搜尋</button>
							<input type="hidden" name="action"
								value="search_roomFirm_by_name">
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="search_by_add container"
			style="display: none; margin-top: 15px; margin-bottom: 15px; border-radius: 10px 10px 10px 10px; box-shadow: 0px 1px 3px #888888; position: sticky; top: 79px; background-color: white; z-index: 1;">
			<div class="search_body">
				<form method="post"
					action="<%=request.getContextPath()%>/front_end/roomtype/roomSearch.do">
					<div class="row"
						style="padding: 20px 0px; justify-content: center;">
						<div class="col-lg-2"
							style="display: flex; justify-content: center; align-items: center;">
							<div class="switch_btn_add"
								style="border: 1px solid #c8b9b9; border-radius: 5px 5px 5px 5px; padding: 5px 5px; font-weight: 600; color: white; background-color: #81a3a9;">找地區</div>
						</div>
						<div class="col-lg-4">
							<div class="location ">
								<input class="form-control" style="font-size: 14px;"
									name="search_words_add" placeholder="請輸入地區" autocomplete="off"
									value="${search_words_add }" required>
							</div>
						</div>
						<div class="col-lg-3 ">
							<div class="input-group">
								<input class="form-control" style="font-size: 14px;" id="from"
									name="start_date" placeholder="入住" autocomplete="off"
									value="${start_date }" required> <input
									class="form-control" style="font-size: 14px;" id="to"
									name="end_date" placeholder="退房" autocomplete="off"
									value="${end_date }" required>
							</div>
						</div>

						<div class="col-lg-1">
							<button class="date_search_btn" type="submit"
								style="width: 100%; height: 100%; border: 0px; background-color: #dfa974; border-radius: 10px; color: white; font-weight: 600;">搜尋</button>
							<input type="hidden" name="action" value="search_roomFirm_by_add">
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--Search End-->
		<!--RoomsFirm -->
		<div class="roomsFirm">
			<div class="container">
				<div class="row gx-4 gx-lg-5 justify-content-center">
					<div class="col-md-8 ">
						<div class="firm_body">

							<c:if test="${roomSearchList!=null }">
								<c:forEach var="firmVO" items="${roomSearchList}">
									<form
										action="<%=request.getContextPath()%>/front_end/roomtype/room.do"
										method="post">
										<div class="firm_block" style="display: flex;">
											<div class="">
												<img
													style="width: 100%; height: 100%; border-radius: 10px 0px 0px 10px;"
													src="<%=request.getContextPath()%>/ShowRoomFirmImgServlet?firm_id=${firmVO.firm_id}">
											</div>
											<div class="col-lg-9"
												style="font-size: 18px; font-weight: 700; display: flex; justify-content: space-between; padding-top: 10px;">
												<div>${firmVO.firm_name }</div>
												<c:set var="addString" value="${ firmVO.firm_operate_add }" />
												<c:set var="add" value="${fn:substring(addString,0,6)}" />
												<div
													style="display: flex; flex-direction: column; justify-content: space-between; align-items: flex-end;">
													<p style="margin: 0px; font-size: 14px;">
														<i class="fa-solid fa-map-pin"></i><a
															href="<%=request.getContextPath()%>/front_end/roomtype/roomSearch.do?action=get_one_firm_add&firm_operate_add=${fn:substring(add,0,3)}&start_date=${start_date}&end_date=${end_date}">${fn:substring(add,0,3)}</a><a
															href="<%=request.getContextPath()%>/front_end/roomtype/roomSearch.do?action=get_one_firm_add&firm_operate_add=${fn:substring(add,3,6)}&start_date=${start_date}&end_date=${end_date}">${fn:substring(add,3,6)}</a>
													</p>
													<div>
														<p style="font-size: 16px;">查看此飯店</p>
													</div>
												</div>

											</div>
										</div>
										<input type="hidden" name="room_firm_id"
											value="${firmVO.firm_id }"> <input type="hidden"
											name="action" value="roomType_view_by_search_date"> <input
											type="hidden" name="start_date" value="${start_date }">
										<input type="hidden" name="end_date" value="${end_date }">
										<input type="hidden" name="search_words"
											value="${firmVO.firm_name }"> <input type="hidden"
											name="login_member_id" value="${memberVO.member_id }">
									</form>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->

	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/owl.carousel.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/moment.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/roomtype/js/daterangepicker.js"></script>
	<script>
		if ($('#from, #to').length) {
			// check if element is available to bind ITS ONLY ON HOMEPAGE
			var currentDate = moment().format("YYYY-MM-DD");

			$('#from, #to').daterangepicker({
				locale : {
					format : 'YYYY-MM-DD'
				},

				"alwaysShowCalendars" : true,
				"minDate" : currentDate,
				autoApply : true,
				autoUpdateInput : false,

			}, function(start, end, label) {
				// console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
				// Lets update the fields manually this event fires on selection of range
				var selectedStartDate = start.format('YYYY-MM-DD'); // selected start
				var selectedEndDate = end.format('YYYY-MM-DD'); // selected end

				$checkinInput = $('#from');
				$checkoutInput = $('#to');

				// Updating Fields with selected dates
				$checkinInput.val(selectedStartDate);
				$checkoutInput.val(selectedEndDate);

				// Setting the Selection of dates on calender on CHECKOUT FIELD (To get this it must be binded by Ids not Calss)
				var checkOutPicker = $checkoutInput.data('daterangepicker');
				checkOutPicker.setStartDate(selectedStartDate);
				checkOutPicker.setEndDate(selectedEndDate);

				// Setting the Selection of dates on calender on CHECKIN FIELD (To get this it must be binded by Ids not Calss)
				var checkInPicker = $checkinInput.data('daterangepicker');
				checkInPicker.setStartDate(selectedStartDate);
				checkInPicker.setEndDate(selectedEndDate);

			});

		} // End Daterange Picker
	</script>
	<script>
		$(document).ready(function() {
			$(".firm_block").click(function() {
				$(this).parent("form").submit();
			})
		})
	</script>
	<script>
		$(document).ready(function() {
			$(".switch_btn_name").click(function() {
				$(".search_by_name.container").css({
					"display" : "none"
				})
				$(".search_by_add.container").css({
					"display" : "block"
				})
			});
			$(".switch_btn_add").click(function() {
				$(".search_by_name.container").css({
					"display" : "block"
				})
				$(".search_by_add.container").css({
					"display" : "none"
				})
			});
		});
	</script>
</body>

</html>