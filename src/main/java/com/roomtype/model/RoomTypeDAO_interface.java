package com.roomtype.model;

import java.util.*;

public interface RoomTypeDAO_interface {
	public void insert(RoomTypeVO roomTypeVO);

	public void update(RoomTypeVO roomTypeVO);

	public void delete(Integer room_type_id);

	public RoomTypeVO findByPrimaryKey(Integer room_type_id);

	public List<RoomTypeVO> getRoomTypeByFirmID(Integer firm_id);
	
	public List<RoomTypeVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RoomTypeVO> getAll(Map<String, String[]> map); 
}
