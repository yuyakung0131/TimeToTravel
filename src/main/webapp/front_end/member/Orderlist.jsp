<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--要用for each之程式碼匯入-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.tktorder.model.*"%>
<%@ page import="com.ticketpromote.model.*"%>
<%@ page import="com.shoppinglist.model.*"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.itineraryorder.model.*"%>

<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
TktOrderService tktorderSvc = new TktOrderService();
List<TktOrder> tktOrderList = tktorderSvc.findByMemberId(memberVO.getMember_id());
pageContext.setAttribute("tktOrderList", tktOrderList);

RoomOrderService roomOrderService = new RoomOrderService();
List<RoomOrderVO> roomOrderList = roomOrderService.getOrderByMember(memberVO.getMember_id());
pageContext.setAttribute("roomOrderList", roomOrderList);

ItineraryOrderService itineraryOrderSvc = new ItineraryOrderService();
List<ItineraryOrderVO> itineraryOrderList = itineraryOrderSvc.getMember(memberVO.getMember_id());
pageContext.setAttribute("itineraryOrderList", itineraryOrderList);

Vector<ShoppingList> cart = (Vector<ShoppingList>) session.getAttribute("cart");
Integer total_amount = (Integer) session.getAttribute("total_amount");
pageContext.setAttribute("cart", cart);
%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="TimeToTarvel">
<meta name="keywords" content="TimeToTarvel,TibameG3,html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Member_Center</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/styleforheader_footerNEW.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/member_center.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/mc-itr-order.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/itr-bs.css"
	type="text/css">
<style>
.profile-info::-webkit-scrollbar {
	width: 7px;
}

.profile-info::-webkit-scrollbar-button {
	background: transparent;
	border-radius: 4px;
}

.profile-info::-webkit-scrollbar-track-piece {
	background: transparent;
}

.profile-info::-webkit-scrollbar-thumb {
	border-radius: 4px;
	background-color: rgba(0, 0, 0, 0.4);
	border: 1px solid slategrey;
}

.profile-info::-webkit-scrollbar-track {
	box-shadow: transparent;
}

.nav-tabs .nav-item.show .nav-link, .nav-tabs .nav-link.active {
    color: #222736;
    /* border-color: transparent transparent white; */
    border: 0;
    border-bottom: 4px solid #dfa974;
    font-size: 12px;
    font-weight: bolder;
    pointer-events: none;
}
</style>
</head>


