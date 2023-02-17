<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO");
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
							<h1>員工資料管理</h1>
						</div>
					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<a href="<%=request.getContextPath()%>/back_end/emp/select_page.jsp" style="text-decoration: none;">
							<button class="nav-link" data-bs-toggle="tab"
								data-bs-target="#tab-1" aria-selected="false">查看員工資料</button>
						</a>
						<!-- 分頁2 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-2" aria-selected="true">新增員工資料</button>
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

						<!-- 分頁2 內容 -->
						<div class="tab-pane show active" id="tab-2">
							<div class="row">
								<div class="col-6">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/emp/emp.do" name="form1" enctype="multipart/form-data">
										<table border=1  class="table">
											<tr>
												<td>帳號:</td>
												<td><input type="TEXT" name="emp_account" size="45"
													value="<%=(empVO == null) ? "xxx@timetotravel.com" : empVO.getEmp_account()%>" /></td>
											</tr>
											<tr>
												<td>密碼:</td>
												<td><input type="TEXT" name="emp_pwd" size="45"
													value="<%=(empVO == null) ? "xxx" : empVO.getEmp_pwd()%>" /></td>
											</tr>
											<tr>
												<td>姓名:</td>
												<td><input type="TEXT" name="emp_name" size="45"
													value="<%=(empVO == null) ? "小明" : empVO.getEmp_name()%>" /></td>
											</tr>
											<tr>
												<td>Name:</td>
												<td><input type="TEXT" name="emp_nameeng" size="45"
													value="<%=(empVO == null) ? "Mark" : empVO.getEmp_nameeng()%>" /></td>
											</tr>
											<tr>
												<td>到職日:</td>
												<td><input name="emp_date" id="f_date1" type="text"></td>
											</tr>
											<tr>
												<td>照片:</td>
												<td><input type="file" name="emp_img"></td>
											</tr>
										</table>
										<br> <input type="hidden" name="action" value="insert">
										<input type="submit" value="送出新增">
									</FORM>
								</div>
							</div>
						</div>

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

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Date emp_date = null;
try {
	emp_date = empVO.getEmp_date();
} catch (Exception e) {
	emp_date = new java.sql.Date(System.currentTimeMillis());
}
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<!-- 用動態取得專案位置 -->
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
     $.datetimepicker.setLocale('zh');
     $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=emp_date%>' ,// value:   new Date(),不能直接用這個，因為第120行的物件一開始會是空值，所以會是取第122行的時間。
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>


</html>