package com.roomimg.model;

public class RoomImgVO implements java.io.Serializable {
	private Integer room_img_id;
	private Integer room_type_id;
	private byte[] room_img;

	public Integer getRoom_img_id() {
		return room_img_id;
	}

	public void setRoom_img_id(Integer room_img_id) {
		this.room_img_id = room_img_id;
	}

	public Integer getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}

	public byte[] getRoom_img() {
		return room_img;
	}

	public void setRoom_img(byte[] room_img) {
		this.room_img = room_img;
	}

}
