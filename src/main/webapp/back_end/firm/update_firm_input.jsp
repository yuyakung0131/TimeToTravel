<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.firm.model.*"%>


<!DOCTYPE html>

<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/firm/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/firm/css/back.css" type="text/css">
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
							<h1>廠商資料</h1>
						</div>
					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->

					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="tab-1">
							<div class="row search">
								<div class="col-md-8">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/firm/firm.do" name="form1">
										<table border=1  class="table">
											<tr>
												<td width="200px">廠商編號</td>
												<td width="300px">${firmVO.firm_id}</td>
											</tr>
											<tr>
												<td width="200px">廠商類別</td>
												<td width="300px">${firmVO.firmtype_id}-[${firmVO.firmTypeVO.firmtype_name}]</td>
											</tr>
											<tr>
												<td width="200px">處理員工</td>
												<td width="300px">${empVO.emp_name}</td>
											</tr>
											<tr>
												<td width="200px">廠商負責人</td>
												<td width="300px">${firmVO.firm_prim}</td>
											</tr>
											<tr>
												<td width="200px">廠商名稱</td>
												<td width="300px">${firmVO.firm_name}</td>
											</tr>
											<tr>
												<td width="200px">廠商登記地址</td>
												<td width="300px">${firmVO.firm_regist_add}</td>
											</tr>
											<tr>
												<td width="200px">廠商營業地址</td>
												<td width="300px">${firmVO.firm_operate_add}</td>
											</tr>
											<tr>
												<td width="200px">廠商聯絡人</td>
												<td width="300px">${firmVO.firm_poc}</td>
											</tr>
											<tr>
												<td width="200px">廠商電話</td>
												<td width="300px">${firmVO.firm_phone}</td>
											</tr>
											<tr>
												<td width="200px">廠商e-mail</td>
												<td width="300px">${firmVO.firm_email}</td>
											</tr>
											<tr>
												<td width="200px">廠商申請時間</td>
												<td width="300px">${firmVO.firm_apply_date}</td>
											</tr>
											<tr>
												<td width="200px">廠商狀態</td>
												<td width="300px">
													<select name="firm_state">
														<option value="0" ${(firmVO.firm_state==0)? 'selected':'' }>啟用</option>			
														<option value="1" ${(firmVO.firm_state==1)? 'selected':'' }>無法啟用</option>
														<option value="2" ${(firmVO.firm_state==2)? 'selected':'' }>停權</option>
													</select>
												</td>
												<td  width="200px" style="color:red; background-color:white;">${errorMsgs.firm_review_state}</td>
											</tr>
											<tr>
												<td width="200px">廠商審核狀態</td>
												<td width="300px">
													<select name="firm_review_state">
														<option value="0" ${(firmVO.firm_review_state==0)? 'selected':'' }>未審核</option>			
														<option value="1" ${(firmVO.firm_review_state==1)? 'selected':'' }>審核通過</option>
														<option value="2" ${(firmVO.firm_review_state==2)? 'selected':'' }>審核未通過</option>
													</select>
												</td>
											</tr>
										</table>
										<input type="hidden" name="action" value="update">
										<input type="hidden" name="emp_id" value="${empVO.emp_id}">
										<input type="hidden" name="firm_id" value="${firmVO.firm_id}">
										
										<input type="submit" value="送出修改">
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
	<script src="<%=request.getContextPath()%>/back_end/firm/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/firm/js/bootstrap.min.js"></script>

</body>

</html>