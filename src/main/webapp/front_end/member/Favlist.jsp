<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--要用for each之程式碼匯入-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.firm.model.*"%>
<%@ page import="com.tktlist.model.*"%>
<%@page import="com.roomcollection.model.*"%>
<%@ page import="com.itinerarycollection.model.*"%>
<%@ page import="com.itinerarytype.model.*"%>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
TktListService tktListSvc = new TktListService();
List<TktList> tktListFav = tktListSvc.findByMemberID(memberVO.getMember_id());
pageContext.setAttribute("tktListFav", tktListFav);
RoomCollectionService roomCollectionSvc = new RoomCollectionService();
List<RoomCollectionVO> roomCollectionList = roomCollectionSvc.getCollectionByMember(memberVO.getMember_id());
pageContext.setAttribute("roomCollectionList", roomCollectionList);
ItineraryCollectionService itineraryCollectionSvc = new ItineraryCollectionService();
List<ItineraryCollectionVO> itineraryCollectionList = itineraryCollectionSvc.getCollectionListByMember(memberVO.getMember_id());
pageContext.setAttribute("itineraryCollectionList", itineraryCollectionList);
%>
<!DOCTYPE html>
<html lang="zxx">
<head>

<meta charset="UTF-8">
<meta name="description" content="TimeToTarvel">
<meta name="keywords" content="TimeToTarvel,TibameG3,html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>

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
	href="<%=request.getContextPath()%>/front_end/member/css/home_main.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/roomtype/css/collection.css"
	type="text/css">
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/front_end/member/css/tktfavorite.css" --%>
<!-- 	type="text/css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/mc-itr-favorite.css"
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
										href="<%=request.getContextPath()%>/front_end/member/Orderlist.jsp"><i
											class="fa fa-file-text"></i>訂單管理</a></li>
									<li><a
										href="<%=request.getContextPath()%>/front_end/member/Favlist.jsp"
										class="active "><i class="fa fa-heart"></i>我的最愛</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="mc-table col-lg-9 co1-sm-12">
						<div class="col-lg-12 mc-box3">
							<div class="profile-info " style="overflow-y:scroll ;overflow-x:hidden ;">
								<div class="row">
									<div class="col-lg-3">
										<div class="title">我的最愛</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<nav class="sub-title">
											<div class="nav nav-tabs" id="nav-tab" role="tablist">
												<a class="nav-item nav-link active"
													id="nav-mc-favorite-t-tab" data-toggle="tab"
													href="#nav-mc-favorite-t" role="tab"
													aria-controls="nav-mc-favorite-t" aria-selected="true">票券</a>
												<a class="nav-item nav-link" id="nav-mc-favorite-r-tab"
													data-toggle="tab" href="#nav-mc-favorite-r" role="tab"
													aria-controls="nav-mc-favorite-r" aria-selected="false">住宿</a>
												<a class="nav-item nav-link" id="nav-mc-favorite-i-tab"
													data-toggle="tab" href="#nav-mc-favorite-i" role="tab"
													aria-controls="nav-mc-favorite-i" aria-selected="false">行程</a>
											</div>
										</nav>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">
											<!--票券收藏 start-->
											<div class="tab-pane fade show active" id="nav-mc-favorite-t"
												role="tabpanel" aria-labelledby="nav-mc-favorite-t"
											>
												<jsp:useBean id="tktimgSvc2" scope="session"
													class="com.tktimg.model.TktImgService" />
												<c:forEach var="tkt" items="${tktListFav}">
													<div class="order"
														style="display: flex; padding: 15px 10px; box-shadow: 0px 1px 0px #bfbfbf; width: 666px; margin-left: 0px;">

														<div class="col-lg-3 col-md-3 col-sm-3 col-3">
															<div>
																<img class="tktorderpic"
																	src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimgSvc2.findByTktId(tkt.getTicket().getTkt_id()).get(0).tkt_img_id}"
																	alt="">
															</div>

														</div>

														<div class="col-lg-8 col-md-8 col-sm-5 col-5">
															<div class="tktorderid_date">
																<span class="tktorfavdate"><fmt:formatDate
																		value="${tkt.ticket_fav_date}" pattern="yyyy-MM-dd" /></span>
															</div>
															<div class="tktproductname">${tkt.ticket.tkt_name}</div>
															<div class="tktfirm">${tkt.ticket.firm.firm_name }</div>
															<div class="favleftbottom">
																<div class="tktprice" style="text-align: right;">TWD
																	${tkt.ticket.tkt_price}</div>
																<div class="btn-group"
																	style="display: flex; justify-content: space-between;">
																	<div class="tktdetail">
																		<a class="btn btn-outline-info btn-sm" style=""
																			href="<%=request.getContextPath()%>/front_end/ticket/ticket.do?action=getTicketDetailed_For_Display&tkt_id=${tkt.tkt_id }">商品詳情</a>
																	</div>
																	<div class="tktremovefav">
																		<form method="post" action="list.do"
																			style="margin-top: 0px;">
																			<input class="btn btn-outline-secondary btn-sm"
																				type="submit" value="移除我的最愛"> <input
																				type="hidden" name="tkt_id" value="${tkt.tkt_id}">
																			<input type="hidden" name="action" value="delete">
																		</form>
																	</div>
																</div>
															</div>
														</div>
													</div>

												</c:forEach>
											</div>
											<!--票券收藏 end-->
											<!--住宿收藏 start-->
											<div class="tab-pane fade" id="nav-mc-favorite-r"
												role="tabpanel" aria-labelledby="nav-mc-favorite-r">
												<c:forEach var="roomCollectionListVO"
													items="${roomCollectionList }">
													<div style="display: flex; padding: 10px 0px;">
														<div class="col-lg-4 col-md-4">
														<img style="border-radius: 15px;"
                src="<%=request.getContextPath() %>/ShowRoomImgServlet?room_img_id=${roomCollectionListVO.roomType_id_byRoomType.roomImgVO.room_img_id}">
														</div>
														<div class="col-lg-8 col-md-8"
															style="padding-top: 5px; border-bottom: 1px solid #c6baba;">
															<div style="display: flex;">
																<div class="" style="margin-right: 20px;">
																	<p>${roomCollectionListVO.roomType_id_byRoomType.room_type_name }</p>
																</div>
																<div class="">
																	<p>$${roomCollectionListVO.roomType_id_byRoomType.room_type_price
																		}TWD/晚</p>
																</div>
															</div>
															<div class="" style="">
																<p style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">${roomCollectionListVO.roomType_id_byRoomType.room_type_content }</p>
															</div>
															<div
																style="display: flex; justify-content: space-between;">
																<div style="display: flex;">
																	<form method="get"
																		action="<%=request.getContextPath()%>/front_end/roomtype/room.do">
																		<input type="submit" value="房間詳情"
																			style="border: 2px solid #adadad; background-color: #dfa974; color: white; margin-right: 10px; padding: 5px 10px; border-radius: 3rem; font-size: 12px;">
																		<input type="hidden" name="action"
																			value="roomTypeDetails"> <input type="hidden"
																			name="room_type_id"
																			value="${roomCollectionListVO.room_type_id }">
																		<input type="hidden" name="login_member_id"
																			value="${memberVO.member_id }">
																	</form>
																	<form method="post" class="collectionForm"
																		action="<%=request.getContextPath()%>/front_end/member/roomCollection.do">
																		<button type="submit"
																			class="${memberVO.member_id==null?'doAjaxForCollection button button-like':'doAjaxForCollection button button-like liked' }"
																			style="font-size: 10px">
																			<i class="fa fa-heart"></i> <span
																				class="collection_btn">${memberVO.member_id==null?'收藏':'已收藏' }</span>
																		</button>
																		<input type="hidden" name="login_member_id"
																			value="${memberVO.member_id }"> <input
																			type="hidden" name="room_type_id"
																			value="${roomCollectionListVO.roomType_id_byRoomType.room_type_id }">
																		<input type="hidden" name="action"
																			value="delete_roomCollection">
																	</form>
																</div>
																<p style="font-size: 14px;">
																	收藏時間
																	<fmt:formatDate
																		value="${roomCollectionListVO.fav_date }"
																		pattern="yyyy/MM/dd" />
																</p>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
											<!--住宿收藏 end-->
											<!--行程收藏 start-->
											<div class="tab-pane fade" id="nav-mc-favorite-i"
												role="tabpanel" aria-labelledby="nav-mc-favorite-i">
												<c:forEach var="itineraryCollectionVO"
													items="${itineraryCollectionList}">
													<div class="card mb-3"
														style="max-width: auto; margin: 0 20px;">
														<div id="row" class="row g-0">
															<div id="col" class="col-md-4">
																<img style="height: 220px; width: 300px;"
																	src="<%=request.getContextPath()%>/ItineraryImgReader.do?itinerary_type_id=${itineraryCollectionVO.itineraryTypeVO.itinerary_type_id}&offset=0">
															</div>
															<div id="col" class="col-md-8">
																<a
																	href="<%=request.getContextPath()%>/front_end/itinerarytype/do?itinerary_type_id=${itineraryCollectionVO.itineraryTypeVO.itinerary_type_id}&action=showDetail">
																	<div class="card-body">
																		<div class="fave-list">
																			<h4 class="card-title">${itineraryCollectionVO.itineraryTypeVO.itinerary_title}</h4>
																			<form method="post"
																				action="<%=request.getContextPath() %>/front_end/member/collection.do">
																				<button id="heart-btn" type="submit">
																					<i style="color: red;" id="heart"
																						class="fa fa-heart" aria-hidden="true"></i>
																				</button>
																				<input type="hidden" name="member_id"
																					value="${memberVO.member_id }"> <input
																					type="hidden" name="itinerary_type_id"
																					value="${itineraryCollectionVO.itineraryTypeVO.itinerary_type_id }">
																				<input type="hidden" name="action"
																					value="itrFavoritedelete">
																			</form>
																		</div>
																		<p
																			style="max-width: 200px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${itineraryCollectionVO.itineraryTypeVO.itinerary_info}</p>
																		<c:set var="avgstar"
																			value="${itineraryCollectionVO.itineraryTypeVO.itinerary_total_score / itineraryCollectionVO.itineraryTypeVO.itinerary_total_people}">
																		</c:set>
																		<div class="row">
																			<div id="star" class="col-lg-9">
																				<c:if
																					test="${itineraryCollectionVO.itineraryTypeVO.itinerary_total_score > 0}">
																					<i class="icon_star" style="color: gold"></i>
																					<span style="color: black"><fmt:formatNumber
																							type="number" value="${avgstar}"
																							maxFractionDigits="1" /> </span>
																				</c:if>
																				<c:if
																					test="${itineraryCollectionVO.itineraryTypeVO.itinerary_total_score == 0}">
																					<i class="icon_star" style="color: gold"></i>
																					<span style="color: black">0 (0)</span>
																				</c:if>


																			</div>
																			<div id="price" class="col-lg-3">
																				<p class="price">TWD
																					${itineraryCollectionVO.itineraryTypeVO.itinerary_price}</p>
																			</div>
																		</div>
																	</div>
																</a>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
											<!--行程收藏 end-->
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

</body>

</html>
