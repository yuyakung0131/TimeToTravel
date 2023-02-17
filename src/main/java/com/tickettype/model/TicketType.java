package com.tickettype.model;

public class TicketType implements java.io.Serializable{
	
	private Byte tkt_type_id;
	private String tkt_type_name;
	
	public Byte getTkt_type_id() {
		return tkt_type_id;
	}
	public void setTkt_type_id(Byte tkt_type_id) {
		this.tkt_type_id = tkt_type_id;
	}
	public String getTkt_type_name() {
		return tkt_type_name;
	}
	public void setTkt_type_name(String tkt_type_name) {
		this.tkt_type_name = tkt_type_name;
	}

}
