package com.ticket.model;

import java.util.List;

public interface TicketDAO_interface {
	public void insert(Ticket ticket);
	public void update(Ticket ticket);
	public void updatebyAmount(Ticket ticket);
	public void updatebyComment(Ticket ticket);
    public void delete(Integer empno);
    public Ticket findByPrimaryKey(Integer tkt_id);
    public List<Ticket>getAll();
    public List<Ticket>findByType(Integer tkt_type_id);
    public List<Ticket>findBetweenPrice(Integer min, Integer max);
    public List<Ticket>findBySmallAmount(Integer tkt_amount);
    public List<Ticket>findBySmallDate(Integer tkt_date);
    public List<Ticket>findByCommentScore(Integer tkt_total_score);


}
