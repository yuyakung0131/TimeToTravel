package com.ticketpromote.model;

import java.util.List;


public interface TicketPromoteDAO_interface {
	public void insert(TicketPromote ticketpromote);
	public void update(TicketPromote ticketpromote);
	public TicketPromote findByPrimaryKey(Integer prom_id);
	public void delete(Integer prom_id);
	public List<TicketPromote>getAll();
	public List<TicketPromote> findByPromState(Byte prom_state);
	public List<TicketPromote> findByNumber(Integer prom_achieve_number);

}