<body>
	<!-- Page Preloder Begin -->
	<jsp:include page="../preLoder.jsp"></jsp:include>
	<!-- Page Preloder End -->
	<!-- Offcanvas Menu Section Begin -->
	<!-- offcanvasMenuSection_member -->
	<jsp:include page="../offcanvasMenuSection_member.jsp"></jsp:include>
	<!-- Offcanvas Menu Section End -->
	<div class="toolbarFixed">
		<!-- header Begin -->
		<!-- header_member -->
		<jsp:include page="../header_member.jsp"></jsp:include>
		<!-- Header End -->
		<!-- Member Center Start -->

		<div class="member-center">
			<div class="container">
				<div class="row no-gutters ">
					<div class="mc-slide col-lg-3">
						<div class="mc-box1 col-lg-12">
							<div class="profile">
								<div class="row">
									<div class="col-md-12">
										<form Method="post" action="member.do" id="formPic"
											enctype="multipart/form-data">
											<input type="hidden" name="member_id"
												value="${memberVO.member_id}"> <input type="hidden"
												name="member_email" value="${memberVO.member_email}">
											<input type="hidden" name="action" value="updateMemberImg">
											<img class="member-pic"
												src="${pageContext.request.contextPath}/front_end/member/member.do?action=getImg&member_id=${memberVO.member_id}"
												alt=""> <label for="file"> <i
												class="fa fa-camera" aria-hidden="true"></i> <input
												type="file" id="file" style="display: none" name="upfile"
												accept="image/gif,image/jpeg,image/jpg,image/png"
												data-original-title="upload photos">
											</label>
										</form>
										<input type="hidden" name="member_name"
											value="${memberVO.member_name}">
										<p class="member-name">${memberVO.member_name}</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div style="border-top: 1px solid rgb(211, 211, 211);"></div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-12">
										<img class="ttt" src="img/TTT.png" alt="">
									</div>
								</div>
								<div class="row">
									<div class="col-md-8">
										<span class="member-state">TimeToTarvel會員:</span> <span
											class="member-email">${memberVO.member_email}</span>
									</div>
									<div class="col-md-4">
										<i class="fa fa-check-circle-o"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="mc-box2 col-lg-12">
							<div class="profile-list">
								<ul class="list-unstyled">
									<li><a
										href="${pageContext.request.contextPath}/front_end/member/member.do?action=clickMemberCenter&member_email=${memberVO.member_email}"><i
											class="fa fa-solid fa-gear"></i>帳號設定</a></li>
									<li><a
										href="<%=request.getContextPath()%>/front_end/member/Orderlist.jsp"
										class="active "><i class="fa fa-file-text"></i>訂單管理</a></li>
									<li><a
										href="<%=request.getContextPath()%>/front_end/member/Favlist.jsp"><i
											class="fa fa-heart"></i>我的最愛</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="mc-table col-lg-9 co1-sm-12">
						<div class="col-lg-12 mc-box3">
							<div class="profile-info " style="overflow-y:scroll ;overflow-x:hidden ;">
								<div class="row">
									<div class="col-lg-3">
										<div class="title">訂單管理</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<nav class="sub-title">
											<div class="nav nav-tabs" id="nav-tab" role="tablist">
												<a class="nav-item nav-link active" id="nav-mc-order-t-tab"
													data-toggle="tab" href="#nav-mc-order-t" role="tab"
													aria-controls="nav-mc-order-t" aria-selected="true">票券管理</a>
												<a class="nav-item nav-link " id="nav-mc-order-r-tab"
													data-toggle="tab" href="#nav-mc-order-r" role="tab"
													aria-controls="nav-mc-order-r" aria-selected="false">住宿管理</a>
												<a class="nav-item nav-link " id="nav-mc-order-i-tab"
													data-toggle="tab" href="#nav-mc-order-i" role="tab"
													aria-controls="nav-mc-order-i" aria-selected="false">行程管理</a>
											</div>
										</nav>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">
											<!--票券訂單 start-->
											<div class="tab-pane fade show active" id="nav-mc-order-t"
												role="tabpanel" aria-labelledby="nav-mc-order-t"
											>
												<table table border=1 rules="rows" class="table "
													id="myTktTable" class="tablesorter">
													<thead>
														<tr>
															<th class="sortable"
																style="white-space: nowrap; font-size: 16px; font-weight: bold;">訂單編號</th>
															<th class="sortable"
																style="white-space: nowrap; font-size: 16px; font-weight: bold;">訂單日期</th>
															<th class="sortable"
																style="white-space: nowrap; font-size: 16px; font-weight: bold;">訂單狀態</th>
															<th class="sortable"
																style="white-space: nowrap; font-size: 16px; font-weight: bold;">優惠名稱</th>
															<th class="sortable"
																style="white-space: nowrap; font-size: 16px; font-weight: bold;">訂單總金額</th>
															<th class="sortable"
																style="white-space: nowrap; font-size: 16px; font-weight: bold;">折扣後總價格</th>
															<th class="sortable"
																style="white-space: nowrap; font-size: 16px; font-weight: bold;">訂單明細</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="tktorder" items="${tktOrderList}">
															<tr>
																<td>${tktorder.tkt_order_id}</td>
																<td><fmt:formatDate value="${tktorder.order_date}"
																		pattern="yyyy-MM-dd HH:mm:ss" /></td>
																<td>${(tktorder.tkt_order_state==0)?"成立":"取消"}</td>
																<td>${tktorder.getTicketPromote().prom_name}</td>
																<td>TWD ${tktorder.total}</td>
																<td>TWD ${tktorder.total_discount}</td>
																<td><div class="tktitem">
																		<a class="btn btn-outline-info btn-sm"
																			style="background-color: #1b3b40; color: white; white-space: nowrap;"
																			href="<%=request.getContextPath()%>/front_end/member/tktorder.do?action=getOneOrderID_For_MemberCenter&tkt_order_id=${tktorder.tkt_order_id}">訂單明細</a>
																	</div></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<!--票券訂單 end-->
											<!--住宿訂單 start-->
											<div class="tab-pane fade" id="nav-mc-order-r"
												role="tabpanel" aria-labelledby="nav-mc-order-r">
												<div class="row search">
													<div class="col-md-12">
														<br>
													</div>
												</div>
												<div>
