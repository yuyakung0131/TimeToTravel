<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tktimg.model.*"%>
<%@ page import="java.util.*"%>


<%
Integer tkt_id = (Integer) request.getAttribute("tkt_id");
TktImgService tktimgSvc = new TktImgService();
List<TktImg> list = tktimgSvc.findByTktId(tkt_id);
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/tktimg/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/tktimg/css/back.css" type="text/css">
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
							data-bs-target="#tab-1" aria-selected="true">票券圖片資料管理</button>

					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">
	<div>
								<input type="button" onclick="history.go(-1)" class="btn btn-secondary btn-sm" value="回到上一頁"></input>
							</div>
							<br>

						<div class="tab-pane show active" id="tab-1">

							<table>
								<tr>
									<th>票券編號</th>
									<th>票券圖片編號</th>
									<th>票券名稱</th>
									<th>票券種類</th>
									<th>票券發行公司</th>
									<th>票券圖片</th>
								</tr>
								<c:forEach var="tktimg" items="${list}" begin="0"
									end="${list.size()-1}">
									<tr>
										<td>${tktimg.tkt_id}</td>
										<td>${tktimg.tkt_img_id}</td>
										<td>${tktimg.getTicket().tkt_name}</td>
										<td>${tktimg.getTicket().getTicketType().getTkt_type_name()}</td>
										<td>${tktimg.getTicket().getFirm().getFirm_name()}</td>
										<td><img
											src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimg.tkt_img_id}"
											width=300px height=250px></td>
									</tr>
								</c:forEach>
							</table>











						</div>


					</div>


					<!-- =======================以上區塊可自訂======================= -->
				</div>

			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/tktimg/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/tktimg/js/bootstrap.min.js"></script>
</body>
</body>
</html>