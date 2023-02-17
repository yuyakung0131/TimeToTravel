<%@page import="com.firm.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorderitem.model.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.promotionitem.model.*"%>
<!-- 抓memberVO  這段 -->
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
RoomOrderService roomOrderService = new RoomOrderService();
List<RoomOrderVO> list = roomOrderService.getOrderByMember(memberVO.getMember_id());
pageContext.setAttribute("list", list);
%>
<!-- 抓memberVO  結束 -->

<%
RoomOrderItemVO roomOrderItemVO = (RoomOrderItemVO) request.getAttribute("roomOrderItemVO");
%>
<%
RoomOrderVO roomOrderVO = (RoomOrderVO) request.getAttribute("roomOrderVO");
%>
<%
RoomOrderService roomOrderService2 = new RoomOrderService();
List<RoomOrderVO> roomOrderList = roomOrderService2.getVeryNew();
pageContext.setAttribute("roomOrderList", roomOrderList);
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
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"></script>
<style>
#row {
	margin: 40px 60px 40px 60px;
}

.t2 {
	padding-top: 20px;
	border-top: 1px solid #e6e6e6;
}

#con {
	background-color: #f5f5f5;
	padding: 20px;
	border-radius: 7px;
	margin-bottom: 40px;
}

textarea {
	resize: none;
	width: 100%;
}

.btn-primary {
	margin-top: 3px;
	padding: 15px;
	font-size: 16px;
	width: 100%;
	border-radius: 3px;
	border: 1px solid #dcdcdc;
}

