<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itineraryorder.model.*"%>
<%@ page import="java.util.*"%>

<%
ItineraryOrderVO itineraryOrderVO = (ItineraryOrderVO) request.getAttribute("itineraryOrderVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Time To Travel</title>
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"> -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/itineraryorder/css/bootstrap.min.css" type="text/css">
    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/itineraryorder/css/back.css"  type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <style>
        #itr-3-2 {
            text-align: left;
            padding: 10px;
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
                            <h1>行程訂單管理</h1>
                        </div>

                    </div>

                    <!-- 段2 tab -->
                    <div class="nav nav-tabs nav-justified">
                        <!-- 分頁1 -->
                        <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#itr-3-1"
                            aria-selected="true">
                            查看行程訂單
                        </button>
                       

                    </div>
                    <!-- 段2 內容 -->
                    <div class="tab-content">
                        <!-- 分頁1 內容 -->
                        <div class="tab-pane show active" id="itr-3-1">
                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/itineraryorder/order.do">
                            <div class="row search">
                                <div class="col-md-12">
                                    <b>輸入訂單編號:</b> <input type="text" name="itinerary_order_id">
										<input type="hidden" name="action" value="getOne_For_Display">
                                    <button id="btn-search">搜尋</button>
                                </div>
                            </div>
                            </FORM>
                            <div>
                                <table table border=1 rules="rows" class="table ">
                            
                                        <tr>
                                            <th>訂單編號</th>
                                            <th>會員姓名</th>
                                            <th>行程編號</th>
                                            <th>訂單日期</th>
                                            <th>報名人數</th>
                                            <th>訂單總金額</th>
                                            <th>訂單狀態</th>
                                            <th>退款狀態</th>
                                            <th>訂單備註</th>
                                            <th>取消訂單</th>
                                        </tr>
                    
									<tr>
										<td><%=itineraryOrderVO.getItinerary_order_id() %></td>
										<td>${itineraryOrderVO.memberVO.member_name}</td>
										<td><%=itineraryOrderVO.getItinerary_id() %></td>
										<td><%=itineraryOrderVO.getItinerary_order_time() %></td>
										<td><%=itineraryOrderVO.getItinerary_people_num() %></td>
										<td><%=itineraryOrderVO.getItinerary_ttl_price() %></td>
										<td><%=itineraryOrderVO.getItinerary_order_state() %></td>
										<td><%=itineraryOrderVO.getItinerary_refund_state() %></td>
										<td><%=itineraryOrderVO.getItinerary_order_memo() %></td>

										<td>
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/itineraryorder/order.do"
												style="margin-bottom: 0px;">
												
												<input type="hidden"  name="itinerary_id" value="${itineraryOrderVO.itinerary_id} " />
												<input type="hidden" name="itinerary_order_id" value="${itineraryOrderVO.itinerary_order_id}"/> 
												<input type="hidden"  name="itinerary_people_num" value="${itineraryOrderVO.itinerary_people_num} " />
												<input type="hidden" name="action" value="updateItrOrder">
												<c:if test="${itineraryOrderVO.itinerary_order_state  == 1}">
  														<button type="submit" class="btn btn-light" disabled="disabled">取消訂單</button>
												</c:if>
												<c:if test="${itineraryOrderVO.itinerary_order_state  == 0}">
  														<button type="submit" class="btn btn-light" >取消訂單</button>
												</c:if>
											</FORM>
										</td>

									</tr>
	
                                </table>
                            </div>
                        </div>
                        

                    </div>

                </div>
            </section>

        </div>

    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back_end/itineraryorder/js/back.js"></script>
    <script src="<%=request.getContextPath()%>/back_end/itineraryorder/js/bootstrap.min.js"></script>
</body>

</html>