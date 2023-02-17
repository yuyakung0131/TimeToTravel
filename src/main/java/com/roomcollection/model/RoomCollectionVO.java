package com.roomcollection.model;

import java.sql.Timestamp;

public class RoomCollectionVO implements java.io.Serializable {
	private Integer member_id;
	private Integer room_type_id;
	private Timestamp fav_date;

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Integer getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}

	public Timestamp getFav_date() {
		return fav_date;
	}

	public void setFav_date(Timestamp fav_date) {
		this.fav_date = fav_date;
	}
	
	public com.roomtype.model.RoomTypeVO getRoomType_id_byRoomType(){
		com.roomtype.model.RoomTypeService roomTypeSvc=new com.roomtype.model.RoomTypeService();
		com.roomtype.model.RoomTypeVO roomTypeVO=roomTypeSvc.getOneRoomType(room_type_id);
		return roomTypeVO;
	}
}
