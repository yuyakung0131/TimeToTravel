<%@page import="com.itinerary.model.ItineraryService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itinerarytype.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="com.itinerarycollection.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%

MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");

if (memberVO != null){


ItineraryTypeVO itineraryTypeVO = (ItineraryTypeVO)request.getAttribute("itineraryTypeVO");	

ItineraryCollectionService itineraryCollectionSvc = new ItineraryCollectionService();
ItineraryCollectionVO itineraryCollectionVO = itineraryCollectionSvc.getOneItineraryCollection(memberVO.getMember_id(), itineraryTypeVO.getItinerary_type_id());
pageContext.setAttribute("itineraryCollectionVO", itineraryCollectionVO);}
%>

<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="TimeToTarvel">
<meta name="keywords" content="TimeToTarvel,TibameG3,html">
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
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/itr-bs.css"
	type="text/css">

<!-- wang新增 -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/itr-bs.css"
	type="text/css" />

<style>
.fc-day {
	border-radius: 10px;
}

.fc-event {
	cursor: pointer;
}

.fc td, .fc th {
	/*   border-style: none !important; */
	
}

.fc-day:hover {
	background: rgb(237, 239, 240);
}

.fc-day-today {
	background-color: inherit !important;
}

.container-calendar {
	width: 500px;
	height: 500px;
	border: 1px solid rgb(190, 190, 190);
	border-radius: 3px;
	margin-bottom: 2px;
}

#calendar {
	background-color: white;
	/* border: none; */
}

#fc-dom-1 {
	font-size: 25px;
	margin-left: 180px;
}

.fc .fc-daygrid-day-number {
	font-size: 12px;
}

th {
	font-size: 12px;
}

.fc-event {
	border: none !important;
	/*   background-color: inherit !important;  */
	background-color: #D3A974 !important;
	font-size: 25px;
	font-weight: 700;
}

