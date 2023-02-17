<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.empfunc.model.*"%>

<!DOCTYPE html>

<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/empFunc/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/empFunc/css/back.css" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

<style>
	table td img {
		width: 50px;
		height: 50px;
	}
	
	table tbody td {
		vertical-align: middle;
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
							<h1>員工權限資料</h1>
						</div>
					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->

					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="tab-1">
							<div class="row search">
								<div class="col-md-12">
									<table border=1  class="table ">
										<tr>
											<th>員工</th>
											<th>權限</th>
											<th>刪除</th>
										</tr>
										<c:forEach var="empFuncVO" items="${empFuncVO}">

											<tr>
												<td>${empFuncVO.emp_id}-[${empFuncVO.empVO.emp_name}]</td>
												<td>${empFuncVO.func_id}-[${empFuncVO.funcVO.func_name}]</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/empFunc/empFunc.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="刪除"> 
														<input type="hidden" name="emp_id" value="${empFuncVO.emp_id}">
														<input type="hidden" name="func_id" value="${empFuncVO.func_id}"> 
														<input type="hidden" name="action" value="delete">
													</FORM>
												</td>
											</tr>
										</c:forEach>
									</table>
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
	<script src="<%=request.getContextPath()%>/back_end/empFunc/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/empFunc/js/bootstrap.min.js"></script>

</body>

</html>