<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/ticket/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/ticket/css/back.css" type="text/css">
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
							<h1>票券管理</h1>
						</div>

					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">票券資料查詢</button>
						<!-- 分頁2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-2" aria-selected="false">票券資料管理</button>
						<!-- 分頁3 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-3" aria-selected="false">票券圖片資料查詢</button>
						<!-- 分頁4 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-4" aria-selected="false">票券圖片資料管理</button>

					</div>




					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->

						<%-- 錯誤表列 --%>

						<div class="tab-pane show active" id="tab-1">
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<c:forEach var="message" items="${errorMsgs}">
									<font style="color: red">${message}</font>
								</c:forEach>
							</c:if>
							<br>
							<div>
								<div class="col-md-5">
									<a href="listAllTicket.jsp">列出</a>所有資料
								</div>
							</div>
							<br>


							<div class="row">
								<div class="col-md-6">
									<form method="post" action="ticket.do">
										<input type="text" placeholder="輸入商品編號" name="tkt_id">
										<input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出">

										<!-- 											<i class="fa fa-check-circle" aria-hidden="true" -->
										<!-- 												type="submit">送出</i> -->

									</form>
									<!-- 								</div> -->
								</div>
								<br>

								<jsp:useBean id="ticketSvc" scope="page"
									class="com.ticket.model.TicketService" />
								<!-- 							<div class="row"> -->
								<div class="col-md-6">
									<form method="post" action="ticket.do">
										選擇商品編號<select size=1 name="tkt_id">
											<c:forEach var="ticket" items="${ticketSvc.all}">
												<option value="${ticket.tkt_id}">${ticket.tkt_id}
											</c:forEach>
										</select> <input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出">
									</form>
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-md-6">
									<form method="post" action="ticket.do">
										選擇商品名稱 <select size=1 name="tkt_id">
											<c:forEach var="ticket" items="${ticketSvc.all}">
												<option value="${ticket.tkt_id}">${ticket.tkt_name}
											</c:forEach>

										</select> <input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出">
									</form>
								</div>
								<!-- 							</div> -->
								<br> <br>

								<jsp:useBean id="tickettypeSvc" scope="page"
									class="com.tickettype.model.TicketTypeService" />
								<!-- 							<div class="row"> -->
								<div class="col-md-6">
									<form method="post" action="ticket.do">
										票券種類: <select size="1" name="tkt_type_id">
											<c:forEach var="tkt_type" items="${tickettypeSvc.all}">
												<option value="${tkt_type.tkt_type_id}">${tkt_type.tkt_type_name}
											</c:forEach>
										</select> <input type="hidden" name="action"
											value="getType_For_Display"> <input type="submit"
											value="送出">
									</form>
								</div>
							</div>

							<!-- h3/h4? -->
							<!-- 前台的收藏刪除?? -->
							<br>
							<div class="row">
								<div class="col-md-7">
									<form method="post" action="ticket.do">
										票券價錢: <input type="text" name="min" placeholder="請輸入最小值">
										<input type="text" name="max" placeholder="請輸入最大值"> <input
											type="hidden" name="action" value="getPrice_For_Display">
										<input type="submit" value="送出">
									</form>
								</div>
								<!-- 							</div> -->



								<br>
								<!-- 							<div class="row"> -->
								<div class="col-md-5">
									<FORM METHOD="post" ACTION="ticket.do">
										<select name="action">
											<option value="getAmount_For_Display">最少庫存量小於</option>
											<option value="getTktDate_For_Display">有效日期小於</option>
										</select> <input type="text" name="number"> <input
											type="submit" value="送出">
									</FORM>
								</div>
							</div>
							<br> <br>
							<div class="row">
								<div class="col-md-12">
									<FORM METHOD="post" ACTION="ticket.do">
										1 | 2 | 3 | 4 | 5 (分) <br> 票券評論分數: <input type="range"
											name="tkt_comment_score" min="1" max="5" step="1"> <input
											type="submit" value="送出"> <input type="hidden"
											name="action" value="getComment_For_Display">
									</FORM>
								</div>
							</div>
						</div>














						<!-- 分頁2 內容 -->



						<!-- =======================以上區塊可自訂======================= -->

						<div class="tab-pane" id="tab-2">
							<br>
							<div class="row">
								<div class="col-md-3">
									<a href="addTicket.jsp">新增</a>一筆票券資料
								</div>
							</div>
							<br>

						</div>

						<div class="tab-pane" id="tab-3">




							<div class="row">
								<div class="col-md-3">
								<br>
									<a
										href="<%=request.getContextPath()%>/back_end/tktimg/listAllTktimg.jsp">列出</a>列出所有圖片資料
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-md-5">
									<form method="post"
										action="<%=request.getContextPath()%>/back_end/tktimg/ticketIMG.do">
										票券名稱: <select size="1" name="tkt_id">
											<c:forEach var="ticket" items="${ticketSvc.all}">
												<option value="${ticket.tkt_id}">${ticket.tkt_name}
											</c:forEach>
										</select> <input type="hidden" name="action"
											value="getOnImageId_For_Display"> <input
											type="submit" value="送出">
									</form>
								</div>
							</div>



						</div>

						<div class="tab-pane" id="tab-4">



							<div class="row">
								<div class="col-md-3">
									<br>
									<a
										href="<%=request.getContextPath()%>/back_end/tktimg/addTktImg.jsp">新增</a>一筆票券圖片資料
								</div>
							</div>

							<br>
							<div class="row">
								<div class="col-md-3">
									<!-- 									<a href="addTicket.jsp">修改</a>票券圖片資料 -->
								</div>
							</div>

							<br>

						</div>
					</div>


					<!-- =======================以上區塊可自訂======================= -->
				</div>

			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticket/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticket/js/bootstrap.min.js"></script>
</body>
</body>
</html>