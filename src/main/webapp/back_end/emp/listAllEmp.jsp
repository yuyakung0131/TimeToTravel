<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
EmpService empSvc = new EmpService();
List<EmpVO> list = empSvc.getAll();
pageContext.setAttribute("list", list);
%>

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
							<h1>所有員工資料</h1>
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
									<table border=1 class="table ">
										<tr>
											<th>編號</th>
											<th>頭像</th>
											<th>中文姓名</th>
											<th>英文姓名</th>
											<th>帳號</th>
											<th>密碼</th>
											<th>狀態</th>
											<th>入職日期</th>
											<th>修改</th>
										</tr>
										<%@ include file="page1.file"%>
										<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
											end="<%=pageIndex+rowsPerPage-1%>">

											<tr>
												<td>${empVO.emp_id}</td>
												<td><img src="${pageContext.request.contextPath}/back_end/emp/emp.do?action=getImg&emp_id=${empVO.emp_id}"></td>
												<td>${empVO.emp_name}</td>
												<td>${empVO.emp_nameeng}</td>
												<td>${empVO.emp_account}</td>
												<td>${empVO.emp_pwd}</td>
												<td>
													<c:if test="${empVO.emp_state==0}">
														在職
													</c:if>
													<c:if test="${empVO.emp_state==1}">
														離職
													</c:if>
												</td>
												<td>${empVO.emp_date}</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/back_end/emp/emp.do" style="margin-bottom: 0px;">
														<input type="submit" value="修改"> 
														<input type="hidden" name="emp_id" value="${empVO.emp_id}">
														<input type="hidden" name="action" value="getOne_For_Update">
													</FORM>
												</td>
											</tr>
										</c:forEach>
									</table>
									<%@ include file="page2.file"%>
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