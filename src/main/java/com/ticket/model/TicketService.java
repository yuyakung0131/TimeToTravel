package com.ticket.model;

import java.util.List;



public class TicketService {
	private TicketDAO_interface dao;
	
	public TicketService() {
		dao=new TicketDAO();
	}
	
	public Ticket addTicket(Integer tkt_date, Byte tkt_type_id, String tkt_name, Integer tkt_price, Integer firm_id, Integer tkt_amount, String instruction, 
			Double tkt_total_score, Integer tkt_total_people) {
		
		Ticket ticket=new Ticket();
		ticket.setTkt_date(tkt_date);
		ticket.setTkt_type_id(tkt_type_id);
		ticket.setTkt_name(tkt_name);
		ticket.setTkt_price(tkt_price);
		ticket.setFirm_id(firm_id);
		ticket.setTkt_amount(tkt_amount);
		ticket.setInstruction(instruction);
		ticket.setTkt_total_score(tkt_total_score);
		ticket.setTkt_total_people(tkt_total_people);
		
		dao.insert(ticket);
		
		return ticket;
		
	}
	
	public Ticket updateTicket(Integer tkt_id,Integer tkt_date, Byte tkt_type_id, String tkt_name, Integer tkt_price, Integer firm_id, Integer tkt_amount, String instruction, 
			Double tkt_total_score, Integer tkt_total_people) {
		
		Ticket ticket=new Ticket();
		ticket.setTkt_id(tkt_id);
		ticket.setTkt_date(tkt_date);
		ticket.setTkt_type_id(tkt_type_id);
		ticket.setTkt_name(tkt_name);
		ticket.setTkt_price(tkt_price);
		ticket.setFirm_id(firm_id);
		ticket.setTkt_amount(tkt_amount);
		ticket.setInstruction(instruction);
		ticket.setTkt_total_score(tkt_total_score);
		ticket.setTkt_total_people(tkt_total_people);
		
		dao.update(ticket);
		
		return ticket;
		
	}
	
	
	
	public Ticket updatebyAmount(Integer tkt_id,Integer tkt_amount) {
		Ticket ticket=new Ticket();
		ticket.setTkt_id(tkt_id);
		ticket.setTkt_amount(tkt_amount);
		dao.updatebyAmount(ticket);
		
		return ticket;
	}
	
	public Ticket updatebyComment(Integer tkt_id,Double tkt_total_score,Integer tkt_total_people) {
		Ticket ticket=new Ticket();
		ticket.setTkt_id(tkt_id);
		ticket.setTkt_total_people(tkt_total_people);
		ticket.setTkt_total_score(tkt_total_score);
		dao.updatebyComment(ticket);
		
		return ticket;
	}
	
	
	
	public Ticket getOneTicket(Integer tkt_id) {
		return dao.findByPrimaryKey(tkt_id);
	}
	
	public void deleteTicket(Integer tkt_id) {
		dao.delete(tkt_id);
	}	
	public List<Ticket> getAll() {
		return dao.getAll();
	}
	
	public List<Ticket>findByType(Integer tkt_type_id){
		return dao.findByType(tkt_type_id);
	}
	
	 public List<Ticket>findBetweenPrice(Integer min, Integer max){
		 return dao.findBetweenPrice(min, max);
	 }
	 
	 
	 public List<Ticket>findBySmallAmount(Integer tkt_amount){
		 return dao.findBySmallAmount(tkt_amount);
	 }
	 
		 
	 
	public List<Ticket>findBySmallDate(Integer tkt_date){
		return dao.findBySmallDate(tkt_date);
	}
	
	
	public List<Ticket>findByCommentScore(Integer tkt_total_score){
		return dao.findByCommentScore(tkt_total_score);
	}
	

}
