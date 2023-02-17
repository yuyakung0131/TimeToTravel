package com.ticketcomment.model;

import java.util.List;

import com.ticket.model.Ticket;

public class TicketCommentService {
	
	private TicketCommentDAO_interface dao;
	
	public TicketCommentService() {
		dao= new TicketCommentDAO();
	}
	
	public TicketComment addTicketComment(Integer tkt_id,Integer member_id, String ticket_comment_content, 
			Double tkt_total_score) {
		
		TicketComment ticketcomment = new TicketComment();
		ticketcomment.setTkt_id(tkt_id);
		ticketcomment.setMember_id(member_id);
		ticketcomment.setTicket_comment_content(ticket_comment_content);
		ticketcomment.setTkt_total_score(tkt_total_score);
		
		dao.insert(ticketcomment);
		
		return ticketcomment;
		
		
	}
	
	
	public TicketComment updateTicketComment(Integer ticket_comment_id,Integer tkt_id,Integer member_id, String ticket_comment_content, 
			Double tkt_total_score) {
		
		TicketComment ticketcomment = new TicketComment();
		ticketcomment.setTicket_comment_id(ticket_comment_id);
		ticketcomment.setTkt_id(tkt_id);
		ticketcomment.setMember_id(member_id);
		ticketcomment.setTicket_comment_content(ticket_comment_content);
		ticketcomment.setTkt_total_score(tkt_total_score);
		
		dao.update(ticketcomment);
		
		return ticketcomment;
		
		
	}
	
	
	public TicketComment getOneTicketComment(Integer ticket_comment_id) {
		return dao.findByPrimaryKey(ticket_comment_id);
	}
	
	
	public void deleteTicketComment(Integer ticket_comment_id) {
		dao.delete(ticket_comment_id);
	}
	
	
	
	public List<TicketComment> getAll() {
		return dao.getAll();
	}
	
	public Double getAllCommentScore(Integer tkt_id) {
		return dao.getAllCommentScore(tkt_id);
	}
	
	public Integer getAllCommentCount(Integer tkt_id) {
		return dao.getAllCommentCount(tkt_id);
	}

}
