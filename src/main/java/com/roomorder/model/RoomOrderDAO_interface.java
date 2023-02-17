package com.roomorder.model;

import java.util.List;

public interface RoomOrderDAO_interface {
	public void insert(RoomOrderVO roomOrderVO);

	public RoomOrderVO findByPrimaryKey(Integer room_order_id);

	public List<RoomOrderVO> getAll();
	

	public RoomOrderVO updateState(Integer room_order_id);

	public List<RoomOrderVO> getOrderByMember(Integer member_id);

	//抓狀態用
	public List<RoomOrderVO> getRoomOrderState(Byte room_order_state);

	
	// 最新訂房編號
	public List<RoomOrderVO> getVeryNew();
}
