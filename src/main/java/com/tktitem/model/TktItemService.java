package com.tktitem.model;

import java.time.LocalDateTime;
import java.util.List;


public class TktItemService {
	
	private TktItemDAO_interface dao;
	
	public TktItemService() {
		
		dao=new TktItemDAO();
	}
	
	public TktItem addTktItem(Integer tkt_id, Integer tkt_order_id, Integer amount, 
			Integer tkt_price, LocalDateTime tkt_deadline) {
		
		TktItem tktitem=new TktItem();
		tktitem.setTkt_id(tkt_id);
		tktitem.setTkt_order_id(tkt_order_id);
		tktitem.setAmount(amount);
		tktitem.setTkt_price(tkt_price);
		tktitem.setTkt_deadline(tkt_deadline);
		
		dao.insert(tktitem);
		
		return tktitem;
		
		
	}
	
	
	public TktItem updateTktItem(Integer tkt_id, Integer tkt_order_id, Integer amount, 
			Integer tkt_price, LocalDateTime tkt_deadline) {
		
		TktItem tktitem=new TktItem();
		tktitem.setTkt_id(tkt_id);
		tktitem.setTkt_order_id(tkt_order_id);
		tktitem.setAmount(amount);
		tktitem.setTkt_price(tkt_price);
		tktitem.setTkt_deadline(tkt_deadline);
		
		dao.update(tktitem);
		
		return tktitem;
		
	}
	
	public TktItem getOneTktItem(Integer tkt_id,Integer tkt_order_id) {
		return dao.findByPrimaryKey(tkt_id,tkt_order_id);
	}
	
	public List<TktItem> getAll() {
		return dao.getAll();
	}
	
	public List<TktItem> getAllOrder(Integer tkt_order_id){
		return dao.findByOrderId(tkt_order_id);
	}
	
	public void deleteTktItem(Integer tkt_id,Integer tkt_order_id) {
		dao.delete(tkt_id, tkt_order_id); 
	}
	
	
	
}
