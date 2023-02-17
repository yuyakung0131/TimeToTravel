package com.tktitem.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class TktItem implements java.io.Serializable{

	
	private Integer tkt_id;
	private Integer tkt_order_id;
	private Integer amount;
	private Integer tkt_price;
	private LocalDateTime tkt_deadline;
	
	
	public Integer getTkt_id() {
		return tkt_id;
	}
	public void setTkt_id(Integer tkt_id) {
		this.tkt_id = tkt_id;
	}
	public Integer getTkt_order_id() {
		return tkt_order_id;
	}
	public void setTkt_order_id(Integer tkt_order_id) {
		this.tkt_order_id = tkt_order_id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getTkt_price() {
		return tkt_price;
	}
	public void setTkt_price(Integer tkt_price) {
		this.tkt_price = tkt_price;
	}
	public LocalDateTime getTkt_deadline() {
		return tkt_deadline;
	}
	public void setTkt_deadline(LocalDateTime tkt_deadline) {
		this.tkt_deadline = tkt_deadline;
	}
	
	public com.tktorder.model.TktOrder getTktOrder(){
		com.tktorder.model.TktOrderService tktorderSvc = new com.tktorder.model.TktOrderService();
		com.tktorder.model.TktOrder tktorder=tktorderSvc.getOneTktOrder(tkt_order_id);
		return tktorder;
	}
	
	 public com.ticket.model.Ticket getTicket() {
		    com.ticket.model.TicketService ticketSvc = new com.ticket.model.TicketService();
		    com.ticket.model.Ticket ticket = ticketSvc.getOneTicket(tkt_id);
		    return ticket;
	    }
	@Override
	public String toString() {
		
	    com.ticket.model.TicketService ticketSvc = new com.ticket.model.TicketService();
	    com.ticket.model.Ticket ticket = ticketSvc.getOneTicket(tkt_id);
		
		
		return "票券名稱=" + ticket.getTkt_name() + ", 數量=" + amount + "張, 票價="
				+ tkt_price + "元, 票券截止日期=" + tkt_deadline ;
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
		TktItem other = (TktItem) obj;
		return Objects.equals(tkt_id, other.tkt_id);
	}

	 
	
	

	

}

