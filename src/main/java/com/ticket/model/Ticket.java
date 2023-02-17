package com.ticket.model;

import java.util.Objects;

public class Ticket implements java.io.Serializable {
	
	

	private Integer tkt_id;
	private Integer tkt_date;
	private Byte tkt_type_id;
	private String tkt_name;
	private Integer tkt_price;
	private Integer firm_id;
	private Integer tkt_amount;
	private String instruction;
	private Double tkt_total_score;
	private Integer tkt_total_people;
	
	
	public Integer getTkt_id() {
		return tkt_id;
	}
	public void setTkt_id(Integer tkt_id) {
		this.tkt_id = tkt_id;
	}
	public Integer getTkt_date() {
		return tkt_date;
	}
	public void setTkt_date(Integer tkt_date) {
		this.tkt_date = tkt_date;
	}
	public Byte getTkt_type_id() {
		return tkt_type_id;
	}
	public void setTkt_type_id(Byte tkt_type_id) {
		this.tkt_type_id = tkt_type_id;
	}
	public String getTkt_name() {
		return tkt_name;
	}
	public void setTkt_name(String tkt_name) {
		this.tkt_name = tkt_name;
	}
	public Integer getTkt_price() {
		return tkt_price;
	}
	public void setTkt_price(Integer tkt_price) {
		this.tkt_price = tkt_price;
	}
	public Integer getFirm_id() {
		return firm_id;
	}
	public void setFirm_id(Integer firm_id) {
		this.firm_id = firm_id;
	}
	public Integer getTkt_amount() {
		return tkt_amount;
	}
	public void setTkt_amount(Integer tkt_amount) {
		this.tkt_amount = tkt_amount;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public Double getTkt_total_score() {
		return tkt_total_score;
	}
	public void setTkt_total_score(Double tkt_total_score) {
		this.tkt_total_score = tkt_total_score;
	}
	public Integer getTkt_total_people() {
		return tkt_total_people;
	}
	public void setTkt_total_people(Integer tkt_total_people) {
		this.tkt_total_people = tkt_total_people;
	}
	
	public com.firm.model.FirmVO getFirm(){
		com.firm.model.FirmService firmSvc=new com.firm.model.FirmService();
		com.firm.model.FirmVO firm =firmSvc.getOneFirm_Ticket(firm_id);
		return firm;
		
	}
	
	public com.tickettype.model.TicketType getTicketType() {
		com.tickettype.model.TicketTypeService tickettypeSvc = new com.tickettype.model.TicketTypeService();
		com.tickettype.model.TicketType tickettype=tickettypeSvc.getOneTicketType(tkt_type_id);
		return tickettype;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(tkt_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(tkt_id, other.tkt_id);
	}
	


}

