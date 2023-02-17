package com.roomorder.model;

import java.util.List;

public class RoomOrderService {
	private RoomOrderDAO_interface dao;

	public RoomOrderService() {
		dao = new RoomOrderDAO();

	}

//新增
	public RoomOrderVO addRoomOrder(Integer member_id,Integer room_order_ttl_price) {
		// 宣告變數 創立物件
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setMember_id(member_id);
		roomOrderVO.setRoom_order_ttl_price(room_order_ttl_price);
		System.out.println(roomOrderVO.getMember_id());
		System.out.println(roomOrderVO.getRoom_order_ttl_price());
		dao.insert(roomOrderVO);
		return roomOrderVO;
	}

	public RoomOrderVO getOneRoomOrder(Integer room_order_id) {

		return dao.findByPrimaryKey(room_order_id);
	}
	
	
	public RoomOrderVO updateState(Integer room_order_id) {
		
		return dao.updateState(room_order_id);
	}

	public List<RoomOrderVO> getAll() {
		return dao.getAll();
	}
	public List<RoomOrderVO> getOrderByMember(Integer member_id) {
		return dao.getOrderByMember(member_id);
		
	}
	public List<RoomOrderVO> getRoomOrderState(Byte room_order_state) {
		return dao.getRoomOrderState(room_order_state);
		
	}
	
	//最新編號
	public List<RoomOrderVO> getVeryNew() {
		return dao.getVeryNew();
	}

	
}