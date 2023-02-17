package com.firmtype.model;

public class FirmTypeVO implements java.io.Serializable {
	private Integer firmtype_id;
	private String firmtype_name;

	public Integer getFirmtype_id() {
		return firmtype_id;
	}

	public void setFirmtype_id(Integer firmtype_id) {
		this.firmtype_id = firmtype_id;
	}

	public String getFirmtype_name() {
		return firmtype_name;
	}

	public void setFirmtype_name(String firmtype_name) {
		this.firmtype_name = firmtype_name;
	}
}
