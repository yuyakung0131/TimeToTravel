<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/emp/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/emp/css/back.css" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

<style>
	table td img {
		width: 50px;
		height: 50px;
	}
	
	table tbody td {
		vertical-align: middle;
	}
	
	ul {
		list-style: none;
		margin: 0px;
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
							<h1>員工資料管理</h1>
						</div>
					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">查看員工資料</button>
						<!-- 分頁2 -->
						<a href="<%=request.getContextPath()%>/back_end/emp/addEmp.jsp" style="text-decoration: none;">
							<button class="nav-link" data-bs-toggle="tab"
								data-bs-target="#tab-2" aria-selected="false">新增員工資料</button>
						</a>
						<!-- 分頁3 -->
					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="tab-1">
						
							<div class="row mt-3 justify-content-center">
								<div class="col-4">
									<a href='<%=request.getContextPath()%>/back_end/emp/listAllEmp.jsp'>顯示所有員工資料</a>
								</div>
							</div>
							
							<div class="row mt-3 ms-2 justify-content-center">
								<div class="col-4">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/emp/emp.do">

										<b>輸入員工編號:</b> <input type="text" name="emp_id" size="10">
										<input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出">

									</FORM>
								</div>
							</div>

							<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
							<!-- 相等於EmpService empSvc = new EmpService(); -->
							<div class="row mt-3 justify-content-center">
								<div class="col-4">

									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/emp/emp.do">
										<b>選擇員工姓名:</b> <select size="1" name="emp_id">
											<c:forEach var="empVO" items="${empSvc.all}">
												<option value="${empVO.emp_id}">${empVO.emp_name}
											</c:forEach>
										</select> <input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出">
									</FORM>

								</div>
							</div>
							
						</div>

						<!-- 分頁2 內容 -->

						<!-- 分頁3 內容 -->

					</div>
					<!-- =======================以上區塊可自訂======================= -->
				</div>
			</section>

		</div>

	</div>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/emp/js/back.js"></script>
    <script src="<%=request.getContextPath()%>/back_end/emp/js/bootstrap.min.js"></script>
</body>

</html>