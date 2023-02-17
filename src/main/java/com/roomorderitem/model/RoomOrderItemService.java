package com.roomorderitem.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

public class RoomOrderItemService {
	private RoomOrderItemDAO_interface dao;

	public RoomOrderItemService() {
		dao =new RoomOrderItemDAO();
	}
	
//新增
	public RoomOrderItemVO addRoomOrderItem(Integer room_order_id,Integer room_type_id,Integer room_amount,Integer room_type_price,Integer room_sale_price,
			String special_req,Integer checkin_amount,Date room_order_checkin_date,Date room_order_checkout_date,String room_guest_name) {
		RoomOrderItemVO roomOrderItemVO = new RoomOrderItemVO();
		
		roomOrderItemVO.setRoom_order_id(room_order_id);
		roomOrderItemVO.setRoom_type_id(room_type_id);
		roomOrderItemVO.setRoom_amount(room_amount);
		roomOrderItemVO.setRoom_type_price(room_type_price);
		roomOrderItemVO.setRoom_sale_price(room_sale_price);
		roomOrderItemVO.setSpecial_req(special_req);
		roomOrderItemVO.setCheckin_amount(checkin_amount);
		roomOrderItemVO.setRoom_order_checkin_date(room_order_checkin_date);
		roomOrderItemVO.setRoom_order_checkout_date(room_order_checkout_date);
		roomOrderItemVO.setRoom_guest_name(room_guest_name);
		
		
		dao.insert(roomOrderItemVO);
		
		return roomOrderItemVO;
	}
	
	//取單
public RoomOrderItemVO getOneRoomOrderItem(Integer room_order_id) {
		
		return dao.findByPrimaryKey(room_order_id);
	}
//取全部 裝集合
public List<RoomOrderItemVO> getAll(){
	return dao.getAll();
}
//超級全部 裝集合
public List<RoomOrderItemVO> getAllLong(){
	return dao.getAllLong();
}

//找尋訂單 的訂單明細  做集合 
public List<RoomOrderItemVO> getOneForOrderId(Integer room_order_id){
	return dao.getOneForOrderId(room_order_id);
}
}
