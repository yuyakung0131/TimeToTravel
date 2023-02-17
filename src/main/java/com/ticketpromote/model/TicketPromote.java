package com.ticketpromote.model;

import java.math.BigDecimal;

public class TicketPromote implements java.io.Serializable {

	private Integer prom_id;
	private String prom_name;
	private Byte prom_state;
	private Double discount_amount; 
	private Integer prom_achieve_number;
	
	
	public Integer getProm_achieve_number() {
		return prom_achieve_number;
	}
	public void setProm_achieve_number(Integer prom_achieve_number) {
		this.prom_achieve_number = prom_achieve_number;
	}
	public Integer getProm_id() {
		return prom_id;
	}
	public void setProm_id(Integer prom_id) {
		this.prom_id = prom_id;
	}
	public String getProm_name() {
		return prom_name;
	}
	public void setProm_name(String prom_name) {
		this.prom_name = prom_name;
	}
	public Byte getProm_state() {
		return prom_state;
	}
	public void setProm_state(Byte prom_state) {
		this.prom_state = prom_state;
	}
	public Double getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(Double discount_amount) {
		this.discount_amount = discount_amount;
	}
	
}

