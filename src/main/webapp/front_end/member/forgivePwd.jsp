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
    <title>Forgive Password</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/flaticon.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/login.css" type="text/css">
</head>

<body>
    <!-- Login page Start -->
    <div class="sidenav">
        <div class="login-main-text">
            <h3>Time to Travel</h3>
            <p>Forgive Passwowd</p>
        </div>
    </div>
    <div class="main">
        <div class="col-sm-12">
            <nav>
                <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                    <a class="nav-item nav-link active" id="nav-regist-tab" data-toggle="tab" href="#nav-regist"
                        role="tab" aria-controls="nav-regist" aria-selected="false">忘記密碼</a>
                </div>
            </nav>

            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active " id="nav-regist" role="tabpanel"
                    aria-labelledby="nav-regist-tab">
                    <div class="col-12 form-div">
                        <form Method="post" action="<%=request.getContextPath()%>/front_end/member/member.do">
                         <!-- hidden input which is servlet find action -->
                         <input type="hidden" name="action" value="forgivePwd">
                            <div class="form-group">
                                <label>使用者帳戶</label>
                                <input type="text" class="form-control account" placeholder="&#xf0e0; 電子郵件"
                                    style="font-family:Arial, FontAwesome" name="member_Email" value="${param.member_Email}">
                                <p>${errorMsgs.member_Email}</p>
                            </div>
                            <div class="form-group">
                                <label>新密碼</label>
                                <input type="password" class="form-control password" id="member_newPwd"
                                    placeholder="&#xf023; 密碼" style="font-family:Arial, FontAwesome" name="member_newPwd" value="${param.member_newPwd}">
                                <p>${errorMsgs.member_newPwd}</p>
                            </div>
                            <div class="form-group">
                                <label>確認密碼</label>
                                <input type="password" class="form-control password" id="member_newPwd_confirm"
                                    placeholder="&#xf084; 確認密碼" style="font-family:Arial, FontAwesome" name="member_newPwd_confirm" value="${param.member_newPwd_confirm}" >
                                <p>${errorMsgs.member_newPwd_confirm}</p>
                            </div>
                            <div class="form-group">

                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault3"
                                    onclick="showChangePwd()">
                                <label class="form-check-label" for="flexCheckDefault3">
                                    顯示密碼
                                </label>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn-change"><span>確認修改</span></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
</div>
        <!-- login page End -->
        <!-- Js Plugins -->
        <script src="<%=request.getContextPath()%>/front_end/member/js/jquery-3.3.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/front_end/member/js/jquery.magnific-popup.min.js"></script>
        <script src="<%=request.getContextPath()%>/front_end/member/js/jquery.nice-select.min.js"></script>
        <script src="<%=request.getContextPath()%>/front_end/member/js/jquery-ui.min.js"></script>
        <script src="<%=request.getContextPath()%>/front_end/member/js/jquery.slicknav.js"></script>
        <script src="<%=request.getContextPath()%>/front_end/member/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/front_end/member/js/owl.carousel.min.js"></script>
        <script src="<%=request.getContextPath()%>/front_end/member/js/main.js"></script>
         <script src="<%=request.getContextPath()%>/front_end/member/js/RegistLogin.js"></script>
</body>

</html>