<%-- 													<%@ include file="page1.file"%> --%>
													<table border=1 rules="rows" class="table ">
														<thead style="border: 1px;">
															<tr>
																<th>訂單編號</th>
																<th>訂單成立日期</th>
																<th>訂單狀態</th>
																<th>總金額</th>
																<th>詳細明細</th>
																<th><i class="bi bi-chat-dots"></i>訂單評論</th>
																<th>取消訂單</th>
															</tr>
														</thead>
														<tbody>
															<!--           跌代出list -->
															<c:forEach var="roomOrderVO" items="${roomOrderList}">
<%-- 																begin="<%=pageIndex%>" --%>
<%-- 																end="<%=pageIndex+rowsPerPage-1%>"> --%>
																<tr>
																	<td>${roomOrderVO.room_order_id}</td>
																	<td><fmt:formatDate
																			value="${roomOrderVO.room_order_date}"
																			pattern="yyyy-MM-dd .hh時mm分" /></td>
																	<c:if test="${roomOrderVO.room_order_state == 0}"
																		var="true">
																		<td class="myState"><font color=red>訂單成立</font></td>
																	</c:if>
																	<c:if test="${roomOrderVO.room_order_state == 1}"
																		var="true">
																		<td class="myState"><font color=brown>訂單取消</font></td>
																	</c:if>
																	<c:if test="${roomOrderVO.room_order_state == 2}"
																		var="true">
																		<td class="myState"><font color=green><b>訂單完成</b></font></td>
																	</c:if>
																	<td>${roomOrderVO.room_order_ttl_price}/NT$</td>
																	<td><FORM METHOD="post" ACTION="roomOrderItem.do"
																			style="margin-bottom: 0px;">
																			<!-- Button trigger modal -->
																			<input type="hidden" name="room_order_id"
																				value="${roomOrderVO.room_order_id}"> <input
																				type="hidden" name="action"
																				value="getOne_Front_For_Display">
																			<button type="submit" class="btn btn-primary">詳細</button>
																		</FORM></td>
																	<td>
																		<FORM METHOD="post" ACTION="roomOrderItem.do"
																			style="margin-bottom: 0px;">
																			<!-- Button trigger modal -->
																			<input type="hidden" name="room_order_id"
																				value="${roomOrderVO.room_order_id}"> <input
																				type="hidden" name="action"
																				value="getOne_Front_For_Comment">
																			<button style="display: none;" type="submit"
																				class="btn btn-primary hiddenComment">
																				<i class="bi bi-chat-dots"></i>評論
																			</button>

																		</FORM>

																	</td>

																	<td>
																		<FORM METHOD="post" ACTION="roomOrder.do"
																			style="margin-bottom: 0px;">
																			<input type="hidden" name="room_order_id"
																				value="${roomOrderVO.room_order_id}"> <input
																				type="hidden" name="action" value="Update_state">
																			<button style="display: block;" type="submit"
																				class="btn btn-primary hiddenCancer">
																				<i class="bi bi-heartbreak"></i>取消
																			</button>
																		</FORM>


																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
