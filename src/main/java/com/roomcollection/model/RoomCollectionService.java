package com.roomcollection.model;

import java.sql.Timestamp;
import java.util.List;

public class RoomCollectionService {
	private RoomCollection_interface dao;

	public RoomCollectionService() {
		dao = new RoomCollectionDAO();
	}

	public RoomCollectionVO insert(Integer member_id, Integer room_type_id) {
		RoomCollectionVO roomCollectionVO = new RoomCollectionVO();
		roomCollectionVO.setMember_id(member_id);
		roomCollectionVO.setRoom_type_id(room_type_id);
		
		dao.insert(roomCollectionVO);
		return roomCollectionVO;
	}

	public void delete(Integer member_id, Integer room_type_id) {
		dao.delete(member_id, room_type_id);
	}

	public RoomCollectionVO getOneRoomCollection(Integer member_id,Integer room_type_id) {
		return dao.getOneRoomCollection(member_id, room_type_id);
	}
	
	public List<RoomCollectionVO> getCollectionByMember(Integer member_id) {
		return dao.getCollectionByMember(member_id);
	}

	public List<RoomCollectionVO> getAll() {
		return dao.getAll();
	}
	
	

}