.submit-btn {
	width: 100%;
	background: #DFA974;
	margin-top: 20px;
	padding: 10px;
	font-size: 20px;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	transition: .2s linear;
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
		<!-- 訂單開始 -->
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/front_end/roomtype/roomOrder.do"
			name="form1" id="roomform1">
			<div id="row" class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-8">
					<header>
						<h4 style="font-weight: bold;">確認訂單資訊</h4>
					</header>
					<div id="con" class="con">
						<div class="row">
							<div class="col-lg-6">
								<label>會員<span class="text-danger">*</span></label><br> <input
									id="memberID" type="hidden" name="member_id"
									value="${memberVO.member_id}"> <input type="TEXT"
									value="${memberVO.member_name}" readonly disabled />

							</div>
							<div class="col-lg-6">
								<label>總金額<span class="text-danger">*</span></label><br> <input
									id="price" type="TEXT" name="room_order_ttl_price"
									value="${room_sale_price}" disabled />
							</div>
						</div>

						<div>
							<br> <br>
							<h4>注意事項:</h4>
							<br>
							<p>1.如付款完成訂房己確認，而要取消訂房者，請以書面或電子郵件等方式通知客服人員取消訂房，恕不接受住宿旅客、訂房連絡人以外之第三人以電話取消訂房，以確保您的權益。


							
							<p>2.確認住宿並付完款後，若旅客因個人因素未能於指定日期入住原預訂飯店，視同當日取消訂房，將收取全程總訂房費用。
							<p>3.取消訂房之相關費用因日期及飯店而有不同之規則，敬請參照住宿券所示之取消費說明。
							<p>4.如訂房為事先保證住房或全程保證住房(展覽、慶典、農曆春節..等等)之訂房時，則不接受退費及更改要求，取消將收取全額訂房費用。


							
							<p>5.任何更改或取消訂房請通知本公司客服人員，由服務人員為您處理，週末假日等非上班期間，請撥打飯店電話做變更，並需取得飯店人員姓名以便後續處理。


							
							<p>6.旅客於縮短住宿日期或其他變動，務必取得飯店同意並開立不予收費之憑證，以為事後辦理退費之證明。貴客戶需於住宿完畢三日內提出申請，惟本公司仍須酌收每晚、每房NT＄500元手續費，無檢附證明文件或逾期恕無法受理。


							
							<p>8.旅客於續住如未取得本公司之同意時，請自行於當地結清應付費用。
							<p>★ Time To Travel 旅遊官網 關心您 ★
						</div>

					</div>
				</div>
				<div class="col-lg-2"></div>
			</div>

			<div id="row" class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-8">

					<input type="hidden" name="action" value="insertOrder">
					<button type="button" class="btn btn-primary" id="roombutton1"
						data-bs-toggle="modal" data-bs-target="#staticBackdrop"
						style="width: 100%; background: #DFA974; margin: auto; padding: 10px; font-size: 20px; color: #fff; border-radius: 10px; cursor: pointer; transition: .2s linear">
						下一步確認明細</button>
				</div>
				<div class="col-lg-2"></div>
			</div>
		</FORM>
		<!-- 訂單結束 -->
		<!-- 訂單明細開始-->
		<!-- 彈窗開始	 -->
		<!-- Modal -->
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/front_end/roomtype/roomOrderItem.do"
			name="form1" id="roomform2">
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-xl">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">Time To
								Travel 旅遊官網</h5>
						</div>
						<div class="modal-body">
							<header>
								<h4 style="font-weight: bold;">訂房資訊</h4>
							</header>
							<div id="con" class="con">
								<div class="row">
									<div class="col-lg-6">
										<c:forEach var="roomOrderVO" items="${roomOrderList}">

											<label>訂單編號<span class="text-danger">*</span></label>
											<br>
											<input type="hidden" name="room_order_id" id="room_order_id"
												value="${roomOrderVO.room_order_id+1}" />
											<input type="TEXT" readonly disabled
												value="${roomOrderVO.room_order_id+1}">

										</c:forEach>
									</div>
									<div class="col-lg-6">
										<label>房型<span class="text-danger">*</span></label><br> <input
											type="TEXT" name="room_type_name" id="room_type_name"
											value="${roomTypeVO.room_type_name}" disabled>
									</div>

								</div>
								<div class="row">
									<div class="col-lg-6">
										<label>房間數<span class="text-danger">*</span></label><br>
										<input type="text" name="room_amount" id="room_amount"
											value="${reservation_amount}" disabled>
									</div>
									<div class="col-lg-6">
										<label>每晚房價<span class="text-danger">*</span></label><br>
										<input type="text" name="room_type_price" id="room_type_price"
											value="${roomTypeVO.room_type_price}" disabled>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<label>入住日期<span class="text-danger">*</span></label><br>
										<input type="text" name="room_order_checkin_date"
											id="2022-12-24" value="${start_date }" disabled>
									</div>
									<div class="col-lg-6">
										<label>總價<span class="text-danger">*</span></label><br> <input
											type="text" name="total_price" value="${total_price }"
											disabled>

									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<label>退房日期<span class="text-danger">*</span></label><br>
										<input type="text" name="room_order_checkout_date"
											id="2022-12-24" value="${end_date }" disabled>
									</div>
									<div class="col-lg-6">
										<label>優惠後總價<span class="text-danger">*</span></label><br>
										<input type="text" name="room_sale_price"
											value="${room_sale_price }" disabled>
									</div>
								</div>
							</div>
							<div class="t2">
								<header>
									<h4 style="font-weight: bold;">訂房資料</h4>
								</header>
							</div>
							<div id="con" class="con">


								<div></div>
								<div class="row">
									<div class="col-lg-12" style="">
										<label>入住人數<span class="text-danger">*</span></label><br>
										<div class="form-group">
											<input type="text" name="checkin_amount"
												value="${checkin_amount }" disabled>
										</div>
										<label>入住天數<span class="text-danger">*</span></label><br>
										<input type="text" name="dates" value="${dates }" disabled>
									</div>

									<div class="col-lg-12">
										<h5>入住名單填寫:</h5>

										<div id="form" class="form-group">
											<textarea class="form-control" id="Text"
												name="room_guest_name" id="room_guest_name"></textarea>
										</div>
									</div>
								</div>

							</div>
							<div>
								<header>
									<h4 style="font-weight: bold;">特殊需求備註</h4>
								</header>
							</div>
							<div id="con" class="con">
								<div class="row">
									<div class="col-lg-12">
										<div id="form" class="form-group">
											<textarea class="form-control" id="special_req"
												name="special_req" rows="7"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<input type="hidden" name="room_type_id"
								value="${roomTypeVO.room_type_id }"> <input
								type="hidden" name="room_type_amount"
								value="${roomTypeVO.room_type_amount }"> <input
								type="hidden" name="reservation_amount"
								value="${reservation_amount }"> <input type="hidden"
								name="room_type_price" value="${roomTypeVO.room_type_price }">
							<input type="hidden" name="room_sale_price"
								value="${room_sale_price }"> <input type="hidden"
								name="checkin_amount" value="${checkin_amount}"> <input
								type="hidden" name="room_order_checkin_date"
								value="${start_date}"> <input type="hidden"
								name="room_order_checkout_date" value="${end_date}"> <input
								type="hidden" name="action" value="insertOrderItem">
							<button id="btn" class="submit-btn">確認訂單</button>
						</div>
					</div>
				</div>
			</div>
		</FORM>
		<!-- 送出彈窗結束 -->
		<!-- 訂單明細結束-->

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
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>

	<!-- Js Plugins -->
	<!-- 	晉昇開始 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- 	晉昇結束 -->
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/member/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/moment.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/daterangepicker.js"></script>
	<!-- 	晉昇加 -->
	<script src="https://unpkg.com/axios@1.1.2/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<!-- 晉昇結束 -->
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
		//insertOrder
		const roombutton1 = document.querySelector("#roombutton1");
		roombutton1.addEventListener("click",(e)=>{
			
			const data = {
					action: "insertOrder",
					member_id: document.querySelector("#memberID").value,
					room_order_ttl_price: document.querySelector("#price").value
			};
			axios({
				headers:{'Content-Type':'application/x-www-form-urlencoded'},
				method: "post",
				url: "roomOrder.do",
				data: data
			});
		})
		
		
	</script>


	<script>
const demo1 = document.getElementById("btn");
demo1.addEventListener("click", function() {
  Swal.fire("完成訂單 系統於3秒後回首頁");
});
</script>
</body>

</html>