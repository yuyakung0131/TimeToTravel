package com.ticketcomment.model;

import java.util.List;

public interface TicketCommentDAO_interface {
	public void insert(TicketComment ticketcomment);
	public void update(TicketComment ticketcomment);
	public TicketComment findByPrimaryKey(Integer ticket_comment_id);
	public void delete(Integer ticket_comment_id);
	public List<TicketComment>getAll();
	public Double getAllCommentScore(Integer tkt_id);
	public Integer getAllCommentCount(Integer tkt_id);

}
