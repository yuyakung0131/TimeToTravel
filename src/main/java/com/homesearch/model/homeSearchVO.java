package com.homesearch.model;

import java.io.Serializable;

import javax.mail.search.FromStringTerm;

public class homeSearchVO implements java.io.Serializable{
	private Integer firm_id;
	private Integer firm_type;
	private String firmtype_name;
	private Integer product;
	private String title;

	public Integer getFirm_id() {
		return firm_id;
	}

	public void setFirm_id(Integer firm_id) {
		this.firm_id = firm_id;
	}

	public Integer getFirm_type() {
		return firm_type;
	}

	public void setFirm_type(Integer firm_type) {
		this.firm_type = firm_type;
	}

	public String getFirmtype_name() {
		return firmtype_name;
	}

	public void setFirmtype_name(String firmtype_name) {
		this.firmtype_name = firmtype_name;
		// firm_name AS title, (SELECT 2) AS firm_type FROM time_to_travel.firm WHERE
		// firmtype_id = 2
		// from firm
		// JOIN time_to_travel.firm_type ft ON ft.firmtype_id = a.firm_type
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		// tkt_name AS title, (SELECT 1) AS firm_type FROM time_to_travel.ticket
		// from ticket
	}



}