.fc .fc-highlight {
	background: #D3A974 !important;
	border-radius: 10px;
	color: #ddd
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
		<!-- 行程明細頁開始 -->
		<!-- 路徑 -->
		<div class="path">
			<P>
				<a class="wanga"
					href="<%=request.getContextPath() %>/front_end/itinerarytype/itinerary_list.jsp">
					行程</a> > <a class="wanga"
					href="<%=request.getContextPath()%>/front_end/itinerarytype/do?itinerary_type_id=${itineraryTypeVO.itinerary_type_id}&action=showDetail">
					${itineraryTypeVO.itinerary_title} </a>
			</P>
		</div>
		<!-- 封面圖+簡介 -->
		<div class="container">
			<div class="row text-center">


				<div class="col-md-12">
					<h1 style="color: #222736;font-size:50px; font-weight: bold; margin: 20px;">${itineraryTypeVO.itinerary_title}</h1>

					<form method="post"
						action="<%=request.getContextPath() %>/front_end/itinerarytype/collection.do">
						<button id="heart-btn" type="button" onclick="changeHeart()">
							<c:if test="${itineraryCollectionVO==null}">
								<i style="font-size: 30px; color: gary;" id="heart"
									class="fa fa-heart" aria-hidden="true"></i>
							</c:if>
							<c:if test="${itineraryCollectionVO!=null}">
								<i style="font-size: 30px; color: red;" id="heart"
									class="fa fa-heart" aria-hidden="true"></i>
							</c:if>
						</button>
						<input type="hidden" name="member_id"
							value="${memberVO.member_id }" id="mid"> <input
							type="hidden" name="itinerary_type_id"
							value="${itineraryTypeVO.itinerary_type_id }" id="itr"> <input
							type="hidden" name="action"
							value="insert_delete_itineraryCollection">
					</form>


				</div>

				<div class="row text-center">


					<div class="col-md-6">
						<div id="itrCarousel" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
				  <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <c:forEach  begin="2" end="${(imgList).size()-1}" step="1" varStatus="s">
    <li data-target="#carouselExampleIndicators" data-slide-to="${s.count+1}"></li>
     </c:forEach>
							</ol>
							<div class="carousel-inner">

								<c:if test="${(imgList).size() >= 2}">
									<div class="carousel-item active">
										<img class="d-block w-100" style="width: 100%;height: 80%;object-fit: cover;"
											src="<%=request.getContextPath()%>/ItineraryImgReader.do?itinerary_type_id=${itineraryTypeVO.itinerary_type_id}&offset=1">
									</div>

								</c:if>
								<!-- 				除錯 -->
								<c:if test="${(imgList).size() > 2}">

									<c:forEach begin="2" end="${(imgList).size()-1}" step="1"
										varStatus="s">
										<div class="carousel-item">

											<img class="d-block w-100" style="width: 100%;height: 80%;object-fit: cover;"
												src="<%=request.getContextPath()%>/ItineraryImgReader.do?itinerary_type_id=${itineraryTypeVO.itinerary_type_id}&offset=${s.count+1}">
										</div>
									</c:forEach>

								</c:if>



							</div>
							<a class="carousel-control-prev" href="#itrCarousel"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#itrCarousel"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>

					</div>

					<div class="col-md-6">
						<!--                   隱藏type_id -->
						<p id="pTypeId2" style="display: none">${itineraryTypeVO.itinerary_type_id}</p>

						<h4 style="font-weight: 700; color: white">
							<span
								style="background-color: #DF9750; padding: 5px 10px; border-radius: 6px;">行程簡介</span>
						</h4>
						<p
							style="word-wrap: break-word; background-color: #F3F3F3; padding: 8px 16px; border-radius: 6px; margin-top: 20px; margin-bottom: 20px">${itineraryTypeVO.itinerary_info}</p>
						<h4 style="font-weight: 700; color: white">
							<span
								style="background-color: #DF9750; padding: 5px 10px; border-radius: 6px;">其他說明</span>
						</h4>
						<p
							style="word-wrap: break-word; background-color: #F3F3F3; padding: 8px 16px; border-radius: 6px; margin-top: 20px">${itineraryTypeVO.itinerary_other}</p>
					</div>
				</div>
			</div>
		</div>
		<!-- 封面圖+簡介end -->
		<!-- 行程 -->

		<div class="container">
			<div class="row itrtitle shadow p-3 mb-5 bg-white rounded">
				<div class="col-md-6 text-left" style="height: 500px;">

					<h4 style="margin-top: 20px;">
						行程標題：<span>${itineraryTypeVO.itinerary_title}</span>
					</h4>
					<h4 style="margin-top: 20px;">
						行程開始日：<span id="itrstart">-</span>
					</h4>
					<h4 style="margin-top: 20px;">
						行程結束日：<span id="itrend">-</span>
					</h4>
					<h4 style="margin-top: 20px;">
						最少成團人數：<span id="itrmin">${itineraryTypeVO.itinerary_min}</span>
					</h4>
					<h4 style="margin-top: 20px;">
						額滿人數：<span id="itrmax">${itineraryTypeVO.itinerary_max}</span>
					</h4>
					<h4 style="margin-top: 20px;">
						已報名人數：<span id="itrnowp">-</span>
					</h4>
					<h4 style="margin-top: 20px;">
						報名日期：<span id="itrestart">-</span> ~ <span id="itreend">-</span>
					</h4>
					<!--             <h4 style="margin-top: 20px;">狀態：<span id="itrstat"> -->
					<!--             請選擇日期 -->
					<!--             </span> </h4> -->
					<h4 style="margin-top: 20px;">
						價格$ <span id="itrprice"
							style="font-size: 32px; font-weight: 600; color: rgba(239, 44, 73, 0.984)">${itineraryTypeVO.itinerary_price}</span>
						/起
					</h4>
					<span style="color: #8e92a0; margin-right: 50px">金額為單人價格，請點選報名前往結帳頁面確認總金額</span>

					<a id="itrida" href="#">

						<button type="button" class="btn btn-warning"
							style="color: rgb(251, 251, 251); font-weight: 800; background-color: #DF9750;">立即報名</button>
					</a>
				</div>
				<!-- 日曆 -->

				<div class="col-md-6">
					選擇日期

					<div class="container-calendar">

						<div id='calendar'></div>
					</div>
				</div>
			</div>
		</div>
		<!-- 行程end -->
		<!-- 評論 -->

		<section id="itr-comment">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<h3 style="font-weight: 900; margin-left: 60px">行程評論</h3>
						<div class="row">
							<div class="col-md-2 offset-md-1 total-star">
								<p>
									<!-- 								奇怪的計算方法 -->
									<c:if test="${(itrCommentList).size() > 0}">
										<c:set var="avgStar" value="0">
										</c:set>

										<c:forEach var="itineraryCommentVO" items="${itrCommentList}"
											begin="0" end="${(itrCommentList).size()}" step="1">

											<c:set var="avgStar"
												value="${avgStar+itineraryCommentVO.itinerary_comment_star}">

											</c:set>

										</c:forEach>
										<fmt:formatNumber type="number"
											value="${avgStar/(itrCommentList).size()}"
											maxFractionDigits="1" />

									</c:if>
									<c:if test="${empty itrCommentList}">
									0
									</c:if>
								</p>
							</div>
							<div class="col-md-9">

								<c:forEach begin="1" end="${avgStar/(itrCommentList).size()}"
									step="1">
									<div class="star2">
										<span class="star star-under fa fa-star"></span> <span
											class="star star-over fa fa-star"></span>
									</div>
								</c:forEach>

								<input type="hidden"
									value="${avgStar/(itrCommentList).size() % 1}" id="starMod" />

								<%-- 								<c:if test="${avgStar/(itrCommentList).size() % 1 != 0}"> --%>
								<c:if test="${avgStar/(itrCommentList).size() % 1 > 0}">
									<div class="star5">
										<span class="star star-under fa fa-star"></span> <span
											class="star star-over fa fa-star"></span>
									</div>
								</c:if>

								<c:if test="${empty avgStar}">
									<c:forEach begin="1" end="4" step="1">
										<div class="star5">
											<span class="star star-under fa fa-star"></span> <span
												class="star star-over fa fa-star"></span>
										</div>
									</c:forEach>
								</c:if>
								<c:if test="${5 - avgStar/(itrCommentList).size() >= 1}">
									<c:forEach begin="1"
										end="${5 - avgStar/(itrCommentList).size()}" step="1">

										<div class="star3">
											<span class="star star-under fa fa-star"></span> <span
												class="star star-over fa fa-star"></span>
										</div>
									</c:forEach>
								</c:if>
								<p>
									<!-- 								奇怪的計算人數方法 -->
									<c:if test="${(itrCommentList).size() >= 0}">
									
										${(itrCommentList).size()}
								</c:if>
									則評論
								</p>
							</div>
						</div>

						<hr>
						<%-- 					<jsp:useBean id="memberSvc" scope="page" --%>
						<%-- 					class="com.member.model.MemberService" /> --%>

						<!-- 單個評論 -->

						<c:forEach var="itineraryCommentVO" items="${itrCommentList}"
							varStatus="s">
							<div class="row">
								<c:if
									test="${not empty itineraryCommentVO.itinerary_comment_post}">
									<div class="col-md-4 text-center">
										<!-- 會員照片+id -->
										<img src="${pageContext.request.contextPath}/front_end/member/member.do?action=getImg&member_id=${itineraryCommentVO.memberVO.member_id}" alt="">
										<h5>${itineraryCommentVO.memberVO.member_name}</h5>
										<%-- 									<h5>${itineraryCommentVO.member_id}</h5> --%>
									</div>
								</c:if>
								<div class="col-md-8">

									<c:forEach begin="1"
										end="${itineraryCommentVO.itinerary_comment_star}" step="1">
										<c:if
											test="${not empty itineraryCommentVO.itinerary_comment_post}">
											<div class="star4">
												<span class="star star-under fa fa-star"></span> <span
													class="star star-over fa fa-star"></span>
											</div>
										</c:if>
									</c:forEach>
									<c:if
										test="${5 - itineraryCommentVO.itinerary_comment_star >= 1}">
										<c:forEach begin="1"
											end="${5 - itineraryCommentVO.itinerary_comment_star}"
											step="1">
											<c:if
												test="${not empty itineraryCommentVO.itinerary_comment_post}">
												<div class="star41">
													<span class="star star-under fa fa-star"></span> <span
														class="star star-over fa fa-star"></span>
												</div>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if
										test="${not empty itineraryCommentVO.itinerary_comment_post}">
										<span style="color: #505050; margin-left: 10px"> <fmt:formatDate
												value="${itineraryCommentVO.itinerary_comment_time}"
												pattern="yyyy-MM-dd" />
										</span>

										<p
											style="word-wrap: break-word; background-color: #F3F3F3; padding: 8px 16px; border-radius: 6px">${itineraryCommentVO.itinerary_comment_post}</p>
									</c:if>



								</div>
							</div>
							<c:if
								test="${not empty itineraryCommentVO.itinerary_comment_post}">
								<hr>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</section>

		<!-- 評論end -->

		<!-- 行程明細頁結束 -->
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
	<!-- wang新增 -->
	<script>const path = '<%=request.getContextPath()%>'; </script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/index.global.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/itr-bs.js"></script>
	<script>
	
			var eventList = []
		$(document).ready(function() {
			let itinerary_type_id = $("#pTypeId2").text();
		    $.get("<%=request.getContextPath()%>/itinerarytype/ItineraryTypeServletForAjax.do?action=itrCalendar",
												{
													'itinerary_type_id' : itinerary_type_id
												},
												function(data) {
													
													var calendarEl = document.getElementById('calendar');
													var calendar = new FullCalendar.Calendar(calendarEl, {
														initialView : 'dayGridMonth',
														locale : 'zh-tw',
	
														navlinks : true,
														headerToolbar : {
														// left:'prev, next day',
														// center:'title',
														// right:'dayGridMonth,timeGridWeek,timeGridDay'
														},
														height : 500,
														events : eventList,
														
														eventClick : function(info) {
															for (var j = 0; j < data.length; j++) {
															if (info.event.id == data[j].itinerary_start){
	// 														alert(info.event.id);
										
											
															$("#itrstart").text(data[j].itinerary_start);
															$("#itrend").text(data[j].itinerary_end);
															$("#itrmin").text(data[j].itinerary_min);
															$("#itrmax").text(data[j].itinerary_max);
															$("#itrnowp").text(data[j].itinerary_now);
															$("#itrestart").text(data[j].entered_start);
															$("#itreend").text(data[j].entered_end);
// 															$("#itrstat").text(data[j].itinerary_state);
															$("#itrprice").text(data[j].itinerary_price);
															$("#itrida").attr("href", "<%=request.getContextPath()%>/front_end/itinerarytype/order.do?itinerary_id="+data[j].itinerary_id+"&action=orderDetail")
															break;
															}
														
															}
														},
	
	// 													selectable : true,
														eventConstraint:{
														      startTime: '00:00', 
														      endTime: '24:00', 
														    },
													});
													calendar.render();
													for (var i = 0; i < data.length; i++) {
													calendar.addEvent({
														id : data[i].itinerary_start,
														title :"$  "+ data[i].itinerary_price,
														start : data[i].itinerary_start,
												        textColor: "#FFFFFF",
													});	}
	
												}, "json");
							});
	
	
		</script>

	<!-- wang新增結束 -->
</body>

</html>