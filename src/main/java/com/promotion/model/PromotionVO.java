package com.promotion.model;

import java.sql.Date;

public class PromotionVO implements java.io.Serializable {
	private Integer promotion_id;
	private String promotion_name;
	private Date promotion_startdate;
	private Date promotion_enddate;
	private Byte promotion_state;

	public Integer getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
	}

	public String getPromotion_name() {
		return promotion_name;
	}

	public void setPromotion_name(String promotion_name) {
		this.promotion_name = promotion_name;
	}

	public Date getPromotion_startdate() {
		return promotion_startdate;
	}

	public void setPromotion_startdate(Date promotion_startdate) {
		this.promotion_startdate = promotion_startdate;
	}

	public Date getPromotion_enddate() {
		return promotion_enddate;
	}

	public void setPromotion_enddate(Date promotion_enddate) {
		this.promotion_enddate = promotion_enddate;
	}

	public Byte getPromotion_state() {
		return promotion_state;
	}

	public void setPromotion_state(Byte promotion_state) {
		this.promotion_state = promotion_state;
	}
}
