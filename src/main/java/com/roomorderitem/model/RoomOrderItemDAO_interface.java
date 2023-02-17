package com.roomorderitem.model;

import java.util.List;

public interface RoomOrderItemDAO_interface {
	public void insert(RoomOrderItemVO roomOrderItemVO);

	public RoomOrderItemVO findByPrimaryKey(Integer room_order_id);

	public List<RoomOrderItemVO> getAll();
	
	public List<RoomOrderItemVO> getAllLong();
	
	//訂單 的訂單明細
	public List<RoomOrderItemVO> getOneForOrderId(Integer room_order_id);

	
	
}
