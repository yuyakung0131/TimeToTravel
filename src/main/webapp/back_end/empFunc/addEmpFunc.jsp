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
							<h1>所有員工權限資料</h1>
						</div>
					</div>

					<hr>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<a href="<%=request.getContextPath()%>/back_end/empFunc/listAllEmpFunc.jsp" style="text-decoration: none;">
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="false">搜尋員工權限</button>
						</a>
						<!-- 分頁2 -->
						
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-2" aria-selected="true">新增員工權限</button>
						
						<!-- 分頁3 -->
					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					
					<div class="tab-content">
						<!-- 分頁1 內容 -->
					<div class="row justify-content-center">
						<div class="col-4">
						<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
						<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
							<form method="post" action="<%=request.getContextPath()%>/back_end/empFunc/empFunc.do">
								<br>
								<table>
									<tr>
										<td><b>選擇員工姓名:</b></td>
										<td>
											<select size="1" name="emp_id">
												<c:forEach var="empVO" items="${empSvc.all}">
													<option value="${empVO.emp_id}" ${(param.emp_id==empVO.emp_id)? 'selected':'' }>${empVO.emp_name}
												</c:forEach>
											</select> 
										</td>
										<td><pre>     </pre></td>
										<td><b>選擇權限:</b></td>
										<td>
											<select size="1" name="func_id">
												<c:forEach var="funcVO" items="${funcSvc.all}">
													<option value="${funcVO.func_id}" ${(param.func_id==funcVO.func_id)? 'selected':'' }>${funcVO.func_name}
												</c:forEach>
											</select> 
										</td>
										<td><pre> </pre></td>
										<td><input type="hidden" name="action" value="insert"> 
											<input type="submit" value="新增">
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>

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