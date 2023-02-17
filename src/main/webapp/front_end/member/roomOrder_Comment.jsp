<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--要用for each之程式碼匯入-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.roomcomment.model.*"%>
<%@ page import="com.roomorderitem.model.*"%>

<!-- 抓memberVO  這段 -->
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
%>

<%
RoomCommentVO roomCommentVO = (RoomCommentVO) request.getAttribute("roomCommentVO");
%>
<!-- 抓memberVO  結束 -->
<%
// RoomOrderService roomOrderService = new RoomOrderService();
// List<RoomOrderVO> list = roomOrderService.getOrderByMember(memberVO.getMember_id());
// pageContext.setAttribute("list", list);
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

						<!-- 						左下邊攔位 -->
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
					<!--開始-->
					<div class="mc-table col-lg-9 co1-sm-12">
						<div class="col-lg-12 mc-box3">
							<div class="profile-info ">
								<div class="row">
									<div class="col-lg-3">
										<div class="title">住宿訂單評價</div>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-12">
										<nav class="sub-title">
											<div class="nav nav-tabs" id="nav-tab" role="tablist">

												<a class="nav-item nav-link active" id="nav-mc-order-r-tab"
													data-toggle="tab" href="#nav-mc-order-r" role="tab"
													aria-controls="navnav-mc-order-r" aria-selected="false">住宿管理</a>

											</div>
										</nav>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-12">
										<div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">

											<div class="tab-pane fade show active" id="nav-mc-order-r"
												role="tabpanel" aria-labelledby="nav-mc-order-r">
												<div class="tab-pane show active" id="rpt-1-1">
													<div class="row search">
														<div class="col-md-12">

															<br>

														</div>
													</div>


													<div>

														<!-- 評論開始 -->
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/front_end/member/roomComment.do"
															name="form1">
															<label for="comment">評論:</label>
															<div class="rows"></div>
															<div class="col-sm-10"></div>
															<textarea class="form-control" style="resize:none;" placeholder="Describe  here..." id="comment"
																name="room_comment_content" rows="5"
																style="width: 100%;"></textarea>
															<input type="hidden" name="room_comment_content">

															<fieldset class="row mb-3">
																<legend class="col-form-label col-sm-2 pt-0">請給評分</legend>
																<div class="col-sm-10">
																	<div class="form-check">
																		<input class="form-check-input" type="radio"
																			name="room_comment_star" id="gridRadios1" value="5"
																			checked> <label class="form-check-label"
																			for="gridRadios1"> 非常滿意 </label>
																	</div>
																	<div class="form-check">
																		<input class="form-check-input" type="radio"
																			name="room_comment_star" id="gridRadios2" value="4">
																		<label class="form-check-label" for="gridRadios2">
																			滿意 </label>
																	</div>
																	<div class="form-check disabled">
																		<input class="form-check-input" type="radio"
																			name="room_comment_star" id="gridRadios3" value="3">
																		<label class="form-check-label" for="gridRadios3">
																			一般般 </label>
																	</div>
																	<div class="form-check disabled">
																		<input class="form-check-input" type="radio"
																			name="room_comment_star" id="gridRadios4" value="2">
																		<label class="form-check-label" for="gridRadios4">
																			有點可惜 </label>
																	</div>
																	<div class="form-check disabled">
																		<input class="form-check-input" type="radio"
																			name="room_comment_star" id="gridRadios5" value="1">
																		<label class="form-check-label" for="gridRadios5">
																			不佳的體驗 </label>
																	</div>
																</div>

															</fieldset>
															<input type="hidden" name="room_type_id"
																value="${roomOrderItemVO.room_type_id}"> <input
																type="hidden" name="member_id"
																value="${memberVO.member_id}"> <input
																type="hidden" name="action" value="insert">
															<button type="submit" class="btn btn-primary">提交評論</button>
														</form>


														<!-- 評論結束 -->

														<hr>

														<form class="row g-3">
															<div class="col-md-4">
																<label for="inputEmail4" class="form-label">訂單編號</label>
																<input type="text" class="form-control" id="inputEmail4"
																	value="${roomOrderItemVO.room_order_id}" readonly>
															</div>
															<div class="col-md-4">
																<label for="inputPassword4" class="form-label">房型編號</label>
																<input type="text" class="form-control"
																	id="inputPassword4"
																	value="${roomOrderItemVO.room_type_id}" readonly>
															</div>


															<div class="col-md-4">
																<label for="inputAddress2" class="form-label">房間金額</label>
																<input type="text" class="form-control"
																	id="inputAddress2"
																	value="${roomOrderItemVO.room_type_price}" readonly>
															</div>




															<div class="col-md-4">
																<label for="inputCity" class="form-label">入住時間</label> <input
																	type="text" class="form-control" id="inputCity"
																	value="${roomOrderItemVO.room_order_checkin_date}"
																	readonly>
															</div>
															<div class="col-md-4">
																<label for="inputCity" class="form-label">退房時間</label> <input
																	type="text" class="form-control" id="inputCity"
																	value="${roomOrderItemVO.room_order_checkout_date}"
																	readonly>
															</div>

														</form>
													</div>
												</div>
											</div>

										</div>

										<!--結束-->
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
	<!-- Search model Begin -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch">
				<i class="icon_close"></i>
			</div>
			<form class="search-model-form">
				<input type="text" id="search-input" placeholder="Search here.....">
			</form>
		</div>
	</div>
	<!-- Search model end -->

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
		src="<%=request.getContextPath()%>/front_end/member/js/back2.js"></script>

	<script
		src="<%=request.getContextPath()%>/front_end/member/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/front_end/member/js/back2.js"></script>

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