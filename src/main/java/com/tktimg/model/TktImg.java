package com.tktimg.model;

import java.util.Arrays;
import java.util.Objects;

public class TktImg implements java.io.Serializable{
	
	private Integer tkt_img_id;
	private byte [] tkt_img;
	private Integer tkt_id;
	
	
	
	public byte[] getTkt_img() {
		return tkt_img;
	}
	public void setTkt_img(byte[] tkt_img) {
		this.tkt_img = tkt_img;
	}
	public Integer getTkt_img_id() {
		return tkt_img_id;
	}
	public void setTkt_img_id(Integer tkt_img_id) {
		this.tkt_img_id = tkt_img_id;
	}
	public Integer getTkt_id() {
		return tkt_id;
	}
	public void setTkt_id(Integer tkt_id) {
		this.tkt_id = tkt_id;
	}
	
	public com.ticket.model.Ticket getTicket() {
		com.ticket.model.TicketService ticketSvc=new com.ticket.model.TicketService();
		com.ticket.model.Ticket ticket=ticketSvc.getOneTicket(tkt_id);
		
		return ticket;
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
		TktImg other = (TktImg) obj;
		return Objects.equals(tkt_id, other.tkt_id);
	}
	
	
	

	
	
	


}

