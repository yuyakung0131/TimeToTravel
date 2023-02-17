<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotionitem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
PromotionItemVO promotionItemVO = (PromotionItemVO) request.getAttribute("promotionItemVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/roomPromotion/css/bootstrap.min.css"
	type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/roomPromotion/css/back.css"
	type="text/css">
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
							<h1>房型促銷管理</h1>
						</div>

					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->

						<a
							href="<%=request.getContextPath()%>/back_end/roomPromotion/select_pagePromotion.jsp"
							style="text-decoration: none; color: black;">
							<button class="nav-link " data-bs-toggle="tab"
								data-bs-target="#rpt-1-1" aria-selected="true">查看促銷專案</button>
						</a>
						<!-- 分頁2 -->

						<a
							href="<%=request.getContextPath()%>/back_end/roomPromotion/addPromotion.jsp"
							style="text-decoration: none; color: black;">
							<button class="nav-link" data-bs-toggle="tab"
								data-bs-target="#rpt-1-2" aria-selected="false">新增促銷專案</button>
						</a>
						<!-- 分頁3 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#rpt-1-3" aria-selected="false">促銷折扣列表</button>
						<!-- 分頁4 -->

						<a
							href="<%=request.getContextPath()%>/back_end/roomPromotion/addPromotionItem.jsp"
							style="text-decoration: none; color: black;">
							<button class="nav-link" data-bs-toggle="tab"
								data-bs-target="#rpt-1-4" aria-selected="false">新增促銷折扣</button>
						</a>

					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">

						<!-- =============================分頁1 =========================內容 -->
						<div class="tab-pane " id="rpt-1-1"></div>

						<!-- 分頁2  資料新增 -->

						<div class="tab-pane" id="rpt-1-2"></div>
						<!-- 						分頁3 內容 -->


						<div class="tab-pane show active" id="rpt-1-3">

							<div>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back_end/roomPromotion/promotionItem.do"
									name="form1">
									<table border=1 rules="rows" class="table ">
										<thead>
											<tr>
												<th>促銷名稱<font style="color: red;">*</font></th>
												<th>房型類別<font style="color: red;">*</font></th>
												<th>折扣數</th>
												<th>修改</th>

											</tr>
										</thead>
										<tbody>
											<!--跌代出list -->

											<tr>
												<td>${promotionItemVO.promotionVO.promotion_name}</td>
												<td>${promotionItemVO.roomTypeVO.room_type_name}</td>

												<td>更改折扣: <font color=red>${promotionItemVO.discount_number}*
												</font><input type="TEXT" name="discount_number" size="45px"
													value="<%=promotionItemVO.getDiscount_number()%>" /><font
													color=red>${errorMsgs.discount_number}</font>
												</td>




												<td><input type="hidden" name="promotion_id"
													value="${promotionItemVO.promotion_id}"> <input
													type="hidden" name="room_type_id"
													value="${promotionItemVO.room_type_id}">
													<button type="submit" class="btn btn-primary">送出修改</button>
													<input type="hidden" name="action"
													value="update_promotionItem"></td>

											</tr>

										</tbody>
									</table>
								</FORM>
							</div>
						</div>
						<!-- 						分頁3 內容結束 -->
						<!-- 						分頁4 內容 -->
						<div class="tab-pane" id="rpt-1-4"></div>

						<!-- 						分頁4 內容結束 -->
						<!-- 下面這DIV 包整塊的喔!! -->
					</div>
					<!-- =======================以上區塊可自訂======================= -->
				</div>
			</section>

		</div>

	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/roomPromotion/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/roomPromotion/js/bootstrap.min.js"></script>
</body>

</html>