package com.shoppinglist.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ShoppingList implements java.io.Serializable{
	

	private Integer  tkt_id;
	private Integer member_id;
	private Timestamp cart_fav_date;
	private Integer tkt_amount;
	
	public Integer getTkt_amount() {
		return tkt_amount;
	}
	public void setTkt_amount(Integer tkt_amount) {
		this.tkt_amount = tkt_amount;
	}
	public Integer getTkt_id() {
		return tkt_id;
	}
	public void setTkt_id(Integer tkt_id) {
		this.tkt_id = tkt_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public Timestamp getCart_fav_date() {
		return cart_fav_date;
	}
	public void setCart_fav_date(Timestamp cart_fav_date) {
		this.cart_fav_date = cart_fav_date;
	}
	
	
	public com.ticket.model.Ticket getTicket(){
		com.ticket.model.TicketService ticketSvc=new com.ticket.model.TicketService();
		com.ticket.model.Ticket ticket =ticketSvc.getOneTicket(tkt_id);
		return ticket;
	}
	
	
	public com.member.model.MemberVO getMember(){
		com.member.model.MemberService memberSvc=new com.member.model.MemberService();
		com.member.model.MemberVO member=memberSvc.getOneMember_Ticket(member_id);
		return member;
	}
	@Override
	public int hashCode() {
		return Objects.hash(member_id, tkt_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingList other = (ShoppingList) obj;
		return Objects.equals(member_id, other.member_id) && Objects.equals(tkt_id, other.tkt_id);
	}

	
	
	
	
}

