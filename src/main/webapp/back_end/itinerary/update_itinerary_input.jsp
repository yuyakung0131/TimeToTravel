<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itinerary.model.*"%>

<%
	ItineraryVO itineraryVO = (ItineraryVO) request.getAttribute("itineraryVO");
%>

<%-- --<%= itineraryVO==null %>--${itineraryVO.itinerary_type_id}-- <!-- line 100 --> --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>行程修改 -update_itinerary_input</title>



</head>
<body bgcolor='white'>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font id="myDiv" style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/itinerary/itinerary.do" name="form1">

<table style="width:100%">

		<tr>
		<td>行程編號:<font color=red><b>*</b></font></td>
		<td><%=itineraryVO.getItinerary_id()%></td>
	</tr>


	<jsp:useBean id="itinerarytypeSvc" scope="page" class="com.itinerarytype.model.ItineraryTypeService" />
	<tr>
		<td>請選擇行程種類:<font color=red><b>*</b></font></td>
		<td><select size="1" name="itinerary_type_id">
			<c:forEach var="itineraryTypeVO" items="${itinerarytypeSvc.all}">
				<option value="${itineraryTypeVO.itinerary_type_id}" ${(itineraryVO.itinerary_type_id==itineraryTypeVO.itinerary_type_id)? 'selected':'' } >${itineraryTypeVO.itinerary_title}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>報名開始日:</td>
		<td><input name="entered_start" id="B_date1" type="text"></td>
		
	</tr>
		<tr>
		<td>報名結束日:</td>
		<td><input name="entered_end" id="B_date2" type="text"></td>
	</tr>
		<tr>
		<td>行程開始日:</td>
		<td><input name="itinerary_start" id="B_date3" type="text"></td>
	</tr>
		<tr>
		<td>行程結束日:</td>
		<td><input name="itinerary_end" id="B_date4" type="text"></td>
	</tr>
	<tr>
		<td>行程狀態:<font color=red><b>*</b></font></td>
		<td><select size="1" name="itinerary_state">
				<option value="3" ${(itineraryVO.itinerary_state==3)? 'selected':'' }>未開始報名
				<option value="0" ${(itineraryVO.itinerary_state==0)? 'selected':'' }>正常
				<option value="1" ${(itineraryVO.itinerary_state==1)? 'selected':'' }>額滿
				<option value="2" ${(itineraryVO.itinerary_state==2)? 'selected':'' }>取消
		</select></td>
	</tr>
		<tr>
		<td>目前報名人數:</td>
		<td><input type="TEXT" name="itinerary_now" size="10"
			 value="<%=itineraryVO.getItinerary_now()%>" /></td>
	</tr>

		<tr>
		<td>行程費用:</td>
		<td><input type="TEXT" name="itinerary_price" size="10"
			 value="<%=itineraryVO.getItinerary_price()%>" /></td>
	</tr>
	
			<tr>
		<td>最少成行人數:</td>
		<td><input type="TEXT" name="itinerary_min" size="10"
			 value="<%=itineraryVO.getItinerary_min()%>" /></td>
	</tr>
	
			<tr>
		<td>最多可報名人數:</td>
		<td><input type="TEXT" name="itinerary_max" size="10"
			 value="<%=itineraryVO.getItinerary_max()%>" /></td>
	</tr>

</table>


<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="itinerary_id" value="<%=itineraryVO.getItinerary_id()%>">
<input  type="submit" value="送出修改"></FORM>


<br>
  

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


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
	 $('#B_date1').datetimepicker({
	  format:'Y-m-d',
	  value: '<%=itineraryVO.getEntered_start()%>',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#B_date2').val()?$('#B_date2').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#B_date2').datetimepicker({
	  format:'Y-m-d',
	  value: '<%=itineraryVO.getEntered_end()%>',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#B_date1').val()?$('#B_date1').val():false,
	    maxDate:$('#B_date3').val()?$('#B_date3').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#B_date3').datetimepicker({
		  format:'Y-m-d',
		  value: '<%=itineraryVO.getItinerary_start()%>',
		  onShow:function(){
		   this.setOptions({
		    maxDate:$('#B_date4').val()?$('#B_date4').val():false,
		    minDate:$('#B_date2').val()?$('#B_date2').val():false
		   })
		  },
		  timepicker:false
		 });
		 
	 $('#B_date4').datetimepicker({
		  format:'Y-m-d',
		  value: '<%=itineraryVO.getItinerary_end()%>',
		  onShow:function(){
		   this.setOptions({
		    minDate:$('#B_date3').val()?$('#B_date3').val():false
		   })
		  },
		  timepicker:false
	 });
});
</script>
</html>