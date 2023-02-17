<%@page import="javax.print.attribute.HashPrintRequestAttributeSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.roomimg.model.*"%>
<%@ page import="com.firm.model.*"%>

<%
Integer room_type_id=(Integer) request.getAttribute("room_type_id");
RoomImgService roomImgSvc = new RoomImgService();
List<RoomImgVO> roomImgList = roomImgSvc.getImgByRoomType(room_type_id);
pageContext.setAttribute("roomImgList", roomImgList);


%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/roomtype/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/roomtype/css/back.css" type="text/css">
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
							<h1>房型管理</h1>
						</div>
					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs ">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">新增房型圖片</button>
						<!-- 分頁2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-2" aria-selected="false">瀏覽此房型圖片</button>
						<!-- 分頁3 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-3" aria-selected="false"></button>

					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="tab-1">
							<div class="container">
								<div class="row" style="text-align: initial;">
									<form method="post" action="<%=request.getContextPath() %>/back_end/roomtype/roomImg.do" name="form1" enctype="multipart/form-data">
										
										<table>
											<tr>
												<td>房型名稱:</td>
												<td><input type="text" name="room_type_name" size="45"
													value="${roomTypeVO.room_type_name }" disabled></td>
											</tr>
											<tr>
												<td>選擇圖片:</td>
												<td><input type="file" name="roomImgUpFile" class="roomImgUpFile"
													size="45" accept="image/gif,image/jpeg,image/jpg,image/png"
												data-original-title="upload photos"></td>
											</tr>
										</table>
										<br> 
										<input type="hidden" name="room_type_id" value="${roomTypeVO.room_type_id }">
										<input type="hidden" name="action" value="insert_room_img">
										<input type="submit" value="新增圖片">
									</FORM>
								</div>
							</div>
							
						</div>
						<!-- 分頁2 內容 -->
						<div class="tab-pane" id="tab-2">
						<div class="container">
								<div class="row" style="text-align: initial;">
										<table>
											<tr>
												<td>房型名稱:<span>${roomTypeVO.room_type_name}</span></td>
												
											</tr>
										</table>
										<c:forEach var="roomImgListVO" items="${roomImgList }">
										<div class="row" style="align-items:self-end; border:1px solid #d3cdcd; padding:10px;margin-bottom:10px;">
									<form class="col-lg-10" action="<%=request.getContextPath() %>/back_end/roomtype/roomImg.do" method="post"  enctype="multipart/form-data">
										<img
											src="<%=request.getContextPath()%>/ShowRoomImgServlet?room_img_id=${roomImgListVO.room_img_id}"
											width=300px height=250px>
											<input type="hidden" name="action" value="update_room_img">
											<input type="hidden" name="room_img_id" value="${roomImgListVO.room_img_id }">
											<input type="hidden" name="room_type_id" value="${roomImgListVO.room_type_id }">
											<input type="file" name="roomImgUpFile" >
											<input type="submit" value="修改圖片">
									</form>
									<form class="col-lg-2" method="post" action="<%=request.getContextPath() %>/back_end/roomtype/roomImg.do" >
									<input type="hidden" name="room_img_id" value="${roomImgListVO.room_img_id }">
									<input type="hidden" name="action" value="delete_img">
									<input type="submit" value="刪除圖片" >
									</form>
									</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<!-- 分頁3 內容 -->
						<div class="tab-pane" id="tab-3"></div>

					</div>
					<!-- =======================以上區塊可自訂======================= -->
				</div>
			</section>

			<a
				href="${pageContext.request.contextPath }/back_end/roomtype/select_page.jsp">首頁</a>
			<a
				href="${pageContext.request.contextPath }/back_end/roomtype/addRoomType.jsp">新增</a>
			<a
				href="${pageContext.request.contextPath }/back_end/roomtype/listAllRoomType.jsp">查全部</a>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/roomtype/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/roomtype/js/bootstrap.min.js"></script>
</body>

</html>