<%-- 													<%@ include file="page2.file"%> --%>
												</div>
											</div>
											<!--住宿訂單 end-->
											<!--行程訂單 start-->
											<div class="tab-pane fade" id="nav-mc-order-i"
												role="tabpanel" aria-labelledby="nav-mc-order-i">
												<c:forEach var="itineraryOrderVO"
													items="${itineraryOrderList}">
													<div class="card mb-3" style="max-width: auto;">
														<div id="row" class="row g-0">
															<div id="col" class="col-md-4">
																<img style="height: 220px; width: 300px;"
																	src="<%=request.getContextPath()%>/ItineraryImgReader.do?itinerary_type_id=${itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_type_id}&offset=0">
															</div>
															<div id="col" class="col-md-8">
																<div class="card-body">
																	<a
																		href="<%=request.getContextPath()%>/front_end/itinerarytype/do?itinerary_type_id=${itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_type_id}&action=showDetail">
																		<h4 class="card-title">${itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_title}</h4>

																		<c:set var="avgstar"
																			value="${itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_total_score / itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_total_people}">
																		</c:set> <c:if
																			test="${itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_total_score > 0}">
																			<i class="icon_star" style="color: gold"></i>
																			<span style="color: black"><fmt:formatNumber
																					type="number" value="${avgstar}"
																					maxFractionDigits="1" /> </span>
																		</c:if> <c:if
																			test="${itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_total_score == 0}">
																			<i class="icon_star" style="color: gold"></i>
																			<span style="color: black">0 (0)</span>
																		</c:if>






																	</a>
																	<p class="inf"
																		style="max-width: 200px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_info}</p>
																	<p class="p">下訂時間:${itineraryOrderVO.itinerary_order_time}</p>
																	<p class="p">人數:${itineraryOrderVO.itinerary_people_num}人</p>
																	<p class="p">行程開始日:${itineraryOrderVO.itineraryVO.itinerary_start}</p>
																	<p class="p">會員姓名:${memberVO.member_name}</p>
																	<div class="row">
																		<div class="col-lg-7">
																			<p class="p">訂單狀態:${itineraryOrderVO.itinerary_order_state  == 0 ? "訂單成立" : "已取消"}</p>
																		</div>
																		<div id="btn" class="col-lg-5 d-flex">

																			<p id="pMemId" style="display: none">${memberVO.member_id}</p>
																			<!-- 																			評論 -->
																			<c:if
																				test="${itineraryOrderVO.itinerary_order_state  == 0}">
																				<p id="pTypeId" style="display: none">${itineraryOrderVO.itineraryVO.itineraryTypeVO.itinerary_type_id}</p>
																				<a href="" class="btn btn-info itrCommentBtn"
																					style="background-color: #dfa974; border: 0px; font-weight: 600"
																					type="button" data-toggle="modal"
																					data-target="#itrConmmentModal">點擊評論</a>
																			</c:if>



																			<!-- 																			評論 -->
																			<form method="post"
																				action="<%=request.getContextPath()%>/front_end/member/ItineraryOrder.do"
																				class="d-flex">
																				<input type="hidden" name="itinerary_order_id"
																					value="${itineraryOrderVO.itinerary_order_id}" />
																				<input type="hidden" name="action"
																					value="updateItrOrderMember" /> <input type="hidden"
																					name="itinerary_id"
																					value="${itineraryOrderVO.itinerary_id} " /> <input
																					type="hidden" name="itinerary_people_num"
																					value="${itineraryOrderVO.itinerary_people_num} " />
																				<c:if
																					test="${itineraryOrderVO.itinerary_order_state  == 1}">
																					<button type="submit" class="btn btn-light"
																						disabled="disabled">取消訂單</button>
																				</c:if>
																				<c:if
																					test="${itineraryOrderVO.itinerary_order_state  == 0}">
																					<button type="submit" class="btn btn-light">取消訂單</button>
																				</c:if>
																			</form>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
											<!--行程訂單 end-->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Member Center End -->
		<!-- 彈窗評論 開始 -->
		<div class="modal fade" id="itrConmmentModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- header -->
					<div class="modal-header">
						<h3>
							行程評論<i style="font-size: 22px; margin-left: 5px;"
								class="fa fa-pencil" aria-hidden="true"></i>
						</h3>
						<!-- <button type="button" class="btn-close" data-bs-dismiss="modal"></button> -->
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<!-- body -->
					<div class="modal-body">
						<div class="container">
							<div class="row text-center">
								<div style="height: 45px; width: 150px;" class="col-md-12">
									<h5
										style="width: 436px; margin-top: 10px; font-size: 26px; color: #002B41; font-weight: 700;">為這次的行程打個分數吧</h5>
								</div>
								<FORM
									action="<%=request.getContextPath()%>/front_end/member/itinerarycomment.do"
									style="width: 466px">
									<div class="form-group">
										<!-- 									取得行程種類ID -->
										<input type="hidden" id="itrTypeIdComment"
											name="itinerary_type_id" class="form-control" value="">
										<!-- 									取得會員ID -->
										<input type="hidden" id="itrMemIdComment" name="member_id"
											class="form-control" value=""> <input type="hidden"
											id="search-type" name="itinerary_comment_star"
											class="form-control" value="1">
										<ul class="rating" id="selType"
											style="width: 436px; float: left; left: 25%; position: relative;">
											<li class="rating-item active" id="1" value="1" data-rate="1"
												style="border-style: none"></li>
											<li class="rating-item" id="2" value="2" data-rate="2"
												style="border-style: none"></li>
											<li class="rating-item" id="3" value="3" data-rate="3"
												style="border-style: none"></li>
											<li class="rating-item" id="4" value="4" data-rate="4"
												style="border-style: none"></li>
											<li class="rating-item" id="5" value="5" data-rate="5"
												style="border-style: none"></li>
										</ul>
									</div>
									<!-- 第一格 -->
									<div class="form-group" style="width: 460px;">
										<!-- <label for="itr-com">請輸入評論內容</label> -->
										<textarea name="itinerary_comment_post"
											style="height: 180px; resize: none; width: 100%;"
											placeholder="請輸入評論內容" class="form-control" id="itr-com"
											rows="3"></textarea>
									</div>
									<input type="hidden" name="action" value="addItrComment">
									<!-- 送出按鈕 -->
									<button
										style="width: 436px; background-color: #002B41; border: 0px; font-size: 23px"
										type="submit" class="btn btn-info">提交</button>
								</FORM>
							</div>
						</div>
					</div>
					<!-- footer -->
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
		<!-- 彈窗評論 結束 -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->

	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/owl.carousel.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/front_end/member/js/main.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/front_end/member/js/meberCenter.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/front_end/member/js/homeMember.js"></script>
	<!-- 	tablesorter -->
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/css/theme.ice.min.css"></link>
	<script type="text/javascript"
		src='//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js'>
		
	</script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/js/jquery.tablesorter.min.js">
		
	</script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/itr-bs.js"></script>
	<script>
		$(function() {
			$("#myTktTable").tablesorter({
				theme : "ice",
				sortList : [ [ 0, 0 ], [ 1, 0 ] ]
			});
		});
		var state = document.querySelectorAll('.myState');
		const hiddenComment = document.querySelectorAll('.hiddenComment');
		for (let i = 0; i < state.length; i++) {
			if (state[i].innerText === '訂單完成') {
				hiddenComment[i].style.display = 'block';
			} else if (state[i].innerText === '訂單成立') {
				hiddenComment[i].style.display = 'none';
			} else {
				hiddenComment[i].style.display = 'none';
			}
		}

		var state = document.querySelectorAll('.myState');
		const hiddenCancer = document.querySelectorAll('.hiddenCancer');
		for (let i = 0; i < state.length; i++) {
			if (state[i].innerText === '訂單成立') {
				hiddenCancer[i].style.display = 'block';
			} else if (state[i].innerText === '訂單完成') {
				hiddenCancer[i].style.display = 'none';
			} else {
				hiddenCancer[i].style.display = 'none';
			}
		}
	</script>

</body>

</html>