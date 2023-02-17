package com.roomsearch.model;

import java.sql.Timestamp;

import com.roomtype.model.RoomTypeVO;

public class RoomSearchVO implements java.io.Serializable{
	private Integer firm_id;
	private String firm_name;
	private String firm_operate_add;
	private Integer room_type_id;
	private String room_type_name;
	private Timestamp reservation_date;
	private Integer room_type_amount;
	private Integer reservation_amount;
	private Integer empty_room;
	private String start_date;
	private String end_date;
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public Integer getFirm_id() {
		return firm_id;
	}
	public void setFirm_id(Integer firm_id) {
		this.firm_id = firm_id;
	}
	public String getFirm_name() {
		return firm_name;
	}
	public void setFirm_name(String firm_name) {
		this.firm_name = firm_name;
	}
	public String getFirm_operate_add() {
		return firm_operate_add;
	}
	public void setFirm_operate_add(String firm_operate_add) {
		this.firm_operate_add = firm_operate_add;
	}
	public Integer getRoom_type_id() {
		return room_type_id;
	}
	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}
	public String getRoom_type_name() {
		return room_type_name;
	}
	public void setRoom_type_name(String room_type_name) {
		this.room_type_name = room_type_name;
	}
	public Timestamp getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(Timestamp reservation_date) {
		this.reservation_date = reservation_date;
	}
	public Integer getRoom_type_amount() {
		return room_type_amount;
	}
	public void setRoom_type_amount(Integer room_type_amount) {
		this.room_type_amount = room_type_amount;
	}
	public Integer getReservation_amount() {
		return reservation_amount;
	}
	public void setReservation_amount(Integer reservation_amount) {
		this.reservation_amount = reservation_amount;
	}
	public Integer getEmpty_room() {
		return empty_room;
	}
	public void setEmpty_room(Integer empty_room) {
		this.empty_room = empty_room;
	}
	public com.roomtype.model.RoomTypeVO getRoomTypeData() {
		com.roomtype.model.RoomTypeService roomTypeSvc=new com.roomtype.model.RoomTypeService();
		com.roomtype.model.RoomTypeVO roomTypeVO=roomTypeSvc.getOneRoomType(room_type_id);
		return roomTypeVO;
	}
	
	public com.roomimg.model.RoomImgVO getRoomImgVO(){
		com.roomimg.model.RoomImgService roomImgSvc=new com.roomimg.model.RoomImgService();
		com.roomimg.model.RoomImgVO roomImgVO=roomImgSvc.getImgByRoomTypeLimit1(room_type_id);
		return roomImgVO;
	}
}
