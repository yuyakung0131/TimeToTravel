package com.ticketpromote.model;

import java.math.BigDecimal;
import java.util.List;

import com.ticket.model.Ticket;

public class TicketPromoteService {
	
	private TicketPromoteDAO_interface dao;
	
	public TicketPromoteService() {
		dao=new TicketPromoteDAO();
	}
	
	public TicketPromote addTicketPromote(String prom_name, Byte prom_state, Double discount_amount, Integer prom_achieve_number) {
		
		TicketPromote ticketpromote = new TicketPromote();
		ticketpromote.setProm_name(prom_name);
		ticketpromote.setProm_state(prom_state);
		ticketpromote.setDiscount_amount(discount_amount);
		ticketpromote.setProm_achieve_number(prom_achieve_number);
		
		dao.insert(ticketpromote);
		
		return ticketpromote;
		
	}
	
	public TicketPromote updateTicketPromote(Integer prom_id, String prom_name, Byte prom_state, Double discount_amount, Integer prom_achieve_number ) {
		
		TicketPromote ticketpromote = new TicketPromote();
		
		ticketpromote.setProm_id(prom_id);
		ticketpromote.setProm_name(prom_name);
		ticketpromote.setProm_state(prom_state);
		ticketpromote.setDiscount_amount(discount_amount);
		ticketpromote.setProm_achieve_number(prom_achieve_number);

		
		dao.update(ticketpromote);
		
		return ticketpromote;
		
	}
	
	public TicketPromote getOneTicketPromote(Integer prom_id) {
		return dao.findByPrimaryKey(prom_id);
	}
	
	public List<TicketPromote> getAll() {
		return dao.getAll();
	}
	
	
	public List<TicketPromote> findByPromState(Byte prom_state){
		return dao.findByPromState(prom_state);
	}
	
	public List<TicketPromote> findByNumber(Integer prom_achieve_number){
		return dao.findByNumber(prom_achieve_number);
	}
	
	public void delete(Integer prom_id) {
		dao.delete(prom_id);
	}
	

}
