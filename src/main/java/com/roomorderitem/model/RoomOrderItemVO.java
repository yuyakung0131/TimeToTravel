package com.roomorderitem.model;

import java.sql.Date;
import java.sql.Timestamp;

public class RoomOrderItemVO implements java.io.Serializable {
	private Integer room_order_id;
	private Integer room_type_id;
	private Integer room_amount;
	private Integer room_type_price;
	private Integer room_sale_price;
	private String special_req;
	private Integer checkin_amount;
	private Date room_order_checkin_date;
	private Date room_order_checkout_date;
	private String room_guest_name;

	public Integer getRoom_order_id() {
		return room_order_id;
	}

	public void setRoom_order_id(Integer room_order_id) {
		this.room_order_id = room_order_id;
	}

	public Integer getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}

	public Integer getRoom_amount() {
		return room_amount;
	}

	public void setRoom_amount(Integer room_amount) {
		this.room_amount = room_amount;
	}

	public Integer getRoom_type_price() {
		return room_type_price;
	}

	public void setRoom_type_price(Integer room_type_price) {
		this.room_type_price = room_type_price;
	}

	public Integer getRoom_sale_price() {
		return room_sale_price;
	}

	public void setRoom_sale_price(Integer room_sale_price) {
		this.room_sale_price = room_sale_price;
	}

	public String getSpecial_req() {
		return special_req;
	}

	public void setSpecial_req(String special_req) {
		this.special_req = special_req;
	}

	public Integer getCheckin_amount() {
		return checkin_amount;
	}

	public void setCheckin_amount(Integer checkin_amount) {
		this.checkin_amount = checkin_amount;
	}

	public Date getRoom_order_checkin_date() {
		return room_order_checkin_date;
	}

	public void setRoom_order_checkin_date(Date room_order_checkin_date) {
		this.room_order_checkin_date = room_order_checkin_date;
	}

	public Date getRoom_order_checkout_date() {
		return room_order_checkout_date;
	}

	public void setRoom_order_checkout_date(Date room_order_checkout_date) {
		this.room_order_checkout_date = room_order_checkout_date;
	}

	public String getRoom_guest_name() {
		return room_guest_name;
	}

	public void setRoom_guest_name(String room_guest_name) {
		this.room_guest_name = room_guest_name;
	}
	
	public com.roomtype.model.RoomTypeVO getRoomTypeVO() {
	    com.roomtype.model.RoomTypeService deptSvc = new com.roomtype.model.RoomTypeService();
	    com.roomtype.model.RoomTypeVO roomTypeVO = deptSvc.getOneRoomType(room_type_id);
	    return roomTypeVO;
    }
	
	
}
