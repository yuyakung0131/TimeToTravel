<%@page import="com.firm.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="com.roomtype.model.*"%>
<%@page import="com.roomcollection.model.*"%>
<%@page import="com.roomcomment.model.*"%>
<%@page import="com.roomimg.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%
RoomTypeVO roomTypeVO = (RoomTypeVO) request.getAttribute("roomTypeVO");
RoomImgService roomImgSvc = new RoomImgService();
List<RoomImgVO> roomImgList = roomImgSvc.getImgByRoomType(roomTypeVO.getRoom_type_id());
pageContext.setAttribute("roomImgList", roomImgList);
RoomCommentService roomCommentService = new RoomCommentService();
List<RoomCommentVO> roomCommentList = roomCommentService.getCommentByRoomType(roomTypeVO.getRoom_type_id());
pageContext.setAttribute("roomCommentList", roomCommentList);
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
	href="<%=request.getContextPath()%>/front_end/roomtype/css/collection.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/comment_star.css"
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
		<!--RoomType Details-->
		<section class="room-details-section spad"
			style="padding-bottom: 15px">
			<div class="col-lg-12"
				style="font-size: 22px; color: gray; text-align: center; padding-top: 10px;">
				${firm_name }</div>
			<div class="room_img container">
				<div class="row" style="justify-content: center; padding: 30px 0px;">
					<div class="col-lg-6">
						<c:if test="${roomImgList.size()!=0 }">
							<!--carousel section Begin -->
							<div id="carouselExampleIndicators" class="carousel slide"
								data-ride="carousel">
								<ol class="carousel-indicators">
									<li data-target="#carouselExampleIndicators" data-slide-to="0"
										class="active"></li>
									<c:set var="count" scope="page" value="1" />
									<c:forEach var="roomImgListVO" items="${roomImgList }"
										begin="1" end="${roomImgList.size()-1 }">
										<li data-target="#carouselExampleIndicators"
											data-slide-to="${count }"></li>
										<c:set var="count" scope="page" value="${count+1 }" />
									</c:forEach>
								</ol>
								<div class="carousel-inner room" style="margin:0px;display:flex;height:350px;">
									<c:forEach var="roomImgListVO" items="${roomImgList }"
										begin="0" end="0">
										<div class="carousel-item active" data-bs-interval="100">
											<img class="d-block w-100"
												style="height: 300px; border-radius: 10px"
												src="<%=request.getContextPath() %>/ShowRoomImgServlet?room_img_id=${roomImgListVO.room_img_id}">
										</div>
									</c:forEach>
									<c:forEach var="roomImgListVO" items="${roomImgList }"
										begin="1" end="${roomImgList.size()-1 }">
										<div class="carousel-item " data-bs-interval="100">
											<img class="d-block w-100"
												style="height: 300px; border-radius: 10px"
												src="<%=request.getContextPath() %>/ShowRoomImgServlet?room_img_id=${roomImgListVO.room_img_id}">
										</div>
									</c:forEach>
								</div>
								<a class="carousel-control-prev"
									href="#carouselExampleIndicators" role="button"
									data-slide="prev"> <span class="carousel-control-prev-icon"
									aria-hidden="true"></span> <span class="sr-only">Previous</span>
								</a> <a class="carousel-control-next"
									href="#carouselExampleIndicators" role="button"
									data-slide="next"> <span class="carousel-control-next-icon"
									aria-hidden="true"></span> <span class="sr-only">Next</span>
								</a>
							</div>
							<!--carousel section End  -->
						</c:if>
					</div>
				</div>
			</div>
			<div class="room_details container">
				<div class="row">
					<div class="col-lg-8">
						<div style="display: flex; align-items: center;">
							<div class="col-lg-9"
								style="font-size: 36px; color: gray; padding: 15px 0px; display: flex; align-items: center; justify-content: space-between;">

								${roomTypeVO.room_type_name }
								<div style="display: flex;">
									<div class=""
										style="font-size: 30px; border: 2px solid #e4cfcf; border-radius: 15px; padding: 0px 15px; color: #ac8f47;">
										<c:if test="${(roomCommentList).size() > 0}">
											<c:set var="avgStar" value="0">
											</c:set>
											<c:forEach var="roomCommentVO" items="${roomCommentList}"
												begin="0" end="${(roomCommentList).size()}" step="1">
												<c:set var="avgStar"
													value="${avgStar+roomCommentVO.room_comment_star}">
												</c:set>
											</c:forEach>
											<fmt:formatNumber type="number"
												value="${avgStar/(roomCommentList).size()}"
												maxFractionDigits="1" />
										</c:if>
										<c:if test="${empty roomCommentList}">
									0
									</c:if>
									</div>
									<div class="rating "
										style="font-size: 20px; display: flex; align-items: center;">


										<c:forEach begin="1" end="${avgStar/(roomCommentList).size()}"
											step="1">
											<div class="star2" style="display: flex;">
												<span class="star star-under fa fa-star"></span> <span
													class="star star-over fa fa-star"></span>
											</div>
										</c:forEach>
										<input type="hidden"
											value="${avgStar/(roomCommentList).size() % 1}" id="starMod" />
										<c:if test="${avgStar/(roomCommentList).size() % 1 > 0}">
											<div class="star5" style="display: flex;">
												<span class="star star-under fa fa-star"></span> <span
													class="star star-over fa fa-star"></span>
											</div>
										</c:if>
										<c:if test="${empty avgStar}">
											<c:forEach begin="1" end="4" step="1">
												<div class="star5" style="display: flex;">
													<span class="star star-under fa fa-star"></span> <span
														class="star star-over fa fa-star"></span>
												</div>
											</c:forEach>
										</c:if>
										<c:if test="${5 - avgStar/(roomCommentList).size() >= 1}">
											<c:forEach begin="1"
												end="${5 - avgStar/(roomCommentList).size()}" step="1">
												<div class="star3" style="display: flex;">
													<span class="star star-under fa fa-star"></span> <span
														class="star star-over fa fa-star"></span>
												</div>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</div>
							<!--收藏 開始 -->
							<div class="col-lg-3" style="text-align: right;">
								<form method="post" id="collectionForm"
									action="<%=request.getContextPath()%>/front_end/roomtype/roomCollection.do">
									<button type="submit"
										class="${roomCollectionVO==null?'doAjaxForCollection button button-like':'doAjaxForCollection button button-like liked' }"
										style="font-size: 16px; padding: 10px 15px;">
										<i class="fa fa-heart"></i> <span class="collection_btn">${roomCollectionVO==null?'收藏':'已收藏' }</span>
									</button>
									<input type="hidden" name="login_member_id"
										value="${memberVO.member_id }">
									<c:if test="${roomSearchVO.room_type_id==null }">
										<input type="hidden" name="room_type_id"
											value="${roomTypeVO.room_type_id }">
									</c:if>
									<c:if test="${roomTypeVO.room_type_id==null }">
										<input type="hidden" name="room_type_id"
											value="${roomSearchVO.room_type_id }">
									</c:if>
									<input type="hidden" name="action"
										value="insert_delete_roomCollection">
										<input  type="hidden" value="${memberVO.member_id }" id="member_id">
								</form>
							</div>
						</div>
						<!--收藏 結束 -->
						<!-- 房型說明 開始 -->

						<p style="font-size: 16px;">${roomTypeVO.room_type_content }</p>

						<!-- 房型說明 結束 -->

						<p style="margin: 5px 0px 0px 0px;">
							<c:if test="${(roomCommentList).size() >= 0}">
										${(roomCommentList).size()}
								</c:if>
							則評論
						</p>
						<div class="col-lg-12"
							style="border: 1px solid gray; margin: 5px 0px;"></div>
						<c:forEach var="roomCommentVO" items="${roomCommentList}">
							<div class="comment_block"
								style="display: flex; margin: 30px 0px;">
								<div class="col-lg-2 col-md-2 col-sm-2 col-2">
									<img
										src="<%=request.getContextPath()%>/front_end/member/member.do?action=getImg&member_id=${roomCommentVO.member_id}"
										style="border-radius: 100%;">
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 clo-8">
									<div class="" style="display: flex;">
										<div style="display: flex; align-items: center;">
											<c:forEach begin="1" end="${roomCommentVO.room_comment_star}"
												step="1">
												<div class="star4"
													style="display: flex; align-items: center;">
													<span class="star star-under fa fa-star"></span> <span
														class="star star-over fa fa-star"></span>
												</div>
											</c:forEach>
										</div>
										<div class="comment_time" style="margin-left: 10px;">
											<p style="margin: 0px; font-size: 14px;">
												<fmt:formatDate value="${roomCommentVO.room_comment_time}"
													pattern="yyyy-MM-dd" />
											</p>
										</div>
									</div>
									<p
										style="white-space: pre-wrap; white-space: pre-wrap; word-break: break-all;">${roomCommentVO.room_comment_content}</p>
								</div>

							</div>
						</c:forEach>

					</div>

					<div class="col-lg-4 col-md-6 col-sm-8 col-8"
						style="box-shadow: 0px 5px 15px grey; border-radius: 15px; position: sticky; top: 100px; padding: 15px; height: 370px;">
						<div
							style="display: flex; justify-content: center; margin-bottom: 10px">
							<div style="font-size: 20px; color: grey;">
								$${roomTypeVO.room_type_price }TWD<span
									style="font-size: 12px; color: black;">/每晚</span>
							</div>
						</div>

						<div style="padding: 0px 20px">
							<form
								action="<%=request.getContextPath()%>/front_end/roomtype/reservation.do"
								method="post" id="dateForm">
								<div style="display: flex; justify-content: center;">
									<div class="input-group">
										<input class="form-control" style="font-size: 14px;" id="from"
											name="start_date" placeholder="入住" autocomplete="off"
											value="${start_date }" required> <input
											class="form-control" style="font-size: 14px;" id="to"
											name="end_date" placeholder="退房" autocomplete="off"
											value="${end_date }" required>
									</div>
									<input type="hidden" name="login_member_id"
										value="${memberVO.member_id }"> <input type="hidden"
										name="room_type_price" value="${roomTypeVO.room_type_price }">
									<input type="hidden" name="room_type_id"
										value="${roomTypeVO.room_type_id }"> <input
										type="hidden" name="checkin_max" value="${checkin_max}">
									<input type="hidden" name="action" value="dates">

								</div>

								<div class="dropdown_amount"
									style="display: flex; justify-content: space-between; align-items: center;">

									<div class="form-outline room_res">
										<label class="form-label" for="roomNumber"
											style="margin: 0px;">房數</label> <input min="1"
											max="${empty_room }" type="number" id="roomNumber"
											style="width: 70px;" class="form-control"
											name="reservation_amount" ${empty_room<=0?'disabled':'' }
											required />
									</div>

									<div style="text-align: center;">
										<div class="err_reservation"
											style="font-size: 10px; color: red;"></div>
										<div class="empty_room"
											style="font-size:10px; ${empty_room<=0?'color:red;':'color:#3e74a3;'}  text-align:center;margin-top:5px;">剩${empty_room<=0?'0':empty_room}間
											${empty_room<=0?'無法預訂':'還可預訂' }</div>
									</div>

									<div class="form-outline checkin_res">
										<label class="form-label" for="checkinNumber"
											style="margin: 0px;">人數</label> <input min="1"
											max="${checkin_max }" type="number" style="width: 70px;"
											id="checkinNumber" class="form-control" name="checkin_amount"
											${empty_room<=0?'disabled':'' } required />
									</div>

								</div>
							</form>
							<form
								action="<%=request.getContextPath()%>/front_end/roomtype/reservation.do"
								method="get" id="reservationForm">
								<div class="reservation_btn_ajax"
									style="display: flex; justify-content: center;">

									<button class="reservation_btn"
										style="width: 100%; padding: 10px; margin: 5px 0px 15px 0px;${empty_room<=0?'background-color: gray;':'background-color:#dfa974;'}  color: white; font-size: 20px; border: 0px; border-radius: 10px;"
										${roomSearchVO.empty_room<=0?'disabled ':'' }>預訂</button>
								</div>
								<div style="display: flex; justify-content: space-between;">
									<div class="total_dates">$${roomTypeVO.room_type_price
										}TWDX${dates }晚</div>
									<div class="total_dates_price">$${roomTypeVO.room_type_price*dates
										}TWD</div>
								</div>
								<div class="total_rooms"
									style="text-align: right; padding: 5px 0px;"></div>
								<div class="total_price"
									style="text-align: right; padding: 5px 0px; color: #9a8a62; font-size: 20px;"></div>
								<input type="hidden" name="room_type_id"
									value="${roomTypeVO.room_type_id }"> <input
									type="hidden" name="room_type_amount"
									value="${roomTypeVO.room_type_amount }"> <input
									type="hidden" name="action" value="reservation_data">
								<div class="start_time">
									<input type="hidden" name="start_date" value="${start_date }">
								</div>
								<div class="end_time">
									<input type="hidden" name="end_date" value="${end_date }">
								</div>
								<div class="reservation_amount_ajax">
									<input type="hidden" name="reservation_amount"
										value="${reservation_amount }">
								</div>
								<div class="checkin_amount_ajax">
									<input type="hidden" name="checkin_amount"
										value="${checkin_amount }">
								</div>
								<input type="hidden" name="login_member_id"
									value="${memberVO.member_id }">
							</form>
						</div>

					</div>
				</div>
			</div>
		</section>
		<!--RoomType Details End-->
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
	$(document).ready(function () {
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
				
					$("#dateForm").on("submit",function(e){
		        		e.preventDefault();
		        	})
		            $(".daterangepicker.ltr.auto-apply.show-calendar.opensright").first().hide(function () {
		                $.ajax({
		                    type: "post",
		                    url: "reservation.do",
		                    data: $("#dateForm").serialize(),
		                    dataType: "Json"
		                }).then(function(res){	
		                	console.log(res);
		                	$(".total_dates").html("<div>$"+${roomTypeVO.room_type_price }+"TWD X"+res.dates +"晚</div>");
			        		$(".total_dates_price").html("<div>$"+res.dates_price+"TWD</div>");
			        	
			        		$(".empty_room").html("<div style=\""+(res.empty_room<=0||res.empty_room==null?"color:red;":"color:#3e74a3;")+"\">剩"+(res.empty_room<=0||res.empty_room==null?'0':res.empty_room)+"間 "+(res.empty_room<=0||res.empty_room==null?"無法預訂":"還可預訂")+"</div>");
			        		$(".reservation_btn_ajax").html((res.empty_room<=0||res.empty_room==null?"<button style=\"width: 100%; padding: 10px; margin: 5px 0px 15px 0px; background-color: gray; color: white; font-size: 20px; border: 0px; border-radius: 10px;\" disabled>"+"預訂</button>":"<button style=\"width: 100%; padding: 10px; margin: 5px 0px 15px 0px; background-color: #dfa974; color: white; font-size: 20px; border: 0px; border-radius: 10px;\">"+"預訂</button>"));
			        		$(".form-outline.room_res input").attr((res.empty_room<=0||res.empty_room==null?{'disabled':true,"value":""}:{"value":"","max":res.empty_room,"disabled":false}));
			        		$(".form-outline.checkin_res input").attr((res.empty_room<=0||res.empty_room==null?{'disabled':true,"value":""}:{"value":"","max":res.empty_room,"disabled":false}));
			        		$(".total_rooms").html((res.reservation_amount==null||res.reservation_amount==""?'':"<div>x"+res.reservation_amount+"間</div>"));
			            	
			        		if(res.room_sale_price==null){
			        		$(".total_price").html((res.total_price==null||res.total_price==""?"":"<div>共 $"+res.total_price+"TWD</div>"));
			        			
			            	}else{
			            		$(".total_price").html((res.total_price==null||res.total_price==""?"":"<div style=\"text-decoration:line-through;color:#c3bfb6;\">原價$"+res.total_price+"TWD</div>"+"<div>優惠 $"+res.room_sale_price+"TWD</div>"));
			            	}
			        		
			        		
		                	$(".start_time").html("<input type=\"hidden\" name=\"start_date\" value=\""+res.start_date+"\">");
		                	$(".end_time").html("<input type=\"hidden\" name=\"end_date\" value=\""+res.end_date+"\">");
		                	$(".total_price_data").html("<input type=\"hidden\" name=\"total_price_data\" value=\""+res.total_price+"\">");
		                	$("#checkinNumber").attr("disabled",true);
		                	$(".reservation_btn_ajax button").attr("disabled",true);
		                }, function(err) {
			        		if(err.status == 404) {
			        			console.log("重整中");
			        		} else {
			        			console.log("計算中");
			        		}
			        	});
		            });
					
			});
		} // End Daterange Picker
	});
	</script>
	<script>
	
	$("#dateForm").on("submit",function(e){
		e.preventDefault();
	})
    $("#roomNumber").on("click",function () {
        $.ajax({
            type: "post",
            url: "reservation.do",
            data: $("#dateForm").serialize(),
            dataType: "Json"
        }).then(function(res){
        	console.log(res);
        	$(".reservation_amount_ajax").html("<input type=\"hidden\" name=\"reservation_amount\" value=\""+res.reservation_amount+"\">");
        	$(".total_rooms").html("<div>x"+res.reservation_amount+"間</div>");
        	if(res.room_sale_price==null||res.room_sale_price==''||res.room_sale_price.length==0){
            	$(".total_price").html("<div>共 $"+res.total_price+"TWD</div>");
        	}else{
        	$(".total_price").html("<div style=\"text-decoration:line-through;color:#c3bfb6;\">原價$"+res.total_price+"TWD</div>"+"<div>優惠 $"+res.room_sale_price+"TWD</div>");
        	}
        	$(".form-outline.checkin_res input").attr({"max":res.checkin_amount_max});
        	$("#checkinNumber").attr("disabled",false);
        	$(".reservation_btn_ajax button").attr("disabled",true);
        }, function(err) {
    		if(err.status == 404) {
    			console.log("重整中");
    		} else {
    			console.log("計算中");
    		}
    	});
    });
	</script>
	<script>
	
	$("#dateForm").on("submit",function(e){
		e.preventDefault();
	})
    $("#checkinNumber").on("click",function () {
        $.ajax({
            type: "post",
            url: "reservation.do",
            data: $("#dateForm").serialize(),
            dataType: "Json"
        }).then(function(res){
        	console.log(res);
        	$(".checkin_amount_ajax").html("<input type=\"hidden\" name=\"checkin_amount\" value=\""+res.checkin_amount+"\">");
        	$(".reservation_btn").attr("disabled",false);
        	$(".reservation_btn_ajax button").attr("disabled",false);
        }, function(err) {
    		if(err.status == 404) {
    			console.log("重整中");
    		} else {
    			console.log("計算中");
    		}
    	});
    });
	</script>
	<script>
	$(".form-control.form-control-sm.room_res").blur(function(){
		if($(".form-control.form-control-sm.room_res").val()==null||$(".current").val()=="房數"){
			$(".err_reservation").html("請輸入房數");
			$(".err_reservation").attr("disabled",true);
		}else if($(".form-control.form-control-sm.room_res").val()!=null){
			$(".err_reservation").html("");
		}
	});
	$(".form-control.form-control-sm.checkin_res").blur(function(){
		if($(".form-control.form-control-sm.checkin_res").val()==null){
			$(".err_reservation").html("請輸入人數");
			$(".err_reservation").attr("disabled",true);
		}else if($(".form-control.form-control-sm.checkin_res").val()!=null){
			$(".err_reservation").html("");
		}
	});

	</script>
	<script>
	$(document).ready(function(){
		$('.reservation_btn').click(function(){
			$('.reservationForm').submit();
		});
		$(".reservation_btn").attr("disabled",true);
		$("#checkinNumber").attr("disabled",true);
	});
	</script>

</body>

</html>