package com.roomcomment.model;

import java.sql.Date;
import java.sql.Timestamp;

public class RoomCommentVO implements java.io.Serializable {

	private Integer room_comment_id;
	private Integer room_type_id;
	private Integer member_id;
	private String room_comment_content;
	private Integer room_comment_star;
	private Timestamp room_comment_time;
	
	public Integer getRoom_comment_id() {
		return room_comment_id;
	}
	public void setRoom_comment_id(Integer room_commemt_id) {
		this.room_comment_id = room_commemt_id;
	}
	public Integer getRoom_type_id() {
		return room_type_id;
	}
	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getRoom_comment_content() {
		return room_comment_content;
	}
	public void setRoom_comment_content(String room_comment_content) {
		this.room_comment_content = room_comment_content;
	}
	public Integer getRoom_comment_star() {
		return room_comment_star;
	}
	public void setRoom_comment_star(Integer room_comment_star) {
		this.room_comment_star = room_comment_star;
	}
	public Timestamp getRoom_comment_time() {
		return room_comment_time;
	}
	public void setRoom_comment_time(Timestamp room_comment_time) {
		this.room_comment_time = room_comment_time;
	}
	

	

}
