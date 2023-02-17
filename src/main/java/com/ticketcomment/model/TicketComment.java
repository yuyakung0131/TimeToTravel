package com.ticketcomment.model;

import java.sql.Timestamp;

import com.ticket.model.TicketService;

public class TicketComment implements java.io.Serializable {
	
	
	private Integer ticket_comment_id;
	private Integer tkt_id;
	private Integer member_id;
	private String ticket_comment_content;
	private Double tkt_total_score;
	private Timestamp ticket_comment_time;
	
	
	public Integer getTicket_comment_id() {
		return ticket_comment_id;
	}
	public void setTicket_comment_id(Integer ticket_comment_id) {
		this.ticket_comment_id = ticket_comment_id;
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
	public String getTicket_comment_content() {
		return ticket_comment_content;
	}
	public void setTicket_comment_content(String ticket_comment_content) {
		this.ticket_comment_content = ticket_comment_content;
	}
	public Double getTkt_total_score() {
		return tkt_total_score;
	}
	public void setTkt_total_score(Double tkt_total_score) {
		this.tkt_total_score = tkt_total_score;
	}
	public Timestamp getTicket_comment_time() {
		return ticket_comment_time;
	}
	public void setTicket_comment_time(Timestamp ticket_comment_time) {
		this.ticket_comment_time = ticket_comment_time;
	}
	
	public com.ticket.model.Ticket getTicket(){
		com.ticket.model.TicketService ticketSvc=new com.ticket.model.TicketService();
		com.ticket.model.Ticket ticket=ticketSvc.getOneTicket(tkt_id);
		return ticket;
	}
	
	public com.member.model.MemberVO getMember(){
		com.member.model.MemberService memberSvc=new com.member.model.MemberService();
		com.member.model.MemberVO memberVO=memberSvc.getOneMember_Ticket(member_id);
		return memberVO;
	}

	
	
	
	



}

