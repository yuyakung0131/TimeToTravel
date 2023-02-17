<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--要用for each之程式碼匯入-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
MemberService memberSvc = new MemberService();
List<MemberVO> list = memberSvc.getALL();
pageContext.setAttribute("listALL", list);
%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Time To Travel</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/member/css/bootstrap.min.css" type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/member/css/back.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/member/css/nice-select.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/member/css/back_member.css" type="text/css">
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
							<h1>會員帳號管理</h1>
						</div>

					</div>
					<!-- =======================以下區塊可自訂======================= -->
					<!-- 分頁標籤，可以新增分頁，若不要分頁可以整塊刪掉 -->
					<div class="nav nav-tabs">
						<!-- 分頁1 -->
						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">會員搜尋</button>
						<!-- 分頁2 -->
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-2" aria-selected="false">會員資料管理</button>


					</div>
					<!--  分頁內容，若不要分頁可以整塊刪掉 -->
					<div class="tab-content">
						<!-- 分頁1 內容 -->
						<div class="tab-pane show active" id="tab-1">
							<!-- Title1 -->
							<div class="row">
								<div class="col-md-12">
									<p class="m-search-title">會員搜尋條件:</p>
								</div>
							</div>
								<!-- subtile -->
							<div class="row">
								<div class="col-sm-12">
									<p class="m-sub-title">1.輸入搜尋:</p>
								</div>
							</div>
							<!--search box 1-->
							<div class="row">
								<div class="col-sm-12">
									<form Method="post" action="<%=request.getContextPath()%>/back_end/member/member.do">
										<select name="action">
											<option value="not_choose" disabled selected>-請選擇-</option>
											<option value="s_memberName">會員姓名</option>
											<option value="s_memberNo">會員編號</option>
										</select> 
										<input class="c-search" type="text" name="s_box">
										<input type="hidden" name="action" value="not_choose_member">
										 <input type="submit" value="搜尋" class="row-1-button">
									</form>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<p class="state-ermsg">${errorMsgs.selectMember}</p>
								</div>
							</div>
							<!-- subtile -->
							<div class="row">
								<div class="col-sm-12">
									<p class="m-sub-title">2.狀態搜尋:</p>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<form Method="post" action="<%=request.getContextPath()%>/back_end/member/member.do">
										<select name="action" >
											<option  disabled selected>-請選擇-</option>
											<option value="s_enable">啟用狀態</option>
											<option value="s_disable">停用狀態</option>
										</select> 
										<input type="hidden" name="action" value="not_choose_state">
										<input type="submit"  value = "搜尋" class="row-2-button">
									</form>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<p class="state-ermsg">${errorMsgs.selectState}</p>
								</div>
							</div>

							<!--searchtitle two-->
							<div class="row">
								<div class="col-md-12">
									<p class="m-search-list">會員搜尋列表:</p>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<table table border=1 rules="rows" class="table ">
										<thead>
											<tr>
												<th>會員編號</th>
												<th>Email</th>
												<th>會員密碼</th>
												<th>中文姓名</th>
												<th>英文姓名</th>
												<th>身分證號碼</th>
												<th>性別</th>
												<th>手機號碼</th>
												<th>註冊日期</th>
												<th>會員狀態</th>
											</tr>
										</thead>
										<tbody>
											<!--利用for each之動態產生table->這邊我認為是上java檔案-->


										
											<c:forEach var="memberVO" items="${searchByOne}">
												<tr>
													<td>${memberVO.member_id}</td>
													<td>${memberVO.member_email}</td>
													<td>${memberVO.member_pwd}</td>
													<td>${memberVO.member_name}</td>
													<td>${memberVO.member_nameeng}</td>
													<td>${memberVO.member_idcard}</td>
													<td>${memberVO.member_gender}</td>
													<td>${memberVO.member_phone}</td>
													<td>${memberVO.member_date}</td>
													<td>${memberVO.member_state==0?"啟用中":"停權中"}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>


						<!-- 分頁2 內容 -->
						<div class="tab-pane" id="tab-2">
							<!-- Title1 -->
							<div class="row">
								<div class="col-md-12">
									<p class="m-search-title">會員總表:</p>
								</div>
							</div>
							<div class=>
								<table table border=1 rules="rows" class="table ">
									<thead>
										<tr>
											<th>會員編號</th>
											<th>Email</th>
											<th>會員密碼</th>
											<th>中文姓名</th>
											<th>英文姓名</th>
											<th>身分證號碼</th>
											<th>性別</th>
											<th>手機號碼</th>
											<th>註冊日期</th>
											<th>會員狀態</th>
											<th>會員狀態修改</th>
										</tr>
									</thead>
									<tbody>
	<!--利用for each之動態產生table->這邊我認為是上java檔案-->
	                                   <%@ include file="pageList.file"%>
										<c:forEach var="memberVO" items="${listALL}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
											<tr>
												<td>${memberVO.member_id}</td>
												<td>${memberVO.member_email}</td>
												<td>${memberVO.member_pwd}</td>
												<td>${memberVO.member_name}</td>
												<td>${memberVO.member_nameeng}</td>
												<td>${memberVO.member_idcard}</td>
												<td>${memberVO.member_gender}</td>
												<td>${memberVO.member_phone}</td>
												<td>${memberVO.member_date}</td>
												<td>${memberVO.member_state==0?"啟用中":"停權中"}</td>
												<td>

													<form Method="post" action="<%=request.getContextPath()%>/back_end/member/member.do"
														class="stateChangeEnable">
														<input type="hidden" name="member"
															value="${memberVO.member_id}"> <input
															type="hidden" name="action" value="btn-enable">
														<button class="btn-enable" type="submit">啟用</button>
													</form> <!-- 												<button class="btn-enable">啟用</button> -->
													<form Method="post" action="<%=request.getContextPath()%>/back_end/member/member.do"
														class="stateChangeDisable">
														<input type="hidden" name="member"
															value="${memberVO.member_id}"> <input
															type="hidden" name="action" value="btn-disable">
														<button class="btn-disable" type="submit">停權</button>
													</form> <!-- 													<button class="btn-disable">停權</button> -->
												</td>
											</tr>
										</c:forEach>
                                         
									</tbody>
								</table>
							</div>
							<div class="row">
								<div class="col-12">
									<nav aria-label="Page navigation example" class="page-nav">
										<ul class="pagination">
											<%@ include file="pageNav.file" %> 
										</ul>
									</nav>
								</div>
							</div>
						</div>
					</div>
					<!-- =======================以上區塊可自訂======================= -->
				</div>
			</section>

		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/member/js/back.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/member/js/back-member.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/member/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/member/js/jquery.nice-select.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/member/js/sweetalert2.all.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/member/js/main.js"></script>


</body>

</html>