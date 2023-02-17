package com.tktitem.model;

import java.util.List;


public interface TktItemDAO_interface {
	
	public void insert(TktItem tktitem);
	public void update(TktItem tktitem);
	public TktItem findByPrimaryKey(Integer tkt_id,Integer tkt_order_id);
	public List<TktItem> findByOrderId(Integer tkt_order_id);
	public void delete(Integer tkt_id,Integer tkt_order_id);
	public List<TktItem>getAll();



}
