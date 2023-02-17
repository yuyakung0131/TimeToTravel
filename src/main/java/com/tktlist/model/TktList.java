package com.tktlist.model;

import java.sql.Timestamp;
import java.util.Objects;

public class TktList implements java.io.Serializable{
	
	
	public TktList() {
		tkt_id=0;
		member_id=0;
		
	}

	private Integer tkt_id;
	private Integer member_id;
	private Timestamp ticket_fav_date;
	
	
	
	
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
	public Timestamp getTicket_fav_date() {
		return ticket_fav_date;
	}
	public void setTicket_fav_date(Timestamp ticket_fav_date) {
		this.ticket_fav_date = ticket_fav_date;
	}
	
	public com.member.model.MemberVO getMemberVO(){
		com.member.model.MemberService memberSvc = new com.member.model.MemberService();
		com.member.model.MemberVO member=memberSvc.getOneMember_Ticket(member_id);
		return member;
	}
	
	public com.ticket.model.Ticket getTicket(){
		com.ticket.model.TicketService ticketSvc=new com.ticket.model.TicketService();
		com.ticket.model.Ticket ticket =ticketSvc.getOneTicket(tkt_id);
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
		TktList other = (TktList) obj;
		return Objects.equals(tkt_id, other.tkt_id);
	}
	
	
	

}

