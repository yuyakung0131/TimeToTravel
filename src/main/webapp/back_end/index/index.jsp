<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.empfunc.model.*"%>
<%@ page import="com.func.model.*"%>

<%
EmpVO empVO = (EmpVO)session.getAttribute("empVO");
Integer emp_id = empVO.getEmp_id();

EmpFuncService empFuncSvc = new EmpFuncService();
List<EmpFuncVO> listEmpFunc = empFuncSvc.getByEmpID(emp_id);
session.setAttribute("listEmpFunc", listEmpFunc);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Time To Travel</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/index/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/index/css/back.css"  type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
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
                    <div class="row">
                        <div class="col-md-12 h1">
                            <h1>首頁</h1>
                        </div>
                        </div>
               </div>
            </section>

        </div>

    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back_end/index/js/back.js"></script>
    <script src="<%=request.getContextPath()%>/back_end/index/js/bootstrap.min.js"></script>
</body>

</html>