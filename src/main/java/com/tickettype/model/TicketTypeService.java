package com.tickettype.model;

import java.util.List;

public class TicketTypeService {
	
	private TicketTypeDAO_interface dao;
	
	public TicketTypeService() {
		dao=new TicketTypeDAO();
	}
	
	public TicketType addTicketTypeService(String tkt_type_name) {
		TicketType tickettype=new TicketType();
		tickettype.setTkt_type_name(tkt_type_name);
		dao.insert(tickettype);
		return tickettype;
	}
	
	
	public TicketType updateTicketTypeService(Byte tkt_type_id,String tkt_type_name) {
		TicketType tickettype=new TicketType();
		tickettype.setTkt_type_id(tkt_type_id);
		tickettype.setTkt_type_name(tkt_type_name);
		dao.update(tickettype);
		return tickettype;
	}
	
	public TicketType getOneTicketType(Byte tkt_type_id) {
		return dao.findByPrimaryKey(tkt_type_id);
	}
	
	public List<TicketType>getAll(){
		return dao.getAll();
	}

}
