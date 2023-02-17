<%@page import="com.firm.model.*"%>
<%@page import="com.roomtype.model.*"%>
<%@page import="com.roomcollection.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>


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
	href="<%=request.getContextPath()%>/front_end/roomtype/css/collection.css"
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


.roomType_block:hover {
 box-shadow: 1px 3px 10px gray;
 border-radius:10px;
 transition:0.3s;
 transform:translate(0px,-5px);
 cursor: pointer;
}

.details_btn:hover {
	cursor: pointer;
}

.ri-text a {
	font-weight: 700;
	color: #272727;
}

.ri-text a:hover {
	color: #959da5;
}

.form-control:focus {
	color: #495057;
	background-color: #fff;
	border-color: #e8b15c;
	outline: 0;
	box-shadow: 0 0 0 0.2rem rgb(249 141 15/ 25%);
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
		<div class="search container"
			style="margin-top: 15px; margin-bottom: 15px; border-radius: 10px 10px 10px 10px; box-shadow: 0px 1px 3px #888888; position: sticky; top: 79px; background-color: white; z-index: 1;">
			<div class="search_body">
				<form method="post"
					action="<%=request.getContextPath()%>/front_end/roomtype/roomSearch.do">
					<div class="row"
						style="padding: 20px 0px; justify-content: center;">
						<div class="col-lg-4">
							<div class="location ">
								<input class="form-control" style="font-size: 14px;"
									name="search_words_name" value="${search_words_name }"
									placeholder="請輸入地區、飯店" autocomplete="off"
									${search_words_name!=null?'disabled':'' } required>
							</div>
						</div>
						<div class="col-lg-3 ">
							<div class="input-group">
								<input class="form-control" style="font-size: 14px;" id="from"
									name="start_date" value="${start_date }" placeholder="入住"
									autocomplete="off" required> <input
									class="form-control" style="font-size: 14px;" id="to"
									name="end_date" value="${end_date }" placeholder="退房"
									autocomplete="off" required>
							</div>
						</div>

						<div class="col-lg-2">
							<button class="date_search_btn" type="submit"
								style="width: 100%; height: 100%; border: 0px; background-color: #dfa974; border-radius: 10px; color: white; font-weight: 600;">搜尋此飯店</button>
							<input type="hidden" name="room_firm_id" value="${room_firm_id }">
							<input type="hidden" name="login_member_id"
								value="${memberVO.member_id }"> <input type="hidden"
								name="action" value="search_empty_room_byOneFirm">
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--Search End-->

		<!--RoomType -->
		<section class="roomType_body" style="margin: 30px 0px;">
			<div class="container">
				<div class="row">

					<c:if test="${roomSearchList!=null }">
						<c:forEach var="roomSearchVO" items="${roomSearchList }">
							<div class="col-lg-4 col-md-6" style="margin: 10px 0px;">
								<div class="roomType_item" style="margin-bottom: 30px;">
									<div class="roomType_block">

										<img
											src="<%=request.getContextPath() %>/ShowRoomImgServlet?room_img_id=${roomSearchVO.roomImgVO.room_img_id}"
											alt="" style="border-radius: 10px 10px 0px 0px;">

										<div class="ri-text"
											style="border: 1px solid #ebebeb; border-radius: 0px 0px 10px 10px; padding: 15px 15px 5px 15px;">
											<div
												style="display: flex; justify-content: space-between; align-items: center; justify-items: center;">
												<h4 style="font-weight: 600; color: #7a92a3;">${roomSearchVO.room_type_name }
													<span style="color: red; font-size: 10px;">剩${roomSearchVO.empty_room<=0?'0':roomSearchVO.empty_room }間</span>
												</h4>
												<c:set var="addString"
													value="${ roomSearchVO.firm_operate_add }" />
												<c:set var="add" value="${fn:substring(addString,0,6)}" />
												<p style="margin: 0px; font-size: 14px;">
													<i class="fa-solid fa-map-pin"></i><a
														href="<%=request.getContextPath()%>/front_end/roomtype/roomSearch.do?action=get_one_firm_add_by_start_date&firm_operate_add=${fn:substring(add,0,3)}&start_date=${start_date}&end_date=${end_date}">${fn:substring(add,0,3)}</a><a
														href="<%=request.getContextPath()%>/front_end/roomtype/roomSearch.do?action=get_one_firm_add_by_start_date&firm_operate_add=${fn:substring(add,3,6)}&start_date=${start_date}&end_date=${end_date}">${fn:substring(add,3,6)}</a>
												</p>
											</div>
											<div
												style="display: flex; justify-content: space-between; align-items: center; padding: 5px; margin-top: 10px">
												<form action="room.do" method="post">
													<div style="color: gray; font-size: 16px;"
														class="details_btn">房型詳情</div>
													<input type="hidden" name="room_type_id"
														value="${roomSearchVO.room_type_id }"> <input
														type="hidden" name="action"
														value="roomTypeDetails_by_searh_date"> <input
														type="hidden" name="login_member_id"
														value="${memberVO.member_id }"> <input
														type="hidden" name="start_date" value="${start_date }">
													<input type="hidden" name="end_date" value="${end_date }">
												</form>
												<p style="color: #dfa974; font-size: 20px; margin: 0px;">
													NT$${roomSearchVO.roomTypeData.room_type_price }<span
														style="color: gray; font-size: 12px;">/每晚</span>
												</p>

											</div>

										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<c:if test="${roomSearchList.size()==0 }">
				<div
					style="text-align: center; font-weight: 700; font-size: 22px; color: red;">查無該區間可提供預訂的房型</div>
			</c:if>
		</section>
		<!--RoomType End-->
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
		src="<%=request.getContextPath()%>/front_end/roomtype/js/collection.js"></script>
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
			$(".details_btn").click(function() {
				$(this).parent("form").submit();
			})
		})
	</script>
</body>

</html>