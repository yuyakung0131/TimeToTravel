package com.promotionitem.model;

public class PromotionItemVO implements java.io.Serializable {
	private Integer promotion_id;
	private Integer room_type_id;
	private Double discount_number;

	public Integer getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
	}

	public Integer getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}

	public Double getDiscount_number() {
		return discount_number;
	}

	public void setDiscount_number(Double discount_number) {
		this.discount_number = discount_number;
	}
	// join 名字 促銷
    public com.promotion.model.PromotionVO getPromotionVO() {
    	com.promotion.model.PromotionService proSvc = new com.promotion.model.PromotionService();
    	com.promotion.model.PromotionVO proVO = proSvc.getOnePromotion(promotion_id);
	    return proVO;
    }
    // 房名
    public com.roomtype.model.RoomTypeVO getRoomTypeVO() {
	    com.roomtype.model.RoomTypeService deptSvc = new com.roomtype.model.RoomTypeService();
	    com.roomtype.model.RoomTypeVO roomTypeVO = deptSvc.getOneRoomType(room_type_id);
	    return roomTypeVO;
    }
    }


