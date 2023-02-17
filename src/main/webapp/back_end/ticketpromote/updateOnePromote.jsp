<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketpromote.model.*"%>
<%@ page import="java.util.*"%>


<%
TicketPromote ticketpromote = (TicketPromote) request.getAttribute("ticketpromote");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/ticketpromote/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/ticketpromote/css/back.css" type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}
/*   table, th, td { */
/*     border: 1px solid #CCCCFF; */
/*   } */
th, td {
	padding: 5px;
	text-align: center;
}
</style>
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
							data-bs-target="#tab-1" aria-selected="true">票券促銷專案資料修改</button>

					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">


						<div class="tab-pane show active" id="tab-1">
							<FORM METHOD="post" ACTION="ticketpromote.do" name="form1">
								<table>
									<tr>
										<td>票券促銷專案編號 :</td>
										<td><%=ticketpromote.getProm_id()%></td>
									</tr>
									<tr>
										<td>票券促銷專案名稱 :</td>
										<td><input type="TEXT" name="prom_name" size="45"
											value="<%=ticketpromote.getProm_name()%>" /></td>
									</tr>
									<tr>
										<td>票券促銷專案狀態	 :</td>
										<td><input type="TEXT" name="prom_state" size="45"
											value="<%=ticketpromote.getProm_state()%>" /></td>
									</tr>
									<tr>
										<td>專案促銷折扣數	 :</td>
										<td><input type="TEXT" name="discount_amount" size="45"
											value="<%=ticketpromote.getDiscount_amount()%>" /></td>
									</tr>
									<tr>
										<td>專案達標價格 :</td>
										<td><input type="TEXT" name="prom_achieve_number" size="45"
											value="<%=ticketpromote.getProm_achieve_number()%>" /></td>
									</tr>

								</table>
								<input type="hidden" name="action" value="update"> <input
									type="hidden" name="prom_id"
									value="<%=ticketpromote.getProm_id()%>"> <input
									type="submit" value="送出修改">
							</FORM>










						</div>


					</div>


					<!-- =======================以上區塊可自訂======================= -->
				</div>

			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticketpromote/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/ticketpromote/js/bootstrap.min.js"></script>
</body>
</body>
</html>