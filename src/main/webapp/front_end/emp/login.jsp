<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--要用for each之程式碼匯入-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<!DOCTYPE html>
<html lang="zxx">

<head>
   <meta charset="UTF-8">
   <meta name="description" content="TimeToTarvel">
   <meta name="keywords" content="TimeToTarvel,TibameG3,html">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <title>Login</title>

   <!-- Google Font -->
   <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">

   <!-- Css Styles -->
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/bootstrap.min.css" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/font-awesome.min.css" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/elegant-icons.css" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/flaticon.css" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/owl.carousel.min.css" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/nice-select.css" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/jquery-ui.min.css" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/magnific-popup.css" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/slicknav.min.css" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/emp/css/login.css" type="text/css">
   
   <style>
	   	.sidenav {
	    height: 100%;
	    background-color: rgb(0, 43, 65);
		overflow-x: hidden;
	    padding-top: 20px;
		}
		
		h3 {
	    margin: 0;
	    color: white;
	    font-weight: 400;
	    font-family: "Lora", serif;
		}
   </style>
</head>

<body>
   <!-- Login page Start -->
   <div class="sidenav">
      <div class="login-main-text">
         <h3>Time to Travel</h3>
         <p>Login </p>
      </div>
   </div>
   <div class="main">
      <div class="col-sm-12">
       <nav>
            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
               <a class="nav-item nav-link active" id="nav-login-tab" data-toggle="tab" href="#nav-login" role="tab"
                  aria-controls="nav-profile" aria-selected="true">登入</a>
            </div>
       </nav>

         <div class="tab-content" id="nav-tabContent">
            
            <div class="tab-pane fade show active" id="nav-login" role="tabpanel" aria-labelledby="nav-login-tab">
               <div class="col-12 form-div">
                  <form  Method="post" action="<%=request.getContextPath()%>/back_end/emp/emp.do">

                 	 <input type="hidden" name="action" value="login">
                     <div class="form-group">
                        <label>員工帳號</label>
                        <input type="text" class="form-control account" placeholder="&#xf0e0; 員工帳號"
                           style="font-family:Arial, FontAwesome"  name="emp_account"  value="${param.emp_account}">
                           <p>${errorMsgs.empLogin_account}</p>
                           <p>${errorMsgs.empVO}</p>
                     </div>
                     <div class="form-group">
                        <label>員工密碼</label>
                        <input type="password" class="form-control password" placeholder="&#xf023; 員工密碼"
                           style="font-family:Arial, FontAwesome"  name="emp_pwd" value="">
                            <p>${errorMsgs.empLogin_pwd}</p>
                     </div>
                     <div class="form-group">
                        <button type="submit" class="btn-login"><span>登入</span></button>
                     </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
</div>

      <script src="<%=request.getContextPath()%>/front_end/emp/js/jquery-3.3.1.min.js"></script>
      <script src="<%=request.getContextPath()%>/front_end/emp/js/jquery.magnific-popup.min.js"></script>
      <script src="<%=request.getContextPath()%>/front_end/emp/js/jquery.nice-select.min.js"></script>
      <script src="<%=request.getContextPath()%>/front_end/emp/js/jquery-ui.min.js"></script>
      <script src="<%=request.getContextPath()%>/front_end/emp/js/jquery.slicknav.js"></script>
      <script src="<%=request.getContextPath()%>/front_end/emp/js/bootstrap.min.js"></script>
      <script src="<%=request.getContextPath()%>/front_end/emp/js/owl.carousel.min.js"></script>
      <script src="<%=request.getContextPath()%>/front_end/emp/js/main.js"></script>
</body>

</html>