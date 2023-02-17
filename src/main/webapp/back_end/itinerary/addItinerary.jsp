<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itinerary.model.*"%>

<%
	ItineraryVO additineraryVO = (ItineraryVO) request.getAttribute("additineraryVO");
	

%>
	
<%-- --<%= itineraryVO==null %>--${itineraryVO.itinerary_type_id}-- <!-- line 100 --> --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>行程新增 - addItinerary.jsp</title>


</head>
<body bgcolor='white'>



<c:if test="${not empty adderrorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${adderrorMsgs}">
			<li id="ermsg"  style="color:red;list-style-type: none;">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/itinerary/itinerary.do" name="form1">

<table style="width:100%">

	<jsp:useBean id="itinerarytypeSvc" scope="page" class="com.itinerarytype.model.ItineraryTypeService" />
	<tr>
		<td>請選擇行程種類:<font color=red><b>*</b></font></td>
		<td><select size="1" name="itinerary_type_id">
			<c:forEach var="itineraryTypeVO" items="${itinerarytypeSvc.all}">
				<option value="${itineraryTypeVO.itinerary_type_id}" ${(additineraryVO.itinerary_type_id==itineraryTypeVO.itinerary_type_id)? 'selected':'' } >${itineraryTypeVO.itinerary_title}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>報名開始日:</td>
		<td><input name="entered_start" id="A_date1" type="text" required autocomplete="off"></td>
	</tr>
		<tr>
		<td>報名結束日:</td>
		<td><input name="entered_end" id="A_date2" type="text" required autocomplete="off"></td>
	</tr>
		<tr>
		<td>行程開始日:</td>
		<td><input name="itinerary_start" id="A_date3" type="text" required autocomplete="off"></td>
	</tr>
		<tr>
		<td>行程結束日:</td>
		<td><input name="itinerary_end" id="A_date4" type="text" required autocomplete="off"></td>
	</tr>
	<tr>
		<td>行程狀態:<font color=red><b>*</b></font></td>
		<td><select size="1" name="itinerary_state">
				<option value="3" ${(additineraryVO.itinerary_state==3)? 'selected':'' }>未開始報名
				<option value="0" ${(additineraryVO.itinerary_state==0)? 'selected':'' }>正常
				<option value="1" ${(additineraryVO.itinerary_state==1)? 'selected':'' }>額滿
				<option value="2" ${(additineraryVO.itinerary_state==2)? 'selected':'' }>取消
		</select></td>
	</tr>
		<tr>
		<td>目前報名人數:</td>
		<td><input type="TEXT" name="itinerary_now" size="10"
			 value="<%= (additineraryVO==null)? "0" : additineraryVO.getItinerary_now()%>" /></td>
	</tr>

		<tr>
		<td>行程費用:</td>
		<td><input type="TEXT" name="itinerary_price" size="10"
			 value="<%= (additineraryVO==null)? "" : additineraryVO.getItinerary_price()%>" /></td>
	</tr>
	
			<tr>
		<td>最少成行人數:</td>
		<td><input type="TEXT" name="itinerary_min" size="10"
		
			 value="<%= (additineraryVO==null)? "" : additineraryVO.getItinerary_min()%>" /></td>
	</tr>
	
			<tr>
		<td>最多可報名人數:</td>
		
		<td>
		
		<input type="TEXT" name="itinerary_max" size="10"
		
			 value="<%= (additineraryVO==null)? "0" : additineraryVO.getItinerary_max()%>" /></td>
	</tr>

</table>


<br>
<input type="hidden" name="action" value="insert">
<input class="btn-enable" type="submit" value="送出新增"></FORM>


<br>
  

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

 <%  
   java.sql.Date entered_start = null;
   try {
 	  entered_start = java.sql.Date.valueOf(request.getParameter("entered_start").trim());
    } catch (Exception e) {
 	   entered_start = new java.sql.Date(System.currentTimeMillis());
   }
 
//    java.sql.Date entered_end = null;
//    try {
// 	   entered_end = java.sql.Date.valueOf(request.getParameter("entered_end").trim());
//     } catch (Exception e) {
//     	entered_end = new java.sql.Date(System.currentTimeMillis());
//    }
 
//    java.sql.Date itinerary_start = null;
//    try {
// 	   itinerary_start = java.sql.Date.valueOf(request.getParameter("itinerary_start").trim());
//     } catch (Exception e) {
//     	itinerary_start = new java.sql.Date(System.currentTimeMillis());
//    }
 
//    java.sql.Date itinerary_end = null;
//    try {
// 	   itinerary_end = java.sql.Date.valueOf(request.getParameter("itinerary_end").trim());
//     } catch (Exception e) {
//     	itinerary_end = new java.sql.Date(System.currentTimeMillis());
//    }
 
 %>
  

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#A_date1').datetimepicker({
	  format:'Y-m-d',
	  minDate:               '-1970-01-01',
	  value: '<%=entered_start%>',
	  onShow:function(){
	   this.setOptions({
		   
	    maxDate:$('#A_date2').val()?$('#A_date2').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#A_date2').datetimepicker({
	  format:'Y-m-d',
<%-- 	  value: '<%=entered_end%>', --%>
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#A_date1').val()?$('#A_date1').val():false,
	    maxDate:$('#A_date3').val()?$('#A_date3').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#A_date3').datetimepicker({
		  format:'Y-m-d',
<%-- 	  value: '<%=itinerary_start%>', --%>
		  onShow:function(){
		   this.setOptions({
		    maxDate:$('#A_date4').val()?$('#A_date4').val():false,
		    minDate:$('#A_date2').val()?$('#A_date2').val():false
		   })
		  },
		  timepicker:false
		 });
		 
	 $('#A_date4').datetimepicker({
		  format:'Y-m-d',
<%-- 	  value: '<%=itinerary_end%>', --%>
		  onShow:function(){
		   this.setOptions({
		    minDate:$('#A_date3').val()?$('#A_date3').val():false
		   })
		  },
		  timepicker:false
	 });
});



</script>
</html>