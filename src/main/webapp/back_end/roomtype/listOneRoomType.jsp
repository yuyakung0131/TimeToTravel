<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.roomtype.model.*"%>
<%
RoomTypeService roomSvc=new RoomTypeService();
List<RoomTypeVO> list=roomSvc.getAll();
pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneRoomType</title>
</head>
<body>
	<table border="1" class="table" rules="rows">
		<thead>
			<tr>
				<th>房型編號</th>
				<th>廠商名稱</th>
				<th>房型名稱</th>
				<th>房型數量</th>
				<th>房型說明</th>
				<th>房型價格</th>
				<th>房型狀態</th>
				<th></th>
				<th></th>
				
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${roomTypeVO.room_type_id}</td>
				<td>${roomTypeVO.firm_id}</td>
				<td>${roomTypeVO.room_type_name}</td>
				<td>${roomTypeVO.room_type_amount }</td>
				<td>${roomTypeVO.room_type_content }</td>
				<td>${roomTypeVO.room_type_price }</td>
				<td>${roomTypeVO.room_type_state }</td>
				<form action="room.do" method="post">
					<input type="hidden" name="room_type_id"
						value="${roomTypeVO.room_type_id }"> <input type="hidden"
						name="action" value="update_view">
					<td><input type="submit" value="修改"></td>
				</form>
				<form action="room.do" method="post">
					<input type="hidden" name="room_type_id"
						value="${roomTypeVO.room_type_id }"> <input type="hidden"
						name="action" value="delete">
					<td><input type="submit" value="刪除"></td>
				</form>
			</tr>
		</tbody>
	</table>
</body>
</html>