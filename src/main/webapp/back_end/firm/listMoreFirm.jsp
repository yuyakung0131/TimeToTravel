<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.firm.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
								<div class="col-md-12">
									<table border=1 class="table ">
										<tr>
											<th>廠商編號</th>
											<th>處理員工編號姓名</th>
											<th>廠商類別</th>
											<th>廠商負責人</th>
											<th>廠商名稱</th>
											<th>廠商登記地址</th>
											<th>廠商營業地址</th>
											<th>廠商聯絡人</th>
											<th>廠商電話</th>
											<th>廠商e-mail</th>
											<th>廠商申請時間</th>
											<th>廠商狀態</th>
											<th>廠商審核狀態</th>
											<th>廠商申請書</th>
											<th>修改</th>
											
										</tr>
										
										<c:forEach var="firmVO" items="${firmList}">
										<tr>
											<td>${firmVO.firm_id}</td>
											<td>
												<c:if test="${firmVO.emp_id==0}">
													未處理
												</c:if>
												<c:if test="${firmVO.emp_id!=0}">
													${firmVO.emp_id}-[${firmVO.empVO.emp_name}]
												</c:if>
											</td>
											<td>${firmVO.firmtype_id}-[${firmVO.firmTypeVO.firmtype_name}]</td>
											<td>${firmVO.firm_prim}</td>
											<td>${firmVO.firm_name}</td>
											<td>${firmVO.firm_regist_add}</td>
											<td>${firmVO.firm_operate_add}</td>
											<td>${firmVO.firm_poc}</td>
											<td>${firmVO.firm_phone}</td>
											<td>${firmVO.firm_email}</td>
											<td><fmt:formatDate value="${firmVO.firm_apply_date}" pattern="yyyy-MM-dd"/></td>
											<td>
												<c:if test="${firmVO.firm_state==0}">
													啟用
												</c:if>
												<c:if test="${firmVO.firm_state==1}">
													無法啟用
												</c:if>
												<c:if test="${firmVO.firm_state==2}">
													停權
												</c:if>
											</td>
											<td>
												<c:if test="${firmVO.firm_review_state==0}">
													未審核
												</c:if>
												<c:if test="${firmVO.firm_review_state==1}">
													審核通過
												</c:if>
												<c:if test="${firmVO.firm_review_state==2}">
													審核不通過
												</c:if>
											</td>
											<td>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/firm/firm.do" style="margin-bottom: 0px;">
													<input type="submit" value="查看申請書"> 
													<input type="hidden" name="firm_id" value="${firmVO.firm_id}">
													<input type="hidden" name="action" value="show_img">
												</FORM>
											</td>
											<td>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/firm/firm.do" style="margin-bottom: 0px;">
													<input type="submit" value="修改"> 
													<input type="hidden" name="firm_id" value="${firmVO.firm_id}">
													<input type="hidden" name="action" value="get_one_update">
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
	<script src="<%=request.getContextPath()%>/back_end/firm/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/firm/js/bootstrap.min.js"></script>
</body>

</html>