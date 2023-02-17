package com.roomorder.model;

import java.sql.Timestamp;

public class RoomOrderVO implements java.io.Serializable {
	private Integer room_order_id;
	private Integer member_id;
	private Timestamp room_order_date;
	private Byte room_order_state;
	private Integer room_order_ttl_price;

	public Integer getRoom_order_id() {
		return room_order_id;
	}

	public void setRoom_order_id(Integer room_order_id) {
		this.room_order_id = room_order_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Timestamp getRoom_order_date() {
		return room_order_date;
	}

	public void setRoom_order_date(Timestamp room_order_date) {
		this.room_order_date = room_order_date;
	}

	public Byte getRoom_order_state() {
		return room_order_state;
	}

	public void setRoom_order_state(Byte room_order_state) {
		this.room_order_state = room_order_state;
	}

	public Integer getRoom_order_ttl_price() {
		return room_order_ttl_price;
	}

	public void setRoom_order_ttl_price(Integer room_order_ttl_price) {
		this.room_order_ttl_price = room_order_ttl_price;
	}

	// join 名字
	public com.member.model.MemberVO getMemberVO() {
		com.member.model.MemberService memberService = new com.member.model.MemberService();
		com.member.model.MemberVO memberVO = memberService.roomGetOneMemberSheng(member_id);
		return memberVO;

	}
	
    public com.roomtype.model.RoomTypeVO getRoomTypeVO(Integer room_type_id) {
	    com.roomtype.model.RoomTypeService deptSvc = new com.roomtype.model.RoomTypeService();
	    com.roomtype.model.RoomTypeVO roomTypeVO = deptSvc.getOneRoomType(room_type_id);
	    return roomTypeVO;
    }

}
