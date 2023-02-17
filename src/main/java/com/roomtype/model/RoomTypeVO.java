package com.roomtype.model;

import java.util.List;



public class RoomTypeVO implements java.io.Serializable{
	private Integer room_type_id;
	private Integer firm_id;
	private String room_type_name;
	private Integer room_type_amount;
	private String room_type_content;
	private Byte room_type_state;
	private Integer room_type_price;
	private Integer room_type_score;
	private Integer room_type_pepple;
	

	public Integer getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}

	public Integer getFirm_id() {
		return firm_id;
	}

	public void setFirm_id(Integer firm_id) {
		this.firm_id = firm_id;
	}

	public String getRoom_type_name() {
		return room_type_name;
	}

	public void setRoom_type_name(String room_type_name) {
		this.room_type_name = room_type_name;
	}

	public Integer getRoom_type_amount() {
		return room_type_amount;
	}

	public void setRoom_type_amount(Integer room_type_amount) {
		this.room_type_amount = room_type_amount;
	}

	public String getRoom_type_content() {
		return room_type_content;
	}

	public void setRoom_type_content(String room_type_content) {
		this.room_type_content = room_type_content;
	}

	public Byte getRoom_type_state() {
		return room_type_state;
	}

	public void setRoom_type_state(Byte room_type_state) {
		this.room_type_state = room_type_state;
	}

	public Integer getRoom_type_price() {
		return room_type_price;
	}

	public void setRoom_type_price(Integer room_type_price) {
		this.room_type_price = room_type_price;
	}

	public Integer getRoom_type_score() {
		return room_type_score;
	}

	public void setRoom_type_score(Integer room_type_score) {
		this.room_type_score = room_type_score;
	}

	public Integer getRoom_type_pepple() {
		return room_type_pepple;
	}

	public void setRoom_type_pepple(Integer room_type_pepple) {
		this.room_type_pepple = room_type_pepple;
	}

	public com.firm.model.FirmVO getFirm_id_byFirm(){
		com.firm.model.FirmService firmSvc=new com.firm.model.FirmService();
		com.firm.model.FirmVO firmVO=firmSvc.getOneDeptByRoomLin(firm_id);
		return firmVO;
	}
	public com.promotionitem.model.PromotionItemVO getPromotionItemVORoom(){
		com.promotionitem.model.PromotionItemService promotionItemSvc=new com.promotionitem.model.PromotionItemService();
		com.promotionitem.model.PromotionItemVO promotionItemVO=promotionItemSvc.getProByRoomType(room_type_id);
		return promotionItemVO;
	}
	public com.roomimg.model.RoomImgVO getRoomImgVO(){
		com.roomimg.model.RoomImgService roomImgSvc=new com.roomimg.model.RoomImgService();
		com.roomimg.model.RoomImgVO roomImgVO=roomImgSvc.getImgByRoomTypeLimit1(room_type_id);
		return roomImgVO;
	}
	
	
}
