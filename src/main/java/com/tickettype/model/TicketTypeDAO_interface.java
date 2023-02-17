package com.tickettype.model;

import java.util.List;


public interface TicketTypeDAO_interface {
	public void insert(TicketType tickettype);
	public void update(TicketType tickettype);
	public TicketType findByPrimaryKey(Byte tkt_type_id);
	public List<TicketType>getAll();

}
