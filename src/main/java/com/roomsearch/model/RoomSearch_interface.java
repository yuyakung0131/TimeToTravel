package com.roomsearch.model;

import java.sql.Timestamp;
import java.util.List;

public interface RoomSearch_interface {
public List<RoomSearchVO> getEmptyRoomByFirmName(String firm_name,String start_date,String end_date);
public List<RoomSearchVO> getEmptyRoomByFirmAdd(String firm_name,String start_date,String end_date);
public List<RoomSearchVO> getEmptyRoomByOneFirm(Integer firm_id , String start_date, String end_date);
public List<RoomSearchVO> getEmptyRoomByOneFirmByStartDate(Integer firm_id , String start_date);
public List<RoomSearchVO> getFirmByAddByStartDate(String firm_operate_add , String start_date);
public RoomSearchVO getEmptyRoomByOneRoomByRoomType(Integer room_type_id , String start_date);
public List<RoomSearchVO> getFirmByAddNoDate(String firm_operate_add);
public List<RoomSearchVO> getFirmByNameNoDate(String firm_name);
public List<RoomSearchVO> getByStartDate(String start_date);
}
