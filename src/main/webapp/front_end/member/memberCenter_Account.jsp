<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--要用for each之程式碼匯入-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="TimeToTarvel">
<meta name="keywords" content="TimeToTarvel,TibameG3,html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Member_Center</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/member_center.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/styleforheader_footerNEW.css"
	type="text/css">

	<style>
	.nav-tabs .nav-item.show .nav-link, .nav-tabs .nav-link.active {
    color: #222736;
    /* border-color: transparent transparent white; */
    border: 0;
    border-bottom: 4px solid #dfa974;
    font-size: 12px;
    font-weight: bolder;
    pointer-events: none;
}
	</style>
</head>

<body>
	<!-- Page Preloder Begin -->
	<jsp:include page="../preLoder.jsp"></jsp:include>
	<!-- Page Preloder End -->
	<!-- Offcanvas Menu Section Begin -->
	<c:choose>
		<c:when test="${memberVO.member_id == null}">
			<!-- offcanvasMenuSection_nonmember -->
			<jsp:include page="../offcanvasMenuSection_nonmember.jsp"></jsp:include>
		</c:when>
		<c:when test="${memberVO.member_id != null}">
			<!-- offcanvasMenuSection_member -->
			<jsp:include page="../offcanvasMenuSection_member.jsp"></jsp:include>
		</c:when>
	</c:choose>
	<!-- Offcanvas Menu Section End -->
	<div class="toolbarFixed">
		<!-- header Begin -->
		<c:choose>
			<c:when test="${memberVO.member_id == null}">
				<!-- header_nonmember -->
				<jsp:include page="../header_nonmember.jsp"></jsp:include>
			</c:when>
			<c:when test="${memberVO.member_id != null}">
				<!-- header_member -->
				<jsp:include page="../header_member.jsp"></jsp:include>
			</c:when>
		</c:choose>
		<!-- Header End -->

		<!-- Member Center Start -->

		<div class="member-center">
			<div class="container">
				<div class="row no-gutters ">
					<div class="mc-slide col-lg-3">
						<div class="mc-box1 col-lg-12">
							<div class="profile">
								<div class="row">
									<div class="col-md-12">
										<form Method="post"
											action="<%=request.getContextPath()%>/front_end/member/member.do"
											id="formPic" enctype="multipart/form-data">
											<input type="hidden" name="member_id"
												value="${memberVO.member_id}"> <input type="hidden"
												name="member_email" value="${memberVO.member_email}">
											<input type="hidden" name="action" value="updateMemberImg">
											<img class="member-pic"
												src="${pageContext.request.contextPath}/front_end/member/member.do?action=getImg&member_id=${memberVO.member_id}"
												alt=""> <label for="file"> <i
												class="fa fa-camera" aria-hidden="true"></i> <input
												type="file" id="file" style="display: none" name="upfile"
												accept="image/gif,image/jpeg,image/jpg,image/png"
												data-original-title="upload photos">
											</label>
										</form>
										<input type="hidden" name="member_name"
											value="${memberVO.member_name}">
										<p class="member-name">${memberVO.member_name}</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div style="border-top: 1px solid rgb(211, 211, 211);"></div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-12">
										<img class="ttt" src="img/TTT.png" alt="">
									</div>
								</div>
								<div class="row">
									<div class="col-md-8">
										<span class="member-state">TimeToTarvel會員:</span> <span
											class="member-email">${memberVO.member_email}</span>
									</div>
									<div class="col-md-4">
										<i class="fa fa-check-circle-o"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="mc-box2 col-lg-12">
							<div class="profile-list">
								<ul class="list-unstyled">
									<li><a class="active"
										href="${pageContext.request.contextPath}/front_end/member/member.do?action=clickMemberCenter&member_email=${memberVO.member_email}"><i
											class="fa fa-solid fa-gear"></i>帳號設定</a></li>
									<li><a href="<%=request.getContextPath()%>/front_end/member/Orderlist.jsp"><i
											class="fa fa-file-text"></i>訂單管理</a></li>
									<li><a href="<%=request.getContextPath()%>/front_end/member/Favlist.jsp"><i
											class="fa fa-heart"></i>我的最愛</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="mc-table col-lg-9 co1-sm-12">
						<div class="col-lg-12 mc-box3">
							<div class="profile-info ">
								<div class="row">
									<div class="col-lg-3">
										<div class="title">帳號設定</div>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-12">
										<nav class="sub-title">
											<div class="nav nav-tabs" id="nav-tab" role="tablist">
												<a class="nav-item nav-link " id="nav-member-info-tab"
													data-toggle="tab" href="#nav-member-info" role="tab"
													aria-controls="nav-member-info" aria-selected="false">基本資料</a>
												<a class="nav-item nav-link active" id="nav-pwd-info-tab"
													data-toggle="tab" href="#nav-pwd-info" role="tab"
													aria-controls="nav-pwd-info" aria-selected="ture">帳號安全</a>
											</div>
										</nav>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-12">
										<div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">
											<!--change info start-->
											<div class="tab-pane fade  member-info" id="nav-member-info"
												role="tabpanel" aria-labelledby="nav-member-info">
												<form Method="post"
													action="<%=request.getContextPath()%>/front_end/member/member.do">
													<!-- hidden input which is servlet find action -->
													<input type="hidden" name="action" value="updateMemberInfo">
													<div class="row">
														<div class="col-md-4">
															<p>姓</p>
															<input class="c-name" type="text" name="member_firstname"
																value="${param.member_firstname}">
															<p style="color: red">${errorMsgs.member_firstname}</p>
														</div>
														<div class="col-md-4">
															<p>名</p>
															<input class="c-surname" type="text"
																name="member_secondname"
																value="${param.member_secondname}">
															<p style="color: red">${errorMsgs.member_secondname}</p>
														</div>
														<div class="col-md-4">
															<p>英文姓名</p>
															<input class="c-engname" type="text"
																name="member_nameeng"
																value="${param.member_nameeng==null?memberVO.member_nameeng:param.member_nameeng}">
															<p style="color: red">${errorMsgs.member_nameeng}</p>
														</div>
													</div>
													<div class="row">

														<div class="col-md-4">
															<p>身分證字號</p>
															<input class="c-idcard" type="text" name="member_idcard"
																value="${param.member_idcard==null?memberVO.member_idcard:param.member_idcard}">
															<p style="color: red">${errorMsgs.member_idcard}</p>
														</div>
														<div class="col-md-4">
															<p>性別(僅顯示)</p>
															<input class="gender" type="text" readonly="readonly"
																name="member_gender_show"
																value="${memberVO.member_gender}">
														</div>
														<div class="col-md-4">
															<p>修改性別</p>
															<select class="update-gender" name="member_gender">
																<option disabled selected>-請選擇-</option>
																<option value="not_choose">不填寫</option>
																<option value="boy">男性</option>
																<option value="girl">女性</option>
															</select>
														</div>
													</div>
													<div class="row">
														<div class="col-md-4">
															<p>電話號碼</p>
															<input class="phone" type="text" name="member_phone"
																value="${param.member_phone==null?memberVO.member_phone:param.member_phone}">
															<p style="color: red">${errorMsgs.member_phone}</p>

														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<p>聯絡地址(僅顯示)</p>
															<input class="address" type="text" readonly="readonly"
																name="member_add_show" value="${memberVO.member_add}">
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<p>更新聯絡地址</p>
														</div>
													</div>
													<div class="row">
														<div class="col-md-4">
															<p>縣市</p>
															<input type="hidden" name="selectedLabel_city"
																id="selectedLabel_city"> <select id="city"
																name="city"
																onchange="javascript:getSelectedLabel_city(this);">
																<option value="not-choose" disabled selected>-請選擇-</option>
															</select>
														</div>
														<div class="col-md-4">
															<p>區域</p>
															<input type="hidden" name="selectedLabel_area"
																id="selectedLabel_area"> <select id="area"
																name="area"
																onchange="javascript:getSelectedLabel_area(this);">
																<option value="not-choose" disabled selected>-請先選擇縣市-</option>
															</select>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<p>詳細地址</p>
															<input type="text" class="update-address"
																name="update_address">
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<input type="hidden" name="member_id"
																value="${memberVO.member_id}"> <input
																type="hidden" name="member_email"
																value="${memberVO.member_email}">
															<button type="submit">儲存</button>
														</div>
													</div>
												</form>
											</div>
											<!--change info end-->
											<!--change account start-->
											<div class="tab-pane fade  show active password-info"
												id="nav-pwd-info" role="tabpanel"
												aria-labelledby="nav-pwd-info">
												<form Method="post"
													action="<%=request.getContextPath()%>/front_end/member/member.do">
													<!-- hidden input which is servlet find action -->
													<input type="hidden" name="action"
														value="updateMemberAccount">
													<div class="row">
														<div class="col-md-12">
															<div class="account-title">&nbsp;修改帳號</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<p>原帳號(僅顯示)</p>
															<input class="old-email" type="text" readonly="readonly"
																name="member_email" value="${memberVO.member_email}">
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<p>新帳號(註:這為登入帳號,一旦修改就不能回頭!)</p>
															<input class="new-email" type="text" placeholder="輸入新帳號"
																name="member_newEmail" value="${param.member_newEmail}">
															<p style="color: red">${errorMsgs.member_newEmail}</p>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="password-title">&nbsp;修改密碼</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<p>原密碼</p>
															<input class="old-pwd" type="password" id="old-pwd"
																placeholder="輸入舊密碼" name="member_oldPwd"
																value="${param.member_oldPwd}">
															<p style="color: red">${errorMsgs.member_oldPwd}</p>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<p>新密碼</p>
															<input class="new-pwd" type="password" id="new-pwd"
																placeholder="輸入新密碼" name="member_newPwd"
																value="${param.member_newPwd}">
															<p style="color: red">${errorMsgs.member_newPwd}</p>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<input class="confirm-pwd" type="password"
																id="confirm-pwd" placeholder="再次確認新密碼"
																name="member_newPwd_confirm"
																value="${param.member_newPwd_confirm}">
															<p style="color: red">${errorMsgs.member_newPwd_confirm}</p>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<input class="form-check-input" type="checkbox" value=""
																id="flexCheckDefault" onclick="showPwd()"> <label
																class="form-check-label" for="flexCheckDefault">
																顯示密碼 </label>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<input type="hidden" name="member_name"
																value="${memberVO.member_name}"> <input
																type="hidden" name="member_id"
																value="${memberVO.member_id}">
															<button type="submit">儲存</button>
														</div>
													</div>
												</form>
											</div>
											<!--change account end-->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Member Center End -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->


	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/member/js/owl.carousel.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/front_end/member/js/main.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/front_end/member/js/meberCenter.js"></script>



</body>

</html>