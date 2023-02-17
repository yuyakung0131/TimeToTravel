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
<%

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
									<li><a href="${pageContext.request.contextPath}/front_end/member/member.do?action=clickMemberCenter&member_email=${memberVO.member_email}"><i
											class="fa fa-solid fa-gear"></i>帳號設定</a></li>
									<li><a href="<%=request.getContextPath()%>/front_end/member/Orderlist.jsp" class="active "><i
											class="fa fa-file-text"></i>訂單管理</a></li>
									<li><a href="<%=request.getContextPath()%>/front_end/member/Favlist.jsp"><i
											class="fa fa-heart"></i>我的最愛</a></li>
								</ul>
								<form Method="post"
									action="<%=request.getContextPath()%>/front_end/member/member.do"
									id="memberCenterForm">
									<input type="hidden" name="member_email"
										value="${memberVO.member_email}"> <input type="hidden"
										name="action" value="clickMemberCenter">
								</form>
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
												<a class="nav-item nav-link active" id="nav-ticket-info-tab"
													data-toggle="tab" href="#nav-ticket-info" role="tab"
													aria-controls="nav-ticket-info" aria-selected="true">票券管理</a>
												<a class="nav-item nav-link " id="nav-room-info-tab"
													data-toggle="tab" href="#nav-room-info" role="tab"
													aria-controls="nav-room-info" aria-selected="false">住宿管理</a>
												<a class="nav-item nav-link " id="nav-itinerary-info-tab"
													data-toggle="tab" href="#nav-itinerary-info" role="tab"
													aria-controls="nav-itinerary-info" aria-selected="false">行程管理</a>
											</div>
										</nav>
									</div>
								</div>
								<!--票券訂單明細 start-->
								<div class="tab-pane fade show active ticket-info"
									id="nav-ticket-info" role="tabpanel"
									aria-labelledby="nav-ticket-info"
									style="overflow: auto; height: 500px">
									<div>
								<input type="button" onclick="history.go(-1)"
									class="btn btn-secondary btn-sm" value="回到上一頁"></input>
							</div>
							<br>
								<table table border=1 rules="rows" class="table " id="myTktTable"
									class="tablesorter">
									<thead>

										<tr>

											<th class="sortable" style="white-space: nowrap;font-size:16px;font-weight:bold;">票券名稱</th>
											<th class="sortable" style="white-space: nowrap;font-size:16px;font-weight:bold;">票券數量</th>
											<th class="sortable" style="white-space: nowrap;font-size:16px;font-weight:bold;">票券價格</th>
											<th class="sortable" style="white-space: nowrap;font-size:16px;font-weight:bold;">票券截止日期</th>
											<th class="sortable" style="white-space: nowrap;font-size:16px;font-weight:bold;">訂單日期</th>
										</tr>
									</thead>
									<tbody>
											<c:forEach var="tktOrderListItem" items="${tktOrderListItem}">
												<tr>
													<td>${tktOrderListItem.ticket.tkt_name}</td>
													<td>${tktOrderListItem.amount}</td>
													<td>${tktOrderListItem.tkt_price}</td>
													<td><fmt:parseDate
															value="${ tktOrderListItem.tkt_deadline }"
															pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
															type="both" /> <fmt:formatDate
															pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime }" /></td>
													<td><fmt:formatDate
															value="${tktOrderListItem.getTktOrder().order_date}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													</FORM>
													</td>
													
												</tr>
											</c:forEach>
										</tbody>
								</table>
							
								</div>
								<!--票券明細 end-->
								<!--住宿訂單 start-->
								<div class="tab-pane fade room-info" id="nav-room-info"
									role="tabpanel" aria-labelledby="nav-room-info"
									style="overflow: auto; height: 500px"></div>
								<!--住宿訂單 end-->
								<!--行程訂單 start-->
								<div class="tab-pane fade itinerary-info"
									id="nav-itinerary-info" role="tabpanel"
									aria-labelledby="nav-itinerary-info"
									style="overflow: auto; height: 500px"></div>
								<!--行程訂單 end-->
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
	<script>
		$(function() {
			$("#myTktTable").tablesorter({
				theme : "ice",
				sortList : [ [ 0, 0 ], [ 1, 0 ] ]
			});
		});
	</script>


</body>

</html>