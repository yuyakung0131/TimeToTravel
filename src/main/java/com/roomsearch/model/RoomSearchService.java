package com.roomsearch.model;


import java.util.List;

public class RoomSearchService {
	private RoomSearch_interface dao;

	public RoomSearchService() {
		dao = new RoomSearchDAO();
	}

	public List<RoomSearchVO> getEmptyRoomByFirmName(String firm_name, String start_date, String end_date) {
		return dao.getEmptyRoomByFirmName(firm_name, start_date, end_date);
	}
	public List<RoomSearchVO> getEmptyRoomByFirmAdd(String firm_operate_add, String start_date, String end_date) {
		return dao.getEmptyRoomByFirmAdd(firm_operate_add, start_date, end_date);
	}
	
	public List<RoomSearchVO> getEmptyRoomByOneFirm(Integer firm_id , String start_date, String end_date){
		return dao.getEmptyRoomByOneFirm(firm_id,  start_date, end_date);
	}
	
	public List<RoomSearchVO> getEmptyRoomByOneFirmByStartDate(Integer firm_id , String start_date){
		return dao.getEmptyRoomByOneFirmByStartDate(firm_id, start_date);
	}
	public RoomSearchVO getEmptyRoomByOneRoomByRoomType(Integer room_type_id , String start_date) {
		return dao.getEmptyRoomByOneRoomByRoomType(room_type_id, start_date);
	}
	public List<RoomSearchVO> getFirmByAddByStartDate(String firm_operate_add , String start_date){
		return dao.getFirmByAddByStartDate(firm_operate_add, start_date);
	}
	public List<RoomSearchVO> getFirmByAddNoDate(String firm_operate_add){
		return dao.getFirmByAddNoDate(firm_operate_add);
	}
	public List<RoomSearchVO> getFirmByNameNoDate(String firm_name){
		return dao.getFirmByNameNoDate(firm_name);
	}
	public List<RoomSearchVO> getByStartDate(String start_date){
		return dao.getByStartDate(start_date);
	}
}
