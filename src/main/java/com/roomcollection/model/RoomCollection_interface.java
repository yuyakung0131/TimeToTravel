package com.roomcollection.model;

import java.util.List;

public interface RoomCollection_interface {
	public void insert(RoomCollectionVO roomCollectionVO);
	
	public void delete(Integer member_id ,Integer room_type_id);
	
	public RoomCollectionVO getOneRoomCollection(Integer member_id,Integer room_type_id);

	public List<RoomCollectionVO> getCollectionByMember(Integer member_id);

	public List<RoomCollectionVO> getAll();
}
