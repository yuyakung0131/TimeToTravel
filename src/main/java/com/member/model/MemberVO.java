package com.member.model;

import java.sql.Timestamp;

public class MemberVO implements java.io.Serializable {

	private Integer member_id;
	private String member_email;
	private String member_pwd;
	private String member_name;
	private String member_nameeng;
	private String member_idcard;
	private String member_gender;
	private byte[] member_img;
	private String member_add;
	private String member_phone;
	private Byte member_state;
	private Timestamp member_date;
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_pwd() {
		return member_pwd;
	}
	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_nameeng() {
		return member_nameeng;
	}
	public void setMember_nameeng(String member_nameeng) {
		this.member_nameeng = member_nameeng;
	}
	public String getMember_idcard() {
		return member_idcard;
	}
	public void setMember_idcard(String member_idcard) {
		this.member_idcard = member_idcard;
	}
	public String getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
	public byte[] getMember_img() {
		return member_img;
	}
	public void setMember_img(byte[] member_img) {
		this.member_img = member_img;
	}
	public String getMember_add() {
		return member_add;
	}
	public void setMember_add(String member_add) {
		this.member_add = member_add;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public Byte getMember_state() {
		return member_state;
	}
	public void setMember_state(Byte member_state) {
		this.member_state = member_state;
	}
	public Timestamp getMember_date() {
		return member_date;
	}
	public void setMember_date(Timestamp member_date) {
		this.member_date = member_date;
	}


	
}
