<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--要用for each之程式碼匯入-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%String memberData = "memberData" ;%>


<head>
   <meta charset="UTF-8">
    <meta http-equiv="refresh" content="5;url= <%=request.getContextPath()%>/front_end/member/home.jsp">
   <meta name="description" content="TimeToTarvel">
   <meta name="keywords" content="TimeToTarvel,TibameG3,html">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <title>Register Success</title>

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
         <p>Register Success.</p>
      </div>
   </div>

         <p class="sucessMessage">註冊成功!系統跳轉中,請靜候5秒鐘...</p>
   
        
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